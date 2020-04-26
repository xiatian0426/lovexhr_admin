package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxProVideo;
import com.acc.model.UserInfo;
import com.acc.service.IBxProVideoService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.vo.Page;
import com.acc.vo.ProVideoQuery;
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
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/proVideo")
public class BxProVideoController {
	private static Logger _logger = LoggerFactory.getLogger(BxProVideoController.class);

	@Autowired
	private IBxProVideoService bxProVideoService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * 用户视频信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getProVideoList", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getProVideoList(ModelAndView mav, final HttpServletRequest request,@ModelAttribute ProVideoQuery query) throws IOException {
        Map<String, Object> model = mav.getModel();
	    try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            Page<BxProVideo> page = null;
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
                    page = bxProVideoService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxProVideoService.selectPage(query);
                    }
                }
                String url;
                for (BxProVideo bxProVideo:page.getResult()){
                    url = basePath+ Constants.userVideoPath+bxProVideo.getMemberId()+"/"+bxProVideo.getVideoUrl();
                    bxProVideo.setVideoUrl(url);
                }
            }
            if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                model.put("page", page);
                model.put("query", query);
                mav=new ModelAndView("/proVideo/proVideoManageList", model);
            }else{
                if(page.getResult()!=null && page.getResult().size()>0){
                    model.put("bxProVideo",page.getResult().get(0));
                }
                mav=new ModelAndView("/proVideo/proVideoList", model);
            }
        } catch (Exception e) {
            _logger.error("进入用户视频页失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
        }
        return mav;
	}
    /**
     * 后台管理--更新用户视频
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public ModelAndView updateById(ModelAndView mav, final HttpServletRequest request, @ModelAttribute BxProVideo bxProVideo, @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        Map<String,Object> model = mav.getModel();
        String result;
        try {
            if (bxProVideo != null) {
                if(file!=null && file.length>0){
                    HttpSession session = request.getSession();
                    UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                    if(staff!=null){
                        bxProVideo.setModifierId(String.valueOf(staff.getId()));
                        if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                            bxProVideo.setVideoUrl(null);
                            bxProVideoService.updateById(bxProVideo);
                            result = "更新成功!";
                        }else{
                            BxProVideo oldProVideo = bxProVideoService.getProVideoById(bxProVideo.getId());
                            bxProVideo.setMemberId(oldProVideo.getMemberId());
                            String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                            String fileSavePath=path + Constants.userVideoPath + bxProVideo.getMemberId() + "/";
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,true,false);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            if(re==0){
                                String videoUrl = null;
                                if(bxProVideo.getVideoUrl()!=null && !"".equals(bxProVideo.getVideoUrl())){
                                    videoUrl = bxProVideo.getVideoUrl().split("/")[bxProVideo.getVideoUrl().split("/").length-1];
                                }
                                new File(fileSavePath+videoUrl).delete();
                                List<String> list = (List<String>)mapImg.get("list");
                                if(list!=null && list.size()>0){
                                    bxProVideo.setVideoUrl(list.get(0));
                                    //操作新的文件
                                    bxProVideoService.updateById(bxProVideo);
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
        mav.setViewName("redirect:/proVideo/getProVideoList");
        return mav;
    }

    /**
     * 管理端--上传用户视频
     * @param request
     * @return
     */
    @RequestMapping(value = "/addProVideo", method = RequestMethod.POST)
    public ModelAndView addProVideo(ModelAndView mav, final HttpServletRequest request, @ModelAttribute BxProVideo bxProVideo, @RequestParam(value="file",required=true) MultipartFile[] file) throws IOException {
        Map<String, Object> model = mav.getModel();
        String result;
        try{
            if (bxProVideo != null) {
                if(file!=null && file.length>0){
                    HttpSession session = request.getSession();
                    UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                    if(bxProVideo.getMemberId()==0){
                        bxProVideo.setMemberId(staff.getId());
                    }
                    bxProVideo.setCreaterId(staff.getId());
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.userVideoPath + bxProVideo.getMemberId() + "/";
                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,true,false);
                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                    if(re==0){
                        if(re==0){
                            List<String> list = (List<String>)mapImg.get("list");
                            if(list!=null && list.size()>0){
                                bxProVideo.setVideoUrl(list.get(0));
                                BxProVideo oldProVideo = bxProVideoService.getProVideoBymemberId(bxProVideo.getMemberId());
                                if(oldProVideo!=null){
                                    bxProVideoService.updateById(bxProVideo);
                                }else{
                                    bxProVideoService.insert(bxProVideo);
                                }
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
            _logger.error("addProVideo失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        model.put("result", result);
        mav.setViewName("redirect:/proVideo/getProVideoList");
        return mav;
    }
}
