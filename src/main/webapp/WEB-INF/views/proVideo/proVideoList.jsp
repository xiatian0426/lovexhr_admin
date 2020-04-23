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
                var result = $("#result").val();
                if(result != ""){
                    alert(result);
                }
            });
            function saveData(){
                var file = $("#file").val().length;
                if(file==""){
                    $("file").validationEngine("showPrompt","用户视频不能为空!","error");
                    $("#file").focus();
                    return false;
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
    <body style=" font-size: 13px;">
        <input id="result" value="${result}" type="hidden"/>
        <div class="clearB"></div>
        <div class="r_box" style="padding: 5px;">
            <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
                用户视频
            </div>
            <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
                <a style="cursor:pointer" onclick="openVideo('${bxProVideo.videoUrl}')">点击预览</a>
            </div>
            <br/>&nbsp;
            <table  style=" font-size: 13px; " align="center" border="1" width="30%">
                <form class="form-horizontal" id="proVideoDataListForm" action="/proVideo/addProVideo" method="POST" onsubmit="return saveData();" enctype="multipart/form-data">
                    <c:if test="${bxProVideo.id!=null && bxProVideo.id!=0 }">
                        <input type="hidden" name="id" value="${bxProVideo.id }">
                        <input type="hidden" name="videoUrl" value="${bxProVideo.videoUrl }">
                    </c:if>
                    <tr>
                        <td >
                        </td>
                        <td >
                            <font color="red">*</font>&nbsp;<input type="file" id="file" name="file"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="sub_div">
                                <input type="submit" class="sub_btn" value=" "/>
                            </div>
                        </td>
                    </tr>
                </form>
            </table>
        </div>
    </body>
</body>
</html>
