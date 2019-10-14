<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
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

					</ul>
				</div>

				<!-- Icon header 로그인(OOO님),회원가입(로그아웃) -->
				<div class="wrap-icon-header flex-w flex-r-m">
					<c:choose>
						
						<%-- 관리자모드 로그인 --%>
						<c:when test="${loginuser.usertype eq '1'}">
							<ul class="main-menu">
								<li><a href="">${loginuser.userid} </a></li>
								<li><a href="logout.mc">LOGOUT</a></li>
								
								<%-- 사용자 관리 페이지 만들어야함 --%>
								<li><a href="customerupdate.mc?userid=${loginuser.userid}">회원관리</a></li>
								<!-- <li><a href="proregister.mc">차량관리</a></li> -->
								<!-- <li><a href="proregister.mc">PRO REGISTER</a></li>
								<li><a href="product_list.mc">PRODUCT LIST</a></li> -->
							</ul>
						</c:when>
						
						<%-- 일반모드 로그인 --%>						
						<c:when test="${loginuser.usertype eq null }">
							<ul class="main-menu">
								<li><a href="login.mc">LOGIN</a></li>
								<li><a href="uregister.mc">REGISTER</a></li>
								<li><a href="updateState.mc?car=10">test</a></li>
								<li><a href="updateStateAll.mc">testtest</a></li>
							</ul>
						</c:when>

						<%-- 사용자모드 로그인 --%>
						<c:otherwise>
							<ul class="main-menu">
								<li><a href="customerupdate.mc?userid=${loginuser.userid}">${loginuser.userid}
								</a></li>
								<li><a href="logout.mc">LOGOUT</a></li>
								<li><a href="schelist.mc">일정list</a></li>
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


	<!-- MainPage -->
	<section class="bg0 p-t-23 p-b-140" style="margin-top: 5%;">
		<c:choose>
			<c:when test="${center == null }">
				<jsp:include page="center.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="${center }.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</section>


	<!-- Footer -->

<!-- 	<footer class="bg3 p-t-75 p-b-32"
		style="position: fixed; bottom: 0; width: 100%; background-color: transparent;">
		<div class="container">
			<div class="p-t-40">
				<p class="stext-107 cl6 txt-center"></p>
			</div>
		</div>
	</footer> -->




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