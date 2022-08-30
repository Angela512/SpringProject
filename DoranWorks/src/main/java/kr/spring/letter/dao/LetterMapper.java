package kr.spring.letter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.letter.vo.SendVO;

@Mapper
public interface LetterMapper {
	//쪽지 쓰기
	
	@Select("SELECT mem_num FROM member WHERE mem_id=#{mem_id}")
	public int selectMem_num(String mem_id);
	public void insertSend(SendVO send);
	public void insertReceive(SendVO send);
	
	
}
