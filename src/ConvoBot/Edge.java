package ConvoBot;

public class Edge {

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
	
	public Edge(String sN, String eN, Integer w, Integer m) {
		this(sN, eN, m);
		weight = w;
	}
	
}
