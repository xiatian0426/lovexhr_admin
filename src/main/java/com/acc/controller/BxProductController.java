package com.acc.controller;

import com.acc.exception.ExceptionUtil;
import com.acc.model.*;
import com.acc.service.IBxProductService;
import com.acc.service.IUserInfoService;
import com.acc.util.Constants;
import com.acc.util.PictureChange;
import com.acc.vo.Page;
import com.acc.vo.ProductQuery;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/product")
public class BxProductController {
	private static Logger _logger = LoggerFactory.getLogger(BxProductController.class);
	
	@Autowired
	private IBxProductService bxProductService;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 进入添加产品页
     * @param mav
     * @param request
     * @return
     */
    @RequestMapping(value = "/goAddProductByMemId")
    public ModelAndView goAddProductByMemId (ModelAndView mav, final HttpServletRequest request) {
        Map<String, Object> model = mav.getModel();
        try {
            List<UserInfo> userInfoList = userInfoService.getAll();
            model.put("userInfoList", userInfoList);
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            model.put("result", request.getParameter("result"));
            mav=new ModelAndView("/productData/addProductData", model);
        } catch (Exception e) {
            _logger.error("进入添加产品页失败：" + ExceptionUtil.getMsg(e));
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
        }
        return mav;
    }

    /**
     * 根据会员id获取对应权限产品
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProductByMemId", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getProductByMemId(ModelAndView mav, final HttpServletRequest request,@ModelAttribute ProductQuery query) throws IOException {
        Map<String, Object> model = mav.getModel();
        try{
            HttpSession session = request.getSession();
            UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
            model.put("staff", staff);
            Page<BxProduct> page = null;
            if(staff!=null){
                query.setSortColumns("c.CREATE_DATE desc");
                if(staff.getRoleId()!=null && staff.getRoleId().equals(Constants.ROLEIDO)){
                    Map<Integer, UserInfo> userInfoDictMap = userInfoService.getAllMap();
                    model.put("userInfoDictMap", userInfoDictMap);
                    List<UserInfo> userInfoList = userInfoService.getAll();
                    model.put("userInfoList", userInfoList);
                    page = bxProductService.selectPage(query);
                }else{
                    String memberId = String.valueOf(staff.getId());
                    if(StringUtils.isNotEmpty(memberId) ){
                        query.setMemberId(Integer.valueOf(memberId));
                        page = bxProductService.selectPage(query);
                    }
                }
                if(page != null && page.getResult()!=null && page.getResult().size()>0){
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + path + "/";
                    for(BxProduct bxProduct:page.getResult()){
                        bxProduct.setProductImg(basePath+ Constants.proImgPath+bxProduct.getId()+"/"+bxProduct.getProductImg());
                    }
                }
            }
            model.put("page", page);
            model.put("query", query);
            model.put("result", request.getParameter("result"));
            mav=new ModelAndView("/productData/productDataList", model);
        } catch (Exception e) {
            _logger.error("getProductByMemId失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
        }
        return mav;
    }

    /**
     * 管理端--点击修改/查看(回显)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProDetail", method = RequestMethod.GET)
    public ModelAndView getProDetail(ModelAndView mav,final HttpServletRequest request) throws IOException {
        Map<String, Object> model = mav.getModel();
        try{
            String basePath="";
            String productId = request.getParameter("productId");
            if(StringUtils.isNotEmpty(productId)){
                List<BxProduct> bxProductList = bxProductService.getProDetail(productId);
                if(bxProductList!=null && bxProductList.size()>0){
                    BxProduct bxProductResult= new BxProduct();
                    bxProductResult.setId(bxProductList.get(0).getId());
                    String path = request.getContextPath();
                    basePath = request.getScheme() + "://"
                            + request.getServerName() + ":" + request.getServerPort()
                            + path + "/";
                    bxProductResult.setProductImg(basePath+ Constants.proImgPath+bxProductList.get(0).getId()+"/"+bxProductList.get(0).getProductImg());
                    bxProductResult.setProductName(bxProductList.get(0).getProductName());
                    bxProductResult.setProductDesc(bxProductList.get(0).getProductDesc());
                    bxProductResult.setProductOrder(bxProductList.get(0).getProductOrder());
                    bxProductResult.setVideoId(bxProductList.get(0).getVideoId());
                    if(bxProductList.get(0).getProductVideo()!=null && !"".equals(bxProductList.get(0).getProductVideo())){
                        bxProductResult.setProductVideo(basePath+ Constants.proVideoPath+bxProductList.get(0).getId()+"/"+bxProductList.get(0).getProductVideo());
                    }
                    List<BxProductImg> bxProductImgList = new ArrayList<BxProductImg>();
                    BxProductImg bxProductImg;
                    for(BxProduct bxProduct:bxProductList){
                        if(bxProduct.getImgId()!=0){
                            bxProductImg = new BxProductImg();
                            bxProductImg.setId(bxProduct.getImgId());
                            bxProductImg.setImageUrl(basePath+ Constants.proDetailImgPath+bxProduct.getId()+"/"+bxProduct.getImageUrl());
                            bxProductImg.setImageOrder(bxProduct.getImageOrder());
                            bxProductImgList.add(bxProductImg);
                        }
                    }
                    bxProductResult.setBxProductImgList(bxProductImgList);
                    //产品详情最多上传10个图片 所以需要生成
                    List imgNulllist = new ArrayList();
                    for (int i=0;i<10-bxProductImgList.size();i++){
                        imgNulllist.add(1);
                    }
                    model.put("imgNulllist",imgNulllist);
                    BxCase bxCase = bxProductService.getCaseDetail(productId);
                    bxProductResult.setBxCase(bxCase);
                    model.put("bxProductResult",bxProductResult);
                }
            }
            model.put("result", request.getParameter("result"));
            model.put("basePath",basePath);
            mav=new ModelAndView("/productData/editProductData", model);
        } catch (Exception e) {
            _logger.error("getProDetail失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
            mav = new ModelAndView(Constants.SERVICES_ERROR, model);
        }
        return mav;
    }

    /**
     * 管理端--点击删除
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public void deleteById(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> result = new HashMap<String, Object>();
        try{
            String productId = request.getParameter("productId");
            if(StringUtils.isNotEmpty(productId)){
                bxProductService.deleteById(productId);
                result.put("code",0);
                result.put("message","删除成功!");
            }
        } catch (Exception e) {
            result.put("code",-1);
            result.put("message","删除失败!");
            _logger.error("deleteById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }

    /**
     * 后台管理--更新商品信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addOrUpdateProductById", method = RequestMethod.POST)
    public ModelAndView addOrUpdateProductById(ModelAndView mav,final HttpServletRequest request, @RequestParam(value="file",required=false)MultipartFile[] file, @ModelAttribute BxProduct bxProduct
                                  ) throws IOException {
        Map<String, Object> model = mav.getModel();
        String result;
        boolean boo = true;
        try {
            if (bxProduct != null) {
                if(bxProduct.getType()!=null && !"".equals(bxProduct.getType())){
                    if(file != null){
                        HttpSession session = request.getSession();
                        UserInfo staff = (UserInfo)session.getAttribute(Constants.LOGINUSER);
                        if(bxProduct.getType().equals("0")){//添加
                            if(bxProduct.getMemberId()==0){
                                bxProduct.setMemberId(staff.getId());
                            }
                            bxProduct.setCreateId(staff.getId());
                            bxProductService.addProduct(bxProduct);
                        }else{
                            if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                                boo = false;
                                bxProduct.setProductImg(null);
                                bxProduct.setModifierId(String.valueOf(staff.getId()));
                                bxProductService.updateProduct(bxProduct);
                            }
                        }
                        if(boo){
                            String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                            String fileSavePath=path + Constants.proImgPath + bxProduct.getId() + "/";
                            Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,true,true);
                            int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                            List<String> imgNameList = (List<String>)mapImg.get("list");
                            if(re == 0){
                                if(imgNameList!=null && imgNameList.size()>0){
                                    bxProduct.setProductImg(imgNameList.get(0));
                                    bxProduct.setModifierId(String.valueOf(staff.getId()));
                                    bxProductService.updateProduct(bxProduct);
                                    result = "添加/更新成功!";
                                }else{
                                    result = "参数有误，请联系管理员!";
                                }
                            }else{
                                result = "添加/更新失败!";
                            }
                        }else{
                            result = "更新成功!";
                        }
                    }else{
                        result = "文件不能为空!";
                    }
                }else{
                    result = "参数有误，请联系管理员!";
                }
            } else {
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            result = "添加/更新失败，请联系管理员!";
            _logger.error("updateProductById失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        model.put("result", result);
        if (bxProduct != null && bxProduct.getType()!=null && !"".equals(bxProduct.getType())&&bxProduct.getType().equals("0")) {
            mav.setViewName("redirect:/product/goAddProductByMemId");
        }else{
            model.put("productId", bxProduct.getId());
            mav.setViewName("redirect:/product/getProDetail");
        }
        return mav;
    }

   /**
    * 后台管理--添加商品视频信息
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/addProductVideo", method = RequestMethod.POST)
    public ModelAndView addProductVideo(ModelAndView mav,final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProductVideo bxProductVideo,
                                       @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        Map<String, Object> model = mav.getModel();
        String result;
        try {
            BxProduct bxProduct = bxProductService.getProductById(bxProductVideo.getProductId());
            if(bxProduct!=null){
                if (bxProductVideo != null) {
                    if(file != null){
                        String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                        String fileSavePath=path + Constants.proVideoPath + bxProductVideo.getProductId() + "/";
                        BxProductVideo oldbxProductVideo = bxProductService.getProductDetailVideoById(String.valueOf(bxProductVideo.getId()));
                        if(oldbxProductVideo!=null){
                            //删除old
                            new File(fileSavePath+oldbxProductVideo.getVideoUrl()).delete();
                            bxProductService.deleteProductDetailVideoById(String.valueOf(bxProductVideo.getId()));
                        }
                        //添加new
                        Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,true,false);
                        int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                        if(re == 0){
                            List<String> videoNameList = (List<String>)mapImg.get("list");
                            if(videoNameList!=null && videoNameList.size()>0){
                                bxProductVideo.setVideoUrl(videoNameList.get(0));
                                bxProductService.insertProductVideo(bxProductVideo);
                            }
                            result = "添加成功";
                        }else{
                            result = "添加失败!";
                        }
                    }else{
                        result = "视频文件不能为空!";
                    }
                }else{
                    result = "参数有误，请联系管理员!";
                }
            }else{
                result = "参数有误，请联系管理员!";
            }
        } catch (Exception e) {
            result = "添加失败，请联系管理员!";
            _logger.error("addProductVideo失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        model.put("result", result);
        model.put("productId", bxProductVideo.getProductId());
        mav.setViewName("redirect:/product/getProDetail");
        return mav;
    }
    /**
     * 后台管理--修改商品图片信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/editProductDetailImg", method = RequestMethod.POST)
    public ModelAndView editProductDetailImg(ModelAndView mav,final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute BxProductImg bxProductImg,
                                             @RequestParam(value="file",required=false)MultipartFile[] file) throws IOException {
        Map<String, Object> model = mav.getModel();
        String result;
        try {
            if(file != null && file.length>0){
                if(file[0].getOriginalFilename()==null || "".equals(file[0].getOriginalFilename())){
                    bxProductImg.setImageUrl(null);
                    bxProductService.updateProductImg(bxProductImg);
                    result = "添加成功";
                }else{
                    String path = (String)request.getSession().getServletContext().getAttribute("proRoot");
                    String fileSavePath=path + Constants.proDetailImgPath + bxProductImg.getProductId() + "/";
                    Map<String,Object> mapImg = PictureChange.imageUpload(file,fileSavePath,false,true);
                    int re = Integer.valueOf((String)mapImg.get("code")).intValue();
                    if(re == 0){
                        //删除老图片
                        String imgUrl = null;
                        if(bxProductImg.getImageUrl()!=null && !"".equals(bxProductImg.getImageUrl())){
                            imgUrl = bxProductImg.getImageUrl().split("/")[bxProductImg.getImageUrl().split("/").length-1];
                        }
                        File oldFile = new File(fileSavePath+imgUrl);
                        oldFile.delete();
                        List<String> imgNameList = (List<String>)mapImg.get("list");
                        if(imgNameList!=null && imgNameList.size()>0){
                            bxProductImg.setImageUrl(imgNameList.get(0));
                            if(bxProductImg.getId()!=0){
                                //更新
                                bxProductService.updateProductImg(bxProductImg);
                            }else{
                                //添加
                                bxProductService.insertProductImg(bxProductImg);
                            }
                        }
                        result = "添加成功";
                    }else{
                        result = "添加失败!";
                    }
                }
            }else{
                result = "文件不能为空!";
            }
        } catch (Exception e) {
            result = "添加失败，请联系管理员!";
            _logger.error("aeditProductDetailImg失败：" + ExceptionUtil.getMsg(e));
            e.printStackTrace();
        }
        model.put("result", result);
        model.put("productId", bxProductImg.getProductId());
        mav.setViewName("redirect:/product/getProDetail");
        return mav;
    }

    @RequestMapping(value = "/openVideo", method = RequestMethod.GET)
    public ModelAndView openVideo(ModelAndView mav,final HttpServletRequest request) throws IOException {
        Map<String, Object> model = mav.getModel();
        String videoUrl = request.getParameter("videoUrl");
        model.put("videoUrl",videoUrl);
        return new ModelAndView("/productData/openVideo", model);
    }
}
