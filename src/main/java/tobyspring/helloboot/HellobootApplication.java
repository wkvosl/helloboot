package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//@SpringBootApplication
@Configuration
@ComponentScan
public class HellobootApplication {

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
