<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://api2.sktelecom.com/tmap/js?version=1&format=javascript&appKey=a9ee13e1-cb7e-46a8-b144-14bfd0103a90"></script>
<script type="text/javascript"></script>
<script>							
// 1. 지도 띄우기
function initTmap(){
	// map 생성
	// Tmap.map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
	map = new Tmap.Map({
	div : 'map_div',
	width : "100%",
	height : "400px",
});
map.setCenter(new Tmap.LonLat("127.058908811749", "37.52084364186228").transform("EPSG:4326", "EPSG:3857"), 12);
routeLayer = new Tmap.Layer.Vector("route");
map.addLayer(routeLayer);

markerStartLayer = new Tmap.Layer.Markers("start");
markerEndLayer = new Tmap.Layer.Markers("end");
markerWaypointLayer = new Tmap.Layer.Markers("waypoint");
markerWaypointLayer2 = new Tmap.Layer.Markers("waypoint2");
pointLayer = new Tmap.Layer.Vector("point");

// 2. 시작, 도착 심볼찍기
// 시작
map.addLayer(markerStartLayer);

var size = new Tmap.Size(24, 38);
var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
var icon = new Tmap.IconHtml("<img src='http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_s.png' />", size, offset);
var marker_s = new Tmap.Marker(new Tmap.LonLat("127.02810900563199", "37.519892712436906").transform("EPSG:4326", "EPSG:3857"), icon);
markerStartLayer.addMarker(marker_s);

// 도착
map.addLayer(markerEndLayer);

var size = new Tmap.Size(24, 38);
var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
var icon = new Tmap.IconHtml("<img src='http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_e.png' />", size, offset);
var marker_e = new Tmap.Marker(new Tmap.LonLat("127.13281976335786", "37.52130314703887").transform("EPSG:4326", "EPSG:3857"), icon);
markerEndLayer.addMarker(marker_e);

//경유지 마커 제거
markerWaypointLayer.clearMarkers();
markerWaypointLayer2.clearMarkers();


// 3. 경유지 심볼 찍기
map.addLayer(markerWaypointLayer);

var size = new Tmap.Size(24, 38);
var offset = new Tmap.Pixel(-(size.w / 2), -size.h); 
var icon = new Tmap.IconHtml("<img src='http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_p.png' />", size, offset);
var marker = new Tmap.Marker(new Tmap.LonLat("127.04724656694417","37.524162226778515").transform("EPSG:4326", "EPSG:3857"), icon);
markerWaypointLayer.addMarker(marker);

var size = new Tmap.Size(24, 38);
var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
var icon = new Tmap.IconHtml("<img src='http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_p.png' />", size, offset);
var marker = new Tmap.Marker(new Tmap.LonLat("127.10887300128256","37.5289781669373").transform("EPSG:4326", "EPSG:3857"), icon);
markerWaypointLayer.addMarker(marker);

markerWaypointLayer2.clearMarkers();


// 4. 경로 탐색 API 사용요청
var startX = 127.02810900563199;
var startY = 37.519892712436906;
var endX = 127.13281976335786;
var endY = 37.52130314703887;
var passList = "127.04724656694417,37.524162226778515_127.10887300128256,37.5289781669373";
var prtcl;
var headers = {}; 
headers["appKey"]="a9ee13e1-cb7e-46a8-b144-14bfd0103a90";
$.ajax({
		method:"POST",
		headers : headers,
		url:"https://apis.openapi.sk.com/tmap/routes?version=1&format=xml",//
		async:false,
		data:{
			startX : startX,
			startY : startY,
			endX : endX,
			endY : endY,
			passList : passList,
			reqCoordType : "WGS84GEO",
			resCoordType : "EPSG3857",
			angle : "172",
			searchOption : "0",
			trafficInfo : "Y" //교통정보 표출 옵션입니다.
		},
		success:function(response){
			prtcl = response;
		
		//5. 경로탐색 결과 Line 그리기
		//출발지,도착지 마커 제거
		markerStartLayer.clearMarkers();
		markerEndLayer.clearMarkers();
		//경유지 마커 제거
		markerWaypointLayer.clearMarkers();
						
		var trafficColors = {
				extractStyles:true,
				
				/* 실제 교통정보가 표출되면 아래와 같은 Color로 Line이 생성됩니다. */
				trafficDefaultColor:"#000000", //Default
				trafficType1Color:"#009900", //원할
				trafficType2Color:"#8E8111", //지체
				trafficType3Color:"#FF0000"  //정체
				
			};    
		var kmlForm = new Tmap.Format.KML(trafficColors).readTraffic(prtcl);
		routeLayer = new Tmap.Layer.Vector("vectorLayerID"); //백터 레이어 생성
		routeLayer.addFeatures(kmlForm); //교통정보를 백터 레이어에 추가   
		
		map.addLayer(routeLayer); //지도에 백터 레이어 추가
		
		markerWaypointLayer2 = new Tmap.Layer.Markers("waypoint2");
		map.addLayer(markerWaypointLayer2);
		
		var size = new Tmap.Size(24, 38);
		var offset = new Tmap.Pixel(-(size.w / 2), -size.h); 
		var icon = new Tmap.IconHtml("<img src='http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_p.png' />", size, offset);
		var marker = new Tmap.Marker(new Tmap.LonLat("127.07389565460413","37.5591696189164").transform("EPSG:4326", "EPSG:3857"), icon);
		markerWaypointLayer2.addMarker(marker);
		
		var size = new Tmap.Size(24, 38);
		var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
		var icon = new Tmap.IconHtml("<img src='http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_p.png' />", size, offset);
		var marker = new Tmap.Marker(new Tmap.LonLat("127.13346617572014", "37.52127761904626").transform("EPSG:4326", "EPSG:3857"), icon);
		markerWaypointLayer2.addMarker(marker);
		
		
		
		// 6. 경로탐색 결과 반경만큼 지도 레벨 조정
		map.zoomToExtent(routeLayer.getDataExtent());
		
	},
	error:function(request,status,error){
		console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	}
});



// 맵 생성 실행
initTmap();
</script>

<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body onload="initTmap()">
	<div id="map_div"></div>
	<p id="result">결과 표시</p>
</body>
</html>