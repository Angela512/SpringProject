<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<style>
#ck_msg{
	text-decoration: underline !important;
	font-weight: bold;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messanger.js"></script>
<div id="message_detail" class="page-main">
	<!-- 채팅방 검색 및 채팅방 생성 -->
	<h2 id="msg_title">메신저</h2>
	<div class="main_list">
	<ul class="chat_search">
		<li><input type="button" value="채팅방 생성" id="createroom_btn"></li>
		<li>
			<input type="search" name="keyword" id="chat_keyword" placeholder="채팅방 멤버 검색">
		</li>
	</ul>
	<div class="chatroomMain">
	<!-- 채팅방 리스트 -->
	</div>
	</div>
	<!-- 채팅방 생성 시 멤버 선택 -->
	<div id="searchChatroom" style="display:none;">
		<h2>멤버 선택</h2>
		<form action="createChatroom.do" id="search_form" method="get">
			<ul class="mem_search">
				<li>												<!-- 검색하는 내용 보여져야되니까 -->
					<input type="search" name="keyword" id="mem_keyword" value="${param.keyword}" placeholder="이름, 부서, 이메일 검색">
				</li> 
			</ul>
			<!-- 전체,팀별,직급별 보기 -->
			<!-- <ul class="mem_view">
				<li>전체</li>
				<li>팀별</li>
				<li>직급별</li>
			</ul> -->
			<!-- 멤버리스트 시작 -->
			<div id="member_list"></div> 
			<!-- 멤버리스트 끝 -->
		</form> 
		
		<!-- 선택된 멤버 리스트 시작 -->
		<hr size="1" width="100%">
		<form action="confirm.do" method="post" id="checked_form" style="display:none;">
		   <input type="hidden" name="members" value="${user.mem_num}">
			<div class="checked_div"><ul class="checked_ul">
			</ul></div>
		<!-- 선택된 멤버 리스트 끝 -->
			
			<div class="checkedListBtn">
				<input type="button" value="취소" class="mem_reset">
				<input type="submit" value="확인" class="mem_confirm">
			</div>
		</form>
	</div>

	
	<!-- 채팅 시작 -->
	<div id="chatting_main">
		<div class="chat_form" style="display:none;"></div>
		<div class="msg_formUI"></div>
	</div>
	<!-- 채팅 끝 -->
	
	
</div>
<!-- 내용 끝 -->
