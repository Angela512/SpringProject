<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/letter.write.js"></script>
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
	<h2>쪽지답장</h2>
	<form action="write.do" id="register_form" enctype="multipart/form-data" method="post">
		<div class="align-right">
			<button>보내기</button>
		</div>
		
		<ul>
			<li>
				<label for="lt_receiver_id">받는사람</label>
				<input type="text" id="lt_receiver_id" name="lt_receiver_id" value="${letter.lt_sender_id}">
			</li>

			<li>
				<label for="lt_reference_id">참조</label>
				<input type="text" id="lt_reference_id" name="lt_reference_id">
			</li>

			<li>
				<label for="lt_title">제목</label>
				<input type="text" id="lt_title" name="lt_title" value="RE: ${letter.lt_title}">
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
				<textarea rows="5" cols="30" name="lt_content" id="lt_content">${letter.lt_content}</textarea>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#lt_content' ),{
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
		</ul>
	</form>
</div>
<!-- 내용 끝 -->