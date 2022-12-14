<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<style>
#topButton {position: fixed; right: 2%; bottom: 120px; display: none; z-index: 999;}
figure.image img{
	max-width:600px;
}
figure.image{
	text-align: center;
}
#ck_notice{
	text-decoration: underline !important;
	font-weight: bold;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/letter.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/notice.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<div class="content">
	<h2>공지사항</h2>
	<div class="align-right">
		<input type="button" class="list_button" value="목록" onclick="location.href='list.do'">
		<c:if test="${notice.prev_num != 0}">
		<input type="button" class="list_button" value="▲" onclick="location.href='detail.do?notice_num=${notice.prev_num}'">
		</c:if>
		<c:if test="${notice.next_num != 0}">
		<input type="button" class="list_button" value="▼" onclick="location.href='detail.do?notice_num=${notice.next_num}'">
		</c:if>
	</div>
	<hr size="1" width="100%">
	<h2>${notice.notice_title}</h2>
	<ul class="detail-info">
		<li>
			<c:if test="${!empty notice.mem_photo_name}">
			<img src="imageView.do?notice_num=${notice.notice_num}&image_type=3" width="40" height="40" class="my-photo">
			</c:if>

			<c:if test="${empty notice.mem_photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
		</li>
		
		<li>
		${notice.mem_name}<br>
		${notice.notice_date }
		</li>
	</ul>
	
	<c:if test="${!empty notice.notice_filename1 || !empty notice.notice_filename2 }">
	<hr size="1" width="100%">
		<ul>
			<li>
				<b>첨부파일</b>
			</li>
			<li>
				<a href="file.do?notice_num=${notice.notice_num}&file_type=1">${notice.notice_filename1}</a>
			</li>
			<li>
				<a href="file.do?notice_num=${notice.notice_num}&file_type=2">${notice.notice_filename2}</a>
			</li>
		</ul>
	</c:if>
	
	<hr size="1" width="100%">
	<c:if test="${fn:endsWith(notice.notice_filename1,'.jpg') ||
				  fn:endsWith(notice.notice_filename1,'.JPG') ||
				  fn:endsWith(notice.notice_filename1,'.JPEG') ||
				  fn:endsWith(notice.notice_filename1,'.jpeg') ||
				  fn:endsWith(notice.notice_filename1,'.GIF') ||
				  fn:endsWith(notice.notice_filename1,'.PNG') ||
				  fn:endsWith(notice.notice_filename1,'.gif') ||
				  fn:endsWith(notice.notice_filename1,'.png')}">
				  
	<div class="align-center">
		<img src="imageView.do?notice_num=${notice.notice_num}&image_type=1" style="max-width:500px;">
	</div>
	<br>
	</c:if>
	
	<c:if test="${fn:endsWith(notice.notice_filename2,'.jpg') ||
				  fn:endsWith(notice.notice_filename2,'.JPG') ||
				  fn:endsWith(notice.notice_filename2,'.JPEG') ||
				  fn:endsWith(notice.notice_filename2,'.jpeg') ||
				  fn:endsWith(notice.notice_filename2,'.GIF') ||
				  fn:endsWith(notice.notice_filename2,'.PNG') ||
				  fn:endsWith(notice.notice_filename2,'.gif') ||
				  fn:endsWith(notice.notice_filename2,'.png')}">
				  
	<div class="align-center">
		<img src="imageView.do?notice_num=${notice.notice_num}&image_type=2" style="max-width:500px;">
	</div>
	</c:if>
	
	<p>
		${notice.notice_content }
	</p>
	
	<div id="topButton">
		<input type="button" value="맨위로▲" id="topButtonImg" class="list_button">
	</div>
	
	<div class="align-right">
		<c:if test="${!empty user && user.auth==2 }">
		<input type="button" class="list_button" value="수정" onclick="location.href='update.do?notice_num=${notice.notice_num}'">
		<input type="button" class="list_button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?notice_num=${notice.notice_num}');
				}
			};
		</script>
		</c:if>
	</div>
	<hr size="1" width="100%">
	
	
	
	<input type="hidden" value="${notice.notice_replyagree}" id="notice_replyagree">
	<!-- 댓글 UI 시작 -->
	<c:if test="${notice.notice_replyagree==1 }">
	<div id="reply_div">
		<span class="re-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="notice_num" id="notice_num" value="${notice.notice_num}">
			<textarea rows="3" cols="50" name="reply_content" id="reply_content" class="rep-content"
			<c:if test="${empty user }">disabled="desabled"</c:if>
			><c:if test="${empty user }">로그인해야 작성할 수 있습니다.</c:if></textarea>
			
			<c:if test="${!empty user }">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			
			<div id="re_second" class="align-right">
				<input type="submit" class="list_button" value="전송">
			</div>
			</c:if>
		</form>
	</div>
	
	
	<!-- 댓글 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" class="list_button" value="다음글 보기">
	</div>
	
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif" width="100" height="100">
	</div>
	
	</c:if>
	<!-- 댓글 UI 끝 -->
	</div>
</div>
<!-- 내용 끝 -->