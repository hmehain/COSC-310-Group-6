package Topics;

import java.util.ArrayList;
import java.util.Scanner;


public abstract class Topic {
	
	public boolean currentTopic;

	public void startTopic() {
		currentTopic = true;
	};
	
	public void endTopic() {
		currentTopic = false;
	};
	
	/**
	 * Produces what the chatbot says
	 */
	public void output(String s) {
		System.out.println("\n" + s);
	}
	/**
	 * Interprets what the user says
	 */
	public String input() {
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}
	
	public ArrayList<String> extractKeywords() {
		ArrayList<String> keywords = new ArrayList<String>();
		
		return keywords;
	}
}
