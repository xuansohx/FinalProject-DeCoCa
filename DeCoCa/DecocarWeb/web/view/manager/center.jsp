<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Manager's main -->
<style>
@import
	url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap')
	;

.homepackage {
	width: 250px;
	margin: 0 auto;
	margin-top: 20px;
}

.homebtimg {
	width: 250px;
	height: 250px;
	margin: 0 auto;
}

.homebttext {
	width: 250px;
	margin: 0 auto;
	text-align: center;
	margin-top: 10px;
	margin-bottom: 10px;
}

.homebttext> a > h1 {
	font-family: 'Lalezar', cursive;
}

.homebttext > h6 {
	font-family: 'Noto Sans KR', sans-serif;
}
</style>

<div class="center_page">
	<div class="container">

		<div class="row isotope-grid">
			<c:choose>
				<%-- 사용자모드 --%>
				<c:when test="${loginuser.usertype eq '0' }">
					<div class="homepackage">
						<a href="schedule.mc?type=1"><img src="img/taxi.png"
							class="homebtimg"></a>
						<div class="homebttext">
							<a href="schedule.mc?type=1" id="sche1"><h1>Smart Taxi</h1></a>
							<!-- <h6>내 캘린더 일정에 맞춰</h6>
							<h6>움직이는 스마트 택시</h6> -->
						</div>
					</div>

					<div class="homepackage">
						<a href="schedule.mc?type=2"><img src="img/pickup.png"
							class="homebtimg"></a>
						<div class="homebttext">
							<a href="schedule.mc?type=2"><h1>Pick up</h1></a>
							<h6>인증키를 활용한</h6>
							<h6>부모 안심 서비스</h6>
						</div>
					</div>

					<div class="homepackage" style="margin-bottom: 100px;">
						<a href="schedule.mc?type=3"> <img src="img/box.png"
							class="homebtimg"></a>
						<div class="homebttext">
							<a href="schedule.mc?type=3"><h1>Quick</h1></a>
							<h6>보내는 사람과 받는 사람</h6>
							<h6>모두 확실한 서비스</h6>
						</div>
					</div>
				</c:when>
				
				<%-- manager mode --%>
				<c:when test="${loginuser.usertype eq '1' }">
					<div class="homepackage">
						
					</div>

					<div class="homepackage">
						
					</div>

					<div class="homepackage" style="margin-bottom: 100px;">
						
					</div>
				</c:when>

				<%-- 일반모드 --%>
				<c:otherwise>
					<div class="homepackage">
						<a href="login.mc"><img src="img/taxi.png" class="homebtimg"></a>
						<div class="homebttext">
							<a href="login.mc" id="sche1"><h1>Smart Taxi</h1></a>
							<h6>내 캘린더 일정에 맞춰</h6>
							<h6>움직이는 스마트 택시</h6>
						</div>
					</div>

					<div class="homepackage">
						<a href="login.mc"><img src="img/pickup.png" class="homebtimg"></a>
						<div class="homebttext">
							<a href="login.mc"><h1>Pick up</h1></a>
							<h6>인증키를 활용한</h6>
							<h6>부모 안심 서비스</h6>
						</div>
					</div>

					<div class="homepackage" style="margin-bottom: 100px;">
						<a href="login.mc"> <img src="img/box.png" class="homebtimg"></a>
						<div class="homebttext">
							<a href="login.mc"><h1>Quick</h1></a>
							<h6>보내는 사람과 받는 사람</h6>
							<h6>모두 확실한 서비스</h6>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
