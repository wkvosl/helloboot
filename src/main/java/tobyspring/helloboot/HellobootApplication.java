package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HellobootApplication.class, args);
		
		//스프링컨테이너
		GenericWebApplicationContext appCon = new GenericWebApplicationContext();
		appCon.registerBean(HelloController.class); //bean 등록 끝.
		appCon.registerBean(SimpleHelloService.class); //bean 등록 끝.
		appCon.refresh(); //초기화 : 빈 오브젝트 생성
		
		
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); 
		WebServer webServer = serverFactory.getWebServer(servletContext -> {		
			servletContext.addServlet("dispatcherServlet", 
						new DispatcherServlet(appCon)
					).addMapping("/*");
		});
		
		webServer.start();
		
	}

}
