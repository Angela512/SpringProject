package kr.spring.letter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.letter.vo.LetterReadVO;
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
	@Update("UPDATE letter_send SET lt_important=#{important} WHERE lt_num=#{lt_num}")
	public void updateSendImportant(Map<String, Object> map);
	
	//중요 업데이트 보낸테이블 기본정보 가져오기
	@Select("SELECT * FROM letter_send WHERE lt_num=#{lt_num}")
	public LetterVO selectSendLetter(int lt_num);
	
	//받은쪽지함 정보 가져오기
	@Select("SELECT * FROM letter_receive WHERE snum=#{lt_num} AND lt_receiver_num=#{lt_receiver_num}")
	public LetterVO selectRecLetter(Map<String, Object> map);
	//받은쪽지함 중요 업데이트
	@Update("UPDATE letter_receive SET lt_important=#{important} WHERE snum=#{lt_num} AND lt_receiver_num=#{lt_receiver_num}")
	public void updateReceiveImportant(Map<String, Object> map);
	
	//보낸쪽지함 리스트 읽음처리
	@Update("UPDATE letter_send SET lt_read=#{lt_read} WHERE lt_num=#{lt_num}")
	public void updateSendRead(LetterReadVO readVO);
	//받은쪽지함 리스트 읽음처리
	@Update("UPDATE letter_receive SET lt_read=#{lt_read} WHERE snum=#{lt_num} AND lt_receiver_num=#{mem_num}")
	public void updateReceiveRead(LetterReadVO readVO);
	
	//보낸쪽지함 리스트 삭제 처리
	@Update("UPDATE letter_send SET lt_delete=1 WHERE lt_num=#{lt_num}")
	public void deleteSendDelete(int lt_num);
	//받은쪽지함 리스트 삭제 처리
	@Update("UPDATE letter_receive SET lt_delete=1 WHERE snum=#{lt_num} AND lt_receiver_num=#{mem_num}")
	public void deleteReceiveDelete(LetterReadVO readVO);
	
	
	//쪽지 상세
	public LetterVO selectLetter(int lt_num);
	public List<LetterVO> selectName(String[] rids);
	
}
