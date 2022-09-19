$(function(){
	
	
	//중요 읽기
	//중요 선택 여부
	function selectData(lt_num){
		$.ajax({
			url:'getImportant.do',
			type:'post',
			data:{lt_num:lt_num},
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
			data:{lt_num:$('#lt_num').val()},
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
	
	//삭제 버튼 클릭시
	$('#detail_delete').click(function(){
		let choice = confirm('삭제하시겠습니까?');
		if(choice){
			location.replace('detailDelete.do?lt_num='+$('#lt_num').val());
		}
	});
	
	//안읽음 버튼 클릭시
	$('#detail_noread').click(function(){
		$.ajax({
			url:'detailNoRead.do',
			type:'post',
			data:{lt_num:$('#lt_num').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용해주세요!');
				}else if(param.result=='success'){
					alert('안읽음 처리 완료!');
				}else{
					alert('안읽음 처리시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	$(window).scroll(function() {
	    // top button controll
	    if ($(this).scrollTop() > 50) {
	        $('#topButton').fadeIn();
	    } else {
	        $('#topButton').fadeOut();
	    }
	});

	$("#topButtonImg").click(function() {
		$('html, body').animate({scrollTop:0}, '300');
	});
	
	//초기 데이터 표시
	selectData($('#lt_num').val());
	
});