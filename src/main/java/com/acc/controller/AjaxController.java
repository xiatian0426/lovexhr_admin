package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.service.IBxProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value="/ajax")
public class AjaxController {
	private static Logger _logger = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private IBxProductService bxProductService;

	/*@Autowired
	private IUserInfoService userInfoService;
	
	
	@Autowired
	private IAccRoleService accRoleService;
	
	@Autowired
	private IAccIpService accIpService;
	
	@Autowired
	private IGrhxMessageTypeService grhxMessageTypeService;
	
	@Autowired
	private IGrhxMessageDataService grhxMessageDataService;
	
	@Autowired
	private IGrhxMessageDataFrontService grhxMessageDataFrontService;

	/**
	 * 删除产品信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
    @RequestMapping(value = "/deleteByProdictId", method = RequestMethod.POST)
    public Map<String, Object> deleteByProdictId (final HttpServletRequest request,
                                                  final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            String productId = request.getParameter("id");
            if(StringUtils.isNotEmpty(productId)){
                bxProductService.deleteById(productId);
                model.put("info","1");
                model.put("message","删除成功!");
            }
        } catch (Exception e) {
            _logger.error("删除产品信息失败：" + ExceptionUtil.getMsg(e));
            model.put("info", "删除失败");
        }
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteProductDetailImgById", method = RequestMethod.POST)
    public Map<String, Object> deleteProductDetailImgById (final HttpServletRequest request,
                                                  final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            String productId = request.getParameter("id");
            if(StringUtils.isNotEmpty(productId)){
                bxProductService.deleteProductDetailImgById(productId);
                model.put("info","1");
                model.put("message","删除成功!");
            }
        } catch (Exception e) {
            _logger.error("删除产品信息失败：" + ExceptionUtil.getMsg(e));
            model.put("info", "删除失败");
        }
        return model;
    }

	/**
	 * 添加信息类型
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 *//*
	@ResponseBody
	@RequestMapping(value = "/addMessage", method = RequestMethod.POST)
	public Map<String, Object> addMessage (final HttpServletRequest request,
	final HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String messageName = request.getParameter("messageName");
			GrhxMessageType grhxMessageType = new GrhxMessageType();
			grhxMessageType.setMessageName(messageName);
			grhxMessageType.setState("0");
			grhxMessageTypeService.insert(grhxMessageType);
			model.put("info", "添加成功");
		} catch (Exception e) {
			_logger.error("添加信息类型失败：" + ExceptionUtil.getMsg(e));
			model.put("info", "添加失败");
		}
		return model;
	}
	*//**
	 * 修改信息类型
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 *//*
	@ResponseBody
	@RequestMapping(value = "/editMessage", method = RequestMethod.POST)
	public Map<String, Object> editMessage (final HttpServletRequest request,
	final HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String messageName = request.getParameter("messageName");
			GrhxMessageType grhxMessageType = new GrhxMessageType();
			grhxMessageType.setId(Integer.valueOf(id));
			grhxMessageType.setMessageName(messageName);
			grhxMessageType.setState("0");
			grhxMessageTypeService.update(grhxMessageType);
			model.put("info", "修改成功");
		} catch (Exception e) {
			_logger.error("修改信息类型失败：" + ExceptionUtil.getMsg(e));
			model.put("info", "修改失败");
		}
		return model;
	}
    /**//**
	 * 删除信息数据
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 *//*
	@ResponseBody
	@RequestMapping(value = "/deleteMessageData", method = RequestMethod.POST)
	public Map<String, Object> deleteMessageData (final HttpServletRequest request,
	final HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String info = "1";
		try {
			String id = request.getParameter("id");
			String isdelete = request.getParameter("isdelete");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("isdelete", isdelete);
			grhxMessageDataService.deleteById(map);
			if(isdelete!=null && "1".equals(isdelete)){
				grhxMessageDataFrontService.deleteById(map);
			}
		} catch (Exception e) {
			info = "-1";
			e.printStackTrace();
			_logger.error("删除信息数据失败：" + ExceptionUtil.getMsg(e));
		}
		model.put("info", info);
		return model;
	}*/
	
}
