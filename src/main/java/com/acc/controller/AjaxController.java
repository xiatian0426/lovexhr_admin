package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.BxProductImg;
import com.acc.model.BxToken;
import com.acc.service.*;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
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

    @Autowired
    private IBxTokenService bxTokenService;

    @Autowired
    private IBxProVideoService bxProVideoService;

    @Autowired
    private IBxThumbUpService thumbUpService;

    @Autowired
    private IBxQuestionService bxQuestionService;

    @Autowired
    private IBxCompanyService bxCompanyService;


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
            String id = request.getParameter("id");
            if(StringUtils.isNotEmpty(id)){
                BxProductImg bxProductImg = bxProductService.getProductDetailImgById(id);
                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                String fileSavePath=path + Constants.proDetailImgPath + bxProductImg.getProductId() + "/";
                new File(fileSavePath+bxProductImg.getImageUrl()).delete();
                bxProductService.deleteProductDetailImgById(id);
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

    @ResponseBody
    @RequestMapping(value = "/downloadImg", method = RequestMethod.POST)
    public Map<String, Object> downloadImg(final HttpServletRequest request,
                                           final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //处理中文乱码
            request.setCharacterEncoding("UTF-8");
            String fileName = request.getParameter("imgUrl");
            fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
            //处理浏览器兼容
            response.setContentType("application/msexcel;charset=utf-8");//定义输出类型
            Enumeration enumeration = request.getHeaders("User-Agent");
            String browserName = (String) enumeration.nextElement();
            boolean isMSIE = browserName.contains("MSIE");
            if (isMSIE) {
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF8"));
            } else {
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            }
            //url地址如果存在空格，会导致报错！  解决方法为：用+或者%20代替url参数中的空格。
            fileName = fileName.replace(" ", "%20");
            //图片下载
            URL url = new URL(fileName);
            URLConnection conn = url.openConnection();
            outputStream = response.getOutputStream();
            inputStream = conn.getInputStream();
            IOUtils.copy(inputStream, outputStream);
            model.put("info","0");
            model.put("message","下载成功!");
        } catch (IOException e) {
            e.printStackTrace();
            model.put("info","1");
            model.put("message","下载失败!");
        }finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
        return model;
    }

    /**
     * 生成小程序码
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveWxaCode", method = RequestMethod.POST)
    public Map<String, Object> saveWxaCode (final HttpServletRequest request,
                                                  final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try{
            String scene = request.getParameter("id");
            if(StringUtils.isNotEmpty(scene)) {
                String page = "page/msg_waist/msg_waist";
                BxToken bxToken = bxTokenService.getToken(0);
                if (bxToken != null && bxToken.getAccessToken() != null && !bxToken.getAccessToken().equals("")) {
                    String token = bxToken.getAccessToken();
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("scene", scene);  //参数
                    //            params.put("page", "page/msg_waist/msg_waist"); //位置
                    params.put("width", 430);
                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + token);  // 接口
                    httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
                    String body = JSON.toJSONString(params);           //必须是json模式的 post
                    StringEntity entity;
                    entity = new StringEntity(body);
                    entity.setContentType("image/png");
                    httpPost.setEntity(entity);
                    HttpResponse response1;
                    response1 = httpClient.execute(httpPost);
                    InputStream inputStream = response1.getEntity().getContent();
                    String name = scene + ".png";
                    String filePath = Constants.memberImgWxaCodePath;
                    String path = (String) request.getSession().getServletContext().getAttribute("webproRoot");
                    int result = PictureChange.saveToImgByInputStream(inputStream, path + filePath, name);  //保存图片
                    if (result == 1) {//保存成功
                        //保存数据
                        bxTokenService.updateMemberWxaCodeById(scene, name);
                    }
                    model.put("info", "1");
                    model.put("message", "删除成功!");
                }
            }
        } catch (Exception e) {
            _logger.error("删除招聘信息失败：" + ExceptionUtil.getMsg(e));
            model.put("info", "删除失败");
        }
        return model;
    }

    /**
     * 删除用户视频
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteProVideoById", method = RequestMethod.POST)
    public Map<String, Object> deleteProVideoById (final HttpServletRequest request,
                                                final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try{
            String id = request.getParameter("id");
            String memberId = request.getParameter("memberId");
            String videoUrl = request.getParameter("videoUrl");
            if(StringUtils.isNotEmpty(id)){
                bxProVideoService.deleteById(id);
                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                String fileSavePath=path + Constants.proVideoPath + memberId + "/";
                String oldVideoUrl = null;
                if(videoUrl!=null && !"".equals(videoUrl)){
                    oldVideoUrl = videoUrl.split("/")[videoUrl.split("/").length-1];
                }
                new File(fileSavePath+oldVideoUrl).delete();
                model.put("info","1");
                model.put("message","删除成功!");
            }
        } catch (Exception e) {
            _logger.error("删除用户视频失败：" + ExceptionUtil.getMsg(e));
            model.put("info", "删除失败");
        }
        return model;
    }
    /**
     * 删除点赞信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteThumbUpById", method = RequestMethod.POST)
    public Map<String, Object> deleteThumbUpById (final HttpServletRequest request,
                                                final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try{
            String id = request.getParameter("id");
            if(StringUtils.isNotEmpty(id)){
                thumbUpService.deleteById(Integer.valueOf(id));
                model.put("info","1");
                model.put("message","删除成功!");
            }
        } catch (Exception e) {
            _logger.error("删除点赞信息失败：" + ExceptionUtil.getMsg(e));
            model.put("info", "删除失败");
        }
        return model;
    }

    /**
     * 删除问题
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteQuestionById", method = RequestMethod.POST)
    public Map<String, Object> deleteQuestionById (final HttpServletRequest request,
                                                  final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try{
            String id = request.getParameter("id");
            if(StringUtils.isNotEmpty(id)){
                bxQuestionService.deleteById(id);
                model.put("info","1");
                model.put("message","删除成功!");
            }
        } catch (Exception e) {
            _logger.error("删除问题失败：" + ExceptionUtil.getMsg(e));
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
    @RequestMapping(value = "/deleteCompanyById", method = RequestMethod.POST)
    public Map<String, Object> deleteCompanyById (final HttpServletRequest request,
                                                final HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        try{
            String id = request.getParameter("id");
            String memberId = request.getParameter("memberId");
            String imageUrl = request.getParameter("imageUrl");
            if(StringUtils.isNotEmpty(id)){
                bxCompanyService.deleteById(id);
                String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                String fileSavePath=path + Constants.companyImgPath + memberId + "/";
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
}
