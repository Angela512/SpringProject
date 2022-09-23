package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.interceptor.AdminCheckInterceptor;
import kr.spring.interceptor.LoginCheckInterceptor;

//자바코드 기반 설정 클래스

@Configuration
public class AppConfig implements WebMvcConfigurer{
	//인터셉터 지정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/member/myPage.do")
															.addPathPatterns("/member/update.do")
															.addPathPatterns("/member/changePassword.do")
															.addPathPatterns("/messanger/chatroomList.do")
															.addPathPatterns("/messanger/createChatroom.do")
															.addPathPatterns("/messanger/confirm.do")
															.addPathPatterns("/messanger/list.do")
															.addPathPatterns("/messanger/gotochat.do")
															.addPathPatterns("/messanger/writeMsg.do")
															.addPathPatterns("/letter/write.do")
															.addPathPatterns("/letter/detail.do")
															.addPathPatterns("/letter/detailDelete.do")
															.addPathPatterns("/letter/reply.do")
															.addPathPatterns("/letter/forward.do")
															.addPathPatterns("/notice/write.do")
															.addPathPatterns("/notice/list.do")
															.addPathPatterns("/notice/detail.do")
															.addPathPatterns("/notice/update.do")
															.addPathPatterns("/notice/delete.do")
															.addPathPatterns("/workflow/list.do")
															.addPathPatterns("/workflow/update.do")
															.addPathPatterns("/workflow/signList.do")
															.addPathPatterns("/workflow/write.do")
															.addPathPatterns("/workflow/detail.do");
	
		registry.addInterceptor(new AdminCheckInterceptor()).addPathPatterns("/member/admin_list.do")
															.addPathPatterns("/member/registerUser.do");
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		
		//해당 경로에 xml 설정 파일을 넣음
		configurer.setDefinitions(new String[] {
			"/WEB-INF/tiles-def/main.xml",
			"/WEB-INF/tiles-def/member.xml",
			"/WEB-INF/tiles-def/workflow.xml",
			"/WEB-INF/tiles-def/letter.xml",
			"/WEB-INF/tiles-def/messanger.xml",
			"/WEB-INF/tiles-def/notice.xml"
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	
}


