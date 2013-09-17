package edu.cdu.fpt.alg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import edu.cdu.fpt.util.ListSizeComparator;
import edu.cdu.fpt.util.LogUtil;
import edu.uci.ics.jung.graph.Graph;


public class AlgorithmHillClimbing extends AbstractAlgorithm {
	

	/**
	 * @param minDSLen
	 */
	protected final void postComputing(State state) {
		List<String> ds= state.getDs();
		int minDSLen = ds.size();
		int k= getK();
		if (minDSLen <= k) {
			Logger log = LogUtil.getLogger(AlgorithmHillClimbing.class);
			log.info("By using 'HILL CLIMBING WITH GREEDY', it could find solutions whose size is less than or equal to parameter k ("+k+"). (the mininum size is " + minDSLen +")");

		}
	}

	@Override
	protected final void preComputing(IAlgorithm ag, Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {
		ag.computing(g, vertexDegreeList);
	
	}
	/**
	 * @param g
	 * @param vertexDegreeList
	 * @return
	 */
	@Override
	public final  void computing(Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {

		
		postGreedy(g);
	}

	/**
	 * @param g
	 * @param state
	 * @return
	 */
	private void postGreedy(Graph<String, Integer> g) {
		State state = getState();
		Set<List<String>> dominatingSetSet = new TreeSet<List<String>>(new ListSizeComparator());
		

		List<String> certainDS = state.getDs();
		dominatingSetSet.add(certainDS);

		int certainDSLen = certainDS.size();

		if (certainDSLen <= getK()) {
			dominatingSetSet.add(certainDS);
		}

		// take use of hill climbing idea based on the generated dominating set
		int minDSLen = certainDSLen;

		List<String> complementaryDS = state.getComplementaryDs();
		int complementaryDSLen = complementaryDS.size();

		for (int i = 0; i < complementaryDSLen; i++) {
			List<String> certainDSBk = new ArrayList<String>(certainDSLen);

			List<String> complementaryDSBk = new ArrayList<String>(
					complementaryDSLen);
			certainDSBk.addAll(certainDS);
			complementaryDSBk.addAll(complementaryDS);
			String temp = complementaryDS.get(i);
			certainDSBk.add(temp);
			complementaryDSBk.remove(temp);
			Collection<String> neighborsOfI = g.getNeighbors(temp);
			Iterator<String> nIIt = neighborsOfI.iterator();
			while (nIIt.hasNext()) {

				String nIItNextStr = nIIt.next();
				if (certainDSBk.contains(nIItNextStr)) {
					complementaryDSBk.add(nIItNextStr);
					certainDSBk.remove(nIItNextStr);
				}

			}

			// verify certainDSBk is a dominating set
			boolean isDS = AlgorithmUtil.isDS(g, certainDSBk, complementaryDSBk);
			if (isDS) {
				int certainDSBkLen = certainDSBk.size();
				if (minDSLen > certainDSBkLen) {

					minDSLen = certainDSBkLen;

//					dominatingSetSet.add(certainDSBk);
					List<String> existingDs = state.getDs();
					List<String> existingCplDs = state.getComplementaryDs();
					state.setPrevDs(existingDs);
					state.setPrevCplDs(existingCplDs);
					state.setDs(certainDSBk);
					state.setComplementaryDs(complementaryDSBk);
				
				}
			}
		}
		dominatingSetSet.add(state.getDs());
		setDominatingSetSet(dominatingSetSet);
		setState(state);
	}

	


}
