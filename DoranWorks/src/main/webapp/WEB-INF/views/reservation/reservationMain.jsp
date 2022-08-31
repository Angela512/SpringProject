<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<div class="page-main">
	<h2>회의실 예약</h2>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input type="button" value="예약하기" onclick="location.href='Write.do'">
	</div>
	</c:if>
	<!-- <form action="calendar.do" id="calendar_form" method="get">
		
	</form> -->
	
	<table>
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="board" items="${list}">
		<tr>
			<td>${board.board_num}</td>
			<td><a href="detail.do?board_num=${board.board_num}">${board.title}</a></td>
			<td>
				<c:if test="${empty board.nick_name}">${board.id}</c:if>
				<c:if test="${!empty board.nick_name}">${board.nick_name}</c:if>
			</td>
			<td>${board.reg_date}</td>
			<td>${board.hit}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
</div>
<!-- 내용 끝 -->






