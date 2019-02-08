package ConvoBot;

import java.util.ArrayList;
import java.util.Scanner;

public class Message {

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
