<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改数据</title>
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
			$("#editFrontDataForm").validationEngine();
			var notice = $("#notice").val();
			if(notice == 1){
				alert("操作成功");
				window.open("/frontData/index","main");
				window.close();
			}else if(notice == -1){
				alert("操作失败");
				window.close();
			}
		})
		function resett(){
			$("#name").val("");
			$("#url").val("");
		}
	</script>
</head>
<body style=" font-size: 13px;">
	<form action="/frontData/editFrontData" target="_self" method="post" target="main" id="editFrontDataForm">
		<input type="hidden" name="id" value="${frontData.id }">
		<input id="notice" value="${notice}" type="hidden"/>
		<div class="clearB"></div>
		<div class="r_box" style="padding: 0; width: 777px;">
			<div class="adress" >
				当前位置：数据修改
			</div>
			<table  style=" font-size: 13px; " align=center>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">名称：</td>
					<td colspan="3">
						<span>
							<input id="name" name="name" type="text" value="${frontData.name }" style="width: 300px;" class="validate[required] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">url：</td>
					<td colspan="3">
						<span>
							<input id="url" name="url" type="text" value="${frontData.url }" style="width: 300px;" class="validate[required,noSpecialCaracters] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">类别：</td>
					<td colspan="3">
						<select name="type" class="select-nosearch validate[required,noSpecialCaracters,,maxSize[12]]" style="width: 300px;">
							<option value='1' <c:if test="${frontData.type=='1' }">selected="selected"</c:if>>行业动态</option>
							<option value='2' <c:if test="${frontData.type=='2' }">selected="selected"</c:if>>展会信息</option>
							<option value='3' <c:if test="${frontData.type=='3' }">selected="selected"</c:if>>优秀供应商</option>
							<option value='4' <c:if test="${frontData.type=='4' }">selected="selected"</c:if>>会员中标榜</option>
						</select>
					</td>
				</tr>
			</table>
			<div class="sub_div">
				<input type="submit" class="sub_btn" value=" "/>
				<input type="button" class="reset_btn" onclick="resett();" value=" " />
			</div>
		</div>
	</form>
</body>
</html>
