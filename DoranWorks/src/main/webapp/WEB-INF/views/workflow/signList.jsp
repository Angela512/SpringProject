<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
.center{
padding:1px 0 0 80px;
}

.btn_center{
padding:80px 0 0 200px;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow.js"></script>
<title>결재 정보</title>
<h2>결재 정보</h2>

<form action="/workflow/signList.do" method="post" id="request">

		<c:forEach var="flow" items="${list}">
		<c:if test="${flow.mem_name != '관리자' }">
		<c:if test="${user_name != flow.mem_name }">
		<div class="center">
		<input type="checkbox"  value="${flow.mem_name}" />
		<div style="display:inline-block; width:100px; padding-left:50px;">${flow.mem_name}</div>
		<div style="display:inline-block; width:80px">${flow.mem_rank}</div>
		${flow.mem_dpt}<br>
		</div>
		</c:if>
		</c:if>
		
		</c:forEach>		

	<br>
	<div class="btn_center">
	<input type="button" value="확인" id="list_read" 
	style="
	background-color:#5AAEFF;
	border:none;
	color:#FFF;
	border-radius: 5px;
	height:30px;"
	/>
	<input type="button" value="취소" onClick="window.close()"
	style="
	background-color:#5AAEFF;
	border:none;
	color:#FFF;
	border-radius: 5px;
	height:30px;"
	/>
	</div>
	</form>