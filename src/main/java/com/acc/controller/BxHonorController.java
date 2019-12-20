package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxHonor;
import com.acc.model.UserInfo;
import com.acc.service.IBxHonorService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.vo.HonorQuery;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/honor")
public class BxHonorController {
	private static Logger _logger = LoggerFactory.getLogger(BxHonorController.class);

	@Autowired
	private IBxHonorService bxHonorService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * 荣誉信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getHonorList", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getHonorList(ModelAndView mav, final HttpServletRequest request,@ModelAttribute HonorQuery query) throws IOException {
        Map<String,Object> model = new HashMap<String, Object>();
	    try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            Page<BxHonor> page = null;
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
                    page = bxHonorService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxHonorService.selectPage(query);
                    }
                }
                String url;
                for (BxHonor bxHonor:page.getResult()){
                    url = basePath+ Constants.honorImgPath+bxHonor.getMemberId()+"/"+bxHonor.getImageUrl();
                    bxHonor.setImageUrl(url);
                }
            }
            model.put("page", page);
            model.put("query", query);
            mav=new ModelAndView("/honor/honorList", model);
        } catch (Exception e) {
            _logger.error("bxHonorService失败：" + ExceptionUtil.getMsg(e));
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
    public ModelAndView updateById(ModelAndView mav, final HttpServletRequest request, @ModelAttribute BxHonor bxHonor, @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        Map<String,Object> model = new HashMap<String, Object>();
        String result;
        int status = 0;
        try {
            if (bxHonor != null) {
                if(file!=null && file.length>0){
                    HttpSession session = request.getSession();
                    UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                    if(staff!=null){
                        bxHonor.setModifierId(String.valueOf(staff.getId()));
                        if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                            bxHonor.setImageUrl(null);
                            bxHonorService.updateById(bxHonor);
                        }else{
                            BxHonor oldBxHonor = bxHonorService.getHonorById(bxHonor.getId());
                            bxHonor.setMemberId(oldBxHonor.getMemberId());
                            String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                            String fileSavePath=path + Constants.honorImgPath + bxHonor.getMemberId() + "/";
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            if(re==0){
                                String imgUrl = null;
                                if(bxHonor.getImageUrl()!=null && !"".equals(bxHonor.getImageUrl())){
                                    imgUrl = bxHonor.getImageUrl().split("/")[bxHonor.getImageUrl().split("/").length-1];
                                }
                                new File(fileSavePath+imgUrl).delete();
                                List<String> list = (List<String>)mapImg.get("list");
                                if(list!=null && list.size()>0){
                                    bxHonor.setImageUrl(list.get(0));
                                    //操作新的文件
                                    bxHonorService.updateById(bxHonor);
                                }
                                result = "添加/更新成功!";
                            }else if(re==-1){
                                status = 3;
                                result = "没有文件!";
                            }else{
                                status = 2;
                                result = "上传文件有问题!";
                            }
                        }
                    }
                }else{
                    status = 3;
                    result = "没有文件";
                }
            } else {
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "添加/更新失败，请联系管理员!";
            _logger.error("updateMemberById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        model.put("status", status);
        return getHonorList(mav,request,new HonorQuery());
    }

    /**
     * 管理端--上传荣誉信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/addHonor", method = RequestMethod.POST)
    public ModelAndView addHonor(ModelAndView mav, final HttpServletRequest request, @ModelAttribute BxHonor bxHonor, @RequestParam(value="file") MultipartFile[] file) throws IOException {
        Map<String, Object> model = new HashMap<String, Object>();
        String result;
        int status = 0;
        try{
            if (bxHonor != null) {
                if(file!=null && file.length>0){
                    HttpSession session = request.getSession();
                    UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                    if(bxHonor.getMemberId()==0){
                        bxHonor.setMemberId(staff.getId());
                    }
                    bxHonor.setCreaterId(staff.getId());
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.honorImgPath + bxHonor.getMemberId() + "/";
                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                    if(re==0){
                        if(re==0){
                            List<String> list = (List<String>)mapImg.get("list");
                            if(list!=null && list.size()>0){
                                bxHonor.setImageUrl(list.get(0));
                                bxHonorService.insert(bxHonor);
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
                status = 1;
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            result = "添加失败，请联系管理员!";
            _logger.error("addRecruit失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        model.put("status", status);
        model.put("result", result);
        return getHonorList(mav, request,new HonorQuery());
    }
}
