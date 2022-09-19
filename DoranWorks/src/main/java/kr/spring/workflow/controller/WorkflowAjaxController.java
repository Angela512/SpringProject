package kr.spring.workflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.letter.controller.LetterAjaxController;
import kr.spring.member.vo.MemberVO;
import kr.spring.workflow.service.WorkflowMainService;

@Controller
public class WorkflowAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(WorkflowAjaxController.class);
	
	@Autowired
	private WorkflowMainService workFlowMainService;
	
	//상세페이지 승인 업데이트
	@RequestMapping("/workflow/updateii.do")
	@ResponseBody
	public Map<String, Object> updateIi(int fw_num,HttpSession session){
		logger.debug("<<상세페이지 승인 등록>> : "+fw_num);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			
		}
		
		return mapJson;
	}
}
