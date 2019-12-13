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
			$("#proprietorForm").validationEngine();
		})
	</script>
</head>
<body style=" font-size: 13px;">
	<form action="/proprietor/add" name="proprietorForm" method="post" target="main" id="proprietorForm">
		<div class="clearB"></div>
		<div class="r_box" style="padding: 0; width: 777px;">
			<div class="adress" >
				当前位置：业主添加
			</div>
			<table  style=" font-size: 13px; " align=center>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">公司名称：</td>
					<td>
						<span>
							<input id="company" name="company" type="text" style="width: 172px;" class="validate[required] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
					<td style="background:#A0E0F7;padding: 10px 15px;">联系人：</td>
					<td>
						<span>
							<input id="linkman" name="linkman" type="text" style="width: 172px;" class="validate[required] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">联系电话：</td>
					<td>
						<span>
							<input id="tel" name="tel" type="text" style="width: 172px;" class="validate[required,noSpecialCaracters] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
					<td style="background:#A0E0F7;padding: 10px 15px;">&nbsp;&nbsp;&nbsp;&nbsp;地址：</td>
					<td>
						<span>
							<input id="address" name="address" type="text" style="width: 172px;" class="validate[required] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">类别：</td>
					<td colspan="3">
						<select name="type" class="select-nosearch validate[required,noSpecialCaracters,,maxSize[12]]" style="width: 172px;">
							<option value='1'>业主库</option>
							<option value='2'>设计院库</option>
							<option value='3'>代理机构库</option>
						</select>
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
