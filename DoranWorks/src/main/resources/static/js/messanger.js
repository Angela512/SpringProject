$(function(){
	$('#createroom_btn').click(function(){
		$('#searchChatroom').show();
		
		if($('#member_list *').length == 0){ 
			//member_list테이블 아래에 아무것도 없으면 초기 데이터 호출
			selectList();
		}
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
				let count = param.count;
				let user_num = param.user_num;
				
				$(param.list).each(function(index, item){
					if(user_num != item.mem_num){ //로그인한 회원은 제외하고 멤버 리스트 띄움
						let output = '';
						output += '<tr>';
						output += '<td>';
						output += '<input type="checkbox" name="mem_num" data-num="' + item.mem_num + '" id="' + item.mem_name + '" class="checkedMember">';
						output += '</td>';
						output += '<td><a href="detail.do?mem_num='+ item.mem_num + '">' + item.mem_name + '</a></td>';
						output += '<td>' + item.mem_dpt + '</td>';
						output += '</tr>';
						//문서 객체에 추가
						$('#member_list').append(output);
					}
				});
				
				$(document).on('click', '.checkedMember', function(){
					//체크된 회원번호 받음(이벤트가 발생한 태그에서 받아옴)
					let mem_num = $(this).attr('data-num');
					let mem_name = $(this).attr('id');
					let modifyUI = '';
					let isChecked = $(this).attr("checked");
					if(isChecked){ //이미 체크되어있으면 체크 해제
						isChecked = $(this).attr("checked", false);
						//폼에서도 삭제함
						$('#'+mem_num).remove();
						if($('.checked_div *').length == 0){//체크된 멤버가 없으면 폼 숨김
							$('#checked_form').hide();
						}
					}else{ //체크 안되어 있으면 체크하기
						isChecked = $(this).attr("checked", true);
						//한명이라도 체크되면 div폼 노출
						$('#checked_form').show();
						//댓글 수정폼 UI
						modifyUI += '<input type="text" name="members" value="'+ mem_num +'" id="'+ mem_num + '">';
						
						//체크된 멤버 노출
						$('.checked_div').append(modifyUI);
					}
				}); //end of document(checkedMember)
				
				//취소버튼 클릭 시 멤버 리스트 폼 숨김
				$('.mem_reset').click(function(){
					$('#searchChatroom').hide();
				});
				
			}, //end of success()
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	} //end of selectList()
	
	//멤버 선택 후 확인하면 채팅방 생성
	$('#checked_form').submit(function(event){
		let form_data = $(this).serialize();
		$('#searchChatroom').hide();
		
		//데이터 전송
		$.ajax({
			url:'confirm.do',
			type:'post',
			data:form_data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				let user_num = param.user_num;
				let chatUI;
				$(param.list).each(function(index, item){
					chatUI = '';
					chatUI += '<h2>' + item.mem_name + '</h2>';
					$('.chat_start').append(chatUI);
				});
				chatUI = '';
				chatUI += '<textarea rows="8" cols="90" name="msg_content" id="msg_content" class="msgContent">'
				
				$('.chat_start').append(chatUI);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		event.preventDefault();
	});
	
	
	
	
	
	
	
});






