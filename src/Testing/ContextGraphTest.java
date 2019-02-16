package Testing;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.junit.Test;
import ConvoBot.Characteristic;
import ConvoBot.ContextGraph;
import ConvoBot.Edge;
import ConvoBot.Solution;


public class ContextGraphTest{
	
	
	/**
	 * Explanation of (almost) all objects and classes in contextGraphREADME.txt
	 */
	
	
	public static void main(String[] args) {
	
		// creates arrayLists for characteristics and solutions read from text file, to be sent to graph
		ArrayList<Characteristic> characteristicsList = new ArrayList<>();
		ArrayList<Solution> solutionsList = new ArrayList<>();
		
		// reads characteristics and solutions from file
		createLists("characteristicsList.txt", "solutionsList.txt", characteristicsList, solutionsList);
		
		// creates initial graph
		ContextGraph g = new ContextGraph(characteristicsList, solutionsList);
		
		// prints string representation of graph
		System.out.print("\n\n" + g.toString());
		
		// increments characteristic
		g.incrementCharacteristic(characteristicsList.get(4));
		g.incrementCharacteristic(characteristicsList.get(2), 2.0);
		g.incrementCharacteristic(characteristicsList.get(0), 5);
		
		System.out.print("\n\n" + g.toString());
		
		g.setNodeEnabled(characteristicsList.get(4), false);
		g.setEdgeEnabled(characteristicsList.get(2), solutionsList.get(3), true);
		g.setNodeEnabled(characteristicsList.get(0), false);
		g.setEdgeEnabled(characteristicsList.get(1), solutionsList.get(3), false);
		g.setNodeEnabled(solutionsList.get(2), false);
		
		System.out.print("\n\n" + g.toString());
		
		
		
		// gets top solution from graph (solution with highest weight)
		Solution topSolution = g.getTopSolution();
		System.out.println("\nReturned solution: " + topSolution.toString());
		
		// gets array of all solutions sorted by weight (highest - lowest)
		Solution[] topSolutions = g.getTopSolutionsArray();
		
		System.out.println("Top solutions");
		for (Solution s : topSolutions) {
			System.out.println("\t" + s.toString());
		}
		
	}
	
	/**
	 * Reads potential solutions from file and populates solutionsList, then potential characteristics from file to characteristicsList
	 * 	(order is important, solutions added to characteristics only when they exist in solutions list)
	 * 
	 * @param characteristicsFilename = name of file where characteristics list stored
	 * 		all characteristics must be stored as "characteristic1;synonym1,synonym2;treatment1-m1,treatment2-m2,treatment3-m3;"
	 * 			m1 = multiplier (stored as int)
	 * 			only adds solutions already in solution list, searches by name
	 * @param solutionsFilename = name of file where solutions list stored
	 * 		all solutions stored as "treatment1;\ntreatment2;\n..."
	 * 			based on simple solution class (name is only attribute)
	 * @return true if no errors encountered
	 */
	private static boolean createLists(String characteristicsFilename, String solutionsFilename, ArrayList<Characteristic> characteristicsList, ArrayList<Solution> solutionsList) {
		
		try (BufferedReader cbr = new BufferedReader(new InputStreamReader(new FileInputStream(characteristicsFilename)));
				BufferedReader sbr = new BufferedReader(new InputStreamReader(new FileInputStream(solutionsFilename)));) {
		    
			String line;
		    
		    while ((line = sbr.readLine()) != null) {
		    	String[] sections = line.split(";");
		    	
		    	Solution s = new Solution(sections[0]);
		    	solutionsList.add(s);
		    }
		    
		    
		    // bad run time, small data set assumed (for demo)
		    while ((line = cbr.readLine()) != null) {
		        String[] sections = line.split(";");
		        
		        // sections[0] is the name, sections[1] is the synonyms, and sections[2] is the patientSolutions
		        String name = sections[0];
		        
		        ArrayList<String> synonyms = new ArrayList<String>(Arrays.asList(sections[1].split(",")));		        
		        ArrayList<String> solutions = new ArrayList<String>(Arrays.asList(sections[2].split(",")));
		       
		        Characteristic ch = new Characteristic(name, synonyms);
		        
		        for (String sm : solutions) {
		        	int separaterIndex = sm.indexOf('-');
		        	String solution = sm.substring(0, separaterIndex);
		        	double multiplier = Double.parseDouble(sm.substring(separaterIndex + 1));
		        	
		        	for (Solution s : solutionsList) {
		        		if (solution.equals(s.getName())) {
		        			ch.addSolution(s, multiplier);
		        		}
		        	}
		        	
		        }
		        
		        characteristicsList.add(ch);
		    }
		    
		    return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	@Test
	public void testGetCharacteristicNodes() {
		HashMap<String, Characteristic> characteristicNodes = graph.getCharacteristicNodes();
		//List<String, List> charNamesAndSynonyms = new ArrayList<String,List>(); // Figure out how to make a 2d array list 
		ArrayList<String> charNames = new ArrayList<String>();
		for(Characteristic c: characteristicNodes.values()) {
			charNames.add(c.getName());
			System.out.println(c.getName() + " " + c.getSynonyms());
			// I'm not sure what the edge solutions are or how to access them? PriorityQueue<Edge> patientSolutions
		}
		assertTrue(charNames.get(0).equals("depression"));
		assertTrue(charNames.get(1).equals("substance abuse"));
	}
	
	@Test
	public void testGetSolutionNodes() {
		HashMap<String, Solution> solutionNodes = graph.getSolutionNodes();
		ArrayList<String> solutions = new ArrayList<String>();
		for(Solution s: solutionNodes.values()) {
			solutions.add(s.getName());
		}
		System.out.println(solutions.get(1));
		assertTrue(solutions.get(0).equals("therapy"));
		// If there are multiple therapy options this only tests the first one. Need to change to a list of lists solutions somehow.
	}
//	
//	@Test
//	public void testGetPatientCharacteristics()
//
//	@Test
//	public void testConstructor() {
//	}
	*/
}
