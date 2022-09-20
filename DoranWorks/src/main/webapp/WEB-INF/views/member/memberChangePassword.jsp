<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
<div class="page-main">
	<h2>비밀번호변경</h2>
	<form:form modelAttribute="memberVO" 
	  action="changePassword.do" id="change_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="now_passwd">현재 비밀번호</label>
				<form:password path="now_passwd"/>
				<form:errors path="now_passwd" 
				                   cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_pw">새비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw" 
				                   cssClass="error-color"/>
			</li>
			<li>
				<label for="confirm_passwd">새비밀번호 확인</label>
				<input type="password" id="confirm_passwd">
				<span id="message_id"></span>
			</li>
		</ul>  
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="MY페이지" 
			 onclick="location.href='myPage.do'">
		</div>
	</form:form>
</div>
<!-- 내용 끝 -->



