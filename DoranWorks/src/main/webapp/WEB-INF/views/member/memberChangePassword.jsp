<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 내용 시작 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
<style>
#ck_mypage{
	text-decoration: underline !important;
	font-weight: bold;
}
#change_form li{
	font-size:15px;
	font-weight:bold;
}
</style>

<div class="page-main">

	<h2>비밀번호변경</h2>
	<br>
	<form:form modelAttribute="memberVO" style="border:none; padding-top:50px; "
	  action="changePassword.do" id="change_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li style="padding-bottom:30px">
				<label for="now_passwd">현재 비밀번호</label>
				<form:password path="now_passwd"/>
				<form:errors path="now_passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_pw">새 비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
			</li>
			<li>
				<label for="confirm_passwd">새 비밀번호 확인</label>
				<input type="password" id="confirm_passwd">
				<span id="message_id"></span>
			</li>
		</ul>  
		<div class="align-center">
			<button type="submit" class="btn btn-primary" style="width:20%; border-radius: 20px; font-size:1em;">변경하기</button>
			<button type="button" class="btn btn-secondary" onclick="location.href='myPage.do'" style="width:20%; border-radius: 20px; font-size:1em;">MY페이지</button>
		</div>
	</form:form>
</div>
<!-- 내용 끝 -->



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>