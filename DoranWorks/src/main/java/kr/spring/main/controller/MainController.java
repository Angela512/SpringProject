package kr.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.member.vo.MemberVO;



	
@Controller
public class MainController {
	
	//자바빈(VO) 초기화
		@ModelAttribute
		public MemberVO initCommand() {
			return new MemberVO();
		}
		
		
	@RequestMapping("/")
	public String page() {
		return "redirect:/member/main.do";
	}
	
	@RequestMapping("/member/main.do")
	public String main() {
		       //타일스 설정 식별자
		return "memberLogin";
	}
	
	
	
	
	
	
}




