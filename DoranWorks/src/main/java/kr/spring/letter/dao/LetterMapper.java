package kr.spring.letter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.letter.vo.LetterVO;
import kr.spring.letter.vo.NextPrevVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface LetterMapper {
	//쪽지 쓰기
	
	@Select("SELECT send_seq.nextval FROM dual")
	public int sendNum();
	@Select("SELECT * FROM member m JOIN member_detail d USING(mem_num) WHERE mem_id=#{mem_id}")
	public MemberVO selectMem_vo(String mem_id);
	public void insertSend(LetterVO letter);
	public void insertReceive(LetterVO letter);
	
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
	
	
	//쪽지 상세
	@Select("SELECT * FROM letter_send WHERE lt_num=#{lt_num}")
	public LetterVO selectLetter(int lt_num);
	
	
	
}
