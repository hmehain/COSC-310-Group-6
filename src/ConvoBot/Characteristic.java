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
	// Getters and Setters
	
	public String getName() {
		return name;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
	public ArrayList<String> getSynonyms() {
		return synonyms;
	}
	
	public PriorityQueue<Edge> getSolutions() {
		return patientSolutions;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public void setSynonyms(ArrayList<String> synonyms) {
		this.synonyms = synonyms;
	}
}
