package edu.cdu.fpt;

import java.util.List;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import edu.cdu.fpt.alg.IAlgorithm;
import edu.cdu.fpt.io.InputFile;
import edu.cdu.fpt.judge.IJudge;
import edu.cdu.fpt.util.Util;

public class AlgorithmTask extends Task {

	// private IAlgorithm alg;
	// private IAlgorithm preAlg;

	private String algClass;

	private String preAlgClass;

	public void setPreAlgClass(String preAlgClass) {
		this.preAlgClass = preAlgClass;
	}

	public void setAlgClass(String algClass) {
		this.algClass = algClass;
	}

	private String testFilePath;

	public void setTestFilePath(String testFilePath) {
		this.testFilePath = testFilePath;
	}

	private int k;

	public void setK(int k) {
		this.k = k;
	}

	// private IJudge judge;
	private String judgeClass;

	public void setJudgeClass(String judgeClass) {
		this.judgeClass = judgeClass;
	}

	// The method executing the task
	public void execute() throws BuildException {

		try {
			InputFile input = new InputFile();
			input.setInputFile(this.testFilePath);
			input.getAdjacencyInfo();

			IAlgorithm preAlg = null;
			if (!Util.isStrNull(preAlgClass)) {
				Class preAlgClazz;
				try {
					preAlgClazz = Class.forName(preAlgClass);
					preAlg = (IAlgorithm) preAlgClazz.newInstance();
				} catch (Exception e) {
					preAlg = null;
				}
				
			}

			Class algClazz = Class.forName(algClass);
			IAlgorithm alg = (IAlgorithm) algClazz.newInstance();

			alg.setPreAlg(preAlg);

			alg.initialization(input, k);

			alg.generateDominatingSet();

			Set<List<String>> dsSet = alg.getDominatingSetSet();
			
			// int dsSetLen = dsSet.size();

			Class judgeClazz = Class.forName(judgeClass);
			IJudge judge = (IJudge) judgeClazz.newInstance();
			
//			for(List<String> ds: dsSet){
//				judge.isDS(g, ds, complementaryDS);
//			}
			// Assert.assertEquals(1, dsSetLen);
			//
			// Object[] dsArr = (dsSet.toArray());
			// @SuppressWarnings("unchecked")
			// List<String> ds = (List<String>) dsArr[0];
			// int dsLen = ds.size();
			// Assert.assertEquals(k, dsLen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public void setJudge(IJudge judge) {
	// this.judge = judge;
	// }
	//
	// public void SetAlg(IAlgorithm alg) {
	// this.alg = alg;
	// }

}
