package kr.spring.address.controller;

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

import kr.spring.address.controller.AddressController;
import kr.spring.address.service.AddressService;
import kr.spring.address.vo.AddressVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

public class AddressController {
	private static final Logger logger =
	         LoggerFactory.getLogger(
			          AddressController.class);
private int rowCount = 20;
private int pageCount = 10;

@Autowired
private AddressService addressService;

//자바빈(VO) 초기화
@ModelAttribute
public AddressVO initCommand() {
return new AddressVO();
}

//===========게시판 글 등록============//
//등록 폼
@GetMapping("/address/main.do")
public String form() {
return "addressMain";
}
//등록 폼에서 전송된 데이터 처리
@PostMapping("/address/main.do")
public String submit(@Valid AddressVO addressVO,
	      BindingResult result,
	      HttpServletRequest request,
	      HttpSession session,
	      Model model) {

logger.debug("<<게시판 글 저장>> : " + addressVO);

//유효성 검사 결과 오류가 있으면 폼 호출
if(result.hasErrors()) {
	return form();
}

MemberVO user = 
		(MemberVO)session.getAttribute("user");
//회원번호 셋팅
addressVO.setMem_num(user.getMem_num());

//글쓰기
addressService.insertAddress(addressVO);

//View에 표시할 메시지
model.addAttribute(
		"message", "글 등록이 완료되었습니다.");
model.addAttribute(
"url", request.getContextPath()+"/address/main.do");

return "common/resultView";
}

}
