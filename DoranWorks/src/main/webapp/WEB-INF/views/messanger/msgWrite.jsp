<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메신저</title>
</head>
<body>
<div>
	<h2>메시지 입력</h2>
	<form:form action="write.do" modelAttribute="messangerVO" id="register_form">
	<form:errors element="div" cssClass="error-color"/>
		<b>내용입력</b>
		<form:input path="msg_content"/>
		<form:errors path="msg_content" cssClass="error-color"/>
		<form:button>전송</form:button>
	</form:form>
</div>
</body>
</html>






