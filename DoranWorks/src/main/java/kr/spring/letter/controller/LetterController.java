package kr.spring.letter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.letter.dao.LetterMapper;
import kr.spring.letter.service.LetterService;
import kr.spring.letter.vo.LetterReadVO;
import kr.spring.letter.vo.LetterVO;
import kr.spring.letter.vo.NextPrevVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class LetterController {
	private static final Logger logger = LoggerFactory.getLogger(LetterController.class);
	
	private int rowCount = 10;
	private int pageCount = 5;
	
	@Autowired
	private LetterService letterService;
	
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public LetterVO initCommand() {
		return new LetterVO();
	}

	
	//===================쪽지 쓰기==========================//
	//쓰기 폼
	@GetMapping("/letter/write.do")
	public String form() {
		return "letterWrite";
	}
	
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/letter/write.do")
	public String submit(LetterVO letterVO,HttpServletRequest request,HttpSession session,Model model) {
		logger.debug("<<쪽지 쓰기>> : "+letterVO);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		letterVO.setLt_sender_num(user.getMem_num());
		letterVO.setLt_sender_id(user.getMem_id());
		
		letterVO.setRids(letterVO.getLt_receiver_id().split(","));
		letterVO.setRids2(letterVO.getLt_reference_id().split(","));
		
		
		
		//sendVO.setSend_receiver_num(letterService.selectMem_num(sendVO.getSend_receiver_id()));
		
		System.out.println("쓰기 : "+letterVO);
		//쪽지쓰기
		letterService.insertSend(letterVO);
		
		//View에서 표시할 메시지
		model.addAttribute("message","쪽지를 성공적으로 보냈습니다.");
		model.addAttribute("url",request.getContextPath()+"/letter/main.do");
		return "common/resultView";
	}
	
	//======================쪽지 목록====================//
	@RequestMapping("/letter/main.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="keyfield",defaultValue="") String keyfield,
								@RequestParam(value="keyword",defaultValue="") String keyword,
								@RequestParam(defaultValue="0") int letter_type, HttpSession session) {
		
		logger.debug("<<letter_type>> : "+letter_type);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("mem_num", user.getMem_num());
		
		int count=0;
		List<LetterVO> list = null;
		PagingUtil page = null;
		
		if(letter_type==0) {//전체쪽지함
			//글의 총 개수
			count = letterService.selectAllRowCount(map);
			
			logger.debug("<<count>> : "+count );
			
			//페이지 처리
			page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"main.do");
			
			
			
			if(count>0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list=letterService.selectAllList(map);
				
			}
			
		}else if(letter_type==1) {//받은쪽지함
			//글의 총 개수
			count = letterService.selectRecRowCount(map);
			
			logger.debug("<<count>> : "+count);
			
			//페이지 처리
			page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount,pageCount,"main.do","letter_type="+letter_type);
			
			if(count>0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list=letterService.selectRecList(map);
			}
		}else if(letter_type==2) {//보낸쪽지함
			//글의 총 개수
			count = letterService.selectSendRowCount(map);
			
			logger.debug("<<count>> : "+count);
			
			//페이지 처리
			page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount,pageCount,"main.do","letter_type="+letter_type);
			
			if(count>0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list=letterService.selectSendList(map);
				
			}
			
		}else if(letter_type==3) {//내게쓴쪽지함
			map.put("lt_receiver_num", String.valueOf(user.getMem_num()));
			//글의 총 개수
			count = letterService.selectMyRowCount(map);
			
			logger.debug("<<count>> : "+count);
			
			//페이지 처리
			page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount,pageCount,"main.do","letter_type="+letter_type);
			
			if(count>0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list=letterService.selectMyList(map);
				
			}
		}else if(letter_type==4) {//중요쪽지함
			//글의 총 개수
			count = letterService.selectImportantRowCount(map);
			
			logger.debug("<<count>> : "+count);
			
			//페이지 처리
			page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"main.do","letter_type="+letter_type);
			
			if(count>0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list=letterService.selectImportantList(map);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("letterList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		
		
		return mav;
	}
	
	//====================쪽지 상세=======================//
	@RequestMapping("/letter/detail.do")
	public ModelAndView detail(int lt_num,@RequestParam(defaultValue="0") int letter_type,HttpSession session) {
		logger.debug("<<lt_num>> : "+lt_num);
		
		LetterVO letter = letterService.selectLetter(lt_num);
		
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		LetterReadVO readVO = new LetterReadVO();
		readVO.setLt_num(lt_num);
		readVO.setMem_num(user.getMem_num());
		readVO.setLt_read(1);
		if(letter.getLt_sender_num()==user.getMem_num()) {
			letterService.updateSendRead(readVO);
			
			if(!letter.getLt_receiver_num().contains(",") && letter.getLt_sender_num()==Integer.parseInt(letter.getLt_receiver_num())) {
				letterService.updateReceiveRead(readVO);
			}
			
		}else {
			letterService.updateReceiveRead(readVO);
		}
		
		String[] rids = letter.getLt_receiver_id().split(",");
		List<LetterVO> receiverName= letterService.selectName(rids);
		String recname = "";
		for(int i=0;i<receiverName.size();i++) {
			if (i==receiverName.size()-1)
				recname+=receiverName.get(i).getMem_name()+"("+receiverName.get(i).getMem_id()+")";
			else
				recname+=receiverName.get(i).getMem_name()+"("+receiverName.get(i).getMem_id()+"),";
		}
		letter.setLt_receiver_id(recname);
		
		List<LetterVO> referenceName = null;
		String refname = "";
		if(letter.getLt_reference_id() != null) {
			rids = letter.getLt_reference_id().split(",");
			referenceName= letterService.selectName(rids);
			
			for(int i=0;i<referenceName.size();i++) {
				if(i==referenceName.size()-1)
					refname+=referenceName.get(i).getMem_name()+"("+referenceName.get(i).getMem_id()+")";
				else
					refname+=referenceName.get(i).getMem_name()+"("+referenceName.get(i).getMem_id()+"),";
			}
			letter.setLt_reference_id(refname);
		}
		
		letter.setLt_title(StringUtil.useNoHtml(letter.getLt_title()));
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_num", user.getMem_num());
		map.put("lt_num", lt_num);
		
		NextPrevVO np = null;
		if(letter_type==0)
			np = letterService.selectAllNP(map);
		else if(letter_type==1)
			np = letterService.selectRecNP(map);
		else if(letter_type==2)
			np = letterService.selectSendNP(map);
		else if(letter_type==3) {
			map.put("lt_receiver_num", String.valueOf(user.getMem_num()));
			np = letterService.selectMyNP(map);
		}else if(letter_type==4) {
			np = letterService.selectImportantNP(map);
		}
		
		System.out.println("이전다음 : "+np);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("letterView");
		mav.addObject("letter",letter);
		mav.addObject("np",np);
		
		return mav;
	}
	
	//==================상세페이지 글 삭제=============//
	@RequestMapping("/letter/detailDelete.do")
	public String submitDetailDelete(int lt_num,Model model,HttpServletRequest request,HttpSession session) {
		logger.debug("<<글삭제>> : "+lt_num);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		LetterReadVO readVO = new LetterReadVO();
		readVO.setLt_num(lt_num);
		readVO.setMem_num(user.getMem_num());
		LetterVO letter = letterService.selectSendLetter(lt_num);
		if(letter.getLt_sender_num()==readVO.getMem_num()) {//보낸쪽지함 삭제 경우
			if(letter.getLt_sender_id().equals(letter.getLt_receiver_id())) {//내게쓴쪽지일경우
				letterService.deleteSendDelete(lt_num);
				letterService.deleteReceiveDelete(readVO);
			}else 
				letterService.deleteSendDelete(lt_num);
		}else {//받은쪽지함 삭제 경우
			letterService.deleteReceiveDelete(readVO);
		}
		
		//View에 표시할 메시지
		model.addAttribute("message","글삭제 완료!!");
		model.addAttribute("url",request.getContextPath()+"/letter/main.do");
		
		return "common/resultView";
	}
	
	//=========================상세페이지 답장=======================//
	@RequestMapping("/letter/reply.do")
	public ModelAndView detailReply(int lt_num) {
		
		logger.debug("<<상세페이지 답장>> : "+lt_num);
		
		LetterVO letter = letterService.selectSendLetter(lt_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("letterReply");
		mav.addObject("letter",letter);
		
		return mav;
	}
	
	//=====================상세페이지 전달================================//
	@RequestMapping("/letter/forward.do")
	public ModelAndView detailForward(int lt_num) {
		
		logger.debug("<<상세페이지 전달>> : "+lt_num);
		
		LetterVO letter = letterService.selectSendLetter(lt_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("letterForward");
		mav.addObject("letter",letter);
		
		return mav;
	}
	
	//=================파일 다운로드==============================//
	@RequestMapping("/letter/file.do")
	public ModelAndView download(@RequestParam int lt_num,int file_type) {
		
		LetterVO letter = letterService.selectSendLetter(lt_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		
		if(file_type==1) {
			mav.addObject("downloadFile",letter.getLt_uploadfile1());
			mav.addObject("filename",letter.getLt_filename1());
		}else if(file_type==2) {
			mav.addObject("downloadFile",letter.getLt_uploadfile2());
			mav.addObject("filename",letter.getLt_filename2());
		}
		
		return mav;
	}
	
	//======================이미지 출력===========================//
	@RequestMapping("/letter/imageView.do")
	public ModelAndView viewImage(int lt_num,int image_type) {
		
		LetterVO letter = letterService.selectSendLetter(lt_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(image_type==1) {
			mav.addObject("imageFile",letter.getLt_uploadfile1());
			mav.addObject("filename",letter.getLt_filename1());
		}else if(image_type==2) {
			mav.addObject("imageFile",letter.getLt_uploadfile2());
			mav.addObject("filename",letter.getLt_filename2());
		}
		
		return mav;
	}
	
}
