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
                page("mommentListForm", ${page.pageInfo}, "pageMommentList");
                var result = $("#result").val();
                if(result != ""){
                    alert(result);
                }
            });

            function deleteById(id){
                $.ajax({
                    url:'/ajax/deleteMommentById',
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
                            $("#mommentListForm").submit();
                        }else{
                            alert("操作失败!");
                        }
                    },
                    error : function(){
                        alert("操作失败!");
                    }
                });
            }

            function checkMommentById(id,status){
                $.ajax({
                    url:'/ajax/checkMommentById',
                    data:{
                        id:id,
                        status:status
                    },
                    dataType:'json',
                    type:'post',
                    cache:false,
                    async:false,
                    success:function(data) {
                        if(data.info=='1'){
                            alert("操作成功!");
                            $("#mommentListForm").submit();
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
    <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
        评论列表
    </div>
    <form class="form-horizontal" id="mommentListForm" action="/momment/getMommentList" method="POST">
        <c:if test="${staff.roleId eq '1' }">
            <div class="r_box" style="margin-top: 10px;">
                <div style="height: 15px; width: 100%;"><span></span></div>
                <span class="infoLable">评论内容：</span>
                <input id="comment_context" name="comment_context" type="text" value="${query.comment_context }" class="self-form-control" style="width: 150px;"/>
                <span class="infoLable">被评论人：</span>
                <select class="select-nosearch" name="respondent_id" style="width: 200px;height: 28px;">
                    <option value="0" selected="selected">---请选择---</option>
                    <c:forEach items="${userInfoList}" var="userInfo" varStatus="status">
                        <option value='${userInfo.id}'<c:if test="${userInfo.id==query.respondent_id }">selected="selected"</c:if>>
                                ${userInfo.userName}
                        </option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-default"
                        style="background-color:#337ab7;">搜索</button>
                <br />
                <div><span></span></div>
            </div>
        </c:if>
        <div class="pageMommentList" style="height:52px;margin-top:-30px;"></div>
    </form>
    <div class="r_box" style="border:0px dashed #00F" >
        <table width="130%" cellpadding="0" cellspacing="0" class="table-bordered">
            <tr>
                <th width="3%" align="center" height="38" align="center">
                    序号
                </th>
                <%--<th width="5%" align="center">--%>
                    <%--评论人微信号--%>
                <%--</th>--%>
                <th width="5%" align="center">
                    评论人头像
                </th>
                <th width="5%" align="center">
                    评论人昵称
                </th>
                <th width="5%" align="center">
                    评论内容
                </th>
                <th width="5%" align="center">
                    星级
                </th>
                <th width="5%" align="center">
                    评论标签
                </th>
                <th width="5%" align="center">
                    评论时间
                </th>
                <c:if test="${userInfoList != null}">
                    <th width="5%" align="center">
                        被评论人
                    </th>
                </c:if>
                <th width="5%" align="center">
                    审核状态
                </th>
                <th width="5%" align="center">
                    审核人
                </th>
                <th width="5%" align="center">
                    审核时间
                </th>
                <th width="8%" align="center">
                    操作
                </th>
            </tr>
            <c:forEach items="${page.result}" var="data" varStatus="count">
                <tr>
                    <td align="center" height="33" align="center">
                        ${count.count}
                    </td>
                    <%--<td align="center">--%>
                            <%--${data.commentator_wechat}--%>
                    <%--</td>--%>
                    <td align="center">
                        <img src="${data.commentator_img}" width="50" height="50"/>
                    </td>
                    <td align="center">
                            ${data.commentator_name}
                    </td>
                    <td align="center" title="${data.comment_context}">
                        <c:if test="${fn:length(data.comment_context)>8 }">
                            ${fn:substring(data.comment_context,0,8) }...
                        </c:if>
                        <c:if test="${fn:length(data.comment_context)<=8 }">
                            ${data.comment_context }
                        </c:if>
                    </td>
                    <td align="center">
                            ${data.star_level}
                    </td>
                    <td align="center">
                            ${data.comment_tag}
                    </td>
                    <td align="center">
                            ${data.create_date}
                    </td>
                    <c:if test="${userInfoList != null}">
                        <td align="center">
                                ${userInfoDictMap[data.respondent_id].userName }
                        </td>
                    </c:if>
                    <td align="center">
                        <c:if test="${data.status == 0}">
                            未审核
                        </c:if>
                        <c:if test="${data.status == 2}">
                            审核通过
                        </c:if>
                        <c:if test="${data.status == 3}">
                            审核不通过
                        </c:if>
                    </td>
                    <td align="center">
                            ${userInfoDictMap[data.check_id].userName }
                    </td>
                    <td align="center">
                            ${data.check_date}
                    </td>
                    <td align="center">
                        <c:if test="${data.status == 0}">
                            <a href="#" onclick="checkMommentById('${data.id}',2)" target="_blank">审核通过</a>&nbsp;&nbsp;
                            <a href="#" onclick="checkMommentById('${data.id}',3)" target="_blank">审核不通过</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="#" onclick="deleteById('${data.id}')" target="_blank">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
