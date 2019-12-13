<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Price Forcast Classify</title>
	<%@ include file="../common/js_contants.jsp" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${jsRoot}/jquery-1.8.3.min.js"></script>
	<script src="${jsRoot}/chart/chartPage.js"></script>
	<%-- 图表所需的JS 开始 --%>
	<script src="${toolRoot}/chart/echarts-all.js"></script>
	<script src="${toolRoot}/chart/CalculateMaxMin.js"></script>
	<script src="${toolRoot}/chart/curveNumber.js"></script>
	<%-- 图表所需的JS 结束 --%>
	<%-- 初始化图表 开始 --%>
	<script type="text/javascript">
		$(function(){
			curveNumber("chartDiv", "${indexClassify.name}价格预测", ${chartData},"heh");
		});
	</script>
	<%-- 初始化图表 结束 --%>
</head>

<body>
	<input type="button" value="刷新" onclick="refresh()"/>
	<br />
	<%-- 图表 容器--%>
	<div>
		<div id="chartDiv" style="width:724px; height:367px;"></div>
	</div>
	<%-- 图表 容器--%>
</body>
</html>
