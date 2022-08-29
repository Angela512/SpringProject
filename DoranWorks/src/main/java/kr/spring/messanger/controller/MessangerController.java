package kr.spring.messanger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.messanger.service.MessangerService;
import kr.spring.messanger.vo.MessangerVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.controller.MessangerController;

@Controller
public class MessangerController {
	private static final Logger logger = LoggerFactory.getLogger(MessangerController.class);
	
	@Autowired
	private MessangerService messangerService;
	
	//자바빈 초기화
	@ModelAttribute 
	public MessangerVO initCommand() {
		return new MessangerVO();
	}
	
	//등록 폼
	@GetMapping("/messanger/write.do")
	public String form() {
		return "msgWrite"; //tiles
	}
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/messanger/write.do")
	public String submit(@Valid MessangerVO messangerVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		logger.debug("<<게시판 글 저장>> : " + messangerVO);

		//유효성 검사 결과 오류 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}

		//세션에서 유저 정보 뽑아냄
		MemberVO user = (MemberVO)session.getAttribute("user");
		//회원번호 셋팅
		messangerVO.setMem_num(user.getMem_num());
		

		//글쓰기
		messangerService.insertMessage(messangerVO);

		//View에 표시할 메시지 지정(식별자, 내용)
		model.addAttribute("message", "글 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/messanger/main.do"); //작성이 완료되면 목록으로 이동

		//얘는 jsp
		return "common/resultView"; //등록이 되면 자바스크립트를 활용하여 등록됐다고 띄울거임
	}
	
	
}










