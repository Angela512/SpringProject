$(function(){
	$(document).on('click', '#createroom_btn', function(){
		$('#searchChatroom').show();
		
		if($('#member_list *').length == 0){ 
			//member_list테이블 아래에 아무것도 없으면 초기 데이터 호출
			selectList();
		}
	});
	
	$('#chat_list').click(function(){
		$('#chat_list').hide();
		createChat();
	});
	
	
	//검색 유효성 체크
	$('#search_form').submit(function(){
		if($('#keyword').val().trim() == ''){
			alert('검색어를 입력하세요');
			$('#keyword').val('').focus();
			return false;
		}
	});
	
	//채팅방 목록
	function list(){
		$.ajax({
			url:'chatroomList.do',
			type:'post',
			data:{keyword:$('#keyword').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					let chatroomMainUI = '';
					chatroomMainUI += '<h2>메신저</h2>';
					chatroomMainUI += '<form action="list.do" id="search_form" method="get">';
					chatroomMainUI += '<ul class="search">';
					chatroomMainUI += '<li><input type="search" name="keyword" id="keyword" placeholder="채팅방 이름, 메시지 검색"></li>';
					chatroomMainUI += '</ul>';
					chatroomMainUI += '<div>';
					
					chatroomMainUI += '<input type="button" value="채팅방 생성" id="createroom_btn">';
					chatroomMainUI += '</div>';
					chatroomMainUI += '</form>';					
					$('.chatroomMain').prepend(chatroomMainUI);
					
					$(param.list).each(function(index, item){
						let chatroomListUI = '';
						chatroomListUI += '<div id="' + item.chatroom_num + '" class="chatroom" data-num="' + item.mem_num + '">';
						chatroomListUI += '[' + item.chatroom_num + '] -> mem_num : [' + item.mem_num + ']<br>';
						chatroomListUI += '</div>';
						chatroomListUI += '';
						chatroomListUI += '';
						chatroomListUI += '';
						chatroomListUI += '';
						$('#chatroomList').append(chatroomListUI);
					});
					
					//이 div가 클릭되면 대화창 띄움
					$(document).on('click', '.chatroom', function(){
						if($('.chat_start *').length == 0){ 
							createChat($(this).attr('id'));
						}
					}); 
				}
			},
			error:function(){
				//로딩 이미지 감추기
				alert('chatroomList.do 네트워크 오류 발생');
			}
		});
	}
	
	//대화창 띄우기
	function createChat(chatroom_num){
		let form_data = $(this).serialize();
		//로딩 이미지 노출 (url:listReply.do는 BoardAjaxCntrl에 있음)
		$.ajax({
			url:'gotochat.do',
			type:'post',
			data:{chatroom_num:chatroom_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				let user_num = param.user_num;
				let chatUI;
				$(param.list).each(function(index, item){
					if(user_num != item.mem_num){ //대화창에서 상대방 프로필만 띄움
						
					}
					
				});
				chatUI = '';
				chatUI += '<textarea rows="8" cols="90" name="msg_content" id="msg_content" class="msgContent">';
				
				$('.chat_start').append(chatUI);
			},
			error:function(){
				//로딩 이미지 감추기
				alert('gotochat.do 네트워크 오류 발생');
			}
		});
	}
	
	
	//멤버 리스트 및 검색(완료)
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
				let searchChatroomUI = '';
				searchChatroomUI += '<h2>멤버 선택</h2>';
				searchChatroomUI += '<form action="createChatroom.do" id="search_form" method="get">';
				searchChatroomUI += '<ul class="search">';
				searchChatroomUI += '<li>';
				searchChatroomUI += '<input type="search" name="keyword" id="keyword" value="${param.keyword}" placeholder="이름, 부서, 이메일 검색">'; 
				searchChatroomUI += '</li>';
				searchChatroomUI += '</ul>';
				searchChatroomUI += '<div><table id="member_list"></table></div>';
				searchChatroomUI += '</form>';
				$('#mem_list').append(searchChatroomUI);
				
				$(param.list).each(function(index, item){
					if(user_num != item.mem_num){ //로그인한 회원은 제외하고 멤버 리스트 띄움
						let member_listUI = '';
						member_listUI += '<tr>';
						member_listUI += '<td>';
						member_listUI += '<input type="checkbox" name="mem_num" data-num="' + item.mem_num + '" id="' + item.mem_name + '" class="checkedMember">';
						member_listUI += '</td>';
						member_listUI += '<td><a href="detail.do?mem_num='+ item.mem_num + '">' + item.mem_name + '</a></td>';
						member_listUI += '<td>' + item.mem_dpt + '</td>';
						member_listUI += '</tr>';
						//문서 객체에 추가
						$('#member_list').append(member_listUI);
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
			/*data:form_data,*/
			data:{chatroom_num:chatroom_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				alert('success');
				let user_num = param.chatroom_num;
				createChat();
			},
			error:function(){
				alert('confirm.do 네트워크 오류 발생');
			}
		});
		event.preventDefault();
	});
	
	//초기 데이터 호출
	list();
	
	
	
	
});






