var localhost = "http://120.55.98.170:8093"

function geturl() {
	var url = "https://www.zjtoprs.com/town/";
	return url;
};
//图片格式
function getImgList(testimg){
	var imgurl = localhost+"/static/",
		tmp ='',
		list = [];

	if (testimg != undefined && testimg != null){
		for(j = 0; j < testimg.split(";").length - 1; j++) {
			tmp = imgurl + testimg.split(";")[j];
			list.push(tmp);
		}
		return list;
	}else{
		
	}
	//	console.log(imgs);
	//	console.log(testimg.split(";").length);
	//  tmp = testimg.split(";")[1];
	//	console.log(tmp);
}
//项目状态
function projectStatusImg(projectStatus) {
	var divclass ;
	switch(projectStatus) {
		case "在谈":
			//在谈
			divclass = "table-view-li-qiatan";
			break;
		case "签约":
			//签约
			divclass = "table-view-li-qianyue";
			break;
		case "开工":
			//开工
			divclass = "table-view-li-kaigong";
			break;
		case "投产":
			//投产
			divclass = "table-view-li-touchang";
			break;
		case "收尾":
			//收尾
			divclass = "table-view-li-shouwei";
			break;
		default:;
			//n 与 case 1 和 case 2 不同时执行的代码
			//url = "./img/statusImg/zaitan.png";
	}
	return divclass;
};

function random(type) {
	var zhuangtai;
	switch(type) {
		case "1":
			return "在谈";
		case "2":
			return "签约";
		case "3":
			return "开工";
		case "4":
			return "投产";
		case "5":
			return "收尾";
	}
};

function choose(type) {
	switch(type) {
		case "在谈":
			return 1;
		case "签约":
			return 2;
		case "开工":
			return 3;
		case "投产":
			return 4;
		case "收尾":
			return 5;
		default:
			return 6;
	}
};
function belongArea(type) {
	switch(type) {
		case "高新区":
			return 1;
		case "地信小镇":
			return 2;
		case "通航小镇":
			return 3;
//		default:
//			return 6;
	}
};
function subTime(date){
//	console.log(date);
	if (date != undefined && date != ""){
		return date.substring(0,10);
	}else{
		return date;
	}
};
//获取参数
function GetRequest() {  
     var getUrl = window.location.href;  
     var getData = getUrl.split("=");        //截取 url中的“=”,获得“=”后面的参数  
     var  getId = decodeURI(getData[1]);   //decodeURI解码       
     //console.log(getId);
     //console.log(JSON.parse(searchText));//将搜索的数据显示在搜索页面的搜索框中  
  return getId;  
}  
