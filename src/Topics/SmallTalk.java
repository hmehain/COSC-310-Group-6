package Topics;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ConvoBot.MessageSimple;

public class SmallTalk extends Topic {

	String[] messages = { "Thank you! What do you do for work or school 0?", "And how old are you?",
			"Are you male or female?", "Thank you 0! That's all I needed. How are you feeling today?" };

	@Override
	public void messageRules(String input, int count) { // I want to use recursion to keep track of what output to use.
		// TODO Auto-generated method stub
		int count = count;
		input = input.toLowerCase();
		String output = null;
		Scanner in = new Scanner(System.in);

		if (count == 0) { 
			// User just passed through smalltalk and accepted getting asked more questions.
			System.out.println(messages[count]);
			input = in.nextLine();
			count++;
			messageRules(input, count);
			
		} else if (count == 1) { // First small talk round
			
			// Case 1: Patient responds with an "I". Ex. I work at _____, I go to _____
			Pattern p1 = Pattern.compile("(.*)(i)(.*)"); // . --> matches any character * --> Occurs zero or more times. Patient responds with I
			Matcher m1 = p1.matcher(input); // Checks input for pattern p1

			// Case 2: Patient responds with an "I'm a". Ex. I'm a student, I'm a doctor, ect.
			Pattern p2 = Pattern.compile("(.*)(i'm a)(.*)");
			Matcher m2 = p2.matcher(input);

			// Case 3: Patient responds with "I am a"
			Pattern p3 = Pattern.compile("(.*)(i am a)(.*)");
			Matcher m3 = p3.matcher(input);

			if (m1.find())
				output = "You say you " + m1.group(3) + ". Thank you for telling me! " + messages[count];
			else if (m2.find() || m3.find()) // Gramerically the output of patterns two and three can be the same
				output = "You say that you are a " + m2.group(3) + ". That sounds interesting." + messages[count];
			
			System.out.println(output);
			input = in.nextLine();
			count++;
			messageRules(input, count); // Calls the messageRules method again with an incremented count

		} else if (count == 2) { // Get users age. Assume the user responds using integers
			
			Pattern p1 = Pattern.compile("(.*)(\d+)(.*)"); // \d+ should mean one or more digits but its giving me an error.
			Matcher m1 = p1.matcher(input);
			output = "Oh, you're " + m1.group(2) + ". Thanks you! " + messages[count];
			System.out.println(output);
			input = in.nextLine();
			count++;
			messageRules(input, count);
			
		} else if(count ==3) { // Find users gender
			
			
			
			//Case 1: I am a (man/woman) or (guy/girl)
			Pattern p1 = Pattern.compile("(.*)(i am a)(.*)");
			Matcher m1 = p3.matcher(input);
			
			//Case 2 : I'm a (guy/girl)/(male/female)
			Pattern p2 = Pattern.compile("(.*)(i'm a)(.*)");
			Matcher m2 = p2.matcher(input);
			
			//Case 3: only responds with male/female/guy/girl
			// Input would just be the gender.
			//Maybe have the string split on spaces? if its only one word assume its the gender.
			
			String[] words = input.split(" ");
			if (words.length == 1)
				String gender = input;
			else if(m1.find() || m2.find()) {
				String gender = m1.group(3);
			}
			
			// Now check the gender against known synonyms for male and female. Ill need to redo the synonym class for this.
			
			currentTopic++;
			
			
			
		} else { // Count is not an acceptable value
			System.out.println("Error, Invalid count value");
			return;
		}

	}

	/*
	 * String output = null; Pattern p1 = Pattern.compile("(.*)(I[^'m])(.*)");
	 * Matcher m1 = p1.matcher(input); Pattern p2 =
	 * Pattern.compile("(.*)(everybody)(.*)"); Matcher m2 = p2.matcher(input);
	 * Pattern p3 = Pattern.compile("(.*)(my)(.*)"); Matcher m3 = p3.matcher(input);
	 * System.out.println(m1.groupCount()); if (m1.find()) output = "You say you" +
	 * m1.group(3) + "."; if (m2.find()) output =
	 * "Who in particular are you thinking of?"; if (m3.lookingAt()) { String saying
	 * = myMessages[(int) (Math.random()*myMessages.length)]; saying =
	 * saying.replaceAll(".*0", m3.group(3)); if (output == null) output = saying;
	 * else messages.add(saying); } if (output == null) output = noMessages[(int)
	 * (Math.random()*noMessages.length)];
	 * 
	 * return output;
	 */
	/*
	 * public static void main(String[] args) { MessageSimple m = new
	 * MessageSimple(); System.out.println("How are you doing today?"); while
	 * (m.conversationRounds < 30) { Scanner in = new Scanner(System.in); String
	 * input = in.nextLine(); System.out.println(m.discussionRules(input));
	 * m.conversationRounds++; } System.out.println("Goodbye");
	 * 
	 * }
	 */

}
