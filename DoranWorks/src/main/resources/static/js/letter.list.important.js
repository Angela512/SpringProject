$(function(){
	let star;
	
	//중요 등록
	$('.output_important').click(function(){
		let lt_type=$('#lt_type').val();
		
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
				
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
		
		alert(cheks);
	});
});