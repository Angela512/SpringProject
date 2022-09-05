<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 내용 시작 -->
<div class="page-main">
	<h2>쪽지쓰기</h2>
	<form action="write.do" id="register_form" enctype="multipart/form-data" method="post">
		<div class="align-right">
			<button>보내기</button>
		</div>
		
		<ul>
			<li>
				<label for="lt_receiver_id">받는사람</label>
				<input type="text" id="lt_receiver_id" name="lt_receiver_id">
			</li>

			<li>
				<label for="lt_reference_id">참조</label>
				<input type="text" id="lt_reference_id" name="lt_reference_id">
			</li>

			<li>
				<input type="checkbox" value="1" name="lt_important" id="lt_important"> 중요!
				<label for="lt_title">제목</label>
				<input type="text" id="lt_title" name="lt_title">
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
				<textarea rows="5" cols="30" name="lt_content"></textarea>
			</li>
		</ul>
	</form>
</div>
<!-- 내용 끝 -->