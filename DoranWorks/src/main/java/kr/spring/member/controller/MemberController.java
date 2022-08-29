package kr.spring.member.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.FileUtil;

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
	
	//=========회원가입==========
	//회원등록 폼 호출
	@GetMapping("/member/registerUser.do")
	public String form() {
		return "memberRegister";
	}
	
	//회원가입 데이터 전송
	//회원가입 폼에서 전송된 데이터 처리
	@PostMapping("/member/registerUser.do")							//jsp에 데이터 셋팅 위해 model 추가
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model) {
		//로그 처리
		logger.debug("<<회원가입>> : " + memberVO);

		//유효성 체크 결과 오류 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}

		memberService.insertMember(memberVO);

		//속성명		속성값
		model.addAttribute("accessMsg", "회원가입 완료");	

		return "common/notice"; 
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
	
	//==========My Page==========
	@RequestMapping("/member/myPage.do")
	public String myPage(HttpSession session, Model model) {
		//로그인된 상태에서 myPage접근해야하니까 session에 저장된 user정보 읽어오기
		MemberVO user = (MemberVO)session.getAttribute("user");

		//한 건의 레코드 읽기
		MemberVO member = memberService.selectMember(user.getMem_num());

		logger.debug("<<회원 상세정보>> : " + member);

		//request에서 참조할 수 있게 저장해줌
		model.addAttribute("member", member);

		return "memberView"; //tiles 식별자
	}
	
	//==========회원정보 수정==========
	//수정폼
	@GetMapping("/member/update.do")
	public String formUpdate(HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");

		MemberVO memberVO = memberService.selectMember(user.getMem_num());

		model.addAttribute("memberVO", memberVO);

		return "memberModify"; //tiles 설정 이름
	}

	// 수정폼에서 전송된 데이터 처리
	@PostMapping("/member/update.do")
	public String submitUpdate(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		logger.debug("<<회원정보수정 처리>> : " +  memberVO);

		//유효성 체크 결과 오류 있으면 폼 호출
		if(result.hasErrors()) {
			return "memberModify";
		}
		//전송되지 않은 회원번호를 세션에서 추출
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());

		//회원정보 수정
		memberService.updateMember(memberVO);

		return "redirect:/member/myPage.do";
	}

	//프로필 사진 출력(로그인 전용)
	@RequestMapping("/member/photoView.do")
	public ModelAndView getProfile(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		MemberVO user = (MemberVO)session.getAttribute("user");

		if(user == null) {//로그인이 안 된 경우
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			mav.addObject("imageFile", readbyte);
			mav.addObject("filename", "face.png");
			mav.setViewName("imageView"); //tiles 식별자 명
		}else {//로그인이 된 경우
			MemberVO memberVO = memberService.selectMember(user.getMem_num());
			viewProfile(memberVO, request, mav);
		}

		return mav;
	}

	//프로필 사진 출력(회원번호 지정)
	@RequestMapping("/member/viewProfile.do")
	public ModelAndView getProfileByMem_num(@RequestParam int mem_num, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		MemberVO memberVO = memberService.selectMember(mem_num);

		viewProfile(memberVO, request, mav);

		return mav;
	}

	//프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, ModelAndView mav) {
		if(memberVO.getMem_photo_name() == null) {
			//업로드한 프사 없을 경우					webapp아래 경로를 절대경로로 읽어옴
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			mav.addObject("imageFile", readbyte);
			mav.addObject("filename", "face.png");
		}else {
			//업로드한 프사 있음
			mav.addObject("imageFile", memberVO.getMem_photo());
			mav.addObject("filename", memberVO.getMem_photo_name());
		}
		//뷰 이름 지정
		mav.setViewName("imageView");
	}
	
	
	
	
	
	
	
}
