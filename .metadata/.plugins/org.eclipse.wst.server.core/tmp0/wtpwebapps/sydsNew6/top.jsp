<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title></title>
    <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <link rel="stylesheet" type="text/css" href="css/introduction.css">
  <!-- 导航栏 -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script> 
  <!-- 登录注册弹出窗口 -->
  <link rel="stylesheet" type="text/css" href="css/tanchuchuangkou.css"/>
  <script src="js/tanchuchuangkou.js" type="text/javascript" charset="utf-8"></script>
  <script src="js/loginAndRegiste.js" type="text/javascript"></script>
  
  </head>
  <body>
<!-- 头部 -->
    <div class="header">
   <img src="img/title.jpg" style="width: 100%;height: auto;">
 </div>
<!--  导航栏 -->
<nav class="nav navbar-inverse" role="navgation"> 
 <div class="container-fluid"> 
 <div class="navbar-header">
  <a href="#" class="navbar-brand"></a>
   <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> 
      <span class="icon-bar"></span>
      <span class="icon-bar"></span> 
      <span class="icon-bar"></span> 
   </button>
    </div> 
    <div class="collapse navbar-collapse"> 
    <ul class="navbar-nav nav"> 
    <li class="active"><a href="index0.jsp">首页</a></li> 
    <li><a href="introduction.jsp">大赛简介</a></li> 
    <li><a href="<c:url value = '/AlbumServlet' />?method=queryAlbum">个人中心</a></li>
     </ul>
      <div class="navbar-form navbar-right" style="margin-right: 30px;"> 
      <!-- <input type="text" class="form-control" name="" id="searchStr" placeholder="请输入搜索内容"> 
      <button class="btn btn-success" style="margin-right: 30px;">搜索</button> -->
       <a class="navbar-link" javascript:void(0) id = "register_id" onClick="showBox1()">注册</a> 
       <a class="navbar-link" javascript:void(0) id = "login_id" onClick="showBox()">登录 </a> 
       <a class="navbar-link" id = "showUserName" >${sessionScope.user.userName}</a>
       <a class="navbar-link" type = "hidden" id = "quit">退出</a>
       </div> 
       </div>
        </div> 
        </nav>
    <!-- 轮播 -->     

    <!-- 头部结束 -->
  </body>
</html>
