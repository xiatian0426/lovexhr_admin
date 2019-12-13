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
		<script src="${jsRoot}/userinfo/editUser.js"></script>
		<script type="text/javascript">
			$(function(){
				$("div.customerSearchBar ul > li").click(function (e) {
					$("div.customerSearchBar ul > li").removeClass("active");
					$(this).addClass("active");
				});
				//函数来源page.js
				page("userListForm", ${page.pageInfo}, "pageUserList");
			});
		</script>
	</head>
<body style="width: 95%;  font-size: 13px;">
	<form class="form-horizontal" id="userListForm" action="/user/list" method="POST">
		<div class="r_box" style="margin-top: 10px;">
			<div style="height: 15px; width: 100%;"><span></span></div>
			<span class="infoLable">登录名称：</span>
				<input name="userName" type="text" class="self-form-control" style="width: 100px;" value="${query.userName }"/>
			<span class="infoLable">真实姓名：</span>
				<input name="userRealname" type="text" class="self-form-control" style="width: 100px;" value="${query.userRealname }"/>
			<span class="infoLable">用户角色：</span>
				<select class="select-nosearch" name="roleId" style="width: 120px;height: 28px;">
					<option value="" selected="selected">---请选择---</option>
					<option value="1" <c:if test="${query.roleId=='1' }">selected="selected"</c:if>>管理员</option>
					<option value="2" <c:if test="${query.roleId=='2' }">selected="selected"</c:if>>普通用户</option>
				</select>
			<span class="infoLable">所属部门：</span>
				<select class="select-nosearch" name="departclass" style="width: 200px;height: 28px;">
					<option value="" selected="selected">---请选择---</option>
					<c:forEach items="${departList}" var="depart" varStatus="status">
						<option value='${depart.depId}' <c:if test="${query.departclass==depart.depId }">selected="selected"</c:if>>
							${depart.itemname} 
						</option>
					</c:forEach>
				</select>
			<button type="submit" class="btn btn-default"
					style="background-color:#337ab7;">搜索</button>
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
						登录名称
					</th>
					<th width="14%" align="center">
						真实姓名
					</th>
					<th width="14%" align="center">
						角色
					</th>
					<th width="14%" align="center">
						部门
					</th>
					<th width="14%" align="center">
						创建人
					</th>
					<th width="14%" align="center">
						创建时间
					</th>
					<th width="25%" align="center">
						操作
					</th>
				</tr>
				<c:forEach items="${page.result}" var="user" varStatus="count">
					<tr>
						<td align="center" height="33" align="center">
							${count.count}
						</td>
						<td align="center">
							${user.userName}
						</td>
						<td align="center">
							${user.userRealname}
						</td>
						<td align="center">
							<c:forEach items="${roleList}" var="role" varStatus="status">
								<c:if test="${user.roleId eq role.id}">${role.roleName}</c:if>
							</c:forEach>
						</td>
						<td align="center">
							<c:forEach items="${deList}" var="de" varStatus="status">
								<c:if test="${de.depId eq user.departClass}">${de.itemname }</c:if>
							</c:forEach>
						</td>
						<td align="center">
							${userInfoDictMap[user.createrId].userRealname }
						</td>
						<td align="center">
							${user.createDateString}
						</td>
						<td align="center">
							<div class="btn-group">
								<button type="button" class="btn btn-success" onclick="goEditUserInfo('${user.id}');" target="_blank">编辑</button>
								<c:choose>
									<c:when test="${user.status eq '1'}">
										<button type="button" class="btn btn-danger" onclick="userUseOrNot('${user.id}')" id="useOrNot_${user.id}">禁用</button>
									</c:when>
									<c:otherwise>
										<button type="button" class="btn btn-warning" onclick="userUseOrNot('${user.id}')" id="useOrNot_${user.id}">启用</button>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8" height="40" bgcolor="#D9F3FD" align="left" class="pageUserList">
						<%-- 共${page.recordCount}条|当前${page.currentPage}/${page.pageCount}页 --%>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
