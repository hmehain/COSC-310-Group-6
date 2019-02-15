package ConvoBot;

import java.util.PriorityQueue;

public class Node implements Comparable<Node> {
	
	// currently no getter/setter methods (except for weight), implement later?
	Subject subject;
	PriorityQueue<Edge> edgesToNode;
	PriorityQueue<Edge> edgesFromNode;
	
	private Double weight;
	
	// creates a new node with no to/from edges
	public Node(Subject subject) {
		this.subject = subject;	
		this.setWeight(0.0);
		
		edgesToNode = new PriorityQueue<>();
		edgesFromNode = new PriorityQueue<>();
	}
	
	
	public Double getWeight() {
		return this.weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
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
		for (Edge e : edgesToNode) {
			weightSum += e.getWeightOut();
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