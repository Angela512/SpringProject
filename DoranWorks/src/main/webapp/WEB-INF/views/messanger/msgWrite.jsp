<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<div>
	<h2>메시지 입력</h2>
	<form:form action="write.do" modelAttribute="messangerVO" id="register_form">
	<form:errors element="div" cssClass="error-color"/>
		<label for="msg_content">내용</label>
		<form:input path="msg_content"/>
		<form:errors path="msg_content" cssClass="error-color"/>
		<label for="upload">파일 업로드</label>
		<input type="file" name="upload" id="upload">
		<form:button>전송</form:button>
	</form:form>
</div>







