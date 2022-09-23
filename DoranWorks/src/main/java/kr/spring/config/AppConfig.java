package kr.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.interceptor.AdminCheckInterceptor;
import kr.spring.interceptor.LoginCheckInterceptor;
import kr.spring.util.CipherTemplate;

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
	@Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
       Properties prop = new Properties();
       prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
       prop.put("mail.smtp.starttls.enable", "true");
       prop.put("mail.transport.protocol", "smtp");
       prop.put("mail.smtp.auth", "true");
       prop.put("mail.debug", "true");
       
       JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
       javaMail.setHost("smtp.gmail.com");
       javaMail.setPort(587);
       javaMail.setDefaultEncoding("utf-8");
       javaMail.setUsername("sk4cks@gmail.com");
       javaMail.setPassword("ulmrmzatbcfjtrrv");
       javaMail.setJavaMailProperties(prop);
       return javaMail;
    }
	
	@Bean
    public CipherTemplate cipherTempate() {
       CipherTemplate cipher = new CipherTemplate();
       cipher.setAlgorithm("AES");
       cipher.setSecretKey("j2sroojcj2vgz5v9qap2sps9u");
       return cipher;
    }
}


