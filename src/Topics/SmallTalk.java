package Topics;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ConvoBot.Conversation;
import ConvoBot.Patient;
import ConvoBot.PrintMessage;

public class SmallTalk extends Topic {

	static String[] messages = { "Thank you! What do you do for work or school?", "And how old are you?",
			"Are you male or female?", "Thank you! That's all I needed. How are you feeling today?" };
	
	public static int age;
	String gender = null;

	public static void startTopic(String name, String input, int count) {

		PrintMessage.messageFromBot("**********Starting SmallTalk**********");

		input = input.toLowerCase();
		String output = null;
		Scanner in = new Scanner(System.in);

		if (count == 0) {
			// User just passed through smalltalk and accepted getting asked more questions.
			PrintMessage.messageFromBot(messages[count]);
			input = in.nextLine();
			count++;
			startTopic(name, input, count);

		} else if (count == 1) { // First small talk round, asking about occupation
			System.out.println("Count = 1");

			// Case 1: Patient responds with an "I". Ex. I work at _____, I go to _____
			Pattern p1 = Pattern.compile("(.*)(i[^'m])(.*)"); // . --> matches any character * --> Occurs zero or more times.
															// Patient responds with I
			Matcher m1 = p1.matcher(input); // Checks input for pattern p1

			// Case 2: Patient responds with an "I'm a". Ex. I'm a student, I'm a doctor,
			// ect.
			Pattern p2 = Pattern.compile("(.*)(i'm a)(.*)");
			Matcher m2 = p2.matcher(input);

			// Case 3: Patient responds with "I am a"
			Pattern p3 = Pattern.compile("(.*)(i am a)(.*)"); // m3 pattern not working
			Matcher m3 = p3.matcher(input);

			if (m1.find()) {
				if(m3.find()) {
					output = "You say that you are a " + m3.group(3) + ". That sounds interesting. " + messages[count];
					String occupation = m3.group(3);
				}else {
					output = "You say you " + m1.group(3) + ". Thank you for telling me! " + messages[count];
					String temp = m1.group(3);
					String[] tempWords = temp.split(" ");
					if (tempWords.length >= 3) {
						String occupation = tempWords[2]; // it would be in the form of "am a blank" so the occupation
															// should be the third word
					} else {
						String occupation = tempWords[tempWords.length - 1]; // assume its the last one if its less than
																				// three words long.
					}
				}

			} else if (m2.find()) {// Grammatically the output of patterns two and three can be the same
				output = "You say that you are a" + m2.group(3) + ". That sounds interesting. " + messages[count];
				String occupation = m2.group(3);
				
			}

			// Set Occupation in the patient class somehow?
			PrintMessage.messageFromBot(output);
			input = in.nextLine();
			count++;
			startTopic(name, input, count); // Calls the messageRules method again with an incremented count

		} else if (count == 2) { // Get users age. Assume the user responds using integers
			System.out.println("Count = 2");

			Pattern p1 = Pattern.compile("(.*)(\\d+\\d+)(.*)"); // This is only outputting the last digit.
			Matcher m1 = p1.matcher(input);
			if (m1.find()) {
				age = (Integer.parseInt(m1.group(2))); // So we can set age in the patient class
				output = "So you're " + m1.group(2) + ". Thank you! " + messages[count];
			}else {
				System.out.println("I'm sorry I didn't catch that. Could you tell me your age using integers?");
				input = in.nextLine();
				age = Integer.parseInt(input);
				output = "So you're " + age + ". Thank you! " + messages[count];
			}
			PrintMessage.messageFromBot(output);
			input = in.nextLine();
			count++;
			startTopic(name, input, count);

		} else if (count == 3) { // Find users gender
			System.out.println("Count = 3");

			ArrayList<String> maleList = new ArrayList<String>();
			maleList.add("male");
			maleList.add("guy");
			maleList.add("dude");
			maleList.add("man");

			ArrayList<String> femaleList = new ArrayList<String>();
			femaleList.add("female");
			femaleList.add("girl");
			femaleList.add("woman");
			femaleList.add("chick");

			// Synonyms male = new Synonyms(maleList);
			// Synonyms female = new Synonyms(femaleList);

			// Case 1: I am a (man/woman) or (guy/girl)
			Pattern p1 = Pattern.compile("(.*)(i[^'m] am a)(.*)");
			Matcher m1 = p1.matcher(input);

			// Case 2 : I'm a (guy/girl)/(male/female)
			Pattern p2 = Pattern.compile("(.*)(i'm a)(.*)");
			Matcher m2 = p2.matcher(input);

			// Case 3: only responds with male/female/guy/girl
			String[] words = input.split(" ");
			String gender = null;
			if (m1.find() || m2.find()) { // This pattern recognition isnt working super well
				gender = m1.group(3);
			} else if (words.length == 1) { // If its only a one word response assume the word is the gender.
				gender = input;
			} else {
				output = "I'm sorry I didnt understand you, would you be able to tell me your gender again? Answer with either a 'Guy' or 'Girl'.";
				PrintMessage.messageFromBot(output);
				input = in.nextLine();
				gender = input;
				System.out.println(gender);
			}

			if (maleList.contains(gender.toLowerCase())) {
				// set gender in patient object, don't know how to change shit from different
				// packages
				gender = "guy";
				output = "So you're a " + gender + "!" + messages[count];
			} else if (femaleList.contains(gender.toLowerCase())) {
				gender = "girl";
				output = "So you're a " + gender + "!" + messages[count];
			} else {
				output = "I'm sorry I didnt understand you, would you be able to tell me your gender again? Answer with either a 'male' or 'female'.";
				input = in.nextLine();
				gender = input;
			}
			
			PrintMessage.messageFromBot(output); // This last message isn't being printed.
			PrintMessage.messageFromBot("current topic: " + ++currentTopic);

		} else { // Count is not an acceptable value
			PrintMessage.messageFromBot("Error, Invalid count value");
			return;
		}

	}
	

}
