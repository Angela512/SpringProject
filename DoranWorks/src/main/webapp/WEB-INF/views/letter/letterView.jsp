<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<style>
#topButton {position: fixed; right: 2%; bottom: 50px; display: none; z-index: 999;}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/letter.detail.important.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<input type="button" value="답장" onclick="location.href='reply.do?lt_num=${letter.lt_num}'">
	<input type="button" value="전달" onclick="location.href='forward.do?lt_num=${letter.lt_num}'">
	<input type="button" value="삭제" id="detail_delete">
	<input type="button" value="안읽음" id="detail_noread">
	<input type="button" value="목록" onclick="location.href='main.do'">
	
	<hr size="1" width="100%">
	<%-- important --%>
	<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
	<input type="hidden" name="lt_num" id="lt_num" value="${letter.lt_num }">
	<input type="hidden" name="lt_type" id="lt_type" value="${param.letter_type}">
	<h2>${letter.lt_title }</h2>
	<ul class="detail-info">
		<li>보낸사람 : ${letter.lt_sender_id}</li>
		<li>받는사람 : ${letter.lt_receiver_id}</li>
		<li>참조 : ${letter.lt_reference_id}</li>
	</ul>
	
	
	<c:if test="${!empty letter.lt_filename1 || !empty letter.lt_filename2 }">
	<hr size="1" width="100%">
		<ul>
			<li>
				첨부파일
			</li>
			<li>
				<a href="file.do?lt_num=${letter.lt_num}&file_type=1">${letter.lt_filename1}</a>
			</li>
			<li>
				<a href="file.do?lt_num=${letter.lt_num}&file_type=2">${letter.lt_filename2}</a>
			</li>
		</ul>
	</c:if>
	
	<hr size="1" width="100%">
	<c:if test="${fn:endsWith(letter.lt_filename1,'.jpg') ||
				  fn:endsWith(letter.lt_filename1,'.JPG') ||
				  fn:endsWith(letter.lt_filename1,'.JPEG') ||
				  fn:endsWith(letter.lt_filename1,'.jpeg') ||
				  fn:endsWith(letter.lt_filename1,'.GIF') ||
				  fn:endsWith(letter.lt_filename1,'.PNG') ||
				  fn:endsWith(letter.lt_filename1,'.gif') ||
				  fn:endsWith(letter.lt_filename1,'.png')}">
				  
	<div class="align-center">
		<img src="imageView.do?lt_num=${letter.lt_num}&image_type=1" style="max-width:500px;">
	</div>
	</c:if>
	
	<br>
	
	<c:if test="${fn:endsWith(letter.lt_filename2,'.jpg') ||
				  fn:endsWith(letter.lt_filename2,'.JPG') ||
				  fn:endsWith(letter.lt_filename2,'.JPEG') ||
				  fn:endsWith(letter.lt_filename2,'.jpeg') ||
				  fn:endsWith(letter.lt_filename2,'.GIF') ||
				  fn:endsWith(letter.lt_filename2,'.PNG') ||
				  fn:endsWith(letter.lt_filename2,'.gif') ||
				  fn:endsWith(letter.lt_filename2,'.png')}">
				  
	<div class="align-center">
		<img src="imageView.do?lt_num=${letter.lt_num}&image_type=2" style="max-width:500px;">
	</div>
	</c:if>
	
	<p>
		${letter.lt_content }
	</p>
	
	<div id="topButton">
		<input type="button" value="맨위로" id="topButtonImg">
	</div>
	
	
	<c:if test="${param.letter_type==0 || param.letter_type == null || param.letter_type==4}">
		<c:if test="${np.prev_send_id != user.mem_id }">
		<div>${np.prev_num } ${np.prev_send_id} <a href="detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}">${np.prev_title }</a></div>
		</c:if>
		<c:if test="${np.prev_send_id == user.mem_id }">
		<div>${np.prev_num } ${np.prev_receiver_id} <a href="detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}">${np.prev_title }</a></div>
		</c:if>

		<c:if test="${np.next_send_id != user.mem_id }">
		<div>${np.next_num } ${np.next_send_id} <a href="detail.do?lt_num=${np.next_num}&letter_type=${param.letter_type}">${np.next_title }</a></div>
		</c:if>
		<c:if test="${np.next_send_id == user.mem_id }">
		<div>${np.next_num } ${np.next_receiver_id} <a href="detail.do?lt_num=${np.next_num}&letter_type=${param.letter_type}">${np.next_title }</a></div>
		</c:if>
	</c:if>
	
	
	<c:if test="${param.letter_type==1 }">
		<c:if test="${np.prev_num != null }">
		<div>${np.prev_num } ${np.prev_mem_name }(${np.prev_send_id}) <a href="detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}">${np.prev_title }</a></div>
		</c:if>
		<c:if test="${np.next_num != null }">
		<div>${np.next_num } ${np.next_mem_name }(${np.next_send_id}) <a href="detail.do?lt_num=${np.next_num }&letter_type=${param.letter_type}">${np.next_title }</a></div>
		</c:if>
	</c:if>

	<c:if test="${param.letter_type==2 || param.letter_type==3}">
		<div>${np.prev_num } ${np.prev_receiver_id} <a href="detail.do?lt_num=${np.prev_num }&letter_type=${param.letter_type}">${np.prev_title }</a></div>
		<div>${np.next_num } ${np.next_receiver_id} <a href="detail.do?lt_num=${np.next_num }&letter_type=${param.letter_type}">${np.next_title }</a></div>
	</c:if>

</div>

<!-- 내용 끝 -->