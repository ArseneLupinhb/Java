<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" charset="UTF-8" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=decice-width"/>
	<title>提示登录页面</title>
	<style type="text/css">
		.errorBtn{
			font-weight: bold;
			border-radius: 5px;
			position: relative;
			overflow: hidden;
			display: inline-block; 
            margin:25% 35% 0 40%;
        }
        .errorCenter{
        	width: 100%;
        	height: auto;
        }
	</style>
</head>
<body style="background: url(img/errorTip.jpg) no-repeat;background-size: 100%;">
   
     <input type="button" class="errorBtn" value="返回首页" style="width: 20%;height: 50px;background: #363735;font-size: 1.2em;color: #fff;letter-spacing: 0.4em;" onclick="window.location.href='<c:url value = '/index0.jsp' />'">
	
</body>
</html>
