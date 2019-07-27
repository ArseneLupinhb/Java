<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> <html> 
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>第五届“和谐校园”摄影大赛平台</title> 
<!--   <meta name="description" content=""> 
  <meta name="keywords" content=""> --> 
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script> 
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/carousel.css">
  <script type="text/javascript" src="js/carousel.js"></script> 
  <link rel="stylesheet" type="text/css" href="css/index.css">
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <!-- 登录注册弹出窗口 -->
  <link rel="stylesheet" type="text/css" href="css/tanchuchuangkou.css"/>
  <link rel="stylesheet" type="text/css" href="css/index.css">
  <script src="js/tanchuchuangkou.js" type="text/javascript" charset="utf-8"></script>
  <script src="js/jquery-1.9.0.min.js" type="text/javascript" charset="utf-8"></script>   
  <script src="js/jQuery-placeholder.js" type="text/javascript" charset="utf-8"></script>
  
  <script src="js/style.js" type="text/javascript" charset="utf-8"></script> 
<script src="js/loginAndRegiste.js" type="text/javascript"></script>
<script type="text/javascript">
function praise(index) {
 	
    var album_id = $("#"+index).attr("id");
    var isPraise = false;
    
    if ($("#praise"+index).attr("src") != "images/dianzan1.png" ){
		   isPraise = true;
		   $("#praise"+index).attr("src","images/dianzan1.png") ;
	   }else{
		   isPraise = false;
		   $("#praise"+index).attr("src","images/like.png") ;
	   }
       var params = {"album_id":album_id, "isPraise":isPraise};
	   $.ajax({
		   
			"url":"<c:url value = '/PraiseServlet' />?method=praiseAlbum",
			"data":params,
			"type":"POST",
			"success":function(data){
			},
			"error":function(){
			},
			"dataType":"json"
		});
    
 }

//搜索
$(document).ready(function() {
	
	$('#search_id').bind('click', function() {
				searchStr = $("#searchStr").val();
				if (searchStr == "") {
					alert("请输入关键词");
					return;
				}
				window.location="GetAlbumsByPage?condition="+searchStr;
			});
	
	$('#searchStr').bind('keydown', function(e) {
	      if (e.keyCode == 13) {
	      	searchStr = $("#searchStr").val();
	  		if (searchStr == "") {
	  			alert("请输入关键词");
	  			return;
	  		}
	  		window.location="GetAlbumsByPage?condition="+searchStr;
	      }
	  });
	});

</script>
   </head> 
   <body> 
  
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
      <input type="text" class="form-control" name="" id="searchStr" placeholder="请输入搜索内容"> 
      <button class="btn btn-success" style="margin-right: 30px;" id = "search_id">搜索</button>
       <a class="navbar-link" javascript:void(0) id = "register_id" onClick="showBox1()">注册</a> 
       <a class="navbar-link" javascript:void(0) id = "login_id" onClick="showBox()">登录 </a> 
       <a class="navbar-link" id = "showUserName" >${sessionScope.user.userName}</a>
       <a class="navbar-link" type = "hidden" style="cursor:pointer" id = "quit">退出</a>
       </div> 
       </div>
        </div> 
        </nav>
    <!-- 轮播 -->     
 <div id="container">
    <div id="list" style="left: 0px;">
        <img src="images/carsouel11.jpg" alt="1"/>
        <img src="images/carsouel22.jpg" alt="2"/>
        <img src="images/carsouel33.jpg" alt="3"/>
        <img src="images/carsouel44.jpg" alt="4"/>
        <img src="images/carsouel33.jpg" alt="5"/>
    </div>
    <div id="buttons">
        <span index="1" class="on"></span> <!--  buttons[0] -->
        <span index="2"></span>
        <span index="3"></span>
        <span index="4"></span>
        <span index="5"></span>
    </div>
    <div id="prev" class="arrow">&lt;</div>
    <div id="next" class="arrow">&gt;</div>
</div>
<!-- 登录注册弹出窗口 -->
<!-- 注册窗口 -->
<div class="event" id="register_box" style="display: none;">
    <div class="register">
      <div class="title">
        <img class="t_txt" src="images/logo.png" style="width: 200px;height:45px; ">
        <span class="del" onClick="deleteRegister()">X</span>
      </div>
    <form  id="" action="" method="post" name="form1" class="FRL">
                <input  placeholder="请输入用户名" name="userName" type="text" id="userName" >
               <input  placeholder="请输入密码" name="password" type="password" id="password">
               <input  placeholder="请确认密码" name="passwordAgain" type="password" id="passwordAgain" >
               <select name="department" onChange="getDeparment()" id="department">
                  <option value="0">请选择参赛组织</option>
                  <option value="职能部门">职能部门</option>
                  <option value="学院">学院</option>
               </select>
               <select name="academy" id="academy">
               <option value="0">请选择参赛单位</option>
               </select>
             
               <input type="text" name="realName" id="realName"  value="" placeholder="姓名"/>
               <input type="text" name="cellnumber" id="cellnumber"  placeholder="联系方式" />
               <input type="submit" name="tregister" id="tregister" onclick="checkCellNumber()" value="注册" class="btnR" style="color: #ffffff;" />
             <div><a class="RL" javascript:void(0) onClick="showBox2()">已有账号？去登录</a>
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
      <form action="" method="post"  class="FRL">
        <input type="text" name="userName" id="userName1" value="" placeholder="请输入用户名"/>
        <input type="password" name="" id="password1" value="" placeholder="请输入密码"/>
        <input type="button" name="password"  id = "login" value="登录" class="btnR" style="color: #ffffff;" />    
        <div><a class="RL"  javascript:void(0) onClick="showBox3()">还没有账号？去注册</a></div>
      </form> 
    </div>
  </div>
  <div class="bg_color" onClick="deleteLogin()" id="bg_filter" style="display: none;"></div>

<!-- 图片展示部分 -->
 <div id="container2">
 
    <div class="picContent">
<div class="imgs" id="div_albums">
				<c:forEach items="${albums}" var="item">
					<div class="album effect-2">
						<div class="member-image">
							<img id="${item.id}" 
								src="<c:url value='${item.albumImage_url }'/>">
						</div>
						<div class="member-info">
							<h3>
								${item.albumName}<img src="img/like.png" id = "praise${item.id}" onClick = "praise(${item.id})" 
									style="width: 22px;height: 22px;margin-left: 4px;">
							</h3>
							<h5>${item.userName}</h5>
							<%-- <p style="text-align: center;">${item.description}</p> --%>

							<div class="social-touch">
								<div class="fb-touch"></div>
								<div class="tweet-touch"></div>
								<div class="linkedin-touch"></div>

							</div>
						</div>
					</div>
				</c:forEach>

			</div>
    
      <div id="fade" class="black_overlay"></div>
      
      

    <!-- 相册里的照片 -->
			<div id="editForm" class="white_content">
				<div class="imgs" id="div_imgs"></div>
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
		     <img id="image_first" src="" />
          </div>
        </div>
              
        <div class="photo-right">
          <div class="arrow-next"></div>
        </div>
      </div>
    </div>
    </div>
  </div>
 
  <script src="js/jquery.min.js"></script>
  <script>
    var img_index = 0;
    var img_src = "";
    var num=0;
 //  for(var i=0;i<4;i++){


    $(function() {
		//计算居中位置
		var mg_top = ((parseInt($(window).height()) - parseInt($(
				".photo-div").height())) / 2);

		$(".photo-div").css({
			"margin-top" : "" + mg_top + "px"
		});
		//关闭
		$(".photo-close").click(function() {
			$(".photo-mask").hide();
			$(".photo-panel").hide();
		});
		//下一张
		$(".photo-panel .photo-div .arrow-next")
				.click(
						function() {
							if (img_index == -1) {
								img_index = 1;
							} else {
								img_index++;
							}
							if (img_index >= $("#editForm .imgs .album img").length) {
								img_index = 0;
							}
							img_src = $("#editForm .imgs .album img").eq(
									img_index).attr("src");
							photoView($("#editForm .imgs .album img"));
						});
		//上一张
		$(".photo-panel .photo-div .arrow-prv")
				.click(
						function() {
							img_index--;

							if (img_index < 0) {
								img_index = $("#editForm .imgs .album img").length - 1;
							}
							img_src = $("#editForm .imgs .album img").eq(
									img_index).attr("src");
							photoView($("#editForm .imgs .album img"));
						});
		//如何调用？展示相册的图片
		$(".effect-2 .member-image img").click(
				function() {
					var albumId = $(this).attr("id");
					$.ajax({
						type : "POST",
						url : "GetImgsByPage", //根据相册id获取相册图片
						data : {
							"albumId" : albumId
						//需要获取照片的相册的id
						},
						success : function(data) {

							var imgs = eval("(" + data + ")");
							//alert("相册照片数量："+imgs.length);
							var str = "";
							var p = document.getElementById("div_imgs");
							for ( var i = 0; i < imgs.length; i++) {
								var item = imgs[i];
								//alert(i);
								str += "<div class=\"album\"><img src='"
										+ item.image_url.substring(1,
												item.image_url.length)
										+ "'></div>";
							}
							p.innerHTML = str;
							var item = imgs[0];
							$("#image_first").attr(
									"src",
									item.image_url.substring(1,
											item.image_url.length));
							$(".photo-mask").show();
							$(".photo-panel").show();
							img_src = $(this).attr("src");
							img_index = $(this).index();
							photoView($(this));
						}
					});
				});
	});

	//自适应预览
	function photoView(obj) {
		if ($(obj).width() >= $(obj).height()) {
			$(".photo-panel .photo-div .photo-img .photo-view-h").attr(
					"class", "photo-view-w");
			$(".photo-panel .photo-div .photo-img .photo-view-w img").attr(
					"src", img_src);
		} else {
			$(".photo-panel .photo-div .photo-img .photo-view-w").attr(
					"class", "photo-view-h");
			$(".photo-panel .photo-div .photo-img .photo-view-h img").attr(
					"src", img_src);
		}
		//此处写调试日志
		console.log(img_index);
	} 
</script>


<!-- 分页 -->
	<div class="tcdPageCode">
		<c:forEach begin="${currentPage-2<0? 1:currentPage-2}"
			end="${currentPage+3}" var="item" step="1">
			<c:if test="${item>0&&item<=totalPage}">
				<c:if test="${item==currentPage}">
					<a href="GetAlbumsByPage?currentPage=${item}"><span
						class="current">${item}</span>
					</a>
				</c:if>
				<c:if test="${item!=currentPage}">
					<a href="GetAlbumsByPage?currentPage=${item}" class="tcdNumber">${item}</a>
				</c:if>
			</c:if>
		</c:forEach>
	</div>

<!-- 底部 -->
<div class="Ifooter" >
	<p style="margin-left:35%;">
	  <span>copyright©2017 </span>
	  <span style="margin-left: 20px;">武汉科技大学党委宣传部</span>
	</p>
	<p style="margin-left:30%;">
	  <span>电话：027-68862542</span>
	  <span style="margin-left: 10px;">传真：027-68862461</span>
	  <span style="margin-left: 10px;">邮箱：xcb@wust.edu.cn</span>
	</p>
	 <p style="margin-left:37%;">
	     <span>技术支持：武汉科技大学阿豆工作室 <a href="<c:url value = '/Back/login.jsp' />">管理</a> </span>
	     
	 </p>
</div>
 </body> 
 </html>
