package ConvoBot;

import java.util.PriorityQueue;

public class Node implements Comparable<Node> {
	
	private Subject subject;
	private PriorityQueue<Edge> edgesToNode;
	private PriorityQueue<Edge> edgesFromNode;
	
	private Double weight;
	
	//Enable or Disable nodes
	private boolean enabled; //Set it to either true (enabled) or false (disabled)
	
	//getter and setter for enabled
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		// if enabled set to true, update edges away from node
	
		 if (!this.getSubjectName().equals("centerNode")) { 
			this.enabled = enabled;
			for (Edge e : edgesFromNode) {
				e.updateEdgeWeight();
			}
		}
	}
	
	// creates a new node with no to/from edges
	public Node(Subject subject) {
		this.subject = subject;	
		this.setWeight(0.0);
		
		edgesToNode = new PriorityQueue<>();
		edgesFromNode = new PriorityQueue<>();
		this.setEnabled(true);
	}
	
	//getter and setter methods for weight, subject, edgesToNode, and edgesFromNode
	public Double getWeight() {
		return this.weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public Subject getSubject() {
		return this.subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public PriorityQueue<Edge> getEdgesToNode() {
		return edgesToNode;
	}


	public void setEdgesToNode(PriorityQueue<Edge> edgesToNode) {
		this.edgesToNode = edgesToNode;
	}


	public PriorityQueue<Edge> getEdgesFromNode() {
		return edgesFromNode;
	}


	public void setEdgesFromNode(PriorityQueue<Edge> edgesFromNode) {
		this.edgesFromNode = edgesFromNode;
	}

	// increments weight by some amount, should only be called if node subject is a characteristic
	public void incrementWeight() {
		this.incrementWeight(1.0);
	}
	
	public void incrementWeight(Double weight) {
		this.setWeight(this.weight + weight);
		
		if (!this.getSubjectName().equals("centerNode")) {
			for (Edge e : edgesFromNode) {
				e.updateEdgeWeight();
			}
		}
	}
	
	// collects total weights (out) of edges leading to node, used to 'fill buckets' 
	public void collectWeights() {
		Double weightSum = 0.0;
		if (this.isEnabled()) {
			for (Edge e : edgesToNode) {
				// if edge == enabled only
				//if (e.isEnabled() == true) {
					weightSum += e.getWeightOut();
				//}
			}
		}
		this.setWeight(weightSum);
		
		if (!this.getSubjectName().equals("centerNode")) {
			for (Edge e : edgesFromNode) {
				e.updateEdgeWeight();
			}
		}
	}
	
	
	public void addEdgeToNode(Edge e) {
		edgesToNode.add(e);
	}
	public void addEdgeFromNode(Edge e) {
		edgesFromNode.add(e);
	}
	
	
	public String getSubjectName() {
		return subject.getName();
	}
	
	@Override
	public String toString() {
		return "[subject=" + subject.getName() + ";edges to node=" + edgesToNode.toString() + ";edges from node=" + edgesFromNode.toString() + "]";
	}

	public boolean equals(Node ch) {
		if (ch.subject.equals(this.subject)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Used when returning top solutions from graph
	 */
	@Override
	public int compareTo(Node n) {
		if (this.weight == n.weight)
			return 0;
		else
			return this.weight < n.weight ? 1: -1;
	}
	
}