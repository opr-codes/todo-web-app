package com.practice.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller								//web ui related component that handles web requests, thats why @Controller
public class SayHelloController {

	//"say-hello"=> "Hello! What are you learning today?"
	
	//"say-hello
	// http://localhost:8080/say-hello
	@RequestMapping("say-hello")		//mapping request url "say-hello" to sayHello() method
	@ResponseBody						//return whatever is returned to the msg as is to the browser
	public String sayHello() {
		return "Hello! What are you learning today?";
	}
	
	@RequestMapping("say-hello-html")		//mapping request url "say-hello" to sayHello() method
	@ResponseBody							//return whatever is returned to the msg as is to the browser
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer(); 
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> My first HTML page - Changed</title>");
		sb.append("<head>");
		sb.append("<body>");
		sb.append("My first html page with body - Changed");
		sb.append("</body>");
		sb.append("</html>");

		
		return sb.toString();
	}
	
	//sayHello.jsp
	//"say-hello-jsp" => sayHello.jsp
	//	/src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	//	/src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
	//	/src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
	//	/src/main/resources/META-INF/resources/WEB-INF/jsp/todos.jsp
	@RequestMapping("say-hello-jsp")		
	//@ResponseBody			//Don't want to return back directly; Want to redirect to a view or a JSP		
	public String sayHelloJsp() {
		return "sayHello";
	}
	
	
}
