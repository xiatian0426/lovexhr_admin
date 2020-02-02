<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改用户</title>
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
	<script type="text/javascript">
		$(function(){
			//开启表单验证
			$("#editUserFrom").validationEngine();
			var notice = $("#notice").val();
			if(notice == 1){
				alert("操作成功");
				window.open("/user/index","main");
				window.close();
			}else if(notice == -1){
				alert("操作失败");
				window.close();
			}if(notice == 2){
                alert("操作成功");
            }
		})
		function save(){
            var years = $("#years").val();
            var re = new RegExp("^[0-9]*[1-9][0-9]*$");
            if (years != "") {
                if (!re.test(years)) {
                    $("#years").validationEngine("showPrompt","从业年限只能为整数!","error");
                    $("#years").focus();
                    return false;
                }
            }else{
                $("#years").validationEngine("showPrompt","从业年限不能为空!","error");
                $("#years").focus();
                return false;
            }
			var roleId = $("#roleId").val();
			if(roleId==''){
				$("#roleId").validationEngine("showPrompt","请选择所属角色","error");
				$(this).focus();
				return false;
			}
            var longitude = $("#longitude").val();
            var re1 = new RegExp("^\\d+[\\.]?\\d{0,2}$");
            if (longitude != "") {
                if (!re1.test(longitude)) {
                    $("#longitude").validationEngine("showPrompt","经度格式不正确!","error");
                    $("#longitude").focus();
                    return false;
                }
            }else{
                $("#longitude").validationEngine("showPrompt","经度不能为空!","error");
                $("#longitude").focus();
                return false;
            }
            var latitude = $("#latitude").val();
            if (latitude != "") {
                if (!re1.test(latitude)) {
                    $("#latitude").validationEngine("showPrompt","纬度格式不正确!","error");
                    $("#latitude").focus();
                    return false;
                }
            }else{
                $("#latitude").validationEngine("showPrompt","纬度不能为空!","error");
                $("#latitude").focus();
                return false;
            }
		}
	</script>
</head>
<body style=" font-size: 13px;">
	<form action="/user/editUser" name="userForm" method="post" target="_self" id="editUserFrom" onsubmit="return save();" enctype="multipart/form-data">
        <input type="hidden" name="customerFlag" value="${customerFlag}"/>
        <input type="hidden" name="memberImg" value="${userInfo.memberImg}"/>
		<div class="clearB"></div>
		<div class="r_box" style="padding: 0; width: 777px;">
			<div class="adress" >
				当前位置：用户信息修改
			</div>
			<input name="id" type="hidden" value="${userInfo.id }"/>
			<input id="notice" value="${notice}" type="hidden"/>
			<table  style=" font-size: 13px; " align=center>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">登录名称：</td>
					<td>
						<input id="userName" name="userName" readonly="readonly" value="${userInfo.userName}" type="text" style="width: 172px;" class="validate[required,noSpecialCaracters,maxSize[20]] text-input self-form-control"/>
					</td>
					<td style="background:#A0E0F7;padding: 10px 15px;">登录密码：</td>
					<td>
						<input id="userPassword" name="userPassword" type="password"  style="width: 172px;" class="validate[maxSize[20]] text-input self-form-control" />
					</td>
				</tr>
				<tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">昵称：</td>
                    <td>
                        <input id="name" name="name" value="${userInfo.name}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
					<td style="background:#A0E0F7;padding: 10px 15px;">真实姓名：</td>
					<td>
						<input id="userRealname" name="userRealname" value="${userInfo.userRealname}" type="text" style="width: 172px;" 
							class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
					</td>
				</tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">职务：</td>
                    <td>
                        <input id="post_name" name="post_name" value="${userInfo.post_name}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">手机：</td>
                    <td>
                        <input id="phone" name="phone" value="${userInfo.phone}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[11]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">公司名称：</td>
                    <td>
                        <input id="company_name" name="company_name" value="${userInfo.company_name}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[50]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">公司地址：</td>
                    <td>
                        <input id="company_addr" name="company_addr" value="${userInfo.company_addr}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[100]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">经度：</td>
                    <td>
                        <input id="longitude" name="longitude" value="${userInfo.longitude}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[10]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">纬度：</td>
                    <td>
                        <input id="latitude" name="latitude" value="${userInfo.latitude}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,maxSize[10]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">从业年限：</td>
                    <td>
                        <input id="years" name="years" value="${userInfo.years}" type="text" style="width: 172px;"
                               class="text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">个性签名：</td>
                    <td>
                        <input id="signature" name="signature" value="${userInfo.signature}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[50]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">微信号：</td>
                    <td>
                        <input id="wechat" name="wechat" value="${userInfo.wechat}" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[30]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">头像：</td>
                    <td>
                        <input type="file" name="file"/>
                        <img src="${userInfo.memberImg}" width="50" height="50"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">页面风格：</td>
                    <td>
                        <input name="page_style" value="0" type="radio" <c:if test="${userInfo.page_style==0}">checked</c:if>/> 风格1&nbsp;&nbsp;
                        <input name="page_style" value="1" type="radio" <c:if test="${userInfo.page_style==1}">checked</c:if>/> 风格2
                    </td>
                    <c:if test="${staff.roleId eq '1' }">
                    <td style="background:#A0E0F7;padding: 10px 15px;">角色：</td>
                    <td>
                        <select class="select-nosearch" name='roleId' id="roleId" style="width: 172px;">
                            <option value='0' <c:if test="${userInfo.roleId eq '0' }">selected = selected</c:if>>
                                客户
                            </option>
                            <option value='1' <c:if test="${userInfo.roleId eq '1' }">selected = selected</c:if>>
                                管理员
                            </option>
                        </select>
                    </td>
                    </c:if>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">自我介绍：</td>
                    <td colspan="3">
                        <input id="introduce" name="introduce" value="${userInfo.introduce}" type="text" style="width: 435px;"
                               class="validate[required,noSpecialCaracters,,maxSize[100]] text-input self-form-control"/>
                    </td>
                </tr>
			</table>
			<div class="sub_div">
				<input type="submit" class="sub_btn" value=" "/>
				<input type="reset" class="reset_btn" value=" " />
			</div>
		</div>
	</form>
</body>
</html>
