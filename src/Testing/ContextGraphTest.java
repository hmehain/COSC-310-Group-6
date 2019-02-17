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
	
		// creates initial graph
		ContextGraph g = new ContextGraph("characteristicsList.txt", "solutionsList.txt");
		ArrayList<Characteristic> characteristicsList = g.getCharacteristicsList();
		ArrayList<Solution> solutionsList = g.getSolutionsList();
		
		// prints string representation of graph
		System.out.print("\n" + g.toString());
		
		System.out.println("\n\nTest incrementing nodes");
		
		// increments characteristic
		g.incrementCharacteristic(characteristicsList.get(0), 6);
		g.incrementCharacteristic(characteristicsList.get(1));
		g.incrementCharacteristic(characteristicsList.get(2), 7.1);
		g.incrementCharacteristic(characteristicsList.get(3), 2.9);
		g.incrementCharacteristic(characteristicsList.get(4), 8);
		
		System.out.print("\n" + g.toString());
		
		// gets top solution from graph (solution with highest weight)
		Solution topSolution = g.getTopSolution();
		System.out.println("\nReturned solution: " + topSolution.toString());
			
		// gets array of all solutions sorted by weight (highest - lowest)
		Solution[] topSolutions = g.getTopSolutionsArray();
			
		System.out.println("Top solutions");
		for (Solution s : topSolutions) {
			System.out.println("\t" + s.toString());
		}
		
		
		
		
		System.out.println("\n\nTest enabling/disabling nodes and edges");
		System.out.println("Characteristic nodes 2 and 5 disabled, solution nodes 2, 9, and 10 disabled");
		System.out.println("edges ch1->s2, ch5->s8, ch5->s10 disabled");
		
		g.setNodeEnabled(characteristicsList.get(1), false);
		g.setNodeEnabled(characteristicsList.get(4), false);
		
		g.setNodeEnabled(solutionsList.get(1), false);
		g.setNodeEnabled(solutionsList.get(8), false);
		g.setNodeEnabled(solutionsList.get(9), false);
		
		g.setEdgeEnabled(characteristicsList.get(0), solutionsList.get(2), false);
		g.setEdgeEnabled(characteristicsList.get(4), solutionsList.get(7), false);
		g.setEdgeEnabled(characteristicsList.get(4), solutionsList.get(9), false);
		
		System.out.print("\n\n" + g.toString());
		
		
		
		// gets top solution from graph (solution with highest weight)
		topSolution = g.getTopSolution();
		System.out.println("\nReturned solution: " + topSolution.toString());
		
		// gets array of all solutions sorted by weight (highest - lowest)
		topSolutions = g.getTopSolutionsArray();
		
		System.out.println("Top solutions");
		for (Solution s : topSolutions) {
			System.out.println("\t" + s.toString());
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
