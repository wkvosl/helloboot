package tobyspring.helloboot;

import java.io.IOException;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HellobootApplication.class, args);
		
		//톰캣 시작 : 내장톰캣 불러오기
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); //ServletWebServerFactory으로 받아도 됨.

		
		/*
		//익명클래스 생성
		WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {

			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				
			}
			
		}); //서블릿 컨테이너를 만드는 생성함수.
		*/
		
		//위 소스가 람다식으로 변경됨. 단축키 Ctrl + 1
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {

				protected void service(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException {
					// TODO Auto-generated method stub
					resp.setStatus(200);
					resp.setHeader("Content-Type", "text/plain");
					resp.getWriter().print("Hello Servlet");
				}
				
			}).addMapping("/hello");
		}); //서블릿 컨테이너를 만드는 생성함수.
		
		webServer.start();
		
	}

}
