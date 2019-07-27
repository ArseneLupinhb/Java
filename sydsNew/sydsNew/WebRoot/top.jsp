<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="css/introduction.css">
</head>
<body>
	<!-- 头部 -->
	<div class="header">
		<div class="logo">
			<img src="images/logo.png" style="width: 100%; height: 45px;">
		</div>
		<div class="navbar">
			<li><a href="index.jsp">首&nbsp;&nbsp;&nbsp;页</a></li>
			<li><a href="introduction.jsp">大赛简介</a></li>
			<li><a href="uploadPic.jsp">作品上传</a></li>
			<li><a href="personalHomepage.jsp">个人中心</a></li>
		</div>
		<div class="wrapper">
			<input type="text" name="search" id="" />
			<div class="research" id=""></div>
			<div class="btn">
				<button alt="" name="register"
					style="background-color: #3e4839; color: #fff;">注册</button>
				<button alt="" name="login">登录</button>
			</div>
		</div>
		<!--  如果已登录 -->

		<!-- <div class="wrapper">
            <input type="text" name="search">
            <div class="research"></div>
            <div class="customer">
              <img src="images/yonghu.png" style="margin-left: 3%;">
               <span style="font-size: 1em; color:#607869;margin-left:1%; margin-top:2%;">用户名</span>
            </div>
        </div> -->

		<!--如果已登录结束线-->
	</div>
	<!-- 头部结束 -->
</body>
</html>
