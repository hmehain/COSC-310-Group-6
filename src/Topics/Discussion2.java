package Topics;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ConvoBot.PrintMessage;

public class Discussion2 extends Topic {

	String[] sampleMessages = {"That’s too bad! Do you like being busy?",
			"Can you do anything to have more free time? Good time management skills can often free up leisure time!",
			"There are many tools to help with procrastination! Have you tried keeping a journal or setting personal deadlines?",
			"That's too bad, did it used to help?",
			"It can be hard to start something, but it only takes an average of 66 days to form a new habit!",
			"Good idea! Procrastination can also be a symptom of anxiety or depression. Do you feel anxious or depressed?",
			"You’re not alone in that feeling *. One in five university students suffer from anxiety or depression. Thankfully there is many resources for students to reach out to on most campuses. Have you looked into your schools student resources?",
			"It never hurts to talk to someone, but if you’re sure there are things you can do for yourself that can help with anxiety. Is your sleep schedule regular?",
			"I see. How about exercise? Do you workout or play any sports."};
	
	public ArrayList<String> messages;
	public int conversationRounds;
	public static String[] myMessages = {"Let's discuss further why your 0",
			"Earlier you said your 0",
			"Does that have anything to do with the fact that your 0"};
	public static String[] noMessages = {"Please go on.",
			"That's very interesting",
			"I see."};

	public Discussion2() {
		conversationRounds = 0;	
		messages = new ArrayList<String>();
	}
	
	public void startTopic() {
		// TODO Auto-generated method stub
		PrintMessage.messageFromBot("******Starting Discussion*****");
		String input;
		for (int i = 0; i < 20; i++) {
			input = PrintMessage.messageFromUser();
			PrintMessage.messageFromBot(discussionRules(input));
		}
		currentTopic++;
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
	
	public String extractKeywords(String input) {
		
		return null;
	}

}
