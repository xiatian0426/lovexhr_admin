<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib  prefix="acc" uri="/acc-tags"%>
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
	<style>
	html{height:100%;}
	*{margin:0; padding:0;}
		.nav-stacked li.active{background:#1F2A3E;}
		.nav-pills>li.active>a, .nav-pills>li.active>a:focus{ color:#1DB9D0; background-color:#1F2A3E;}
		.nav-pills>li a{color:#fff; font-weight:bold;}
		.nav>li>a:hover{background-color:#337ab7;}
	</style>
	<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("ul.nav > li").click(function (e) {
				$('ul.nav > li').removeClass('active');
				$(this).addClass('active');
			});
		});
	</script>
</head>
<body style=" font-size: 13px; background:#2A3852; position:relative; height:100%;">
	<ul class="nav nav-pills nav-stacked">
		<acc:oa opId="0,1">
			<li class="active"><a href="/product/getProductByMemId" target="main"><img src="../../../res/images/main/field.png" height="25px;" width="25px;">&nbsp;产品数据列表</a></li>
            <li><a href="/product/goAddProductByMemId" target="main"><img src="../../../res/images/main/edit.png" height="25px;" width="25px;">&nbsp;添加产品数据</a></li>
        </acc:oa>
        <acc:oa opId="0,1">
            <li><a href="/honor/getHonorList" target="main"><img src="../../../res/images/main/ship.png" height="25px;" width="25px;">&nbsp;荣誉管理</a></li>
        </acc:oa>
        <acc:oa opId="0,1">
            <li><a href="/QA/getQAList" target="main"><img src="../../../res/images/main/form.png" height="25px;" width="25px;">&nbsp;QA管理</a></li>
        </acc:oa>
        <acc:oa opId="0,1">
            <li><a href="/recruit/getRecruitList" target="main"><img src="../../../res/images/main/channel.png" height="25px;" width="25px;">&nbsp;招聘管理</a></li>
        </acc:oa>
        <acc:oa opId="0">
            <li><a href="/user/goEdit?customerFlag=1" target="main"><img src="../../../res/images/main/customer.png" height="25px;" width="25px;">&nbsp;个人信息管理</a></li>
        </acc:oa>
        <acc:oa opId="0,1">
            <li><a href="/proVideo/getProVideoList" target="main"><img src="../../../res/images/main/channel.png" height="25px;" width="25px;">&nbsp;用户视频管理</a></li>
        </acc:oa>
        <acc:oa opId="0,1">
        <li><a href="/thumbUp/getThumbUpList" target="main"><img src="../../../res/images/main/channel.png" height="25px;" width="25px;">&nbsp;点赞管理</a></li>
        </acc:oa>
        <%--<acc:oa opId="0,1">--%>
        <%--<li><a href="/product/getProductVideoList" target="main"><img src="../../../res/images/main/channel.png" height="25px;" width="25px;">&nbsp;问题咨询管理</a></li>--%>
        <%--</acc:oa>--%>
        <%--<acc:oa opId="0,1">--%>
        <%--<li><a href="/product/getProductVideoList" target="main"><img src="../../../res/images/main/channel.png" height="25px;" width="25px;">&nbsp;评论管理</a></li>--%>
        <%--</acc:oa>--%>
        <%--<acc:oa opId="0,1">--%>
        <%--<li><a href="/product/getProductVideoList" target="main"><img src="../../../res/images/main/channel.png" height="25px;" width="25px;">&nbsp;企业风采管理</a></li>--%>
        <%--</acc:oa>--%>
		<%--<acc:oa opId="1">--%>
			<%--<li><a href="/manage/roleList" target="main"><img src="../../../res/images/main/form.png" height="25px;" width="25px;">&nbsp;用户角色查询</a></li>--%>
		<%--</acc:oa>--%>
        <acc:oa opId="1">
            <li><a href="/user/index" target="main"><img src="../../../res/images/main/user.png" height="25px;" width="25px;">&nbsp;后台用户管理</a></li>
        </acc:oa>
	</ul>
</body>
</html>
