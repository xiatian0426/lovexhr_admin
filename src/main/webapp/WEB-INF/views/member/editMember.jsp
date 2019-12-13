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
	
	<script src="${jsRoot}/member/editMember.js"></script>
	<script type="text/javascript">
		$(function(){
			//开启表单验证
			$("#editMemberFrom").validationEngine();
			var notice = $("#notice").val();
			if(notice == 1){
				alert("操作成功");
				window.open("/member/index","main");
				window.close();
			}else if(notice == -1){
				alert("操作失败");
				window.close();
			}
			var oldUserid = $("#oldUserid").val();
			//新增客户验证用户名
			if(oldUserid==''){
				//为用户名添加光标失去事件
				$("#userid").blur(function(){
					//用户名
					var userid = $("#userid").val();
					if(userid == ''){
						$("#userid").validationEngine("showPrompt","用户名不能是空","error");
					}else if (validateUserId (userid)) {
						$("#userid").validationEngine("showPrompt","用户名已存在","error");
					}
				});
			}
		});
		function perReset(){
			var oldUserid = $("#oldUserid").val();
			//新增客户验证用户名
			if(oldUserid==''){
				$("#userid").val("");
			}
			$("#pwd1").val("");
			$("#pwd2").val("");
			$("#phone").val("");
			$("#tel").val("");
			$("#linkman").val("");
			$("#sex").val("");
			$("#company").val("");
			$("#authid").val("0");
		}
	</script>
</head>
<body style=" font-size: 13px;">
	<form action="/member/editMember" name="memberForm" method="post" target="_self" id="editMemberFrom" onsubmit="return save();">
		<input name="oldUserid" id="oldUserid" type="hidden" value="${member.userid }"/>
		<input id="notice" value="${notice}" type="hidden"/>
		<div class="clearB"></div>
		<div class="r_box" style="padding: 0; width: 500px;">
			<div style="line-height:38px; font-weight: bold;font-size: 20px;" align="center">
				<c:if test="${member==null }">客户信息添加</c:if>
				<c:if test="${member!=null  }">客户信息修改</c:if>
			</div>
			<table  style=" font-size: 13px; " align=center>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">用户名：</td>
					<c:if test="${member==null }">
						<td>
							<input id="userid" name="userid" value="" type="text" style="width: 172px;" 
							class="validate[required,noSpecialCaracters,maxSize[11]] text-input self-form-control"/>
							<font color="red">*</font>
						</td>
					</c:if>
					<c:if test="${member!=null  }">
						<td style="padding: 10px 15px;">${member.userid }</td>
					</c:if>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">登录密码：</td>
					<td>
						<input id="pwd1" name="pwd" type="password" style="width: 172px;" class="validate[maxSize[12]] text-input self-form-control" />
						<c:if test="${member==null }">
							<font color="red">(密码默认6个8)</font>
						</c:if>
						<c:if test="${member!=null }">
							<font color="red">(不填默认不修改)</font>
						</c:if>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">确认密码：</td>
					<td>
						<input id="pwd2" type="password" style="width: 172px;" class="validate[maxSize[12]] text-input self-form-control" />
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">状态：</td>
					<td>
						<select class="select-nosearch" name='status' id="status" style="width: 172px;">
							<option value='0' <c:if test="${member.status eq '0' }">selected = selected</c:if>>正常</option>
							<option value='1' <c:if test="${member.status eq '1' }">selected = selected</c:if>>删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">手机：</td>
					<td>
						<input id="phone" name="phone" value="${member.phone}" type="text" style="width: 172px;" 
							class="validate[required,noSpecialCaracters,maxSize[11]] text-input self-form-control"/>
							<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">座机：</td>
					<td>
						<input id="tel" name="tel" value="${member.tel}" type="text" style="width: 172px;" 
							class="validate[required,noSpecialCaracters,maxSize[12]] text-input self-form-control"/>
							<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">联系人：</td>
					<td>
						<input id="linkman" name="linkman" value="${member.linkman}" type="text" style="width: 172px;" 
							class="validate[required,noSpecialCaracters,maxSize[12]] text-input self-form-control"/>
							<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">性别：</td>
					<td>
						<select class="select-nosearch" name='sex' id="sex" style="width: 172px;">
							<option value='0' <c:if test="${member.sex eq '0' }">selected = selected</c:if>>男</option>
							<option value='1' <c:if test="${member.sex eq '1' }">selected = selected</c:if>>女</option>
						</select>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">公司全称：</td>
					<td>
						<input id="company" name="company" value="${member.company}" type="text" style="width: 172px;" 
							class="validate[required,noSpecialCaracters,,maxSize[50]] text-input self-form-control"/>
							<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">会员类型</td>
					<td>
						<select class="select-nosearch" name='authid' id="authid" style="width: 172px;">
							<option value='0'>请选择</option>
							<c:forEach items="${memberAuthList}" var="memberAuth" varStatus="status">
								<option value='${memberAuth.id}' <c:if test="${member.authid eq memberAuth.id }">selected = selected</c:if> >
									${memberAuth.membertype} 
								</option>
							</c:forEach>
						</select>
						<font color="red">*</font>
					</td>
				</tr>
			</table>
			<div class="sub_div">
				<input type="submit" class="sub_btn" value=" "/>
				<input type="button" class="reset_btn" onclick="perReset();" value=" " />
			</div>
		</div>
	</form>
</body>
</html>
