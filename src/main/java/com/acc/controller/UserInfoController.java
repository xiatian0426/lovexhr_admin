package com.acc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.acc.model.UserInfo;
import com.acc.vo.UserInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.acc.exception.ExceptionUtil;
import com.acc.exception.SelectException;
import com.acc.frames.web.Md5PwdEncoder;
import com.acc.model.AccDepart;
import com.acc.model.AccRole;
import com.acc.service.IAccDepartService;
import com.acc.service.IAccRoleService;
import com.acc.service.IUserInfoService;
import com.acc.util.CalendarUtil;
import com.acc.util.Constants;
import com.acc.vo.AccDepartVo;
import com.acc.vo.Page;
import com.alibaba.fastjson.JSON;


@Controller
@RequestMapping(value="/user")
public class UserInfoController {
	private static Logger _logger = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private IAccRoleService accRoleService;

	@Autowired
	private IAccDepartService accDepartService;

	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index (ModelAndView mav, final HttpServletRequest request, @ModelAttribute UserInfoQuery query) {
		Map<String, Object> model = mav.getModel();
		try {
			mav.setViewName("/userinfo/index");
		} catch (Exception e) {
			_logger.error("转到用户列表页失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}
	/**
	 * 用户列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list (ModelAndView mav, final HttpServletRequest request, @ModelAttribute UserInfoQuery query) {
		Map<String, Object> model = mav.getModel();
		try {
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            if(staff!=null && staff.getRoleId()!=null && staff.getRoleId().equals("1")){
                Page<UserInfo> page = userInfoService.selectPage(query);
                model.put("page", page);
                model.put("query", query);
            }
			mav.setViewName("/userinfo/userList");
		} catch (Exception e) {
			_logger.error("转到用户列表页失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}

	/**
	 * 跳转到添加用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd (ModelAndView mav, final HttpServletRequest request) {
		Map<String, Object> model = mav.getModel();
		try {
			List<AccRole> roleList = accRoleService.getUserRoleAll();
			model.put("roleList", roleList);
			//部门树结构
			List<AccDepartVo> departTreeList = accDepartService.getDepartTreeAll();
			String departItem =JSON.toJSONString(departTreeList);
			model.put("departItem", departItem);

			//获取全部部门(用于所属部门)
			List<AccDepart> list = accDepartService.getDepartAll();
			List<AccDepart> departList = new ArrayList<AccDepart>();
			AccDepart ad;
			for (int i = 0; i < list.size(); i++) {
				ad = list.get(i);
				if(ad.getDepId().length()==4){
					ad.setItemname("&nbsp;|-&nbsp;"+ad.getItemname());
				}else if(ad.getDepId().length()==6){
					ad.setItemname("&nbsp;|&nbsp;&nbsp;&nbsp;|-&nbsp;"+ad.getItemname());
				}else if(ad.getDepId().length()==8){
					ad.setItemname("&nbsp;|&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;|-&nbsp;"+ad.getItemname());
				}else if(ad.getDepId().length()==10){
					ad.setItemname("&nbsp;|&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;|-&nbsp;"+ad.getItemname());
				}
				departList.add(ad);
			}
			model.put("departList", departList);
			mav.setViewName("/userinfo/add");
		} catch (Exception e) {
			_logger.error("转到跳转到添加用户页失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}

	/**
	 * 添加用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add (ModelAndView mav, final HttpServletRequest request, @ModelAttribute UserInfo user) {
		Map<String, Object> model = mav.getModel();
		try {
			HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
			user.setCreaterId(staff.getId());
			user.setCreateDate(new Date());
			//密码用MD5加密
			user.setUserPassword(Md5PwdEncoder.getMD5Str(user.getUserPassword()));
			user.setStatus("1");//默认启用
			userInfoService.insert(user);
			mav.setViewName("redirect:/user/index");
		} catch (Exception e) {
			_logger.error("转到添加用户页失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}

	/**
	 * 跳转修改用户信息
	 * @param mav
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit (ModelAndView mav, final HttpServletRequest request) {
		Map<String, Object> model = mav.getModel();
		try {
			String userId = request.getParameter("userId");
			List<AccRole> roleList = accRoleService.getUserRoleAll();
			model.put("roleList", roleList);
            UserInfo userInfo = userInfoService.getById(userId);
			model.put("userInfo", userInfo);
			model.put("notice", request.getParameter("notice"));
			//部门树结构
			List<AccDepartVo> departTreeList = accDepartService.getDepartTreeAll();
			String departItem =JSON.toJSONString(departTreeList);
			model.put("departItem", departItem);
			model.put("manageDepart", userInfo.getManageDepart());
			//已经拥有的部门树结构
			List<String> upDepartItem = new ArrayList<String>();
			if(userInfo.getManageDepart()!=null){
				String[] str = userInfo.getManageDepart().split(",");
				for (int i = 0; i < str.length; i++) {
					upDepartItem.add(str[i]);
				}
			}
			model.put("upDepartItem", JSON.toJSONString(upDepartItem));

			//获取全部部门(用于所属部门)
			List<AccDepart> list = accDepartService.getDepartAll();
			List<AccDepart> departList = new ArrayList<AccDepart>();
			AccDepart ad;
			for (int i = 0; i < list.size(); i++) {
				ad = list.get(i);
				if(ad.getDepId().length()==4){
					ad.setItemname("&nbsp;|-&nbsp;"+ad.getItemname());
				}else if(ad.getDepId().length()==6){
					ad.setItemname("&nbsp;|&nbsp;&nbsp;&nbsp;|-&nbsp;"+ad.getItemname());
				}else if(ad.getDepId().length()==8){
					ad.setItemname("&nbsp;|&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;|-&nbsp;"+ad.getItemname());
				}else if(ad.getDepId().length()==10){
					ad.setItemname("&nbsp;|&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;|-&nbsp;"+ad.getItemname());
				}
				departList.add(ad);
			}
			model.put("departList", departList);
			mav.setViewName("/userinfo/editUser");
		} catch (Exception e) {
			_logger.error("转到添加用户页失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}

	/**
	 * 修改用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editUser")
	public ModelAndView editUser (final ModelAndView mav, final HttpServletRequest request, @ModelAttribute UserInfo user) {
		String userId = request.getParameter("id");
		try {
			HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
			user.setModifierId(staff.getId()+"");
			user.setModifyDate(CalendarUtil.getCurrentDate());
            UserInfo userInfo = userInfoService.getById(userId);
			//密码用MD5加密
			if (StringUtils.isNotEmpty(user.getUserPassword())) {
				user.setUserPassword(Md5PwdEncoder.getMD5Str(user.getUserPassword()));
			} else {
				user.setUserPassword(userInfo.getUserPassword());
			}
			user.setCreaterId(userInfo.getCreaterId());
			user.setCreateDate(userInfo.getCreateDate());
			user.setStatus(userInfo.getStatus());
			userInfoService.update(user);
			mav.getModel().put("userId", userId);
			mav.getModel().put("notice", 1);

		} catch (Exception e) {
			_logger.error("修改用户失败：" + ExceptionUtil.getMsg(e));
			mav.getModel().put("userId", userId);
			mav.getModel().put("notice", -1);
		}
		mav.setViewName("redirect:/user/goEdit");
		return mav;
	}

	/**
	 * 验证用户名称 用户名是否存在  存在 false 不存在 true
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/validateUserName")
	public boolean validateUserName (final HttpServletRequest request) {
		String newUserName = request.getParameter("newUserName");
		String oldUserName = request.getParameter("oldUserName");
		try {
            UserInfo userInfo = userInfoService.getByUserName(newUserName.trim());
			//当前登录名称不存在
			if (StringUtils.isNotEmpty(oldUserName) && (userInfo == null || oldUserName.equals(newUserName))) return false;
		} catch (SelectException e) {
			return true;
		}
		return true;
	}

	/**
	 * 禁用或者启用用户
	 * @param request
	 * @return
	 * @author TANGCY
	 * @since 2016年10月25日
	 */
	@ResponseBody
	@RequestMapping(value="/userUseOrNot")
	public boolean userUseOrNot (final HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		try {
			userInfoService.updateUserStatus(userId, status);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
