<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<!--===============================================================================================-->
<!-- Manager's Schedule List Detail -->
<head>
<style>
@import url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap');

.column-4 {
	padding: 10px;
}
</style>


<!-- a 링크 속성 없애기 -->
<style type="text/css">
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
</style>

<title>Schedule List[admin]</title>
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

	<h1 id="page_title">Reservation Detail<br><br></h1>
	
		<form class="bg0  p-b-85">
		<div class="flex-w flex-tr">
			<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">일정 상태</label> 
					
					<c:choose>
					<c:when test="${r.calstatus == 0}">
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text"readonly="readonly" value="예정">
					</c:when>
					
					<c:when test="${r.calstatus == 1}">
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text"readonly="readonly" value="준비">
					</c:when>
					
					<c:when test="${r.calstatus == 2}">
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text"readonly="readonly" value="진행">
					</c:when>
					
					<c:when test="${r.calstatus ==3}">
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text"readonly="readonly" value="완료">
					</c:when>
					
					</c:choose>
				</div>
				
				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">사용자정보</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20" 
					type="text" readonly="readonly" value="${r.userid }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">일정 번호</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text"readonly="readonly" value="${r.calid}">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">배차 차량</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text" readonly="readonly" value="${r.carid}">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">서비스 타입</label>

					<c:choose>
					<c:when test="${r.sStyle == 1 }">
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text"readonly="readonly" value="스마트택시">
					</c:when>
					
					<c:when test="${r.sStyle == 2 }">
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text"readonly="readonly" value="픽업">
					</c:when>
					
					<c:when test="${r.sStyle == 3 }">
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text"readonly="readonly" value="퀵">
					</c:when>
					</c:choose>
					
				</div>


				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">예약 날짜</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text" readonly="readonly" value="${r.calDate }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">출발 시간</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text" readonly="readonly" value="${r.sTime }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">도착 시간</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text" readonly="readonly"  value="${r.eTime }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">출발지</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text" readonly="readonly" value="${r.sAddress }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">도착지</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text" readonly="readonly" value="${r.eAddress }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">받는 사람</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20" 
					type="text" readonly="readonly" value="${r.reuserid }">
				</div>

				<div class="col-sm-6 p-b-5 m-lr-auto ">
					<label class="stext-102 cl3">인증 번호</label> 
					<input class="size-111 bor8 stext-102 cl2 p-lr-20"
					type="text" readonly="readonly"value="${r.pinNum }">
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