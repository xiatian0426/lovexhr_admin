package com.acc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.acc.model.BxToken;
import com.acc.model.UserInfo;
import com.acc.service.IBxTokenService;
import com.acc.util.PictureChange;
import com.acc.vo.UserInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
    private IBxTokenService bxTokenService;

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
            if(staff!=null && staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
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
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
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
	public ModelAndView add (ModelAndView mav, final HttpServletRequest request, @ModelAttribute UserInfo user,@RequestParam(value="file") MultipartFile[] file) {
		Map<String, Object> model = mav.getModel();
		try {
            UserInfo userInfo = userInfoService.getByUserName(user.getUserName());
            if (userInfo == null) {
                HttpSession session = request.getSession();
                UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                if(staff!=null){
                    if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                        user.setCreaterId(staff.getId());
                        user.setCreateDate(new Date());
                        //密码用MD5加密
                        user.setUserPassword(Md5PwdEncoder.getMD5Str(user.getUserPassword()+"Diegoxhr"));
                        user.setStatus("1");//默认启用
                        userInfoService.insert(user);
                        if(file!=null && file.length>0){
                            String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                            String fileSavePath=path + Constants.memberImgPath + user.getId() + "/";
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            if(re==0){
                                List<String> list = (List<String>)mapImg.get("list");
                                if(list!=null && list.size()>0){
                                    user.setMemberImg(list.get(0));
                                    userInfoService.updateImg(user);
                                }

                                String scene = String.valueOf(user.getId());
                                String page="page/msg_waist/msg_waist";
                                BxToken bxToken = bxTokenService.getToken(0);
                                if(bxToken!=null && bxToken.getAccessToken()!=null && !bxToken.getAccessToken().equals("")){
                                    String token = bxToken.getAccessToken();
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("scene", scene);  //参数
                                    //            params.put("page", "page/msg_waist/msg_waist"); //位置
                                    params.put("width", 430);
                                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                                    HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token);  // 接口
                                    httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
                                    String body = JSON.toJSONString(params);           //必须是json模式的 post
                                    StringEntity entity;
                                    entity = new StringEntity(body);
                                    entity.setContentType("image/png");
                                    httpPost.setEntity(entity);
                                    HttpResponse response1;
                                    response1 = httpClient.execute(httpPost);
                                    InputStream inputStream = response1.getEntity().getContent();
                                    String name = scene+".png";
                                    String filePath = Constants.memberImgWxaCodePath;
                                    int result = saveToImgByInputStream(inputStream, path + filePath,name);  //保存图片
                                    if(result==1){//保存成功
                                        //保存数据
                                        bxTokenService.updateMemberWxaCodeById(scene,name);
                                    }
                                }
                            }
                        }
                    }
                }
                mav.setViewName("redirect:/user/index");
            }
		} catch (Exception e) {
			_logger.error("转到添加用户页失败：" + ExceptionUtil.getMsg(e));
			mav = new ModelAndView(Constants.SERVICES_ERROR, model);
		}
		return mav;
	}

    /**
     * 将二进制转换成文件保存
     * @param instreams 二进制流
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToImgByInputStream(InputStream instreams,String imgPath,String imgName){
        int stateInt = 1;
        if(instreams != null){
            try {
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                // 判断文件父目录是否存在
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdir();
                }
                FileOutputStream fos=new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
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
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            if(staff!=null){
                if(userId==null || userId.equals("")){
                    userId = String.valueOf(staff.getId());
                }
                UserInfo userInfo = userInfoService.getById(userId);
                if(userInfo!=null){
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + path + "/";
                    String fileSavePath=basePath + Constants.memberImgPath + userInfo.getId() + "/";
                    userInfo.setMemberImg(fileSavePath+userInfo.getMemberImg());
                    if(userInfo.getWxaCode()!=null && !userInfo.getWxaCode().equals("")){
                        fileSavePath=basePath + Constants.memberImgWxaCodePath;
                        userInfo.setWxaCode(fileSavePath+userInfo.getWxaCode());
                    }
                }
                model.put("userInfo", userInfo);
                model.put("notice", request.getParameter("notice"));
                String customerFlag = request.getParameter("customerFlag");
                model.put("customerFlag", customerFlag);
            }
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
	public ModelAndView editUser (final ModelAndView mav, final HttpServletRequest request, @ModelAttribute UserInfo user,@RequestParam(value="file") MultipartFile[] file) {
		String userId = request.getParameter("id");
		try {
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
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            if(staff!=null){
                user.setModifierId(staff.getId()+"");
                user.setModifyDate(CalendarUtil.getCurrentDate());
                if(staff.getRoleId()==null || staff.getRoleId().equals(Constants.ROLEIDZ)){
                    //客户不能修改角色
                    user.setRoleId(null);
                }
                UserInfo userInfo = userInfoService.getById(userId);
                //密码用MD5加密
                if (StringUtils.isNotEmpty(user.getUserPassword())) {
                    user.setUserPassword(Md5PwdEncoder.getMD5Str(user.getUserPassword()+"Diegoxhr"));
                } else {
                    user.setUserPassword(userInfo.getUserPassword());
                }
                user.setModifierId(String.valueOf(staff.getId()));
                user.setStatus(userInfo.getStatus());
                userInfoService.update(user);
                mav.getModel().put("userId", userId);
                String customerFlag = request.getParameter("customerFlag");
                mav.getModel().put("customerFlag", customerFlag);
                if(user.getCustomerFlag()!=null && user.getCustomerFlag().equals(Constants.ROLEIDO)){
                    mav.getModel().put("notice", 2);
                }else{
                    mav.getModel().put("notice", 1);
                }
            }
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
