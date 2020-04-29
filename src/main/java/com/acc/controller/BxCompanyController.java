package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxCompany;
import com.acc.model.UserInfo;
import com.acc.service.IBxCompanyService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.vo.CompanyQuery;
import com.acc.vo.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/company")
public class BxCompanyController {
	private static Logger _logger = LoggerFactory.getLogger(BxCompanyController.class);

	@Autowired
	private IBxCompanyService bxCompanyService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * 企业风采信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCompanyList", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getCompanyList(ModelAndView mav,final HttpServletRequest request, @ModelAttribute CompanyQuery query) throws IOException {
        Map<String,Object> model = new HashMap<String, Object>();
	    try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            Page<BxCompany> page = null;
            if(staff!=null){
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                query.setSortColumns("c.CREATE_DATE desc");
                if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                    Map<Integer, UserInfo> userInfoDictMap = userInfoService.getAllMap();
                    model.put("userInfoDictMap", userInfoDictMap);
                    List<UserInfo> userInfoList = userInfoService.getAll();
                    model.put("userInfoList", userInfoList);
                    page = bxCompanyService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxCompanyService.selectPage(query);
                    }
                }
                for (BxCompany bxCompany:page.getResult()){
                    bxCompany.setImageUrl(basePath+ Constants.companyImgPath+bxCompany.getMemberId()+"/"+bxCompany.getImageUrl());
                }
            }
            model.put("page", page);
            model.put("query", query);
            model.put("result", request.getParameter("result"));
            mav=new ModelAndView("/company/companyList", model);
        } catch (Exception e) {
            _logger.error("getCompanyList 失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
            e.printStackTrace();
        }
        return mav;
	}
    /**
     * 后台管理--更新个人荣誉榜图片
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public ModelAndView updateById(ModelAndView mav, final HttpServletRequest request, @ModelAttribute BxCompany bxCompany, @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        Map<String,Object> model = mav.getModel();
        String result;
        try {
            if (bxCompany != null) {
                if(file!=null && file.length>0){
                    HttpSession session = request.getSession();
                    UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                    if(staff!=null){
                        bxCompany.setModifierId(String.valueOf(staff.getId()));
                        if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                            bxCompany.setImageUrl(null);
                            bxCompanyService.updateById(bxCompany);
                            result = "更新成功!";
                        }else{
                            BxCompany oldBxCompany = bxCompanyService.getCompanyById(bxCompany.getId()+"");
                            bxCompany.setMemberId(oldBxCompany.getMemberId());
                            String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                            String fileSavePath=path + Constants.companyImgPath + bxCompany.getMemberId() + "/";
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            if(re==0){
                                String imgUrl = null;
                                if(bxCompany.getImageUrl()!=null && !"".equals(bxCompany.getImageUrl())){
                                    imgUrl = bxCompany.getImageUrl().split("/")[bxCompany.getImageUrl().split("/").length-1];
                                }
                                new File(fileSavePath+imgUrl).delete();
                                List<String> list = (List<String>)mapImg.get("list");
                                if(list!=null && list.size()>0){
                                    bxCompany.setImageUrl(list.get(0));
                                    //操作新的文件
                                    bxCompanyService.updateById(bxCompany);
                                }
                                result = "更新成功!";
                            }else if(re==-1){
                                result = "没有文件!";
                            }else{
                                result = "上传文件有问题!";
                            }
                        }
                    }else{
                        result = "未登录";
                    }
                }else{
                    result = "没有文件";
                }
            } else {
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            result = "添加/更新失败，请联系管理员!";
            _logger.error("updateMemberById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        model.put("result", result);
        mav.setViewName("redirect:/company/getCompanyList");
        return mav;
    }
    /**
     * 管理端--上传企业风采信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public ModelAndView addCompany(ModelAndView mav,final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxCompany bxCompany,@RequestParam(value="file",required=true) MultipartFile[] file) throws IOException {
        Map<String, Object> model = mav.getModel();
        String result;
        try{
            if (bxCompany != null) {
                if(file!=null && file.length>0){
                    HttpSession session = request.getSession();
                    UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                    if(bxCompany.getMemberId()==0){
                        bxCompany.setMemberId(staff.getId());
                    }
                    bxCompany.setCreaterId(staff.getId());
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.companyImgPath + bxCompany.getMemberId() + "/";
                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                    if(re==0){
                        if(re==0){
                            List<String> list = (List<String>)mapImg.get("list");
                            if(list!=null && list.size()>0){
                                bxCompany.setImageUrl(list.get(0));
                                bxCompanyService.insert(bxCompany);
                            }
                        }
                        result = "添加成功!";
                    }else if(re==-1){
                        result = "没有文件";
                    }else{
                        result = "上传文件有问题";
                    }
                }else{
                    result = "没有文件";
                }
            }else{
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            result = "添加失败，请联系管理员!";
            _logger.error("addCompany失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        model.put("result", result);
        mav.setViewName("redirect:/company/getCompanyList");
        return mav;
    }
}
