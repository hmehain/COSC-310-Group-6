package Topics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.*;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;

import ConvoBot.Characteristic;
import ConvoBot.ContextGraph;
import ConvoBot.PrintMessage;
import ConvoBot.Synonyms;

public class Discussion extends Topic {

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
	ContextGraph contextGraph;

	public Discussion(ContextGraph contextGraph) {
		this.contextGraph = contextGraph;
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
		extractKeywords(input);
		input = input.toLowerCase();
		String output = null;
		
		
		// Case 1: User responds with I
		Pattern p1 = Pattern.compile("(.*)(i[^'m])(.*)");
		Matcher m1 = p1.matcher(input);
		
		// Case 2: User responds with I'm
		Pattern p2 = Pattern.compile("(.*)(i'm)(.*)");
		Matcher m2 = p2.matcher(input);
		
		// Case 3: user responds with my
		Pattern p3 = Pattern.compile("(.*)(my)(.*)");
		Matcher m3 = p3.matcher(input);
		
		// Case 4: User responds with everybody
		Pattern p2 = Pattern.compile("(.*)(everybody)(.*)");
		Matcher m2 = p2.matcher(input);
		
		// Case 5: Single word response
		int inputLength = input.split(" ").length;
		
		if(m1.find()) {
			
			//Case 1.1: I am
			Pattern p1_1 = Pattern.compile("(.*)(i am)(.*)");
			Matcher m1_1 = p1_1.matcher(input);
			if(m1_1.find()) {
				// Case 1.1.1: I am feeling BLANK
				Pattern p1_1_1 = Pattern.compile("(.*)(feeling)(.*)");
				Matcher m1_1_1 = p1_1_1.matcher(input);
				
				if(m1_1_1.find()) {
					String keyword = m1_1_1.group(3);
					// Check synonyms for keyword
					
					if(/*isHappy(keyword)*/) {
						output = "I'm glad to hear that you're feeling good!";
						//Change graph weight
					}else if(/*isSad(keyword)*/) {
						//Change graph weights
						output = "I'm sorry to hear that.";
					}
					
					// Case 1.1.2 I am BLANK. EX I am worried/sad
				}else {
					String keyword = m1_1.group(3);
					if(/*isHappy(keyword)*/) {
						output = "I'm glad to hear that you're feeling good!";
						//Change graph weight
					}else if(/*isSad(keyword)*/) {
						//Change graph weights
						output = "I'm sorry to hear that.";
					}
				}
			}else { //Case 1.2: I BLANK. EX I hate BLANK, I love BLANK, I procrastinate, ECT
				String[] words = m1.group(3).split(" "); // Splits the String into individual words
				String keyword = words[0];
				String sentence = null;
				if(words.length > 1) {
					words = ArrayUtils.removeElement(words, 0); // Removes the keyword from the sentence
					sentence = Arrays.toString(words); // Recombines into a partial sentence
				}else {
					sentence = keyword;
				}
				
				if(/*isPositve(keyword)*/) {
					output = "Its good to hear that you like " + sentence + ". " + "How does " + sentence + " make you feel?";
				}else if(/*isNegative(keyword)*/) {
					output = "I'm sorry to hear that " + sentence + " makes you feel like that. Why do you think it makes you feel that way?";
				}else if(keyword.equals("proctastinate")) {
					output = sampleMessages[2];
				}else {
					output = noMessages[(int) (Math.random()*noMessages.length)];
				}
				
			}
			
		}else if(m2.matches()) { // Case 2
			// Case 2.1 I'm feeling BLANK
			// Case 2.2 I'm worried about BLANK
			// Case 2.3 I'm BLANK
		}else if(/*Case 3 */) {
			// Case 3.1: My job is BLANK
			// Case 3.2: My friends 
		}else if(/*Case 4*/) {
			// Case 4.1: Everyone hates BLANK
			// Case 4.2: Everyone Loves BLANK
			// Case 4.3: Everyone says BLANK
		}else if(inputLength == 1) {
			// Check synonyms list
		}else {
			output = noMessages[(int) (Math.random()*noMessages.length)];
		}
		
		
		
		
		
		
		
		
		
/*		Pattern p2 = Pattern.compile("(.*)(everybody)(.*)");
		Matcher m2 = p2.matcher(input);
		Pattern p3 = Pattern.compile("(.*)(my)(.*)");
		Matcher m3 = p3.matcher(input);
		if (m1.find()) 
			output = "You say you " + m1.group(3) + ".";
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
			output = noMessages[(int) (Math.random()*noMessages.length)];*/
		
		return output;
	
	}
	
	public void extractKeywords(String input) {
		for (Characteristic c: contextGraph.getCharacteristicsList()) {
			if (input.contains(c.getName())) {
				// What method do we use when we find a keyword or synonym in the patient message?
			}
			for(String s: c.getSynonymns()) {
				if (input.contains(s)) {
					// Same as before 
				}
			}
		}
	}

}
