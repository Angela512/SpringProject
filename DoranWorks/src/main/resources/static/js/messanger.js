$(function(){
	//검색 유효성 체크
	$('#search_form').submit(function(){
		if($('#keyword').val().trim() == ''){
			alert('검색어를 입력하세요');
			$('#keyword').val('').focus();
			return false;
		}
		
	});
	
	//멤버 체크박스 클릭 시 하단에 선택된 멤버 띄움 
	$('.checkedMember').click(function(){
		//체크된 회원번호 받음(이벤트가 발생한 태그에서 받아옴)
		let mem_num = $(this).attr('data-num');
		let isChecked = $(this).attr("checked", true);
		let modifyUI = '';
		if(isChecked){ //클릭되었으면
			//댓글 수정폼 UI
			modifyUI += '<input type="text" name="members" value="'+ mem_num +'">';
			
			//체크된 멤버 노출
			$('.checked_div').append(modifyUI);
		}else if(!isChecked){//클릭 안되어있거나 해제되었으면
			//댓글 수정폼 UI
			modifyUI += '<input type="text" name="members" value="False!!">';
			
			//체크된 멤버 노출
			$('.checked_div').append(modifyUI);
		}
		
		
		
		
		
		
	});
	
});






