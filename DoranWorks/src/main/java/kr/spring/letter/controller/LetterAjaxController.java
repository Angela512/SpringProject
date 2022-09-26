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
import kr.spring.letter.vo.LetterReadVO;
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
			LetterVO letter =  letterService.selectSendLetter(lt_num);
			if(letter.getLt_sender_num()==user.getMem_num()) {//보낸쪽지함 중요 업데이트
				if(letter.getLt_sender_id().equals(letter.getLt_receiver_id())) {//내게쓴 쪽지
					//보낸쪽지 업데이트
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
					
					
					//받는쪽지 업데이트
					map.put("lt_receiver_num", user.getMem_num());
					
					letterService.updateReceiveImportant(map);
					
					
				}else {//내게쓴쪽지 아닌경우
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
			
			if(letter.getLt_sender_num()==user.getMem_num()) {//보낸쪽지함 정보
				if(letter.getLt_important()==1) {
					mapJson.put("status", "yesImportant");
				}else {
					mapJson.put("status", "noImportant");
				}
				
			}else {//받는쪽지함 정보
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
			Map<String, Object> map = new HashMap<String, Object>();
			LetterReadVO readVO = new LetterReadVO();
			String[] nums = lt_nums.split(",");
			readVO.setNums(nums);
			readVO.setMem_num(user.getMem_num());
			readVO.setLt_read(1);
			
			letterService.updateRead(readVO);
			
			mapJson.put("result", "success");
		}
		return mapJson;
	}
	
	//목록삭제버튼
	@RequestMapping("/letter/listDelete.do")
	@ResponseBody
	public Map<String, Object> listDelete(String lt_nums,HttpSession session){
		logger.debug("<<목록 삭제 버튼>> : "+lt_nums);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			LetterReadVO readVO = new LetterReadVO();
			String[] nums = lt_nums.split(",");
			readVO.setNums(nums);
			readVO.setMem_num(user.getMem_num());
			
			letterService.deleteLetterList(readVO);
			
			mapJson.put("result", "success");
		}
		
		
		return mapJson;
	}
	
	//디테일 안읽음 버튼
	@RequestMapping("/letter/detailNoRead.do")
	@ResponseBody
	public Map<String, Object> detailNoRead(int lt_num,HttpSession session){
		logger.debug("<<상세페이지 안읽음 버튼>> : "+lt_num);
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			LetterReadVO readVO = new LetterReadVO();
			LetterVO letter = letterService.selectSendLetter(lt_num);
			
			readVO.setLt_num(lt_num);
			readVO.setLt_read(0);
			readVO.setMem_num(user.getMem_num());
			
			if(letter.getLt_sender_num()==user.getMem_num()) {//보낸쪽지함 정보
				letterService.updateSendRead(readVO);
				if(letter.getLt_sender_id().equals(letter.getLt_receiver_id())) {//내게쓴 쪽지
					letterService.updateReceiveRead(readVO);
				}
			}else {//받는쪽지함 정보
				letterService.updateReceiveRead(readVO);
			}
			
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	//쪽지 등록 성공 메시지 처리
	@RequestMapping("/letter/getLetterData.do")
	@ResponseBody
	public Map<String, Object> getLetterData(){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("result", "success");
		return mapJson;
	}
}
