<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- java에서 array list 가져오기 -->
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<script
	src="https://api2.sktelecom.com/tmap/js?version=1&format=javascript&appKey=a9ee13e1-cb7e-46a8-b144-14bfd0103a90"></script>
<script type="text/javascript"></script>
<!-- Jquery JS-->
<script src="vendor/jquery-3.2.1.min.js"></script>


<head>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap')
	;

a 링크 속성 없애기 -->a:link {
	color: inherit;
	text-decoration: none;
}

a:visited {
	color: inherit;
	text-decoration: none;
}

a:hover {
	color: blue;
	text-decoration: underline;
}

#page_title {
	font-family: 'Lalezar', cursive;
	text-align: center;
}

.column-4 {
	padding: 10px;
}

.table_image {
	width: 100px;
	height: 100px;
	text-align: center;
	margin: 0 auto;
}

table>tr>td {
	margin: 0 auto;
	vertical-align: center;
}

#div_temp{
	text-align: -webkit-center;
}
</style>

<title>Car Status Detail[admin]</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.png" />
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
	href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->

</head>
<body class="animsition">

	<h1 id="page_title">
		Car Status Detail<br> <br>
	</h1>

	<form class="bg0  p-b-85">
		<div class="container">

			<div class="row">
				<div class="m-lr-auto m-b-50">
					<!-- col-lg-10 col-xl-7 (remove) -->
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart" style="overflow: hidden">

							<!-- #################################################################################### -->
							<!-- Table One -->
							<!-- Car's basic information -->
							<table class="table-shopping-cart">
								<tr class="table_row">
									<!-- Car Type -->
									<c:choose>
										<c:when test="${car.cartype == 0}">
											<td class="column-1" rowspan="2"><br> <img
												src="img/CarStatus/car.png" class="table_image"></td>
										</c:when>

										<c:when test="${car.cartype == 1}">
											<td class="column-1" rowspan="2"><br> <img
												src="img/CarStatus/carBox.png" class="table_image"></td>
										</c:when>
									</c:choose>

									<!-- Car Number -->
									<td class="column-1" class="tableone">Car Number</td>
									<td class="column-2" class="tableone">${car.carnumber}</td>
								</tr>


								<!-- Car's Calendar ID -->
								<tr class="table_row">
									<td class="column-1" class="tableone">Calendar ID</td>
									<td class="column-2" class="tableone">${car.calid}</td>
								</tr>

							</table>
							<br>

							<!-- #################################################################################### -->
							<!-- Table Two -->
							<!-- HEAD -->
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Engine</th>
									<th class="column-2">Door</th>
									<th class="column-3">Battery</th>
									<th class="column-4">Speed</th>
								</tr>

								<!-- IMAGE -->
								<tr class="table_row">

									<!-- Engine -->
									<c:choose>
										<c:when test="${engine == 0}">
											<td class="column-1"><br> <img
												src="img/CarStatus/off.png" class="table_image"></td>
										</c:when>

										<c:when test="${engine == 1}">
											<td class="column-1"><br> <img
												src="img/CarStatus/on.png" class="table_image"></td>
										</c:when>
									</c:choose>

									<!-- Door -->
									<c:choose>
										<c:when test="${door == 0}">
											<td class="column-2"><br> <img
												src="img/CarStatus/locked.png" class="table_image"></td>
										</c:when>

										<c:when test="${door == 1}">
											<td class="column-2"><br> <img
												src="img/CarStatus/unlocked.png" class="table_image"></td>
										</c:when>
									</c:choose>


									<!-- Battery -->
									<c:choose>
										<c:when test="${battery == 0}">
											<td class="column-3"><br> <img
												src="img/CarStatus/battery/zero.png" class="table_image"></td>
										</c:when>

										<c:when test="${0 < battery && battery <= 25}">
											<td class="column-3"><br> <img
												src="img/CarStatus/battery/one.png" class="table_image"></td>
										</c:when>

										<c:when test="${25 < battery  && battery <= 75}">
											<td class="column-3"><br> <img
												src="img/CarStatus/battery/two.png" class="table_image"></td>
										</c:when>

										<c:when test="${75 < battery && battery <= 100}">
											<td class="column-7"><br> <img
												src="img/CarStatus/battery/full.png" class="table_image"></td>
										</c:when>
									</c:choose>

									<!-- Speed -->
									<td class="column-4"><img src="img/CarStatus/speed.png"
										class="table_image"></td>
								</tr>
								<!-- ----------------------------------------------------------------------------- -->
								<!-- DATA -->
								<tr class="table_row">
									<!-- Engine -->
									<c:choose>
										<c:when test="${engine == 0}">
											<td class="column-1">OFF</td>
										</c:when>

										<c:when test="${engine == 1}">
											<td class="column-1">ON</td>
										</c:when>
									</c:choose>

									<!-- Door -->
									<c:choose>
										<c:when test="${door == 0}">

											<td class="column-2"><a href="changeStatus.mc?carid=${car.carid}&msg=door">Locked</a></td>
										</c:when>

										<c:when test="${door == 1}">
											<td class="column-2"><a href="changeStatus.mc?carid=${car.carid}&msg=door">UnLocked</a></td>

										</c:when>
									</c:choose>

									<!-- Battery -->
									<td class="column-3">${battery }%</td>
									<!-- Speed -->
									<td class="column-4">${speed }km/h</td>
								</tr>

							</table>
							<br>

							<!-- #################################################################################### -->
							<!-- Table Three -->
							<table class="table-shopping-cart">
								<!-- ----------------------------------------------------------------------------- -->
								<!-- IMAGE -->
								<tr class="table_head">
									<th class="column-1">SeatBelt</th>
									<th class="column-2">Temperature</th>
									<th class="column-3">Pressure</th>
									<th class="column-4">Brake</th>

								</tr>

								<tr class="table_row">
									<!-- SeatBelt -->
									<td class="column-1"><br> <img
										src="img/CarStatus/seat-belt.png" class="table_image"></td>
									<!-- Temperature -->
									<td class="column-1"><br> <img
										src="img/CarStatus/thermometer.png" class="table_image"></td>
									<!-- Pressure -->
									<td class="column-3"><br> <img
										src="img/CarStatus/wheel.png" class="table_image"></td>
									<!-- Brake -->
									<td class="column-4"><br> <img
										src="img/CarStatus/brake.png" class="table_image"></td>
								</tr>
								<!-- ----------------------------------------------------------------------------- -->
								<!-- DATA -->
								<tr class="table_row">
									<!-- SeatBelt -->
									<c:choose>
										<c:when test="${seatbelt == 0}">
											<td class="column-1">Safety</td>
										</c:when>

										<c:when test="${seatbelt == 1}">
											<td class="column-1">UnSafety</td>
										</c:when>
									</c:choose>

									<!-- Temperature -->
									<td class="column-2">
									<div id="div_temp">
										<a id="slider_val_view">${temperature }</a>
										<br><br>										
										<input type="range" name="temper" min="18" max="28" class="slider_range">
										<br>
										<input type="button" onclick="tempclick()" value="OK">
									</div>
									</td>

									<!-- Pressure -->
									<td class="column-3">${pressure}kPa</td>

									<!-- Brake -->
									<c:choose>
										<c:when test="${brake == 0}">
											<td class="column-1">Normal</td>
										</c:when>

										<c:when test="${brake == 1}">
											<td class="column-1">BreakDown</td>
										</c:when>
									</c:choose>
								</tr>
							</table>

						</div>

						<div id="map_div"></div>
					</div>

				</div>

			</div>

		</div>

	</form>

	<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
	<script>
		$(".js-select2").each(function() {
			$(this).select2({
				minimumResultsForSearch : 20,
				dropdownParent : $(this).next('.dropDownSelect2')
			});
		})
	</script>
	<!--===============================================================================================-->
	<script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<!--===============================================================================================-->
	<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
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

	<!-- Map Function -->
	<script>
		var map;
		var la;
		var lo;
		var result;
		var test = "${path}";
		var obj = JSON.parse('${json}');
		var marker;
		var cnt=0;
		var markerLayer;
		var markerLayer2;
		// 페이지가 로딩이 된 후 호출하는 함수입니다.
		var arr = new Array();
		var arr2 = new Array();
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
				height : '400px' // map의 height 설정
			});
			markerLayer = new Tmap.Layer.Markers();//마커 레이어 생성
			map.addLayer(markerLayer);//map에 마커 레이어 추가
			
			//index와 아이템
			$(obj).each(function(index, item){
					la = item.lat;
					lo = item.lng;
					result = item.idx;
					var lonlat = new Tmap.LonLat(la, lo).transform("EPSG:4326","EPSG:3857");//좌표 설정
					arr2[index] = lonlat;
					//makers.clearMarkers();
			var size = new Tmap.Size(24, 38);//아이콘 크기 설정
			map.setCenter(lonlat, 14);//map의 중심 좌표 설정
			var offset = new Tmap.Pixel(-(size.w / 2), -(size.h));
			var icon = new Tmap.Icon(
					'http://tmapapis.sktelecom.com/upload/tmap/marker/pin_b_m_a.png',
					size, offset);//마커 아이콘 설정
			arr[index] = new Tmap.Marker(lonlat, icon);//마커 생성
			//markerLayer.addMarker(arr[index]);//레이어에 마커 추가
			// marker = new Tmap.Marker(lonlat, icon);//마커 생성
			});
			function loop(x) {
				setTimeout(function() {
					markerLayer.removeMarker(arr[x-1])
					markerLayer.addMarker(arr[x]);//레이어에 마커 추가
					//map.setCenter(arr2[x], 13.5);//map의 중심 좌표 설정
					}, 2000 * x);
			}
			for (i = 1; i < arr.length; i++) {
				loop(i);
			}
			//markerLayer.removeMarker(marker); // 기존 마커 삭제
		}
		// 맵 생성 실행
		initTmap();
	</script>
	


<!-- temperature slide value -->
<script>
function ShowSliderVal(sVal){
	var obValView = document.getElementById("slider_val_view");
	obValView.innerHTML = sVal;
}
function tempclick(){
	var val= document.getElementById("slider_val_view").innerHTML;
	var url='changeStatus.mc?carid=${car.carid}&msg=temp_' + val;
	location.href=url;	
}
var RangeSlider = function(){
	var range = $('.slider_range');
	range.on('input',function(){
		ShowSliderVal(this.value);
	});
};
RangeSlider();
</script>
};
</body>



</html>