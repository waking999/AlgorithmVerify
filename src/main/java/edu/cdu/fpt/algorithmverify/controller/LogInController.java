package edu.cdu.fpt.algorithmverify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.cdu.fpt.algorithmverify.model.User;
import edu.cdu.fpt.algorithmverify.service.UserService;

@Controller
@RequestMapping("/logIn")
@SessionAttributes("user") 
public class LogInController {
	@Autowired
	UserService userService;

//	@RequestMapping(method = RequestMethod.GET)
//	public String setupForm(ModelMap model) {
//		User user = new User();
//		model.addAttribute("user", user);
//		return "logIn";
//	}
	
	@ModelAttribute("user")
	public User createModel() {
	    return new User();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("user") User user,
			BindingResult result, SessionStatus status) {
		boolean logInSuccess = userService.logIn(user);
		if (logInSuccess) {
			return "algorithmUploadHistory";
		} else {
			return "logIn";
		}

	}
}
