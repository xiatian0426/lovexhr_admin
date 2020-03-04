<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script src="${jsRoot}/ckplayer/ckplayer.js"></script>
		<script type="text/javascript">

		</script>
	</head>
<body>
<div>
    <div class="video" style="padding: 5px;left: 30px;top: 20px;width: 90%;height:430px;"></div>
</div>
<script type="text/javascript">
    var videoObject = {
        container: '.video',//“#”代表容器的ID，“.”或“”代表容器的class
        variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
        loaded:'loadedHandler',//监听播放器加载成功
        autoplay:true,//自动播放
        //video:'http://localhost:8080/res/video/proVideo/10/1577091429607.mp4'//视频地址
        video:'${videoUrl}'
    };
    var player=new ckplayer(videoObject);
</script>
</body>
</html>
