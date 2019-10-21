<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://api2.sktelecom.com/tmap/js?version=1&format=javascript&appKey=a9ee13e1-cb7e-46a8-b144-14bfd0103a90"></script>
<script type="text/javascript"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datetimepicker.min.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datetimepicker.css" media="all" />

<!-- Map Function -->
<script>
//map 생성
//Tmap.map을 이용하여, 지도가 들어갈 div, 넓이, 높이를 설정합니다.
var map;
var tData, tData1;
function initTmap(){
	$("#map_div").empty();
map = new Tmap.Map({
	div : "map_div", // map을 표시해줄 div
	width : "100%", // map의 width 설정
	height : "380px", // map의 height 설정
});
map.setCenter(new Tmap.LonLat(lon, lat).transform("EPSG:4326", "EPSG:3857"), 15);//설정한 좌표를 "EPSG:3857"로 좌표변환한 좌표값으로 즁심점으로 설정합니다.
var routeLayer = new Tmap.Layer.Vector("route");//벡터 레이어 생성
var markerLayer = new Tmap.Layer.Markers("start");// 마커 레이어 생성
map.addLayer(routeLayer);//map에 벡터 레이어 추가
map.addLayer(markerLayer);//map에 마커 레이어 추가
//시작
var size = new Tmap.Size(24, 38);//아이콘 크기 설정
var offset = new Tmap.Pixel(-(size.w / 2), -size.h);//아이콘 중심점 설정
var icon = new Tmap.IconHtml('<img src=http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_s.png />', size, offset);//마커 아이콘 설정
var marker_s = new Tmap.Marker(new Tmap.LonLat(lon, lat).transform("EPSG:4326", "EPSG:3857"), icon);//설정한 좌표를 "EPSG:3857"로 좌표변환한 좌표값으로 설정합니다.
markerLayer.addMarker(marker_s);//마커 레이어에 마커 추가

//도착
var icon = new Tmap.IconHtml('<img src=http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_e.png />', size, offset);//마커 아이콘 설정
var marker_e = new Tmap.Marker(new Tmap.LonLat(lon1, lat1).transform("EPSG:4326", "EPSG:3857"), icon);//설정한 좌표를 "EPSG:3857"로 좌표변환한 좌표값으로 설정합니다.
markerLayer.addMarker(marker_e);//마커 레이어에 마커 추가

var headers = {}; 
headers["appKey"]="a9ee13e1-cb7e-46a8-b144-14bfd0103a90";//실행을 위한 키 입니다. 발급받으신 AppKey(appKey)를 입력하세요.
$.ajax({
	method:"POST",
	headers : headers,
	url:"https://apis.openapi.sk.com/tmap/routes?version=1&format=xml",//자동차 경로안내 api 요청 url입니다.
	async:false,
	data:{
		//출발지 위경도 좌표입니다.
		startX : lon,
		startY : lat,
		
		/* startX : "126.9850380932383",
		startY : "37.566567545861645", */
		//목적지 위경도 좌표입니다.
		endX : lon1,
		endY : lat1,
		//출발지, 경유지, 목적지 좌표계 유형을 지정합니다.
		reqCoordType : "WGS84GEO",
		resCoordType : "EPSG3857",
		//경로 탐색 옵션 입니다.
		searchOption : 1,
		//교통정보 포함 옵션입니다.
		trafficInfo : "Y"
		
	},

	//데이터 로드가 성공적으로 완료되었을 때 발생하는 함수입니다.
	success:function(response){
		prtcl = response;
		
		// 결과 출력
		var innerHtml ="";
		var prtclString = new XMLSerializer().serializeToString(prtcl);//xml to String	
		xmlDoc = $.parseXML( prtclString ),
		$xml = $( xmlDoc ),
		$intRate = $xml.find("Document");
 	
		var tDistance = " 총 거리 : "+($intRate[0].getElementsByTagName("tmap:totalDistance")[0].childNodes[0].nodeValue/1000).toFixed(1)+"km,";
		var tTime = " 총 시간 : "+($intRate[0].getElementsByTagName("tmap:totalTime")[0].childNodes[0].nodeValue/60).toFixed(0)+"분,";
		document.getElementById("eTime").value = ($intRate[0].getElementsByTagName("tmap:totalTime")[0].childNodes[0].nodeValue/60).toFixed(0);
		var tFare = " 총 요금 : "+$intRate[0].getElementsByTagName("tmap:totalFare")[0].childNodes[0].nodeValue+"원,";	
		var taxiFare = " 예상 택시 요금 : "+$intRate[0].getElementsByTagName("tmap:taxiFare")[0].childNodes[0].nodeValue+"원";	

		$("#result").text(tDistance+tTime+tFare+taxiFare);
		
		routeLayer.removeAllFeatures();//레이어의 모든 도형을 지웁니다.
				
		var traffic = $intRate[0].getElementsByTagName("traffic")[0];
		//교통정보가 포함되어 있으면 교통정보를 포함한 경로를 그려주고
		//교통정보가 없다면  교통정보를 제외한 경로를 그려줍니다.
		if(!traffic){
			var prtclLine = new Tmap.Format.KML({extractStyles:true, extractAttributes:true}).read(prtcl);//데이터(prtcl)를 읽고, 벡터 도형(feature) 목록을 리턴합니다.
			
			//표준 데이터 포맷인 KML을 Read/Write 하는 클래스 입니다.
			//벡터 도형(Feature)이 추가되기 직전에 이벤트가 발생합니다.
			routeLayer.events.register("beforefeatureadded", routeLayer, onBeforeFeatureAdded);
	        function onBeforeFeatureAdded(e) {
		        	var style = {};
		        	switch (e.feature.attributes.styleUrl) {
		        	case "#pointStyle":
			        	style.externalGraphic = "http://topopen.tmap.co.kr/imgs/point.png"; //렌더링 포인트에 사용될 외부 이미지 파일의 url입니다.
						style.graphicHeight = 16; //외부 이미지 파일의 크기 설정을 위한 픽셀 높이입니다.
						style.graphicOpacity = 1; //외부 이미지 파일의 투명도 (0-1)입니다.
						style.graphicWidth = 16; //외부 이미지 파일의 크기 설정을 위한 픽셀 폭입니다.
		        	break;
		        	default:
						style.strokeColor = "#ff0000";//stroke에 적용될 16진수 color
						style.strokeOpacity = "1";//stroke의 투명도(0~1)
						style.strokeWidth = "5";//stroke의 넓이(pixel 단위)
		        	};
	        	e.feature.style = style;
	        }
			
			routeLayer.addFeatures(prtclLine); //레이어에 도형을 등록합니다.
		}else{
			//기존 출발,도착지 마커릉 지웁니다.
			markerLayer.removeMarker(marker_s); 
			markerLayer.removeMarker(marker_e);
			routeLayer.removeAllFeatures();//레이어의 모든 도형을 지웁니다.
			
			//-------------------------- 교통정보를 그려주는 부분입니다. -------------------------- 
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
			
			map.addLayer(routeLayer); // 지도에 백터 레이어 추가
			//-------------------------- 교통정보를 그려주는 부분입니다. -------------------------- 
		}
		
		map.zoomToExtent(routeLayer.getDataExtent());//map의 zoom을 routeLayer의 영역에 맞게 변경합니다.	
	},
	//요청 실패시 콘솔창에서 에러 내용을 확인할 수 있습니다.
	error:function(request,status,error){
		console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	}
});
}/////////////////맵생성하기 
///////////////////////////////////////////한글값 좌표 받아오기
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
			if(cnt == 1)
				{
			 lon = data.searchPoiInfo.pois.poi[0].noorLon;//data에서 받아온  lon 좌표값 입니다.
			 lat = data.searchPoiInfo.pois.poi[0].noorLat;//data에서 받아온  lat 좌표값 입니다.
			 name = data.searchPoiInfo.pois.poi[0].name;//data에서 받아온  명칭입니다.
			 document.getElementById("sLng").value = lon;
			 document.getElementById("sLat").value = lat;
				}
			if(cnt == 2)
			{
		 lon1 = data.searchPoiInfo.pois.poi[0].noorLon;//data에서 받아온  lon 좌표값 입니다.
		 lat1 = data.searchPoiInfo.pois.poi[0].noorLat;//data에서 받아온  lat 좌표값 입니다.
		 name1 = data.searchPoiInfo.pois.poi[0].name;//data에 받아온  명칭입니다.
		 	document.getElementById("eLng").value = lon1;
		 	document.getElementById("eLat").value = lat1;
			}
			 
		}
	});
} 
var cnt = 0;
var lon,lon1;
var lat,lat1;
var name, name1;
var temp1;
var temp2;

function input1(){
	cnt = 1;
	var input1 = document.getElementById("schedulelocationstart").value;
	temp1 = input1;
	tData = new Tmap.TData();
	tData.events.register("onComplete", tData, onComplete);
	tData.getAutoCompleteSearch(temp1, 5);
}
function input2(){
	cnt = 2;
	var input2 = document.getElementById("schedulelocationend").value;
	temp2 = input2;
	tData = new Tmap.TData();
	tData.events.register("onComplete", tData, onComplete);
	tData.getAutoCompleteSearch(temp2, 5);
	setTimeout(function() {initTmap();}, 500);
	
}
</script>
<!-- select 변경 옵션 -->
<script>
	function changeStype() {
		var typeSelect = document.getElementById("scheduletype");
		var typeValue = typeSelect.options[typeSelect.selectedIndex].value;
		console.log(typeValue);
		change(typeValue);
		/* reIdCheck() */
	};

	$(document).ready(function() {
		var typeSelect = document.getElementById("scheduletype");
		var typeValue = typeSelect.options[typeSelect.selectedIndex].value;
		console.log(typeValue);
		change(typeValue);
		reIdCheck();
	});

	function change(typeValue) {
		var rePart = document.getElementById("receive");

		if (typeValue == 1) {
			console.log("스마트택시 선택");
			$("#receive").hide();
			$('input[name="reuserid"]').val('');			
			$('input[name="schesubmit"]').removeAttr('disabled');
		} else if (typeValue == 2) {
			console.log("픽업 선택");
			$("#receive").show();
			$('input[name="schesubmit"]').attr('disabled', 'disabled');
		} else if (typeValue == 3) {
			console.log("퀵  선택");
			$("#receive").show();
			$('input[name="schesubmit"]').attr('disabled', 'disabled');
		}
	};

	function reIdCheck() {
		$('input[name="idceck"]')
				.click(
						function() {
							var reuserid = $('input[name="reuserid"]').val();
							$
									.ajax({
										url : "usercheckId.mc",
										data : {
											'userid' : reuserid
										},
										method : "POST",
										success : function(result) {
											if (result == '1') {
												alert("확인되었습니다.");
												$('input[name="schesubmit"]')
														.removeAttr('disabled');
												$('.idsame')
														.html(
																'<span style="color:red"></span>');
												return false;
											} else if (result == '0') {
												alert("존재하지 않는 사용자입니다.");
												$('input[name="schesubmit"]')
														.attr('disabled',
																'disabled');
												$('.idsame')
														.html(
																'<span style="color:red">존재하지 않는 사용자입니다.</span>');
												return false;
											}
										}
									});
						});
	};
</script>
<head>
<meta charset="EUC-KR">
<title>DeCoCa</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="img/icon.png" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/MagnificPopup/magnific-popup.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->


<style>
@import url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap');

#schedulememo {
	resize: none;
}

#page_title {
	font-family: 'Lalezar', cursive;
	text-align: center;
}
</style>

</head>

<body class="animsition">
	<!-- Header -->
	<header>
		<div class="wrap-menu-desktop">


			<nav class="limiter-menu-desktop container">

				<!-- Logo desktop -->
				<a href="main.mc" class="logo"> <img src="img/icon.png"
					alt="IMG-LOGO" href="main.mc">
				</a>

				<!-- Menu desktop -->
				<div class="menu-desktop">
					<ul class="main-menu">
						<li class="active-menu"><a href="main.mc">HOME</a></li>
						<!-- <li>
							<a href="cart.mc">CART</a>
						</li> -->
					</ul>
				</div>

				<!-- Icon header 로그인(OOO님),회원가입(로그아웃) -->
				<div class="wrap-icon-header flex-w flex-r-m">

					<c:choose>
						<c:when test="${loginuser.usertype eq '1' }">
							<ul class="main-menu">
								<li><a href="">${loginuser.userid} </a></li>
								<li><a href="logout.mc">LOGOUT</a></li>
								<li><a href="customerupdate.mc?userid=${loginuser.userid}">회원정보수정</a></li>
								<li><a href="proregister.mc">PRO REGISTER</a></li>
								<li><a href="product_list.mc">PRODUCT LIST</a></li>
							</ul>
						</c:when>
						<c:when test="${loginuser.usertype eq null }">
							<ul class="main-menu">
								<li><a href="login.mc">LOGIN</a></li>
								<li><a href="uregister.mc">REGISTER</a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="main-menu">
								<li><a>${loginuser.userid} </a></li>
								<li><a href="mypage.mc">MyPage</a></li>
								<li><a href="logout.mc">LOGOUT</a></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</nav>
		</div>
	</header>


	<!-- 여백 -->
	<div class="sec-banner bg0 p-t-80 p-b-50">
		<div class="container">
			<div class="row"></div>
		</div>
	</div>



	<form name="scheduleform" action="schregisterimpl.mc" method="POST">
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td align="center" id="page_title"><h1>Reservation</h1> <br></td>
			</tr>
		</table>
    	
		<div class="flex-w flex-tr">
			<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
			
				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">예약 날짜</label> 
					<input
						class="size-111 bor8 stext-102 cl2 p-lr-20 form-control"
						id="scheduledate" type="text" name="calDate" required="required">
				</div>
		

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">일정 이름</label> 
					<input
						class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="schedulename" type="text" name="calName" required="required">
				</div>
        
				<div class="col-sm-6 p-b-5 m-lr-auto">
					<label class="stext-102 cl3">출발지 정보</label> 
					<div class="button">
					<input
						class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="schedulelocationstart" type="text" name="sAddress" required="required" dislplay="inline-block">
						<img src="img/mapButton.png" type="button" onclick="input1()" dislplay="inline-block">
						<!-- <input type="button" class="flex-c-m m-lr-auto stext-101 cl0 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer"
							onclick="input1()" style="width: 300px; height: 50px" value="등록"> -->
							</div>
					<input type="hidden" name="sLat" id="sLat">
					<input type="hidden" name="sLng" id="sLng">
				</div>
		
				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">도착지 정보</label> 
					<input	class="size-111 bor8 stext-102 cl2 p-lr-20"
					id="schedulelocationend" type="text" name="eAddress" required="required">
					<input type="button" class="flex-c-m m-lr-auto stext-101 cl0 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer"
					onclick="input2()"	style="width: 300px; height: 50px" value="등록">
					<input type="hidden" name="eLat" id="eLat">
					<input type="hidden" name="eLng" id="eLng">
					<input type="hidden" name="eTime" id="eTime">
				</div>
        
				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">서비스 타입</label> 
					<select id="scheduletype" class="input size-111 bor8 stext-102 cl2 p-lr-20"
					name="sStyle" onchange="changeStype()">
						<!-- 선택된 서비스를 기본서비스로 해볼까? -->
						<!-- <option value="~~" <c:if test="${stype==1}"> selected </c:if> > ~~ </option> -->
						<option value="1" <c:if test="${stype==1}"> selected </c:if>>스마트 택시 서비스</option>
						<option value="2" <c:if test="${stype==2}"> selected </c:if>>픽업 서비스</option>
						<option value="3" <c:if test="${stype==3}"> selected </c:if>>퀵 서비스</option>					
				</select>		
				</div>
		
				<div class="col-sm-6 p-b-5 m-lr-auto" id="receive">
					<label class="stext-102 cl3">받는 사람</label> 
					<input
						class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="schedulereceiver" type="text" name="reuserid"  >
          <input type="button"
					name="idceck" value="사용자확인" /> &nbsp;&nbsp; <span class="idsame"></span>
				</div>

		  	<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">메모</label> 
					<textarea class="size-111 bor8 stext-102 cl2 p-lr-20" id="schedulememo" name="memo"></textarea>
				</div>
			</div>
		</div>
			<label class="stext-102 cl3"></label> 
			<input type="submit" id="submitbt" name="schesubmit"			
							class="flex-c-m m-lr-auto stext-101 cl0 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer"
							style="width: 300px; height: 50px" value="등록">

		<input type="hidden" name="userid" value="${loginuser.userid}">
	</form>

	<br>
	<br>

	<!-- 맵 추가된 부분 테이블 안에 넣어둠 -->
	<div id="map_div"></div>
	<p id="result"></p>

	<!-- Footer -->

	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->


	<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/slick/slick.min.js"></script>
	<script src="js/slick-custom.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/parallax100/parallax100.js"></script>
	<script>
		$('.parallax100').parallax100();
	</script>
	<!--===============================================================================================-->
	<script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<script>
		$('.gallery-lb').each(function() { // the containers for all your galleries
			$(this).magnificPopup({
				delegate : 'a', // the selector for gallery item
				type : 'image',
				gallery : {
					enabled : true
				},
				mainClass : 'mfp-fade'
			});
		});
	</script>
	<!--===============================================================================================-->
	<script src="vendor/isotope/isotope.pkgd.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/sweetalert/sweetalert.min.js"></script>

	<!--===============================================================================================-->
	<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$(function() {
			$('#scheduledate').datetimepicker({
				/* 날짜 포맷변경 */
				format : 'YYYY-MM-DD HH:mm',

				/* 오늘, reset, 닫기 버튼 만들기*/
				showTodayButton : true,
				showClear : true,
				showClose : true,

				/* 날짜선택, 시간선택 한번에 나오게 하기 */
				/* sideBySide: true */

				/* 위치조절 */
				widgetPositioning : {
					horizontal : 'left',
					vertical : 'bottom'
				}

			});
		});
	</script>

	<script>
		$('.js-pscroll').each(function() {
			$(this).css('position', 'relative');
			$(this).css('overflow', 'hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed : 1,
				scrollingThreshold : 1000,
				wheelPropagation : false,
			});

			$(window).on('resize', function() {
				ps.update();
			})
		});
	</script>
	<!--===============================================================================================-->
	<script src="js/main.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js"
		type="text/javascript"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>



	<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>

</body>

</html>