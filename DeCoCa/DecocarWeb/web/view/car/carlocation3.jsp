<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://api2.sktelecom.com/tmap/js?version=1&format=javascript&appKey=a9ee13e1-cb7e-46a8-b144-14bfd0103a90"></script>




<script>
	var map;
	// 페이지가 로딩이 된 후 호출하는 함수입니다.
	function initTmap(){
		// map 생성
		// Tmap.map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
		map = new Tmap.Map({div:'map_div', width:'100%', height:'800px'});
		var tData = new Tmap.TData();//REST API 에서 제공되는 경로, 교통정보, POI 데이터를 쉽게 처리할 수 있는 클래스입니다.
		
		var s_lonLat = new Tmap.LonLat(127.033131, 37.508390); //시작 좌표입니다.   
		var e_lonLat = new Tmap.LonLat(126.975598, 37.554034); //도착 좌표입니다.
		var optionObj = {
				reqCoordType:"WGS84GEO", //요청 좌표계 옵셥 설정입니다.
				resCoordType:"EPSG3857"  //응답 좌표계 옵셥 설정입니다.
             }
		
		tData.getRoutePlan(s_lonLat, e_lonLat, optionObj);//경로 탐색 데이터를 콜백 함수를 통해 XML로 리턴합니다.
		
		tData.events.register("onComplete", tData, onComplete);//데이터 로드가 성공적으로 완료되었을 때 발생하는 이벤트를 등록합니다.
		tData.events.register("onProgress", tData, onProgress);//데이터 로드중에 발생하는 이벤트를 등록합니다.
		tData.events.register("onError", tData, onError);//데이터 로드가 실패했을 떄 발생하는 이벤트를 등록합니다.
		
	}
	
	//데이터 로드가 성공적으로 완료되었을 때 발생하는 이벤트 함수 입니다. 
	function onComplete(){
		console.log(this.responseXML); //xml로 데이터를 받은 정보들을 콘솔창에서 확인할 수 있습니다.
			      
		var kmlForm = new Tmap.Format.KML({extractStyles:true}).read(this.responseXML);
		var vectorLayer = new Tmap.Layer.Vector("vectorLayerID");
		vectorLayer.addFeatures(kmlForm);
		map.addLayer(vectorLayer);
		//경로 그리기 후 해당영역으로 줌  
		map.zoomToExtent(vectorLayer.getDataExtent());
	   }
	//데이터 로드중 발생하는 이벤트 함수입니다.
	function onProgress(){
		//alert("onComplete");
	}
	//데이터 로드시 에러가 발생시 발생하는 이벤트 함수입니다.
	function onError(){
		alert("onError");
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