<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
<div class="page-main">
	<h2>${workflow_main.flow_title}</h2>
	<ul class="detail-info">
		<%-- <li>
			<c:if test="${!empty workflow_main.flow_photo_name}">
			<img src="imageView.do?flow_num=${workflow_main.flow_num}&board_type=1" width="40" height="40" class="my-photo">
			</c:if>
			<c:if test="${empty workflow_main.flow_photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
		</li> --%>
		<li>
			${workflow_main.mem_name} ${workflow_main.mem_rank}
			<br>
			<%-- 
			<c:if test="${!empty board.modify_date}">
			최근 수정일 : ${board.modify_date}	
			</c:if>
			 --%>
			 
			<%-- <c:if test="${empty workflow_main.modify_date}">
			작성일 : ${workflow_main.date}	
			</c:if> --%>
			결재 종류  [${workflow_main.flow_sort}]
			<br>
			작성일 : ${workflow_main.flow_date}
		</li>
	</ul>
	<%-- 
	<ul>
		<c:if test="${!empty board.filename}">
		<li>
			첨부파일 : <a href="file.do?board_num=${board.board_num}">${board.filename}</a>
		</li>
		</c:if>
	</ul>
	 --%>
	<hr size="1" width="100%">
	<%-- 
	<c:if test="${fn:endsWith(board.filename,'.jpg') ||
	              fn:endsWith(board.filename,'.JPG') ||
	              fn:endsWith(board.filename,'.jpeg') ||
	              fn:endsWith(board.filename,'.JPEG') ||
	              fn:endsWith(board.filename,'.gif') ||
	              fn:endsWith(board.filename,'.GIF') ||
	              fn:endsWith(board.filename,'.png') ||
	              fn:endsWith(board.filename,'.PNG')}">
	<div class="align-center">
		<img src="imageView.do?board_num=${board.board_num}&board_type=2" style="max-width:500px;">
	</div>
	</c:if>
	 --%>
	<p>
		${workflow_main.flow_content}
	</p>
	<%-- 
	<div>
		좋아요
		<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
		<span id="output_fcount"></span>
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user && user.mem_num == board.mem_num}">
		<input type="button" value="수정" 
		  onclick="location.href='update.do?board_num=${board.board_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?board_num=${board.board_num}');
				}
			};
		</script>  
		<input type="button" value="목록"
		       onclick="location.href='list.do'">
		</c:if>
	</div>
	<hr size="1" width="100%">
	<div id="reply_div">
		<span class="re-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="board_num"
			   value="${board.board_num}" id="board_num">
		</form>
	</div>
	 --%>
</div>
<!-- 내용 끝 -->


