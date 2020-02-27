package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxToken;
import com.acc.service.IBxTokenService;
import com.acc.util.weChat.WechatUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/weChat")
public class BxWeChatController {

	private static Logger _logger = LoggerFactory.getLogger(BxWeChatController.class);

    @Autowired
    private IBxTokenService bxTokenService;

    /**
     * 获取openID
     * @param code
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getOpenId", method = RequestMethod.POST)
    @ResponseBody
    public void getOpenId(@RequestBody String code, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            System.out.println("code:" + code);
            String openid = WechatUtil.getOpenIdAndSessionKey(code).get("openid");
            result.put("openid",openid);
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","获取openid失败!");
            _logger.error("getOpenId失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    /**
     * 获取token
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getDDToken", method = RequestMethod.POST)
    @ResponseBody
    public void getDDToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            BxToken bxToken = bxTokenService.getToken();
            if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                result.put("access_token",bxToken.getAccessToken());
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","获取access_token失败!");
            _logger.error("getDDToken失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    /**
     * 更新token
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/updateDDToken", method = RequestMethod.POST)
    @ResponseBody
    public void updateDDToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String access_token = WechatUtil.getDDToken();
            BxToken bxToken = new BxToken();
            bxToken.setAccessToken(access_token);
            bxTokenService.updateToken(bxToken);
            result.put("access_token",access_token);
            result.put("code",0);
            result.put("message","更新access_token成功!");
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","更新access_token失败!");
            _logger.error("getDDToken失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public void getUserInfo(@RequestBody String infoData, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            System.out.println("infoData" + infoData);
            JSONObject dataObj = JSONObject.parseObject(infoData);
            String  code =  (String) dataObj.get("code");
            String  encryptedData =  (String) dataObj.get("encryptedData");
            String  iv =  (String) dataObj.get("iv");
            String sessionkey = WechatUtil.getOpenIdAndSessionKey(code).get("session_key");
            JSONObject userInfo = WechatUtil.getUserInfo(encryptedData, sessionkey, iv);
            System.out.println(userInfo);
            out.print(JSON.toJSONString(userInfo));
        } catch (Exception e) {
            map.put("code",-1);
            map.put("message","获取用户信息失败!");
            _logger.error("getUserInfo失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(map));
        out.flush();
        out.close();
    }

}
