<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="${jsRoot}/jquery-1.11.1.min.js"></script>
		<!-- 引入 Bootstrap -->
		<link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
		<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
		<!-- 引入 select2 -->
		<link href="${toolRoot}/select/select2.min.css" rel="stylesheet">
		<script src="${toolRoot}/select/select2.full.min.js"></script>
		<script src="${toolRoot}/select/select2-self.js"></script>
		<script type="text/javascript">
			$(function(){
				$("div.customerSearchBar ul > li").click(function (e) {
					$("div.customerSearchBar ul > li").removeClass("active");
					$(this).addClass("active");
				});
			});
			function functionChange (url) {
				$("#detailFrame").attr("src", url);
			}
			$(function(){
				var height = window.parent.parent.parent.document.getElementById("indexFrame").contentWindow.document.body.clientHeight;
				$("#detailFrame").css("height", height*0.75);
			})
		</script>
	</head>
	<body style="margin-left: 20px; font-size: 13px;">
		<div align="center">
			<nav class="" role="navigation">
				<div class="container-fluid">
					<%-- <div class="navbar-header">
						<a class="navbar-brand" href="#">用户管理</a>
					</div> --%>
					<div class="customerSearchBar">
						<ul class="nav navbar-nav nav-pills">
							<li class="active" onclick="functionChange('/user/list')"><a href="javascript:void(0);">&nbsp;&nbsp;&nbsp;&nbsp;用户列表&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
							<li onclick="functionChange('/user/goAdd')"><a href="javascript:void(0);">&nbsp;&nbsp;&nbsp;&nbsp;新增用户&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<iframe id="detailFrame" name="detailFrame" scrolling="yes" src="/user/list" frameborder="0" style="height: 600px;width: 100%;"></iframe>
	</body>
</html>
