<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
       <div class="author">2017-03-14 dsdsad</div>
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
  console.log(arguments);
  alert('当前第 ' +page +'页,每页 '+size+'条,总页数：'+count+'页');
  }});
$('#pageToolbar').Paging({pagesize:10,count:85,toolbar:true});
</script>


</body>
</html>