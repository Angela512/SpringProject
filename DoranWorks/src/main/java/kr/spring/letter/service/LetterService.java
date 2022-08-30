package kr.spring.letter.service;

import kr.spring.letter.vo.SendVO;

public interface LetterService {
	
	public int selectMem_num(String mem_id);
	public void insertSend(SendVO send);
}
