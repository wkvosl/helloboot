package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

//@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HellobootApplication.class, args);
		
		//톰캣 시작 : 내장톰캣 불러오기
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); //ServletWebServerFactory으로 받아도 됨.
		WebServer webServer = serverFactory.getWebServer(); //서블릿 컨테이너를 만드는 생성함수.
		webServer.start();
		
	}

}
