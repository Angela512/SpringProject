<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>글쓰기</h2>
	<form:form action="write.do" modelAttribute="workflowMainVO"
	        id="register_form"
	        enctype="multipart/form-data">
	    <form:errors element="div" cssClass="error-color"/>    
		<ul>
			<li>
				<label for="flow_sort">결제종류</label>
				<form:radiobutton path="flow_sort" value="휴가신청"  /> 휴가신청
				<form:radiobutton path="flow_sort" value="기안서"/> 기안서
				<form:radiobutton path="flow_sort" value="예산서"/> 예산서
				<form:radiobutton path="flow_sort" value="경비청구"/> 경비청구
			</li>
			<br>
			<li>
				<label for="flow_title">제목</label>
				<form:input path="flow_title"/>
				<form:errors path="flow_title" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="">수신참조</label>
				<form:input path=""/>
				<form:errors path="" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">참조문서</label>
				<input type="file" name="upload" id="upload">
			</li>
			<li>
				<label for="">시행자</label>
				<form:input path=""/>
				<form:errors path="" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="">신청일수</label>
				<form:input path=""/>
				<form:errors path="" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="">휴가구분</label>
				<form:select path="">
				    <form:option value="">종류 선택</form:option>
				    <form:option value="연차">연차</form:option>
				    <form:option value="오후반차">오전반차</form:option>
				    <form:option value="기타">기타</form:option>
				</form:select>
			</li>
			<br>
			<li>
				휴가기간
			</li>
			<li>
				결재정보
			</li>
			<li><b>사유</b></li>
			<li>
				<form:textarea path="flow_content"/>
				<form:errors path="flow_content" 
				             cssClass="error-color"/>               
			</li>
			
		</ul>    
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="목록"
			            onclick="location.href='list.do'">
		</div>    
	</form:form>
</div>
<!-- 내용 끝 -->