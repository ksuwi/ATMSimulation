package com.ksuwimon.atmsimulation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeConroller {

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.put("name", "ksuwimon test");
        return "greeting";
    }
	
	@RequestMapping("/welcome")
	public String loginMessage(){
		return "welcome";
	}
    
}
