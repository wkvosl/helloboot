package tobyspring.helloboot;

import org.springframework.stereotype.Component;

@Component
public class SimpleHelloService implements HelloService {
	
	public String sayHello(String name) {
		return "Hello" + name;
	}
	
}
