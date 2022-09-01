package kr.spring.calendar.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.calendar.service.CalendarService;
import kr.spring.calendar.vo.CalendarVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class CalendarController {

	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	private int rowCount = 20;
	private int pageCount = 10;

	@Autowired
	private CalendarService calendarService;

	// 자바빈(VO) 초기화
	@ModelAttribute
	public CalendarVO initCommand() {
		return new CalendarVO();
	}

	// ===========게시판 글 등록============// //등록 폼

	@GetMapping("/calendar/main.do")
	public String form() {
		return "calendarList";
	} // 등록 폼에서 전송된 데이터 처리

	@PostMapping("/calendar/write.do")
	public String submit(@Valid CalendarVO calendarVO, BindingResult result, HttpServletRequest request,
			HttpSession session, Model model) {

		logger.debug("<<게시판 글 저장>> : " + calendarVO);

		// 유효성 검사 결과 오류가 있으면 폼 호출 if(result.hasErrors()) { return form(); }

		MemberVO user = (MemberVO) session.getAttribute("user"); // 회원번호 셋팅
		calendarVO.setMem_num(user.getMem_num()); // ip셋팅

		// 글쓰기 boardService.insertBoard(boardVO);

		// View에 표시할 메시지 model.addAttribute( "message", "글 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/calendar/list.do");

		return "common/resultView";
	}

}
