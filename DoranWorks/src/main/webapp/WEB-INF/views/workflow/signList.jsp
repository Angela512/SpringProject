<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/workflow.js"></script>
<h2>결재 라인 리스트</h2>

<form action="/workflow/signList.do" method="post" id="request">

		<c:forEach var="flow" items="${list}">

		<input type="checkbox" name="sign_name" value="${flow.mem_name}"/>${flow.mem_name}
		${flow.mem_rank}
		${flow.mem_dpt}
		
		<br>
		
		</c:forEach>
	
	<br>
	

	<input type="text" name="sign_no"/>
	<input type="submit" value="전송" />
	<input type="button" value="취소" onClick="window.close()">
	
	</form>