$(function(){
	let star;
	let lt_type=0;
	if($('#lt_type').val()!=''){
		lt_type=$('#lt_type').val();
	}
	
	
	
	//중요 등록
	$('.output_important').click(function(){
		
		star=$(this);
		
		$.ajax({
			url:'writeImportant.do',
			type:'post',
			data:{lt_num:$(this).attr('data-ltnum')},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 별을 체크하세요!');
				}else if(param.result=='success'){
					if(lt_type==4){
						location.reload();
					}else{
						displayImportant(param);
					}
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
		star.attr('src',output);
	}
	
	
	//읽음버튼클릭
	$('#list_read').click(function(){
		let cheks='';
		if($('input[type=checkbox]:checked').length<1){
			alert('하나 이상 선택하세요!');
			return;
		}
		
		 $('input[type=checkbox]:checked').each(function(index){
			if(index==0){
				cheks+=$(this).val();
			}else{
				cheks+=','+$(this).val();
			}
		});
		
		$.ajax({
			url:'listRead.do',
			type:'post',
			data:{lt_nums:cheks},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='logout'){
					alert('로그인 후 가능합니다!');
				}else if(param.result=='success'){
					location.reload();
				}else{
					alert('읽음처리시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
		
	});
	
	//삭제버튼클릭
	$('#list_delete').click(function(){
		let cheks='';
		if($('input[type=checkbox]:checked').length<1){
			alert('하나 이상 선택하세요!');
			return;
		}
		
		 $('input[type=checkbox]:checked').each(function(index){
			if(index==0){
				cheks+=$(this).val();
			}else{
				cheks+=','+$(this).val();
			}
		});
		
		$.ajax({
			url:'listDelete.do',
			type:'post',
			data:{lt_nums:cheks},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='logout'){
					alert('로그인 후 가능합니다!');
				}else if(param.result=='success'){
					alert($('input[type=checkbox]:checked').length+'개의 쪽지가 삭제되었습니다.');
					location.reload();
				}else{
					alert('삭제시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
		
	});
});