package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxRecruit;
import com.acc.model.UserInfo;
import com.acc.service.IBxRecruitService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.vo.Page;
import com.acc.vo.RecruitQuery;
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
@RequestMapping(value="/recruit")
public class BxRecruitController {
	private static Logger _logger = LoggerFactory.getLogger(BxRecruitController.class);

	@Autowired
	private IBxRecruitService bxRecruitService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * 招聘信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getRecruitList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getRecruitList(ModelAndView mav, final HttpServletRequest request, final HttpServletResponse response,@ModelAttribute RecruitQuery query) throws IOException {
        Map<String,Object> model = new HashMap<String, Object>();
	    try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            Page<BxRecruit> page = null;
            if(staff!=null){
                query.setSortColumns("c.CREATE_DATE desc");
                if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                    Map<Integer, UserInfo> userInfoDictMap = userInfoService.getAllMap();
                    model.put("userInfoDictMap", userInfoDictMap);
                    List<UserInfo> userInfoList = userInfoService.getAll();
                    model.put("userInfoList", userInfoList);
                    page = bxRecruitService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxRecruitService.selectPage(query);
                    }
                }
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                for (BxRecruit bxRecruit:page.getResult()){
                    bxRecruit.setImageUrl(basePath+ Constants.recruitImgPath+bxRecruit.getMemberId()+"/"+bxRecruit.getImageUrl());
                }
            }
            if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                model.put("page", page);
                model.put("query", query);
                mav=new ModelAndView("/recruit/recruitManageList", model);
            }else{
                if(page.getResult()!=null && page.getResult().size()>0){
                    model.put("bxRecruit",page.getResult().get(0));
                }
                mav=new ModelAndView("/recruit/recruitList", model);
            }
        } catch (Exception e) {
            _logger.error("bxRecruitService失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
            e.printStackTrace();
        }
        return mav;
	}
    /**
     * 管理端--上传招聘信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/addRecruit", method = RequestMethod.POST)
    public ModelAndView addRecruit(ModelAndView mav,final HttpServletRequest request, @ModelAttribute BxRecruit bxRecruit,@RequestParam(value="file") MultipartFile[] file) throws IOException {
        Map<String, Object> model = new HashMap<String, Object>();
        String result="";
        int status = 0;
        try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            if (staff!=null && bxRecruit != null) {
                if(file!=null && file.length>0){
                    if(bxRecruit.getMemberId()==0){
                        bxRecruit.setMemberId(staff.getId());
                    }
                    bxRecruit.setCreaterId(staff.getId());
                    if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                        bxRecruit.setImageUrl(null);
                        bxRecruitService.updateById(bxRecruit);
                    }else{
                        //删除图片
                        String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                        String fileSavePath=path + Constants.recruitImgPath + bxRecruit.getMemberId() + "/";
                        String imgUrl = null;
                        if(bxRecruit.getImageUrl()!=null && !"".equals(bxRecruit.getImageUrl())){
                            imgUrl = bxRecruit.getImageUrl().split("/")[bxRecruit.getImageUrl().split("/").length-1];
                        }
                        new File(fileSavePath+imgUrl).delete();
                        //保存新的图片
                        Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                        int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                        if(re==0){
                            List<String> list = (List<String>)mapImg.get("list");
                            if(list!=null && list.size()>0){
                                bxRecruit.setImageUrl(list.get(0));
                                if(bxRecruit.getId()!=0){
                                    bxRecruitService.updateById(bxRecruit);
                                }else{
                                    bxRecruitService.insert(bxRecruit);
                                }
                            }
                            result = "添加成功!";
                        }else if(re==-1){
                            result = "没有文件";
                        }else{
                            result = "上传文件有问题";
                        }
                    }
                }else{
                    result = "没有文件";
                }
            }else{
                status = 1;
                result = "参数有误，请联系管理员!";
            }
            model.put("status", status);
            model.put("result", result);
            mav.setViewName("redirect:/recruit/getRecruitList");
        } catch (Exception e) {
            status = -1;
            result = "添加失败，请联系管理员!";
            _logger.error("addRecruit失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        return mav;
    }
}
