<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
	<h2>비밀번호 찾기</h2>
	<div>
		비밀번호를 분실하셨나요?<br>
		가입할 때 사용한 아이디와 이메일를 입력하시면<br>
		이메일로 임시비밀번호를 보내드립니다.<br><br>		
	</div>
	<form:form modelAttribute="memberVO" action="sendPassword.do">
		<form:errors element="div" cssClass="error-color"/>	
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<form:input path="mem_id"/>
				<form:errors path="mem_id" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_email">이메일</label>
				<form:input path="mem_email"/>
				<form:errors path="mem_email" 
				             cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="홈으로"
			      onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>