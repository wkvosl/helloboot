package tobyspring.helloboot;

import java.io.IOException;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HellobootApplication.class, args);
		
		//스프링컨테이너
		GenericApplicationContext appCon = new GenericApplicationContext();
		appCon.registerBean(HelloController.class); //bean 등록 끝.
		appCon.refresh(); //초기화 : 빈 오브젝트 생성
		
		
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); 
		WebServer webServer = serverFactory.getWebServer(servletContext -> {		
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				private static final long serialVersionUID = 1L;

				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					
					// 인증, 보안, 다국어, 공통 기능을 만들고
					
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");
						
						HelloController helloController = appCon.getBean(HelloController.class);
						String ret = helloController.hello(name);
						
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().print("Hello Servlet : " + ret);
					}
					
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
					
				}
				
			}).addMapping("/*");
		}); 
		
		webServer.start();
		
	}

}
