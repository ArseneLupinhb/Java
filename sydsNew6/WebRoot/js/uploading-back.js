
var countPic = 0;
var countPicId = 0;
var filesArray = "";
function setImagePreviews() {
  var MAXWIDTH = 130;
  var MAXHEIGHT = 100;
  if (countPicId == 0) {
    var docObj = document.getElementById("uploading");
    filesArray += "uploading" + ",";
  }
  else {
    var item = countPicId-1;
    var docObj = document.getElementById("up-pic" +item);
    filesArray += "up-pic" + countPicId + ",";
    console.log(filesArray);
  }
  var dd = document.getElementById("pictures");// 展示的div
  dd.style.display = "block";
  // 隐藏和创建新的文件上传input
  // if($("#pictures").attr("display") === "block")
  if(countPicId !== 0){
     var loop=countPicId-1;
    var forbidden=document.getElementById("continue-up" + loop);
    forbidden.style.display = "none";// 隐藏
    var liFileP= document.createElement("li");
    liFileP.setAttribute("id", "continue-up"+ countPicId );
    liFileP.style.float="left";
    var liHtmlP = "<label for='up-pic"+countPicId+"'><img src='images/contium-upload.png' alt='continue-up'></label> <input type='file' id='up-pic"+countPicId+"' hidden multiple='multiple' onchange='javascript:setImagePreviews();' > ";
    liFileP.innerHTML = liHtmlP;
    // console.log(liFileP.innerHTML);
    $("#pictures ul").append(liFileP);
  }
  //wo
  document.getElementById("choose").style.display = "none";// 隐藏
  countPicId++; //1
  // var liFile = document.createElement("li");
  // liFile.style.float = "left";
  if(docObj.files){
    var fileList = docObj.files; // 获取文件对象集合
    console.log(fileList);
  }else{
    alert("请使用最新版本的IE浏览器或者更换浏览器")
  }
  for (var i = 0; i < fileList.length; i++) {
    var liFile = document.createElement("li");
    liFile.style.float = "left";
    // var index = i + countPic;  //dd
    var index = countPic;
    liFile.innerHTML += "<div class='img-container' style='float:left ;position: relative;' ><img id='img" + index
      + "' /> <span id='delete'><button onclick='deletePic(event)'></button></span>   </div>";
    $('#pictures ul').prepend(liFile);
    var imgObjPreview = document.getElementById("img" + index);
    // console.log(imgObjPreview);
    if (docObj.files && docObj.files[i]) {
      // 火狐下，直接设img属性
      imgObjPreview.style.display = 'block';
      imgObjPreview.style.width = '130px';
      imgObjPreview.style.height = '100px';
      imgObjPreview.style.marginRight = '25px';
      imgObjPreview.style.marginBottom = '25px';
      // imgObjPreview.src = docObj.files[0].getAsDataURL();
      // 火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
      imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
      // console.log(countPic);
      countPic++;
    } else {
      // IE下，使用滤镜
      docObj.select();
      var imgSrc = document.selection.createRange().text;
      // alert(imgSrc);
      var localImagId = document.getElementById("img" + countPic);
      countPic++;
      // 必须设置初始大小
      localImagId.style.marginLeft = '10px';
      // 图片异常的捕捉，防止用户修改后缀来伪造图片
      try {
        localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
        localImagId.filters
          .item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;

        // 改变图片大小
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
          img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ','
        + rect.width + ',' + rect.height);
        div.innerHTML += "<div id=divhead style='width:" + rect.width
          + "px;height:" + rect.height + "px;margin-top:"
          + rect.top + "px;" + sFilter + src + "\"'></div>";
      } catch (e) {
        alert("您上传的图片格式不正确，请重新选择!");
        return false;
      }
      imgObjPreview.style.display = 'none';
      document.selection.empty();
    }

  }
  var imgs = $(".img-container>img");
  for (i = 0; i < imgs.length; i++) {

    $(imgs[i]).parent().hover(function () {
      $(this).children("span#delete").css("display", "block");
    }, function () {
      $(this).children("span#delete").css("display", "none");
    });
  }
  return true;
}
$("#label1").bind("click",function () {
    $("#uploading").click();
});
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
  var param = {
    top : 0,
    left : 0,
    width : width,
    height : height
  };
  if (width > maxWidth || height > maxHeight) {
    rateWidth = width / maxWidth;
    rateHeight = height / maxHeight;

    if (rateWidth > rateHeight) {
      param.width = maxWidth;
      param.height = Math.round(height / rateWidth);
    } else {
      param.width = Math.round(width / rateHeight);
      param.height = maxHeight;
    }
  }

  param.left = Math.round((maxWidth - param.width) / 2);
  param.top = Math.round((maxHeight - param.height) / 2);
  return param;
}

function deletePic(event) {
     // alert()
  console.log(countPic);
    $(event.target).parent().parent().remove();

}
// 提交信息
function upload() {
  $("#submit").attr("disabled", true);
  filesArray = filesArray.substring(0, filesArray.length - 1);
  $.ajaxFileUpload({
    url: 'projectAction_uploadProject.do', // 用于文件上传的服务器端请求地址
    secureuri: false, // 一般设置为false
    fileElementId: filesArray, // 文件上传空间的id属性
    dataType: 'json', // 返回值类型 一般设置为json
    success: function (data, status) {
      // 就是root对应的值这里是result!!
      var member = eval("(" + data + ")"); // 包数据解析为json
      if (member.result == "success") {
        alert("保存成功");
        window.parent.close();
      } else {
        alert("保存失败");
        window.location.reload();
      }
    },
    error: function (data, status, e)// 服务器响应失败处理函数
    {
      alert("上传失败,不支持上传格式");
    }
  });

}
$(function(){
  var showAlbumList = false;
  $(".detail").bind("click",function(){
    if(!showAlbumList){
      $(".album-list").css("height","200px");
      showAlbumList = true;
    }else {
      $(".album-list").css("height","0");
      showAlbumList = false;
    }
  })
  $(".album-list li").bind("click",function(){
    $(".selected-item").html($(this).html());
  });
});