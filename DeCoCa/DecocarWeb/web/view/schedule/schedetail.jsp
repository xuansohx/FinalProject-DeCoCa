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
	
<head>
<meta charset="EUC-KR">
<title>DeCoCar</title>
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
@import
	url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap')
	;

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
							</ul>
						</c:when>
						<c:when test="${loginuser.usertype eq null }">
							<ul class="main-menu">
								<li><a href="login.mc">LOGIN</a></li>
								<li><a href="curegister.mc">REGISTER</a></li>
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
<!-- 	<div class="sec-banner bg0 p-t-80 p-b-50">
		<div class="container">
			<div class="row"></div>
		</div>
	</div> -->



	<form name="scheduleform" action="schregisterimpl.mc" method="POST">
		<table style="margin-left: auto; margin-right: auto;">
			<tr>
				<td align="center" id="page_title"><h1>Reservation</h1> <br></td>
			</tr>
		</table>

		<div class="flex-w flex-tr">
			<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
				<div class="col-sm-6 p-b-5 m-lr-auto ">
						<label class="stext-102 cl3">예약 날짜</label> <input
						class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="scheduledate" type="text" name="calDate" required="required"
						readonly="readonly" value="${sch.calDate } ${sch.sTime}">
				</div>


				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">일정 이름</label> <input
						class="size-111 bor8 stext-102 cl2 p-lr-20" id="schedulename"
						type="text" name="calName" required="required" readonly="readonly" value="${sch.calName }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">출발지 정보</label> <input
						class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="schedulelocationstart" type="text" name="sAddress"
						required="required" readonly="readonly" value="${sch.sAddress }">
				</div>


				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">도착지 정보</label> <input
						class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="schedulelocationend" type="text" name="eAddress"
						required="required" readonly="readonly" value="${sch.eAddress }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">서비스 타입</label>

					<c:choose>

					<c:when test="${sch.sStyle==1}">
					<input id="scheduletype" type="text"
						class="input size-111 bor8 stext-102 cl2 p-lr-20" name="sStyle" readonly="readonly" value="스마트 택시 서비스">
					</c:when>

					<c:when test="${sch.sStyle==2}">
					<input id="scheduletype" type="text"
						class="input size-111 bor8 stext-102 cl2 p-lr-20" name="sStyle" readonly="readonly" value="픽업 서비스">
					</c:when>

					<c:when test="${sch.sStyle==3}">
					<input id="scheduletype" type="text"
						class="input size-111 bor8 stext-102 cl2 p-lr-20" name="sStyle" readonly="readonly" value="퀵 서비스">
					</c:when>

					</c:choose>
					

				</div>
				
				<!-- receive id -->				
				<c:choose>
				<c:when test="${sch.reuserid eq null }">
				</c:when>
				<c:otherwise>
				<div class="col-sm-6 p-b-5 m-lr-auto" id="receive">
					<label class="stext-102 cl3">받는 사람</label>
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="schedulereceiver" type="text" name="reuserid"
						readonly="readonly" value="${sch.reuserid }">
				</div>
				</c:otherwise>
				</c:choose>



				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">메모</label>
					<textarea class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="schedulememo" name="memo" readonly="readonly">${sch.memo }</textarea>
				</div>
				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">인증번호</label>
					<textarea class="size-111 bor8 stext-102 cl2 p-lr-20"
						id="scheduleNum" name="pinNum" readonly="readonly">${sch.pinNum }</textarea>
				</div>
			</div>
		</div>

		<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		<!-- 3 button : list, modify, delete -->

		<label class="stext-102 cl3"></label> <!-- <input type="submit"
			id="submitbt" name="schesubmit"
			class="flex-c-m m-lr-auto stext-101 cl0 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer"
			style="width: 300px; height: 50px" value="등록">  -->
			
			<input	type="button" id="listBt" name="listBt" value="list" class="flex-c-m m-lr-auto stext-101 cl0 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer"
			style="width: 300px; height: 50px" onclick="location.href='schelist.mc?userid=${sch.userid}'"> 
			&nbsp;
			<input type="button" id="modifyBt" name="modifyBt" value="modify" class="flex-c-m m-lr-auto stext-101 cl0 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer"
			style="width: 300px; height: 50px" onclick="location.href='scheupdate.mc?calid=${sch.calid}'"> 
			&nbsp;
			<input type="button" id="deleteBt" name="deleteBt" value="delete" class="flex-c-m m-lr-auto stext-101 cl0 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer"
			style="width: 300px; height: 50px" onclick="location.href='schedelete.mc?calid=${sch.calid}'">

		<!-- userid -->
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




</body>

</html>