package com.powerconsuption.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth/")
public class PageController {
	@GetMapping("/")
	public String Home() {
		return "index.html";
	}
	
	@GetMapping("/login")
	public String Login() {
		return "login.html";
	}
	
	@GetMapping("/registration")
	public String registration() {
		return "registration.html";
	}
}
