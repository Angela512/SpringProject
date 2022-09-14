<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/letter.detail.important.js"></script>
<div class="page-main">
	<%-- important --%>
	<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
	<input type="hidden" name="lt_num" id="lt_num" value="${letter.lt_num }">
	<h2>${letter.lt_title }</h2>
	<ul class="detail-info">
		<li>보낸사람 : ${letter.lt_sender_id}</li>
		<li>받는사람 : ${letter.lt_receiver_id}</li>
		<li>참조 : ${letter.lt_reference_id}</li>
	</ul>
	
	<hr size="1" width="100%">
	
	<ul>
		<c:if test="${!empty letter.lt_filename1 || !empty letter.lt_filename2 }">
		<li>
			첨부파일 : 
		</li>
		</c:if>
	</ul>
	
	<hr size="1" width="100%">
	
	<p>
		${letter.lt_content }
	</p>
	
	
	<c:if test="${param.letter_type==0 || param.letter_type == null || param.letter_type==4}">
		<c:if test="${np.prev_send_id != user.mem_id }">
		<div>${np.prev_num } ${np.prev_send_id} ${np.prev_title }</div>
		</c:if>
		<c:if test="${np.prev_send_id == user.mem_id }">
		<div>${np.prev_num } ${np.prev_receiver_id} ${np.prev_title }</div>
		</c:if>

		<c:if test="${np.next_send_id != user.mem_id }">
		<div>${np.next_num } ${np.next_send_id} ${np.next_title }</div>
		</c:if>
		<c:if test="${np.next_send_id == user.mem_id }">
		<div>${np.next_num } ${np.next_receiver_id} ${np.next_title }</div>
		</c:if>
	</c:if>
	
	
	<c:if test="${param.letter_type==1 }">
		<c:if test="${np.prev_num != null }">
		<div>${np.prev_num } ${np.prev_mem_name }(${np.prev_send_id}) ${np.prev_title }</div>
		</c:if>
		<c:if test="${np.next_num != null }">
		<div>${np.next_num } ${np.next_mem_name }(${np.next_send_id}) ${np.next_title }</div>
		</c:if>
	</c:if>

	<c:if test="${param.letter_type==2 || param.letter_type==3}">
		<div>${np.prev_num } ${np.prev_receiver_id} ${np.prev_title }</div>
		<div>${np.next_num } ${np.next_receiver_id} ${np.next_title }</div>
	</c:if>

</div>

<!-- 내용 끝 -->