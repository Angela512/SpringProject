$(function(){
	$('#register_form').submit(function(){
		if($('#lt_receiver_id').val().trim()==''){
			alert('받는사람을 입력하세요!');
			$('#lt_receiver_id').val('').focus();
			return false;
		}

		if($('#lt_title').val().trim()==''){
			alert('제목을 입력하세요!');
			$('#lt_title').val('').focus();
			return false;
		}

		if($('#lt_content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#lt_content').val('').focus();
			return false;
		}
	});
	
	function reqList() {
		alert('hi');
		
		$.ajax({
			success:function(data){
				alert('요청성공');
				console.log(data);
			},
			error:function(){
				alert('쪽지보내기 실패!');
			}
		});
	}
});