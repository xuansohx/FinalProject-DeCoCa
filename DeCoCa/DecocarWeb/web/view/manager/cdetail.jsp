<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>


<style type="text/css">
@import url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap');

<!-- a 링크 속성 없애기 -->
a:link {
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

.table_image{
width: 100px;
height: 100px;
text-align: center;
margin: 0 auto;
}

table > tr > td{
margin: 0 auto;
vertical-align:center;
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

	<h1 id="page_title">Car Status Detail<br><br></h1>
	
	<form class="bg0  p-b-85">
		<div class="container">

			<div class="row">
				<div class="m-lr-auto m-b-50">
				<!-- col-lg-10 col-xl-7 (remove) -->
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart" style="overflow: hidden">

                            <!-- #################################################################################### -->
							<!-- Table One --> <!-- Car's basic information -->
							<table class="table-shopping-cart">
								<tr class="table_row">
								<!-- Car Type -->
									<c:choose>
										<c:when test="${car.cartype == 0}">
											<td class="column-1" rowspan="2"><br>
											<img src="img/CarStatus/car.png" class="table_image"></td>
										</c:when>

										<c:when test="${car.cartype == 1}">
											<td class="column-1" rowspan="2"><br>
											<img src="img/CarStatus/carBox.png" class="table_image"></td>
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
											<td class="column-1"><br><img
												src="img/CarStatus/off.png" class="table_image"></td>
										</c:when>

										<c:when test="${engine == 1}">
											<td class="column-1"><br><img
												src="img/CarStatus/on.png" class="table_image"></td>
										</c:when>
										</c:choose>

									<!-- Door -->
									<c:choose>
										<c:when test="${door == 0}">
											<td class="column-2"><br><img
												src="img/CarStatus/locked.png" class="table_image"></td>
										</c:when>

										<c:when test="${door == 1}">
											<td class="column-2"><br><img
												src="img/CarStatus/unlocked.png" class="table_image"></td>
										</c:when>
										</c:choose>
										
									
									<!-- Battery -->
									<c:choose>
										<c:when test="${battery == 0}">
											<td class="column-3"><br><img
												src="img/CarStatus/battery/zero.png" class="table_image"></td>
										</c:when>

										<c:when test="${0 < battery && battery <= 25}">
											<td class="column-3"><br><img
												src="img/CarStatus/battery/one.png" class="table_image"></td>
										</c:when>

										<c:when test="${25 < battery  && battery <= 75}">
											<td class="column-3"><br><img
												src="img/CarStatus/battery/two.png" class="table_image"></td>
										</c:when>

										<c:when test="${75 < battery && battery <= 100}">
											<td class="column-7"><br><img
												src="img/CarStatus/battery/full.png" class="table_image"></td>
										</c:when>
									</c:choose>
									
									<!-- Speed -->
									<td class="column-4"><img src="img/CarStatus/speed.png" class="table_image"></td>
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
											<td class="column-2">Locked</td>
										</c:when>

										<c:when test="${door == 1}">
											<td class="column-2">UnLocked</td>
										</c:when>
									</c:choose>

									<!-- Battery -->
									<td class="column-3">${battery } %</td> 
									<!-- Speed -->
									<td class="column-4">${speed } km/h</td> 
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
									<td class="column-1"><br><img
										src="img/CarStatus/seat-belt.png" class="table_image"></td>
									<!-- Temperature -->
									<td class="column-1"><br><img
										src="img/CarStatus/thermometer.png" class="table_image"></td>
									<!-- Pressure -->
									<td class="column-3"><br><img src="img/CarStatus/wheel.png"
										class="table_image"></td>
									<!-- Brake -->
									<td class="column-4"><br><img src="img/CarStatus/brake.png"
										class="table_image"></td>
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
									<td class="column-2">${temperature } ℃</td>

									<!-- Pressure -->
									<td class="column-3">${pressure} kPa</td>

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


</body>

</html>