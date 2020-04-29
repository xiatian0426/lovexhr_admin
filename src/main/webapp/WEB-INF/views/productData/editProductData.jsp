<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改产品</title>
	<%@ include file="../common/jsp_contants.jsp"%>
	<%@ include file="../common/js_contants.jsp"%>
	<link rel="stylesheet" type="text/css" href="${cssRoot }/right_style.css" />
	<link rel="stylesheet" type="text/css" href="${cssRoot }/zTreeStyle.css" />
	<script type="text/javascript" src="${jsRoot }/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${jsRoot }/move_sel_tree.js"></script>
	<!-- 日历控件 -->
	<script type="text/javascript" src="${toolRoot }/My97DatePicker/WdatePicker.js"></script>
	<!-- ZTree控件 -->
	<script type="text/javascript" src="${jsRoot }/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="${jsRoot }/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript" src="${jsRoot }/dtree.js"></script>
	<!-- 验证 -->
	<link rel="stylesheet" href="${toolRoot }/validata/validationEngine.css" />
	<script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine-zh_CN.js" charset="utf-8"></script>
	<!-- 引入 Bootstrap -->
	<link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
	<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
	<!-- 文档编辑 -->
	<script type="text/javascript" charset="utf-8"
		src="<%=toolRoot%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=toolRoot%>/kindeditor/lang/zh_CN.js"></script>
	<!-- 引入 select2 -->
	<link href="${toolRoot}/select/select2.min.css" rel="stylesheet">
	<script src="${toolRoot}/select/select2.full.min.js"></script>
	<script src="${toolRoot}/select/select2-self.js"></script>
	<script type="text/javascript">
		//字符串去空格
		String.prototype.trim=function(){
			return this.replace(/(^\s*)|(\s*$)/g, "");
		}
		var editor;
		KindEditor.ready(function(K) {
			//创建编辑器
			editor = K.create('#editor_id');
		});
		$(function(){
			//开启表单验证
			$("#editMessageDataFrom").validationEngine();
            $("#editMessageVideoDataFrom").validationEngine();
            $("#editMessageImgDataFrom").validationEngine();
            var result = $("#result").val();
            if(result != ""){
                alert(result);
            }
		});
		function editProduct(){
            var re = new RegExp("^[0-9]*[0-9][0-9]*$");
            var productOrder = $("#productOrder").val();
            if (productOrder != "") {
                if (!re.test(productOrder)) {
                    $("#productOrder").validationEngine("showPrompt","排序只能为整数!","error");
                    $("#productOrder").focus();
                    return false;
                }
            }else{
                $("#productOrder").validationEngine("showPrompt","排序不能为空!","error");
                $("#productOrder").focus();
                return false;
            }
            var bxCaseAge = $("#bxCaseAge").val();
            if (bxCaseAge != "") {
                if (!re.test(bxCaseAge)) {
                    $("#bxCaseAge").validationEngine("showPrompt","年龄只能为整数!","error");
                    $("#bxCaseAge").focus();
                    return false;
                }
            }else{
                $("#bxCaseAge").validationEngine("showPrompt","年龄不能为空!","error");
                $("#bxCaseAge").focus();
                return false;
            }
            var bxCaseCost = $("#bxCaseCost").val();
            if (bxCaseCost != "") {
                if (isNaN(bxCaseCost)) {
                    $("#bxCaseCost").validationEngine("showPrompt","保费格式不正确!","error");
                    $("#bxCaseCost").focus();
                    return false;
                }
            }else{
                $("#bxCaseCost").validationEngine("showPrompt","保费不能为空!","error");
                $("#bxCaseCost").focus();
                return false;
            }
            var bxCaseTimeLimit = $("#bxCaseTimeLimit").val();
            if (bxCaseTimeLimit != "") {
                if (!re.test(bxCaseTimeLimit)) {
                    $("#bxCaseTimeLimit").validationEngine("showPrompt","保障期限只能为整数!","error");
                    $("#bxCaseTimeLimit").focus();
                    return false;
                }
            }else{
                $("#bxCaseTimeLimit").validationEngine("showPrompt","保障期限不能为空!","error");
                $("#bxCaseTimeLimit").focus();
                return false;
            }

        }

        function editProductVideo(){
            var productVideo = $("#productVideo").val().length;
            if(productVideo==""){
                $("#productVideo").validationEngine("showPrompt","荣誉图片不能为空!","error");
                $("#productVideo").focus();
                return false;
            }
        }
        function editProductImage(id){
            var re = new RegExp("^[0-9]*[0-9][0-9]*$");
            var imageOrder = $("#productOrder"+id).val();
            if (imageOrder != "") {
                if (!re.test(imageOrder)) {
                    $("#imageOrder"+id).validationEngine("showPrompt","排序只能为整数!","error");
                    $("#imageOrder"+id).focus();
                    return false;
                }
            }else{
                $("#imageOrder"+id).validationEngine("showPrompt","排序不能为空!","error");
                $("#imageOrder"+id).focus();
                return false;
            }
        }
        function addProductImage(){
            var fileNew = $("#fileNew").val().length;
            if(fileNew==""){
                $("#fileNew").validationEngine("showPrompt","产品详情图片不能为空!","error");
                $("#fileNew").focus();
                return false;
            }
            var re = new RegExp("^[0-9]*[0-9][0-9]*$");
            var imageOrderNew = $("#imageOrderNew").val();
            if (imageOrderNew != "") {
                if (!re.test(imageOrderNew)) {
                    $("#imageOrderNew").validationEngine("showPrompt","排序只能为整数!","error");
                    $("#imageOrderNew").focus();
                    return false;
                }
            }else{
                $("#imageOrderNew").validationEngine("showPrompt","排序不能为空!","error");
                $("#imageOrderNew").focus();
                return false;
            }
        }
        function deleteProductDetailImgById(id,productId){
		    var  basePath = $("#basePath").val();
            $.ajax({
                url:'/ajax/deleteProductDetailImgById',
                data:{
                    id:id
                },
                dataType:'json',
                type:'post',
                cache:false,
                async:false,
                success:function(data) {
                    if(data.info=='1'){
                        alert("操作成功!");
                        window.location.href = basePath+"product/getProDetail?productId="+productId;
                    }else{
                        alert("操作失败!");
                    }
                },
                error : function(){
                    alert("操作失败!");
                }
            });
        }
        function openVideo(videoUrl){
            var openUrl = "/product/openVideo?videoUrl="+videoUrl;//弹出窗口的url
            var iWidth=700; //弹出窗口的宽度;
            var iHeight=500; //弹出窗口的高度;
            var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
            var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
            var opener= window.open(openUrl,"","height="+iHeight+", width="+iWidth+", top="+iTop+",left="+iLeft+",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
        }
	</script>
</head>
<body style=" font-size: 13px;">
<input id="result" value="${result}" type="hidden"/>
<input id="basePath" value="${basePath}" type="hidden"/>
	<form action="/product/addOrUpdateProductById" name="editMessageDataFrom" method="post" target="_self" id="editMessageDataFrom" onsubmit="return editProduct();" enctype="multipart/form-data">

        <input type="hidden" name="type" value="1">
        <input type="hidden" name="id" value="${bxProductResult.id }">
        <input type="hidden" name="memberId" value="${bxProductResult.bxCase.memberId }">
        <input type="hidden" name="createId" value="${bxProductResult.bxCase.createId }">
		<div class="clearB"></div>
		<div class="r_box" style="padding: 5px;">
			<div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
                产品信息修改
			</div>
			<table  style=" font-size: 13px; " align="center" border="1">
                <tr>
                    <td style=" padding: 10px 35px;font-weight: bold" colspan="4" align="center">产品信息</td>
                </tr>
				<tr>
					<td style="background:#A0E0F7; padding: 10px 35px; width: 150px;"><font color="red">*</font>&nbsp;产品名称：</td>
					<td >
						<input id="productName" name="productName" value="${bxProductResult.productName}" type="text" style="width: 172px;"
						class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
					</td>
					<td style="background:#A0E0F7; padding: 10px 35px; width: 150px;"><font color="red">*</font>&nbsp;产品排序：</td>
					<td >
                        <input id="productOrder" name="productOrder" value="${bxProductResult.productOrder}" type="text" style="width: 172px;"
                               class="text-input self-form-control"/>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;产品描述：</td>
					<td >
                        <input id="productDesc" name="productDesc" value="${bxProductResult.productDesc}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
					</td>
					<td style="background:#A0E0F7; padding: 10px 35px;">&nbsp;产品图片：</td>
					<td >
                        <input id="file" type="file" name="file"/>
                        <img src="${bxProductResult.productImg}" width="50" height="50"/>
					</td>
				</tr>
                <tr>
                    <td style="padding: 10px 35px;font-weight: bold" colspan="4" align="center">产品案例</td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;产品名称：</td>
                    <td >
                        <input id="bxCaseProductName" name="bxCaseProductName" value="${bxProductResult.bxCase.productName}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;姓名：</td>
                    <td >
                        <input id="bxCaseName" name="bxCaseName" value="${bxProductResult.bxCase.name}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[10]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;年龄：</td>
                    <td >
                        <input id="bxCaseAge" name="bxCaseAge" value="${bxProductResult.bxCase.age}" type="text" style="width: 172px;"
                               class="text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;保费：</td>
                    <td >
                        <input id="bxCaseCost" name="bxCaseCost" value="${bxProductResult.bxCase.cost}" type="text" style="width: 172px;"
                               class="text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;保障期限：</td>
                    <td >
                        <input id="bxCaseTimeLimit" name="bxCaseTimeLimit" value="${bxProductResult.bxCase.timeLimit}" type="text" style="width: 172px;"
                               class="text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;投保内容：</td>
                    <td >
                        <input id="bxCaseTbContext" name="bxCaseTbContext" value="${bxProductResult.bxCase.tbContext}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[500]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;理赔内容：</td>
                    <td >
                        <input id="bxCaseLpContext" name="bxCaseLpContext" value="${bxProductResult.bxCase.lpContext}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[500]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;出险内容：</td>
                    <td >
                        <input id="bxCaseCxContext" name="bxCaseCxContext" value="${bxProductResult.bxCase.cxContext}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[500]] text-input self-form-control"/>
                    </td>
                </tr>
			</table>
			<div class="sub_div">
				<input type="submit" class="sub_btn" value=" "/>
				<input type="button" class="back_btn" onclick="javascript:history.go(-1);" value=" " />
			</div>
		</div>
	</form>

    <form action="/product/addProductVideo" name="editMessageVideoDataFrom" method="post" target="_self" id="editMessageVideoDataFrom" onsubmit="return editProductVideo();" enctype="multipart/form-data">
        <div class="clearB"></div>
        <input type="hidden" name="productId" value="${bxProductResult.id }">
        <input type="hidden" name="id" value="${bxProductResult.videoId }">
        <div class="r_box" style="padding: 5px;">
            <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
                产品详情--视频信息修改
            </div>
            <table  style=" font-size: 13px; " align="center" border="1">
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;"><font color="red">*</font>&nbsp;产品视频：</td>
                    <td >
                        <input type="file" id="productVideo" name="file" value=""></td>
                    <c:if test="${bxProductResult.productVideo!=null && bxProductResult.productVideo!=''}">
                        <td>
                            <font color="green"><a style="cursor:pointer" onclick="openVideo('${bxProductResult.productVideo}')">点击查看</a></font>
                        </td>
                    </c:if>

                </tr>
            </table>
            <div class="sub_div">
                <input type="submit" class="sub_btn" value=" "/>
                <input type="button" class="back_btn" onclick="javascript:history.go(-1);" value=" " />
            </div>
        </div>
    </form>

    <div class="clearB"></div>
    <div class="r_box" style="padding: 5px;">
        <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
            产品详情--信息修改
        </div>
        <table  style=" font-size: 13px; " align="center" border="1">
            <c:if test="${not empty bxProductResult.bxProductImgList}">
            <c:forEach items="${bxProductResult.bxProductImgList}" var="bxProductImg" varStatus="count">
                <form action="/product/editProductDetailImg" name="editMessageImgDataFrom" method="post" target="_self" id="editMessageImgDataFrom"  onsubmit="return editProductImage('${bxProductImg.id }');" enctype="multipart/form-data">
                        <input type="hidden" name="productId" value="${bxProductResult.id }">
                        <c:if test="${bxProductImg.id != null && bxProductImg.id != 0}">
                            <input type="hidden" name="id" value="${bxProductImg.id }">
                            <input type="hidden" name="imageUrl" value="${bxProductImg.imageUrl }">
                        </c:if>
                    <tr>
                        <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;">&nbsp;产品详情信息：</td>
                        <td >
                            <input type="file" name="file" value="">
                            <img src="${bxProductImg.imageUrl}" width="50" height="50"/>
                        </td>
                        <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;">排序：</td>
                        <td >
                            <input id="imageOrder${bxProductImg.id }" name="imageOrder" value="${bxProductImg.imageOrder}" type="text" style="width: 172px;"
                                   class="text-input self-form-control"/>
                        </td>
                        <td style="background:#A0E0F7; padding: 10px 20px; width: 180px;">
                            <button type="submit" class="btn btn-success">保存</button>
                            <button type="button" class="btn btn-success" onclick="deleteProductDetailImgById('${bxProductImg.id }','${bxProductResult.id }');">删除</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </c:if>
            <c:if test="${not empty imgNulllist}">
                <c:forEach items="${imgNulllist}" var="bxProductImg" varStatus="count">
                    <form action="/product/editProductDetailImg" name="messageImgDataForm" method="post" target="_self" id="editMessageImgDataFrom" onsubmit="return addProductImage();"enctype="multipart/form-data">
                        <input type="hidden" name="productId" value="${bxProductResult.id }">
                        <tr>
                            <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;"><font color="red">*</font>&nbsp;产品详情信息：</td>
                            <td >
                                <input type="file" id="fileNew" name="file" value="">
                            </td>
                            <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;">排序：</td>
                            <td >
                                <input name="imageOrder" id="imageOrderNew" value="0" type="text" style="width: 172px;"
                                       class="text-input self-form-control"/>
                            </td>
                            <td style="background:#A0E0F7; padding: 10px 20px; width: 180px;">
                                <button type="submit" class="btn btn-success">保存</button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </c:if>
        </table>
        <div class="sub_div">
            <input type="button" class="back_btn" onclick="javascript:history.go(-1);" value=" " />
        </div>
    </div>
</body>
</html>
