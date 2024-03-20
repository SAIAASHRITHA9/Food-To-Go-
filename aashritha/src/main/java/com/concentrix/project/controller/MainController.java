package com.concentrix.project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	private static final Logger logs = LogManager.getLogger("MainController.class");
	
	@GetMapping("/")
	public String homePage() {
		
		logs.info("The application has been opened");
		return "home";
	}

}
