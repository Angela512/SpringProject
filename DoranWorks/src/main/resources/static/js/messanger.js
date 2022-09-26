$(function(){
	let wsocket;
	
	$(document).on('click', '#createroom_btn', function(){
		//멤버리스트가 이미 show되어져있는데 버튼을 또 클릭하면 안보이게 처리&선택된 멤버 초기화
		if($('#searchChatroom').css('display') != 'none'){
			$('#member_list').empty();
			$('.checked_ul').empty();
	        $('#searchChatroom').hide();
	    }else{
			$('#searchChatroom').show();
			if($('#searchChatroom').css('display') != 'none'){
				//채팅창이 열려있으면 숨김
				$('.chat_form').hide();
				$('.msg_formUI').hide();
			}
			
		}
	//	$('#member_list').empty();
		selectList();
		/*if($('#member_list *').length == 0){ 
			//member_list테이블 아래에 아무것도 없으면 초기 데이터 호출
			
		}*/
	});
	
	$('#chat_list').click(function(){
		$('#chat_list').hide();
		createChat();
	});
	
	
	//검색 유효성 체크
	$('#search_form').submit(function(){
		if($('#chat_keyword').val().trim() == ''){
			alert('검색어를 입력하세요');
			$('#chat_keyword').val('').focus();
			return false;
		}
	});
	
	//오늘 날짜 구하기
	let d = new Date();
	let strDate;
	if(d.getMonth() + 1 < 10){ //month가 두자리수가 아니면 월 앞에 0붙임
		strDate = d.getFullYear() + "-0" + (d.getMonth()+1) + "-" + d.getDate();
	}else{
		strDate = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();
	}
	
	
	
	//================채팅방 목록==============================
	function list(){
		$.ajax({
			url:'chatroomList.do',
			type:'post',
			data:{keyword:$('#chat_keyword').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				let user_name = param.user_name; //세션에 로그인한 사람의 이름 받음
				
				$('.chatroomMain').empty();
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					let chatroomListUI = '<div id="chatroomList">';
					$(param.list).each(function(index, item){
						chatroomListUI += '<div id="' + item.chatroom_num + '" class="chatroom">';
						 //내 이름은 빼고 출력
						let chatNameStr = item.chatroom_name;
						chatNameStr = chatNameStr.replace(user_name, '').replace(/, , /g, ', ');
												
						if(chatNameStr.substr(0,1) == ','){
							chatNameStr = chatNameStr.replace(', ', '');
						}
						chatNameStr = chatNameStr.replace(/, $/, '');
						
						chatroomListUI += '<span class="chatName">[' + chatNameStr + ']</span> <span class="chatCnt">(' + item.count + ') </span><span class="chatTime">';
						//최신 메시지가 오늘이면 시간만, 오늘이 아니면 날짜만 표시
						chatroomListUI += (item.messangerVO == null ? '' : (item.messangerVO.msg_sendtime).substr(0,10) == strDate ? (item.messangerVO.msg_sendtime).substr(11,5) : (item.messangerVO.msg_sendtime).substr(0,10)) + '</span><br>';
						chatroomListUI += '<span class="chatDate">' + (item.messangerVO == null ? '' : item.messangerVO.msg_content.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\r\n/g, '<br>').replace(/\r/g,'<br>').replace(/\n/g,'<br>')) + '</span>';
						chatroomListUI += '</div>';
						chatroomListUI += '';
						chatroomListUI += '';
					});
					chatroomListUI += '</div>';
					$('.chatroomMain').append(chatroomListUI);
				}
			},
			error:function(){
				alert('chatroomList.do 네트워크 오류 발생');
			}
		});
	}
	
	//이 div가 클릭되면 대화창 띄움
	$(document).on('click', '.chatroom', function(){
		//멤버 리스트가 show인데 div 클릭하면 멤버리스트 hide
		if($('#searchChatroom').css('display') != 'none'){
			$('#searchChatroom').hide();
		}
		createChat($(this).attr('id'));
	}); 
	
	$(document).on('keyup','#chat_keyword',function(){
		list();
	});
	
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
				let user_name = param.user_name;
				$('.chat_form').empty();
				$('.msg_formUI').empty();
				$('.chatroom').css('background-color','#FFF');
				
				$('.chat_form').show();
				$('.msg_formUI').show();
				$('#' + chatroom_num).css('background-color','#D6FFFF');
				let msgUI = '<div class="chat_head">';
				var i = 0;
				let len = $(param.list).length;
				let memNameStr = '';
				$(param.list).each(function(index, item){
					let chatNameStr = item.chatroom_name;
					chatNameStr = chatNameStr.replace(user_name, '').replace(/, , /g, ', ');
											
					if(chatNameStr.substr(0,1) == ','){
						chatNameStr = chatNameStr.replace(', ', '');
					}
					chatNameStr = chatNameStr.replace(/, $/, '');
					
					for(i; i < 1; i++){ //한번만 실행
						msgUI += '<h3>' + chatNameStr + ' | ' + item.chatroom_num + '</h3>';
						msgUI += '<span>멤버 : ';
					}
					
					memNameStr += item.mem_name + ', ';
				});
				//========채팅방 메시지 찍기========
				memNameStr = memNameStr.replace(user_name, '').replace(/, , /g, ', ');
				if(memNameStr.substr(0,1) == ','){
					memNameStr = memNameStr.replace(', ', '');
				}
				memNameStr = memNameStr.replace(/, $/, '');
				msgUI += memNameStr;
				msgUI += '</span></div>';
				$('.chat_form').append(msgUI);
				//채팅방 대화목록
				let msg_time;
				msgUI = '<div class="chat_body">';
				$(param.msgList).each(function(index, item){
					//알람 삭제(알람,페이지 이동이 없으면 0)
                	deleteAlarm(1,0);
					//msgUI = '';
					let sendtime = (item.msg_sendtime).substr(0,10);
					
					//오늘이면 날짜 표시(오늘 날짜를 한번도 안띄웠을 경우에만)
					if(msg_time != sendtime){ //날짜가 달라질때만 날짜 띄움
						msg_time = sendtime;
						msgUI += '<div id="' + sendtime + '" class="msg_sendtime"><span class="sendtime_span">' + sendtime + '</span></div>';
					}
					
					//채팅 말풍선 시작
					msgUI += '<div class=';
					if(item.mem_num != user_num){
						msgUI += '"your_chat">'; 
						if(item.mem_photo_name != null){//사진이 있으면 띄움
							msgUI += '<img src="../member/viewProfile.do?mem_num=' + item.mem_num + '" width="50" height="50" class="you_photo">';
						}else{
							msgUI += '<img src="../images/face.png" width="50" height="50" class="you_photo">';
						}
						msgUI += '<div class="you_name">' + item.mem_name + '</div>';
						msgUI += '<div class="you_bubble">';
						
					}else{
						msgUI += '"my_chat">';
						msgUI += '<div class="my_name">' + '&nbsp;' + '</div>';
						msgUI += '<div class="me_bubble">';
					}
					msgUI += item.msg_content.replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\r\n/g, '<br>').replace(/\r/g,'<br>').replace(/\n/g,'<br>');
					msgUI += '</div>';
					msgUI += '<span class="time_count">';
					if(item.total_cnt != 0){
						msgUI += item.total_cnt;
					}
					
					msgUI += '<br>' + (item.msg_sendtime).substr(11,5);
					msgUI += '</span>';
					msgUI += '</div>';
					msgUI += '';
					msgUI += '';
					msgUI += '';
					//$('.chat_form').append(msgUI);
				});
				msgUI += '</div>';
				$('.chat_form').append(msgUI);
				$(".chat_body").scrollTop($(".chat_body")[0].scrollHeight);
	
				let chatUI = '';
				chatUI += '<form id="msg_form">';
				chatUI += '<input type="hidden" name="chatroom_num" value="' + chatroom_num + '">';
				chatUI += '<textarea rows="8" cols="60" name="msg_content" id="msg_content" class="msgContent"></textarea>';
				chatUI += '<div id="msg_first"><span class="letter-count"></span></div>';
				chatUI += '<input type="file" name="upload">';
				chatUI += '<div id="msg_second" class="align-right"><input type="submit" value="전송"></div>';
				chatUI += '</form>';
				
				$('.msg_formUI').append(chatUI);
			},
			error:function(){
				wsocket.close();
				alert('gotochat.do 네트워크 오류 발생');
			}
		});
	}
	
	//====================메시지 전송======================
	$(document).on('submit', '#msg_form', function(event){
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
					wsocket.close();
					alert('로그인 후 사용 가능');
				}else if(param.result == 'success'){
					
					let chatroom_num = param.chatroom_num;
					$('.chatroomMain').empty();
				//	$('#chatroomList').empty();
				    wsocket.send('msg:'+chatroom_num);

				//=================알림 처리===================
                  aalarm_socket.send('msg:1');

					list();
					createChat(chatroom_num);
				}
			},
			error:function(){
				wsocket.close();
				alert('네트워크 오류 발생');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	/*//글자수 
	$(document).on('keyup', 'textarea', function(){
		//입력한 글자 수
		let inputLength = $(this).val().length;
		
		if(inputLength > 4000){ //100자 이상인 경우
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
	});*/
	
	$(document).on('keydown', 'textarea', function(event){
        if (event.keyCode == 13)
            if (!event.shiftKey){
                event.preventDefault();
                $('#msg_form').submit();
            }
	});
	
	
	//멤버 리스트 및 검색(완료)
	function selectList(){
		$.ajax({
			url:'createChatroom.do',
			type:'post',
			data:{keyword:$('#mem_keyword').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				$('#member_list').empty();
				let count = param.count;
				let user_num = param.user_num;
				
				$(param.list).each(function(index, item){
					if(user_num != item.mem_num){ //로그인한 회원은 제외하고 멤버 리스트 띄움
						let member_listUI = '';
						member_listUI += '<div id="li_' + item.mem_num + '" class="mem_li">';
						member_listUI += '<input type="checkbox" name="mem_num" data-num="' + item.mem_num + '" id="' + item.mem_name + '" class="checkedMember">';
						if(item.mem_photo_name != null){ //사진이 있으면 사진 출력
							member_listUI += '<img src="../member/viewProfile.do?mem_num=' + item.mem_num + '" width="50" height="50" class="my-photo">';  
						} else{
							member_listUI += '<img src="../images/face.png" width="50" height="50" class="my-photo">';
						}
						member_listUI += '<div class="divdiv"><span class="div_span">' + item.mem_name + '</span><br>';
						member_listUI += item.mem_dpt + ' | ';
						member_listUI += item.mem_rank + '</div>';
						member_listUI += '</div>';
						//문서 객체에 추가
						$('#member_list').append(member_listUI);
					}
				});
				
				$(document).on('click', '.checkedMember', function(){
					//체크된 회원번호 받음(이벤트가 발생한 태그에서 받아옴)
					let mem_num = $(this).attr('data-num');
					let mem_name = $(this).attr('id');
					let modifyUI = '';
					let isChecked = $(this).attr('checked');
					$('#checked_form').show();
					
					if(isChecked){ //이미 체크되어있으면 체크 해제
						isChecked = $(this).attr('checked', false);
						//폼에서도 삭제함
						$('#' + mem_num).remove();
						$('.' + mem_num).remove();
						$('#li' + mem_num).remove();
						$('#' + mem_num + 'jsp').remove();
						$('#li_' + mem_num).css('background-color','#FFF');
						
						if($('.checked_ul *').length == 0){//체크된 멤버가 없으면 폼 숨김
							$('.checked_ul').empty();
							$('#checked_form').hide();
						}
					}else{ //체크 안되어 있으면 체크하기
						isChecked = $(this).attr("checked", true);
						//한명이라도 체크되면 div폼 노출
						$('#checked_form').show();
						$('#li_' + mem_num).css('background-color','#D6FFFF');
						modifyUI += '<input type="hidden" name="members" value="'+ mem_num +'" id="'+ mem_num + '">';
						modifyUI += '<input type="hidden" name="mem_names" value="'+ mem_name + '" class="' + mem_num + '">';
						modifyUI += '<li class="name_li" id="li'+ mem_num + '">' + mem_name + '<span close-num="' + mem_num + '" close-name="' + mem_name + '" class="close">X</span></li>';
						
						//체크된 멤버 노출
						$('.checked_ul').append(modifyUI);
						
					}
				}); //end of document(checkedMember)
				
				//X버튼 누르면 삭제
				$(document).on('click', '.close', function(){
					let mem_num = $(this).attr('close-num');
					let mem_name = $(this).attr('close-name');
					
					$('#'+mem_name).removeAttr('checked');
					$('#'+mem_name).empty();
					//폼에서도 삭제함
					$('#' + mem_num).remove();
					$('.' + mem_num).remove();
					$('#li' + mem_num).remove();
					$('#' + mem_num + 'jsp').remove();
					$('#li_' + mem_num).css('background-color','#FFF');
				});
				
				
				//취소버튼 클릭 시 멤버 리스트 폼 숨기고 reset
				$('.mem_reset').click(function(){
					$('#searchChatroom').hide();
					$('.checked_ul').empty();
					$('#checked_form').hide();
					$('#member_list').empty();
				});
				
			}, //end of success()
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	} //end of selectList()
	
	$(document).on('keyup','#mem_keyword',function(){
		selectList();
	});
	
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
				let chatroom_num = param.chatroom_num;
				//채팅방 리스트에도 추가
				$('.chatroomMain').empty();
			//	$('#chatroomList').empty();
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
	
	function connect() {
         wsocket = new WebSocket("ws://localhost:8080/chat-ws.do");
         wsocket.onopen = function(evt) {
            wsocket.send("msg");
         };
         //서버로부터 메시지를 받으면 호출되는 함수 지정
         wsocket.onmessage = function(evt) {
            let data = evt.data;
			list();
			if(data.substring(0,4) == 'msg:'){
				console.log(data.substring(4));
				createChat(data.substring(4));
			}
            
            //setTimeout(() => {
               
            //}, 10);
         };
         wsocket.onclose = function(evt) {
            //소켓이 종료된 후 부과적인 작업이 있을 경우 명시
            console.log('chat close');
            alert('채팅이 종료되었습니다!');
         };         
      }
      connect();
	
	
});






