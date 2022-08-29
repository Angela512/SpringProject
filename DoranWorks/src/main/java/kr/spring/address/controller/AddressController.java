package kr.spring.address.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class AddressController {
	@RequestMapping("/")
	public String page() {
		return "redirect:/address/main.do";
	}

	@RequestMapping("/main/main.do")
	public String main() {
		// 타일스 설정 식별자
		return "main";
	}
}
