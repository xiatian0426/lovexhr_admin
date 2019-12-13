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
		});
		function edit(){
			var date = $("#date").val();
			if(date==''){
				$("#date").validationEngine("showPrompt","日期不能是空","error");
				return false;
			}
			var province = $("#province").val();
			if(province=='' || province=='0'){
				$("#province").validationEngine("showPrompt","省市不能是空","error");
				return false;
			}
			var messagetype = $("#messagetype").val();
			if(messagetype=='' || messagetype=='0'){
				$("#messagetype").validationEngine("showPrompt","信息类型不能是空","error");
				return false;
			}
			if(editor.html()==''){
				alert("内容不能为空!");
				return false;
			}
		}
	</script>
</head>
<body style=" font-size: 13px;">
	<form action="/product/addOrUpdateProductById" name="messageDataForm" method="post" target="_self" id="editMessageDataFrom" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${bxProductResult.id }">
        <input type="hidden" name="memberId" value="${bxProductResult.bxCase.memberId }">
        <input type="hidden" name="createId" value="${bxProductResult.bxCase.createId }">
        <c:if test="${bxProductResult.id != null}">
            <input type="hidden" name="type" value="1">
        </c:if>
        <c:if test="${bxProductResult.id == null}">
            <input type="hidden" name="type" value="0">
        </c:if>
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
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;产品描述：</td>
					<td >
                        <input id="productDesc" name="productDesc" value="${bxProductResult.productDesc}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        <%--<textarea id="editor_id" name="productDesc" style="height: 350px;">${bxProductResult.productDesc }</textarea>--%>
					</td>
					<td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;产品图片：</td>
					<td >
                        <input type="file" name="file"/>
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
                        <%--<textarea id="editor_id" name="productDesc" style="height: 350px;">${bxProductResult.productDesc }</textarea>--%>
                    </td>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;姓名：</td>
                    <td >
                        <input id="bxCaseName" name="bxCaseName" value="${bxProductResult.bxCase.name}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;年龄：</td>
                    <td >
                        <input id="bxCaseAge" name="bxCaseAge" value="${bxProductResult.bxCase.age}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        <%--<textarea id="editor_id" name="productDesc" style="height: 350px;">${bxProductResult.productDesc }</textarea>--%>
                    </td>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;保费：</td>
                    <td >
                        <input id="bxCaseCost" name="bxCaseCost" value="${bxProductResult.bxCase.cost}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;保障期限：</td>
                    <td >
                        <input id="bxCaseTimeLimit" name="bxCaseTimeLimit" value="${bxProductResult.bxCase.timeLimit}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        <%--<textarea id="editor_id" name="productDesc" style="height: 350px;">${bxProductResult.productDesc }</textarea>--%>
                    </td>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;投保内容：</td>
                    <td >
                        <input id="bxCaseTbContext" name="bxCaseTbContext" value="${bxProductResult.bxCase.tbContext}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;理赔内容：</td>
                    <td >
                        <input id="bxCaseLpContext" name="bxCaseLpContext" value="${bxProductResult.bxCase.lpContext}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        <%--<textarea id="editor_id" name="productDesc" style="height: 350px;">${bxProductResult.productDesc }</textarea>--%>
                    </td>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;出险内容：</td>
                    <td >
                        <input id="bxCaseCxContext" name="bxCaseCxContext" value="${bxProductResult.bxCase.cxContext}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                    </td>
                </tr>
			</table>
			<div class="sub_div">
				<input type="submit" class="sub_btn" value=" "/>
				<input type="button" class="back_btn" onclick="javascript:history.go(-1);" value=" " />
			</div>
		</div>
	</form>

    <form action="/product/addProductVideo" name="messageVideoDataForm" method="post" target="_self" id="editMessageVideoDataFrom" enctype="multipart/form-data">
        <div class="clearB"></div>
        <input type="hidden" name="productId" value="${bxProductResult.id }">
        <div class="r_box" style="padding: 5px;">
            <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
                产品详情--视频信息修改
            </div>
            <table  style=" font-size: 13px; " align="center" border="1">
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;"><font color="red">*</font>&nbsp;产品视频：</td>
                    <td >
                        <input type="file" id="productVideo" name="file" value="">
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
                <form action="/product/editProductDetailImg" name="messageImgDataForm" method="post" target="_self" id="editMessageImgDataFrom" enctype="multipart/form-data">
                        <input type="hidden" name="productId" value="${bxProductResult.id }">
                        <c:if test="${bxProductImg.id != null && bxProductImg.id != 0}">
                            <input type="hidden" name="id" value="${bxProductImg.id }">
                            <input type="hidden" name="imageUrl" value="${bxProductImg.imageUrl }">
                        </c:if>
                    <tr>
                        <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;"><font color="red">*</font>&nbsp;产品详情信息：</td>
                        <td >
                            <input type="file" name="file" value="">
                            <img src="${bxProductImg.imageUrl}" width="50" height="50"/>
                        </td>
                        <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;">排序：</td>
                        <td >
                            <input name="imageOrder" value="${bxProductImg.imageOrder}" type="text" style="width: 172px;"
                                   class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        </td>
                        <td colspan="2" style="background:#A0E0F7; padding: 10px 20px; width: 150px;">
                            <input type="submit" class="sub_btn" value=" "/>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </c:if>
            <c:if test="${not empty imgNulllist}">
                <c:forEach items="${imgNulllist}" var="bxProductImg" varStatus="count">
                    <form action="/product/editProductDetailImg" name="messageImgDataForm" method="post" target="_self" id="editMessageImgDataFrom" enctype="multipart/form-data">
                        <input type="hidden" name="productId" value="${bxProductResult.id }">
                        <tr>
                            <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;"><font color="red">*</font>&nbsp;产品详情信息：</td>
                            <td >
                                <input type="file" name="file" value="">
                            </td>
                            <td style="background:#A0E0F7; padding: 10px 20px; width: 150px;">排序：</td>
                            <td >
                                <input name="imageOrder" value="0" type="text" style="width: 172px;"
                                       class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                            </td>
                            <td colspan="2" style="background:#A0E0F7; padding: 10px 20px; width: 150px;">
                                <input type="submit" class="sub_btn" value=" "/>
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
