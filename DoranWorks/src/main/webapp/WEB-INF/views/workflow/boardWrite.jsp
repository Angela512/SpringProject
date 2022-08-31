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
				<label for="flow_title">제목</label>
				<form:input path="flow_title"/>
				<form:errors path="flow_title" 
				             cssClass="error-color"/>
			</li>
			<li><b>내용</b></li>
			<li>
				<form:textarea path="flow_content"/>
				<form:errors path="flow_content" 
				             cssClass="error-color"/>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#content' ),{
		            	extraPlugins: [MyCustomUploadAdapterPlugin]
		            })
		            .then( editor => {
						window.editor = editor;
					} )
		            .catch( error => {
		                console.error( error );
		            } );
			    </script>               
			</li>
			<li>
				<label for="flow_sort">결제 종류</label>
				<form:select path="flow_sort">
				    <form:option value="">종류 선택</form:option>
				    <form:option value="기안서">기안서</form:option>
				    <form:option value="휴가신청">휴가신청</form:option>
				    <form:option value="예산서">예산서</form:option>
   				    <form:option value="경비청구">경비청구</form:option>
				</form:select>
			</li>
			<li>
				<label for="upload">파일업로드</label>
				<input type="file" name="upload" id="upload">
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