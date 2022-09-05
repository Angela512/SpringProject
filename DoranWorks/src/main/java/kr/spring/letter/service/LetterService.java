package kr.spring.letter.service;

import java.util.List;
import java.util.Map;

import kr.spring.letter.vo.LetterVO;
import kr.spring.letter.vo.NextPrevVO;
import kr.spring.member.vo.MemberVO;

public interface LetterService {
	
	public MemberVO selectMem_vo(String mem_id);
	public void insertSend(LetterVO letter);
	
	//전체 쪽지함
	public int selectAllRowCount(Map<String, Object> map);
	public List<LetterVO> selectAllList(Map<String, Object> map);
	//전체 쪽지함 이전글 다음글
	public NextPrevVO selectAllNP(Map<String, Object> map);
	
	//받은쪽지함
	public int selectRecRowCount(Map<String, Object>map);
	public List<LetterVO> selectRecList(Map<String, Object> map);
	//받은 쪽지함 이전글 다음글
	public NextPrevVO selectRecNP(Map<String, Object> map);

	
	public LetterVO selectLetter(int lt_num);
}
