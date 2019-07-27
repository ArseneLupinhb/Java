

function popDiv(showID,backID){
	document.getElementById(showID).style.display='block';
	document.getElementById(backID).style.display='block';

	var backdiv=document.getElementById(backID);
	backdiv.style.width=document.body.scrollWidth;
	$("#"+backID).height($(document).height());
};
function hideDiv(showID,backID){
	document.getElementById(showID).style.display='none';
	document.getElementById(backID).style.display='none';
};
/*function deleteAlbum(){
   
    var list=document.getElementById("main");
    list.removeChild($(this).prev());
}*/



