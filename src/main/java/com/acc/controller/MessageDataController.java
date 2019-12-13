package com.acc.controller;

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
import com.acc.model.GrhxMessageData;
import com.acc.service.IAccRoleService;
import com.acc.service.IGrhxMessageDataService;
import com.acc.util.Constants;
import com.acc.vo.MessageDataQuery;
import com.acc.vo.Page;


@Controller
@RequestMapping(value="/messageData")
public class MessageDataController {
	private static Logger _logger = LoggerFactory.getLogger(MessageDataController.class);
	
	@Autowired
	private IGrhxMessageDataService grhxMessageDataService;

	@Autowired
	private IAccRoleService accRoleService;
	/**
	 * 产品信息数据列表
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/messageDataList")
	public ModelAndView messageDataList (ModelAndView mav, final HttpServletRequest request,@ModelAttribute MessageDataQuery query) {
		Map<String, Object> model = mav.getModel();
		try {
            Page<GrhxMessageData> page = grhxMessageDataService.selectPage(query);
            model.put("page", page);
			model.put("query", query);
			mav=new ModelAndView("/messageData/messageDataList", model);
		} catch (Exception e) {
			_logger.error("信息数据列表失败：" + ExceptionUtil.getMsg(e));
			e.printStackTrace();
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}*/
	/**
	 * 添加信息数据页面
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/goAddMessageData")
	public ModelAndView goAddMessageData (ModelAndView mav, final HttpServletRequest request) {
		Map<String, Object> model = mav.getModel();
		try {
			//省份
			List<AccProvince> provinceList = accProvinceService.getAll();
			model.put("provinceList", provinceList);
			//信息类型
			HttpSession session = request.getSession();
			UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
			List<AccRole> roleList= accRoleService.getById(staff.getRoleId());
			if(roleList!=null && roleList.size()>0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ids", roleList.get(0).getRoleMessageRight());
				List<GrhxMessageType> messageTypeList = grhxMessageTypeService.getAllById(map);
				model.put("messageTypeList", messageTypeList);
			}
			
			mav=new ModelAndView("/messageData/addMessageData", model);
		} catch (Exception e) {
			_logger.error("信息数据列表失败：" + ExceptionUtil.getMsg(e));
			e.printStackTrace();
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}
	*//**
	 * 保存信息数据
	 * @param mav
	 * @param request
	 * @param messageData
	 * @return
	 *//*
	@RequestMapping(value = "/saveMessageData")
	public ModelAndView saveMessageData (ModelAndView mav, final HttpServletRequest request, @ModelAttribute GrhxMessageData messageData) {
		Map<String, Object> model = mav.getModel();
		int notice = 1;
		try {
			HttpSession session = request.getSession();
			UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
			messageData.setCreateid(staff.getId());
			messageData.setCreateTime(new Date());
			messageData.setWebtype("0");//录入
			grhxMessageDataService.insert(messageData);
		} catch (Exception e) {
			notice = -1;
			_logger.error("信息数据列表失败：" + ExceptionUtil.getMsg(e));
			e.printStackTrace();
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		model.put("notice", notice);
		mav=new ModelAndView("/messageData/addMessageData", model);
		return goAddMessageData(mav,request);
	}
	
	*//**
	 * 修改信息数据页面
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/goEditMessageData")
	public ModelAndView goEditMessageData (ModelAndView mav, final HttpServletRequest request) {
		Map<String, Object> model = mav.getModel();
		try {
			//用于查询回显
			String titleQuery = request.getParameter("titleQuery");
			String provinceQuery = request.getParameter("provinceQuery");
			String messagetypeQuery = request.getParameter("messagetypeQuery");
			model.put("titleQuery", titleQuery);
			model.put("provinceQuery", provinceQuery);
			model.put("messagetypeQuery", messagetypeQuery);
			
			String id = request.getParameter("id");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			GrhxMessageData messageData = grhxMessageDataService.getById(map);
			GrhxMessageData messageDataContent = grhxMessageDataService.getByIdContent(map);
			messageData.setContent(messageDataContent==null?"":messageDataContent.getContent());
			GrhxMessageDataFront messageDataFront = grhxMessageDataFrontService.getById(map);
			if(messageDataFront!=null){
				messageData.setFrontmodule(messageDataFront.getFrontmodule());
			}
			model.put("messageData", messageData);
			//省份
			List<AccProvince> provinceList = accProvinceService.getAll();
			model.put("provinceList", provinceList);
			//信息类型
			HttpSession session = request.getSession();
			UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
			List<AccRole> roleList= accRoleService.getById(staff.getRoleId());
			if(roleList!=null && roleList.size()>0){
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("ids", roleList.get(0).getRoleMessageRight());
				List<GrhxMessageType> messageTypeList = grhxMessageTypeService.getAllById(map1);
				model.put("messageTypeList", messageTypeList);
			}
			
			mav=new ModelAndView("/messageData/editMessageData", model);
		} catch (Exception e) {
			_logger.error("修改信息数据页面失败：" + ExceptionUtil.getMsg(e));
			e.printStackTrace();
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}
	
	*//**
	 * 修改信息数据
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/editMessageData")
	public ModelAndView editMessageData (ModelAndView mav, final HttpServletRequest request,@ModelAttribute GrhxMessageData messageData) {
		Map<String, Object> model = mav.getModel();
		try {
			HttpSession session = request.getSession();
			UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
			messageData.setOperaterid(staff.getId());
			messageData.setOperatetime(new Date());
			grhxMessageDataService.update(messageData);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", messageData.getId());
			GrhxMessageDataFront messageDataFront = grhxMessageDataFrontService.getById(map);
			if(messageDataFront==null){
				messageDataFront = new GrhxMessageDataFront();
				GrhxMessageData messageData2 = grhxMessageDataService.getById(map);
				messageDataFront.setId(messageData.getId());
				messageDataFront.setTitle(messageData2.getTitle());
				messageDataFront.setDate(messageData2.getDate());
				messageDataFront.setProvince(messageData2.getProvince());
				messageDataFront.setMessagetype(messageData2.getMessagetype());
				messageDataFront.setWebtype(messageData2.getWebtype());
				messageDataFront.setBusType(messageData2.getBusType());
				messageDataFront.setBusType(messageData2.getBusType());
				messageDataFront.setFrontmodule(messageData.getFrontmodule());
				grhxMessageDataFrontService.insert(messageDataFront);
			}else{
				GrhxMessageData messageData2 = grhxMessageDataService.getById(map);
				messageDataFront.setId(messageData.getId());
				messageDataFront.setTitle(messageData2.getTitle());
				messageDataFront.setDate(messageData2.getDate());
				messageDataFront.setProvince(messageData2.getProvince());
				messageDataFront.setMessagetype(messageData2.getMessagetype());
				messageDataFront.setWebtype(messageData2.getWebtype());
				messageDataFront.setBusType(messageData2.getBusType());
				messageDataFront.setBusType(messageData2.getBusType());
				if(messageData.getFrontmodule()==null || "".equals(messageData.getFrontmodule()) || "0".equals(messageData.getFrontmodule())){
					grhxMessageDataFrontService.deleteById(map);
				}else{
					messageDataFront.setFrontmodule(messageData.getFrontmodule());
					grhxMessageDataFrontService.update(messageDataFront);
				}
			}
		} catch (Exception e) {
			_logger.error("修改信息数据页面失败：" + ExceptionUtil.getMsg(e));
			e.printStackTrace();
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		//用于查询回显
		String titleQuery = request.getParameter("titleQuery");
		String provinceQuery = request.getParameter("provinceQuery");
		String messagetypeQuery = request.getParameter("messagetypeQuery");
		MessageDataQuery query = new MessageDataQuery();
		query.setTitle(titleQuery);
		query.setProvince(provinceQuery);
		query.setMessagetype(String.valueOf(Integer.parseInt(messagetypeQuery)));
		return messageDataList(mav,request,query);
	}*/
	
}
