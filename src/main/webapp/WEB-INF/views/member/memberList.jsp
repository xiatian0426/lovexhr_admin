<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script src="${jsRoot}/jquery-1.11.1.min.js"></script>
		<link href="${cssRoot}/right_style.css" rel="stylesheet">
		<!-- 引入 Bootstrap -->
		<link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
		<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
		<!-- 引入 select2 -->
		<link href="${toolRoot}/select/select2.min.css" rel="stylesheet">
		<script src="${toolRoot}/select/select2.full.min.js"></script>
		<script src="${toolRoot}/select/select2-self.js"></script>
		<!-- 引入分页 -->
		<script src="${jsRoot}/page.js"></script>
		<script src="${jsRoot}/member/editMember.js"></script>
		<script type="text/javascript">
			$(function(){
				$("div.customerSearchBar ul > li").click(function (e) {
					$("div.customerSearchBar ul > li").removeClass("active");
					$(this).addClass("active");
				});
				//函数来源page.js
				page("memberListForm", ${page.pageInfo}, "pageMemberList");
			});
		</script>
	</head>
<body style="width: 95%;  font-size: 13px;">
	<form class="form-horizontal" id="memberListForm" action="/member/memberList" method="POST">
		<div class="r_box" style="margin-top: 10px;">
			<div style="height: 15px; width: 100%;"><span></span></div>
			<span class="infoLable">用户名：</span>
				<input name="userid" type="text" value="${query.userid }" class="self-form-control" style="width: 100px;"/>
			<span class="infoLable">手机/座机：</span>
				<input name="telPhone" type="text" value="${query.telPhone }" class="self-form-control" style="width: 100px;"/>
			<span class="infoLable">联系人：</span>
				<input name="linkman" type="text" value="${query.linkman }" class="self-form-control" style="width: 100px;"/>
			<span class="infoLable">公司全称：</span>
				<input name="company" type="text" value="${query.company }" class="self-form-control" style="width: 100px;"/>
			<span class="infoLable">会员类型：</span>
				<select class="select-nosearch" name="authid" style="width: 120px;height: 28px;">
					<option value='0'>请选择</option>
					<c:forEach items="${memberAuthList}" var="memberAuth" varStatus="status">
						<option value='${memberAuth.id}' <c:if test="${query.authid==memberAuth.id}">selected</c:if>>${memberAuth.membertype}</option>
					</c:forEach>
				</select>
			<button type="submit" class="btn btn-default" style="background-color:#337ab7;">搜索</button>
			<br />
			<div><span></span></div>
		</div>
		<div class="r_box" >
			<table width="100%" cellpadding="0" cellspacing="0" class="table-bordered">
				<tr>
					<th width="5%" align="center" height="38" align="center">
						序号
					</th>
					<th width="15%" align="center">
						用户名
					</th>
					<th width="10%" align="center">
						手机
					</th>
					<th width="10%" align="center">
						座机
					</th>
					<th width="10%" align="center">
						联系人
					</th>
					<th width="6%" align="center">
						性别
					</th>
					<th width="20%" align="center">
						公司全称
					</th>
					<th width="8%" align="center">
						会员类型
					</th>
					<th width="8%" align="center">
						创建时间
					</th>
					<th width="8%" align="center">
						状态
					</th>
					<th width="8%" align="center">
						操作
					</th>
				</tr>
				<c:forEach items="${page.result}" var="member" varStatus="count">
					<tr>
						<td align="center" height="33" align="center">
							${count.count}
						</td>
						<td align="center">
							${member.userid}
						</td>
						<td align="center">
							${member.phone}
						</td>
						<td align="center">
							${member.tel}
						</td>
						<td align="center">
							${member.linkman}
						</td>
						<td align="center">
							<c:if test="${member.sex eq '0'}">男</c:if>
							<c:if test="${member.sex eq '1'}">女</c:if>
						</td>
						<td align="center">
							${member.company}
						</td>
						<td align="center">
							${grhxMemberAuthDictMap[member.authid].membertype }
						</td>
						<td align="center">
							${member.createtimeString}
						</td>
						<td align="center">
							<c:if test="${member.status eq '0'}">正常</c:if>
							<c:if test="${member.status eq '1'}">删除</c:if>
						</td>
						<td align="center">
							<button type="button" class="btn btn-success" onclick="goEditMember('${member.userid}');" target="_blank">编辑</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="11" height="30" bgcolor="#D9F3FD" align="center">
						<button type="button" class="btn btn-success" onclick="goEditMember('');" target="_blank">添加新客户</button>
					</td>
				</tr>
				<tr>
					<td colspan="11" height="30" bgcolor="#D9F3FD" align="left" class="pageMemberList">
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
