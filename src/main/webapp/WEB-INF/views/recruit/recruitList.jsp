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
            function saveData(){
                var file = $("#file").val().length;
                if(file==""){
                    $("file").validationEngine("showPrompt","招聘图片不能为空!","error");
                    $("#file").focus();
                    return false;
                }
            }
		</script>
	</head>
    <body style=" font-size: 13px;">
        <div class="clearB"></div>
        <div class="r_box" style="padding: 5px;">
            <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
                招聘信息图片
            </div>
            <div style="line-height:48px; font-weight: bold;font-size: 20px;" align="center">
                <img src="${bxRecruit.imageUrl}" width="40%" height="40%"/>
            </div>
            <br/>&nbsp;
            <table  style=" font-size: 13px; " align="center" border="1" width="30%">
                <form class="form-horizontal" id="recruitDataListForm" action="/recruit/addRecruit" method="POST" onsubmit="return saveData();" enctype="multipart/form-data">
                    <c:if test="${bxRecruit.id!=null && bxRecruit.id!=0 }">
                        <input type="hidden" name="id" value="${bxRecruit.id }">
                        <input type="hidden" name="imageUrl" value="${bxRecruit.imageUrl }">
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
