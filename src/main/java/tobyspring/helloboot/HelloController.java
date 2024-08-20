package tobyspring.helloboot;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class HelloController {

	private HelloService helloService;
	
	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello(@RequestParam("name") String name){
		return helloService.sayHello(Objects.requireNonNull(name));
	}
	
}
