<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" charset="UTF-8" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=decice-width"/>
	<title>照片展示</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script src="js/style.js"></script>
	<script src="js/jquery-3.1.1.min.js"></script>
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
    
      //修改或删除图片
        function alertOrDelete(index){
          	 var album_id = $("#album_id").val();
          	 var url = "<c:url value = '/ImageServlet' />?method=deleteImage";
     		 var image_id = $("#image_id"+index).val();
     		 var image_url = $("#id_image_url"+index).val();
     		 var params = {"image_id":image_id,"image_url":image_url};
         	 $(this).load(url,params,function(data){
         		 var jsonData = eval("("+data+")");
         	 });
         	setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
         		window.location.reload();//页面刷新
         		},100);
        };
        
    </script>

    <!-- 导航栏 -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script> 
  <!-- 登录注册弹出窗口 -->
  <link rel="stylesheet" type="text/css" href="css/tanchuchuangkou.css"/>
  <script src="js/tanchuchuangkou.js" type="text/javascript" charset="utf-8"></script>
  <script src="js/jQuery-placeholder.js" type="text/javascript" charset="utf-8"></script>
  <link rel="stylesheet" type="text/css" href="css/index.css"/>
  <style type="text/css">
  .imgs .album img{
  width: 90%;
  height: 80%;
  margin: 5% 5% 0 5%;
  position: relative;
  text-decoration: none;
  display: inline-block;
  background-position:center center;
  background-repeat: no-repeat;
  
  background-size:cover;
  -moz-background-size:cover;
  -webkit-background-size:cover;
}
  </style>

</head>
</head>
<body>
<div class="header">
   <img src="img/title.jpg" style="width: 100%;height: auto;">
 </div>
<!--  导航栏 -->
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
     <!--  <input type="text" class="form-control" name="" placeholder="请输入搜索内容"> 
      <button class="btn btn-success" style="margin-right: 30px;">搜索</button> -->
       <!-- <a class="navbar-link" javascript:void(0) onClick="showBox1()">注册</a> 
       <a class="navbar-link" javascript:void(0) onClick="showBox()">登录 </a>  -->      
        <a class="navbar-link" id = "showUserName" >${sessionScope.user.userName}</a>
       <a class="navbar-link" type = "hidden" style="cursor:pointer" id = "quit">退出</a>
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
    	   	   <div class="tab"><a href=""><b>${sessionScope.countImageByAlbum }照片</b></a></div>
    	   	   <div class="tab">${sessionScope.countPraise }赞</div>
    	   </div>
    	</div>

        <!-- 点击作品集封面上的“编辑按钮”，弹出修改窗口 -->
        
        


    <!--照片的编写-->

        <div id="main">
           <div class="album">
                <div class="inner">
                    <a href="uploadPic.jsp" style="display: inline-block;" >
                    <img src="img/add.png">
                    <span style="display: inline-block;font-size: 0.8em;color: #666666;margin-left:15%;width: 100px;height: auto;text-align: center;">新增照片</span>
                    </a>
                </div>
            </div>

           
                
            <div class="imgs">
                 <c:forEach var="image" items="${images }" varStatus="status" >
                    <div class="album">
                        <img onclick = "showDetail(${status.count})"  value = ${image.image_url } src="<c:url value='${image.image_url }'/>">
                        <input type = "hidden" id = "id_image_url${status.count}" value = "${image.image_url }">
                        <input type = "hidden" id = "image_id${status.count}" value = "${image.id }">
                        <input type = "hidden" id = "album_id" value = "${sessionScope.album.id }">
                        <button  id = "alert" type="button" class="button gray" onclick= "alertOrDelete(${status.count})">删除</button>  
                    </div> 
                </c:forEach>    
                    <!-- <div class="album">
                    <img src="img/photo4.jpg">
                    <button type="button" class="button gray" onclick="popDiv('editForm','fade')">编辑</button></div>  -->
                </div>

        </div>
  
                
        <div class="photo-mask"></div>
        <div class="photo-panel">
            <div class="photo-div">
               <div class="photo-left">
                    <div class="arrow-prv"></div>
               </div>
               <div class="photo-img">
                    <div class="photo-bar">
                        <div class="photo-close"></div>
                    </div>
            
                    <div class="photo-view-h">
                        <img src="http://b.zol-img.com.cn/sjbizhi/images/9/800x1280/1471524533521.jpg" />
                    </div>
               </div>
               
               <div class="photo-right">
                    <div class="arrow-next"></div>
               </div>
            </div>
        </div>

    </div>
           
           
<script src="js/jquery.min.js"></script>
<script>
    var img_index = 0;
    var img_src = "";

    $(function() {
        //计算居中位置
        var mg_top = ((parseInt($(window).height()) - parseInt($(".photo-div").height())) / 2);

        $(".photo-div").css({
            "margin-top": "" + mg_top + "px"
        });
        //关闭
        $(".photo-close").click(function() {
            $(".photo-mask").hide();
            $(".photo-panel").hide();
        });
        //下一张
        $(".photo-panel .photo-div .arrow-next").click(function() {
            img_index++;
            if(img_index >= $(".imgs .album img").length) {
                img_index = 0;
            }
            img_src = $(".imgs .album img").eq(img_index).attr("src");
            photoView($(".imgs .album img"));
        });
        //上一张
        $(".photo-panel .photo-div .arrow-prv").click(function() {
            img_index--;
            if(img_index < 0) {
                img_index = $(".imgs .album img").length - 1;
            }
            img_src = $(".imgs .album img").eq(img_index).attr("src");
            photoView($(".imgs .album img"));
        });
        //如何调用？
        $(".imgs .album img").click(function() {

            $(".photo-mask").show();
            $(".photo-panel").show();
            img_src = $(this).attr("src");
            img_index = $(this).index();
            photoView($(this));
        });

    });
    //自适应预览
    function photoView(obj) {
        if($(obj).width() >= $(obj).height()) {
            $(".photo-panel .photo-div .photo-img .photo-view-h").attr("class", "photo-view-w");
            $(".photo-panel .photo-div .photo-img .photo-view-w img").attr("src", img_src);
        } else {
            $(".photo-panel .photo-div .photo-img .photo-view-w").attr("class", "photo-view-h");
            $(".photo-panel .photo-div .photo-img .photo-view-h img").attr("src", img_src);
        }
        //此处写调试日志
        console.log(img_index);
    }
</script>

    <!-- 底部 -->
<div class="Ifooter" style="margin-top: 20px;">
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
  <div class="bg_color" onClick="deleteLogin()" id="bg_filter" style="display: none;"></div>	
	
</body>
</html>
