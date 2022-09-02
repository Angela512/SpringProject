package kr.spring.messanger.service;

import java.util.List;
import java.util.Map;

import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;

public interface MessangerService {
	public void insertChatroom(ChatroomVO chatroom);
	public List<ChatroomVO> selectChatroomList(Map<String,Object> map);
	public int selectChatroomRowCount(Map<String, Object> map);
	public List<MessangerVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	public void insertMessage(MessangerVO messanger);
	public int selectCheckedMemberCount(Map<String,Object> map);
	public List<ChatroomVO> selectCheckedMemberList(Map<String,Object> map);
}
