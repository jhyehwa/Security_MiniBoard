package com.sp.admin.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin.main.mainController")
public class MainController {
	
	@RequestMapping(value = "/admin/main")
	public String main() {

		return "admin/main/main";
	}
}
