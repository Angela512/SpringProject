package kr.spring.notice.service;

import java.util.List;
import java.util.Map;

import kr.spring.notice.vo.NoticeReplyVO;
import kr.spring.notice.vo.NoticeVO;

public interface NoticeService {
	
	//공지사항 목록 카운트
	public int selectRowCount(Map<String,Object> map);
	//공지사항 목록 리스트
	public List<NoticeVO> selectList(Map<String, Object> map);
		
	//공지사항 글쓰기
	public void insertNotice(NoticeVO notice);
	
	//공지사항 상세페이지
	public NoticeVO selectNotice(Integer notice_num);
	
	//공지사항 글수정
	public void updateNotice(NoticeVO notice);
	
	//글 수정폼 파일 삭제
	public void deleteFile(Map<String, Object> map);
	
	//공지사항 글 삭제
	public void deleteNotice(Integer notice_num);
	
	//=============================댓글=======================//
	//댓글 목록 카운트
	public int selectRowCountReply(Map<String,Object> map);
	//댓글 목록 리스트
	public List<NoticeReplyVO> selectListReply(Map<String, Object> map);
	//댓글등록
	public void insertReply(NoticeReplyVO noticeReply);
	//댓글정보 불러오기
	public NoticeReplyVO selectReply(Integer reply_num);
	//댓글수정
	public void updateReply(NoticeReplyVO noticeReply);
	//댓글삭제
	public void deleteReply(Integer reply_num);
	
}
