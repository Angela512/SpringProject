<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 내용 시작 -->
<div class="page-main">
	<h2>회의실 예약</h2>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input type="button" value="예약하기" onclick="location.href='write.do'">
	</div>
	</c:if>

</div>
<!-- 내용 끝 -->






