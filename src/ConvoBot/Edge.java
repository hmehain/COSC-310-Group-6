package ConvoBot;

public class Edge implements Comparable<Edge> {

	private Node startNode;
	private Node endNode;
	private Double weightIn; 
	private Double weightOut;
	private int multiplier; //doesn't make sense to have double weight, int multiplier, fix later
	
	
	// constructors, simplify later?
	public Edge(Node sN, Node eN) {
		this(sN, eN, 0.0, 1);
	}
	public Edge(Node sN, Node eN, int m) {
		this(sN, eN, 0.0, m);
	}
	public Edge(Node sN, Node eN, Double w) {
		this(sN, eN, w, 1);
	}
	public Edge(Node sN, Node eN, Double w, int m) {
		startNode = sN;
		endNode = eN;
		weightIn = w;
		multiplier = m;
		weightOut = w*m;
	}

	// getter/setter methods for weights, set methods are private
	// 	set methods should not be called from outside of class, all fields are calculated in updateEdgeWeight()
	public Double getWeightIn() {
		return this.weightIn;
	}
	private void setWeightIn(Double weight) {
		this.weightIn = weight;
	}
	
	public Double getWeightOut() {
		return weightOut;
	}
	private void setWeightOut(Double weight) {
		this.weightOut = weight;
	}
	
	// updates edge weights, weightIn = weight of start node, weightOut = weightIn * multiplier
	public void updateEdgeWeight() {
		setWeightIn(startNode.getWeight());
		setWeightOut(this.weightIn * this.multiplier);
		
		if (!endNode.getSubjectName().equals("centerNode")) {
			this.endNode.collectWeights();
		}
	}
	
	
	@Override
	public String toString() {
		return "[startNode=" + startNode.getSubjectName() + ",endNode=" + endNode.getSubjectName() + ";weight=" + weightIn + ",multiplier=" + multiplier + "]";
	}
	
	// getter/setter methods for multiplier
	public int getMultiplier() {
		return this.multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	// start/end nodes can not be changed
	public Node getEndNode() {
		return this.endNode;
	}
	public Node getStartNode() {
		return this.startNode;
	}
	
	// not currently used
	@Override
	public int compareTo(Edge e) {
		if (this.weightIn == e.weightIn)
			return 0;
		else
			return this.weightIn > e.weightIn ? 1: -1;
	}
	
}
