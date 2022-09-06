<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<h2>결재 라인 리스트</h2>
<form:form action="sign.do" modelAttribute="workflowMainVO"
	        id="register_form"
	        enctype="multipart/form-data">
 <table>
	<tr>
		<th>이름</th>
		<th>직급</th>
		<th>소속</th>
	</tr>
	
	<c:forEach var="flow" items="${list}">
	<tr>
		
		<td><input type="checkbox" name="flow_state" value="${flow.mem_name}"/>${flow.mem_name}</td>
		<td>${flow.mem_rank}</td>
		<td>${flow.mem_dpt}</td>
	</tr>	
	</c:forEach>
	<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="목록"
			            onclick="location.href='list.do'">
		</div>  
	
</table>
</form:form>
