package kr.spring.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.notice.vo.NoticeReplyVO;
import kr.spring.notice.vo.NoticeVO;

@Mapper
public interface NoticeMapper {

	//공지사항 목록 카운트
	public int selectRowCount(Map<String,Object> map);
	//공지사항 목록 리스트
	public List<NoticeVO> selectList(Map<String, Object> map);
	
	//공지사항 글쓰기
	@Insert("INSERT INTO notice (notice_num,notice_title,notice_content,notice_uploadfile1,notice_uploadfile2,notice_filename1,notice_filename2,notice_head,mem_num,notice_replyagree) VALUES (notice_seq.nextval,#{notice_title},#{notice_content},#{notice_uploadfile1},#{notice_uploadfile2},#{notice_filename1},#{notice_filename2},#{notice_head},#{mem_num},#{notice_replyagree})")
	public void insertNotice(NoticeVO notice);
	
	//공지사항 상세페이지
	@Select("SELECT * FROM (SELECT notice_num,mem_num,notice_title,notice_date,notice_content,notice_uploadfile1,notice_uploadfile2,notice_filename1,notice_filename2,notice_head,notice_replyagree,LAG(notice_num,1) OVER(ORDER BY notice_num) next_num,LEAD(notice_num,1) OVER(ORDER BY notice_num) prev_num FROM notice) n JOIN member m ON n.mem_num=m.mem_num JOIN member_detail d ON d.mem_num=m.mem_num WHERE n.notice_num=#{notice_num}")
	public NoticeVO selectNotice(Integer notice_num);
	
	//공지사항 글수정
	public void updateNotice(NoticeVO notice);
	//글 수정폼 파일 삭제
	public void deleteFile(Map<String, Object> map);
	//공지사항 글 삭제
	@Delete("DELETE FROM notice WHERE notice_num=#{notice_num}")
	public void deleteNotice(Integer notice_num);
	
	//========================댓글========================//
	//댓글 목록 카운트
	@Select("SELECT COUNT(*) FROM notice_reply r JOIN member m ON r.mem_num=m.mem_num JOIN member_detail d ON m.mem_num=d.mem_num WHERE notice_num=#{notice_num}")
	public int selectRowCountReply(Map<String,Object> map);
	//댓글 목록 리스트
	public List<NoticeReplyVO> selectListReply(Map<String, Object> map);
	//댓글등록
	@Insert("INSERT INTO notice_reply (reply_num,mem_num,notice_num,reply_content) VALUES (notice_reply_seq.nextval,#{mem_num},#{notice_num},#{reply_content})")
	public void insertReply(NoticeReplyVO noticeReply);
	//댓글정보 불러오기
	@Select("SELECT * FROM notice_reply WHERE reply_num=#{reply_num}")
	public NoticeReplyVO selectReply(Integer reply_num);
	//댓글수정
	@Update("UPDATE notice_reply SET reply_content=#{reply_content},reply_mdate=SYSDATE WHERE reply_num=#{reply_num}")
	public void updateReply(NoticeReplyVO noticeReply);
	//댓글삭제
	@Delete("DELETE FROM notice_reply WHERE reply_num=#{reply_num}")
	public void deleteReply(Integer reply_num);
	
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM notice_reply WHERE notice_num=#{notice_num}")
	public void deleteReplyByNoticeNum(Integer notice_num);
}
