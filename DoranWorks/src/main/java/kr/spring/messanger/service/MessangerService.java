package kr.spring.messanger.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.vo.ChatmemVO;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;

public interface MessangerService {
	public void insertChatroom(ChatroomVO chatroomVO, String user_name);
	public List<ChatmemVO> selectChatmem(Integer chatroom_num);
	public List<ChatmemVO> selectChatroomList(Integer mem_num);
	public List<ChatmemVO> selectChatmemCount(Map<String,Object> map);
	public void insertMessage(MessangerVO messanger);
	public int selectCheckedMemberCount(Map<String,Object> map);
	public List<ChatroomVO> selectCheckedMemberList(Map<String,Object> map);
	public List<MessangerVO> selectMsgList(Integer mem_num, Integer chatroom_num);
	public List<Integer> selectMemberList(MessangerVO messanger);
}
