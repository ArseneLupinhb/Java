<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = this.getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/backCommon.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/backLogin.css">
<title>第四届“和谐*美丽”校园摄影大赛网站后台管理平台</title>
</head>
<body>

<div class="login_w">
	<p class="login_topic_w">摄影大赛后台登陆系统</p>
    <form action="<%=path%>/AdminServlet?method=login" method="post">
    <div class="login_div_w" >
        <p class="login_name">用户名：</p>
        <input type="text" name="adminName"/>
    </div>
    <div style="clear:both"></div>
    <div class="login_div_w" style="margin-top:10px;">
        <p class="login_name">密码：</p>
        <input type="password" name="adminPwd"/>
    </div>
    <p class="message_p" align="center">${message}</p>
    <div style="clear:both"></div>
    <input type="hidden" name="status" value="backLogin"/>
    <input type="submit" value="登录" class="login_input_w" style="margin-left:170px;cursor:pointer;" />
    <input type="button" value="取消" class="login_input_w" style="cursor:pointer;" onclick="window.location='<%=path%>/index.jsp'"/>
    </form>
</div>
</body>
</html>