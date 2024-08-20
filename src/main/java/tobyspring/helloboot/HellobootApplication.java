package tobyspring.helloboot;

import java.io.IOException;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
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
		
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); 
		WebServer webServer = serverFactory.getWebServer(servletContext -> {		
			
			HelloController helloController = new HelloController();
			
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				private static final long serialVersionUID = 1L;

				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					
					// 인증, 보안, 다국어, 공통 기능을 만들고
					
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");
						
						String ret = helloController.hello(name);
						
						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE.toString(), MediaType.TEXT_PLAIN.toString());
						resp.getWriter().print("Hello Servlet : " + ret);
					}
					
					else if (req.getRequestURI().equals("/user")) {
						// 로직
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
