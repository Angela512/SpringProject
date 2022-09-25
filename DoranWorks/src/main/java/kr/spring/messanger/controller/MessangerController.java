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
import kr.spring.alarm.service.AlarmService;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.controller.MessangerController;

@Controller
public class MessangerController {
	private static final Logger logger = LoggerFactory.getLogger(MessangerController.class);
	
	@Autowired
	private MessangerService messangerService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AlarmService alarmService;
	
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
	public String msgList() {
		return "list";
	}
	
	//==============채팅방 목록==============
	@RequestMapping("/messanger/chatroomList.do")
	@ResponseBody
	public Map<String,Object> chatroomList(@RequestParam(value="keyword", defaultValue="") String keyword, HttpSession session) {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			mapAjax.put("result", "logout");
		}else {
			//채팅방 목록 가져오기
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("mem_num", user.getMem_num());
			map.put("keyword", keyword);
			List<ChatmemVO> list = messangerService.selectChatmemCount(map);
			logger.debug("<<채팅방 마지막메시지>> : " + list);
			mapAjax.put("result", "success");
			mapAjax.put("list", list);
			mapAjax.put("user_name", user.getMem_name());
		}
		
		return mapAjax;
	}
	
	//==============채팅방===============
	//멤버 리스트 및 검색(완료)
	@RequestMapping("/messanger/createChatroom.do")
	@ResponseBody
	public Map<String,Object> memSearch(@RequestParam(value="keyword", defaultValue="") String keyword,
											HttpSession session) {
		//파라미터들 맵으로 묶어서 보냄
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);

		//총 멤버 수
		int count = memberService.selectMsgMemberRowCount(map);
		
		logger.debug("<<count>> : " + count);

		List<MemberVO> list = null;
		
		if(count > 0) {
			list = memberService.selectMsgMemberList(map);
		}

		Map<String,Object> mapAjax = new HashMap<String,Object>();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user != null) {
			mapAjax.put("user_num", user.getMem_num());
		}
		mapAjax.put("count", count);
		mapAjax.put("list", list);

		return mapAjax;
	}
	
	//==============채팅방 멤버 선택 후 메시지방 생성하기==============
	@RequestMapping("/messanger/confirm.do")
	@ResponseBody
	public Map<String,Object> createChatroom(ChatroomVO chatroomVO, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//채팅방번호, 채팅방멤버들 생성
		messangerService.insertChatroom(chatroomVO, user.getMem_name()); 
		int chatroom_num = chatroomVO.getChatroom_num();

		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		if(user != null) {
			mapAjax.put("user_num", user.getMem_num());
		}
		mapAjax.put("chatroom_num", chatroom_num);
		
		return mapAjax;
	}
	
	//==============채팅방 띄우고 메시지 보여주기==============
	@RequestMapping("/messanger/gotochat.do")
	@ResponseBody
	public Map<String, Object> goChat(@RequestParam int chatroom_num, HttpSession session){
		logger.debug("<<선택된 채팅방>> : " + chatroom_num);
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		List<ChatmemVO> list = null;
		List<MessangerVO> msgList = null;
		//해당 채팅방의 멤버들 정보
		list = messangerService.selectChatmem(chatroom_num); //채팅방 안에 있는 멤버들 정보 읽어옴
		msgList = messangerService.selectMsgList(user.getMem_num(), chatroom_num); //채팅방 대화내용 읽어옴
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		if(user != null) {
			mapAjax.put("user_num", user.getMem_num());
		}
		mapAjax.put("list", list);
		mapAjax.put("msgList", msgList);
		mapAjax.put("user_name", user.getMem_name());
		
		return mapAjax;
	}
	
	
	//==============메시지==============	
	@RequestMapping("/messanger/writeMsg.do")
	@ResponseBody
	public Map<String, Object> submitMsg(MessangerVO messangerVO, HttpSession session){
		logger.debug("<<메시지 전송>> : " + messangerVO);
		logger.debug("<<chatroom_num>> : " + messangerVO.getChatroom_num());
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//알림용(나를 제외한 채팅방 멤버들 mem_num 읽어옴)
		List<Integer> member_list = messangerService.selectMemberList(messangerVO);
		member_list.remove((Integer)user.getMem_num());
		logger.debug("<<member_list>> : " + member_list);
		
		//알람 정보 저장
        alarmService.insertAlarm(1,member_list);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();

		if(user == null) { //로그인 안 된 경우
			mapAjax.put("result", "logout");
		}else{ 
			messangerVO.setMem_num(user.getMem_num());
			messangerService.insertMessage(messangerVO); //메시지 저장
			
			mapAjax.put("chatroom_num", messangerVO.getChatroom_num());
			mapAjax.put("result", "success");
			mapAjax.put("member_list", member_list);
		}
		
		return mapAjax;
	}
	
	
	
	
	
	
	
}










