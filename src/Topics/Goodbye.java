package Topics;

import java.util.Scanner;

public class Goodbye extends Topic {

	String[] messages = {"Goodbye *"};

	@Override
	public void messageRules(String input) {
		// TODO Auto-generated method stub
		
		// program will exit if the user types in goodbye thebo
		input = input.toLowerCase();
		String goodbye = "goodbye thebo";
		if (input.contains(goodbye)) {
			System.out.println(messages);
			// close scanner, dont know how to do it from a different class.
		}
		
	}
	


}
