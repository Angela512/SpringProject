<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.admin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/confirmId.js"></script>
<!-- 내용 시작 -->
<style>
#ck_admin{
	text-decoration: underline !important;
	font-weight: bold;
}
</style>
<div class="page-main">
	<h2>DORANWORKS 관리자 페이지</h2>
	<form action="admin_list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield"> <!-- request.getparameter : 전송된 파라미터 읽어들임 -->
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>이름</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>부서</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>이메일</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>이름+부서+이메일</option>
				</select>
			</li>
			<li>												<!-- 검색하는 내용 보여져야되니까 -->
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='admin_list.do'">
			</li>	
		</ul>
	</form>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input type="button" value="회원등록" class="registerBtn" onclick="location.href='registerUser.do'">
	</div>
	</c:if>
	<c:if test="${count == 0}">
	<div class="result-display">회원이 없습니다.</div>
	</c:if>
	
<!-- 회원리스트 시작 -->
	<c:if test="${count > 0}">
	<table class="admin_table">
		<thead>
			<tr>
				<th>사진</th>
				<th>이름</th>
				<th>소속 | 직급</th>
				<th>휴대폰번호</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${list}">
				<tr>
					<td>
					<c:if test="${!empty member.mem_photo_name}">
						<img src="${pageContext.request.contextPath}/member/viewProfile.do?mem_num=${member.mem_num}"
							width="30" height="30" class="my-photo">
					</c:if>
					<c:if test="${empty member.mem_photo_name}">
						<img src="${pageContext.request.contextPath}/images/face.png"
							width="30" height="30" class="my-photo">
					</c:if>
					</td>
					<td>${member.mem_name}</td>
					<td><c:if test="${!empty member.mem_rank}">${member.mem_dpt} | ${member.mem_rank}</c:if></td>
					<td>${member.mem_phone}</td>
					
					
				</tr>
			</c:forEach>

			

			
			
		</tbody>
	</table>
	<div class="board_page">
		${page}
	</div>
	</c:if>
</div>
<!-- 회원리스트 끝 -->
<!-- 내용 끝 -->