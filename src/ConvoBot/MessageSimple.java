package ConvoBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageSimple {
	
	
	public ArrayList<String> messages;
	public int conversationRounds;
	public static String[] myMessages = {"Let's discuss further why your 0",
			"Earlier you said your 0",
			"Does that have anything to do with the fact that your 0"};
	public static String[] noMessages = {"Please go on.",
			"That's very interesting",
			"I see."};
	
	
	public MessageSimple() {
		conversationRounds = 0;	
		messages = new ArrayList<String>();
	}

	/**
	 * The bot can converse with messages from the user involving keywords I, everybody, and my
	 * @param input The message from the user
	 * @return The bot response
	 */
	public String discussionRules(String input) {
		
		String output = null;
		Pattern p1 = Pattern.compile("(.*)(I[^'m])(.*)");
		Matcher m1 = p1.matcher(input);
		Pattern p2 = Pattern.compile("(.*)(everybody)(.*)");
		Matcher m2 = p2.matcher(input);
		Pattern p3 = Pattern.compile("(.*)(my)(.*)");
		Matcher m3 = p3.matcher(input);
		System.out.println(m1.groupCount());
		if (m1.find()) 
			output = "You say you" + m1.group(3) + ".";
		if (m2.find()) 
			output = "Who in particular are you thinking of?";
		if (m3.lookingAt()) {
			String saying = myMessages[(int) (Math.random()*myMessages.length)];
			saying = saying.replaceAll(".*0", m3.group(3));
			if (output == null)
				output = saying;
			else
				messages.add(saying);
		}
		if (output == null)
			output = noMessages[(int) (Math.random()*noMessages.length)];
		
		return output;
	
	}
	
	public static void main(String[] args) {
		MessageSimple m = new MessageSimple();
		System.out.println("How are you doing today?");
        while (m.conversationRounds < 30) {
        	Scanner in = new Scanner(System.in);
    		String input = in.nextLine();
    		System.out.println(m.discussionRules(input));
    		m.conversationRounds++;
        }
        System.out.println("Goodbye");
        
	}

}
