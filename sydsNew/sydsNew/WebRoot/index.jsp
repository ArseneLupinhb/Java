<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<title>摄影平台欢迎你</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
	  <link rel="stylesheet" type="text/css" href="css/index.css">
<!-- 照片 -->
    <link rel="stylesheet" type="text/css" href="css/zStyle.css" />
    <script type="text/javascript" src="js/jquery.easing.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<!-- 分页CSS-->
    <link rel="stylesheet" type="text/css" href="css/paging.css">
<!-- 登录注册 -->
    <link rel="stylesheet" type="text/css" href="css/tanchuchuangkou.css"/>
    <script src="js/tanchuchuangkou.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/jquery-1.7.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/jquery-1.9.0.min.js" type="text/javascript" charset="utf-8"></script>    
   
</head>
<body onload="clipInit()">
    <!-- 头部 -->
    <div class="header">
        <div class="logo">
            <img src="images/logo.png" style="width: 200px;height: 45px;">
        </div>
        <div class="navbar">
            <li><a href="index.html">首&nbsp;&nbsp;&nbsp;页</a></li>
            <li><a href="">大赛简介</a></li>
            <li><a href="">作品上传</a></li>
            <li><a href="">个人中心</a></li>
             ${user.userName }
        </div>
        <div class="wrapper">
            <input type="text" name="search" id="">
            <div class="research" id=""></div>
            <div class="btn">
              <button  alt="" name="register" style="background-color: #3e4839;color: #fff" javascript:void(0) onClick="showBox1()">
              注册</button>
              <button alt="" name="login" javascript:void(0) onClick="showBox()">登录</button>
            </div>
        </div>
       <!--  如果已登录 -->
      <!--  <div class="wrapper">
            <input type="text" name="search">
            <div class="research"></div>
            <div class="customer">
              <img src="images/yonghu.png" style="margin-left: 20px;">
               用户名
            </div>
        </div> -->
       <!---->
    </div>
    <!-- 头部结束 -->
    <div class="banner">
    	<img src="images/banner.jpg" style="width:100%;height: 85px;">
    </div>
    <!-- 主体图片展示部分 -->
 <div class="picBox">
  <ul class="picL" id="picLsy" >
    <li>
      <a href="#"><img src="images/photo1.jpg" alt="" width="200" height="150"/>
      <div class="author">2017-03-14 作者：李三</div>
      </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
       <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">《猜图学科学》：这么是什么动物？不是松鼠！摄影平台欢迎您！摄影平台欢迎您！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo2.jpg" alt="" width="200" height="150"/>
      <div class="author">2017-03-14 作者：李三</div>
      </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a  href="#">摄影平台欢迎您！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo3.jpg" alt="罐装车撞扁小轿车场面真惨 人竟然没事" width="200" height="150" border="0" />
       <div class="author">2017-03-14 作者：李三</div>
      </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a  href="#">摄影平台欢迎您！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo4.jpg" alt="组图：长腿白嫩女神的床上短裙诱惑" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
      </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">摄影平台欢迎您！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo5.jpg" alt="" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
       </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">摄影平台欢迎您！ </a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo6.jpg" alt="" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
       </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a  href="#">摄影平台欢迎您！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo1.jpg" alt="" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
       </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">摄影平台欢迎您！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo3.jpg" alt="宋美龄的后半生" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
       </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">宋摄影平台欢迎您！摄影平台欢迎您！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo6.jpg" alt="" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
       </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">摄影平台欢迎您！摄影平台欢迎您！风情 </a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo2.jpg" alt="" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
       </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">摄影平台欢迎您！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo3.jpg" alt="" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
       </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">摄影平台欢迎您！！</a></p>
      </div>
    </li>
    <li>
      <a href="#"><img src="images/photo5.jpg" alt="" width="200" height="150"/>
       <div class="author">2017-03-14 作者：李三</div>
       </a>
      <div class="text">
        <b><img src="images/dianzan.png" >&nbsp;&nbsp;点赞</b>
        <!--  如果点击图片已点赞 -->
        <!-- <b><img src="images/dianzan1.png" >&nbsp;&nbsp;点赞</b> -->
        <p><a href="#">摄影平台欢迎您！摄影平台欢迎您！！</a></p>
      </div>
    </li>
  </ul>
</div>
<!-- 分页 -->
 <div id="pageToolbar" style="width: 100%;height: 70px;margin-top: 880px;"></div>
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="js/query.js"></script>
<script type="text/javascript" src="js/paging.js"></script>
<script>
	$('#pageTool').Paging({pagesize:10,count:100,callback:function(page,size,count){
	  console.log(arguments)
	  alert('当前第 ' +page +'页,每页 '+size+'条,总页数：'+count+'页');
	  }});
	$('#pageToolbar').Paging({pagesize:10,count:85,toolbar:true});
</script>
<!-- 底部开始线 -->
<div class="footer"> 
    <div class="Ifooter">
      <p style="margin-left:35%;">
      <span>copyright©2017 </span>
      <span style="margin-left: 20px;">武汉科技大学党委宣传部</span>
      </p>
      <p style="margin-left:30%;">
      <span>电话：027-68862542</span>
      <span style="margin-left: 10px;">传真：027-68862461</span>
      <span style="margin-left: 10px;">邮箱：web@wust.edu.cn</span>
      </p>
      <p style="margin-left:37%;"><span>技术支持：武汉科技大学阿豆工作室</span></p>
    </div>
  </div>
<!-- 底部结束线 -->

<!-- 注册窗口 -->
<div class="event" id="register_box" style="display: none;">
    <div class="register">
      <div class="title">
        <img class="t_txt" src="images/logo.png" style="width: 200px;height:45px; ">
        <span class="del" onClick="deleteRegister()">X</span>
      </div>
      <form action="<c:url value = '/UserServlet' />" method="post" name="form1">
        <input type="hidden" name="method" value="register" />
        <input type="text" name="userName" id="userName" placeholder="请输入用户名" onblur="checkUserName()" />
        <input type="password" name="password" id="password" value="" placeholder="请输入密码" />
        <input type="password" name="passwordAgain" id="passwordAgain" value="" placeholder="请确认密码" onblur="checkPwd()"/>
        <select name="department" onChange="getDeparment()" id="department">
            <option value="0">请选择参赛组织</option>
            <option value="职能部门">职能部门</option>
            <option value="学院">学院</option>
        </select>
        <select name="academy" id="academy">
            <option value="0">请选择参赛单位</option>
        </select>
     
        <input type="text" name="realName" id="realName" value="" placeholder="姓名" onblur="realName()"/>
        <input type="text" name="cellnumber" id="cellnumber" value="" placeholder="联系方式"/>
        <input type="button" name="" id="" value="注册" class="btnR" onclick="checkCellNumber()"/>
        <div><a href="" style="float: left;width: 200px;height: 20px;" javascript:void(0) onClick="showBox2()">已有账号？去登录</a></div>
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
      <form action="<c:url value = '/UserServlet' />" method="post">
        <input type="hidden" name="method" value="login" />
        <input type="text" name="userName" id="userName" value="" placeholder="请输入用户名"/>
        <input type="password" name="password" id="password" value="" placeholder="请输入密码"/>
        <input type="button" name="doSubmit" value="登录" class="btnR" onclick="test()"/>    
        <div><a href="" style="float: left;width: 200px;height: 20px;" javascript:void(0) onClick="showBox3()">还没有账号？去注册</a></div>
      </form> 
    </div>
  </div>
  <div class="bg_color" onClick="deleteLogin()" id="bg_filter" style="display: none;"></div>

<!-- 图片悬停漂浮JS -->
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easing.min.js"></script>
<script type="text/javascript">
	//登录的校验
	function test(){
	
		var name1 = document.getElementById('userName');
		var password1 = document.getElementById("password");
		
		var userName = name1.value;
		var password = password1.value;
		if (userName.length <= 0) {
			alert("请输入用户名");
			name1.focus();
			return false;
		} else if (password.length <= 0) {
			alert("密码未填写");
			password1.focus();
			return false;
		}else{
			$.ajax({
				       type: "POST",      
				       url: '<%=path%>/UserServlet?method=login',      
				       data: {'userName':userName,
				       		  'password':password 
				       },
				       success: function(data){
 
						   if(data == 'false'){
							   alert("用户名或密码错误，请重新输入！");
							   $("#userName").val("");
							   $("#userName").focus();
							}else if(data == 'true' ){
								window.location.href = "index.jsp";
							}else if(data == 'logining'){
								alert("您已经登录");
								window.location.href = "index.jsp";
							}
			       		}            
			   });     
		}
	}
	
	function realName(){
   		var nei = document.getElementById("realName").value;
   		if(nei.length<=0){
   			alert("姓名不能为空");
   			$("#realName").val("");
		    $("#realName").focus();
   		}
   		
		
	}
	//注册的用户名检验
	function checkUserName(){  
			var userName=document.getElementById("userName").value;  
			if(userName == "")  
			{  
			   return;  
			}  
			$.ajax({
				       type: "POST",      
				       url: '<%=path%>/UserServlet?method=checkUserName',      
				       data: {'userName':userName},
				       success: function(data){  
						   if(data == 'true'){
							   alert("用户名已经存在，请重新输入！");
							   $("#userName").val("");
							   $("#userName").focus();
							   
						   }
			       	   }            
			       });     
        }  
        
        //两次密码输入是否一致的校验
        function checkPwd()
    	{
    		var password1 = document.getElementById('password').value;
			var password2 = document.getElementById('passwordAgain').value;
			if(password1 != password2){
				alert("两次密码输入不一致！！");
			}
			/*
    		if($("#password").val()!=$("#passwordAgain").val()&&$("#passwordAgain").val()&&$("#password").val()!="")
    			$("#message").text("两次输入的密码不一致！").css({"color":"red"});
    		else $("#message").text("");
    		*/
    	}
    
   		 //对联系方式的校验
   		 function checkCellNumber(){
   		 
    		var objApplicantCell = document.getElementById("cellnumber");
    		if(objApplicantCell != null)  
    		{  
    		    var cellNum = document.getElementById("cellnumber").value; 
    		    var rex=/^1[3-8]+\d{9}$/;
    		    var rex2=/^([0-9]{3,4}-)?[0-9]{7,8}$/;
				//var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
    		    if( cellNum == "")  
    		    {  
    		    	alert("联系方式不能为空！");
    		        //document.getElementById("flag").value="手机号码不能为空";  
    		    	 $("#cellnumber").val("");
					 $("#cellnumber").focus();
    		    }  
				else if(!rex.test(cellNum)){
					if(!rex2.test(cellNum)){
						alert("请输入正确的联系方式！");
						//$("#message_phone").text("请输入正确的联系方式！").css({"color":"red"});
						$("#cellnumber").val("");
						$("#cellnumber").focus(); 
					}
					//else $("#message_phone").text("");
				}
	
    		}  
    				var userName = document.getElementById("userName").value;
					var password =  document.getElementById("password").value;
					var department =  document.getElementById("department").value;
					var academy =  document.getElementById("academy").value;
					var realName =  document.getElementById("realName").value;
					var cellnumber =  document.getElementById("cellnumber").value;
					if(realName.length<=0){
						alert("真实姓名不能为空！");
						$("#realName").val("");
						$("#realName").focus(); 
					}else if(userName.length<=0){
						alert("用户名不能为空！");
						$("#userName").val("");
						$("#userName").focus(); 
					}else{
						$.ajax({
					       type: "POST",      
					       url: '<%=path%>/UserServlet?method=register',      
					       data: {'userName':userName,
					       		  'password':password,
					       		  'department':department,
					       		  'academy' : academy,
					       		  'realName':realName,
					       		  'cellnumber':cellnumber
					       		  
					       },
					       success: function(data){
	 
							   if(data == 'true'){
								   alert("注册成功！");
								   deleteRegister();
								   showBox();
								}
				       		}            
				  		 });  
			  	}   
    		
    	}
    
	$(document).ready(function(){
	
	  $("#picLsy li").hover(function(){
	    $(this).find('.text:not(:animated)').animate({top:"0px"}, {easing:"easeInOutExpo"}, 50, function(){});
	  },function () {
	    $(this).find('.text').animate({top:"250px"}, {easing:"easeInOutExpo"}, 50, function(){});
	  });
	
	});

</script>


</div>
</body>
</html>