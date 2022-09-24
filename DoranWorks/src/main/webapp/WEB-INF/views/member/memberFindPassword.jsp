<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 중앙 컨텐츠 시작 -->
<style>
#find_form{
	border: none;
}
#find_form .underline{
	border-top: black;
	border-right: black;
	border-left: black;
}
.find_button{
	background-color:#5AAEFF;
	border:none;
	color:#FFF;
	border-radius: 5px;
	cursor: pointer;
}
</style>
<div class="page-main">
	<h2>비밀번호 찾기</h2>
	<div>
		비밀번호를 분실하셨나요?<br>
		가입할 때 사용한 아이디와 이메일를 입력하시면<br>
		이메일로 임시비밀번호를 보내드립니다.<br><br>		
	</div>
	<form:form modelAttribute="memberVO" id="find_form" action="sendPassword.do">
		<form:errors element="div" cssClass="error-color"/>	
		<ul>
			<li>
				<label for="mem_id"><b>아이디</b></label>
				<form:input path="mem_id" class="underline"/>
				<form:errors path="mem_id" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_email"><b>이메일</b></label>
				<form:input path="mem_email" class="underline"/>
				<form:errors path="mem_email" 
				             cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" class="find_button" value="전송">
			<input type="button" class="find_button" value="홈으로"
			      onclick="location.href='${pageContext.request.contextPath}/member/main.do'">
		</div>
	</form:form>
</div>