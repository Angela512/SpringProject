package kr.spring.letter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.letter.dao.LetterMapper;
import kr.spring.letter.vo.SendVO;

@Service
@Transactional
public class LetterServiceImpl implements LetterService{

	@Autowired
	private LetterMapper letterMapper;
	
	@Override
	public int selectMem_num(String mem_id) {
		return letterMapper.selectMem_num(mem_id);
	}

	@Override
	public void insertSend(SendVO send) {
		String[] rname = send.getRnames();
		String rrecnum="";
		SendVO sendVV = send;
		
		for(int i=0;i<rname.length;i++) {
			String recnum=String.valueOf(letterMapper.selectMem_num(rname[i]));
			if(i==rname.length-1)rrecnum+=recnum;
			else rrecnum+=(recnum+", ");
		}
		sendVV.setSend_receiver_num(rrecnum);
		
		letterMapper.insertSend(sendVV);
		
		
		for(int i=0;i<rname.length;i++) {
			String recname = rname[i];
			send.setSend_receiver_id(recname);
			
			send.setSend_receiver_num(String.valueOf(letterMapper.selectMem_num(recname)));
			
			letterMapper.insertReceive(send);
		}
		
	}

}
