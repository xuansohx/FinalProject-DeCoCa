<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style type="text/css">
@import url('https://fonts.googleapis.com/css?family=Lalezar|Noto+Sans+KR&display=swap');

#page_title {
	font-family: 'Lalezar', cursive;
	text-align: center;
}
</style>

	<h1 id="page_title">
		CHART
	</h1>
<br><br>
<div id="container1" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

<br><br>
<div id="container2" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

<script src="vendor/jquery/jquery-3.2.1.min.js"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script>
function chartStype(pdata){
	var chart = Highcharts.chart('container1', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: '서비스 이용 현황'
	    },
	    
	    subtitle: {
	        text: '어떤 서비스를 선호하시나요?'
	    },xAxis: {
	        categories: ['스마트택시 서비스', '픽업 서비스', '퀵 서비스']
	    },series: [{
	        colorByPoint: true,
	        data: pdata,
	        showInLegend: false
	    }]
	});
};

function chartStime(pdata){
	var chart = Highcharts.chart('container2', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: '서비스 이용시간대 비교'
	    },
	    subtitle: {
	        text: '주로 어느 시간대에 서비스를 이용하나요?'
	    },xAxis: {
	        categories: ['00 ~ 03', '03 ~ 06' , '06 ~ 09' , '09 ~ 12' , '12 ~ 15' , '15 ~ 18' , '18 ~ 21' , '22 ~ 24' ]
	    },series: [{
	        colorByPoint: true,
	        data: pdata,
	        showInLegend: false
	    }]
	});
};

$(document).ready(function(){
	$.ajax({
		url:"chartStype.mc",
		success:function(pdata){
			chartStype(pdata);			
		}
	});
	$.ajax({
		url:"chartStime.mc",
		success:function(pdata){
			chartStime(pdata);			
		}
	});
}); 
</script>
