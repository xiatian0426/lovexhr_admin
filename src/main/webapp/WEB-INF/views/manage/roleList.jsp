<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	</script>
</head>
<body style="margin-left: 15px; font-size: 13px;">
	<div style="float: inherit; height:550px; overflow:auto;">
		<table width="95%" cellpadding="0" cellspacing="0" class="table2 widthCss">
      		<tr>
       			<th width="10%"  height="38" align="center">序号</th>
       			<th width="35%" align="center">用户角色</th>
       			<th width="35%" align="center">权限</th>
       		</tr>
             	<c:if test="${not empty page.result}">
				<c:forEach items="${page.result}" var="role" varStatus="count">
					<tr>
						<td height="33" align="center">${count.count }</td>
						<td height="33" align="center">${role.roleName }</td>
						<td height="33" align="center">
							${role.roleMessageRightName }
						</td>
					</tr>
				</c:forEach>
              </c:if>
              <tr>
              	<td colspan="4" align="center">
              		&nbsp;
              	</td>
              </tr>
          </table>
    </div>
</body>
</html>
