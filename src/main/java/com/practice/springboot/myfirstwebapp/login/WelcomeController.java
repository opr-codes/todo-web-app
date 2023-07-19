package com.practice.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class WelcomeController {
	
//	//private Logger logger = LoggerFactory.getLogger(getClass());
//
//	private AuthenticationService authenticationService;
//	
//	public LoginController(AuthenticationService authenticationService) {
//		super();
//		this.authenticationService = authenticationService;
//	}

	//login
	//GET, POST
	@RequestMapping(value="/", method = RequestMethod.GET)		//login url supports GET only	
	public String gotoWelcomePage(ModelMap model) {
		model.put("name",getLoggedinUsername());
		return "welcome";			//returning view name
	}
	
	private String getLoggedinUsername( ) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
//	@RequestMapping(value="login", method = RequestMethod.POST)		//login url supports POST only	
//	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) { 
//		// @RequestParam- Capture name and pwd from form data, put them in map and show them in jsp
//		
//		if(authenticationService.authenticate(name, password)) {
//			model.put("name", name);
//			//model.put("password", password);
//			
//			//Authentication 		//Correct cred -> welcome page otherwise return to login page
//			//name - udemy
//			//password - dummy
//			
//			return "welcome";			//returning view name
//		}	
//		
//		model.put("errorMessage", "Invalid Credentials! Please try again.");
//		return "login";
//	}
	
	
	
//	///login => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp
//	
//	//http://localhost:8080/login?name=Om
//	//Model			//Passing anything from controller to jsp by putting it into a model
//	@RequestMapping("login")		
//	//@ResponseBody			//Don't want to return back directly; Want to redirect to a view or a JSP; that's why commented		
//	public String gotoLoginPage(@RequestParam String name, ModelMap model) {
//		model.put("name", name);							// To show it in your JSP
//		logger.debug("Request param is {}",name);
//		logger.info("Info lvl is {}", name);
//		logger.warn("Warn lvl is {}", name);
//		System.out.println("Request param is " + name);		//NOT RECOMMENDED for prod code
//		return "login";			//returning view name
//	}
}
