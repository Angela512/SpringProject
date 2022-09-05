$(function(){
	$('#createroom_btn').click(function(){
		$('#searchChatroom').show();
		//초기데이터 호출
		selectList();
	});
	
	//검색 유효성 체크
	$('#search_form').submit(function(){
		if($('#keyword').val().trim() == ''){
			alert('검색어를 입력하세요');
			$('#keyword').val('').focus();
			return false;
		}
		
	});
	
	//멤버 리스트 및 검색
	function selectList(){
		$.ajax({
			url:'createChatroom.do',
			type:'post',
			data:{keyword:$('#keyword').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				count = param.count;
				$(param.list).each(function(index, item){
					let output = '<h2>멤버 선택</h2>';
					/*output += '<h4>';
					output += '<img src="../member/viewProfile.do?mem_num='+ item.mem_num +'" width="30" height="30" class="my-photo">';
					if(item.nick_name){
						output += item.nick_name + '</h4>';
					}else{
						output += item.id + '</h4>';
					}
					output += '<div class="sub-item">';
					output += '<p>' + item.re_content.replace(/\r\n/g,'<br>') + '</p>';
					
					if(item.re_mdate){
						output += '<span class="modify-date">최근 수정일 : ' + item.re_mdate + '</span>';
					}else{
						output += '<span class="modify-date">등록일 : ' + item.re_date + '</span>';					
					}
					
					if(param.user_num == item.mem_num){ //user_num을 BoardAjaxCntr에서 받아옴
						//로그인 회원 == 댓글 작성자
						output += ' <input type="button" data-num="' + item.re_num + '" value="수정" class="modify-btn">';
						output += ' <input type="button" data-num="' + item.re_num + '" value="삭제" class="delete-btn">';
					}
					output += '<hr size="1" noshade>';
					output += '</div>'; //sub-item을 닫음
					output += '</div>';*/
					 
					//문서 객체에 추가
					$('#memberList').append(output);
					 
					
				});
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	//멤버 체크박스 클릭 시 하단에 선택된 멤버 띄움 
	$('.checkedMember').click(function(){
		//체크된 회원번호 받음(이벤트가 발생한 태그에서 받아옴)
		let mem_num = $(this).attr('data-num');
		let mem_name = $(this).attr('id');
		let modifyUI = '';
		let isChecked = $(this).attr("checked");
		if(isChecked){ //이미 체크되어있으면 체크 해제
			isChecked = $(this).attr("checked", false);
			//폼에서도 삭제함
			$('#'+mem_num).remove();
			/*if($('.checked_div').childNodes == null){//자식 노드가 없으면
				$('#checked_form').hide();
			}*/
		}else{ //체크 안되어 있으면 체크하기
			isChecked = $(this).attr("checked", true);
			//한명이라도 체크되면 div폼 노출
			$('#checked_form').show();
			//댓글 수정폼 UI
			modifyUI += '<input type="text" name="members" value="'+ mem_name +'" id="'+ mem_num + '">';
			
			//체크된 멤버 노출
			$('.checked_div').append(modifyUI);
		}
		
		
		
		
		
		
		
		
	});
	
});






