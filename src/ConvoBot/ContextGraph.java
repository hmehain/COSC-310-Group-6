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
		    	PriorityQueue<Edge> patientSolutions;
		    	for (String s: Arrays.asList(sections[2])) {
		    		
		    	}
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
