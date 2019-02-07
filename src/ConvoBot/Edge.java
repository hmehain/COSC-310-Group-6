package ConvoBot;

public class Edge implements Comparable<Edge> {

	String startNode;
	String endNode;
	Integer weight;
	Integer multiplier;
	
	public Edge(String sN, String eN, Integer m) {
		startNode = sN;
		endNode = eN;
		weight = 0;
		multiplier = m;
	}

	@Override
	public int compareTo(Edge e) {
		if (this.weight == e.weight)
			return 0;
		else
			return this.weight > e.weight ? 1: -1;
	}
	
}
