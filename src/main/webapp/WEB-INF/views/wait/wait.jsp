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
		<script src="${jsRoot}/customerImport/invalidateFileType.js"></script>
		
		<!-- 引入 Bootstrap -->
		<link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
		<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
		<!-- 引入 select2 -->
		<link href="${toolRoot}/select/select2.min.css" rel="stylesheet">
		<script src="${toolRoot}/select/select2.full.min.js"></script>
		<script src="${toolRoot}/select/select2-self.js"></script>
		
		<script src="${jsRoot}/customerImport/excelupload.js"></script>
		<%--引入遮罩效果 --%>
		<script src="${jsRoot}/customerImport/zhezhao.js"></script>
		<link href="${cssRoot}/zhezhao.css" rel="stylesheet">
		<%--引入遮罩效果 --%>
		<script type="text/javascript">
			$(function() {
				var downflag = '${downflag}';
				if (downflag == "0") {
					/* 1秒轮询读取函数 Push()引用参数js-excelupload.js*/
					var iCount = setInterval(function() { Push("/customerImport/polling/" + '${type}'); }, 1000);
					//绑定事件
					$("#btn").click(function (e) {
						clearInterval(iCount);
					});
				}
				$("#bt").trigger("click");
			});
		</script>
	</head>
	<body style="margin-left: 20px; width: 100%;">
		<%-- 遮罩效果 --%>
		<div class="zhezhao" id="zhezhao"></div>
		<div class="login" id="login">
			<img src="${imageRoot}/dealing.gif" style="width: 160px; height:60px; float: left; margin-top: 15%;" align="middle"/>
			<table width="60%" cellpadding="0" cellspacing="0" style="float: left;margin-top: 15%;">
				<c:if test="${wait eq '1' }">
					<tr id="wait" style="display:block;">
						<td colspan="11" style="border:none; height:50px; padding-left:30px;">
							<div align="center" class="tj_box">
								处理中，请等待...
							</div>
						</td>
					</tr>
				</c:if>
				<tr id="download" style="display:none;">
					<td colspan="11" style="border:none; height:50px; padding-left:30px;">
						<div align="left" class="tj_box">
							<p align="center"><font color="red" size="4px;" >导入失败。请重试...</font></p>
							<table width="400" cellpadding="0" cellspacing="0" class="table" id="errorTable">
							</table>
						</div>
					</td>
				</tr>
				<tr id="success" style=" display:none;">
					<td colspan="11" style="border:none; height:50px;">
						<div align="left" class="tj_box">
							导入成功.
						</div>
					</td>
				</tr>
			</table>
			<button id="uploadSuccess" class="btn btn-default" disabled="disabled">确&nbsp;&nbsp;定</button>
		</div>
		<div class="content" style=" display:none;">
			<button id="bt" class="btn btn-default"></button>
		</div>
		<%-- 遮罩效果 --%>
		<input type="hidden" id="btn"/>
	</body>
</html>
