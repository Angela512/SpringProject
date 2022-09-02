<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messanger.js"></script>
<div class="page-main">
	<h2>멤버 선택</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield"> <!-- request.getparameter : 전송된 파라미터 읽어들임 -->
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>이름</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>부서</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>이메일</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>이름/부서/이메일</option>
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
	
	<!-- 멤버 리스트 시작 -->
	<c:if test="${count == 0}">
	<div class="result-display">회원이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<c:forEach var="member" items="${list}">
			<tr>
				<td>
				<input type="checkbox" name="mem_num" data-num="${member.mem_num}" class="checkedMember"><!-- checked="checked">--> 
				</td>
				<td><a href="detail.do?mem_num=${member.mem_num}">${member.mem_name}</a></td>
				<td>${member.mem_dpt}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
	<!-- 멤버 리스트 끝 -->
	
	<!-- 선택된 멤버 리스트 시작 -->
	<hr size="1" width="100%">
	<div class="checked_div">
	</div>
	
	<!-- 선택된 멤버 리스트 끝 -->
	
	<c:if test="${!empty user}">
	<div class="align-right">
		<input type="button" value="확인" onclick="location.href='confirm.do'">
	</div>
	</c:if>
	
	<!-- 
	<form:form action="createChatroom.do" modelAttribute="chatroomVO" id="register_form">
	<form:errors element="div" cssClass="error-color"/>
		<label for="chatroom_name">채팅방이름</label>
		<form:input path="chatroom_name"/>
		<form:errors path="chatroom_name" cssClass="error-color"/>
		<form:button>만들기</form:button>
	</form:form>
	 -->
	 
</div>