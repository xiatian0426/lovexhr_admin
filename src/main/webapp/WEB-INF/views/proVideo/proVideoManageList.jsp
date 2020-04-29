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
                page("proVideoListForm", ${page.pageInfo}, "pageProVideoList");
                var result = $("#result").val();
                if(result != ""){
                    alert(result);
                }
            });

            function deleteById(id,memberId,videoUrl){
                $.ajax({
                    url:'/ajax/deleteProVideoById',
                    data:{
                        id:id,
                        memberId:memberId,
                        videoUrl:videoUrl
                    },
                    dataType:'json',
                    type:'post',
                    cache:false,
                    async:false,
                    success:function(data) {
                        if(data.info=='1'){
                            alert("操作成功!");
                            $("#proVideoListForm").submit();
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
            }
            function saveData(){
                var fileNew = $("#fileNew").val().length;
                if(fileNew==""){
                    $("#fileNew").validationEngine("showPrompt","用户视频不能为空!","error");
                    $("#fileNew").focus();
                    return false;
                }
                var memberIdFlag = $("#memberIdFlag").val();
                if(memberIdFlag != ""){
                    //需要验证
                    var memberIdNew = $("#memberIdNew").val();
                    if(memberIdNew=='0'){
                        $("#memberIdNew").validationEngine("showPrompt","请选择所属人!","error");
                        $("#memberIdNew").focus();
                        return false;
                    }
                }
            }

            function openVideo(videoUrl){
                var openUrl = "/product/openVideo?videoUrl="+videoUrl;//弹出窗口的url
                var iWidth=700; //弹出窗口的宽度;
                var iHeight=500; //弹出窗口的高度;
                var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
                var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
                var opener= window.open(openUrl,"","height="+iHeight+", width="+iWidth+", top="+iTop+",left="+iLeft+",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
            }
		</script>
	</head>
<body style="width: 95%;  font-size: 13px;">
<input id="result" value="${result}" type="hidden"/>
    <form class="form-horizontal" id="proVideoListForm" action="/proVideo/getProVideoList" method="POST">
        <c:if test="${staff.roleId eq '1' }">
            <div class="r_box" style="margin-top: 10px;">
                <div style="height: 15px; width: 100%;"><span></span></div>
                <span class="infoLable">所属人：</span>
                <select class="select-nosearch" name="memberId" style="width: 200px;height: 28px;">
                    <option value="0" selected="selected">---请选择---</option>
                    <c:forEach items="${userInfoList}" var="userInfo" varStatus="status">
                        <option value='${userInfo.id}'<c:if test="${userInfo.id==query.memberId }">selected="selected"</c:if>>
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
        <div class="pageProVideoList" style="height:52px;margin-top:-30px;"></div>
    </form>
    <div class="r_box" style="border:0px dashed #00F" >
        <table width="100%" cellpadding="0" cellspacing="0" class="table-bordered">
            <tr>
                <th width="10%" align="center" height="38" align="center">
                    序号
                </th>
                <th width="25%" align="center">
                    预览
                </th>
                <th width="35%" align="center">
                    视频
                </th>
                <c:if test="${userInfoList != null}">
                    <th width="10%" align="center">
                        所属人
                    </th>
                </c:if>
                <th width="15%" align="center">
                    操作
                </th>
            </tr>
            <c:forEach items="${page.result}" var="data" varStatus="count">
                <form class="form-horizontal" id="proVideoDataListForm" action="/proVideo/updateById" method="POST" onsubmit="return updateData('${data.id}');" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${data.id }">
                    <c:if test="${data.id!=null && data.id!=0}">
                        <input type="hidden" name="videoUrl" value="${data.videoUrl }">
                    </c:if>
                    <tr>
                        <td align="center" height="33" align="center">
                            ${count.count}
                        </td>
                        <td align="center">
                            <a style="cursor:pointer" onclick="openVideo('${data.videoUrl}')">点击查看</a>
                        </td>
                        <td align="center">
                            <input type="file" name="file"/>
                        </td>
                        <c:if test="${userInfoList != null}">
                            <td align="center">
                                ${userInfoDictMap[data.memberId].userName }
                            </td>
                        </c:if>
                        <td align="center">
                            <button type="submit" class="btn btn-success">更新</button>
                            <button type="button" class="btn btn-success" onclick="deleteById('${data.id}','${data.memberId}','${data.videoUrl}')" target="_blank">删除</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>

    <div class="r_box" >
        <br/>
        <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
            添加用户视频
        </div>
        <form class="form-horizontal" id="proVideoDataForm" action="/proVideo/addProVideo" method="POST" onsubmit="return saveData();" enctype="multipart/form-data">
            <div class="clearB"></div>
            <div class="r_box" style="padding: 5px;">
                <table width="60%" cellpadding="0" cellspacing="0" class="table-bordered" align="center">
                    <tr>
                        <td align="center" height="33" align="center" style="width: 15%;background:#A0E0F7;">
                            <font color="red">*</font>&nbsp;用户视频：
                        </td>
                        <td align="center" style="width: 40%;">
                            <input type="file" id="fileNew" name="file" value="">
                        </td>
                        <c:if test="${userInfoList != null}">
                            <td align="center" height="33" align="center" style="width: 15%;background:#A0E0F7;">
                                <input type="hidden" name="memberIdFlag" id="memberIdFlag" value="1">
                                &nbsp;&nbsp;&nbsp;<font color="red">*</font>&nbsp;所属人：
                            </td>
                            <td align="center" style="width: 40%;">
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
</body>
</html>
