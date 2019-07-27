var upPic = document.getElementById("up-pic");
//noinspection JSAnnotator
upPic.onclick=function(){
  var oHead=document.createElement("div");
  oHead.className="wrap";
  oHead.innerHTML ="<div id='close-box'><p>上传照片-普通上传</p><button id='close' onclick='closeIframe(event)'></button></div>";
  var oMask=document.createElement("iframe");
  oMask.id="mask";
  oMask.height="470";
  oMask.width="1240";
  oMask.border="1px solid #ccc";
  oMask.src="uploading.html";
  document.body.appendChild(oHead);
  document.body.appendChild(oMask);
};
function closeIframe(event) {
  var $iF=$(event.target).parent().parent();
  $iF.siblings("#mask").remove();
  $iF.remove();
  // $(event.target).parent().parent().siblings("#mask").remove();
}