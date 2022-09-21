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
<!-- include ckedtor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>글쓰기</h2>
	<form:form action="write.do" modelAttribute="noticeVO" id="register_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="notice_title">제목</label>
				<form:input path="notice_title"/>
				<form:errors path="notice_title" cssClass="error-color"/>
			</li>
			
			<li>
				<label>필독여부</label>
				<form:radiobutton path="notice_head" value="0" checked="checked"/>공지
				<form:radiobutton path="notice_head" value="1"/>필독
			</li>
			
			<li>
				<label for="upload1">파일첨부1</label>
				<input type="file" name="upload1" id="upload1">
			</li>

			<li>
				<label for="upload2">파일첨부2</label>
				<input type="file" name="upload2" id="upload2">
			</li>
			
			<li>
				<form:textarea path="notice_content"/>
				<form:errors path="notice_content" cssClass="error-color"/>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#notice_content' ),{
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
				<label>댓글허용</label>
				<form:radiobutton path="notice_replyagree" value="0" checked="checked"/>불가
				<form:radiobutton path="notice_replyagree" value="1"/>허용
			</li>
		</ul>
		
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="작성취소" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>
<!-- 내용 끝 -->