<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class ="center_page">
<h1> Carstatus List Page</h1>
<c:forEach var="cs" items ="${cslist }">
<h4> CAR ID : <a href="carmap.mc">${cs.statusid}</a>  위도 : ${cs.carlng}  경도 : ${cs.carlat} </h4>
</c:forEach>


<c:forEach var="re" items ="${relist }">
<h4> Reservation ID : <a href="#">${re.calid}</a>   메모 : ${re.memo}</a></h4>
</c:forEach>

</div>



