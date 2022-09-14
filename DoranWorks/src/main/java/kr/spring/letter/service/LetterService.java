package kr.spring.letter.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	
	//보낸쪽지함
	public int selectSendRowCount(Map<String, Object>map);
	public List<LetterVO> selectSendList(Map<String, Object> map);
	//보낸 쪽지함 이전글 다음글
	public NextPrevVO selectSendNP(Map<String, Object> map);

	//내게쓴쪽지함
	public int selectMyRowCount(Map<String, Object>map);
	public List<LetterVO> selectMyList(Map<String, Object> map);
	//내게쓴 쪽지함 이전글 다음글
	public NextPrevVO selectMyNP(Map<String, Object> map);
	
	//중요쪽지함
	public int selectImportantRowCount(Map<String, Object>map);
	public List<LetterVO> selectImportantList(Map<String, Object> map);
	//중요 쪽지함 이전글 다음글
	public NextPrevVO selectImportantNP(Map<String, Object> map);
	
	//보낸쪽지함 중요 업데이트
	public void updateSendImportant(Map<String, Object> map);
	
	//받은쪽지함 정보 가져오기
	public LetterVO selectRecLetter(Map<String, Object> map);
	//받은쪽지함 중요 업데이트
	public void updateReceiveImportant(Map<String, Object> map);
	
	//상세페이지 기본정보
	public LetterVO selectLetter(int lt_num);
	//상세페이지 이름정보 가져오기
	public List<LetterVO> selectName(String[] rids);
}
