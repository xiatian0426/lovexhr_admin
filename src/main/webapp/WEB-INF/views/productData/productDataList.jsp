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
		<script type="text/javascript">
            $(function(){
                //函数来源page.js
                page("productListForm", ${page.pageInfo}, "pageProductList");
                var result = $("#result").val();
                if(result != ""){
                    alert(result);
                }
            });

			function goEditMessageData(productId){
				window.location="/product/getProDetail?productId="+productId;
			}
			function deleteMessageData(id,isdelete){
				$.ajax({
					url:'/ajax/deleteByProdictId',
					data:{
						id:id,
						isdelete:isdelete
					},
					dataType:'json',
					type:'post',
					cache:false,
					async:false,
					success:function(data) {
						if(data.info=='1'){
							alert("操作成功!");
                            $("#productListForm").submit();
						}else{
							alert("操作失败!");
						}
					},
					error : function(){
						alert("操作失败!");
					}
				});
			}
		</script>
	</head>
<body style="width: 95%;  font-size: 13px;">
<input id="result" value="${result}" type="hidden"/>
	<form class="form-horizontal" id="productListForm" action="/product/getProductByMemId" method="POST">
		<div class="r_box" style="margin-top: 10px;">
			<div style="height: 15px; width: 100%;"><span></span></div>
			<span class="infoLable">商品名称：</span>
				<input id="productName" name="productName" type="text" value="${query.productName }" class="self-form-control" style="width: 150px;"/>
            <c:if test="${staff.roleId eq '1' }">
                <span class="infoLable">所属人：</span>
                <select class="select-nosearch" name="memberId" style="width: 200px;height: 28px;">
                    <option value="0" selected="selected">---请选择---</option>
                    <c:forEach items="${userInfoList}" var="userInfo" varStatus="status">
                        <option value='${userInfo.id}'<c:if test="${userInfo.id==query.memberId }">selected="selected"</c:if>>
                                ${userInfo.userName}
                        </option>
                    </c:forEach>
                </select>
            </c:if>
			<button type="submit" class="btn btn-default" style="background-color:#337ab7;">搜索</button>
			<br />
			<div><span></span></div>
		</div>
		<div class="r_box" >
			<table width="100%" cellpadding="0" cellspacing="0" class="table-bordered">
				<tr>
					<th width="5%" align="center" height="38" align="center">
						序号
					</th>
					<th width="13%" align="center">
						商品名称
					</th>
					<th width="13%" align="center">
						商品描述
					</th>
					<th width="5%" align="center">
						排序
					</th>
					<th width="13%" align="center">
						商品图片
					</th>
                    <c:if test="${staff.roleId eq '1' }">
                        <th width="10%" align="center">
                            所属人
                        </th>
                    </c:if>
					<th width="8%" align="center">
						操作
					</th>
				</tr>
				<c:forEach items="${page.result}" var="data" varStatus="count">
					<tr>
						<td align="center" height="33" align="center">
							${count.count}
						</td>
						<td align="center">
							${data.productName}
						</td>
                        <td align="center" title="${data.productDesc}">
                            <c:if test="${fn:length(data.productDesc)>20 }">
                                ${fn:substring(data.productDesc,0,20) }...
                            </c:if>
                            <c:if test="${fn:length(data.productDesc)<=20 }">
                                ${data.productDesc }
                            </c:if>
                        </td>
						<td align="center">
							${data.productOrder}
						</td>
                        <td align="center">
                            <img src="${data.productImg}" width="70px" height="70px"/>
                        </td>
                        <c:if test="${userInfoList != null}">
                            <td align="center">
                                    ${userInfoDictMap[data.memberId].userName }
                            </td>
                        </c:if>
                        <td align="center">
                            <c:if test="${data.status==0}">
                                <button type="button" class="btn btn-success" onclick="goEditMessageData('${data.id}')" target="_blank">编辑</button>
                                <button type="button" class="btn btn-success" onclick="deleteMessageData('${data.id}','1')" target="_blank">删除</button>
                            </c:if>
                            <c:if test="${data.status==1}">
                                <button type="button" class="btn btn-success" onclick="deleteMessageData('${data.id}','0')" target="_blank">还原</button>
                            </c:if>
                        </td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="10" height="30" bgcolor="#D9F3FD" align="left" class="pageProductList">
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
