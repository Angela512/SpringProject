<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 내용 시작 -->
<div class="page-main">
	<h2>회의실예약하기</h2>
	<form action="write.do" id="register_form" enctype="multipart/form-data" method="post">
		<div class="align-right">
			<button>예약</button>
		</div>
		
		<ul>
			<li>
				<label for="send_receiver_id">회의명</label>
				<input type="text" id="send_receiver_id" name="send_receiver_id">
			</li>

			<li>
				<label for="send_reference_id">일시</label>
				<input type="text" id="send_reference_id" name="send_reference_id">
			</li>

			<li>
				<input type="checkbox" value="1" name="send_important" id="send_important"> 중요!
				<label for="send_title">회의실</label>
				<input type="text" id="send_title" name="send_title">
			</li>
			<li>
				<input type="checkbox" value="1" name="send_important" id="send_important"> 중요!
				<label for="send_title">참석자</label>
				<input type="text" id="send_title" name="send_title">
			</li>
			
			
			<li>
				<textarea rows="5" cols="30" name="send_content"></textarea>
			</li>
		</ul>
	</form>
</div>
<!-- 내용 끝 -->