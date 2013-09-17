package edu.cdu.fpt.alg;

import java.util.List;
import java.util.Set;

import edu.cdu.fpt.io.IInput;
import edu.uci.ics.jung.graph.Graph;

public abstract class AbstractAlgorithm implements IAlgorithm {
	private State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	private IAlgorithm preAlg;

	public IAlgorithm getPreAlg() {
		return preAlg;
	}

	public void setPreAlg(IAlgorithm preAlg) {
		this.preAlg = preAlg;
	}

	private int numOfVertex; // number of vertex
	private List<String[]> adjacencyMatrix; // adjacency matrix
	private int k; // the parameter that the size of dominatingSet should less
					// than or equal to it.
	private Set<List<String>> dominatingSetSet; // an array contain dominating
												// set

	protected void setDominatingSetSet(Set<List<String>> dominatingSetSet) {
		this.dominatingSetSet = dominatingSetSet;
	}

	@Override
	public final void initialization(IInput input, int k) {
		int numOfVertex = input.getNumOfVertex();
		List<String[]> adjacencyMatrix = input.getAdjacencyMatrix();

		initialization(numOfVertex, adjacencyMatrix, k);

		if (this.preAlg != null) {
			preAlg.initialization(input, k);
		}

	}

	public final int getNumOfVertex() {
		return numOfVertex;
	}

	public final List<String[]> getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public final int getK() {
		return k;
	}

	private void initialization(int numOfVertex,
			List<String[]> adjacencyMatrix, int k) {
		this.numOfVertex = numOfVertex;
		this.adjacencyMatrix = adjacencyMatrix;
		this.k = k;

	}

	@Override
	public final void generateDominatingSet() {
		Graph<String, Integer> g = prepareGraph();
		List<VertexDegree> vertexDegreeList = sortVertexAccordingToDegree(g);

		if(preAlg!=null){
			preComputing(preAlg, g, vertexDegreeList);

			State state = preAlg.getState();
			setState(state);
		}
		computing(g, vertexDegreeList);

		postComputing();

	}

	/**
	 * @param
	 * @param
	 */
	protected void preComputing(IAlgorithm ag, Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {

	}

	/**
	 * @param state
	 */
	protected void postComputing() {
		// Set<List<String>> dominatingSetSet = null;
		// setDominatingSetSet(dominatingSetSet);
	}

	/**
	 * @param g
	 * @param vertexDegreeList
	 * @return
	 */
	public void computing(Graph<String, Integer> g,
			List<VertexDegree> vertexDegreeList) {
	}

	/**
	 * @param g
	 * @return
	 */

	protected final List<VertexDegree> sortVertexAccordingToDegree(
			Graph<String, Integer> g) {
		List<VertexDegree> vertexDegreeList = AlgorithmUtil
				.sortVertexAccordingToDegree(g, getNumOfVertex());
		return vertexDegreeList;
	}

	/**
	 * @return
	 */

	protected final Graph<String, Integer> prepareGraph() {
		Graph<String, Integer> g = AlgorithmUtil.prepareGraph(getNumOfVertex(),
				getAdjacencyMatrix());
		return g;
	}

	@Override
	public Set<List<String>> getDominatingSetSet() {
		return dominatingSetSet;
	}
}
