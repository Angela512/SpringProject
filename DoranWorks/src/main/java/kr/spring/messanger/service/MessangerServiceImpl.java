package kr.spring.messanger.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.dao.MessangerMapper;
import kr.spring.messanger.vo.ChatmemVO;
import kr.spring.messanger.vo.ChatreadVO;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;

@Service 
@Transactional 
public class MessangerServiceImpl implements MessangerService{
	@Autowired 
	public MessangerMapper msgMapper;
	
	@Override
	public void insertMessage(MessangerVO messanger) {
		messanger.setMsg_sendtime(msgMapper.selectMsgSendtime());
		messanger.setMsg_num(msgMapper.selectMsg_num()); //msg_num 시퀀스 생성
		msgMapper.insertMessage(messanger); 
		
		int msg_num = messanger.getMsg_num(); 
		int chatroom_num = messanger.getChatroom_num();
		int user_num = messanger.getMem_num();
		
		//해당 채팅방에 있는 멤버들의 회원번호들 읽어옴
		List<Integer> members = msgMapper.selectMsgMem_num(chatroom_num);
		
		ChatreadVO chatreadVO = new ChatreadVO();
		
		for(Integer mem_num : members) {
			
			chatreadVO.setChatread_num(msgMapper.selectChatread_num()); //chatread_num 시퀀스 생성
			chatreadVO.setMsg_num(msg_num);
			chatreadVO.setMem_num(mem_num);
			chatreadVO.setChatroom_num(chatroom_num);

			msgMapper.insertChatread(chatreadVO); 
		}
	}
	
	@Override
	public void insertChatroom(ChatroomVO chatroomVO) {
		//채팅방 이름은 멤버들 이름으로 생성(나중에 변경 가능)
		String arr_name = ""; //이름을 쭉 일자로 넣을거임
		int len = chatroomVO.getMem_names().length; //나 빼고임
		int i = 1;
		for(String mem_name : chatroomVO.getMem_names()) {
			arr_name += mem_name;
			if(len != i) {
				arr_name += ", ";
			}
			System.out.println("len : " + len);
			i++;
		}
		
		chatroomVO.setChatroom_num(msgMapper.selectChatroom_num());
		chatroomVO.setChatroom_name(arr_name);
		msgMapper.insertChatroom(chatroomVO);
		
		ChatmemVO chatmemVO = new ChatmemVO();
		
		for(int mem_num : chatroomVO.getMembers()) {
			chatmemVO.setChatroom_num(chatroomVO.getChatroom_num());
			chatmemVO.setMem_num(mem_num);
			
			msgMapper.insertChatmem(chatmemVO);
		}
	}
	
	@Override
	public List<ChatmemVO> selectChatmem(Integer chatroom_num) {
		return msgMapper.selectChatmem(chatroom_num);
	}

	@Override
	public List<ChatmemVO> selectChatroomList(Integer mem_num) {
		return msgMapper.selectChatroomList(mem_num);
	}

	@Override
	public int selectCheckedMemberCount(Map<String, Object> map) {
		return 0;
	}

	@Override
	public List<ChatroomVO> selectCheckedMemberList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<MessangerVO> selectMsgList(Integer mem_num, Integer chatroom_num) {
		//메시지 읽으면 삭제
		msgMapper.deleteChatread(mem_num, chatroom_num);
		return msgMapper.selectMsgList(chatroom_num);
	}

	@Override
	public List<ChatmemVO> selectChatmemCount(Map<String,Object> map) {
		List<ChatmemVO> list = msgMapper.selectChatmemCount(map);
		//루프를 돌면서
		
		  for(ChatmemVO chat : list) { //채팅방 별 가장 최신 메시지 가져오기(채팅방 목록에서 보여주기 용)
		  MessangerVO recentMsg = msgMapper.selectRecentMsg(chat.getChatroom_num());
		  chat.setMessangerVO(recentMsg);
		  }
		 
		return list;
		
	}

	@Override
	public List<Integer> selectMemberList(MessangerVO messanger) {
		return msgMapper.selectMemberList(messanger);
	}

}
