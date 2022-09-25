<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/letter.css">
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
#ck_notice{
	text-decoration: underline !important;
	font-weight: bold;
}
</style>
<!-- include ckedtor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>글수정</h2>
	<form:form action="update.do" modelAttribute="noticeVO" id="register_form" enctype="multipart/form-data">
		<form:hidden path="notice_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li class="notice_form_write">
				<label for="notice_title">제목</label>
				<form:input path="notice_title"/>
				<form:errors path="notice_title" cssClass="error-color"/>
			</li>
			
			<li class="notice_form_write">
				<label>필독여부</label>
				<form:radiobutton path="notice_head" value="0" checked="checked"/>공지
				<form:radiobutton path="notice_head" value="1"/>필독
			</li>
			
			<li class="notice_form_write">
				<label for="upload1">파일첨부1</label>
				<input type="file" name="upload1" id="upload1">
				<c:if test="${!empty noticeVO.notice_filename1}">
				<div id="file_detail1">
					(${noticeVO.notice_filename1})파일 등록
					<input type="button" class="list_button" value="파일삭제" id="file_del1">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del1').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{notice_num:${noticeVO.notice_num},file_type:1},
									type:'post',
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result=='logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail1').hide();
										}else{
											alert('파일 삭제 오류 발생');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								});
							}
						});
					});
				</script>
				</c:if>
			</li>

			<li class="notice_form_write">
				<label for="upload2">파일첨부2</label>
				<input type="file" name="upload2" id="upload2">
				<c:if test="${!empty noticeVO.notice_filename1}">
				<div id="file_detail2">
					(${noticeVO.notice_filename2})파일 등록
					<input type="button" class="list_button" value="파일삭제" id="file_del2">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del2').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{notice_num:${noticeVO.notice_num},file_type:2},
									type:'post',
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result=='logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail2').hide();
										}else{
											alert('파일 삭제 오류 발생');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								});
							}
						});
					});
				</script>
				</c:if>
			</li>
			
			<li class="notice_form_write">
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
			
			<li class="notice_form_write">
				<label>댓글허용</label>
				<form:radiobutton path="notice_replyagree" value="0"/>불가
				<form:radiobutton path="notice_replyagree" value="1"/>허용
			</li>
		</ul>
		
		<div class="align-center">
			<form:button class="list_button">수정</form:button>
			<input type="button" class="list_button" value="목록으로" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>
<!-- 내용 끝 -->