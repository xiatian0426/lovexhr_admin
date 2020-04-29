package com.acc.controller;

import com.acc.dao.BxCommentTagMapper;
import com.acc.exception.ExceptionUtil;
import com.acc.model.BxCommentTag;
import com.acc.model.BxCompany;
import com.acc.model.BxMomment;
import com.acc.model.UserInfo;
import com.acc.service.IBxCompanyService;
import com.acc.service.IBxMommentService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.vo.CompanyQuery;
import com.acc.vo.MommentQuery;
import com.acc.vo.Page;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/momment")
public class BxMommentController {
	private static Logger _logger = LoggerFactory.getLogger(BxMommentController.class);

	@Autowired
	private IBxMommentService bxMommentService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private BxCommentTagMapper bxCommentTagMapper;

	/**
	 * 评论信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMommentList", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getMommentList(ModelAndView mav,final HttpServletRequest request, @ModelAttribute MommentQuery query) throws IOException {
        Map<String,Object> model = new HashMap<String, Object>();
	    try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            Page<BxMomment> page = null;
            if(staff!=null){
                query.setSortColumns("c.CREATE_DATE desc");
                if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                    Map<Integer, UserInfo> userInfoDictMap = userInfoService.getAllMap();
                    model.put("userInfoDictMap", userInfoDictMap);
                    List<UserInfo> userInfoList = userInfoService.getAll();
                    model.put("userInfoList", userInfoList);
                    page = bxMommentService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setRespondent_id(Integer.valueOf(memberId));
                        page = bxMommentService.selectPage(query);
                    }
                }
                String temp = "";
                for (BxMomment bxMomment : page.getResult()) {
                    temp = "";
                    if(bxMomment.getComment_tag()!=null && !bxMomment.getComment_tag().equals("")){
                        List<BxCommentTag> bxCommentTagList = bxCommentTagMapper.getCommentTagList(bxMomment.getComment_tag());
                        for (BxCommentTag bxCommentTag:bxCommentTagList){
                            temp+=bxCommentTag.getTag_content()+",";
                        }
                        bxMomment.setComment_tag(temp.substring(0,temp.length()-1));
                    }
                }
            }
            model.put("page", page);
            model.put("query", query);
            model.put("result", request.getParameter("result"));
            mav=new ModelAndView("/momment/mommentList", model);
        } catch (Exception e) {
            _logger.error("getMommentList 失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
            e.printStackTrace();
        }
        return mav;
	}
}
