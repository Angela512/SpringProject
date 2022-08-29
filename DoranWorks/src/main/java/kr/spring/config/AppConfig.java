package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.interceptor.LoginCheckInterceptor;

//자바코드 기반 설정 클래스

@Configuration
public class AppConfig implements WebMvcConfigurer{

	//인터셉터 지정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/member/myPage.do");
		
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		
		//해당 경로에 xml 설정 파일을 넣음
		configurer.setDefinitions(new String[] {
				"/WEB-INF/tiles-def/main.xml",
				"/WEB-INF/tiles-def/member.xml",
				"/WEB-INF/tiles-def/second.xml",
				"/WEB-INF/tiles-def/third.xml",
				"/WEB-INF/tiles-def/forth.xml",
				"/WEB-INF/tiles-def/fifth.xml"
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


