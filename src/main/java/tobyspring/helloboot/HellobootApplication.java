package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//@SpringBootApplication
@Configuration
public class HellobootApplication {

	//팩토리메서드
	@Bean
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService);
	}
	
	@Bean
	public HelloService helloService() {
		return new SimpleHelloService(); //인터페이스타입으로 생성
	}
	
	
	public static void main(String[] args) {
//		SpringApplication.run(HellobootApplication.class, args);
		
		//스프링컨테이너
		AnnotationConfigWebApplicationContext appCon = new AnnotationConfigWebApplicationContext() {
			
			@Override
			protected void onRefresh() {
				super.onRefresh();
				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); 
				WebServer webServer = serverFactory.getWebServer(servletContext -> {		
					servletContext.addServlet("dispatcherServlet", 
								new DispatcherServlet(this))
					.addMapping("/*");
				});
				
				webServer.start();
			}
		};

		appCon.register(HellobootApplication.class);
		appCon.refresh(); //초기화 : 빈 오브젝트 생성
		
	}

}
