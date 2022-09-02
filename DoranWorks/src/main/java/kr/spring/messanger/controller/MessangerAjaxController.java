package kr.spring.messanger.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.service.MessangerService;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.util.PagingUtil;

@Controller
public class MessangerAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(MessangerAjaxController.class);
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MessangerService messangerService;
	
	//채팅방 멤버 선택 시 선택된 멤버 보여주기
	@RequestMapping("/messanger/checkedMemberList.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam int mem_num, HttpSession session){
		logger.debug("<<mem_num>> : " + mem_num);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", mem_num);
		
		//총 선택된 멤버 수
		int count = messangerService.selectCheckedMemberCount(map);
		
		List<ChatroomVO> list = null;
		
		if(count > 0) {
			list = messangerService.selectCheckedMemberList(map);
		}else { //data가 없다면
			list = Collections.emptyList(); //빈 list를 보냄..?
		}
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("list", list);
		
		//로그인한 회원정보 셋팅(board.reply.js에)
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user != null) {
			mapAjax.put("user_num", user.getMem_num());
		}
		
		return mapAjax;
	}
	
	
}








