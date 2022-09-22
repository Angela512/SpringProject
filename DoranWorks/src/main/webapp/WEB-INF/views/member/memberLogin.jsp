<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Shrikhand&display=swap" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bungee+Shade&family=Cinzel+Decorative&family=Shrikhand&display=swap" rel="stylesheet">
	
	<style>
    /*Don't forget to add Font Awesome CSS : "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"*/
input[type="text"] {
  width: 16%;
  border: 2px solid #aaa;
  border-radius: 20px;
  margin: 8px 0;
  outline: none;
  padding: 8px;
  box-sizing: border-box;
  transition: 0.3s;
}
input[type="password"] {
  width: 16%;
  border: 2px solid #aaa;
  border-radius: 20px;
  margin: 8px 0;
  outline: none;
  padding: 8px;
  box-sizing: border-box;
  transition: 0.3s;
}

input[type="text"]:focus {
  border-color: dodgerBlue;
  box-shadow: 0 0 8px 0 dodgerBlue;
}
input[type="password"]:focus {
  border-color: dodgerBlue;
  box-shadow: 0 0 8px 0 dodgerBlue;
}

.inputWithIcon input[type="text"] {
  padding-left: 40px;
}
.inputWithIcon input[type="password"] {
  padding-left: 40px;
}

.inputWithIcon {
  position: relative;
}

.inputWithIcon i {
  position: absolute;
  left: 0;
  top: 8px;
  padding: 9px 8px;
  color: #aaa;
  transition: 0.3s;
}

.inputWithIcon input[type="text"]:focus + i {
  color: dodgerBlue;
}

.inputWithIcon.inputIconBg i {
  background-color: #aaa;
  color: #fff;
  padding: 9px 4px;
  border-radius: 4px 0 0 4px;
  margin:auto;
}

.inputWithIcon.inputIconBg input[type="text"]:focus + i {
  color: #fff;
  background-color: dodgerBlue;
}
.inputWithIcon.inputIconBg input[type="password"]:focus + i {
  color: #fff;
  background-color: dodgerBlue;
}


.center{
margin:200px 0px 0px 0px;
text-align:center;
}

.error-color{
font-size:0.1em;
	color:#ff0000;
}

body{

background-color:#F2F2F2;


}
</style>
	
	
<div class="center">	
<span style="font-size:70px;  font-family: 'Shrikhand', cursive;">Doran Works </span>
<h4>로그인</h4>
<br>
	<form:form id="login_form" action="login.do" modelAttribute="memberVO">
	<form:errors element="div" cssClass="error-color"/>
	
	<div class="inputWithIcon ">
    <input type="text" name="mem_id" placeholder="아이디 입력">
    <i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
  </div>
  <form:errors path="mem_id" cssClass="error-color"/>
  
  <div class="inputWithIcon ">
    <input type="password" name="mem_pw" placeholder="비밀번호 입력">
    <i class="fa fa-envelope fa-lg fa-fw" aria-hidden="true"></i>
  </div>
  <form:errors path="mem_pw" cssClass="error-color"/>
  <br>
  
  <button type="submit" class="btn btn-primary" style="width:16%; border-radius: 20px;">로그인</button>

	</form:form>
	
	</div>
	
	<%-- <form:form id="login_form" action="login.do" modelAttribute="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<form:input path="mem_id"/>
				<form:errors path="mem_id" cssClass="error-color"/>
			</li>
			
			<li>
				<label for="mem_pw">비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
			</li>
		</ul>
		
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	
	</form:form> --%>

<!-- 내용 끝 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>