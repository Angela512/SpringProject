package kr.spring.messanger.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.messanger.dao.MessangerMapper;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;

@Service 
@Transactional 
public class MessangerServiceImpl implements MessangerService{
	@Autowired //주입받아야하니까
	public MessangerMapper msgMapper;
	
	@Override
	public void insertMessage(MessangerVO messanger) {
		msgMapper.insertMessage(messanger);
		
	}

	@Override
	public List<MessangerVO> selectList(Map<String, Object> map) {
		return msgMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return msgMapper.selectRowCount(map);
	}

	@Override
	public void insertChatroom(ChatroomVO chatroom) {
		msgMapper.insertChatroom(chatroom);
		
	}

	@Override
	public List<ChatroomVO> selectChatroomList(Map<String, Object> map) {
		return msgMapper.selectChatroomList(map);
	}

	@Override
	public int selectChatroomRowCount(Map<String, Object> map) {
		return msgMapper.selectChatroomRowCount(map);
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

}
