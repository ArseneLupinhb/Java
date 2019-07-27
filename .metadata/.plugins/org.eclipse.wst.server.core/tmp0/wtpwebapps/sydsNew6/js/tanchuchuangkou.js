function deleteLogin(){
	var del=document.getElementById("login_box");
	var bg_filter=document.getElementById("bg_filter");
	bg_filter.style.display="none";
	del.style.display="none";
}
function showBox(){
	var show=document.getElementById("login_box");
	var bg_filter=document.getElementById("bg_filter");
	show.style.display="block";
	bg_filter.style.display="block";
	
}
function showBox1(){
	var show=document.getElementById("register_box");
	var bg_filter=document.getElementById("bg_filter");
	show.style.display="block";
	bg_filter.style.display="block";	
}
function deleteRegister(){
	var del=document.getElementById("register_box");
	var bg_filter=document.getElementById("bg_filter");
	bg_filter.style.display="none";
	del.style.display="none";
}
function showBox2(){
	var show=document.getElementById("login_box");
	var bg_filter=document.getElementById("bg_filter");
	show.style.display="block";
	bg_filter.style.display="block";

	var del=document.getElementById("register_box");
	var bg_filter1=document.getElementById("bg_filter");
	bg_filter1.style.display="none";
	del.style.display="none";	
}
function showBox3(){
	var show=document.getElementById("register_box");
	var bg_filter=document.getElementById("bg_filter");
	show.style.display="block";
	bg_filter.style.display="block";

	var del=document.getElementById("login_box");
	var bg_filter1=document.getElementById("bg_filter");
	bg_filter1.style.display="none";
	del.style.display="none";
	
}
//二级联动下拉列表
var academy=[    
               ["学校办公室","纪委办公室","党委组织部","党委宣传部","党委学生工作部","工会、妇女委员会","团委","科学技术发展学院","研究生院",
                "人事处","教务处","教学质量与评估处","财务处","审计处","实验室与设备管理处","基建与后勤管理处","国际交流合作处","离退休工作处",
                "保卫处","住宅建设与改革领导小组办公室","采购与招标管理办公室","工程训练中心","图书馆","档案馆","现代教育信息中心","高等教育研究所",
                "学报编辑部","后勤集团","校医院","天佑医院","资产经营有限公司","国际钢铁研究院","武钢—武科大钢铁新技术研究院","绿色制造与节能减排中心",
                "高性能钢铁材料及其应用湖北省协同创新中心","黄家湖校区综合办","洪山校区综合办","其它"],  
               ["材料与冶金学院","管理学院","城市建设学院","汽车与交通工程学院","外国语学院","文法与经济学院","国际学院","信息科学与工程学院",
                "化学工程与技术学院","医学院","机械自动化学院","艺术与设计学院","计算机科学与技术学院","资源与环境工程学院","理学院","体育课部",
                "马克思主义学院","继续教育学院","职业技术学院"],  
           ];    
           function getDeparment(){    
               //获得参赛单位下拉框的对象    
               var sltDeparment=document.form1.department;  
               //获得部门或学院下拉框的对象    
               var sltAcademy=document.form1.academy;    
               //得到对应职能部门或学院的数组    
               var departmentAcademy=academy[sltDeparment.selectedIndex - 1];    
               //清空职能部门或学院下拉框，仅留提示选项    
               sltAcademy.length=1;    
               //将职能部门或学院数组中的值填充到职能部门或学院下拉框中    
               for(var i=0;i<departmentAcademy.length;i++){    
                   sltAcademy[i+1]=new Option(departmentAcademy[i],i);    
                   }    
           }       