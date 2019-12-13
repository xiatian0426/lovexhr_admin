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
							location.reload();
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
    <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
        QA信息列表
    </div>
    <div class="r_box" >
        <table width="100%" cellpadding="0" cellspacing="0" class="table-bordered">
            <tr>
                <th width="5%" align="center" height="38" align="center">
                    序号
                </th>
                <th width="13%" align="center">
                    问题
                </th>
                <th width="23%" align="center">
                    答案
                </th>
                <th width="5%" align="center">
                    排序
                </th>
                <th width="8%" align="center">
                    操作
                </th>
            </tr>
            <c:forEach items="${list}" var="data" varStatus="count">
                <form class="form-horizontal" id="qaDataListForm" action="/QA/goQa" method="POST">
                    <tr>
                        <td align="center" height="33" align="center">
                            ${count.count}
                        </td>
                        <td align="center">
                            ${data.ask}
                        </td>
                        <td align="center" title="${data.answer}">
                            <c:if test="${fn:length(data.answer)>20 }">
                                ${fn:substring(data.answer,0,20) }...
                            </c:if>
                            <c:if test="${fn:length(data.answer)<=20 }">
                                ${data.answer }
                            </c:if>
                        </td>
                        <td align="center">
                            ${data.qaOrder}
                        </td>
                        <td align="center">
                            <button type="button" class="btn btn-success" onclick="save('${data.id}')" target="_blank">保存</button>
                            <button type="button" class="btn btn-success" onclick="deleteById('${data.id}','1')" target="_blank">删除</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
        <br/>
        <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
            添加QA信息
        </div>
        <form class="form-horizontal" id="qaDataForm" action="/QA/goQa" method="POST">
            <div class="clearB"></div>
            <input type="hidden" name="productId" value="${bxProductResult.id }">
            <div class="r_box" style="padding: 5px;">
                <table width="60%" cellpadding="0" cellspacing="0" class="table-bordered" align="center">
                    <tr>
                        <td align="center" height="33" align="center" style="width: 10%;">
                            问题：
                        </td>
                        <td align="center" style="width: 40%;">
                            <input id="ask" name="ask" value="" type="text" style="width: 90%;"
                                   class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        </td>
                        <td align="center" height="33" align="center" style="width: 10%;">
                            排序：
                        </td>
                        <td align="center" style="width: 40%;">
                            <input id="qaOrder" name="qaOrder" value="" type="text" style="width: 90%"
                                   class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        </td>
                    </tr>
                    <tr>

                        <td align="center" height="33" align="center">
                            答案：
                        </td>
                        <td align="center" colspan="3">
                            <input id="answer" name="answer" value="" type="text" style="width: 96%"
                                   class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        </td>
                    </tr>
                </table>
                <div class="sub_div">
                    <input type="button" class="sub_btn" onclick="save('${data.id}')" target="_blank" value=" "/>
                    <input type="button" class="delete_btn" onclick="save('${data.id}')" target="_blank" value=" "/>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
