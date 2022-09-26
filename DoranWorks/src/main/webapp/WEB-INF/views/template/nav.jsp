<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav_profile">
	<li>
		<c:if test="${!empty user && !empty user.mem_photo}">
			<img src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100" class="my-photo">
		</c:if>
		<c:if test="${!empty user && empty user.mem_photo}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="100" height="100" class="my-photo">
		</c:if>
	</li>
	<li>
		<c:if test="${!empty user && !empty user.mem_name}">
			<span class="user_name">${user.mem_name} ${user.mem_rank}<c:if test="${!empty user.mem_dpt}"> | ${user.mem_dpt}</c:if></span>
		</c:if>
	</li>
	<li>
		<c:if test="${empty user}">
			<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
		</c:if>
	</li>
	<c:if test="${!empty user}">
		<li class="li_logout"><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
	</c:if>
	</ul>
	
	<ul class="nav_menu">
	<c:if test="${!empty user}">	
		<li><a href="${pageContext.request.contextPath}/notice/list.do" id="ck_notice">공지사항</a></li>
		<li><a href="${pageContext.request.contextPath}/messanger/list.do" id="ck_msg">메신저<span class="alarm_badge" style="display:none;"></span></a></li>
		<li><a href="${pageContext.request.contextPath}/letter/main.do" id="ck_letter">쪽지<span class="rgyBadge" id="letter_bz"></span></a></li>
		<li><a href="${pageContext.request.contextPath}/workflow/list.do" id="ck_workflow">전자결재</a></li>
	</c:if>
	<li>
		<c:if test="${!empty user && user.auth == 1}">
			<a href="${pageContext.request.contextPath}/member/myPage.do" id="ck_mypage">MY페이지</a>
		</c:if>
	</li>
	<li>
		<c:if test="${!empty user && user.auth == 2}">
			<a href="${pageContext.request.contextPath}/member/admin_list.do" id="ck_admin">관리자페이지</a>
		</c:if>
	</li>
	<li>
		<a href="${pageContext.request.contextPath}/notice/list.do">홈으로</a>
	</li>
</ul>
