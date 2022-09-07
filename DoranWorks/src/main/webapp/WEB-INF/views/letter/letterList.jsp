<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<!-- 내용 시작 -->
<div class="page-main">
	<h2>쪽지</h2>
	<form action="main.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>보낸사람</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>받는사람</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>제목</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>내용</option>
				</select>
			</li>
			
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword }">
			</li>
			
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='main.do'">
			</li>
		</ul>
	</form>
	
	<button onclick="location.href='main.do?letter_type=0'">전체쪽지함</button>
	<button onclick="location.href='main.do?letter_type=1'">받은쪽지함</button>
	<button onclick="location.href='main.do?letter_type=2'">보낸쪽지함</button>
	
	<c:if test="${!empty user }">
	<div class="align-right">
		<input type="button" value="글쓰기" onclick="location.href='write.do'">
	</div>
	</c:if>
	
	<c:if test="${count==0 }">
		<div class="result-display">표시할 게시물이 없습니다.</div>
	</c:if>
	
	<c:if test="${count>0 }">
	<table>
		<c:forEach var="letter" items="${list }" varStatus="status">
		<tr>
			<td></td>
			<td></td>
			<td>
			
			</td>
			<td>
				<c:if test="${param.letter_type ==0 || param.letter_type == null}">
					<c:if test="${letter.lt_sender_num == user.mem_num}">${letter.lt_receiver_id }</c:if>
					<c:if test="${letter.lt_sender_num != user.mem_num }">${letter.lt_sender_id }</c:if>
				</c:if>
				
				<c:if test="${param.letter_type==1 }">
				${letter.mem_name}(${letter.lt_sender_id })
				</c:if>

				<c:if test="${param.letter_type==2 }">
				${letter.lt_receiver_id }
				</c:if>
			 </td>
			<td><a href="detail.do?lt_num=${letter.lt_num }&letter_type=${param.letter_type}">${letter.lt_title }</a></td>
			<td>${letter.lt_date }</td>
		</tr>
		</c:forEach>
	</table>
	
	<div class="align-center">${page }</div>
	</c:if>
	
</div>
<!-- 내용 끝 -->