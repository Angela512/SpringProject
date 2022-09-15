$(function(){
	$(document).on('click', '#createroom_btn', function(){
		//멤버리스트가 이미 show되어져있는데 버튼을 또 클릭하면 안보이게 처리
		if($('#searchChatroom').css('display') != 'none'){
	        $('#searchChatroom').hide();
	    }else{
			$('#searchChatroom').show();
			if($('#searchChatroom').css('display') != 'none'){
				//채팅창이 열려있으면 숨김
				$('.chat_form').hide();
			}
			
		}
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
	
	//================채팅방 목록==============================
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
					
					//chatroomMainUI += '<div>';
					
					chatroomMainUI += '<li><input type="button" value="채팅방 생성" id="createroom_btn"></li>';
					chatroomMainUI += '</ul>';
					//chatroomMainUI += '</div>';
					chatroomMainUI += '</form>';					
					$('.chatroomMain').prepend(chatroomMainUI);
					
					$(param.list).each(function(index, item){
						let chatroomListUI = '';
						chatroomListUI += '<div id="' + item.chatroom_num + '" class="chatroom" data-num="' + item.mem_num + '">';
						chatroomListUI += '[' + item.chatroom_num + '번 채팅방]<br>';
						chatroomListUI += '</div>';
						chatroomListUI += '';
						chatroomListUI += '';
						chatroomListUI += '';
						chatroomListUI += '';
						$('#chatroomList').append(chatroomListUI);
					});
					
					//이 div가 클릭되면 대화창 띄움
					$(document).on('click', '.chatroom', function(){
						//채팅창이 열린 채로 채팅방 생성하기 클릭 시 채팅창 숨김
						if($('#searchChatroom').css('display') != 'none'){
					        $('.chat_form').hide();
					    }
						createChat($(this).attr('id'));
					}); 
				}
			},
			error:function(){
				alert('chatroomList.do 네트워크 오류 발생');
			}
		});
	}
	
	//======================채팅창 띄우기==========================
	function createChat(chatroom_num){
		$.ajax({
			url:'gotochat.do',
			type:'post',
			data:{chatroom_num:chatroom_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				let user_num = param.user_num;
				$('.chat_form').empty();
				$('.chat_form').show();
				let msgUI = '<h3>' + chatroom_num + '번 채팅방</h3>';
				$('.chat_form').append(msgUI);
				//채팅방 대화목록
				$(param.msgList).each(function(index, item){
					msgUI = '';
					msgUI += '<div class=';
					if(item.mem_num != user_num){
						msgUI += '"align-left"';
					}else{
						msgUI += '"align-right"';
					}
					msgUI += '><b>'+item.total_cnt+'[' + item.mem_name + '] : ' + item.msg_content + '</b> <span>' + item.msg_sendtime +'</span></div>';
					msgUI += '';
					$('.chat_form').append(msgUI);
				});
				let chatUI = '';
				chatUI += '<form id="chat_form">';
				chatUI += '<input type="hidden" name="chatroom_num" value="' + chatroom_num + '">';
				chatUI += '<textarea rows="8" cols="60" name="msg_content" id="msg_content" class="msgContent"></textarea>';
				chatUI += '<div id="msg_first"><span class="letter-count">100/100</span></div>';
				chatUI += '<input type="file" name="upload">';
				chatUI += '<div id="msg_second" class="align-right"><input type="submit" value="전송"></div>';
				chatUI += '</form>';
				
				$('.chat_form').append(chatUI);
				
				
				
			},
			error:function(){
				alert('gotochat.do 네트워크 오류 발생');
			}
		});
	}
	
	//메시지 전송
	$(document).on('submit', '#chat_form', function(event){
		if($('#msg_content').val().trim() == ''){
			alert('내용을 입력하세요');
			$('#msg_content').val('').focus();
			return false;
		}
		//메시지 전송
		let form_data = new FormData($(this)[0]);
		$.ajax({
			url:'writeMsg.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false,
			enctype:'multipart/form-data',
			processData:false,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용 가능');
				}else if(param.result == 'success'){
					let chatroom_num = param.chatroom_num;
					createChat(chatroom_num);
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	//글자수 
	$(document).on('keyup', 'textarea', function(){
		//입력한 글자 수
		let inputLength = $(this).val().length;
		
		if(inputLength > 100){ //100자 이상인 경우
			$(this).val($(this).val().substring(0,100)); //100자 넘으면 잘라냄
		}else{ //300자 이하인 경우
			//남은 글자 수 구하기
			let remain = 100 - inputLength;
			remain += '/100';
			if($(this).attr('id') == 'msg_content'){
				//댓글 등록 폼 글자 수 
				$('#msg_first .letter-count').text(remain);
			}
		}
	});
	
	
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
						$('#' + mem_num).remove();
						if($('.checked_div *').length == 0){//체크된 멤버가 없으면 폼 숨김
							$('#checked_form').hide();
						}
					}else{ //체크 안되어 있으면 체크하기
						isChecked = $(this).attr("checked", true);
						//한명이라도 체크되면 div폼 노출
						$('#checked_form').show();
						modifyUI += '<span name="members" value="'+ mem_num +'" id="'+ mem_num + '">' + mem_name + '</span>';
						
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
				alert('success');
				let chatroom_num = param.chatroom_num;
				//채팅방 리스트에도 추가
				$('.chatroomMain').empty();
				$('#chatroomList').empty();
				list();
				//대화창 열기
				createChat(chatroom_num);
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






