package edu.cdu.fpt.algorithmverify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import edu.cdu.fpt.algorithmverify.model.AlgorithmInfo;
import edu.cdu.fpt.algorithmverify.model.User;
import edu.cdu.fpt.algorithmverify.service.AlgorithmInfoService;
import edu.cdu.fpt.algorithmverify.service.UserService;

@Controller

public class LogInController {
	@Autowired
	UserService userService;
	@Autowired
	AlgorithmInfoService algorithmInfoSerive;
	
	
	@RequestMapping(value = "logIn", method = RequestMethod.GET)
	public String setupForm(ModelMap model) { 
        User user = new User();
        model.addAttribute("user", user); 
        return "logIn"; 
	} 
	
	@RequestMapping(value = "logInSubmit", method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("user") User user,
			BindingResult result, SessionStatus status) {
		boolean logInSuccess = userService.logIn(user);
		System.out.println("LogInSubmit");
		if (logInSuccess) {
			List<AlgorithmInfo> list= algorithmInfoSerive.getAlgorithmInfoList();
			return new ModelAndView("algorithmInfoList").addObject("varAlgorithmInfoList", list);

		} else {
			return new ModelAndView("logIn");
		}

	}
}
