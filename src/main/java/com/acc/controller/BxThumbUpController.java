package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxThumbUp;
import com.acc.model.UserInfo;
import com.acc.service.IBxThumbUpService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.util.weChat.WechatUtil;
import com.acc.vo.HonorQuery;
import com.acc.vo.Page;
import com.acc.vo.ThumbUpQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/thumbUp")
public class BxThumbUpController {
	private static Logger _logger = LoggerFactory.getLogger(BxThumbUpController.class);

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IBxThumbUpService thumbUpService;

	/**
	 * 点赞信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getThumbUpList", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getHonorList(ModelAndView mav, final HttpServletRequest request,@ModelAttribute ThumbUpQuery query) throws IOException {
        Map<String,Object> model = new HashMap<String, Object>();
	    try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            Page<BxThumbUp> page = null;
            query.setSortColumns("c.MODIFY_TIME desc");
            if(staff!=null){
                if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                    Map<Integer, UserInfo> userInfoDictMap = userInfoService.getAllMap();
                    model.put("userInfoDictMap", userInfoDictMap);
                    List<UserInfo> userInfoList = userInfoService.getAll();
                    model.put("userInfoList", userInfoList);
                    page = thumbUpService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
//                        query.setStatus(0);//客户只能看到点赞的，不能看取消的
                        page = thumbUpService.selectPage(query);
                    }
                }
            }
            model.put("page", page);
            model.put("query", query);
            mav=new ModelAndView("/thumbUp/thumbUpList", model);
        } catch (Exception e) {
            _logger.error("bxThumbUpService失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
            e.printStackTrace();
        }
        return mav;
	}
}
