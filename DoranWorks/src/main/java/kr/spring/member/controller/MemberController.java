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
import kr.spring.util.CipherTemplate;
import kr.spring.util.FileUtil;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    private CipherTemplate cipherAES;
	
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
				check=member.isCheckedPasswd(cipherAES.encrypt(memberVO.getMem_pw()));
			}
			if(check) {
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				
				logger.debug("<<인증 성공>>");
				logger.debug("<<id>> : "+member.getMem_id());
				
				return "redirect:/notice/list.do";
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
		
		return "redirect:/member/main.do";
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
	
	
	//===========비밀번호 변경===========//
		//비밀번호 변경 폼
		@GetMapping("/member/changePassword.do")
		public String formChangePassword() {
			return "memberChangePassword";
		}
		
		//비밀번호 변경 폼에서 전송된 데이터 처리
		@PostMapping("/member/changePassword.do")
		public String submitChangePassword(
				@Valid MemberVO memberVO,
				BindingResult result,
				HttpSession session,
				Model model,
				HttpServletRequest request) {
			logger.debug("<<비밀번호 변경 처리>> : " + memberVO);
			
			//유효성 체크 결과 오류가 있으면 폼 호출
			//now_passwd,passwd만 체크
			if(result.hasFieldErrors("now_passwd") || 
					result.hasFieldErrors("mem_pw")) {
				return formChangePassword();
			}
			
			MemberVO user = 
				  (MemberVO)session.getAttribute("user");
			memberVO.setMem_num(user.getMem_num());
			
			//비밀번호 일치 여부 체크를 위해서 회원정보 호출
			MemberVO db_member = 
					memberService.selectMember(
							     memberVO.getMem_num());
			//폼에서 전송한 현재 비밀번호와 DB에서 받아온
			//현재 비밀번호 일치 여부 체크
			if(!db_member.getMem_pw().equals(
					cipherAES.encrypt(memberVO.getNow_passwd()))) {
				result.rejectValue(
						"now_passwd", "invalidPassword");
				return formChangePassword();
			}
			
			  //CipherTemplate을 이용한 암호화
		      memberVO.setMem_pw(cipherAES.encrypt(memberVO.getMem_pw()));
		      //비밀번호 변경
			memberService.updatePassword(memberVO);
			
			//View에 표시할 메시지
			model.addAttribute("message", "비밀번호변경 완료!");
			model.addAttribute("url", 
			  request.getContextPath()+"/member/myPage.do");
			
			return "common/resultView";
		}
	
		//===========회원탈퇴===========//
		//회원 탈퇴 폼
		@GetMapping("/member/delete.do")
		public String formDelete() {
			return "memberDelete";
		}
		//회원 탈퇴 처리
		@PostMapping("/member/delete.do")
		public String submitDelete(
				         @Valid MemberVO memberVO,
				         BindingResult result,
				         HttpSession session,
				         Model model) {
			logger.debug("<<회원탈퇴>> : " + memberVO);
			
			//유효성 체크 결과 오류가 있으면 폼 호출
			//id,passwd 필드의 에러만 체크
			if(result.hasFieldErrors("id") || 
					result.hasFieldErrors("passwd")) {
				return formDelete();
			}
			
			MemberVO user = 
				   (MemberVO)session.getAttribute("user");
			memberVO.setMem_num(user.getMem_num());
			
			try {
				//로그인 한 회원이 아이디 구하기
				MemberVO db_member =
						memberService.selectMember(
								memberVO.getMem_num());
				boolean check = false;
				//로그인 한 회원 아이디와 입력한 아이디 대조
				if(db_member!=null && memberVO.getMem_id().equals(
						                     db_member.getMem_id())) {
					//비밀번호 일치 여부 체크
					check = db_member.isCheckedPasswd(
							              memberVO.getMem_pw());
				}
				if(check) {
					//인증성공, 회원정보 삭제
					memberService.deleteMember(
							      memberVO.getMem_num());
					//로그아웃
					session.invalidate();
					
					model.addAttribute(
							"accessMsg", "회원탈퇴를 완료했습니다.");
					//notice.jsp의 기본 이동이 /main/main.do로
					//설정되어 있이서 그대로 사용
					return "common/notice";
				}
				//인증 실패
				throw new AuthCheckException();
			}catch(AuthCheckException e) {
				result.reject("invalidIdOrPassword");
				return formDelete();
			}
		}
	
	
}
