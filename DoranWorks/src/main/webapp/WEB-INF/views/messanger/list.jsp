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
				<input type="search" name="keyword" id="keyword" value="${param.keyword}" placeholder="채팅방 이름, 메시지 검색">
			</li>	
		</ul>
	</form> 
	<div class="align-center">
		<input type="button" value="채팅방 생성" id="createroom_btn">
	</div>
	
	<!-- 채팅방 리스트 -->
	<div class="chat_list"></div>
	
	
	<div id="searchChatroom" style="display:none;">
		<h2>멤버 선택</h2>
		<form action="createChatroom.do" id="search_form" method="get">
			<ul class="search">
				<li>												<!-- 검색하는 내용 보여져야되니까 -->
					<input type="search" name="keyword" id="keyword" value="${param.keyword}" placeholder="이름, 부서, 이메일 검색">
				</li> 
			</ul>
			<!-- 멤버리스트 시작 -->
			<div><table id="member_list"></table></div>
			<!-- 멤버리스트 끝 -->
		</form>
		
		<!-- 선택된 멤버 리스트 시작 -->
		<hr size="1" width="100%">
		<form action="confirm.do" method="post" id="checked_form" style="display:none;">
		    <input type="hidden" name="members" value="${user.mem_num}">
			<div class="checked_div"></div>
		<!-- 선택된 멤버 리스트 끝 -->
			
			<c:if test="${!empty user}">
			<div class="align-right">
				<input type="button" value="취소" class="mem_reset">
				<input type="submit" value="확인">
			</div>
			</c:if>
			
		</form>
		
	</div>
	<div class="chat_start"></div>
</div>
<!-- 내용 끝 -->
