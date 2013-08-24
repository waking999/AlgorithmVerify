package edu.cdu.fpt.algorithmverify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cdu.fpt.algorithmverify.service.AlgorithmInfoService;

@Controller

public class AlgorithmInfoListController {

	@Autowired
	AlgorithmInfoService algorithmInfoSerive;


	@RequestMapping("runAlgorithm")
	public String runAlgorithm() {
		algorithmInfoSerive.runAlgorithm();
		
		return "algorithmInfo";
	}
	


}
