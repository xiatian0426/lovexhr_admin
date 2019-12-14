package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxHonor;
import com.acc.model.BxMember;
import com.acc.service.IBxHonorService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.alibaba.fastjson.JSON;
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
import java.io.PrintWriter;
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

	/**
	 * 荣誉信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getHonorList", method = RequestMethod.GET)
	public ModelAndView getHonorList(ModelAndView mav, final HttpServletRequest request) throws IOException {
        Map<String,Object> model = new HashMap<String, Object>();
	    try{
            HttpSession session = request.getSession();
            BxMember staff = (BxMember)session.getAttribute(Constants.LOGINUSER);
            String memberId = String.valueOf(staff.getId());
            if(StringUtils.isNotEmpty(memberId) ){
                Integer count = bxHonorService.getHonorCount(memberId);
                model.put("count",count);
                List<BxHonor> bxHonorList = bxHonorService.getHonorList(memberId);
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort()
                        + path + "/";
                List<String> imageUrl = new ArrayList<String>();
                String url;
                for (BxHonor bxHonor:bxHonorList){
                    url = basePath+ Constants.honorImgPath+bxHonor.getMemberId()+"/"+bxHonor.getImageUrl();
                    bxHonor.setImageUrl(url);
                    imageUrl.add(url);
                }
                model.put("list",bxHonorList);
                model.put("imageUrl",imageUrl);
            }
        } catch (Exception e) {
            _logger.error("bxHonorService失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
            e.printStackTrace();
        }
        mav=new ModelAndView("/honor/honorList", model);
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
                    if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                        //删除该代理商的整个荣誉文件夹
                        bxHonor.setImageUrl(null);
                        bxHonorService.updateById(bxHonor);
                    }else{
                        HttpSession session = request.getSession();
                        BxMember staff = (BxMember)session.getAttribute(Constants.LOGINUSER);
                        bxHonor.setMemberId(staff.getId());
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
        return getHonorList(mav,request);
    }
}
