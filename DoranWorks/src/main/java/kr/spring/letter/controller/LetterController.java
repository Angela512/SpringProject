package kr.spring.letter.controller;

import java.util.ArrayList;
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

import kr.spring.letter.service.LetterService;
import kr.spring.letter.vo.LetterVO;
import kr.spring.letter.vo.NamesVO;
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
	
	public List<NamesVO> getListnames(List<LetterVO> letter){
		
		List<NamesVO> names = new ArrayList<NamesVO>();
		
		for(int i=0;i<letter.size();i++) {
			NamesVO name = new NamesVO();
			
			name.setRec_name(letterService.selectMem_vo(letter.get(i).getLt_receiver_id()).getMem_name());
			name.setSen_name(letterService.selectMem_vo(letter.get(i).getLt_sender_id()).getMem_name());
			
			names.add(name);
		}
		return names;
	}
	
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
		List<NamesVO> list2 = null;
		PagingUtil page = null;
		
		if(letter_type==0) {
			//글의 총 개수
			count = letterService.selectAllRowCount(map);
			
			logger.debug("<<count>> : "+count );
			
			//페이지 처리
			page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"main.do");
			
			
			
			if(count>0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list=letterService.selectAllList(map);
				
				list2 = getListnames(list);
			}
			
		}else if(letter_type==1) {
			//글의 총 개수
			count = letterService.selectRecRowCount(map);
			
			logger.debug("<<count>> : "+count);
			
			//페이지 처리
			page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount,pageCount,"main.do");
			
			if(count>0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list=letterService.selectRecList(map);
			}
		}else if(letter_type==2) {
			
		}else if(letter_type==3) {
			
		}else if(letter_type==4) {
			
		}else if(letter_type==5) {
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("letterList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("list2",list2);
		mav.addObject("page",page.getPage());
		
		
		return mav;
	}
	
	//====================쪽지 상세=======================//
	@RequestMapping("/letter/detail.do")
	public ModelAndView detail(int lt_num,@RequestParam(defaultValue="0") int letter_type,HttpSession session) {
		logger.debug("<<lt_num>> : "+lt_num);
		
		LetterVO letter = letterService.selectLetter(lt_num);
		
		letter.setLt_title(StringUtil.useNoHtml(letter.getLt_title()));
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mem_num", user.getMem_num());
		map.put("lt_num", lt_num);
		
		NextPrevVO np = null;
		if(letter_type==0)
			np = letterService.selectAllNP(map);
		else if(letter_type==1)
			np = letterService.selectRecNP(map);
		
		System.out.println("이전다음 : "+np);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("letterView");
		mav.addObject("letter",letter);
		mav.addObject("np",np);
		
		return mav;
	}
	
}
