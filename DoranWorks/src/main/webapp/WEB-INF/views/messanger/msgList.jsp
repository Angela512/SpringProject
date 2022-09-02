<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

<div class="page-main">
	<h2>채팅방 생성</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield"> <!-- request.getparameter : 전송된 파라미터 읽어들임 -->
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>아이디</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
				</select>
			</li>
			<li>												<!-- 검색하는 내용 보여져야되니까 -->
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>	
		</ul>
	</form>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input type="button" value="채팅방 만들기" onclick="location.href='createChatroom.do'">
	</div>
	</c:if>
	<table>
		<c:forEach var="cr" items="${list}">
			<tr>
				<td>${cr.mem_name}</td>
				<td><a href="detail.do?chatroom_num=${cr.chatroom_num}">${cr.chatroom_name}</a></td>
				<td>${cr.msg_sendtime}</td>
			</tr>
		</c:forEach>
	</table>
</div>
<!-- 내용 끝 -->
