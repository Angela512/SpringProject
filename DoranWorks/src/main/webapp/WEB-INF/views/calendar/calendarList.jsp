<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>

<link href='${pageContext.request.contextPath}/resources/fullcalendar-5.11.3/lib/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/resources/fullcalendar-5.11.3/lib/main.js'></script>
<script type='text/javascript'>
	document.addEventListener('DOMContentLoaded', function() {
		
	  var calendarEl = document.getElementById('calendar');
	
	  var calendar = new FullCalendar.Calendar(calendarEl, {
	    googleCalendarApiKey: 'AIzaSyDx3LALjSusZMJgwTAO0oQG3Sa38RbS3rE',
	    eventSources: [
	  	 	 {
	          googleCalendarId: '22tpaeg3hfa354b6dpvtn2bgq4@group.calendar.google.com',
	          className: '웹디자인기능사',
	          color: '#be5683', //rgb,#ffffff 등의 형식으로 할 수 있어요.
	          //textColor: 'black' 
	        }
	    ]
	  });
	  calendar.render();
	});
</script>

<style>
	#calendar{
	   width:100%;
	   height:100%;
	}
</style>

<div class="page-main">

	<h2>캘린더</h2>
	<div id='calendar'></div>
	
</div>
<!-- 내용 끝 -->


