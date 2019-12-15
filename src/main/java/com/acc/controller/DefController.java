package com.acc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acc.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.exception.ExceptionUtil;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;


@Controller
@RequestMapping(value="/def")
public class DefController {
	private static Logger _logger = LoggerFactory.getLogger(DefController.class);
	
	@Autowired
	private IUserInfoService userInfoService;
	
	/**
	 * 过滤器自动跳转到登录首页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index (ModelAndView mav, final HttpServletRequest request) {
		Map<String, Object> model = mav.getModel();
		try {
			mav.setViewName("/def/index");
		} catch (Exception e) {
			_logger.error("转到登录首页失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/top")
	public ModelAndView top(ModelAndView mav, final HttpServletRequest request,
			final HttpServletResponse response) {
		Map<String, Object> model = mav.getModel();
		HttpSession session = request.getSession();
        UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
		String parameter = request.getParameter("prod");
		model.put("parameter", parameter);
		model.put("user", staff);
		mav.setViewName("/def/top");
		return mav;
	}
	
	@RequestMapping(value = "/left")
	public ModelAndView left (ModelAndView mav, final HttpServletRequest request,
			final HttpServletResponse response) {
		Map<String, Object> model = mav.getModel();
		HttpSession session = request.getSession();
        UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
		model.put("user", staff);
		mav.setViewName("/def/left");
		return mav;
	}
	
	@RequestMapping(value = "/detail")
	public ModelAndView detail(ModelAndView mav, final HttpServletRequest request,
			final HttpServletResponse response) {
		Map<String, Object> model = mav.getModel();
		String parameter = request.getParameter("prod");
		model.put("parameter", parameter);
		mav.setViewName("/def/detail");
		return mav;
	}
	
	@RequestMapping(value = "/footer")
	public ModelAndView footer(ModelAndView mav, final HttpServletRequest request,
			final HttpServletResponse response) {
		Map<String, Object> model = mav.getModel();
		String parameter = request.getParameter("prod");
		model.put("parameter", parameter);
		mav.setViewName("/def/footer");
		return mav;
	}
}
