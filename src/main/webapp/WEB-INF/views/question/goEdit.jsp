<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>问题处理</title>
    <%@ include file="../common/jsp_contants.jsp"%>
    <%@ include file="../common/js_contants.jsp"%>
    <link rel="stylesheet" type="text/css" href="${cssRoot }/right_style.css" />
    <link rel="stylesheet" type="text/css" href="${cssRoot }/zTreeStyle.css" />
    <script type="text/javascript" src="${jsRoot }/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${jsRoot }/move_sel_tree.js"></script>
    <!-- 引入 Bootstrap -->
    <link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
    <script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
    <!-- 日历控件 -->
    <script type="text/javascript" src="${toolRoot }/My97DatePicker/WdatePicker.js"></script>
    <!-- ZTree控件 -->
    <script type="text/javascript" src="${jsRoot }/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${jsRoot }/jquery.ztree.excheck-3.5.min.js"></script>
    <script type="text/javascript" src="${jsRoot }/dtree.js"></script>
    <!-- 验证 -->
    <link rel="stylesheet" href="${toolRoot }/validata/validationEngine.css" />
    <script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine-zh_CN.js" charset="utf-8"></script>
    <!-- 引入 Bootstrap -->
    <link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
    <script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
    <script teye="text/javascript">
        $(function(){
            //开启表单验证
            $("#questionForm").validationEngine();
            //为登录名称添加光标失去事件
            $("#answer").blur(function(){
                //用户名
                var answer = $("#answer").val();
                if(answer == ''){
                    $("#answer").validationEngine("showPrompt","登录名称不能是空","error");
                }
            });
        })
        function updateById(){
            //回答
            var answer = $("#answer").val();
            if(answer == ''){
                $("#answer").validationEngine("showPrompt","回答不能是空","error");
                return false;
            }
        }
    </script>
</head>
<body style=" font-size: 13px;">
<form action="/question/updateById" name="questionForm" method="post" target="main" id="questionForm" onsubmit="return updateById();">
    <input type="hidden" name="id" value="${bxQuestion.id }">
    <div class="clearB"></div>
    <div class="r_box" style="padding: 0; width: 777px;">
        <div class="adress" align="center" style="font-weight: bold">
            问题处理
        </div>
        <table  style=" font-size: 13px; " align=center>
            <tr>
                <td style="background:#A0E0F7;padding: 10px 15px;width: 130px;">提问人号码：</td>
                <td style="width: 130px;">
                    ${bxQuestion.phone }&nbsp;&nbsp;
                </td>
                <td style="background:#A0E0F7;padding: 10px 15px;width: 130px;">问题：</td>
                <td style="width: 130px;">
                    ${bxQuestion.question }
                </td>
            </tr>
            <tr>
                <td style="background:#A0E0F7;padding: 10px 15px;">回答：</td>
                <td colspan="3">
                    <input id="answer" name="answer" value="" type="text" style="width: 500px;height: 300px;"
                           class="validate[required,noSpecialCaracters,maxSize[1200]] text-input self-form-control"/>
                </td>
            </tr>
        </table>
        <div class="sub_div">
            <input type="submit" class="sub_btn" value=" "/>
            <input type="reset" class="reset_btn" value=" " />
        </div>
    </div>
</form>
</body>
</html>
