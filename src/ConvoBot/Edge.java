package ConvoBot;

public class Edge implements Comparable<Edge> {

	private Node startNode;
	private Node endNode;
	private Double weightIn; 
	private Double weightOut;
	private double multiplier; 
	
	//Enable/Disable nodes
	private boolean enabled; //Set it to either true (enabled) or false (disabled)
		
	//getter and setter for enabled
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		// if setEnabled == true, update edge weights 
		this.enabled = enabled;
		
		/*
		if (this.enabled == true) {
			updateEdgeWeight();
		}
		*/
	}
	
	/*
	public Edge(Node sN, Node eN) {
		this(sN, eN, 0.0, 1);
	}
	public Edge(Node sN, Node eN, double m) {
		this(sN, eN, 0.0, m);
	}
	
	public Edge(Node sN, Node eN, Double w) {
		this(sN, eN, w, 1);
	}
	*/
	public Edge(Node sN, Node eN, Double w, double m) {
		this.startNode = sN;
		this.endNode = eN;
		this.weightIn = w;
		this.multiplier = m;
		this.weightOut = w*m;
		this.enabled = true;
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
		// if node == disabled, weightIn = 0
		/*
		if (startNode.isEnabled() == false) {
			startNode.setWeight(0.0);
		}
		else {
			setWeightIn(startNode.getWeight());
		}
		
		*/
		setWeightIn(startNode.getWeight());
		setWeightOut(this.weightIn * this.multiplier);
		;
		if (!endNode.getSubjectName().equals("centerNode")) {
			this.endNode.collectWeights();
		}
	}
	
	@Override
	public String toString() {
		return "[startNode=" + startNode.getSubjectName() + ",endNode=" + endNode.getSubjectName() + ";weight=" + weightIn + ",multiplier=" + multiplier + "]";
	}
	
	// getter/setter methods for multiplier
	public double getMultiplier() {
		return this.multiplier;
	}
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

	// start/end nodes can not be changed
	public Node getEndNode() {
		return this.endNode;
	}
	public Node getStartNode() {
		return this.startNode;
	}
	
	private void setStartNode(Node startNode) {
		this.startNode = startNode;
	}
	
	private void setEndNode(Node endNode) {
		this.endNode = endNode;
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
