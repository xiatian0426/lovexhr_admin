<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${cssRoot}/font-awesome.min.css"/>
	<script type="text/javascript" src="${jsRoot}/jquery-1.8.3.min.js"></script>
	<style>
		*{margin:0; padding:0; list-style:none;}
		.clearfix:after{display:block; content:''; clear:both;}
		.clearfix{zoom:1;}
		html,body{height: 100%;}
		body{background:url(../../../res/images/bj.jpg) no-repeat; background-size:cover; overflow:hidden;}
		.main_l{float: left; margin-left:200px;width:633px;height: 429px; position:relative;}
		.main_r{float:left; width:340px; height:440px; background:#fff; border-radius:15px;}
		.form_wrap{width:220px; height:280px; margin:0 auto;}
		.form_wrap li{height:60px; line-height:50px; border-bottom:2px solid #ccc;}
		.form_wrap li img{float:left; width:30px; height:30px; margin:20px 10px 3px 10px;}
		.form_wrap li img.codeImg{width:68px;}
		.form_wrap li input{height:30px; border:none; float:left; margin-top:20px;}
		.code_txt{width:80px;}
		.login{width:100%; height:40px; background:#257CC1; color:#fff; margin-top:20px; border-radius: 5px; border:none; font-size:16px;}
		.btnbottom{margin-top:20px;}
		.btnbottom p{float:left; font-size:12px; color:#ccc;}
		.btnbottom p input{margin-right:5px;}
		.btnbottom a{float:left; text-decoration: none; color:#ccc; font-size:12px;}
		.btnbottom .mepass{ float:right; }
		.btnbottom .mepass:hover{color:#333; text-decoration: underline;}
		.slogan{width:100%; height:100%;}
		.img_opac{position:absolute; width:100%; height: 100%; opacity:0.3; filter:alpha(opacity=30); background:#fff; border-radius:15px;}
		.main_par{ background:rgba(0,0,0,.2); height:100%;}
		.main{width:1300px; margin:0 auto;padding-top:350px;}
		.logo{margin-top:40px; margin-left:60px;}
		@media screen and (max-width:1360px){
			.main{padding-top:230px; width:680px;padding-right:120px;float: right;}
			.main_l{margin-left:150px;width:140px; height:320px; margin-top:15px;}
			.main_r{width:280px; height:360px; border-radius:10px;}
			.logo{margin-top:35px; margin-left:50px; margin-bottom:10px;}
			.logo img{width:190px; height:54px;}
			.form_wrap li{height:46px; line-height:46px; border-bottom:2px solid #ccc;}
			.form_wrap li img{width:24px; height:24px;}
			.form_wrap li input{margin-top:10px;height:26px;}
			.form_wrap li img{margin-top:10px;}
			.login{height:30px;}
		}
	</style>
</head>
<script type="text/javascript">
    $(function(){
        var result = $("#result").val();
        if(result != ""){
            alert(result);
        }
    });
	$(document).ready(function(){
		var sign = $("#sign").val();
		var user = document.getElementById("user");
		var surePwd = document.getElementById("surePwd");
		var prompt = document.getElementById("prompt");
		if(sign==-1){
			prompt.style.fontSize = "13px";
			prompt.style.width = "31%";
			prompt.style.height = "2em";
			prompt.style.textAlign = "center";
			prompt.style.lineHeight = "3em";
			prompt.innerHTML = $("#loginMsg").val();
			user.focus();
			$("#sign").val("");
			$("#prompt").val("");
		}else if(sign==2){
			prompt.style.fontSize = "13px";
			prompt.style.width = "31%";
			prompt.style.height = "2em";
			prompt.style.textAlign = "center";
			prompt.style.lineHeight = "3em";
			prompt.innerHTML = $("#loginMsg").val();
			surePwd.focus();
			$("#sign").val("");
		}
	});
</script>

<body>
<input id="result" value="${loginMsg}" type="hidden"/>
	<%-- 登录表单  开始--%>
	<div class="main_par">
		<div class="main clearfix">
			<div class="main_l">
<!-- 				<p class="img_opac"></p> -->
<!-- 				<img src="../../../res/images/slogan.png" alt="" class="slogan"> -->
			</div>
			<div class="main_r">
				<div class="logo">
					<img src="../../../res/images/LOGO+ZI.png" height="62" width="223" alt="">
				</div>
				<div class="form_wrap">
					<input type="hidden" value="${sign }" id="sign">
					<input type="hidden" value="${loginMsg }" id="loginMsg">
					<form action="/account/login" id="formOne" method="POST" onsubmit="return submitB()" >
						<ul>
							<li class="clearfix">
								<img src="../../../res/images/person.png">
								<input type="text" name="userName" id="user" placeholder="请输入您的用户名" style="background:#fff; color:#000;"/>
							</li>
							<li class="clearfix">
								<img src="../../../res/images/lock.png">
								<input type="password" name="pwd" id="pwd" placeholder="请输入密码" style="background:#fff; color:#000;"/>
							</li>
<!-- 							<li class="clearfix"> -->
<!-- 								<img src="../../../res/images/code.png"> -->
<!-- 								<input type="text" name="verifycode" id="surePwd" placeholder="请输入验证码" style="width: 36%; background:#fff; color:#000;"/> -->
<!-- 								<img title='看不清 点一下' id="validatecodeimg" onclick="changeImg();" src="/account/verifycode" style="width: 70px; height: 36px; cursor: hand;margin-bottom:-1.7%" /> -->
<!-- 								<span id="prompt" style="color:red;"></span> -->
<!-- 							</li> -->
						</ul>
						<input type="submit" value="登&nbsp;录 &nbsp;&nbsp;&nbsp;&nbsp;" id="submit" class="login"/>
<!-- 						<div class="btnbottom"> -->
<!-- 							<p><input type="checkbox" id="checkpass"><label for="checkpass">记住密码</label></p> -->
<!-- 							<a href="javascript:;" class="mepass">忘记密码？</a> -->
<!-- 						</div> -->
					</form>
				</div>
			</div>
		</div>
	</div>
	<%-- 登录表单  结束--%>
	<script type="text/javascript" src="${jsRoot}/login/loginMy.js"></script>
</body>
</html>
