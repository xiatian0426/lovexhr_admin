<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Price Forcast Classify</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${jsRoot}/jquery-1.8.3.min.js"></script>
	<script src="${jsRoot}/sub/subClassify.js"></script>
</head>

<body>
	<%-- 加载栏目结构  开始--%>
	<div style="margin-left: 15%; margin-top: 5%">
		<br>
		<%-- 此处选择完后， 会触发加载二级菜单 --%>
		<select name="columnId" id="columnId">
			<option value="">---请选择---</option>
			<c:forEach items="${indexColumns}" var="indexColumn">
				<option value="${indexColumn.id }">${indexColumn.name}</option>
			</c:forEach>
		</select>
		<%-- 此处选择完后， 会触发一个函数,显示图片 --%>
		<select name="classifyId">
			<option value="">---请选择---</option>
		</select>
	</div>
	<%-- 加载栏目结构  结束--%>
	<%-- 显示曲线图 --%>
	<iframe src="" id="chart" width="1000px" height="400px" scrolling="no" frameborder="x" style="margin-left: 15%;"></iframe>
	<%-- 显示曲线图 --%>
</body>
</html>
