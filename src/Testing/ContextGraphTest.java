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
	}
	
//	@Test
//	public void testGetSolutionNodes() {
//		
//	}
//	
//	@Test
//	public void testGetPatientCharacteristics()
//
//	@Test
//	public void testConstructor() {
//	}
	
}
