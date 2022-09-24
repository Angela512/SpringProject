package kr.spring.interceptor;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.spring.member.vo.MemberVO;

public class AdminCheckInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(AdminCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
																			throws Exception {
		logger.debug("<<AdminCheckInterceptor 진입>>");
		
		//로그인 여부 검사
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user == null || user.getAuth() != 2) { //로그인하지 않았거나 관리자가 아닐 경우
			//권한없음
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			dispatcher.forward(request, response);
			
			return false;
		}
		
		//관리자 권한인 경우 
		return true; //true면 내가 요청한 페이지가 보여짐 false면 redirect해서 공지 페이지 보여지도록
	}
	
	
	
}






