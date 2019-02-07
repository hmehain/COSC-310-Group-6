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

	HashMap<String, Characteristic> characteristicNodes;
	HashMap<String, Solution> solutionNodes;
	PriorityQueue<Edge> patientCharacteristics;
	
	/**
	 * Creates initial graph by reading in info from characteristics.txt file
	 */
	
	public void createGraph() {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("characteristics.txt")));){
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] sections = line.split("-");
		        // sections[0] is the name, sections[1] is the synonymns, and sections[2] is the patientSolutions
		        String name = sections[0];
		        ArrayList<String> synonyms = (ArrayList<String>) Arrays.asList(sections[1]);
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
	
	public boolean updateGraph() {
		
		return true;
	}
}
