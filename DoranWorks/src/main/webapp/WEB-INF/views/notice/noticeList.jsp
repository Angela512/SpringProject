<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 내용 시작 -->
<style>
#ck_notice{
	text-decoration: underline !important;
	font-weight: bold;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/letter.css">
<div class="page-main">
	<div class="content">
	<h2>공지사항 목록</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>ID+이름</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
				</select>
			</li>
			
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword }">
			</li>
			
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	
	<c:if test="${count==0 }">
	<div class="result-display">표시할 게시물이 없습니다.</div>
	</c:if>
	
	<c:if test="${count>0 }">
	<table class="letter_table">
		<tr height="30">
			<th width="15%">필독여부</th>
			<th>제목</th>
			<th width="15%">날짜</th>
		</tr>
		
		<c:forEach var="notice" items="${list }">
		<c:if test="${notice.notice_head==1 }">
		<tr height="30">
			<td>
				<div class="notice_head1">필독</div>
			</td>
			<td><a href="detail.do?notice_num=${notice.notice_num }">${notice.notice_title }</a></td>
			<td>${notice.notice_date }</td>
		</tr>
		</c:if>
		</c:forEach>

		<c:forEach var="notice" items="${list }">
		<c:if test="${notice.notice_head==0 }">
		<tr height="30">
			<td>
				<div class="notice_head2">공지</div>
			</td>
			<td><a href="detail.do?notice_num=${notice.notice_num }">${notice.notice_title }</a></td>
			<td>${notice.notice_date }</td>
		</tr>
		</c:if>
		</c:forEach>
		
		
	</table>
	<div class="align-center">${page }</div>
	</c:if>
	
	<c:if test="${!empty user && user.auth==2}">
	<div class="align-right">
		<input type="button" class="list_button" value="글쓰기" onclick="location.href='write.do'">
	</div>
	</c:if>
	</div>
</div>
<!-- 내용 끝 -->