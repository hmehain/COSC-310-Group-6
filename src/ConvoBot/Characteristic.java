package ConvoBot;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Characteristic {
	
	String name;
	Integer weight;
	ArrayList<String> synonyms;
	PriorityQueue<Edge> patientSolutions;
	
	public Characteristic(String name, ArrayList<String> synonyms, PriorityQueue<Edge> patientSolutions) {
		this.name = name;
		weight = 0;
		this.synonyms = synonyms;
		this.patientSolutions = patientSolutions;
		
	}
}
