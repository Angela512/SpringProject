<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<div class="page-main">
	<h2>예약하기</h2>
	<form:form action="write.do" modelAttribute="reservationVO"
	        id="register_form"
	        enctype="multipart/form-data">
	    <form:errors element="div" cssClass="error-color"/>    
		<ul>
			<li>
				<label for="reserve_title">회의명</label>
				<form:input path="reserve_title"/>
				<form:errors path="reserve_title" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="reserve_title">일시</label>
				<form:input path="reserve_title"/>
				<form:errors path="reserve_title" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="reserve_title">회의실</label>
				<form:input path="reserve_title"/>
				<form:errors path="reserve_title" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="reserve_title">참석자</label>
				<form:input path="reserve_title"/>
				<form:errors path="reserve_title" 
				             cssClass="error-color"/>
			</li>
		</ul>    
		<div class="align-center">
			<form:button>확인</form:button>
			<input type="button" value="취소" onclick="location.href='main.do'">
		</div>    
	</form:form>
</div>
<!-- 내용 끝 -->