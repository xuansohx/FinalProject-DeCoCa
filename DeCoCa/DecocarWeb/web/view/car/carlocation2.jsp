<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://api2.sktelecom.com/tmap/js?version=1&format=javascript&appKey=a9ee13e1-cb7e-46a8-b144-14bfd0103a90"></script>


<p id="result"></p>
<script type="text/javascript">
//페이지가 로딩이 된 후 호출하는 함수입니다.
	var map, marker;
	var gAppKey = 'a9ee13e1-cb7e-46a8-b144-14bfd0103a90';

function initTmap(){
	// map 생성
	// Tmap.map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
	map = new Tmap.Map({div:'map_div', width:'80%', height:'800px'});//map 생성
	markerLayer = new Tmap.Layer.Markers();//마커레이어를 생성합니다.
	map.addLayer(markerLayer);//map에 마커레이어를 추가합니다.
	map.events.register("moveend", map, onMoveEnd);//map 이동 이벤트를 등록합니다.
	map.events.register("click", map, onClick);//map 클릭 이벤트를 등록합니다.
	}
function onMoveEnd(){
	var c_ll = map.getCenter(); //현재 지도의 center 좌표를 가져옵니다.
	c_ll = new Tmap.LonLat(c_ll.lon, c_ll.lat).transform("EPSG:3857","EPSG:4326");//WGS84 좌표계로 변환
	loadGetAddressFromLonLat(c_ll);//중심좌표를 주소로 변환하는 함수입니다.
	 
	//중심좌표를 주소로 변환하는 함수입니다. 
	function loadGetAddressFromLonLat(ll) {
		var tdata = new Tmap.TData(); //REST API 에서 제공되는 경로, 교통정보, POI 데이터를 쉽게 처리할 수 있는 클래스입니다.
		tdata.events.register("onComplete", tdata,onCompleteLoadGetAddressFromLonLat);//데이터 로드가 성공적으로 완료되었을 때 발생하는 이벤트입니다.
		var optionObj = {
			version : 1
		};
		tdata.getAddressFromLonLat(ll, optionObj);//좌표를 통한 주소 정보 데이터를 콜백 함수를 통해 XML로 리턴합니다.
	}
	//데이터 로드가 성공적으로 완료되면, 줌심 좌표를 주소로 변환한 결과를 출력하는 함수입니다.
	function onCompleteLoadGetAddressFromLonLat() {
		var result ='현재 지도의 중심 좌표주소 : '+jQuery(this.responseXML).find("fullAddress").text()+'';//출력될 결과 입니다.
		var resultDiv = document.getElementById("result");//id가 결과를 출력할 result인 element를 찾습니다.
		resultDiv.innerHTML = result;//결과를 htmㅣ에 출력
	}
}
function onClick(e){
	lonlat = map.getLonLatFromViewPortPx(e.xy); 
	var url = "https://apis.openapi.sk.com/tmap/geo/reversegeocoding"; //Reverse Geocoding api 요청 url입니다.
	var params = {
		"version" : "1"//버전 정보입니다.
		,"coordType" : "EPSG3857"
		,"lat" : lonlat.lat //위도 좌표입니다.
		,"lon" : lonlat.lon //경도 좌표입니다.
		,"appKey" : gAppKey//앱 키(appKey) 입니다.
	}
	$.get(url, params, function(data){
		if(data){ 
			markerLayer.removeMarker(marker); //기존 마커를 삭제합니다.
			var address = data.addressInfo;//Reverse Geocoding api 요청하여 받은 결과에서 주소정보를 추출합니다.
			var size = new Tmap.Size(24,38); //Icon 크기 입니다.
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h)); //Icon 중심점 입니다.
			var icon = new Tmap.Icon('http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_h.png',size, offset); //marker에 사용될 아이콘 설정입니다.  
			var label = new Tmap.Label("주소정보 : "+address.fullAddress); //label에 표시될 text입니다.
			marker = new Tmap.Markers(lonlat, icon, label); //생성한 값들을 이용하여 marker를 생성합니다.
			markerLayer.addMarker(marker);//layer에 marker 추가합니다.
			marker.popup.show();//marker 팝업 표시
		}else{
			alert("검색결과가 없습니다.");
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
      <div id="map_div">
      </div>
      <p id="result">결과 표시</p>
    </body>
</html>