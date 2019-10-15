<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
@import url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap');

#mypage_title {
font-family: 'Lalezar', cursive;
text-align: center;
}
#welcome{
font-family: 'Noto Sans KR', sans-serif;
text-align: center;
}

.mypage_package{
display: inline-block;
width: 250px;
margin-top: 20px;
}

.mypage_control{
text-align: center;
}

.mypage_img{
width: 250px;
height: 250px;
margin: 0 auto;
}

.mypage_text{
width: 250px;
	text-align: center;
	margin-top: 10px;
	margin-bottom: 10px;
	font-family: 'Noto Sans KR', sans-serif;
}


</style>

<!-- My Page Title -->
<h1 id="mypage_title">My Page</h1>
<br>
<h4 id="welcome">${loginuser.name}님 안녕하세요!</h4>
<br>


<div class="mypage_control">
<!-- 2. User Calendar List -->
<div class="mypage_package">
<img src="img/user_cal_list.png" class="mypage_img">
<h4 class="mypage_text">내 일정 조회</h4>
</div>

<!-- 3. User Update -->
<div class="mypage_package">
<a href="userupdate.mc?userid=${loginuser.userid}">
<img src="img/user_update.png" class="mypage_img">
<h4 class="mypage_text">내 정보 수정</h4>
</a>
</div>
</div>





