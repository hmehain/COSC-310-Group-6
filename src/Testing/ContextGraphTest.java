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
