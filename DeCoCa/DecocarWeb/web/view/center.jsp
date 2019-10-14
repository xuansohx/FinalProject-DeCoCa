<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<style>
@import url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap');


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

.homebttext > h1 {
	font-family: 'Lalezar', cursive;
}

.homebttext > h6{
font-family: 'Noto Sans KR', sans-serif;
}

</style>

<div class="center_page">
	<div class="container">

		<div class="row isotope-grid">

			<div class="homepackage">
			<a href="schedule.mc">		
			<img src="img/taxi.png" class="homebtimg">
			<div class="homebttext">
			<h1><a href ="schedule.mc">Smart Taxi</h1></a>
			<h6>내 캘린더 일정에 맞춰</h6>
			<h6>움직이는 스마트 택시</h6>
			</div>
			</div>
			
	<div class="homepackage">
			<a href="schedule.mc">
			
			<img src="img/pickup.png" class="homebtimg">
			<div class="homebttext">
			<h1><a href ="#">Pick up</h1></a>
			<h6>인증키를 활용한</h6>
			<h6>부모 안심 서비스</h6>
			</div>
			</div>

	<div class="homepackage" style="margin-bottom: 100px;">
	<a href="schedule.mc">
	
			<img src="img/box.png" class="homebtimg">
			<div class="homebttext">
			<h1><a href ="#">Quick</h1></a>
			<h6>보내는 사람과 받는 사람</h6>
			<h6>모두 확실한 서비스</h6>
			</div>
			</div>

		</div>
	</div>
</div>