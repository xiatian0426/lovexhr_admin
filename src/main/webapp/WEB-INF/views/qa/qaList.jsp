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
                page("qaListForm", ${page.pageInfo}, "pageQAList");
                //开启表单验证
                $("#qaDataForm").validationEngine();
                var result = $("#result").val();
                if(result != ""){
                    alert(result);
                }
            });
            function deleteById(id){
                $.ajax({
                    url:'/ajax/deleteQAById',
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
                            $("#qaListForm").submit();
                        }else{
                            alert("操作失败!");
                        }
                    },
                    error : function(){
                        alert("操作失败!");
                    }
                });
            }
            function updateData(id){
                var ask = $("#ask"+id).val();
                if(ask==""){
                    $("#ask"+id).validationEngine("showPrompt","问题不能是空!","error");
                    $("#ask"+id).focus();
                    return false;
                }
                var answer = $("#answer"+id).val();
                if(answer==""){
                    $("#answer"+id).validationEngine("showPrompt","答案不能是空!","error");
                    $("#answer"+id).focus();
                    return false;
                }
                var qaOrder = $("#qaOrder"+id).val();
                var re = new RegExp("^[0-9]*[0-9][0-9]*$");
                if (qaOrder != "") {
                    if (!re.test(qaOrder)) {
                        $("#qaOrder"+id).validationEngine("showPrompt","排序只能为整数!","error");
                        $("#qaOrder"+id).focus();
                        return false;
                    }
                }else{
                    $("#qaOrder"+id).validationEngine("showPrompt","排序不能为空!","error");
                    $("#qaOrder"+id).focus();
                    return false;
                }
            }
            function saveData(){
                var qaOrderNew = $("#qaOrderNew").val();
                var re = new RegExp("^[0-9]*[0-9][0-9]*$");
                if (qaOrderNew != "") {
                    if (!re.test(qaOrderNew)) {
                        $("#qaOrderNew").validationEngine("showPrompt","排序只能为整数!","error");
                        $("#qaOrderNew").focus();
                        return false;
                    }
                }else{
                    $("#qaOrderNew").validationEngine("showPrompt","排序不能为空!","error");
                    $("#qaOrderNew").focus();
                    return false;
                }
                var memberIdFlag = $("#memberIdFlag").val();
                if(memberIdFlag != ""){
                    //需要验证
                    var memberIdNew = $("#memberIdNew").val();
                    if(memberIdNew=='0'){
                        $("#memberIdNew").validationEngine("showPrompt","请选择所属人!","error");
                        $(this).focus();
                        return false;
                    }
                }
            }
		</script>
	</head>
<div style="width: 95%;  font-size: 13px;">
    <input id="result" value="${result}" type="hidden"/>
    <div style="line-height:48px; font-weight: bold;font-size: 20px;margin-top: 10px;" align="center">
    </div>
    <form class="form-horizontal" id="qaListForm" action="/QA/getQAList" method="POST">
        <div class="r_box" style="margin-top: 10px;">
            <div style="height: 15px; width: 100%;"><span></span></div>
            <span class="infoLable">问题：</span>
            <input name="ask" type="text" class="self-form-control" style="width: 100px;" value="${query.ask }"/>
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
            <button type="submit" class="btn btn-default"
                    style="background-color:#337ab7;">搜索</button>
            <br />
            <div><span></span></div>
        </div>
        <div class="pageQAList" style="height:52px;margin-top:-30px;"></div>
    </form>
    <div class="r_box" style="border:0px dashed #00F" >
        <table width="100%" cellpadding="0" cellspacing="0" class="table-bordered">
            <tr>
                <th width="5%" align="center" height="38" align="center">
                    序号
                </th>
                <th width="20%" align="center">
                    问题
                </th>
                <th width="43%" align="center">
                    答案
                </th>
                <th width="5%" align="center">
                    排序
                </th>
                <c:if test="${staff.roleId eq '1' }">
                    <th width="10%" align="center">
                        所属人
                    </th>
                </c:if>
                <th width="13%" align="center">
                    操作
                </th>
            </tr>
            <c:forEach items="${page.result}" var="data" varStatus="count">
                <form class="form-horizontal" id="qaDataListForm" action="/QA/updateById" method="POST" onsubmit="return updateData('${data.id}');">
                    <input type="hidden" name="id" value="${data.id }">
                    <tr>
                        <td align="center" height="33" align="center">
                                ${count.count}
                        </td>
                        <td align="center">
                            <input name="ask" id="ask${data.id}" value="${data.ask}" type="text" style="width: 90%;"
                                   class="text-input self-form-control"/>
                        </td>
                        <td align="center" title="${data.answer}">
                            <c:if test="${fn:length(data.answer)>40 }">
                                <input name="answer" id="answer${data.id}" value="${fn:substring(data.answer,0,40) }..." type="text" style="width: 96%"
                                       class="text-input self-form-control"/>
                            </c:if>
                            <c:if test="${fn:length(data.answer)<=40 }">
                                <input name="answer" id="answer${data.id}" value="${data.answer }" type="text" style="width: 96%"
                                       class="text-input self-form-control"/>
                            </c:if>

                        </td>
                        <td align="center">
                            <input name="qaOrder" id="qaOrder${data.id}" value="${data.qaOrder}" type="text" style="width: 90%"
                                   class="text-input self-form-control"/>
                        </td>
                        <c:if test="${userInfoList != null}">
                            <td align="center">
                                    ${userInfoDictMap[data.memberId].userName }
                            </td>
                        </c:if>
                        <td align="center">
                            <button type="submit" class="btn btn-success">更新</button>
                            <button type="button" class="btn btn-success" onclick="deleteById('${data.id}','1')" target="_blank">删除</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>

    <div class="r_box" >
        <br/>
        <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
            添加QA信息
        </div>
        <form class="form-horizontal" id="qaDataForm" action="/QA/addQA" method="POST" onsubmit="return saveData();">
            <div class="clearB"></div>
            <input type="hidden" name="productId" value="${bxProductResult.id }">
            <div class="r_box" style="padding: 5px;">
                <table width="60%" cellpadding="0" cellspacing="0" class="table-bordered" align="center">
                    <tr>
                        <td align="center" height="33" align="center"  style="width: 15%;background:#A0E0F7;">
                            <font color="red">*</font>&nbsp;问题：
                        </td>
                        <td align="center" style="width: 40%;">
                            <input name="ask" value="" type="text" style="width: 90%;"
                                   class="validate[required,noSpecialCaracters,maxSize[200]] text-input self-form-control"/>
                        </td>
                        <td align="center" height="33" align="center"  style="width: 15%;background:#A0E0F7;">
                            <font color="red">*</font>&nbsp;排序：
                        </td>
                        <td align="center" style="width: 40%;">
                            <input id="qaOrderNew" name="qaOrder" value="" type="text" style="width: 90%"
                                   class="text-input self-form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" height="33" align="center" style="width: 15%;background:#A0E0F7;">
                            <font color="red">*</font>&nbsp;答案：
                        </td>
                        <td align="center">
                            <input id="answer" name="answer" value="" type="text" style="width: 90%"
                                   class="validate[required,noSpecialCaracters,maxSize[500]] text-input self-form-control"/>
                        </td>
                        <c:if test="${staff.roleId eq '1' }">
                            <td align="center" height="33" align="center" style="width: 15%;background:#A0E0F7;">
                                <input type="hidden" name="memberIdFlag" id="memberIdFlag" value="1">
                                <font color="red">*</font>&nbsp;所属人：
                            </td>
                            <td align="center">
                                <select class="select-nosearch" id="memberIdNew" name="memberId" style="width: 90%;height: 28px;">
                                    <option value="0" selected="selected">---请选择---</option>
                                    <c:forEach items="${userInfoList}" var="userInfo" varStatus="status">
                                        <option value='${userInfo.id}'>
                                                ${userInfo.userName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                        </c:if>
                    </tr>
                </table>
                <div class="sub_div">
                    <input type="submit" class="sub_btn" value=" "/>
                    <input type="button" class="reset_btn" value=" "/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
