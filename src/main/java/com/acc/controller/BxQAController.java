package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxQA;
import com.acc.model.UserInfo;
import com.acc.service.IBxQAService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.vo.Page;
import com.acc.vo.QAQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/QA")
public class BxQAController {
	private static Logger _logger = LoggerFactory.getLogger(BxQAController.class);
	
	@Autowired
	private IBxQAService bxQAService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * QA信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getQAList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getQAList(ModelAndView mav, final HttpServletRequest request, QAQuery query) throws IOException {
        Map<String, Object> model = mav.getModel();
	    try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            Page<BxQA> page = null;
            if(staff!=null){
                query.setSortColumns("c.CREATE_DATE desc");
                if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                    List<UserInfo> userInfoList = userInfoService.getAll();
                    model.put("userInfoList", userInfoList);
                    page = bxQAService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxQAService.selectPage(query);
                    }
                }
            }
            model.put("page", page);
            model.put("query", query);
            mav=new ModelAndView("/qa/qaList", model);
        } catch (Exception e) {
            _logger.error("getMemberById失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
            e.printStackTrace();
        }
	    return mav;
	}


    /**
     * 理端--根据id查QA信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public ModelAndView updateById(ModelAndView mav,final HttpServletRequest request, @ModelAttribute BxQA bxQA) throws IOException {
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            if(bxQA != null){
                HttpSession session = request.getSession();
                UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                bxQA.setModifierId(String.valueOf(staff.getId()));
                bxQAService.updateById(bxQA);
                result.put("code",0);
                result.put("message","更新成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","更新失败!");
            _logger.error("updateById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        return getQAList(mav,request,null);
    }

    /**
     * 理端--添加QA信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/addQA", method = RequestMethod.POST)
    public ModelAndView addQA(ModelAndView mav,final HttpServletRequest request, @ModelAttribute BxQA bxQA) throws IOException {
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            if(bxQA != null){
                HttpSession session = request.getSession();
                UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                if(bxQA.getMemberId()==0){
                    bxQA.setMemberId(staff.getId());
                }
                bxQA.setCreaterId(staff.getId());
                bxQAService.insert(bxQA);
                result.put("code",0);
                result.put("message","保存成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","保存失败!");
            _logger.error("addQA失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        return getQAList(mav,request,null);
    }
}
