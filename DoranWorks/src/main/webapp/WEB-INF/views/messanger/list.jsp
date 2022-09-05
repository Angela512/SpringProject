<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messanger.js"></script>
<div class="page-main">
	<h2>메신저</h2>
	 <form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>												<!-- 검색하는 내용 보여져야되니까 -->
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>	
		</ul>
	</form> 
	<div class="align-right">
		<input type="button" value="채팅방 생성" id="createroom_btn">
	</div>
	<!-- <input type="button" value="채팅방 생성" onclick="location.href='createChatroom.do'"> -->
	<div id="searchChatroom" style="display:none;">
		<h2>멤버 선택</h2>
		<form action="createChatroom.do" id="search_form" method="get">
			<ul class="search">
				<li>												<!-- 검색하는 내용 보여져야되니까 -->
					<input type="search" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="찾기">
					<input type="button" value="목록" onclick="location.href='list.do'">
				</li>	
			</ul>
		</form>
		
		<!-- 멤버 리스트 시작 -->
		<c:if test="${count == 0}">
		<div class="result-display">회원이 없습니다.</div>
		</c:if>
		<c:if test="${empty user}">
		<div class="result-display">로그인 후 사용하세요.</div>
		</c:if>
		<c:if test="${count > 0 && !empty user}">
		<table>
			<c:forEach var="member" items="${list}">
			<c:if test="${member.mem_num != user.mem_num}">
				<tr>
					<td>
					<input type="checkbox" name="mem_num" data-num="${member.mem_num}" id="${member.mem_name}" class="checkedMember">
					</td>
					<td><a href="detail.do?mem_num=${member.mem_num}">${member.mem_name}</a></td>
					<td>${member.mem_dpt}</td>
				</tr>
			</c:if>
			</c:forEach>
		</table>
		<div class="align-center">${page}</div>
		</c:if>
		<!-- 멤버 리스트 끝 -->
		
		<!-- 선택된 멤버 리스트 시작 -->
		<hr size="1" width="100%">
		<form action="confirm.do" method="post" id="checked_form" style="display:none;">
		    <input type="hidden" name="members" value="${user.mem_num}">
			<div class="checked_div"></div>
			
		<!-- 선택된 멤버 리스트 끝 -->
			
			<c:if test="${!empty user}">
			<div class="align-right">
				<input type="submit" value="확인">
			</div>
			</c:if>
		</form>
	</div>
</div>
<!-- 내용 끝 -->
