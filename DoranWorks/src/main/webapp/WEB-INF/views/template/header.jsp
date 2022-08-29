<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<h2 class="align-center">SpringPage</h2>
<div class="align-right">
	<a href="${pageContext.request.contextPath}/board/list.do">공지사항</a>
	<c:if test="${!empty user && !empty user.mem_photo}">
		<img src="${pageContext.request.contextPath}/member/photoView.do" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user && empty user.mem_photo}">
		<img src="${pageContext.request.contextPath}/images/face.png" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user && !empty user.mem_name}">
		[<span class="user_name">${user.mem_name}</span>]
	</c:if>
	<c:if test="${!empty user && empty user.mem_name}">
		[<span class="user_name">${user.mem_id}</span>]
	</c:if>
	<c:if test="${empty user}">
		<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
	</c:if>
	<c:if test="${!empty user}">
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		<a href="${pageContext.request.contextPath}/messanger/main.do">메신저</a>
		<a href="${pageContext.request.contextPath}/letter/main.do">쪽지</a>
		<a href="${pageContext.request.contextPath}/reservation/main.do">회의실예약</a>
		<a href="${pageContext.request.contextPath}/calendar/main.do">캘린더</a>
		<a href="${pageContext.request.contextPath}/workflow/list.do">전자결재</a>
		<a href="${pageContext.request.contextPath}/member/main.do">주소록</a>
		
	</c:if>
	<c:if test="${!empty user && user.auth == 1}">
		<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	</c:if>

	<c:if test="${!empty user && user.auth == 2}">
		<a href="${pageContext.request.contextPath}/member/admin.do">admin</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
</div>
<!-- 상단 끝 -->



