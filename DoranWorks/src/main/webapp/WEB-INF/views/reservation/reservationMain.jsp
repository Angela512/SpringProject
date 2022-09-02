<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

<link href='${pageContext.request.contextPath}/resources/fullcalendar-5.11.3/lib/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/resources/fullcalendar-5.11.3/lib/main.js'></script>
<script type='text/javascript'>
document.addEventListener('DOMContentLoaded', function() {
	
	  var calendarEl = document.getElementById('calendar');
	
	  var calendar = new FullCalendar.Calendar(calendarEl, {
	    googleCalendarApiKey: 'AIzaSyDlDsMct6iXMHFllJDBYkSCUtwGCBiBfZI',
	    eventSources: [
	  	 	 {
	          googleCalendarId: '17m58aainovd0rug3mo313755o@group.calendar.google.com',
	          className: '회의실예약',
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

	<h2>회의실 예약</h2>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input type="button" value="예약하기" onclick="location.href='write.do'">
	</div>
	</c:if>
	<div id='calendar'></div>
	
</div>
<!-- 내용 끝 -->


