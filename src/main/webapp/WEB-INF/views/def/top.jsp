<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="${jsRoot}/jquery-1.8.3.min.js"></script>
<style>
	.topTd{height:85px;}
	@media screen and (max-width:1360px){
		.topTd{height:68px;}
	}
</style>
<script language=JavaScript>
	function logout() {
		if (confirm("您确定要退出控制面板吗？"))
			top.location = "/account/logout";
		return false;
	}
	function showtime() {
		var today, hour, second, minute, year, month, date;
		var strDate;
		today = new Date();
		var n_day = today.getDay();
		switch (n_day) {
		case 0: {
			strDate = "星期日"
		}
			break;
		case 1: {
			strDate = "星期一"
		}
			break;
		case 2: {
			strDate = "星期二"
		}
			break;
		case 3: {
			strDate = "星期三"
		}
			break;
		case 4: {
			strDate = "星期四"
		}
			break;
		case 5: {
			strDate = "星期五"
		}
			break;
		case 6: {
			strDate = "星期六"
		}
			break;
		case 7: {
			strDate = "星期日"
		}
			break;
		}
		year = today.getFullYear();
		month = today.getMonth() + 1;
		date = today.getDate();
		hour = today.getHours();
		minute = today.getMinutes();
		second = today.getSeconds();
		if (second < 10) {
			second = "0" + second;
		}
		if (minute < 10) {
			minute = "0" + minute;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		if (date < 10) {
			date = "0" + date;
		}
		if (month < 10) {
			month = "0" + month;
		}
		/* document.getElementById('time').innerHTML = year + "年" + month + "月"
				+ date + "日" + strDate + " " + hour + ":" + minute + ":"
				+ second; //显示时间
		setTimeout("showtime();", 1000); //设定函数自动执行时间为 1000 ms(1 s) */
	}
	$(function() {
		showtime();
	})
</script>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%"  style="background:url(../../../res/images/top.jpg); text-align:right; background-size:cover;" class="topTd">
				<span>你好,${user.userRealname}!</span><img src="../../../res/images/main/set.png" style="vertical-align:middle; width:20px;margin-left:20px; height:20px; margin-right:10px;"><a href="/account/logout" target="indexFrame" style="text-decoration:none; margin-right:60px; ">注销</a> 
			</td>
		</tr>
	</table>
</body>
</html>
