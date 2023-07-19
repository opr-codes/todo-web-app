package com.practice.springboot.myfirstwebapp.poop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PoopController {

	@RequestMapping("poop")		
	//@ResponseBody			//Don't want to return back directly; Want to redirect to a view or a JSP		
	public String PoopItUp() {
		return "poop";
	}
}
