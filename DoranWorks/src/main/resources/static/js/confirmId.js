$(function(){
	let checkId = 0;
	
	//아이디 중복 체크
	$('#confirmId').click(function(){
		if($('#mem_id').val().trim == ''){
			$('#message_id').css('color', 'red').text('아이디를 입력하세요');
			$('#mem_id').val('').focus();
			return; //클릭은 기본이벤트 아니라서 return만 해서 함수만 빠져나오는거
		}
		
		//서버 프로그램과 통신
		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{id:$('#mem_id').val()},
			dataType:'json', //반환타입
			cache:false,
			timeout:30000,
			success:function(param){ //데이터는 param에 담겨서 옴
				if(param.result == 'idNotFound'){
					$('#message_id').css('color', '#000').text('등록가능ID');
					checkId = 1; //등록가능ID면 1
				}else if(param.result == 'idDuplicated'){
					//ID가 중복일 경우
					$('#message_id').css('color', 'red').text('중복된 ID');
					$('#mem_id').val('').focus(); //아이디 초기화시킴
					checkId = 0;
				}else if(param.result == 'notMatchPattern'){
					$('#message_id').css('color', 'red').text('영문, 숫자(4~12자) 입력');
					$('#mem_id').val('').focus();
					checkId = 0;
				}else{
					checkId = 0;
					alert('ID중복체크 오류');
				}
			},
			error:function(){ //데이터가 형식에 안맞게 오는 경우?
				checkId=0;
				alert('네트워크 오류 발생');
			}
		});
	}); //end of click
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복값 초기화
	$('#register_form #mem_id').keydown(function(){
		checkId = 0; //중복체크 이후에 다시 입력하면 다시 초기화?
		$('#message_id').text('');		
	}); //end of keydown
	
	//submit이벤트 발생 시 아이디 중복 체크 여부 확인
	$('#register_form').submit(function(){
		if(checkId == 0){
			$('#message_id').css('color', 'red').text('아이디 중복 체크 필수');
			if($('#mem_id').val().trim() == ''){
				$('#mem_id').val('').focus();
			}
			return false;
		}
	});//end of submit
});






