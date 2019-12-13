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
	//删除
	function deleteField(id){
		$.ajax({
			url : '/ajax/deleteField',
			data : {
				id:id
			},
			dataType : 'json',
			type : 'post',
			cache : false,
			async : false,
			success : function(data) {
				alert(data.info);
				location.reload();
			},
			error : function() {
				alert("删除失败");
			}
		});
	}
	//修改
	function editField(id){
		var fieldName1 = $("#fieldName"+id).val();
		$.ajax({
			url : '/ajax/editField',
			data : {
				id:id,
				fieldName:fieldName1
			},
			dataType : 'json',
			type : 'post',
			cache : false,
			async : false,
			success : function(data) {
				alert(data.info);
				location.reload();
			},
			error : function() {
				alert("修改失败");
			}
		});
	}
	//添加
	function addField(){
		var fieldName0 = $("#fieldName0").val();
		$.ajax({
			url : '/ajax/addField',
			data : {
				fieldName:fieldName0
			},
			dataType : 'json',
			type : 'post',
			cache : false,
			async : false,
			success : function(data) {
				alert(data.info);
				location.reload();
			},
			error : function() {
				alert("添加失败");
			}
		});
	}
	</script>
</head>
<body style="margin-left: 15px; font-size: 13px;">
	<div style="float: inherit; height:650px; overflow:auto;">
		<table width="95%" cellpadding="0" cellspacing="0" class="table2 widthCss">
      		<tr>
       			<th width="10%"  height="38" align="center">序号</th>
       			<th width="50%" align="center">负责领域名称</th>
       			<th width="20%" align="center">修改</th>
       			<th width="20%" align="center">删除</th>
       		</tr>
             	<c:if test="${not empty page.result}">
				<c:forEach items="${page.result}" var="field" varStatus="count">
					<tr>
						<td height="33" align="center">${count.count }</td>
						<td height="33" align="center"><input id="fieldName${field.id }" value="${field.fieldName }"></td>
						<td height="33" align="center">
							<a href="javascript:editField('${field.id }');" target="_blank">修改</a>
						</td>
						<td height="33" align="center">
							<a href="#" onclick="deleteField('${field.id }')">删除</a>
						</td>
					</tr>
				</c:forEach>
              </c:if>
              <tr>
              	<td colspan="4" align="center">
              		&nbsp;
              	</td>
              </tr>
              <tr>
              	<td colspan="4" align="center">
              		负责领域名称:<input type="text" id="fieldName0" value=" "/>
              		<input type="submit" class="" value="添加" onclick="addField();"/>
              	</td>
              </tr>
          </table>
    </div>
</body>
</html>
