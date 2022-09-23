package kr.spring.alarm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.alarm.service.AlarmService;
import kr.spring.alarm.vo.AlarmVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class AlarmController {
	private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

	@Autowired
	private AlarmService alarmService;

	@RequestMapping("/alarm/getCount.do")
	@ResponseBody
	public Map<String,Integer> process(AlarmVO vo) {

		int count = alarmService.selectAlarmCount(vo);

		Map<String,Integer> mapAjax = new HashMap<String,Integer>();
		mapAjax.put("count",count);

		return mapAjax;
	}
	
	@RequestMapping("/alarm/deleteCount.do")
	@ResponseBody
	public Map<String,String> delete(AlarmVO vo,HttpSession session) {
		Map<String,String> mapAjax = new HashMap<String,String>();
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapAjax.put("result","logout");
		}else {
			alarmService.deleteAlarm(vo);
			mapAjax.put("result","success");
		}
		return mapAjax;
	}
}





