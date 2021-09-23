package com.taskms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
	
	@RequestMapping("/error/404")
	public String notFound() {
		return "404";
	}
	
	@RequestMapping("/user/verified")
	public String accVerified() {
		return "user/verified";
	}

	// My Task
//	@RequestMapping("/task/mytask")
//	public String myTask() {
//		return "task/task";
//	}

	// Add Task
//	@RequestMapping("/task/add")
//	public String addTask() {
//		return "task/add";
//	}

	// Important Task
//	@RequestMapping("/task/important")
//	public String importantTask() {
//		return "task/important";
//	}

	// User Login
//	@RequestMapping("/user/login")
//	public String login() {
//		return "user/login";
//	}

	// User Register
//	@RequestMapping("/user/register")
//	public String register() {
//		return "user/register";
//	}

	// User Profile
//	@RequestMapping("/user/profile")
//	public String profile() {
//		return "user/profile";
//	}

}
