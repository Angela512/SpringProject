<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<div class="page-main">
	<h2>결재 상황</h2>
	
<!-- 	<input type="button" value="목록" onclick="location.href='sign.do'"> -->
	 <a href="#" onclick="window.open('signList.do', '_blank', 'width=600 height=600')">.</a>		            
	<form action="list.do" id="search_form" 
	                                   method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>ID+별명</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword"
				               value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록"
				    onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input type="button" value="새 결재 진행 "
		          onclick="location.href='write.do'">
	</div>
	</c:if>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>	
	</c:if>
	<c:if test="${count > 0}">
	<table class="table table-hover">
		<tr>
			<th>문서번호</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>결재 종류</th>
			<th>작성일</th>
			<th>결재 상태</th>
		</tr>
		<c:forEach var="flow" items="${list}">
		
		<tr>
			<td><fmt:formatDate value="${flow.flow_date}" pattern="yyyyMMdd"/>-${flow.flow_num}</td>
			<td><a href="detail.do?flow_num=${flow.flow_num}">${flow.flow_title}</a></td>
		
			<td>${flow.mem_name} ${flow.mem_rank}</td>
			<td>${flow.flow_sort}</td>
			<td>${flow.flow_date}</td> 	
			<td>${flow.flow_state}</td>	
		</tr>
		
		</c:forEach>
	</table>
	<%-- 
	<c:forEach var="lm" items="${list2}">
		${lm.mem_name} ${lm.mem_dpt} <br>
	</c:forEach>
	 --%>
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 내용 끝 -->


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
