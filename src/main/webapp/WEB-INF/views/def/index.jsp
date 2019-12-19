<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>迪迦软件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="${jsRoot}/jquery-1.11.1.min.js"></script>
</head>
	<frameset rows="14%,125%" frameborder="no" border="0" framespacing="0">
		<frame src="/def/top" noresize="noresize"  name="topFrame" marginwidth="0" marginheight="0" scrolling="no">
		<frameset rows="*" cols="11%,89%" id="frame">
			<frame src="/def/left" name="leftFrame" noresize="noresize" id="iframe1" height="100%" width="25%" frameborder="0" scrolling="no">
			<frame src="/product/getProductByMemId" name="main" id="main" marginwidth="0" marginheight="0" width="70%" frameborder="0" scrolling="yes">
		</frameset>
		<noframes>
			<body></body>
		</noframes>
	</frameset>
</html>
