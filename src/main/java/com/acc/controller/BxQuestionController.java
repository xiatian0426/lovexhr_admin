package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxQuestion;
import com.acc.model.BxThumbUp;
import com.acc.model.UserInfo;
import com.acc.service.IBxQuestionService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.vo.Page;
import com.acc.vo.QAQuery;
import com.acc.vo.QuestionQuery;
import com.acc.vo.ThumbUpQuery;
import com.alibaba.fastjson.JSON;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/question")
public class BxQuestionController {
	private static Logger _logger = LoggerFactory.getLogger(BxQuestionController.class);
	
	@Autowired
	private IBxQuestionService bxQuestionService;

    @Autowired
    private IUserInfoService userInfoService;

	/**
	 * Question信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getQuestionList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getQuestionList(ModelAndView mav, final HttpServletRequest request,@ModelAttribute QuestionQuery query) throws IOException {
        Map<String,Object> model = new HashMap<String, Object>();
        try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            Page<BxQuestion> page = null;
            query.setSortColumns("c.STATUS,CREATE_DATE desc");
            if(staff!=null){
                if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                    Map<Integer, UserInfo> userInfoDictMap = userInfoService.getAllMap();
                    model.put("userInfoDictMap", userInfoDictMap);
                    List<UserInfo> userInfoList = userInfoService.getAll();
                    model.put("userInfoList", userInfoList);
                    page = bxQuestionService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxQuestionService.selectPage(query);
                    }
                }
            }
            model.put("page", page);
            model.put("query", query);
            mav=new ModelAndView("/question/questionList", model);
        } catch (Exception e) {
            _logger.error("getQuestionList失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
            e.printStackTrace();
        }
        return mav;
	}
}
