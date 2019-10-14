<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

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

<head>




	<title>DeCoCa</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="img/icon.png" />
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/MagnificPopup/magnific-popup.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<!--===============================================================================================-->


<style>
body {
	position: relative;
	text-align: center;
}

table {
	border-spacing: none;
}

.label {
	float: left;
	width: 120px;
	text-align: right;
	padding-right: 10px;
}

.form {
	width: 190px;
	text-align: center;
}

.form>.input {
	width: 190px;
}

.label2 {
	float: left;
	width: 120px;
	text-align: right;
	padding-right: 10px;
}
/* .form2 {
	width: 190px;
	height: 60px;
	line-height: 60px;
	text-align: center;
} */
#schedulememo {
	width: 190px;
}
</style>

</head>

<body class="animsition">


	<!-- Header -->
	<header>
		<div class="wrap-menu-desktop">
		

			<nav class="limiter-menu-desktop container">

				<!-- Logo desktop -->
				<a href="main.mc" class="logo">
					<img src="img/icon.png" alt="IMG-LOGO" href="main.mc">
				</a>

				<!-- Menu desktop -->
				<div class="menu-desktop">
					<ul class="main-menu">
						<li class="active-menu">
							<a href="main.mc">HOME</a>
						</li>
						<!-- <li>
							<a href="cart.mc">CART</a>
						</li> -->
					</ul>
				</div>

				<!-- Icon header 로그인(OOO님),회원가입(로그아웃) -->
				<div class="wrap-icon-header flex-w flex-r-m">
						<c:choose>
						<c:when test="${loginuser.usertype eq '1'.charAt(0) }">
<%-- 						<c:when test="${loginuser.CUSTOMER_ADMIN eq '1'.charAt(0) }"> --%>
							<ul class="main-menu">
								<li><a href="">${loginuser.userid} </a></li>	
<%-- 								<li><a href="">${loginuser.CUSTOMER_ID} </a></li>	 --%>
								<li><a href="logout.mc">LOGOUT</a></li>
								<li><a href="customerupdate.mc?userid=${loginuser.userid}">회원정보수정</a></li>
<%-- 								<li><a href="customerupdate.mc?CUSTOMER_ID=${loginuser.CUSTOMER_ID}">회원정보수정</a></li> --%>
								<li><a href="proregister.mc">PRO REGISTER</a></li>
								<li><a href="product_list.mc">PRODUCT LIST</a></li>
							</ul>
						</c:when>
						<c:when test="${loginuser.usertype eq null }">
<%-- 						<c:when test="${loginuser.CUSTOMER_ADMIN eq null }"> --%>
							<ul class="main-menu">
							<li><a href="login.mc">LOGIN</a></li>
							<li><a href="curegister.mc">REGISTER</a></li>
							</ul>
						</c:when>
						<c:otherwise >
							<ul class="main-menu">
								<li><a href="">${loginuser.userid} </a></li>
<%-- 								<li><a href="">${loginuser.CUSTOMER_ID} </a></li> --%>
								<li><a href="logout.mc">LOGOUT</a></li>
								<li><a href="customerupdate.mc?userid=${loginuser.userid}">회원정보수정</a></li>
<%-- 								<li><a href="customerupdate.mc?CUSTOMER_ID=${loginuser.CUSTOMER_ID}">회원정보수정</a></li> --%>
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
			<div class="row">
			</div>
		</div>
	</div>


<form name="scheduleform" action="schregisterimpl.mc" method="POST">
<!-- <form name="scheduleform" action="data.mc" method="POST"> -->
	<!-- <form name="scheduleform" method="POST"> -->
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td align="center"><b>일정 등록</b></td>
			</tr>
		</table>
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td class="label">예약 날짜</td>
				<td class="form"><input type="text" class="form-control"
					id="scheduledate" name="calDate"></td>
			</tr>			
			<tr>
				<td class="label">일정 이름</td>
				<td class="form"><input class="input" type="text"
					id="schedulename" name="calName"></td>
			</tr>
			<!-- <tr>
					<td class="label">일정 메모</td>
					<td class="form"><input type="text" id="schedulememo"></td>
				</tr> -->
			<tr>
				<td class="label">출발지 정보</td>
				<td class="form"><input class="input" type="text"
					id="schedulelocationstart" name="sAddress"></td>
			</tr>
			<tr>
				<td class="label">도착지 정보</td>
				<td class="form"><input class="input" type="text"
					id="schedulelocationend" name="eAddress"></td>
			</tr>
			<tr>
				<td class="label">서비스타입</td>
				<td class="form"><select id="scheduletype" class="input" name="sStyle">
						<!-- 선택된 서비스를 기본서비스로 해볼까? -->
						<!-- <option value="~~" selected>~~</option> -->
						<option value="1">스마트 택시 서비스</option>
						<option value="2">픽업 서비스</option>
						<option value="3">퀵 서비스</option>
<!-- 						<option value="smarttaxi">스마트 택시 서비스</option>
						<option value="pickup">픽업 서비스</option>
						<option value="quick">퀵 서비스</option> -->
				</select></td>
				<!-- <td class="form"><input type="text" id="scheduletype"></td> -->
			</tr>
			<!-- hidden으로 해놓고 서비스타입에 따라서 보였으면 좋겠다. -->
			<tr>
				<td class="label">받는사람</td>
				<td class="form"><input class="input" type="text"
					id="schedulereceiver" name="reuserid"></td>
			</tr>
			<tr>
				<td class="label2">메모</td>
				<td class="form2"><textarea id="schedulememo" name="memo" cols="10"></textarea></td>
				<!-- <td class="form2"><input type="text" id="schedulememo"></td> -->
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" style="width: 80px; height: 40px"
					value="등록" id="submitbt"></td>
			</tr>
		</table>
		<input type="hidden" name="userid" value="${loginuser.userid}">
	</form>


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
		$('.gallery-lb').each(function () { // the containers for all your galleries
			$(this).magnificPopup({
				delegate: 'a', // the selector for gallery item
				type: 'image',
				gallery: {
					enabled: true
				},
				mainClass: 'mfp-fade'
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
		$('.js-pscroll').each(function () {
			$(this).css('position', 'relative');
			$(this).css('overflow', 'hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed: 1,
				scrollingThreshold: 1000,
				wheelPropagation: false,
			});

			$(window).on('resize', function () {
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

<!-- <script type="text/javascript">
$(document).ready(function(){
	$("#submitbt").click(function(){
		console.log("byabya");
		/* out.println("bye"); */
		alert("bye"); 
		/* System.out.println("bye"); */
	});
}); // end document.ready 
</script> -->
</body>

</html>