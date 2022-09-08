<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messanger.js"></script>
<div class="page-main">
	<!-- 채팅 시작 -->
	<div class="chat_start">
		<form id="chat_form"> <!-- hidden값으로 넘겨서 js에서 작업 / 이게 아니면 그냥 여기서 script해서 js작업하면 됨 -->
			<%-- <input type="hidden" name="msg_num" value="${board.board_num}" id="board_num"> --%>
			<textarea rows="3" cols="50" name="re_content" id="re_content" class="rep-content"
			<c:if test="${empty user}">disabled="disabled"</c:if>
			><c:if test="${empty user}">로그인 후 작성</c:if></textarea>
			<c:if test="${!empty user}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second" class="align-right">
				<input type="submit" value="전송">
			</div>
			</c:if>
		</form>
	</div>
	<!-- 채팅 끝 -->
	 
</div>






