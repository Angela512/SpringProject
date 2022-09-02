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
		<%-- 
			<li>
				<label for="flow_sort">결제종류</label>
				<form:radiobutton path="flow_sort" value="휴가신청"  /> 휴가신청
				<form:radiobutton path="flow_sort" value="기안서" selected="selected"/> 기안서
				<form:radiobutton path="flow_sort" value="예산서"/> 예산서
				<form:radiobutton path="flow_sort" value="경비청구"/> 경비청구
			</li>
			<br>
			 --%>
			<li>
				<label for="flow_sort">결제종류</label>
				<input type="radio" name="flow_sort" value="휴가신청" checked> 휴가신청
				<input type="radio" name="flow_sort" value="기안서" disabled> 기안서
				<input type="radio" name="flow_sort" value="예산서" disabled> 예산서
				<input type="radio" name="flow_sort" value="경비청구" disabled> 경비청구			
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
				<input type="text" name="" disabled />				
			</li>
			<li>
				<label for="upload">참조문서</label>
				<input type="file" name=""  disabled/>
			</li>
			<li>
				<label for="">시행자</label>
				<input type="text" name="" disabled />
			</li>
			<li>
				<label for="">신청일수</label>
				<input type="text" name="" disabled />
			</li>
			<li>
				<label for="flow_subsort">휴가구분</label>
				<form:select path="flow_subsort">
				    <form:option value="">종류 선택</form:option>
				    <form:option value="연차">연차</form:option>
				    <form:option value="오후반차">오후반차</form:option>
				    <form:option value="오전반차">오전반차</form:option>
				</form:select>
			</li>
			<br>
			<li>
				<label for="flow_start">휴가기간</label>
				<input type="date" name="flow_start" />
				-
				<input type="date" name="flow_end" />
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