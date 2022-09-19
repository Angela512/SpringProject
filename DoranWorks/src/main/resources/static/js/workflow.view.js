$(function(){
	$('#test').click(function(){
		$.ajax({
			url:'updateii.do',
			type:'post',
			data:{fw_num:$('#fw_num').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용하세요');
				}else if(param.result=='success'){
					alert('승인처리 완료!');
				}else{
					alert('승인처리시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
});