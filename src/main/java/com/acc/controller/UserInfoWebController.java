package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.exception.SelectException;
import com.acc.frames.web.Md5PwdEncoder;
import com.acc.model.BxToken;
import com.acc.model.UserInfo;
import com.acc.service.IBxTokenService;
import com.acc.service.IUserInfoService;
import com.acc.util.CalendarUtil;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.util.weChat.WechatUtil;
import com.acc.vo.Page;
import com.acc.vo.UserInfoQuery;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/userWeb")
public class UserInfoWebController {
	private static Logger _logger = LoggerFactory.getLogger(UserInfoWebController.class);

	@Autowired
	private IUserInfoService userInfoService;

    @Autowired
    private IBxTokenService bxTokenService;

	/**
	 * 跳转修改用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/goEdit", method = RequestMethod.GET)
	public void goEdit (final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
		try {
			String userId = request.getParameter("userId");
            if(userId != null){
                UserInfo userInfo = userInfoService.getById(userId);
                if(userInfo!=null){
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + path + "/";
                    String fileSavePath=basePath + Constants.memberImgPath + userInfo.getId() + "/";
                    userInfo.setMemberImg(fileSavePath+userInfo.getMemberImg());
                }
                map.put("userInfo", userInfo);
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
	 * 修改用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public void editUser (final HttpServletRequest request, final HttpServletResponse response, UserInfo user,@RequestParam(value="file") MultipartFile[] file) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "操作成功!";
        int status = 0;
	    String userId = request.getParameter("id");
		try {
            //敏感信息验证
            BxToken bxToken = bxTokenService.getToken();
            if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                String content = user.getName()+user.getUserRealname()+user.getPost_name()
                        +user.getPhone()+user.getCompany_addr()+user.getCompany_name()
                        +user.getLatitude()+user.getLongitude()+user.getYears()+user.getSignature()
                        +user.getWechat()+user.getPage_style()+user.getIntroduce();
                int checkMsgResult = WechatUtil.checkMsg(bxToken.getAccessToken(),content);
                int checkImgResult = WechatUtil.checkImg(bxToken.getAccessToken(),file[0]);
                if(checkMsgResult== 0 && checkImgResult == 0){
                    if(file!=null && file.length>0){
                        if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                            user.setMemberImg(null);
                        }else{
                            String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                            String fileSavePath=path + Constants.memberImgPath + user.getId() + "/";
                            String imgUrl = null;
                            if(user.getMemberImg()!=null && !"".equals(user.getMemberImg())){
                                imgUrl = user.getMemberImg().split("/")[user.getMemberImg().split("/").length-1];
                            }
                            new File(fileSavePath+imgUrl).delete();
                            //生成新图片
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            if(re==0){
                                List<String> list = (List<String>)mapImg.get("list");
                                if(list!=null && list.size()>0){
                                    user.setMemberImg(list.get(0));
                                }
                            }
                        }
                    }
                    user.setModifyDate(CalendarUtil.getCurrentDate());
                    UserInfo userInfo = userInfoService.getById(userId);
                    //密码用MD5加密
                    if (StringUtils.isNotEmpty(user.getUserPassword())) {
                        user.setUserPassword(Md5PwdEncoder.getMD5Str(user.getUserPassword()+"Diegoxhr"));
                    } else {
                        user.setUserPassword(userInfo.getUserPassword());
                    }
                    user.setStatus(userInfo.getStatus());
                    userInfoService.update(user);
                }else{
                    status = -1;
                    message = "信息校验错误，请联系管理员!";
                }
            }else{
                status = -1;
                message = "信息校验错误，请联系管理员!";
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
	 * 验证用户名称 用户名是否存在  存在 false 不存在 true
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/validateUserName")
	public boolean validateUserName (final HttpServletRequest request) {
		String newUserName = request.getParameter("newUserName");
		try {
            UserInfo userInfo = userInfoService.getByUserName(newUserName.trim());
			//当前登录名称不存在
			if (userInfo == null) return false;
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
