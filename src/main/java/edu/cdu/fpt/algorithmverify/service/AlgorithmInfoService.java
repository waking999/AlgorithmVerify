package edu.cdu.fpt.algorithmverify.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.cdu.fpt.algorithmverify.model.AlgorithmInfo;

@Service
public class AlgorithmInfoService {
	public List<AlgorithmInfo> getAlgorithmInfoList(){
		List<AlgorithmInfo> list = new ArrayList<AlgorithmInfo>();
		AlgorithmInfo ai1 = new AlgorithmInfo();
		ai1.setFilePath("Alogrithm1.java");
		ai1.setLog("Alogrithm1.log");
		ai1.setName("Algorithm1");
		list.add(ai1);
		
		AlgorithmInfo ai2 = new AlgorithmInfo();
		ai2.setFilePath("Alogrithm2.java");
		ai2.setLog("Alogrithm2.log");
		ai2.setName("Algorithm2");
		list.add(ai2);
		
		return list;
	}
	
	public void runAlgorithm(){
		try {
			System.out.println("start ant");
			String command="cmd /c start D:\\dev\\workspace\\AlgorithmVerify\\src\\main\\java\\callant.bat";
			
			Runtime.getRuntime().exec(command);
			
			System.out.println("end ant");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
