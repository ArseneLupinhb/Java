<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <!-- 导航栏 -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script> 
<!-- 登录注册弹出窗口 -->
  <link rel="stylesheet" type="text/css" href="css/tanchuchuangkou.css"/>
  <script src="js/tanchuchuangkou.js" type="text/javascript" charset="utf-8"></script>
  <script src="js/jQuery-placeholder.js" type="text/javascript" charset="utf-8"></script>
  <link rel="stylesheet" type="text/css" href="css/index.css">
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <script src="js/style.js"></script>
  
  <script src="js/jquery-3.1.1.min.js"></script>
	<meta name="viewport" charset="UTF-8" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=decice-width"/>
	<title>个人中心主页</title>
	<link rel="stylesheet" type="text/css" href="css/album.css" />
	<script src="js/style.js"></script>
    <script src="layer/jquery.js?v=1.83.min"></script>
    <script src="layer/layer.min.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function() {
    //退出
  	$('#quit').bind('click', function() {
  		$.ajax({
			type : "POST",
			url : 'UserServlet?method=quit',
			data : {},
			success : function(data) {
				if (data == 'true') {
					alert("退出成功");
					window.location = "index0.jsp"; 
				}else if(data == 'noLogin'){
					alert("请先登录");
				}else{
					alert("退出失败，请重新打开浏览器");
					window.location.reload() ;
				}
			}
		});
  		
  	});
    }); 

        //增加相册
        $(function(){
        	$(".addAlbum").unbind('click').click(function(){
        		 $('#editForm').show();
                 $('#fade').show();
                 $("#albumTitle").val();
                 $("#albumDes").val();
                 $("#span_delete").hide();
                 $("#deleteAlbum").hide();
                 $(".btn").val("添加");
                
                 //添加相册
                 $("#addOrUpdate").unbind('click').click(function(){
                	 if( $("#albumTitle").val() == ""){
                		 alert("请输入作品集名称");
                		 //return;
                	 }else  if( $("#albumTitle").val().length > 9){
            			 alert("请注意输入的标题的长度");
            		 }else{
            			 
                		 var url = "<c:url value = '/AlbumServlet' />?method=addAlbum";
                         var albumName = $("#albumTitle").val();
                    	 var description = $("#albumDes").val();
                    	 if(description.length > 30){
                    		 alert("请注意输入的描述的长度");
                    		 return;
                    	 }
                    	 if(description == "描述不允许超过26个字符" ){
                    		 description = "";
                    	 }
                    	 var params = {"albumName":albumName, "description":description};
                    	 $(this).load(url,params,function(data){
                    		 var jsonData = eval("("+data+")");
         					$('#editForm').hide();
                            $('#fade').hide();
                            setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                         		window.location.reload();//页面刷新
                         		},100);
         					//windows.location ="<c:url value = '/AlbumServlet' />?method=queryAlbum";
                    	 });
                    	 
                    	 
                         
					}
                	 
                 });
                 
                /*  $('.btn').click(function () {
                	 
                	 if( $("#albumTitle").val() != ""){
                		 $('#main').append('<div class="album effect-2"><div class="member-image"><img src="images/album.jpg" style="background: #fff;"></div><div class="member-info"><h3><a href="photoShow.html">${album.albumName }</a></h3><h5>albumName</h5><p><a href="photoShow.html">${album.description }</a></p><button type="button" class="button grey" onclick="dosomething()">编辑</button></div><div class="social-touch"><a class="fb-touch" href="photoShow.html"></a><a class="tweet-touch" href="photoShow.html"></a><a class="linkedin-touch" href="photoShow.html"></a></div></div>');
                         $('#editForm').hide();
                         $('#fade').hide();
                	 }else{
                		  $('#editForm').show();
                          $('#fade').show();
                	 }
                 }); */
                
        	});
        });
        
        //删除相册或修改相册
        function dosomething(index) {
        	var albumName =  $("[id='a_albumName"+index+"']").text();
        	var description = $("[id='a_description"+index+"']").text();
        	var userName = $("[id='h_userName']").html();
        	 $("#albumTitle").val(albumName);
             $("#albumDes").val(description);
        	 $(".btn").val("保存设置");
        	 $("#deleteAlbum").show();
        	 if ($('.white_content').is(':hidden')) {
                 $('.white_content').show();
             }
             else {
                 $('.white_content').hide();
             }
        	 //删除相册
        	 $("#deleteAlbum").click(function(){
        		 var url = "<c:url value = '/AlbumServlet' />?method=deleteAlbum";
            	 var id = $("#album_id"+index).val();
        		 var params = {"id":id};
            	 $(this).load(url,params,function(data){
            	 });
            	 $('#editForm').hide();
                 $('#fade').hide();
                 setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
              		window.location.reload();//页面刷新
              		},100);
        	 });
        	
        	 //修改相册
        	 $("#addOrUpdate").unbind('click').click(function(){
        		 if( $("#albumTitle").val() == ""){
            		 alert("请输入作品集名称");
            		 return;
            	 }
            	 var url = "<c:url value = '/AlbumServlet' />?method=alertAlbum";
            	 var id = $("#album_id"+index).val();
                 var albumNameNew = $("#albumTitle").val();
            	 var description = $("#albumDes").val();
            	 var params = {"id":id, "albumName":albumNameNew, "description":description};
            	 $(this).load(url,params,function(data){
            		 setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                   		window.location.reload();//页面刷新
                   		},100);
            	 });
            	 $('#editForm').hide();
                 $('#fade').hide();
             });
        }
        
    </script>
</head>
</head>
<body>
    <!--头部  -->
    <div class="header">
   <img src="img/title.jpg" style="width: 100%;height: auto;">
 </div>
<nav class="nav navbar-inverse" role="navgation"> 
 <div class="container-fluid"> 
 <div class="navbar-header">
  <a class="navbar-brand"> 
   
  </a>
   <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> 
      <span class="icon-bar"></span>
      <span class="icon-bar"></span> 
      <span class="icon-bar"></span> 
   </button>
    </div> 
    <div class="collapse navbar-collapse"> 
    <ul class="navbar-nav nav"> 
    <li><a href="index0.jsp">首页</a></li> 
    <li><a href="introduction.jsp">大赛简介</a></li> 
    <li class="active"><a href="<c:url value = '/AlbumServlet' />?method=queryAlbum">个人中心</a></li>
     </ul>
       <div class="navbar-form navbar-right" style="margin-right: 30px;"> 
      <!-- <input type="text" class="form-control" name="" id="searchStr" placeholder="请输入搜索内容"> 
      <button class="btn btn-success" style="margin-right: 30px;">搜索</button> -->
       <!-- <a class="navbar-link" javascript:void(0) id = "register_id" onClick="showBox1()">注册</a> 
       <a class="navbar-link" javascript:void(0) id = "login_id" onClick="showBox()">登录 </a>  -->
       <a class="navbar-link" id = "showUserName" >${sessionScope.user.userName}</a>
       <a class="navbar-link" style="cursor:pointer" type = "hidden" id = "quit">退出</a>
       </div> 
       </div>
        </div> 
        </nav>
        <!--头部结束  -->
        
     <div id="container2">
    	<div class="banner">
    	   <div class="userpic"><img src="img/username.png"></div>
    	   <div class="banner-context">
    	       <div class="banner-head">
    	   	      <div class="banner-username"><b id="t" class="change">${user.userName }</b></div>
    	   	       
    	   	   </div>
    	   	   
    	   	   <div class="banner-userinfor">
    	   	      <table>
                        <tr>
                            <td>学院：</td> 
                            <td class="change" style="display: inline-block;width: 200px;">${unitName }</td>
                            <td>真实姓名：</td> 
                            <td class="change">${user.realName }</td>
                        </tr>
                        <tr>
                            <td>联系电话：</td> 
                            <td class="change">${user.phone }</td>
                        </tr>
                   </table>
    	   	   </div> 
    	   </div>
    	   <div class="tabs">
    	   	   <div class="tab"><a href="<c:url value = '/AlbumServlet' />?method=queryAlbum">${sessionScope.countAlbum }作品集</a></div>
    	   	   <div class="tab"><a href=""><b>${sessionScope.countImageByUser }照片</b></a></div>
    	   	   <%-- <div class="tab">${sessionScope.countPraise }赞</div> --%>
    	   	   <div class="tab" style="font-size:1em;color:red;border-right:0;font-weight:bold;">上传照片流程：1）请先建立一个相册  2）点击新相册，进入相册里面进行照片上传</div>
    	   </div>
    	</div>
    	
    	<!-- 那个弹出的窗口 -->
    	<div id="fade" class="black_overlay"></div>
            <div id="editForm" class="white_content">
            <div style="text-align: right; cursor: default; height: 40px;margin-right: 30px">
            <span style="font-size: 16px;" onclick="hideDiv('editForm','fade')">关闭</span>
            </div>
             <form id="edit"  method="post" target="frame">
                <div style="font-size: 1.5em;font-weight: bold;color:#202020;margin-bottom: 30px"><span>新增作品集</span><span style="color:#587265;"><b></b></span></div><br/>
                <div><span class="spanWidth">标题</span><input type="text" id = "albumTitle" name = "albumName" style="width: 400px;height: 30px;" placeholder="标题不允许超过9个字符"></div><br/>
                <div><span class="spanWidth">描述</span><input type="text" id = "albumDes" name = "description" style="width: 400px;height: 80px" placeholder="描述不允许超过26个字符"></div><br/>
                <div><span class="spanWidth" id = "span_delete">删除</span><input type="button" class="button" value="删除作品集" id="deleteAlbum" style="width: 200px;height: 50px;border: 1px solid #aeafae;" onclick=""></div><br/>
                <div><input type="submit" id = "addOrUpdate" class="btn" value="保存设置" style="width: 250px;height: 60px;background: #587265;font-size: 1em;color: #fff;"></div>
            </form>
       </div>

        <div id="main">
           <div class="album">
                <div class="inner">
                    <a  href="javascript:void(0)" style="display: inline-block;" >
                    <img class = "addAlbum" src="images/add.png" >
                    <span style="display: inline-block;font-size: 0.8em;color: #666666;margin-left:28%;">新增作品集</span>
                    </a> 
                </div>
           </div>
           <c:forEach var="item" items="${albumList }" varStatus="status" >
            <div id = "div_album" class="album effect-2">
              <div class="member-image">
                <c:if test="${item.albumImage_url != '' }">
                       <a href="ImageServlet?method=queryImagesBA&id=${item.id }&albumImage_url=${item.albumImage_url}&albumName=${item.albumName }">
	                   <img src="<c:url value='${item.albumImage_url }'/>"  style="background: #fff;">
	                   </a>
                </c:if>
                <c:if test="${item.albumImage_url == '' }">
                       <a href="ImageServlet?method=queryImagesBA&id=${item.id }&albumImage_url=${item.albumImage_url}&albumName=${item.albumName }">
	                   <img src="img/username.png"  style="background: #fff;">
	                   </a>
                </c:if>
              </div>
              <div class="member-info">
                   <h3><a href="ImageServlet?method=queryImagesBA&id=${item.id }&albumImage_url=${item.albumImage_url}&albumName=${item.albumName }" id = "a_albumName${status.count}" class = "albumInfo" >${item.albumName }</a></h3>
                   <h5 id = "h_userName" >${user.userName }</h5>
                   <input type = "hidden" id = "album_id${status.count}" value = "${item.id }">
                   <p><a href="ImageServlet?method=queryImagesBA&id=${item.id }&albumImage_url=${item.albumImage_url}&albumName=${item.albumName }" id = "a_description${status.count}"  class = "albumInfo">${item.description }</a></p>
                   <button type="button" class="button grey" onclick="dosomething(${status.count})">编辑</button>
                   </div><div class="social-touch"><a class="fb-touch" href="ImageServlet?method=queryImagesBA&id=${item.id }&albumImage_url=${item.albumImage_url}&albumName=${item.albumName }"></a>
                   <a class="tweet-touch" href="ImageServlet?method=queryImagesBA&id=${item.id }&albumImage_url=${item.albumImage_url}&albumName=${item.albumName }"></a>
                   <a class="linkedin-touch" href="ImageServlet?method=queryImagesBA&id=${item.id }&albumImage_url=${item.albumImage_url}&albumName=${item.albumName }"></a>
               </div>
            </div>
            </c:forEach>
    	</div>
    </div>
    <!--底部  -->
	<div class="Ifooter">
    <p style="margin-left:35%;">
      <span>copyright©2017 </span>
      <span style="margin-left: 20px;">武汉科技大学党委宣传部</span>
    </p>
    <p style="margin-left:30%;">
      <span>电话：027-68862542</span>
      <span style="margin-left: 10px;">传真：027-68862461</span>
      <span style="margin-left: 10px;">邮箱：xcb@wust.edu.cn</span>
    </p>
     <p style="margin-left:37%;"><span>技术支持：武汉科技大学阿豆工作室</span></p>
</div>

<!--登录  注册-->
<!-- 登录注册弹出窗口 -->
<!-- 注册窗口 -->
<div class="event" id="register_box" style="display: none;">
    <div class="register">
      <div class="title">
        <img class="t_txt" src="images/logo.png" style="width: 200px;height:45px; ">
        <span class="del" onClick="deleteRegister()">X</span>
      </div>
    <form  id="" action="" method="post" name="form1">
               <input  placeholder="请输入用户名" name="uid" type="text" id="uid">
               <input  placeholder="请输入密码" name="psw1" type="password" id="psw1">
               <input  placeholder="请确认密码" name="psw2" type="password" id="psw2" >
               <select name="department" onChange="getDeparment()" id="">
                  <option value="0">请选择参赛组织</option>
                  <option value="职能部门">职能部门</option>
                  <option value="学院">学院</option>
               </select>
               <select name="academy" id="">
               <option value="0">请选择参赛单位</option>
               </select>
             
               <input type="text" name="username" id="username" value="" placeholder="姓名"/>
               <input type="text" name="phone" id="phone"  placeholder="联系方式" />
               <input type="submit" name="tregister" id="tregister" value="注册" class="btnR" style="color: #ffffff;" />
             <div><a class="RL" javascript:void(0) onClick="showBox2()">已有账号？去登录</a>
             </div>
            </div>
     </form> 
    </div>
  </div>
  <div class="bg_color" onClick="deleteRegister()" id="bg_filter" style="display: none;"></div>
<!-- 登录弹出窗口 -->
<div class="event" id="login_box" style="display: none;">
    <div class="login">
      <div class="title">
        <img class="t_txt" src="images/logo.png" style="width: 200px;height:45px; ">
        <span class="del" onClick="deleteLogin()">X</span>
      </div>
      <form action="" method="post">
        <input type="text" name="" id="" value="" placeholder="请输入用户名"/>
        <input type="password" name="" id="" value="" placeholder="请输入密码"/>
        <input type="button" name="" id="" value="登录" class="btnR" style="color: #ffffff;" />    
        <div><a class="RL"  javascript:void(0) onClick="showBox3()">还没有账号？去注册</a></div>
      </form> 
    </div>
  </div>
  <div class="bg_color" onClick="deleteLogin()" id="bg_filter" style="display: none;">
  </div>	
  
<!--登录  注册结束-->
<!-- 弹出提示框 -->
<div id="editForm1" class="white_tip">
            <div style="text-align: right; cursor: default; height: 40px;margin-right: 30px">
            <span style="font-size: 16px;" onclick="hideDiv('editForm','fade')">关闭</span>
            </div>
             wenzi
       </div>
</body>
</html>