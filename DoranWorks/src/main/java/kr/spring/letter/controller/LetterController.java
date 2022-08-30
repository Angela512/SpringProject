package kr.spring.letter.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.letter.service.LetterService;
import kr.spring.letter.vo.ReceiveVO;
import kr.spring.letter.vo.SendVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class LetterController {
	private static final Logger logger = LoggerFactory.getLogger(LetterController.class);
	
	@Autowired
	private LetterService letterService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public SendVO initCommand1() {
		return new SendVO();
	}

	@ModelAttribute
	public ReceiveVO initCommand2() {
		return new ReceiveVO();
	}
	
	//===================쪽지 쓰기==========================//
	//쓰기 폼
	@GetMapping("/letter/write.do")
	public String form() {
		return "letterWrite";
	}
	
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/letter/write.do")
	public String submit(SendVO sendVO,HttpServletRequest request,HttpSession session,Model model) {
		logger.debug("<<쪽지 쓰기>> : "+sendVO);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		sendVO.setSend_sender_num(user.getMem_num());
		sendVO.setSend_sender_id(user.getMem_id());
		
		
		sendVO.setRnames(sendVO.getSend_receiver_id().split(", "));
		
		
		//sendVO.setSend_receiver_num(letterService.selectMem_num(sendVO.getSend_receiver_id()));
		
		System.out.println("쓰기 : "+sendVO);
		//쪽지쓰기
		letterService.insertSend(sendVO);
		
		//View에서 표시할 메시지
		model.addAttribute("message","쪽지를 성공적으로 보냈습니다.");
		model.addAttribute("url",request.getContextPath()+"/letter/main.do");
		return "common/resultView";
	}
	
	//======================쪽지 목록====================//
	@RequestMapping("/letter/main.do")
	public ModelAndView process() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("letterList");
		
		return mav;
	}
}
