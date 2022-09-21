<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav">
	<li>
		<c:if test="${!empty user && !empty user.mem_photo}">
			<img src="${pageContext.request.contextPath}/member/photoView.do" width="25" height="25" class="my-photo">
		</c:if>
		<c:if test="${!empty user && empty user.mem_photo}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="25" height="25" class="my-photo">
		</c:if>
	</li>
	<li>
		<a href="${pageContext.request.contextPath}/notice/list.do">공지사항</a>
	</li>
	<li>
		<c:if test="${!empty user && !empty user.mem_name}">
			[<span class="user_name">${user.mem_name}</span>]
		</c:if>
		<c:if test="${!empty user && empty user.mem_name}">
			[<span class="user_name">${user.mem_id}</span>]
		</c:if>	
	</li>
	<li>
		<c:if test="${empty user}">
			<%-- <a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a> --%>
			<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
		</c:if>
	</li>
	<c:if test="${!empty user}">
		<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
		<li><a href="${pageContext.request.contextPath}/messanger/list.do">메신저</a></li>
		<li><a href="${pageContext.request.contextPath}/letter/main.do">쪽지</a></li>
		<li><a href="${pageContext.request.contextPath}/reservation/main.do">회의실예약</a></li>
		<li><a href="${pageContext.request.contextPath}/calendar/main.do">캘린더</a></li>
		<li><a href="${pageContext.request.contextPath}/workflow/list.do">전자결재</a></li>
		<li><a href="${pageContext.request.contextPath}/member/main.do">주소록</a></li>
	</c:if>
	<li>
		<c:if test="${!empty user && user.auth == 1}">
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
		</c:if>
	</li>
	<li>
		<c:if test="${!empty user && user.auth == 2}">
			<a href="${pageContext.request.contextPath}/member/admin_list.do">관리자페이지</a>
		</c:if>
	</li>
	<li>
		<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
	</li>
</ul>
