package kr.spring.notice.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.vo.MemberVO;
import kr.spring.notice.service.NoticeService;
import kr.spring.notice.vo.NoticeReplyVO;
import kr.spring.util.PagingUtil;

@Controller
public class NoticeAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeAjaxController.class);
	
	private int rowCount=5;
	private int pageCount = 5;
	
	@Autowired
	private NoticeService noticeService;
	
	//====================공지 업로드 파일 삭제===================//
	@RequestMapping("/notice/deleteFile.do")
	@ResponseBody
	public Map<String, String> processFile(@RequestParam int notice_num,int file_type,HttpSession session){
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("notice_num", notice_num);
			
			if(file_type==1) {
				map.put("file_type", 1);
			}else if(file_type==2) {
				map.put("file_type", 2);
			}
			
			noticeService.deleteFile(map);
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	
	//======================댓글 등록=============================//
	@RequestMapping("/notice/writeReply.do")
	@ResponseBody
	public Map<String, String> writeReply(NoticeReplyVO noticeReplyVO,HttpSession session){
		logger.debug("<<댓글 등록>> : "+noticeReplyVO);
		
		Map<String, String> mapAjax=new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {//로그인 안 됨
			mapAjax.put("result", "logout");
		}else {//로그인 됨
			//회원번호 등록
			noticeReplyVO.setMem_num(user.getMem_num());
			
			//댓글 등록
			noticeService.insertReply(noticeReplyVO);
			mapAjax.put("result","success");
		}
		
		return mapAjax;
	}
	
	//====================댓글 목록==============================//
	@RequestMapping("/notice/listReply.do")
	@ResponseBody
	public Map<String, Object> getList(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
										@RequestParam int notice_num,HttpSession session){
		
		logger.debug("<<currentPage>> : "+currentPage);
		logger.debug("<<board_num>> : "+notice_num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notice_num", notice_num);
		
		//총 글의 개수
		int count = noticeService.selectRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		List<NoticeReplyVO> list = null;
		if(count>0) {
			list=noticeService.selectListReply(map);
		}else {
			list = Collections.emptyList();
		}
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		
		//로그인 한 회원정보 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user!=null) {
			mapAjax.put("user_num", user.getMem_num());
		}
		
		return mapAjax;
		
	}
	
	//===========================댓글 수정=================================//
	@RequestMapping("/notice/updateReply.do")
	@ResponseBody
	public Map<String, String> modifyReply(NoticeReplyVO noticeReplyVO, HttpSession session){
		logger.debug("<<댓글 수정>> : "+noticeReplyVO);
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		NoticeReplyVO db_reply = noticeService.selectReply(noticeReplyVO.getReply_num());
		
		if(user==null) {//로그인이 되지 않는 경우
			mapAjax.put("result", "logout");
		}else if(user!=null && user.getMem_num()==db_reply.getMem_num()) {
			//로그인 회원번호와 작성자 회원번호 일치
			
			//댓글 수정
			noticeService.updateReply(noticeReplyVO);
			mapAjax.put("result", "success");
		}else {
			//로그인 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
	
		return mapAjax;
	}
	
	//=============================댓글 삭제===============================//
	@RequestMapping("/notice/deleteReply.do")
	@ResponseBody
	public Map<String, String> deleteReply(int reply_num,HttpSession session){
		logger.debug("<<reply_num>> : "+reply_num);
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		NoticeReplyVO db_reply = noticeService.selectReply(reply_num);
		
		if(user==null) {
			//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else if(user!=null && user.getMem_num()==db_reply.getMem_num()) {
			//로그인이 되어 있고 로그인한 회원번호와 작성자 회원번호 일치
			
			//댓글 삭제
			noticeService.deleteReply(reply_num);
			
			mapAjax.put("result", "success");
		}else {
			//로그인한 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		
		return mapAjax;
		
	}
	
}
