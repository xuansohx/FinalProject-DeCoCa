<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://api2.sktelecom.com/tmap/js?version=1&format=javascript&appKey=a9ee13e1-cb7e-46a8-b144-14bfd0103a90"></script>


<script>									
var map;
var tData;
//초기화
function initTmap(){
	map = new Tmap.Map({div:'map_div', width:'100%', height:'400px'});
	tData = new Tmap.TData();
	
	tData.events.register("onComplete", tData, onComplete);
	tData.getAutoCompleteSearch("역삼역", 5);
	
	markerLayer = new Tmap.Layer.Markers();
	map.addLayer(markerLayer);
}
function onComplete(){
	console.log(this.responseXML);
	var keyword = this.responseXML.getElementsByTagName("keyword")[0].firstChild.data;
	var gAppKey = "a9ee13e1-cb7e-46a8-b144-14bfd0103a90";//실행을 위한 키 입니다. 발급받으신 AppKey(appKey)를 입력하세요.
	var url = "https://apis.openapi.sk.com/tmap/pois";//명칭(POI) 통합검색 API 요청 url 입니다.
	var params = {
			"version" : "1"//버전 정보입니다.
			,"searchKeyword" : keyword //자동완성 키워드로 얻은 명칭 중 하나입니다.
			,"appKey" : gAppKey//위에서 설정한 앱 키(appKey)입니다.
	
		};
	//위에서 설정한 정보를 통하여 api 요청을 보내고, 정보를 받습니다.
	$.get(url, params, function(data){
		if(data){
			var lon = data.searchPoiInfo.pois.poi[0].noorLon;//data에서 받아온  lon 좌표값 입니다.
			var lat = data.searchPoiInfo.pois.poi[0].noorLat;//data에서 받아온  lat 좌표값 입니다.
			var name = data.searchPoiInfo.pois.poi[0].name;//data에서 받아온  명칭입니다.
			 
			var lonlat = new Tmap.LonLat(lon, lat).transform("EPSG:4326", "EPSG:3857");//Icon 좌표를 설정합니다.
			var size = new Tmap.Size(24,38);//Icon 크기 설정을 합니다.
			var offset = new Tmap.Pixel(-(size.w/2), -size.h);//Icon 중심점을 설정합니다.
			var icon = new Tmap.Icon('http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_h.png', size, offset);//Icon 설정을 합니다.    
			var label = new Tmap.Label(name);//label에 출력될 text 설정합니다.
			
			var marker = new Tmap.Markers(lonlat, icon, label);//생성한 값들을 이용하여 marker 생성합니다.
			markerLayer.addMarker(marker);//layer에 marker를 추가합니다.
			marker.popup.show();//marker의 팝업 표시
			
			map.setCenter(lonlat,18);//map의 중심좌표를 설정합니다.
		}
	});
} 
// 맵 생성 실행
initTmap();
</script>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body onload="initTmap()">
	<div id="map_div"></div>
	<p id="result">결과</p>
</body>
</html>