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
        <!-- 验证 -->
        <link rel="stylesheet" href="${toolRoot }/validata/validationEngine.css" />
        <script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine.js"></script>
        <script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine-zh_CN.js" charset="utf-8"></script>
		<script type="text/javascript">
            $(function(){
                //函数来源page.js
                page("questionListForm", ${page.pageInfo}, "pageQuestionList");
                var result = $("#result").val();
                if(result != ""){
                    alert(result);
                }
            });

            function deleteById(id){
                $.ajax({
                    url:'/ajax/deleteQuestionById',
                    data:{
                        id:id
                    },
                    dataType:'json',
                    type:'post',
                    cache:false,
                    async:false,
                    success:function(data) {
                        if(data.info=='1'){
                            alert("操作成功!");
                            $("#questionListForm").submit();
                        }else{
                            alert("操作失败!");
                        }
                    },
                    error : function(){
                        alert("操作失败!");
                    }
                });
            }

            function goEdit(id){
                window.location="/question/goEdit?id="+id;
            }
		</script>
	</head>
<body style="width: 95%;  font-size: 13px;">
<input id="result" value="${result}" type="hidden"/>
    <div style="line-height:48px; font-weight: bold;font-size: 20px;margin-top: 10px;" align="center">
    </div>
    <form class="form-horizontal" id="questionListForm" action="/question/getQuestionList" method="POST">
        <div class="r_box" style="margin-top: 10px;">
            <div style="height: 15px; width: 100%;"><span></span></div>
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
            <span class="infoLable">问题：</span>
            <input name="question" type="text" class="self-form-control" style="width: 150px;" value="${query.question }"/>
            <button type="submit" class="btn btn-default"
                    style="background-color:#337ab7;">搜索</button>
            <br />
            <div><span></span></div>
        </div>
        <div class="pageQuestionList" style="height:52px;margin-top:-30px;"></div>
    </form>
    <div class="r_box" style="border:0px dashed #00F" >
        <table width="100%" cellpadding="0" cellspacing="0" class="table-bordered">
            <tr>
                <th width="5%" align="center" height="38" align="center">
                    序号
                </th>
                <th width="10%" align="center">
                    提问人号码
                </th>
                <th width="10%" align="center">
                    问题
                </th>
                <th width="10%" align="center">
                    提问时间
                </th>
                <th width="10%" align="center">
                    状态
                </th>
                <th width="10%" align="center">
                    处理人
                </th>
                <th width="10%" align="center">
                    处理时间
                </th>
                <c:if test="${userInfoList != null}">
                    <th width="10%" align="center">
                        所属人
                    </th>
                </c:if>
                <th width="10%" align="center">
                    操作
                </th>
            </tr>
            <c:forEach items="${page.result}" var="data" varStatus="count">
                <tr>
                    <td align="center" height="33" align="center">
                        ${count.count}
                    </td>
                    <td align="center">
                            ${data.phone}
                    </td>
                    <td align="center">
                            ${data.question}
                    </td>
                    <td align="center">
                            ${data.createDate}
                    </td>
                    <td align="center">
                        <c:if test="${data.status == 0}">
                            未处理
                        </c:if>
                        <c:if test="${data.status == 1}">
                            已处理
                        </c:if>
                    </td>
                    <td align="center">
                            ${userInfoDictMap[data.checkId].userName }
                    </td>
                    <td align="center">
                            ${data.checkDate}
                    </td>
                    <c:if test="${userInfoList != null}">
                        <td align="center">
                            ${userInfoDictMap[data.memberId].userName }
                        </td>
                    </c:if>
                    <td align="center">
                        <c:if test="${data.status == 0}">
                            <button type="button" class="btn btn-success" onclick="goEdit('${data.id}')">处理</button>
                        </c:if>
                        <button type="button" class="btn btn-success" onclick="deleteById('${data.id}')" target="_blank">删除</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
