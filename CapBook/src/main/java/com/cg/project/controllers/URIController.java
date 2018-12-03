package com.cg.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class URIController {
	
	public URIController() {
	}
	
	@RequestMapping("/")
	public String getIndexPage(){
		return "indexPage";
	}	
	
	
}
