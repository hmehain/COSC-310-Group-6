package ConvoBot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ContextGraph {

	public HashMap<String, Characteristic> characteristicNodes;
	public HashMap<String, Solution> solutionNodes;
	public PriorityQueue<Edge> patientCharacteristics;
	
	/**
	 * Constructor: creates initial graph by reading in info from specified file
	 */
	
	public ContextGraph(String filename) {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));){
		    String line;
		    characteristicNodes = new HashMap<String, Characteristic>();
		    solutionNodes = new HashMap<String, Solution>();
		    patientCharacteristics = new PriorityQueue<Edge>();
		    while ((line = br.readLine()) != null) {
		        String[] sections = line.split("-");
		        // sections[0] is the name, sections[1] is the synonymns, and sections[2] is the patientSolutions
		        String name = sections[0];
		        ArrayList<String> synonyms = new ArrayList(Arrays.asList(sections[1]));
		    	PriorityQueue<Edge> patientSolutions = new PriorityQueue<Edge>();
		    	String[] solutionNames = sections[2].split(",");
		    	for (String s: Arrays.asList(solutionNames)) {
		    		Edge edge = new Edge(name, s, 1); // startNode, endNode, multiplier
		    		patientSolutions.add(edge);
		    		Solution solution = new Solution(s); // solution name, weight
		    		if (!solutionNodes.containsKey(s))
		    			solutionNodes.put(s, solution);
		    	}
		    	Characteristic characteristic = new Characteristic(name, synonyms, patientSolutions);
		    	characteristicNodes.put(name, characteristic);
		    	
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Getters
	
	public HashMap<String, Characteristic> getCharacteristicNodes(){
		return characteristicNodes;
	}
	
	public HashMap<String, Solution> getSolutionNodes(){
		return solutionNodes;
	}
	
	public PriorityQueue<Edge> getPatientCharacteristics(){
		return patientCharacteristics;
	}
	
	public boolean updateGraph() {
		return true;
	}
	/**
	 * Overwriting toString() method to use for testing in ContextGraphTest
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("Characteristics");
		for (Characteristic c: characteristicNodes.values()) {
			s.append("\n\tName: " + c.name + "\n\tSynonymns: ");
			for(String s1: c.synonyms) 
				s.append(s1 + ", ");
			s.append("\n\tSolutions: ");
			for(Edge e: c.patientSolutions)
				s.append(e.endNode + ", ");
		}
		s.append("\n\nSolutions");
		for (Solution sol: solutionNodes.values())
			s.append("\n\t" + sol.name + " weight: " + sol.weight);
		s.append("\n\nEdges");
		for (Edge e: patientCharacteristics) {
			s.append("\n\t" + e.endNode);
		}
		
		return s.toString();
	}
	
}
