package com.acc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acc.exception.ExceptionUtil;
import com.acc.model.AccRole;
import com.acc.service.IAccRoleService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.vo.MemberQuery;
import com.acc.vo.Page;


@Controller
@RequestMapping(value="/manage")
public class ManageController {
	private static Logger _logger = LoggerFactory.getLogger(ManageController.class);
	
	@Autowired
	private IUserInfoService userInfoService;
	
	/*@Autowired
	private IAccRoleService accRoleService;
	
	*//**
	 * 权限管理
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/messageTypeList")
	public ModelAndView messageTypeList (ModelAndView mav, final HttpServletRequest request,@ModelAttribute MemberQuery query) {
		Map<String, Object> model = mav.getModel();
		try {
			query.setPageSize(500);
			Page<GrhxMessageType> page = grhxMessageTypeService.selectPage(query);
			model.put("page", page);
			mav=new ModelAndView("/manage/messageTypeList", model);
		} catch (Exception e) {
			_logger.error("转到权限管理失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}
	
	*//**
	 * 用户角色管理
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/roleList")
	public ModelAndView roleList (ModelAndView mav, final HttpServletRequest request,@ModelAttribute MemberQuery query) {
		Map<String, Object> model = mav.getModel();
		try {
			query.setPageSize(500);
			Page<AccRole> page = accRoleService.selectPage(query);
			model.put("page", page);
			List<GrhxMessageType> messageTypeList = grhxMessageTypeService.getAll();
			model.put("messageTypeList", messageTypeList);
			
			mav=new ModelAndView("/manage/roleList", model);
		} catch (Exception e) {
			_logger.error("用户角色管理失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}*/
	
}
