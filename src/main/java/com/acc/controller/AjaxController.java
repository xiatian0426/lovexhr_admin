package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.service.IBxHonorService;
import com.acc.service.IBxProductService;
import com.acc.service.IBxQAService;
import com.acc.service.IBxRecruitService;
import com.acc.util.Constants;
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
import java.io.File;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value="/ajax")
public class AjaxController {
	private static Logger _logger = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private IBxProductService bxProductService;

    @Autowired
    private IBxQAService bxQAService;

    @Autowired
    private IBxHonorService bxHonorService;

    @Autowired
    private IBxRecruitService bxRecruitService;


	/**
	 * 删除产品信息
	 * @param request
	 * @param response
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

    /**
     * 删除产品详情图片
     * @param request
     * @param response
     * @return
     */
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
     * 删除QA
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteQAById", method = RequestMethod.POST)
    public Map<String, Object> deleteQAById (final HttpServletRequest request,
                                                  final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try{
            String qaId = request.getParameter("id");
            if(StringUtils.isNotEmpty(qaId)){
                bxQAService.deleteById(qaId);
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
     * 删除荣誉
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteHonorById", method = RequestMethod.POST)
    public Map<String, Object> deleteHonorById (final HttpServletRequest request,
                                             final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try{
            String id = request.getParameter("id");
            String memberId = request.getParameter("memberId");
            String imageUrl = request.getParameter("imageUrl");
            if(StringUtils.isNotEmpty(id)){
                bxHonorService.deleteById(Integer.valueOf(id));
                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                String fileSavePath=path + Constants.honorImgPath + memberId + "/";
                String imgUrl = null;
                if(imageUrl!=null && !"".equals(imageUrl)){
                    imgUrl = imageUrl.split("/")[imageUrl.split("/").length-1];
                }
                new File(fileSavePath+imgUrl).delete();
                model.put("info","1");
                model.put("message","删除成功!");
            }
        } catch (Exception e) {
            _logger.error("删除荣誉信息失败：" + ExceptionUtil.getMsg(e));
            model.put("info", "删除失败");
        }
        return model;
    }

    /**
     * 删除招聘
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteRecruitById", method = RequestMethod.POST)
    public Map<String, Object> deleteRecruitById (final HttpServletRequest request,
                                                final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try{
            String id = request.getParameter("id");
            String memberId = request.getParameter("memberId");
            String imageUrl = request.getParameter("imageUrl");
            if(StringUtils.isNotEmpty(id)){
                bxRecruitService.deleteById(id);
                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                String fileSavePath=path + Constants.recruitImgPath + memberId + "/";
                String imgUrl = null;
                if(imageUrl!=null && !"".equals(imageUrl)){
                    imgUrl = imageUrl.split("/")[imageUrl.split("/").length-1];
                }
                new File(fileSavePath+imgUrl).delete();
                model.put("info","1");
                model.put("message","删除成功!");
            }
        } catch (Exception e) {
            _logger.error("删除招聘信息失败：" + ExceptionUtil.getMsg(e));
            model.put("info", "删除失败");
        }
        return model;
    }
}
