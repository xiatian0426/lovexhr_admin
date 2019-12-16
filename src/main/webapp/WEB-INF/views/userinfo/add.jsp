<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>后台用户添加</title>
	<%@ include file="../common/jsp_contants.jsp"%>
	<%@ include file="../common/js_contants.jsp"%>
	<link rel="stylesheet" type="text/css" href="${cssRoot }/right_style.css" />
	<link rel="stylesheet" type="text/css" href="${cssRoot }/zTreeStyle.css" />
	<script type="text/javascript" src="${jsRoot }/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${jsRoot }/move_sel_tree.js"></script>
	<!-- 引入 Bootstrap -->
	<link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
	<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
	<!-- 日历控件 -->
	<script type="text/javascript" src="${toolRoot }/My97DatePicker/WdatePicker.js"></script>
	<!-- ZTree控件 -->
	<script type="text/javascript" src="${jsRoot }/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${jsRoot }/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript" src="${jsRoot }/dtree.js"></script>
	<!-- 验证 -->
	<link rel="stylesheet" href="${toolRoot }/validata/validationEngine.css" />
	<script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine-zh_CN.js" charset="utf-8"></script>
	<!-- 引入 Bootstrap -->
	<link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
	<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
	<script teye="text/javascript">
        $(function(){
            //开启表单验证
            $("#userForm").validationEngine();
            //为登录名称添加光标失去事件
            $("#userName").blur(function(){
                //用户名
                var userName = $("#userName").val();
                if(userName == ''){
                    $("#userName").validationEngine("showPrompt","登录名称不能是空","error");
                }else if (validateUserName (userName)) {
                    $("#userName").validationEngine("showPrompt","登录名称已存在","error");
                }
            });
        })
		function save(){
            //用户名
            var userName = $("#userName").val();
            if(userName == ''){
                $("#userName").validationEngine("showPrompt","登录名称不能是空","error");
                return false;
            }else if (validateUserName (userName)) {
                $("#userName").validationEngine("showPrompt","登录名称已存在","error");
                return false;
            }
			var roleId = $("#roleId").val();
			if(roleId=='0'){
				$("#roleId").validationEngine("showPrompt","请选择所属角色","error");
				$(this).focus();
				return false;
			}
		}
	</script>
</head>
<body style=" font-size: 13px;">
	<form action="/user/add" name="userForm" method="post" target="main" id="userForm" onsubmit="return save();" enctype="multipart/form-data">
		<div class="clearB"></div>
		<div class="r_box" style="padding: 0; width: 777px;">
			<div class="adress" >
				当前位置：后台用户添加
			</div>
            <table  style=" font-size: 13px; " align=center>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">登录名称：</td>
                    <td>
                        <input id="userName" name="userName" value="" type="text" style="width: 172px;" class="validate[required,noSpecialCaracters] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">登录密码：</td>
                    <td>
                        <input id="userPassword" name="userPassword" type="password" value="" style="width: 172px;" class="validate[maxSize[12]] text-input self-form-control" />
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">昵称：</td>
                    <td>
                        <input id="name" name="name" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">真实姓名：</td>
                    <td>
                        <input id="userRealname" name="userRealname" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">公司：</td>
                    <td>
                        <input id="company_name" name="company_name" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">职务：</td>
                    <td>
                        <input id="post_name" name="post_name" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                </tr>

                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">从业年限：</td>
                    <td>
                        <input id="years" name="years" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">个性签名：</td>
                    <td>
                        <input id="signature" name="signature" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">自我介绍：</td>
                    <td>
                        <input id="introduce" name="introduce" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">头像：</td>
                    <td>
                        <input type="file" id="memberImg" name="file">
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">微信号：</td>
                    <td>
                        <input id="wechat" name="wechat" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;">手机：</td>
                    <td>
                        <input id="phone" name="phone" value="" type="text" style="width: 172px;"
                               class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
                    </td>
                </tr>
                <tr>
                    <td style="background:#A0E0F7;padding: 10px 15px;">角色：</td>
                    <td>
                        <select class="select-nosearch" name='roleId' id="roleId" style="width: 172px;">
                            <option value='2'>
                                客户
                            </option>
                            <option value='1'>
                                管理员
                            </option>
                        </select>
                    </td>
                    <td style="background:#A0E0F7;padding: 10px 15px;"></td>
                    <td>

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
