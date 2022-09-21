package kr.spring.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.notice.dao.NoticeMapper;
import kr.spring.notice.vo.NoticeReplyVO;
import kr.spring.notice.vo.NoticeVO;

@Service
@Transactional
public class NoticeServiceImpl  implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;

	//공지사항 목록 카운트
	@Override
	public int selectRowCount(Map<String, Object> map) {
		return noticeMapper.selectRowCount(map);
	}

	//공지사항 목록 리스트
	@Override
	public List<NoticeVO> selectList(Map<String, Object> map) {
		return noticeMapper.selectList(map);
	}
	
	//공지사항 글쓰기
	@Override
	public void insertNotice(NoticeVO notice) {
		noticeMapper.insertNotice(notice);
	}

	//공지사항 상세페이지
	@Override
	public NoticeVO selectNotice(Integer notice_num) {
		return noticeMapper.selectNotice(notice_num);
	}

	//공지사항 글수정
	@Override
	public void updateNotice(NoticeVO notice) {
		noticeMapper.updateNotice(notice);
	}
	
	//글 수정폼 파일 삭제
	@Override
	public void deleteFile(Map<String, Object> map) {
		noticeMapper.deleteFile(map);
	}

	//공지사항 글삭제
	@Override
	public void deleteNotice(Integer notice_num) {
		//댓글이 존재하면 댓글을 우선 삭제
		noticeMapper.deleteReplyByNoticeNum(notice_num);
		//공지글 삭제
		noticeMapper.deleteNotice(notice_num);
	}

	
	//=======================댓글=========================//
	//댓글 목록 카운트
	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return noticeMapper.selectRowCountReply(map);
	}

	//댓글 목록 리스트
	@Override
	public List<NoticeReplyVO> selectListReply(Map<String, Object> map) {
		return noticeMapper.selectListReply(map);
	}
	
	//댓글 등록
	@Override
	public void insertReply(NoticeReplyVO noticeReply) {
		noticeMapper.insertReply(noticeReply);
	}

	//댓글정보 불러오기
	@Override
	public NoticeReplyVO selectReply(Integer reply_num) {
		return noticeMapper.selectReply(reply_num);
	}
		
	//댓글수정
	@Override
	public void updateReply(NoticeReplyVO noticeReply) {
		noticeMapper.updateReply(noticeReply);
	}

	//댓글 삭제
	@Override
	public void deleteReply(Integer reply_num) {
		noticeMapper.deleteReply(reply_num);
	}


	

	

	

	
}
