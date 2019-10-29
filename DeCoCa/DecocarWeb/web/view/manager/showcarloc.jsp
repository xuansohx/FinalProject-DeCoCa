<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script
	src="https://api2.sktelecom.com/tmap/js?version=1&format=javascript&appKey=a9ee13e1-cb7e-46a8-b144-14bfd0103a90"></script>

<style type="text/css">
@import url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap');

#page_title {
	font-family: 'Lalezar', cursive;
	text-align: center;
}
</style>

	<div id="map_div"></div>
	
<br><br>
<div id="container1" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

<br><br>
<div id="container2" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

<script src="vendor/jquery/jquery-3.2.1.min.js"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>


<!-- Map Function -->
<script>

var map;
var la1;
var lo1;
var la2;
var lo2;
var la3;
var lo3;
var result;
var test1 = "${path}";
var obj1 = JSON.parse('${json1}');
var obj2 = JSON.parse('${json2}');
var obj3 = JSON.parse('${json3}');
var marker;
var cnt=0;
var markerLayer;
var markerLayer2;
// 페이지가 로딩이 된 후 호출하는 함수입니다.
var arr1 = new Array();
var arr2 = new Array();
var arr3 = new Array();
var result = '';
var i=0;
var runAction = function(itv) {
	console.log('Called!! ' + itv + 'th');
}
function initTmap() {
	// map 생성
	// Tmap.map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
	map = new Tmap.Map({
		div : 'map_div', // map을 표시해줄 div
		width : '100%', // map의 width 설정
		height : '800px' // map의 height 설정
	});

	markerLayer = new Tmap.Layer.Markers();//마커 레이어 생성
	map.addLayer(markerLayer);//map에 마커 레이어 추가
	
	//index와 아이템
	$(obj1).each(function(index, item){
			la1 = item.lat;
			lo1 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la1, lo1).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			map.setCenter(lonlat, 13.2);//map의 중심 좌표 설정

			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
			var icon1 = new Tmap.Icon(
					
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_g_m_a.png',
				size, offset);//마커 아이콘 설정
			arr1[index] = new Tmap.Marker(lonlat, icon1);//마커 생성
	});
	$(obj2).each(function(index, item){
			la2 = item.lat;
			lo2 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la2, lo2).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			//map.setCenter(lonlat, 13);//map의 중심 좌표 설정

			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
			var icon2 = new Tmap.Icon(
					
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_b.png',
				size, offset);//마커 아이콘 설정
			arr2[index] = new Tmap.Marker(lonlat, icon2);//마커 생성
	});
	$(obj3).each(function(index, item){
			la3 = item.lat;
			lo3 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la3, lo3).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			//map.setCenter(lonlat, 13);//map의 중심 좌표 설정

			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
			/* var icon3 = new Tmap.Icon(
				'http://70.12.60.111/DeCoCa/img/truck.png',
				size, offset);//마커 아이콘 설정 */
 			var icon3 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_c.png',
				size, offset);//마커 아이콘 설정 
			arr3[index] = new Tmap.Marker(lonlat, icon3);//마커 생성
	});
	
	function loop1(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr1[x-1]);
			markerLayer.addMarker(arr1[x]);//레이어에 마커 추가
			}, 2000 * x);
	}
	function loop2(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr2[x-1]);
			markerLayer.addMarker(arr2[x]);//레이어에 마커 추가
			}, 2000 * x);
	}
	function loop3(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr3[x-1]);
			markerLayer.addMarker(arr3[x]);//레이어에 마커 추가
			}, 2000 * x);
	}
	 /* for (i=1; i<arr3.length; i++) {
		loop(i);
	}  */
	for (i=1,j=1,k=1; i<arr1.length, j<arr2.length, k<arr3.length; i++, j++, k++) {
		loop1(i);
		loop2(j);
		loop3(k);
	}
	
}
// 맵 생성 실행
initTmap();
</script>
