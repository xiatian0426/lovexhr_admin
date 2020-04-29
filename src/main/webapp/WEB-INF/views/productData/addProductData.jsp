<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改客户</title>
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
			$("#messageDataForm").validationEngine();
            var result = $("#result").val();
            if(result != ""){
                alert(result);
            }
		});
		function perReset(){
			if(confirm('确定重置吗?')){
				location.reload();
			}
		}
		function addData(){
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
            var file = $("#file").val().length;
            if(file==""){
                $("#file").validationEngine("showPrompt","产品图片不能为空!","error");
                $("#file").focus();
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

            var memberIdFlag = $("#memberIdFlag").val();
            if(memberIdFlag != ""){
                //需要验证
                var memberId = $("#memberId").val();
                if(memberId=='0'){
                    $("#memberId").validationEngine("showPrompt","请选择所属人!","error");
                    $("#memberId").focus();
                    return false;
                }
            }
		}
	</script>
</head>
<body style=" font-size: 13px;">
<input id="result" value="${result}" type="hidden"/>
<form action="/product/addOrUpdateProductById" name="messageDataForm" method="post" target="_self" id="messageDataForm" onsubmit="return addData();" enctype="multipart/form-data" >
    <input type="hidden" name="type" value="0">
    <div class="clearB"></div>
    <div class="r_box" style="padding: 5px;">
        <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
            产品信息添加
        </div>
        <table  style=" font-size: 13px; " align="center" border="1">
            <tr>
                <td style=" padding: 10px 35px;font-weight: bold" colspan="4" align="center">产品信息</td>
            </tr>
            <tr>
                <td style="background:#A0E0F7; padding: 10px 35px; width: 150px;"><font color="red">*</font>&nbsp;产品名称：</td>
                <td >
                    <input id="productName" name="productName" value="" type="text" style="width: 172px;"
                           class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                </td>
                <td style="background:#A0E0F7; padding: 10px 35px; width: 150px;"><font color="red">*</font>&nbsp;产品排序：</td>
                <td >
                    <input id="productOrder" name="productOrder" value="" type="text" style="width: 172px;"
                           class="text-input self-form-control"/>
                </td>
            </tr>
            <tr>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;产品描述：</td>
                <td >
                    <input id="productDesc" name="productDesc" value="" type="text" style="width: 172px;"
                           class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                </td>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;产品图片：</td>
                <td >
                    <input id="file" type="file" name="file"/>
                </td>
            </tr>
            <tr>
                <td style="padding: 10px 35px;font-weight: bold" colspan="4" align="center">产品案例</td>
            </tr>
            <tr>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;产品名称：</td>
                <td >
                    <input id="bxCaseProductName" name="bxCaseProductName" value="" type="text" style="width: 172px;"
                           class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                </td>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;姓名：</td>
                <td >
                    <input id="bxCaseName" name="bxCaseName" value="" type="text" style="width: 172px;"
                           class="validate[required,noSpecialCaracters,maxSize[10]] text-input self-form-control"/>
                </td>
            </tr>
            <tr>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;年龄：</td>
                <td >
                    <input id="bxCaseAge" name="bxCaseAge" value="" type="text" style="width: 172px;"
                           class="text-input self-form-control"/>
                </td>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;保费：</td>
                <td >
                    <input id="bxCaseCost" name="bxCaseCost" value="" type="text" style="width: 172px;"
                           class="text-input self-form-control"/>
                </td>
            </tr>
            <tr>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;保障期限：</td>
                <td >
                    <input id="bxCaseTimeLimit" name="bxCaseTimeLimit" value="" type="text" style="width: 172px;"
                           class="text-input self-form-control"/>
                </td>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;投保内容：</td>
                <td >
                    <input id="bxCaseTbContext" name="bxCaseTbContext" value="" type="text" style="width: 172px;"
                           class="validate[required,noSpecialCaracters,maxSize[500]] text-input self-form-control"/>
                </td>
            </tr>
            <tr>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;理赔内容：</td>
                <td >
                    <input id="bxCaseLpContext" name="bxCaseLpContext" value="" type="text" style="width: 172px;"
                           class="validate[required,noSpecialCaracters,maxSize[500]] text-input self-form-control"/>
                </td>
                <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>&nbsp;出险内容：</td>
                <td >
                    <input id="bxCaseCxContext" name="bxCaseCxContext" value="" type="text" style="width: 172px;"
                           class="validate[required,noSpecialCaracters,maxSize[500]] text-input self-form-control"/>
                </td>
            </tr>
            <c:if test="${staff.roleId eq '1' }">
                <tr>
                    <td style="background:#A0E0F7; padding: 10px 35px;"><font color="red">*</font>
                        <input type="hidden" name="memberIdFlag" id="memberIdFlag" value="1">
                        &nbsp;所属人：
                    </td>
                    <td>
                        <select class="select-nosearch" id="memberId" name="memberId" style="width: 90%;height: 28px;">
                            <option value="0" selected="selected">---请选择---</option>
                            <c:forEach items="${userInfoList}" var="userInfo" varStatus="status">
                                <option value='${userInfo.id}'>
                                        ${userInfo.userName}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:if>
        </table>
        <div class="sub_div">
            <input type="submit" class="sub_btn" value=" "/>
            <input type="button" class="reset_btn" onclick="perReset();" value=" " />
        </div>
    </div>
</form>
</body>
</html>
