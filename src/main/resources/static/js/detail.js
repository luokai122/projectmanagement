
var localhost ="http://120.55.98.170:8093"
var limits = 1,
	userslimits,
	listsdate = [],
	urlpath = geturl(),
	getId = GetRequest(),
	oBtn;

$(function() {
	//获取项目信息
	$.ajax({
		type: "get",
		url: localhost+"/static/selectPJInfo",
		data: {
			id: getId,
		},
		success: function(data) { //data是返回的hash,key之类的值，key是定义的文件名
			if(data.code == "SUCCESS") {
				listsdate = data.data;
				setPageDate(listsdate[0]);
			} else {
				if(data.message.length <= 40) {
					alert(data.message);
				} else {
					alert("服务器内部异常，请联系管理员！！");
				}
			}
		},
		error: function() {
			alert("获取失败");
		}
	});
	//获取项目关联工程进度
	$.ajax({
		type: "get",
		url: localhost+"/static/selectProgress",
		data: {
			id: getId,
		},
		success: function(data) { //data是返回的hash,key之类的值，key是定义的文件名
			if(data.code == "SUCCESS") {
				listsdate = data.data;
				//console.log(listsdate);
				listLiAppend(listsdate);
			} else {
				if(data.message.length <= 40) {
					alert(data.message);
				} else {
					alert("服务器内部异常，请联系管理员！！");
				}
			}
		},
		error: function() {
			alert("获取失败");
		}
	});
	// 编辑迁移
	$("#edit_submit").click(function() {
        var str=location.href; //取得整个地址栏
        if(str.indexOf("?")==-1){
            return
        }else{
            var num=str.split("?")
            var id = num[1].split("=")[1];
        }
        window.location.href ="/static/projectUpdate?id="+id

	});
	oBtn = document.getElementById('selectInput');
	//  var oTi = document.getElementById('title');
	if('oninput' in oBtn) {
		oBtn.addEventListener("input", getWord, false);
	} else {
		oBtn.onpropertychange = getWord;
	}
	// var ie = !!window.ActiveXObject; 
	//    if(ie){ 
	//        oBtn.onpropertychange = getWord; 
	//    }else{ 
	//        oBtn.addEventListener("input",getWord,false); 
	//    } 
	function getWord() {
		//          oTi.innerHTML = oBtn.value;
		//		console.log(oBtn.value);
		//          selectInfo(oBtn.value);
	}
	//删除记录
	$("#pjRecord").on("click", "button", function() {
		//		var recordId = this.id;
		//		console.log(recordId);
		deleteRecord(this.id, this.name);
	});

	$("#updateimgbutton").click(function() {
		userslimits = 1;
		if(limits == userslimits) {
			alert("updateimgbutton")
		} else {
			alert("账户权限不足");
		}
	});
	// 追加工程进度记录
	$("#update_submit").click(function() {
		//		$('#uploadForm').serializeArray();

		//		var formData = new FormData($('#uploadForm')[0]);

		//userslimits = 1;
		//		if(limits == userslimits) {
		//			alert("add-record")
		//		} else {
		//			alert("账户权限不足");
		//		}
		//获取数据发送
		if(getId != undefined && getId != null) {
			addRecord();
		} else {
			alert("项目编号不正确！");
		}

	});
	$("#submit-btn").click(function() {
		//上传表单项目未定
		var name = $("#name").val();
		var virtualurl = $("#virtualurl").val();
		var imgurl = $("#imgurl").val();
		var shpurl = $("#shpurl").val();
		var sceneurl = $("#sceneurl").val();
		var area = $("#area").val();
		var city = $("#city").val();
		$.ajax({
			url: urlpath + "city/SaveCity",
			data: {
				"dataid": dataid,
				"name": name,
				"virtualurl": virtualurl,
				"imgurl": imgurl,
				"shpurl": shpurl,
				"sceneurl": sceneurl,
				"area": area,
				"city": city,

			}, //请求数据
			async: false,
			type: "post",
			jsonpCallback: 'callback',

			success: function(data) {
				var obj = JSON.parse(data);
				window.parent.refresh();
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				if(obj.code == "2000") {
					alert("更新成功");
				} else if(obj.code == "1000") {
					alert("添加成功");
				} else {
					alert("操作失败");
				}
			},
			error: function(error) {
				alert("操作失败");
			}
		});
	});

	//拨打电话
	var tel;
	$("#responsiblerenTell").click(function() {
		tel = this.innerText; //18601624089;
		//console.log(tel);
		phone(tel);
	});
	$("#contactrenTell").click(function() {
		tel = this.innerText;
		phone(tel);
	});

});

function phone(date) {
	window.location.href = 'tel://' + date;
};

function setPageDate(pagedate) {
	//	console.log(pagedate);
	//	document.getElementById("bar").innerHTML = "Test";
	//项目简介
	document.getElementById("projectid").innerHTML = pagedate.projectid; //项目编号
	document.getElementById("projectname").innerHTML = pagedate.projectname; //项目名
	document.getElementById("responsiblerenName").innerHTML = pagedate.responsiblerenName; //负责人
	document.getElementById("responsiblerenTell").innerHTML = pagedate.responsiblerenTell; //负责人电话
	document.getElementById("contactrenName").innerHTML = pagedate.contactrenName; //联络人
	document.getElementById("contactrenTell").innerHTML = pagedate.contactrenTell; //联络人电话
	document.getElementById("projectstatus").innerHTML = random(pagedate.projectstatus); //项目状态
	document.getElementById("department").innerHTML = pagedate.department; //牵头部门
	document.getElementById("projectinfo").innerHTML = pagedate.projectinfo; //项目内容
	//项目建设
	document.getElementById("investor").innerHTML = pagedate.investor; //投资方
	document.getElementById("struct").innerHTML = pagedate.struct; //建设单位
	document.getElementById("signtime").innerHTML = pagedate.signtime; //签约时间
	document.getElementById("starttime").innerHTML = pagedate.starttime; //开工时间
	document.getElementById("endtime").innerHTML = pagedate.endtime; //竣工时间
	document.getElementById("amountinvested").innerHTML = pagedate.amountinvested; //总投资
	document.getElementById("expectvalue").innerHTML = pagedate.expectvalue; //预计产值
	document.getElementById("expecttax").innerHTML = pagedate.expecttax; //预计利税
	//项目建设
	document.getElementById("belongarea").innerHTML = pagedate.belongarea; //归属区域
	document.getElementById("address").innerHTML = pagedate.address; //地址
	document.getElementById("areaid").innerHTML = pagedate.areaid; //项目地块
	document.getElementById("area").innerHTML = pagedate.area; //用地面积
}

/**
 *@name 创建工程记录List
 *@param 程记录List
 **/
function listLiAppend(recordList) {
	var imgurl = localhost+"/static/";
//	console.log(recordList.length);
	for(i = 0; i < recordList.length; i++) {
		//	for(i = 0; i < 3; i++) {

		var projectid = recordList.length;
		progressid = recordList[i].progressid,
			recordDate = subTime(recordList[i].createdate), //recordDate, 项目进度日期节点  目前无
			progressinfo = recordList[i].progressinfo,
			imglist = getImgList(recordList[i].img),
			j = recordList.length - i;
		//		console.log(imglist);	
		var imgli = "";
		if(imglist != undefined && imglist != null) {
			for(var z = 0; z < imglist.length; z++) {
				imgli = imgli +
					'						<li style="list-style-type:none;width: 50%;                                                               ' +
					'								height: auto;padding-left: 4px;padding-right: 4px;                                                ' +
					'								">                                                                                                ' +
					'							<img style="width: 100%;" src="' + imglist[z] + '" />                                              ' +
					'						</li>                                                                                                     '
			}
		}
		$("#pjRecord").append(
			'		<div class="mui-card" id="Record' + progressid + '">                                                                      ' +
			'			<div class="mui-card-header">                                                                                         ' +
			'				Seq: ' + j + ' <label id="">日期: ' + recordDate + '</label>                                                       ' +
			'				<button type="button" style="background-color: #0099FF;color: #FFFFFF" name="Record' + progressid + '" id="btn_submit' + j + '">删除记录</button>    ' +
			'			</div>                                                                                                                ' +
			'			<div class="mui-card-content">                                                                                        ' +
			'				<div class="mui-card-content-inner">                                                                              ' +
			'					<div id="">                                                                                                   ' +
			'						<label id="progressinfo">' + progressinfo + ' </label>                                                    ' +
			'					</div>                                                                                                        ' +
			'					<div class="line"></div>                                                                                      ' +
			'					<div id="recordImg' + progressid + '" style="display: flex;flex-wrap:wrap;">                                  ' +
			imgli +
			'                                                                                                                                 ' +
			'					</div>                                                                                                        ' +
			'				</div>                                                                                                            ' +
			'			</div>                                                                                                                ' +
			'		</div>                                                                                                                    '
		);
	}
};

//删除记录
function deleteRecord(recordid, recordname) {

	//	console.log(recordname.substring(6,recordname.length));
//	console.log(recordname);
	var recordId = recordname.substring(6, recordname.length),
		div = document.getElementById(recordname);
	div.remove();
	$.ajax({
		url: localhost+"/static/deleteProgress",
		data: {
			projectid: getId,
			progressid: recordId
		},
		//		processData: false, // 不处理数据
		//		contentType: false, // 不设置内容类型
		type: 'get',
		success: function(data) { //data是返回的hash,key之类的值，key是定义的文件名
			if(data.code == "SUCCESS") {
				alert("删除成功");
				location.reload();
			} else {
				if(data.message.length <= 40) {
					alert(data.message);
				} else {
					alert("服务器内部异常，请联系管理员！！");
				}
			}
		},
		error: function() {
			alert("删除成功");
		}
	});
}

function addRecord() {
	//	$('#uploadForm');
	var pictures = document.getElementById("doc"),
		getProgressInfo = oBtn.value,
		formData = new FormData();
	formData.append("projectid", getId);
	formData.append("progressinfo", getProgressInfo);

	if(pictures.files.length > 0) {
		for(var i = 0; i < pictures.files.length; i++) {
			//var imgSize = pictures.files[i].size;  
			formData.append("imgs[" + i + "]", pictures.files[i]);
			//console.log(imgSize)
		}
	}
	$.ajax({
		url: localhost+"/static/insertProgress",
		data: formData,
		processData: false, // 不处理数据
		contentType: false, // 不设置内容类型
		type: 'post',
		success: function(data) { //data是返回的hash,key之类的值，key是定义的文件名
			if(data.code == "SUCCESS") {
				alert("上传成功");
				location.reload();
			} else {
				if(data.message.length <= 40) {
					alert(data.message);
				} else {
					alert("服务器内部异常，请联系管理员！！");
				}
			}
		},
		error: function() {
			alert("上传失败");
		}
	});
}