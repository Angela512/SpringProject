package kr.spring.messanger.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.vo.ChatmemVO;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;

public interface MessangerService {
	public void insertChatroom(ChatroomVO chatroomVO);
	public List<ChatmemVO> selectChatmem(Integer chatroom_num);
	public List<ChatroomVO> selectChatroomList(Map<String,Object> map);
	public int selectChatroomRowCount(Map<String, Object> map);
	public void insertMessage(MessangerVO messanger);
	public int selectCheckedMemberCount(Map<String,Object> map);
	public List<ChatroomVO> selectCheckedMemberList(Map<String,Object> map);
}
