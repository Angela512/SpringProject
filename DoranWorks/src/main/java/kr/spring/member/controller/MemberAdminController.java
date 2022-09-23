package kr.spring.member.controller;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
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
import kr.spring.util.CipherTemplate;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;

@Controller
public class MemberAdminController {
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberAdminController.class);
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    private CipherTemplate cipherAES;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//==========회원목록===============//
	@RequestMapping("/member/admin_list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="keyfield",defaultValue="") String keyfield,
								@RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 회원의 개수 또는 검색된 회원의 개수
		int count = memberService.selectRowCount(map);
		
		logger.debug("<<count>> : " + count);
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "admin_list.do");
		 
		List<MemberVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = memberService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_memberList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
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
		logger.debug("<<회원등록>> : " + memberVO);

		//유효성 체크 결과 오류 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
        memberVO.setMem_pw(cipherAES.encrypt(memberVO.getMem_pw()));
		memberService.insertMember(memberVO);

		//속성명		속성값
		model.addAttribute("accessMsg", "회원등록 완료");	

		return "common/notice"; 
	}

	
	
	
	
	
}




