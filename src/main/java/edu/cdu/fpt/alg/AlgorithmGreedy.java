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


public class AlgorithmGreedy extends AbstractAlgorithm {


		
	/**
	 * @param state
	 */
	@Override
	protected final void postComputing() {
		State state = getState();
		
		List<String> certainDS = state.getDs();
		int certainDSLen = certainDS.size();

		Set<List<String>> dominatingSetSet = new TreeSet<List<String>>(new ListSizeComparator());
		if (certainDSLen <= getK()) {
			Logger log = LogUtil.getLogger(AlgorithmGreedy.class);
			log.info("By using 'GREEDY ALGORITHM', it could find solutions whose size is less than or equal to parameter k ("
					+ getK() + "). (the mininum size is " + certainDSLen + ")");
			dominatingSetSet.add(certainDS);
		}
		setDominatingSetSet(dominatingSetSet);
	}

	/**
	 * @param g
	 * @param vertexDegreeList
	 * @return
	 */
	public final void computing(Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {
		greedy(g, vertexDegreeList);
		
	}




	

	/**
	 * @param certainDS
	 * @param complementaryDS
	 * @param T
	 */
	private State greedy(Graph<String, Integer> g, List<VertexDegree> vertexDegreeList) {
		Logger log = LogUtil.getLogger(AlgorithmGreedy.class);
		List<String> certainDS = new ArrayList<String>();
		List<String> complementaryDS = new ArrayList<String>();
		List<String> T = new ArrayList<String>();
		int numOfVertex = getNumOfVertex();
		for (int j = 0; j <numOfVertex  ; j++) {
			T.add(Integer.toString(vertexDegreeList.get(numOfVertex - 1 - j)
					.getVertex()));
		}

		// idea: Take all vertices of the highest degree as an approximate
		// solution
		int index = 0;
		int step = 0;
		while (!T.isEmpty()) {
			String vertexS = T.get(index);

			certainDS.add(vertexS);
			T.remove(vertexS);

			StringBuffer infoSB = new StringBuffer();

			Collection<String> neighborsOfI = g.getNeighbors(vertexS);
			Iterator<String> nIIt = neighborsOfI.iterator();
			infoSB.append("Step " + step + ":");
			infoSB.append("remove vertex ").append(vertexS).append(" (with ")
					.append(neighborsOfI.size()).append(" neighbors ) firstly");
			infoSB.append(" and its neighbors-");
			while (nIIt.hasNext()) {

				String nIItNextStr = nIIt.next();

				if (T.contains(nIItNextStr)) {
					infoSB.append(nIItNextStr).append(",");
					T.remove(nIItNextStr);
					complementaryDS.add(nIItNextStr);
				}
			}

			index = 0;
		
			log.fine(infoSB.toString());
			step++;
		}

		State state = new State(certainDS, complementaryDS);
		setState(state);
		return state;
	}

	

}
