package kr.spring.messanger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.messanger.service.MessangerService;
import kr.spring.messanger.vo.ChatmemVO;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;
import kr.spring.util.PagingUtil;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.controller.MessangerController;

@Controller
public class MessangerController {
	private static final Logger logger = LoggerFactory.getLogger(MessangerController.class);
	private int rowCount = 20;
	private int pageCount = 10;
	
	@Autowired
	private MessangerService messangerService;
	@Autowired
	private MemberService memberService;
	
	//자바빈 초기화
	@ModelAttribute 
	public MessangerVO initCommand() {
		return new MessangerVO();
	}
	@ModelAttribute 
	public ChatroomVO initChatroomCommand() {
		return new ChatroomVO();
	}
	@ModelAttribute 
	public ChatmemVO initChatmemCommand() {
		return new ChatmemVO();
	}
	
	//==============메인===============
	@RequestMapping("/messanger/list.do")
	public ModelAndView msgList(@RequestParam(value="keyword", defaultValue="") String keyword) {
		//파라미터들 맵으로 묶어서 보냄
		/*
		 * Map<String,Object> map = new HashMap<String,Object>(); map.put("keyfield",
		 * keyfield); map.put("keyword", keyword);
		 * 
		 * //총 멤버 수 int count = memberService.selectRowCount(map);
		 * 
		 * logger.debug("<<count>> : " + count);
		 * 
		 * //페이지 처리 PagingUtil page = new PagingUtil(keyfield, keyword, currentPage,
		 * count, rowCount, pageCount, "list.do");
		 * 
		 * List<MemberVO> list = null; List<ChatroomVO> clist = null;
		 * 
		 * if(count > 0) { //page == 0이면 필요없는 작업이니까 이 안으로 넣음 list =
		 * memberService.selectList(map); }
		 */

		ModelAndView mav = new ModelAndView();
		mav.setViewName("list"); //tiles 식별자 명
		/*
		 * mav.addObject("count", count); mav.addObject("list", list);
		 * mav.addObject("clist", clist);
		 */

		return mav;
	}
	
	//==============채팅방 생성===============
	//멤버 리스트 및 검색
	@RequestMapping("/messanger/createChatroom.do")
	@ResponseBody
	public Map<String,Object> chatroomProcess(@RequestParam(value="keyword", defaultValue="") String keyword,
											HttpSession session) {
		//파라미터들 맵으로 묶어서 보냄
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);

		//총 멤버 수
		int count = memberService.selectMessageRowCount(map);
		
		logger.debug("<<count>> : " + count);

		//페이지 처리

		List<MemberVO> list = null;
		List<ChatroomVO> clist = null;
		
		if(count > 0) {
			//page == 0이면 필요없는 작업이니까 이 안으로 넣음

			list = memberService.selectMessageList(map);
		}

		Map<String,Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("list", list);
		mapAjax.put("clist", clist);

		return mapAjax;
	}
	
	//채팅방 멤버 선택 후 메시지방 생성하기
	@RequestMapping("/messanger/confirm.do")//members[]
	@ResponseBody
	public ModelAndView createChatroom(ChatroomVO chatroomVO) {
		
		logger.debug("<<선택된 멤버>> : " + chatroomVO);
		
		//채팅방번호, 채팅방이름 생성
		messangerService.insertChatroom(chatroomVO);
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("msgWrite"); //tiles 식별자 명
		
		
		return mav;
	}
	
	
	//==============메시지==============
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
		logger.debug("<<user 정보>> : " + user);
		//회원번호 셋팅
		messangerVO.setMem_num(user.getMem_num());
		

		//글쓰기
		messangerService.insertMessage(messangerVO);

		//View에 표시할 메시지 지정(식별자, 내용)
		model.addAttribute("message", "글 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/main/main.do"); //작성이 완료되면 목록으로 이동

		//얘는 jsp
		return "common/resultView"; //등록이 되면 자바스크립트를 활용하여 등록됐다고 띄울거임
	}
	
	//==========채팅방 목록==========
	/*
	 * @RequestMapping("/messanger/list.do") public ModelAndView
	 * process(@RequestParam(value="keyfield", defaultValue="") String keyfield,
	 * 
	 * @RequestParam(value="keyword", defaultValue="") String keyword) {
	 * 
	 * //파라미터들 맵으로 묶어서 보냄 Map<String,Object> map = new HashMap<String,Object>();
	 * map.put("keyfield", keyfield); map.put("keyword", keyword);
	 * 
	 * 
	 * List<ChatroomVO> list = null;
	 * 
	 * 
	 * list = messangerService.selectChatroomList(map);
	 * 
	 * 
	 * ModelAndView mav = new ModelAndView(); mav.setViewName("msgList"); //tiles
	 * 식별자 명 mav.addObject("list", list);
	 * 
	 * return mav; }
	 */
	
	
	
	
}










