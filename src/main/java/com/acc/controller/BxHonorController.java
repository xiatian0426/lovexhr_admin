package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxHonor;
import com.acc.model.BxMember;
import com.acc.service.IBxHonorService;
import com.acc.util.Constants;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
	public ModelAndView getHonorList(ModelAndView mav, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
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
}
