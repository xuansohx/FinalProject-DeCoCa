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
<div id="container2" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

<script src="vendor/jquery/jquery-3.2.1.min.js"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script>

function big(pdata){
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
	        categories: ['1' , '2' , '3' , '4' , '5' , '6' , '7' 
	        	, '8' , '9' , '10' , '11' , '12' , '13' , '14' , '15' , '16' 
	        	, '17' , '18' , '19' , '20' , '21' , '22', '23' ]
	    },series: [{
	        colorByPoint: true,
	        data: pdata,
	        showInLegend: false
	    }]
	});
};

$(document).ready(function(){	
	$.ajax({
		url:"bigChart.mc",
		success:function(pdata){
			big(pdata);			
		}
	});
}); 
</script>
