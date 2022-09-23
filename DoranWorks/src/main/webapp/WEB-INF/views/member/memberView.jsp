<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
<div class="page-main">
	<div class="mypage-div">
		<h2>프로필 사진</h2>
		<ul>
			<li>
				<c:if test="${empty member.mem_photo_name}">
					<img src="${pageContext.request.contextPath}/images/face.png" width="200" height="200" class="my-photo">
				</c:if>
				<c:if test="${!empty member.mem_photo_name}">
					<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" height="200" class="my-photo">
				</c:if>
			</li>
			<li>
				<div class="align-center">
					<input type="button" value="수정" id="photo_btn">
				</div>
				<div id="photo_choice" style="display:none;">
					<input type="file" id="upload" accept="image/gif,image/png,image/jpeg"><br>
					<input type="button" value="전송" id="photo_submit">
					<input type="button" value="취소" id="photo_reset">
				</div>
			</li>
		</ul>
		<p class="align-center">
			<input type="button" value="비밀번호 변경" onclick="location.href='changePassword.do'">
			<input type="button" value="회원 탈퇴" onclick="location.href='delete.do'">
		</p>
	</div>
	<div class="w">
		<h2>회원상세정보</h2>
		<ul>
			<li>이름 : ${member.mem_name}</li>
			<li>전화번호 : ${member.mem_phone}</li>
			<li>이메일 : ${member.mem_email}</li>
			<li>우편번호 : ${member.mem_zipcode}</li>
			<li>주소 : ${member.mem_addr1}</li>
			<li>상세주소 : ${member.mem_addr2}</li>
			<li>생년월일 : ${member.mem_birthdate}</li>
			<li>부서명 : ${member.mem_dpt}</li>
			<li>직급 : ${member.mem_rank}</li>
			<li>고용형태 : ${member.mem_type}</li>
			<li>가입일 : ${member.mem_date}</li>
			<c:if test="${!empty member.mem_modify_date}">
				<li>정보 수정일 : ${member.mem_modify_date}</li>
			</c:if>
		</ul>
		<div class="align-center">
			<input type="button" value="회원정보 수정" onclick="location.href='update.do'">
		</div>
	</div>
</div>
<!-- 내용 끝 -->