package kr.spring.messanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.messanger.dao.MessangerMapper;
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

}
