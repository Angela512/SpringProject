package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.member.vo.MemberVO;
import kr.spring.member.email.Email;
import kr.spring.member.email.EmailSender;
import kr.spring.member.service.MemberService;
import kr.spring.util.CipherTemplate;

@Controller
public class MemberFindPasswordController {

	private static final Logger logger = LoggerFactory.getLogger(MemberFindPasswordController.class);
	
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email email;

	@Resource
	private MemberService memberService;
	
	@Resource
	private CipherTemplate cipherAES;

	//자바빈 초기화
	@ModelAttribute
	public MemberVO initCommand(){
		return new MemberVO();
	}

	@RequestMapping(value="/member/sendPassword.do",method=RequestMethod.GET)
	public String form(){
		return "memberFindPassword";
	}

	@RequestMapping(value="/member/sendPassword.do",method=RequestMethod.POST)
	public String sendEmailAction(@Valid MemberVO memberVO,
			BindingResult result,Model model) throws Exception{

		if(logger.isDebugEnabled()){
			logger.debug("<<비밀번호 찾기>> : " +memberVO);
		}

		//유효성 체크
		if(result.hasFieldErrors("id") || result.hasFieldErrors("email")){
			return form();
		}

		MemberVO member = memberService.selectCheckMember(memberVO.getMem_id());
		if(member!=null && member.getMem_email().equals(memberVO.getMem_email())) {
			//기본비밀번호를 임시비밀번호로 변경
			String passwd = randomPassword(10);
			member.setMem_pw(cipherAES.encrypt(passwd));
			//변경된 임시비밀번호를 DB에 저장
			memberService.updateRandomPassword(member);

			email.setContent("임시 비밀번호는 " + passwd +" 입니다.");
			email.setReceiver(member.getMem_email());
			email.setSubject(member.getMem_id()+" 님 비밀번호 찾기 메일입니다.");
			
			if(logger.isDebugEnabled()){
				logger.debug("<<임시 비밀번호>> : " +passwd);
			}
			
			emailSender.sendEmail(email);
			model.addAttribute("message","이메일을 발송했습니다!");
			model.addAttribute("url","main.do");

			return "common/resultView";
		}else {
			result.reject("invalidIdOrEmail");
			return form();
		}
	}

	//비밀번호 보안을 위한 난수 발생 메소드
	public String randomPassword(int length){
		int index = 0;
		
		char[] charSet1 = new char[]{
				'0','1','2','3','4','5','6','7','8','9'
		};
		
		char[] charSet2 = new char[]{
				'@','$','!','%','*','#','?','&'
		};
		
		char[] charSet3 = new char[]{
				'0','1','2','3','4','5','6','7','8','9'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M'
				,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
				,'a','b','c','d','e','f','g','h','i','h','k','l','m'
				,'n','o','p','q','r','s','t','u','v','w','x','y','z'
		};
		
		StringBuffer sb = new StringBuffer();
		index = (int)(charSet1.length * Math.random());
		sb.append(charSet1[index]);
		
		index = (int)(charSet2.length * Math.random());
		sb.append(charSet2[index]);
		
		for(int i=0;i<length-2;i++){
			index = (int)(charSet3.length * Math.random());
			sb.append(charSet3[index]);
		}

		return sb.toString();
	}
}