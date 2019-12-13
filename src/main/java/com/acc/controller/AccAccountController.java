package com.acc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acc.model.BxMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acc.exception.ExceptionUtil;
import com.acc.frames.web.Md5PwdEncoder;
import com.acc.frames.web.WebCookies;
import com.acc.model.AccUserLoginHis;
import com.acc.service.IAccUserLoginHisService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.MakePicture;

/**
 * 登录 退出
 */
@Controller
@RequestMapping(value="/account")
public class AccAccountController {
	private static Logger _logger = LoggerFactory.getLogger(AccAccountController.class);
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IAccUserLoginHisService accUserLoginHisService;
	
	/**
	 * 过滤器自动跳转到登录首页
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login (ModelAndView mav, final HttpServletRequest request) {
		Map<String, Object> model = mav.getModel();
		try {
			mav=new ModelAndView("/account/login", model);
		} catch (Exception e) {
			_logger.error("转到登录首页失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}


	/**
	 * 登录按钮
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(ModelAndView mav, final HttpServletRequest request, final HttpServletResponse response) {
		Map<String, Object> model = mav.getModel();
		try {
			String loginMsg = "";
			String userName = request.getParameter("userName");
			String passwd = request.getParameter("pwd");
//			String verifycode = request.getParameter("verifycode");
//			String vcode = (String)request.getSession().getAttribute(Constants.VALIDATESESSION);
//			if (!verifycode.equalsIgnoreCase(vcode)){
//				loginMsg = "验证码不正确！";
//				model.put("sign", 2);
//				model.put("loginMsg", loginMsg);
//				//TODO 测试需要暂时关闭验证码
//				return new ModelAndView("/account/login",model);
//			}
            BxMember userInfo = userInfoService.getByUserName(userName);
			if (userInfo == null) {
				model.put("sign", -1);
				loginMsg = "该用户不存在";
				model.put("loginMsg", loginMsg);
				return new ModelAndView("/account/login",model);
			} else if ("0".equals(userInfo.getStatus())){
				model.put("sign", -1);
				model.put("loginMsg", "该用户被删除");
				return new ModelAndView("/account/login",model);
			} else {
				String password = userInfo.getUserPassword();
				System.out.println(Md5PwdEncoder.getMD5Str(passwd));
				if (!Md5PwdEncoder.getMD5Str(passwd).equals(password)){
					model.put("sign", -1);
					model.put("loginMsg", "密码错误");
					return new ModelAndView("/account/login",model);
				}
			}
//			String loginIp = getRemoteIpAddr(request);
//			if(!getIpIsRight(loginIp)){
//				model.put("sign", -1);
//				model.put("loginMsg", "ip受限,不能登录！");
//				//return new ModelAndView("/account/login",model);
//			}
			//存入session
			HttpSession session = request.getSession();
			session.setAttribute(Constants.LOGINUSER, userInfo);
			//用户名存入cookies
			WebCookies.addCookie(response, Constants.COOKIESUSERNAME, userInfo.getUserName(), Constants.COOKIESTIMES,"/");
			request.getSession().setAttribute(Constants.LOGINUSER, userInfo);
			//保存登陆日志
			AccUserLoginHis accUserLoginHis = new AccUserLoginHis();
			accUserLoginHis.setUserName(userName);
			accUserLoginHis.setCreateTime(new Date());
			accUserLoginHis.setUrl(request.getRequestURL().toString());
			accUserLoginHisService.insert(accUserLoginHis);
			mav=new ModelAndView("redirect:/", model);
		} catch (Exception e) {
			_logger.error("登录失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}
	
	/**
     * 
     * @Title: getRemoteIpAddr
     * @Description: 获得登陆用户IP地址
     * @param @return
     */
    public static String getRemoteIpAddr(final HttpServletRequest request) {
        
        if (request != null && request instanceof HttpServletRequest) {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            String[] ips = ip.split(",");
            if (ips.length > 1) {
                return ips[0];
            } else {
                return ip;
            }
        } else {
            return null;
        }
    }
	
	/**
	 * 生产验证码图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/verifycode")
	public void verifycode(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			MakePicture mp = new MakePicture();
			String str = mp.drawPicture(60, 20, response.getOutputStream());
			System.out.println(str);
			request.getSession().setAttribute(Constants.VALIDATESESSION, str);
			response.getOutputStream().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 退出系统
	 * @param mav
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout(final ModelAndView mav,final HttpServletRequest request, final HttpServletResponse response) {
		request.getSession().setAttribute(Constants.LOGINUSER, null);
		mav.setViewName("redirect:/");
		return mav;
	}
}
