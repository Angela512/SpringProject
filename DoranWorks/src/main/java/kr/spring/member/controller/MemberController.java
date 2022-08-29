package kr.spring.member.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//================회원로그인============================//
	//로그인 폼
	@GetMapping("/member/login.do")
	public String formLogin() {
		return "memberLogin";
	}
	
	//로그인 폼에서 전송된 데이터 처리
	@PostMapping("/member/login.do")
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result,HttpSession session) {
		logger.debug("<<회원 로그인>> : "+memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		//id와 pw 필드만 체크
		if(result.hasFieldErrors("mem_id") || result.hasFieldErrors("mem_pw")) {
			return formLogin();
		}
		
		//로그인 체크
		MemberVO member = null;
		try {
			member=memberService.selectCheckMember(memberVO.getMem_id());
			
			boolean check=false;
			System.out.println("member : " + member);
			if(member!=null) {
				//비밀번호 일치 여부 체크
				System.out.println("널 : " + memberVO.getMem_pw());
				check=member.isCheckedPasswd(memberVO.getMem_pw());
			}
			if(check) {
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				
				logger.debug("<<인증 성공>>");
				logger.debug("<<id>> : "+member.getMem_id());
				
				return "redirect:/main/main.do";
			}
			
			//인증 실패
			throw new AuthCheckException();
		}catch(AuthCheckException e){
			//인증 실패로 로그인 폼 호출
			if(member!=null && member.getAuth()==0) {
				//정지회원 메시지 표시
				result.reject("noAuthority");
			}else {
				result.reject("invalidIdOrPassword");
			}
			
			logger.debug("<<인증 실패>>");
			
			return formLogin();
		}
	}
	
	//==================회원 로그아웃=======================//
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		//로그아웃
		session.invalidate();
		
		return "redirect:/main/main.do";
	}
}
