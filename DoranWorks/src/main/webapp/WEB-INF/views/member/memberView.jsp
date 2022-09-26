<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>


<style>
#ck_mypage{
	text-decoration: underline !important;
	font-weight: bold;
}
	.detail_view{
	padding:350px 0 0 0;
	}
	.detail_view li{
	padding-top:20px;
	}
	.mem_wrap{
	padding: 0 0 0 50px;
	}
	
	.first_line{
	display:inline-block; 
	width:100px;
	font-weight:bold;
	}
	.line{
	width:65%; float:left;
	border:1px solid lightgray;
	}
	
	#photo_btn, #photo_submit, #photo_reset {
	background-color:#5AAEFF;
	border:none;
	color:#FFF;
	border-radius: 5px;
	height:30px;
	}
	
</style>


<div class="page-main">
<div class="mem_wrap">
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
			<input type="button" value="비밀번호 변경" id="write_button" onclick="location.href='changePassword.do'">
			<input type="button" value="회원 탈퇴" id="write_button" onclick="location.href='delete.do'">
		</p>
	</div>
	<br>
	<div class="detail_view">
		<h2>회원상세정보</h2>
		<hr class="line"/>
		<br>
		<ul>
			<li><div class="first_line">이름</div>${member.mem_name}</li>
			<li><div class="first_line">전화번호</div>${member.mem_phone}</li>
			<li><div class="first_line">이메일</div>${member.mem_email}</li>
			<li><div class="first_line">우편번호</div>${member.mem_zipcode}</li>
			<li><div class="first_line">주소</div>${member.mem_addr1}</li>
			<li><div class="first_line">상세주소</div>${member.mem_addr2}</li>
			<li><div class="first_line">생년월일</div>${member.mem_birthdate}</li>
			<li><div class="first_line">부서명</div>${member.mem_dpt}</li>
			<li><div class="first_line">직급</div>${member.mem_rank}</li>
			<li><div class="first_line">고용형태</div>${member.mem_type}</li>
			<li><div class="first_line">가입일</div>${member.mem_date}</li>
			<c:if test="${!empty member.mem_modify_date}">
				<li><div class="first_line">정보 수정일</div>${member.mem_modify_date}</li>
			</c:if>
		</ul>
		<hr class="line"/>
		<div class="align-center">
			<input type="button" value="회원정보 수정" onclick="location.href='update.do'" style="margin:50px 500px 0 0;" id="write_button">
			
		</div>
		
	</div>
	</div>
</div>
<!-- 내용 끝 -->