<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<div class="page-main">
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
	
	<div>${np.prev_num } ${np.prev_mem_name } ${np.prev_title }</div>
	<div>${np.next_num } ${np.next_mem_name } ${np.next_title }</div>
</div>

<!-- 내용 끝 -->