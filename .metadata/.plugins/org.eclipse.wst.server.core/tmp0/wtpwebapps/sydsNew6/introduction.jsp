<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>大赛简介</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
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
  <a class="navbar-brand"> 
    第五届和谐校园摄影大赛
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
    <li class="active"><a href="introduction.jsp">大赛简介</a></li> 
    <li><a href="<c:url value = '/AlbumServlet' />?method=queryAlbum">个人中心</a></li>
     </ul>
      <div class="navbar-form navbar-right" style="margin-right: 30px;"> 
      <!-- <input type="text" class="form-control" name="" placeholder="请输入搜索内容"> 
      <button class="btn btn-success" style="margin-right: 30px;">搜索</button> -->
       <a class="navbar-link" javascript:void(0) id = "register_id" onClick="showBox1()">注册</a> 
       <a class="navbar-link" javascript:void(0) id = "login_id" onClick="showBox()">登录 </a> 
       <a class="navbar-link" id = "showUserName" >${sessionScope.user.userName}</a>
       <a class="navbar-link" type = "hidden" id = "quit">退出</a>
       </div> 
       </div>
        </div> 
        </nav>
    
<!-- 简介 -->
<div class="center">
<div class="bkground">
</div>

<!-- 主题大赛介绍文字 -->
<div class="Itext">
	<p>全体师生员工：</p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;阳春三月，大地回春，美丽的校园内处处洋溢着清新活泼的气息。“春有桃李樱，夏有荷香闻，秋有桂花看，冬有梅花景”学校优美的校园环境得到了在校师生和社会人士的一致好评。在优美的校园环境和优良的教风、学风的熏陶下，师生的精神面貌广受称道。和谐、美丽，已成为校园生活的主旋律。</p>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用镜头发现美好，记录身边的精彩瞬间。值黄家湖入住十年之际，党委宣传部面向广大摄影爱好者，举办第四届漫步在绿树成荫的校园时，我们心旷神怡。感悟四季的美好时，我们有花作伴-“春有桃李樱、夏有荷香闻、秋有桂花香、主题：以“和谐.美丽”为主题，神怡。主题：以“和谐.美丽”为主题，神怡。主题：以“和谐.美丽”为主题，神怡。</p>
    <p>一、比赛主题：以“和谐•美丽”为主题，参赛者通过自己的慧眼，用镜头记录我校优美的校园风光和精彩的校园生活。</p>
    <p>二、参赛对象：全校师生员工</p>
    <p>三、征稿日期：2017年3月1日——2017年12月20日</p>
    <p>四、参赛作品要求：</p>
    <ol class="">
      <li>参赛作品必须是2017年内新拍摄的作品。</li>
	    <li>每张照片的像素不低于500万，大小1M以上；不接受纸质照片。</li>
	    <li>参赛作品请自拟标题，并另附word文档注明拍摄地点、作者个人简介及有效联系方式。</li>
    </ol>
    <p>五、参赛方式：参赛者可任选一种方式提交参赛作品。</p>
    <ol class="">
      
      <li>请登录“第五届和谐校园摄影大赛”专题网站<a href="###" >http://adou.wustnews.com:8080/syds2017/</a>,注册后上传作品。</li>
      <li>请发送到电子邮箱：xcb@wust.edu.cn，并在邮件主题中注明“第五届‘和谐校园’摄影比赛参赛作品”。</li>
      
    </ol>
    <p>六、评选方式：</p>
    <ol class="">
	    <li>党委宣传部邀请摄影专家组成评审委员会对参赛作品进行评选。</li>
	    <li>奖项设置：一等奖1名，二等奖2名，三等奖5名，优秀奖10名
      </li>
	    <li>每月遴选部分优秀参赛作品在校园网或官方微信等媒体展出。</li>
	    <li>2017年12月下旬评奖及公布获奖名单。</li>
    </ol>
     <p>七、参赛须知：</p>
    <ol class="">
      <li>对所有获奖作品，主办单位将向参赛者收集原稿。</li>
      <li>主办单位有权展出、出版和宣传所有参赛作品，不再另付稿酬。</li>
      <li>参赛作品凡涉及肖像权、名誉权事宜由参赛者自负。</li>
      <li>凡报送参赛作品者，均视为认同且接受本次比赛之规则。</li>
      <li>大赛主办方拥有本次活动的最终解释权</li>
    </ol>
   <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;让我们做个有心人吧，用手中的镜头，随时拍摄下身边让人心灵一动的那花那树、那人那景，为美丽的武汉科技大学留下靓丽的倩影。</p>
</div>
</div>
<!-- 底部 -->
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
</body>
</html>
