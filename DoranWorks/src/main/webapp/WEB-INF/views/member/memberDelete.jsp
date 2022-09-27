<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 내용 시작 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
	<h2>회원탈퇴</h2>
	<form:form modelAttribute="memberVO" style="border:none; padding-top:50px; "
	   action="delete.do" id="change_form">
		<form:errors element="div" 
		                   cssClass="error-color"/>
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<form:input path="mem_id"/>
				
			</li>
			<li>
				<label for="mem_pw">비밀번호</label>
				<form:password path="mem_pw"/>
				
			</li>
		</ul>
		<div class="align-center">
			<button type="submit" class="btn btn-primary" style="width:20%; border-radius: 20px; font-size:1em;">탈퇴하기</button>
			<button type="button" class="btn btn-secondary" onclick="location.href='myPage.do'" style="width:20%; border-radius: 20px; font-size:1em;">MY페이지</button>
		</div>                   
	</form:form>
</div>
<!-- 내용 끝 -->


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>