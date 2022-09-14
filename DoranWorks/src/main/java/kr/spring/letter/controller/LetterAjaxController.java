package kr.spring.letter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.letter.service.LetterService;
import kr.spring.letter.vo.LetterVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class LetterAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(LetterAjaxController.class);
	
	@Autowired
	private LetterService letterService;
	
	//상세페이지 중요체크 등록
	@RequestMapping("/letter/writeImportant.do")
	@ResponseBody
	public Map<String, Object> writeImportant(int lt_num,HttpSession session){
		logger.debug("<<상세페이지 중요 등록>> : "+lt_num);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lt_num", lt_num);
			LetterVO letter =  letterService.selectLetter(lt_num);
			if(letter.getLt_sender_num()==user.getMem_num()) {//보낸쪽지함 중요 업데이트
				if(letter.getLt_important()==1) {
					map.put("important", 0);
					
					letterService.updateSendImportant(map);
					
					mapJson.put("result", "success");
					mapJson.put("status", "noImportant");
				}else {
					map.put("important", 1);
					
					letterService.updateSendImportant(map);
					
					mapJson.put("result", "success");
					mapJson.put("status", "yesImportant");
				}
			}else {//받는쪽지함 중요 업데이트
				map.put("lt_receiver_num", user.getMem_num());
				
				LetterVO rec_letter = letterService.selectRecLetter(map);
				
				if(rec_letter.getLt_important()==1) {
					map.put("important", 0);
					
					letterService.updateReceiveImportant(map);
					
					mapJson.put("result", "success");
					mapJson.put("status", "noImportant");
				}else {
					map.put("important", 1);
					
					letterService.updateReceiveImportant(map);
					
					mapJson.put("result", "success");
					mapJson.put("status", "yesImportant");
				}
			}
		}
		return mapJson;
	}
	
	//상세페이지 중요정보 읽기
	@RequestMapping("/letter/getImportant.do")
	@ResponseBody
	public Map<String, Object> getImportant(int lt_num,HttpSession session){
		
		logger.debug("<<상세페이지 중요 읽기>> : "+lt_num);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {//로그인이 되지 않았을때
			mapJson.put("status", "noImportant");
		}else {//로그인 된 경우
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lt_num", lt_num);
			LetterVO letter =  letterService.selectLetter(lt_num);
			
			if(letter.getLt_sender_num()==user.getMem_num()) {
				if(letter.getLt_important()==1) {
					mapJson.put("status", "yesImportant");
				}else {
					mapJson.put("status", "noImportant");
				}
			}else {
				map.put("lt_receiver_num", user.getMem_num());
				LetterVO rec_letter = letterService.selectRecLetter(map);
				
				if(rec_letter.getLt_important()==1) {
					mapJson.put("status", "yesImportant");
				}else {
					mapJson.put("status", "noImportant");
				}
			}
		}
		
		return mapJson;
	}
	
	//목록 읽기버튼
	@RequestMapping("/letter/listRead.do")
	@ResponseBody
	public Map<String, Object> listRead(String lt_nums,HttpSession session){
		logger.debug("<<목록 읽음 버튼>> : "+lt_nums);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			String[] nums = lt_nums.split(",");
		}
		return mapJson;
	}
	
}
