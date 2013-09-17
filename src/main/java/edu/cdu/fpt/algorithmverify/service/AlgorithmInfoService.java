package edu.cdu.fpt.algorithmverify.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.springframework.stereotype.Service;

import edu.cdu.fpt.alg.AlgorithmGreedy;
import edu.cdu.fpt.alg.AlgorithmHillClimbing;
import edu.cdu.fpt.alg.IAlgorithm;
import edu.cdu.fpt.algorithmverify.model.AlgorithmInfo;
import edu.cdu.fpt.io.InputFile;

@Service
public class AlgorithmInfoService {
	
		Map<String,String> algPreAlgMap=new HashMap<String,String>();
		
	
	public List<AlgorithmInfo> getAlgorithmInfoList(){
		List<AlgorithmInfo> list = new ArrayList<AlgorithmInfo>();
		AlgorithmInfo ai1 = new AlgorithmInfo();
		ai1.setFilePath("Alogrithm1.java");
		ai1.setLog("Alogrithm1.log");
		ai1.setName("AlgorithmGreedy");
		ai1.setUploadDate("2013-08-24 19:20:34");
		list.add(ai1);
		
		AlgorithmInfo ai2 = new AlgorithmInfo();
		ai2.setFilePath("Alogrithm2.java");
		ai2.setLog("Alogrithm2.log");
		ai2.setName("AlgorithmHillClimbing");
		ai2.setUploadDate("2013-08-14 09:02:23");
		list.add(ai2);
		
		
		return list;
	}
	
	public void runAlgorithm(String contextPath,String algorithmName){
		algPreAlgMap.put("AlgorithmHillClimbing", "edu.cdu.fpt.alg.AlgorithmGreedy");
		
		String buildXmlFilePath  = contextPath+"WEB-INF\\classes\\build.xml";
		File buildFile = new File(buildXmlFilePath);
		PrintStream logstream=null;
		DefaultLogger cl=new DefaultLogger(); 
//		String algorithmName="AlgorithmHillClimbing";
		
		
		Project p = new Project();
		try {
			String logFilePath  = contextPath+"log\\"+algorithmName+".log";
			logstream=new PrintStream(new FileOutputStream(logFilePath),true);
			cl.setErrorPrintStream(logstream);
			cl.setOutputPrintStream(logstream);
			
			p.fireBuildStarted();
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			helper.parse(p, buildFile);
			
//			cl.setMessageOutputLevel(Project.MSG_VERBOSE);
			p.addBuildListener(cl);
			
			p.setProperty("algorithm.class.name", algorithmName);
			String testFile=contextPath+"resources\\testcase400.csv";
			p.setProperty("algorithm.test.file", testFile);
			
			int k =11;
			p.setProperty("algorithm.k", Integer.toString(k));
			
			String preAlgName = algPreAlgMap.get(algorithmName);
			if(preAlgName!=null){
			p.setProperty("algorithm.preAlg", preAlgName);
			}
			
			String dftTgt = p.getDefaultTarget();

			p.executeTarget(dftTgt); 
			p.fireBuildFinished(null);
		} catch (BuildException e) {
			p.fireBuildFinished(e);
		} catch(Exception e){
			e.printStackTrace();
			
		}finally{
			logstream.close();
		}
		
	}
	
	public void compareAlg(String contextPath){
		//D:\dev\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\AlgorithmVerify\
		String testFilePath = contextPath+"/resources/testcase400.csv";
		int k =11;
		
		InputFile input = new InputFile();
		input.setInputFile(testFilePath);
		input.getAdjacencyInfo();

		IAlgorithm[] algArray={new AlgorithmGreedy(), new AlgorithmHillClimbing()};
		IAlgorithm[] preAlgArray={null, new AlgorithmGreedy()};
		int algArrayLen = algArray.length;
		
		for(int i=0;i<algArrayLen;i++){
			
			IAlgorithm alg = algArray[i];
			IAlgorithm preAlg = preAlgArray[i];
			
			alg.setPreAlg(preAlg);

			alg.initialization(input, k);

			alg.generateDominatingSet();

			Set<List<String>> dsSet = alg.getDominatingSetSet();
			
			int dsSetLen = dsSet.size();
		}
	}

}
