package edu.cdu.fpt.alg;

import java.util.List;
import java.util.Set;

import edu.cdu.fpt.io.IInput;
import edu.uci.ics.jung.graph.Graph;

/**
 * This interface work for extension of new algorithms
 * 
 * @author : Kai
 * 
 */

public interface IAlgorithm {
//	
	/**
	 * generate dominating set
	 */
	public void generateDominatingSet();

	/**
	 * return the array of dominating set
	 * 
	 * @return
	 */
	public Set<List<String>> getDominatingSetSet();

	/**
	 * After getting source of data, set some initial variables
	 */
	public void initialization(IInput input, int k) ;

	/**
	 * @param g
	 * @param vertexDegreeList
	 * @return
	 */
	public void computing( Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) ;
	/**
	 * 
	 * @param preAlg
	 */
	public void setPreAlg(IAlgorithm preAlg);
//	public IAlgorithm getPreAlg();
	
	public State getState();
}
