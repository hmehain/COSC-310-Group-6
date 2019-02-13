package Testing;
import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.junit.Test;
import ConvoBot.Characteristic;
import ConvoBot.ContextGraph;
import ConvoBot.Edge;
import ConvoBot.Solution;

public class ContextGraphTest{
	
	ContextGraph graph = new ContextGraph("GraphTest.txt");
	
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
	
}
