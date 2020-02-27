package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxQA;
import com.acc.model.BxToken;
import com.acc.service.IBxQAService;
import com.acc.service.IBxTokenService;
import com.acc.util.weChat.WechatUtil;
import com.acc.vo.Page;
import com.acc.vo.QAQuery;
import com.alibaba.fastjson.JSON;
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
import java.util.Map;


@Controller
@RequestMapping(value="/QAWeb")
public class BxQAWebController {
	private static Logger _logger = LoggerFactory.getLogger(BxQAWebController.class);
	
	@Autowired
	private IBxQAService bxQAService;

    @Autowired
    private IBxTokenService bxTokenService;

	/**
	 * QA信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getQAList", method = {RequestMethod.POST,RequestMethod.GET})
	public void getQAList( final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute QAQuery query) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
	    try{
            query.setSortColumns("c.QA_ORDER");
            Page<BxQA> page = bxQAService.selectPage(query);
            map.put("page", page);
        } catch (Exception e) {
            status = -1;
            message = "操作失败，请联系管理员!";
            _logger.error("操作失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("message", message);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
	}


    /**
     * 更新QA信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public void updateById(ModelAndView mav,final HttpServletRequest request,final HttpServletResponse response, @ModelAttribute BxQA bxQA) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
        try{
            if(bxQA != null){
                //敏感信息验证
                BxToken bxToken = bxTokenService.getToken();
                if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                    String content = bxQA.getAsk()+bxQA.getAnswer()+bxQA.getQaOrder();
                    int checkMsgResult = WechatUtil.checkMsg(bxToken.getAccessToken(),content);
                    if(checkMsgResult== 0){
                        bxQAService.updateById(bxQA);
                        message = "更新成功!";
                    }else{
                        status = -1;
                        message = "信息校验错误，请联系管理员!";
                    }
                }else{
                    status = -1;
                    message = "信息校验错误，请联系管理员!";
                }
            }else{
                status = -1;
                message = "操作失败，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            message = "操作失败，请联系管理员!";
            _logger.error("操作失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("message", message);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

    /**
     * 理端--添加QA信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/addQA", method = RequestMethod.POST)
    public void addQA(final HttpServletRequest request,final HttpServletResponse response, @ModelAttribute BxQA bxQA) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
        try{
            if(bxQA != null){
                //敏感信息验证
                BxToken bxToken = bxTokenService.getToken();
                if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                    String content = bxQA.getAsk()+bxQA.getAnswer()+bxQA.getQaOrder();
                    int checkMsgResult = WechatUtil.checkMsg(bxToken.getAccessToken(),content);
                    if(checkMsgResult== 0){
                        bxQAService.insert(bxQA);
                        message = "更新成功!";
                    }else{
                        status = -1;
                        message = "信息校验错误，请联系管理员!";
                    }
                }else{
                    status = -1;
                    message = "信息校验错误，请联系管理员!";
                }
            }else{
                status = -1;
                message = "操作失败，请联系管理员!";
            }
        } catch (Exception e) {
            status = -1;
            message = "操作失败，请联系管理员!";
            _logger.error("操作失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        map.put("status", status);
        map.put("message", message);
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
