<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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

.homebttext>a>h1 {
	font-family: 'Lalezar', cursive;
}

.homebttext>h6 {
	font-family: 'Noto Sans KR', sans-serif;
}
</style>

<div class="center_page">
	<div class="container">

		<div class="row isotope-grid">
			<c:choose>
				<%-- user mode --%>
				<c:when test="${loginuser.usertype eq '0' }">
					<div class="homepackage">
						<a href="schedule.mc?type=1"><img src="img/taxi.png"
							class="homebtimg"></a>
						<div class="homebttext">
							<a href="schedule.mc?type=1" id="sche1"><h1>Smart Taxi</h1></a>
							<h6>내 캘린더 일정에 맞춰</h6>
							<h6>움직이는 스마트 택시</h6>
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
						<a href="manageUser.mc"> <img src="img/manage_user.png"
							class="homebtimg"></a>
						<div class="homebttext">
							<a href="manageUser.mc"><h1>User</h1></a>
						</div>
					</div>

					<div class="homepackage">
						<a href="manageSche.mc"> <img src="img/manage_calendar.png"
							class="homebtimg"></a>
						<div class="homebttext">
							<a href="manageSche.mc"><h1>Reservation</h1></a>
						</div>
					</div>


					<div class="homepackage" style="margin-bottom: 100px;">
						<a href="manageCar.mc"> <img src="img/manage_car.png"
							class="homebtimg"></a>
						<div class="homebttext">
							<a href="manageCar.mc"><h1>Car</h1></a>
						</div>
					</div>

					<%-- CAR MAP --%>
					<div id="map_div"></div>
					<p id="result"></p>

				</c:when>

				<%-- general --%>
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
