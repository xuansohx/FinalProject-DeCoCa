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
var la4;
var lo4;
var la5;
var lo5;
var la6;
var lo6;
var la7;
var lo7;
var la8;
var lo8;
var la9;
var lo9;
var result;
var test1 = "${path}";
var obj1 = JSON.parse('${json1}');
var obj2 = JSON.parse('${json2}');
var obj3 = JSON.parse('${json3}');
var obj4 = JSON.parse('${json4}');
var obj5 = JSON.parse('${json5}');
var obj6 = JSON.parse('${json6}');
var obj7 = JSON.parse('${json7}');
var obj8 = JSON.parse('${json8}');
var obj9 = JSON.parse('${json9}');
var marker;
var cnt=0;
var markerLayer;
var markerLayer2;
// 페이지가 로딩이 된 후 호출하는 함수입니다.
var arr1 = new Array();
var arr2 = new Array();
var arr3 = new Array();
var arr4 = new Array();
var arr5 = new Array();
var arr6 = new Array();
var arr7 = new Array();
var arr8 = new Array();
var arr9 = new Array();
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
			//map.setCenter(lonlat, 13.5);//map의 중심 좌표 설정
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
			var icon1 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_a.png',
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
 			var icon3 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_c.png',
				size, offset);//마커 아이콘 설정 
			arr3[index] = new Tmap.Marker(lonlat, icon3);//마커 생성
	});
	$(obj4).each(function(index, item){
			la4 = item.lat;
			lo4 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la4, lo4).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			//map.setCenter(lonlat, 13);//map의 중심 좌표 설정
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
 			var icon4 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_d.png',
				size, offset);//마커 아이콘 설정 
			arr4[index] = new Tmap.Marker(lonlat, icon4);//마커 생성
	});
	$(obj5).each(function(index, item){
			la5 = item.lat;
			lo5 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la5, lo5).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			map.setCenter(lonlat, 13.5);//map의 중심 좌표 설정
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
 			var icon5 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_e.png',
				size, offset);//마커 아이콘 설정 
			arr5[index] = new Tmap.Marker(lonlat, icon5);//마커 생성
	});
	$(obj6).each(function(index, item){
			la6 = item.lat;
			lo6 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la6, lo6).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			//map.setCenter(lonlat, 13);//map의 중심 좌표 설정
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
 			var icon6 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_f.png',
				size, offset);//마커 아이콘 설정 
			arr6[index] = new Tmap.Marker(lonlat, icon6);//마커 생성
	});
	$(obj7).each(function(index, item){
			la7 = item.lat;
			lo7 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la7, lo7).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			//map.setCenter(lonlat, 13);//map의 중심 좌표 설정
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
 			var icon7 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_g.png',
				size, offset);//마커 아이콘 설정 
			arr7[index] = new Tmap.Marker(lonlat, icon7);//마커 생성
	});
	$(obj8).each(function(index, item){
			la8 = item.lat;
			lo8 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la8, lo8).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			//map.setCenter(lonlat, 13);//map의 중심 좌표 설정
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
 			var icon8 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_g_m_h.png',
				size, offset);//마커 아이콘 설정 
			arr8[index] = new Tmap.Marker(lonlat, icon8);//마커 생성
	});
	$(obj9).each(function(index, item){
			la9 = item.lat;
			lo9 = item.lng;
			result = item.idx;
			var lonlat = new Tmap.LonLat(la9, lo9).transform("EPSG:4326","EPSG:3857");//좌표 설정
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			//map.setCenter(lonlat, 13);//map의 중심 좌표 설정
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
 			var icon9 = new Tmap.Icon(
				'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_g_m_i.png',
				size, offset);//마커 아이콘 설정 
			arr9[index] = new Tmap.Marker(lonlat, icon9);//마커 생성
	});
	
	function loop1(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr1[x-1]);
			markerLayer.addMarker(arr1[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	function loop2(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr2[x-1]);
			markerLayer.addMarker(arr2[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	function loop3(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr3[x-1]);
			markerLayer.addMarker(arr3[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	function loop4(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr4[x-1]);
			markerLayer.addMarker(arr4[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	function loop5(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr5[x-1]);
			markerLayer.addMarker(arr5[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	function loop6(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr6[x-1]);
			markerLayer.addMarker(arr6[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	function loop7(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr7[x-1]);
			markerLayer.addMarker(arr7[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	function loop8(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr8[x-1]);
			markerLayer.addMarker(arr8[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	function loop9(x) {
		setTimeout(function() {
			markerLayer.removeMarker(arr9[x-1]);
			markerLayer.addMarker(arr9[x]);//레이어에 마커 추가
			}, 1000 * x);
	}
	 /* for (i=1; i<arr3.length; i++) {
		loop(i);
	}  */
	for (i=1,j=1,k=1,a=1,b=1,c=1,d=1,e=1,f=1; i<arr1.length, j<arr2.length, k<arr3.length, a<arr4.length, b<arr5.length, c<arr6.length, d<arr7.length, e<arr8.length, f<arr9.length; i++, j++, k++, a++, b++, c++, d++, e++, f++) {
		loop1(i);
		loop2(j);
		loop3(k);
		loop4(a);
		loop5(b);
		loop6(c);
		loop7(d);
		loop8(e);
		loop9(f);
	}
	
}
// 맵 생성 실행
initTmap();
</script>
