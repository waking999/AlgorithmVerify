package edu.cdu.fpt.algorithmverify.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cdu.fpt.algorithmverify.model.AlgorithmInfo;
import edu.cdu.fpt.algorithmverify.service.AlgorithmInfoService;

@Controller

public class AlgorithmInfoListController {

	@Autowired
	AlgorithmInfoService algorithmInfoSerive;


	@RequestMapping("runAlgorithm")
	public String runAlgorithm(HttpServletRequest request,@ModelAttribute("algorithmName") String algorithmName) {
		 String path = request.getSession().getServletContext().getRealPath("/");
		 
		 
		algorithmInfoSerive.runAlgorithm(path,algorithmName);
		
		return "algorithmInfo";
	}
	
	@RequestMapping("compareAlg")
	public String compareAlg(HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/");
		
		algorithmInfoSerive.compareAlg(path);
		
		return "algorithmInfo";
	}
	
	
	@RequestMapping(value = "uploadAlgorithm", method = RequestMethod.GET)
	public String setupFormFormUploadAlgorithm(ModelMap model) { 
        AlgorithmInfo algorithmInfo = new AlgorithmInfo();
        model.addAttribute("algorithmInfo", algorithmInfo); 
        return "uploadAlgorithm"; 
	} 


}
