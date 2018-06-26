var localhost = "http://120.55.98.170:8093"

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}
var my_trail, my_location, oBtn,
	strat = [],
	end = [];

//定义定位坐标
var latitude = "";
var longitude = "";
var myNav;
$(function() {
	getlocation();
	markersAdd();
	//切换列表界面
	oBtn = document.getElementById('selectInput');
	//  var oTi = document.getElementById('title');
	if('oninput' in oBtn) {
		oBtn.addEventListener("input", getWord, false);

		//select_Date;
	}

	function getWord() {
		//          oTi.innerHTML = oBtn.value;
		select_Date();
		console.log(oBtn.value);
	}

	$(".lists").click(function() {
		top.location = "/static/projectList";
	});
	var zoom, evTimeStamp = 0;
	$(".zoomUp").click(function() {
		zoom = map.getZoom()
		var now = +new Date();
		//      console.log(now);
		if(now - evTimeStamp < 100) {
			return;
		}
		evTimeStamp = now;
		//       console.log(evTimeStamp);
		//		console.log("zoomUp"+ zoom);
		if(zoom < 18 && zoom > 0) {
			zoom++;
			map.setZoom(zoom);
		}
	});
	$(".zoomDown").click(function() {
		zoom = map.getZoom()
		var now = +new Date();
		//      console.log(now);
		if(now - evTimeStamp < 100) {
			return;
		}
		evTimeStamp = now;
		//       console.log(evTimeStamp);	
		//		console.log("zoomDown"+ zoom);
		if(zoom <= 18 && zoom > 0) {
			zoom--;
			map.setZoom(zoom);
		}
	});
	$(".location").click(function() {

		//	$(".location").click(function() {
		if(my_trail != undefined) {
			map.fitBounds(my_trail.getBounds());
		}
		//	});

		if(my_location != undefined && my_location != "") {
			map.removeLayer(my_location);
		}
		//console.log([latlng[0]+latlng[1]);
		var lnglat = gcj02towgs84(longitude, latitude);
		map.setView([lnglat[1], lnglat[0]], 14);
		var new_latitude = lnglat[1];
		var new_longitude = lnglat[0];
		if(new_latitude != "" && new_longitude != "") {
			var myIcon = L.icon({
				iconUrl: 'img/main/location.png',
				iconSize: [25, 25],
			});
			var latlng = L.latLng(new_latitude, new_longitude);
			my_location = L.marker(latlng, {
				icon: myIcon
			});
			my_location.bindTooltip("我的位置", {
				permanent: true,
				offset: [13, -3],
				direction: "right",
			}).openTooltip().addTo(map);
		}
	});

});

//侧栏点击，地图加载marker
var markersflag = false;
var markers = new L.FeatureGroup();

function markersAdd() {
	//清除当前地图上的markers
	if(markersflag) {
		console.log(markersflag);
		map.removeLayer(markers);
		if(click_spot != undefined && click_spot != "") {
			map.removeLayer(click_spot);
		}
		markers = new L.FeatureGroup();
		markersflag = false;
	}

	$.ajax({
		type: "get",
		url: localhost+"/static/selectPJInfo", //图片上传出来的url，返回的是图片上传后的路径，http格式
		success: function(data) { //data是返回的hash,key之类的值，key是定义的文件名
			if(data.code == "SUCCESS") {
				listsdate = data.data;
				//				console.log(listsdate);
				ajaxForJsonDate(listsdate);
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
	})

	function ajaxForJsonDate(data) {
		//		console.log(data);
		var spots = data;
		if(spots != "" && spots.length > 0) {
			var tabStr = "";
			var icon = "/static/img/area/icon_project.png";

			for(var i = 0; i < spots.length; i++) {
				//latlng
				var x = spots[i].latitude; //pointx; 
				var y = spots[i].longitude; //pointy;
				var id = spots[i].id;
				if(x != "" && y != "") {
					//地图足迹图标

					var myIcon = L.icon({
						iconUrl: icon,
						iconSize: [35, 40],
						iconAnchor: [0, 0]
					});
					var my_spot = L.marker([y, x], {
						spotid: "li" + i,
						spotname: spots[i].projectname,
						//						alt: imgurl,
						icon: myIcon
					});
					my_spot.bindPopup(
						'<div class="box">' +
						'		<div class="popupwindow">                                                              ' +
						'			<div class="toppopup" >                                                             ' +
						'				<div class="toppopup-img" pointx=' + x + ' pointy=' + y + ' spotname=' + spots[i].projectname + i + ' onclick="gothis(this)">                                                     ' +
						'					<img src="/static/img/area/gothis.png" />                                              ' +
						'				</div>                                                                         ' +
						'				<div class="toppopup-text" projectId=' + id + ' onclick="detail(this)">                                                    ' +
						'					<div class="toppopup-text-text">' + spots[i].projectname + '                                                                                                                         ' +
						'					</div>                                                                     ' +
						'					<div class="toppopup-text-img">                                            ' +
						'						<img src="/static/img/area/icon_right.png" />                                  ' +
						'					</div>                                                                     ' +
						'				</div>                                                                         ' +
						'			</div>                                                                             ' +
						'		</div>                                                                                 ' +
						'</div> '
						, {
							offset: [15, 5],
							closeButton: false,
							spotname: spots[i].projectname,
						}).openPopup();;
					markers.addLayer(my_spot);
					//					console.log(i);
				}
				map.fitBounds(markers.getBounds());
				map.addLayer(markers);
			}
			markersflag = true;
		} 
	}
}

function select_Date() {

	$.ajax({
		//data: data,
		type: "get",
		url: localhost+"/static/selectPJInfo", //图片上传出来的url，返回的是图片上传后的路径，http格式
		date: {
			selectStr: $("#selectInput").val()
		},
		dataType: "json",
		success: function(data) { //data是返回的hash,key之类的值，key是定义的文件名
			if(data.code == "SUCCESS") {
				if(markersflag) {
					console.log(markersflag);
					map.removeLayer(markers);
					if(click_spot != undefined && click_spot != "") {
						map.removeLayer(click_spot);
					}
					markers = new L.FeatureGroup();
					markersflag = false;
				}
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
			alert("检索失败");
		}
	});
}
function detail(obj) {
	var id = $(obj).attr("projectId")
	//	console.log(id);
	//top.location = "detail.html?detail=" + id;
    top.location = "/static/projectInfo?detail=" + id;
}

function gothis(obj) {
	var pointx = $(obj).attr("pointx");
	var pointy = $(obj).attr("pointy");
	var toname = $(obj).attr("spotname");
	var tocood_latlng = wgs84togcj02(pointx, pointy);
	var tocoord = "" + tocood_latlng[1].substring(0, 11) + "," + tocood_latlng[0].substring(0, 11) + "";
	if(latitude != "" && longitude != "") {
		var fromcoord = "" + latitude + "," + longitude + "";
		var urlpath = "http://apis.map.qq.com/uri/v1/routeplan?type=drive&fromcoord=" + fromcoord + "&to=" + toname + "&tocoord=" + tocoord + "&policy=0&referer=myapp";
		self.location = urlpath;
	} else {
		getlocation();
	}
}

function getlocation() {
	var geolocation = new qq.maps.Geolocation("SPXBZ-62VWF-4WIJR-JPN4I-XXL5V-VXBJZ", "myapp");
	var options = {
		timeout: 8000,
	};
	geolocation.getLocation(showPosition, showErr, options);

	function showPosition(position) {
		var latlong = gcj02towgs84(position.lng, position.lat);
		var new_longitude = latlong[0];
		var new_latitude = latlong[1];
		var oldlatlong = wgs84togcj02(new_longitude, new_latitude);
		longitude = oldlatlong[0];
		latitude = oldlatlong[1];
		if(new_latitude != "" && new_longitude != "") {
			var myIcon = L.icon({
				iconUrl: '/static/js/plug-in/leaflet/images/marker-icon.png',
				iconSize: [25, 32],
			});
			var latlng = L.latLng(new_latitude, new_longitude);
			var my_location = L.marker(latlng, {
				icon: myIcon
			});
			my_location.bindTooltip("我的位置", {
				permanent: true,
				offset: [0, -15],
				direction: "top",
				//					className: 'anim-tooltip'
			}).openTooltip().addTo(map);
		}
	};

	function showErr() {
		//		alert("定位失败！");
	};
}