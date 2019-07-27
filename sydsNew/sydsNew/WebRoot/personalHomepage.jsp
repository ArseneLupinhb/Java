<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" charset="UTF-8" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=decice-width"/>
	<title>个人中心主页</title>
	<link rel="stylesheet" type="text/css" href="css/album.css" />
	<script src="js/style.js"></script>
    <script src="layer/jquery.js?v=1.83.min"></script>
    <script src="layer/layer.min.js"></script>
    <script type="text/javascript">
        //修改用户信息
       /*  $(function () {
            $(".change").unbind('click').click(function () {
                //获取td值
                var td = $(this);
                var txt = td.text();
                var input = "<input type='text' value='" + txt + "' />"; 
                td.html("<input type='text' value='" + txt + "' />");
                input.click(function () {
                	return false; 
                });
                
                //获取焦点
                input.trigger("focus");
                //文本框失去焦点后提交内容，重新变为文本
                input.blur(function () {
                    var newtxt = $(this).val();
                    //判断文本有没有修改
                    if (newtxt != txt) {
                        td.html(newtxt);
                    }
                    else {
                        td.html(newtxt);
                    }
                });
            });
        }); */

      
         $(function () {
            $('.btn').click(function () {
                $('#main').append('<div class="album effect-2"><div class="member-image"><img src="images/album.jpg" style="background: #fff;"></div><div class="member-info"><h3><a href="photoShow.html">${album.albumName }</a></h3><h5>用户甲</h5><p><a href="photoShow.html">${album.description }</a></p><button type="button" class="button grey" onclick="dosomething()">编辑</button></div><div class="social-touch"><a class="fb-touch" href="photoShow.html"></a><a class="tweet-touch" href="photoShow.html"></a><a class="linkedin-touch" href="photoShow.html"></a></div></div>');
                $('#editForm').hide();
                $('#fade').hide();

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
                	 var url = "<c:url value = '/AlbumServlet' />?method=addAlbum";
                     var albumName = $("#albumTitle").val();
                	 var description = $("#albumDes").val();
                	 var params = {"albumName":albumName, "description":description};
                	 $(this).load(url,params,function(data){
                		 var jsonData = eval("("+data+")");
     					 alert(jsonData.message);

                	 });
                	 $('#editForm').hide();
                     $('#fade').hide();
                 });
                 
                 $('.btn').click(function () {
                     $('#main').append('<div class="album effect-2"><div class="member-image"><img src="images/album.jpg" style="background: #fff;"></div><div class="member-info"><h3><a href="photoShow.html">${album.albumName }</a></h3><h5>用户甲</h5><p><a href="photoShow.html">${album.description }</a></p><button type="button" class="button grey" onclick="dosomething()">编辑</button></div><div class="social-touch"><a class="fb-touch" href="photoShow.html"></a><a class="tweet-touch" href="photoShow.html"></a><a class="linkedin-touch" href="photoShow.html"></a></div></div>');
                     $('#editForm').hide();
                     $('#fade').hide();

                 });
                
        	});
        });
        
        //删除相册或修改相册
        function dosomething() {
        	var albumName =  $("[id='a_albumName']").html();
        	var description = $("[id='a_description']").html();
        	var userName = $("[id='h_userName']").html();
        /* 	alert(albumName +" "+ description + " " +userName); */
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
        		 var albumName = $("#albumTitle").val();
            	 var description = $("#albumDes").val();
        		 var params = {"albumName":albumName, "description":description};
            	 $(this).load(url,params,function(data){
            		 var jsonData = eval("("+data+")");
 					 alert(jsonData.message);
            	 });
            	 $('#editForm').hide();
                 $('#fade').hide();
        	 });
        	
        	 //修改相册
        	 $("#addOrUpdate").unbind('click').click(function(){
        		 
            	 var url = "<c:url value = '/AlbumServlet' />?method=alertAlbum";
            	 var albumNameOld = albumName;
                 var albumNameNew = $("#albumTitle").val();
            	 var description = $("#albumDes").val();
            	 var params = {"albumNameOld":albumNameOld, "albumName":albumNameNew, "description":description};
            	 $(this).load(url,params,function(data){
            		 var jsonData = eval("("+data+")");
 					 alert(jsonData.message);
            	 });
            	 $('#editForm').hide();
                 $('#fade').hide();
             });
        }
        
        /* $(function(){
    	$(".albumInfo").bind("click",function(){
    		alert("haha");
    	});
    } */
        
    </script>
</head>
</head>
<body>

    <div id="fade" class="black_overlay"></div>
    <div id="editForm" class="white_content">
            <div style="text-align: right; cursor: default; height: 40px;margin-right: 30px">
            <span style="font-size: 16px;" onclick="hideDiv('editForm','fade')">关闭</span>
            </div>
             <form id="edit"  method="post" target="frame">
                <div style="font-size: 1.5em;font-weight: bold;color:#202020;margin-bottom: 30px"><span>新增作品集</span><span style="color:#587265;"><b></b></span></div><br/>
                <div><span class="spanWidth">标题</span><input type="text" id = "albumTitle" name = "albumName" style="width: 400px;height: 30px;"></div><br/>
                <div><span class="spanWidth">描述</span><input type="text" id = "albumDes" name = "description" style="width: 400px;height: 80px"></div><br/>
                <div><span class="spanWidth" id = "span_delete">删除</span><input type="button" class="button" value="删除作品集" id="deleteAlbum" style="width: 200px;height: 50px;border: 1px solid #aeafae;" onclick=""></div><br/>
                <div><input type="submit" id = "addOrUpdate" class="btn" value="保存设置" style="width: 250px;height: 60px;background: #587265;font-size: 1em;color: #fff;"></div>
            </form>
            
    </div>
    <div id="container">
    	<div class="banner">
    	   <div class="userpic"><img src="images/username.png"></div>
    	   <div class="banner-context">
    	       <div class="banner-head">
    	   	      <div class="banner-username"><b id="t" class="change">用户名甲</b></div>
    	   	      <!--  <div class="banner-button"><button type="button" id="click" class="button gray" >编辑</button></div> -->
                  <!-- <script type="text/javascript">
                        document.getElementById("click").onclick=function(){
                            var text=document.getElementById("t");
                            var val=text.innerHTML;
                            text.innerHTML="<input type='text' id='n' value='"+val+"' />";
                            document.getElementById("n").addEventListener("blur",function(e){
                            text.innerHTML=document.getElementById("n").value;
                            });
                        };
                  </script> -->
    	   	    </div>
    	   	   <div class="banner-userinfor">
    	   	      <table>
                        <tr>
                            <td>学院：</td> 
                            <td class="change">管理学院</td>
                            <td>真实姓名：</td> 
                            <td class="change">张三</td>
                        </tr>
                        <tr>
                            <td>专业班级：</td> 
                            <td class="change">信息管理与信息系统1401</td>
                            <td>联系电话：</td> 
                            <td class="change">13412345678</td>
                        </tr>
                   </table>
    	   	   </div> 
    	   </div>
    	   <div class="tabs">
    	   	   <div class="tab"><b>${sessionScope.countAlbum }作品集</b></div>
    	   	   <div class="tab">11照片</div>
    	   	   <div class="tab">12赞</div>
    	   </div>
    	</div>

        <div id="main">
           <div class="album">
                <div class="inner">
                <!-- onclick="popDiv('editForm','fade')" -->
                    <a  href="javascript:void(0)" style="display: inline-block;" >
                    <img class = "addAlbum" src="images/add.png" >
                    <span style="display: inline-block;font-size: 0.8em;color: #666666;margin-left:28%;">新增作品集</span>
                    </a> 
                </div>
           </div>
           <c:forEach var="item" items="${albumList }">
            <div id = "div_album" class="album effect-2">
              <div class="member-image">
              <img src="images/album.jpg" style="background: #fff;"></div>
              <div class="member-info">
                   <h3><a href="ImageServlet?method=queryImagesByUser&album_id=${item.id }" id = "a_albumName" class = "albumInfo" >${item.albumName }</a></h3>
                   <h5 id = "h_userName" >从session中获取</h5>
                   <p><a href="ImageServlet?method=queryImagesByUser&album_id=${item.id }" id = "a_description" class = "albumInfo">${item.description }</a></p>
                   <button type="button" class="button grey" onclick="dosomething()">编辑</button>
                   </div><div class="social-touch"><a class="fb-touch" href="ImageServlet?method=queryImagesByUser&album_id=${item.id }"></a>
                   <a class="tweet-touch" href="ImageServlet?method=queryImagesByUser&album_id=${item.id }"></a>
                   <a class="linkedin-touch" href="ImageServlet?method=queryImagesByUser&album_id=${item.id }"></a>
               </div>
            </div>
            </c:forEach>
    	</div>
    </div>
	
</body>
</html>