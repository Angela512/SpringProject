$(function(){
	
	let lt_type=0;
	if($('#lt_type').val()!=''){
		lt_type=$('#lt_type').val();
	}
	
	//중요 읽기
	//중요 선택 여부
	function selectData(lt_num){
		$.ajax({
			url:'getImportant.do',
			type:'post',
			data:{lt_num:lt_num,lt_type:lt_type},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				displayImportant(param);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	//중요 등록
	$('#output_fav').click(function(){
		$.ajax({
			url:'writeImportant.do',
			type:'post',
			data:{lt_num:$('#lt_num').val(),lt_type:lt_type},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 별을 체크하세요!');
				}else if(param.result=='success'){
					displayImportant(param);
				}else{
					alert('중요체크시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	
	//중요 표시
	function displayImportant(param){
		let output;
		if(param.status == 'noImportant'){
			output='../images/fav01.gif';
		}else{
			output='../images/fav02.gif';
		}
		//문서 객체에 추가
		$('#output_fav').attr('src',output);
	}
	
	//초기 데이터 표시
	selectData($('#lt_num').val());
	
});