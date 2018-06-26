/**
 * List js
 * @time 2018.06.12
 * @author 王凯迪
 */
//var urlpath = statusImg();
	var localhost = "http://120.55.98.170:8093"
var addSelectDateFlag = true;
var projectsDate = [],listsdate = [];
$(function() {

	$.ajax({
		//					data: data,
		type: "get",
		// http://192.168.0.158:8080/project?id=3
		
		// 错误测试接口
		// http://192.168.0.166:8080/static/insertProject
		url: localhost+"/static/selectPJInfo", //图片上传出来的url，返回的是图片上传后的路径，http格式
		//					cache: false,
		//					contentType: false,
		//					processData: false,
//		dataType: "json",
		success: function(data) { //data是返回的hash,key之类的值，key是定义的文件名
			if(data.code == "SUCCESS") {
				listsdate = data.data;
				ajaxForJsonDate(data.data);
			} else {				
				if (data.message.length <= 40){
					alert(data.message);
				}else{
					alert("服务器内部异常，请联系管理员！！");
				}
			}
		},
		error: function() {
			alert("获取失败");
		}
	});
//   document.getElementById("demo1").value;   
    var oBtn = document.getElementById('selectInput');
    var oTi = document.getElementById('title');
        if('oninput' in oBtn){ 
                oBtn.addEventListener("input",getWord,false); 
            }else{ 
                oBtn.onpropertychange = getWord; 
            }
        // var ie = !!window.ActiveXObject; 
     //    if(ie){ 
     //        oBtn.onpropertychange = getWord; 
     //    }else{ 
     //        oBtn.addEventListener("input",getWord,false); 
     //    } 
        function getWord(){
//          oTi.innerHTML = oBtn.value;
            console.log(oBtn.value);
            selectInfo(oBtn.value);
        }
	//搜索栏
	$(".selectInfo").click(function() {
		//			alert("其他没详细")
		selectInfo(strInput);
	});
	//迁移空间分布
	$(".map_img").click(function() {
		top.location = "/index";
	});

	//迁移追加项目
	$("#addprogect").click(function() {
//		top.location = "projectInsert.html";
		top.location = "/static/projectInsert";
	});

	//点击类型
	$("#leixing").on("click", "div", function() {
		//筛选分类
		var projectId = this.id;
		//			console.log(projectId);
		//	console.log(document.getElementById("类型"));
		//	console.log(document.getElementById("类型").$("#img").attr("src",url));
//		selectDate(projectId);
	});
	$("#类型").on("click", "li", function() {
		//筛选分类
		var projectId = this.id;
		//		console.log(projectId);
		if (projectId == "全部"){
			removeLi(projectsDate);
			buildDate(projectsDate);
		}else{
			chooseDate(projectId);
		}
	});
	$("#区域").on("click", "li", function() {
		//筛选分类
		var projectId = this.id;
		//		console.log(projectId);
		if (projectId == "全部"){
			removeLi(projectsDate);
			buildDate(projectsDate);
		}else{
			chooseDate(belongArea(projectId));
		}
	});
	$("#排序").on("click", "li", function() {
		//筛选分类
		var projectId = this.id;
//				console.log(projectId);
		if (projectId == "开工时间"){
			sortTime(projectId);
		}else{
			sortMoney(projectId);
		}
		
		
	});
	//获取项目详细信息
	$("ul#dateJson").on("click", "li", function() {
		// alert($(this).text());
		var id = this.id,
			listdate ;
//		console.log(document.getElementById(id).getElementsByTagName);
		top.location = "/static/projectInfo?listId=" + id;

	})
});

//搜索项目信息
function selectInfo(strInput) {
	$.ajax({
		//data: data,
		type: "get",
		url: localhost+"/static/selectPJInfo", //图片上传出来的url，返回的是图片上传后的路径，http格式
		date: {
			selectStr: strInput
		},
		//					cache: false,
		//					contentType: false,
		//					processData: false,
		dataType: "json",
		success: function(data) { //data是返回的hash,key之类的值，key是定义的文件名
			if(data.code == "1000") {
				var path = urlpath + data.path;
				$('#webtext').summernote('insertImage', path);
			} else {
				//alert("上传失败");
				console.log(data.list);
				//console.log(data.list[0]);
			}

		},
		error: function() {
//			alert("error上传失败");
		}
	});
}

//数据筛选框
function selectDate(type) {

	var iconUP = "img/list/up.png",
		iconDown = "img/list/down.png";

	//console.log(type)
	if(addSelectDateFlag) {
		addSelectDateFlag = false;
		switch(type) {
			case "类型":
				$("#img_a").attr("src", iconUP);
				break;
			case "区域":
				$("#img_b").attr("src", iconUP);
				break;
			case "排序":
				$("#img_c").attr("src", iconUP);
				break;
//			default:
//				$("#img_c").attr("src", iconUP);
		};
		//	document.getElementById("类型").getElementsByTagName("img").src = imgurl;
		//	$("#selectDate").addClass("panel panel-default");
	} else {
		//	$("#selectDate").removeClass("panel panel-default");

		addSelectDateFlag = true;
		switch(type) {
			case "类型":
				$("#img_a").attr("src", iconDown);
				break;
			case "区域":
				$("#img_b").attr("src", iconDown);
				break;
			case "排序":
				$("#img_c").attr("src", iconDown);
				break;
//			default:
//				$("#img_c").attr("src", iconUP);
		};
	}
};

/**
 *@name 数据筛选
 *
 **/
function chooseDate(type) {
	
//		console.log(type);
	var projectstmplist = [];
	for(i = 0; i < projectsDate.length; i++) {
		var li = $("ul li[id='" + projectsDate[i].id + "']");
		li.remove();
		//类型
		console.log(projectsDate[i].belongArea);
		if(type == projectsDate[i].projectStatus) {
			projectstmplist.push(projectsDate[i]);
		} else if(type == projectsDate[i].belongArea) {
			projectstmplist.push(projectsDate[i]);
		};
	}
	//页面数据重构
	buildDate(projectstmplist);
}
//排序
function sortMoney(arr) {
	var projectstmplist = projectsDate,
		listlength = projectstmplist.length,
		projectstmplis2 = [];
	for(var j = 0; j < projectstmplist.length - 1; j++) {
		//两两比较，如果前一个比后一个大，则交换位置。
		for(var i = 0; i < projectstmplist.length - 1 - j; i++) {
			//     	console.log(projectstmplist[i].amountInvested);
			if(projectstmplist[i].amountInvested < projectstmplist[i + 1].amountInvested) {
				//              console.log(projectstmplist[i].amountInvested);
				var temp = projectstmplist[i];
				projectstmplist[i] = projectstmplist[i + 1];
				projectstmplist[i + 1] = temp;
			}
		}
	}
//	for(i = 0; i < projectstmplist.length; i++) {
//		var li = $("ul li[id='" + projectstmplist[i].projectId + "']");
//		li.remove();
//	}
	removeLi(projectstmplist);
	buildDate(projectstmplist);
}
function sortTime(arr) {
	var projectstmplist = projectsDate,
		listlength = projectstmplist.length,
		projectstmplis2 = [];
	for(var j = 0; j < projectstmplist.length - 1; j++) {
		//两两比较，如果前一个比后一个大，则交换位置。
		for(var i = 0; i < projectstmplist.length - 1 - j; i++) {
			//     	console.log(projectstmplist[i].amountInvested);
			if(projectstmplist[i].startTime > projectstmplist[i + 1].startTime) {
				//              console.log(projectstmplist[i].amountInvested);
				var temp = projectstmplist[i];
				projectstmplist[i] = projectstmplist[i + 1];
				projectstmplist[i + 1] = temp;
			}
		}
	}
//	for(i = 0; i < projectstmplist.length; i++) {
//		var li = $("ul li[id='" + projectstmplist[i].projectId + "']");
//		li.remove();
//	}
	removeLi(projectstmplist);
	buildDate(projectstmplist);
}
/**
 *@name 页面数据重构
 *
 **/
function buildDate(projectstmplist) {

	for(j = 0; j < projectstmplist.length; j++) {

		//		console.log(
		//			projectstmplist[j].projectId,
		//			projectstmplist[j].projectName,
		//			projectstmplist[j].startTime,
		//			projectstmplist[j].projectId,
		//			projectstmplist[j].amountInvested);

		// 创建页面List
		listLiAppend(
			projectstmplist[j].id,
			projectstmplist[j].projectName,
			projectstmplist[j].startTime,
			projectstmplist[j].projectId,
			projectstmplist[j].amountInvested,
			projectstmplist[j].statusImg);
	}
}

//去除现有数据
function removeLi(removeLists){
	for(i = 0; i < removeLists.length; i++) {
		var li = $("ul li[id='" + removeLists[i].id + "']");
		li.remove();
	}
}
/**
 *@name 创建页面List
 *@param 项目编号
 *@param 项目名
 *@param 项目开工时间
 *@param 项目编号
 *@param 项目总投资金额
 *@param 项目状态
 **/
function listLiAppend(id, projectName, startTime, projectId, amountInvested, statusImg) {
	$("#dateJson").append(
		//		'<li class="mui-table-view-cell '+ statusImg +'" id="' + projectId + '">       ' +
		//		'<div class="mui-table">                                                             ' +
		//		'<div class="mui-table-cell mui-col-xs-10">                                          ' +
		//		'<div>项目名称：' + projectName + '</div>                                             ' +
		//		'<div>开工时间：' + startTime + '</div>                   ' +
		//		'</div>                                                   ' +
		//		'<div class="mui-table-cell mui-col-xs-10">               ' +
		//		'<div>项目编号：' + projectId + '</div>                   ' +
		//		'<div>投资金额：' + amountInvested + '</div>               ' +
		//		'</div>                                                   ' +
		//		'</div>                                                   ' +
		//		' </li>'

		'	<li  class="' + statusImg + ' table-view-li-list" id="' + id + '">                      ' +
		'			<div class="table-view-li-div">                                                      ' +
		'				<div class="table-view-li-div-div1">项目名称：' + projectName + '</div>          ' +
		'				<div class="table-view-li-div-div1">开工时间：' + startTime + '</div>            ' +
		'				<div class="table-view-li-div-div2">项目编号：' + projectId + '</div>            ' +
		'				<div class="table-view-li-div-div2">投资金额：' + amountInvested + ' 亿</div>     ' +
		'			</div>                                                                               ' +
		'		</li>                                                                                    '
	)
};

/**
 *@name Json数据页面绑定
 *
 **/
function ajaxForJsonDate(jsonData) {
//	console.log(jsonData);
	var i,
		pj = [];
		for(i = 0; i < jsonData.length; i++) {
//	for(i = 0; i < 20; i++) {
		pj = [];
		var datas = jsonData[i],
			id = jsonData[i].id, //唯一识别id
			projectName = jsonData[i].projectname, //项目名
			startTime = subTime(jsonData[i].starttime), // 项目开工时间
			projectId = jsonData[i].projectid, //项目编号
			amountInvested = jsonData[i].amountinvested, //项目总投资金额
			projectStatus = random(jsonData[i].projectstatus), //项目状态
			belongArea = jsonData[i].belongarea, //项目归属区域
			statusImg = projectStatusImg(projectStatus); //获取项目状态

		pj = {  id:id,
				projectName: projectName,
				startTime: startTime,
				projectId: projectId,
				amountInvested: amountInvested,
				projectStatus: projectStatus,
				belongArea: belongArea,
				statusImg: statusImg,
			},
			projectsDate.push(pj); //重构数据
		//创建页面List
		listLiAppend(id, projectName, startTime, projectId, amountInvested, statusImg);
	};
};