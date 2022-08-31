<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
	<h2>메시지 입력</h2>
	<form:form action="createChatroom.do" modelAttribute="chatroomVO" id="register_form">
	<form:errors element="div" cssClass="error-color"/>
		<label for="chatroom_name">채팅방이름</label>
		<form:input path="chatroom_name"/>
		<form:errors path="chatroom_name" cssClass="error-color"/>
		<form:button>만들기</form:button>
	</form:form>
</div>