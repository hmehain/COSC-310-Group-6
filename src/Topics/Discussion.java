package Topics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import org.apache.*;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;

import ConvoBot.Characteristic;
import ConvoBot.ContextGraph;
import ConvoBot.PrintMessage;
import ConvoBot.Synonyms;

public class Discussion extends Topic {

	String[] sampleMessages = { "That’s too bad! Do you like being busy?",
			"Can you do anything to have more free time? Good time management skills can often free up leisure time!",
			"There are many tools to help with procrastination! Have you tried keeping a journal or setting personal deadlines?",
			"That's too bad, did it used to help?",
			"It can be hard to start something, but it only takes an average of 66 days to form a new habit!",
			"Good idea! Procrastination can also be a symptom of anxiety or depression. Do you feel anxious or depressed?",
			"You’re not alone in that feeling. One in five university students suffer from anxiety or depression. Thankfully there is many resources for students to reach out to on most campuses. Have you looked into your schools student resources?",
			"It never hurts to talk to someone, but if you’re sure there are things you can do for yourself that can help with anxiety. Is your sleep schedule regular?",
			"I see. How about exercise? Do you workout or play any sports." };

	public ArrayList<String> messages;
	public int conversationRounds;
	public static String[] myMessages = { "Let's discuss further why your 0", "Earlier you said your 0",
			"Does that have anything to do with the fact that your 0" };
	public static String[] noMessages = { "Please go on.", "That's very interesting", "I see.",
			"How does that make you feel?", "Could you please elaborate?", "Please continue.", "I understand." };
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
	 * @param input The message from the user
	 * @return The bot response
	 */
	public String discussionRules(String input) {
		extractKeywords(input);
		Scanner scanner1 = new Scanner(System.in);
		input = input.toLowerCase();
		String output = null;

		ArrayList<String> happyList = new ArrayList<String>();
		happyList.add("happy");
		happyList.add("good");
		happyList.add("well");
		happyList.add("super");
		happyList.add("awesome");
		happyList.add("great");
		happyList.add("okay");

		ArrayList<String> sadList = new ArrayList<String>();
		sadList.add("sad");
		sadList.add("miserable");
		sadList.add("upset");
		sadList.add("down");
		sadList.add("dissapointed");
		sadList.add("depressed");

		ArrayList<String> positiveList = new ArrayList<String>();
		positiveList.add("love");
		positiveList.add("adore");
		positiveList.add("like");
		positiveList.add("enjoy");

		ArrayList<String> negativeList = new ArrayList<String>();
		negativeList.add("hate");
		negativeList.add("loath");
		negativeList.add("despise");
		negativeList.add("dislike");
		negativeList.add("stressed");
		negativeList.add("tired");

		// Case 1: User responds with I
		Pattern p1 = Pattern.compile("(i\\s)(.*)");
		Matcher m1 = p1.matcher(input);

		// Case 2: User responds with I'm
		Pattern p2 = Pattern.compile("(.*)(i'm)(.*)");
		Matcher m2 = p2.matcher(input);

		// Case 3: user response contains "stress"
		Pattern p3 = Pattern.compile("(.*)(stress)(.*)");
		Matcher m3 = p3.matcher(input);

		// Case 4: User response contains "depressed"
		Pattern p4 = Pattern.compile("(.*)(depressed)(.*)");
		Matcher m4 = p4.matcher(input);

		// Case 5: User responds with yes
		Pattern p5 = Pattern.compile("(.*)(yes)(.*)");
		Matcher m5 = p5.matcher(input);

		// Case 6: User responds with no
		Pattern p6 = Pattern.compile("(.*)(no\\s)(.*)");
		Matcher m6 = p6.matcher(input);
		
		// Case 7: user response contains "Tired"
		Pattern p7 = Pattern.compile("(.*)(tired)(.*)");
		Matcher m7 = p7.matcher(input);
		
		// Case 8: User response contains "procrastinate/ing"
		Pattern p8 = Pattern.compile("(.*)(procrastinat)(.*)");
		Matcher m8 = p8.matcher(input);
		
		// Case 9: Goodbye
		Pattern p9 = Pattern.compile("(.*)(goodbye)(.*)");
		Matcher m9 = p9.matcher(input);
		
		if (m1.matches() && !m2.matches()) {
			System.out.println("Case 1");
			// Case 1.1: I am
			Pattern p1_1 = Pattern.compile("(.*)(am)(.*)");
			Matcher m1_1 = p1_1.matcher(input);

			// Case 1.2: I feel
			Pattern p1_2 = Pattern.compile("(.*)(feel)(.*)");
			Matcher m1_2 = p1_2.matcher(input);
			
			// Case 1.3: I ___ Busy ___

			if (m1_1.find()) {
				System.out.println("Case 1.1");
				// Case 1.1.1: I am feeling BLANK
				Pattern p1_1_1 = Pattern.compile("(.*)(feeling)(.*)");
				Matcher m1_1_1 = p1_1_1.matcher(input);

				if (m1_1_1.find()) {
					System.out.println("case 1.1.1");
					String keyword = m1_1_1.group(3);
					keyword = keyword.replaceAll("\\s+", "");

					if (happyList.contains(keyword.toLowerCase())) {
						output = "I'm glad to hear that you're feeling good!";
						// Change graph weight
					} else if (sadList.contains(keyword.toLowerCase())) {
						// Change graph weights
						output = "I'm sorry to hear that, what's wrong?";
					}

				} else {
					System.out.println("case 1.1.2");
					String keyword = m1_1.group(3);
					keyword = keyword.replaceAll("\\s+", "");
					System.out.println("Keyword:" + keyword);
					if (happyList.contains(keyword.toLowerCase())) {
						output = "I'm glad to hear that you feel good! Keep it up by getting into healthy habits. Do you excercise?";
						// Change graph weight
					} else if (sadList.contains(keyword.toLowerCase())) {
						// Change graph weights
						output = "I'm sorry to hear that. Why do you feel so " + keyword + "?";
					} else {
						output = noMessages[(int) (Math.random() * noMessages.length)];
					}
				}
			}else if(m1_2.find()) { // I feel
				System.out.println("Case 1.2");
				String keyword = m1_2.group(3);
				keyword = keyword.replaceAll("\\s+", "");

				if (happyList.contains(keyword.toLowerCase())) {
					output = "I'm glad to hear that you're feeling good! Keep it up by getting into healthy habits. Do you excercise?";
					// Change graph weight
				} else if (sadList.contains(keyword.toLowerCase()) ||negativeList.contains(keyword.toLowerCase())) {
					// Change graph weights
					output = "I'm sorry to hear that, what's wrong?";
				}else {
					output = noMessages[(int) (Math.random() * noMessages.length)];
				}
				
			} else { // Case 1.3: I BLANK. EX I hate BLANK, I love BLANK, I procrastinate, ECT
				System.out.println("Case 1.3");
				String[] words = m1.group(2).split(" "); // Splits the String into individual words
				String keyword = words[0];
				String sentence = null;
				if (words.length > 1) {
					words = ArrayUtils.removeElement(words, 0); // Removes the keyword from the sentence
					sentence = Arrays.toString(words); // Recombines into a partial sentence
				} else {
					sentence = null;
				}

				if (positiveList.contains(keyword.toLowerCase())) {
					output = "Its good to hear that you like " + sentence + ". " + "How does " + sentence
							+ " make you feel?";
				} else if (negativeList.contains(keyword.toLowerCase())) {
					output = "I'm sorry to hear that " + sentence
							+ " makes you feel like that. Why do you think it makes you feel that way?";
				} else if (keyword.equals("proctastinate")) {
					output = sampleMessages[2];
				} else {
					output = noMessages[(int) (Math.random() * noMessages.length)];
				}

			}

		} else if (m2.matches()) { // Case 2: User responds with I'm
			System.out.println("Case 2");
			String keyword = null;
			// Case 2.1 I'm feeling BLANK
			Pattern p2_1 = Pattern.compile("(.*)(feeling)(.*)");
			Matcher m2_1 = p2_1.matcher(input);
			// Case 2.2 I'm worried about BLANK
			Pattern p2_2 = Pattern.compile("(.*)(worried about)(.*)");
			Matcher m2_2 = p2_2.matcher(input);
			// Case 2.3 I'm BLANK

			if (m2_1.matches()) {
				System.out.println("Case 2.1");
				keyword = m2_1.group(3);
				keyword = keyword.replaceAll("\\s+", "");
				if (happyList.contains(keyword.toLowerCase())) {
					output = "I'm glad to hear that you're feeling good! What would you like to talk about today?";
					// Change graph weight
				} else if (sadList.contains(keyword.toLowerCase())) {
					// Change graph weights
					output = "I'm sorry to hear that. " + sampleMessages[6];
				} else {
					output = noMessages[(int) (Math.random() * noMessages.length)];
				}
			} else if (m2_2.matches()) {
				System.out.println("Case 2.2");
				keyword = m2_2.group(3); // worried implies this will be negative so I dont need to check the synonyms
				keyword = keyword.replaceAll("\\s+", "");
				output = "Why are you worried about " + keyword + "?";
				// Change graph weights
			} else {
				System.out.println("Case 2.3");
				keyword = m2.group(3);
				keyword = keyword.replaceAll("\\s+", "");

				if (happyList.contains(keyword.toLowerCase())) {
					System.out.println("I'm glad to hear that you're " + keyword
							+ "! Keep it up by getting into healthy habits. Do you excercise?");
					String temp = scanner1.next();
					temp = temp.toLowerCase();
					Pattern p2_3_1 = Pattern.compile("(.*)(yes)(.*)");
					Matcher m2_3_1 = p2_3_1.matcher(temp);
					Pattern p2_3_2 = Pattern.compile("(.*)(do)(.*)");
					Matcher m2_3_2 = p2_3_2.matcher(temp);

					if (m2_3_1.find() || m2_3_2.find()) {
						output = "Good for you! Excercise has been proven to improve concentration, helps you sleep better,"
								+ " and reduce anxiety and depression. What else you would like to talk about?";
					} else {
						output = "You should try it. Excercise has been proven to improve concentration, helps you sleep better,"
								+ " and reduces anxiety and depression.";
					}
					// Change graph weight
				} else if (sadList.contains(keyword.toLowerCase())) {
					// Change graph weights
					output = "I'm sorry to hear that. " + sampleMessages[7];
				} else {
					output = noMessages[(int) (Math.random() * noMessages.length)];
				}

			}
		} else if (m3.find()) { // Case 3, user response contains stressed
			System.out.println("Case 3");
			System.out.println("I'm sorry to hear that, make sure to block out time for yourself to unwind. \nRemeber, things you can't change aren't worth worrying about! Whats been stressing you out?");
			String temp = scanner1.next().toLowerCase();
			Pattern p3_1 = Pattern.compile("(.*)(school)(.*)");
			Matcher m3_1 = p3_1.matcher(temp);
			Pattern p3_2 = Pattern.compile("(.*)(work)(.*)");
			Matcher m3_2 = p3_2.matcher(temp);
			
			if(m3_1.find()) {
				output = "I'm sorry schools been so stressful lately. Hopefully it will be worth all of the hard work in the end!"
						+ "\nYou can try to free up some extra time by improving your time management skills. Procrastination can lead to more stress down the line."
						+ "\nDid you have anything else you wanted to talk about?";
			}else if(m3_2.find()){
				System.out.println("Sorry to hear that your work has been so stressful. Do you enjoy what you do?");
				scanner1.hasNextLine();
				temp = scanner1.next().toLowerCase();
				Pattern p3_3 = Pattern.compile("(.*)(yes)(.*)");
				Matcher m3_3 = p3_3.matcher(temp);
				Pattern p3_4 = Pattern.compile("(.*)(yeah)(.*)");
				Matcher m3_4 = p3_4.matcher(temp);
				if(m3_3.find() || m3_4.find()) {
					output = "Thats good, its important to enjoy what you do. Hopefully it will become less stressful soon.";
				}else {
					output = "Thats no good. Its important to enjoy what you do. Have you thought of doing something else?";
				}
			}else{
					output = "I'm sorry about that. I hope it gets easier for you soon. It really does help to find someone to talk to. I'd reccomend talking to friends or family.";				
			}
		} else if (m4.find()) { // Case 4, user response contains depressed
			System.out.println("Case 4");
			output = sampleMessages[6];
		} else if (m5.find()) { // case 5, user response contains yes
			System.out.println("Case 5");
			output = "That's good! I'm glad you're being proactive about you're problems. What else would you like to talk about?";
		} else if (m6.find()) { // Case 6, user response contains no
			System.out.println("Case 6");
			output = "I'd reccomend looking into it. Are you having any other problems?";
		} else if (m7.find()){ // Case 7, "tired"
			System.out.println("Case 7");
			System.out.println("Do you excercise? It can help with sleeping better.");
			String temp = scanner1.next().toLowerCase();
			Pattern p7_1 = Pattern.compile("(.*)(yes)(.*)");
			Matcher m7_1 = p7_1.matcher(temp);
			Pattern p7_2 = Pattern.compile("(.*)(do)(.*)");
			Matcher m7_2 = p7_2.matcher(temp);

			if (m7_1.find() || m7_2.find()) {
				System.out.println("Good for you! Excercise has been proven to improve concentration, helps you sleep better,"
						+ " and reduce anxiety and depression. Since you're still tired do you have a regular sleep schedule?");
				scanner1.nextLine();
				temp = scanner1.next().toLowerCase();
				m7_1 = p7_1.matcher(temp);

				if(m7_1.find()) {
					System.out.println("Thats good, keeping a regular sleep schedule has been proven to improve sleep quality. Make sure you keep it up! Are you going to bed early enough?"
							+ "\nThe national sleep foundation reccomends 7 - 9 hours for adults between 18 and 64. "
							+ "\nApart from that you seem to have excellent sleep habits, if you have trouble falling asleep that can be caused from stress or anxiety."
							+ "\nHave you been feeling stressed or anxious lately?");
					scanner1.nextLine();
					temp = scanner1.next().toLowerCase();
					Pattern p7_3 = Pattern.compile("(.*)(maybe)(.*)");
					Matcher m7_3 = p7_3.matcher(temp);
					m7_1 = p7_1.matcher(temp);
					if(m7_1.find() || m7_3.find()) {
						output = sampleMessages[6];
					}else {
						output = "Thats good. Just make sure you're getting enough sleep then.";
					}
				}else {
					output = "You should try sleeping at around the same time every night. It's been proven to improve the quality of sleep. Is there anything else you would like to talk about?";
				}
			} else {
				System.out.println("You should try it. Excercise has been proven to improve concentration, helps you sleep better,"
						+ " and reduces anxiety and depression. Do you have a reagular sleep schedule?");
				scanner1.nextLine();
				temp = scanner1.next().toLowerCase();
				m7_1 = p7_1.matcher(temp);
				if(m7_1.find()) {
					output = "Thats good, keeping a regular sleep schedule has been proven to improve sleep quality. Make sure you keep it up!"
							+ "\n Try excercising regularly to see if you feel less tired. If that doesn't help trouble sleeping can also be caused by stress or anxiety."
							+ "\n have you been feeling stressed on axious lately?";
					scanner1.nextLine();
					temp = scanner1.next().toLowerCase();
					Pattern p7_3 = Pattern.compile("(.*)(maybe)(.*)");
					Matcher m7_3 = p7_3.matcher(temp);
					m7_1 = p7_1.matcher(temp);
					if(m7_1.find() || m7_3.find()) {
						output = sampleMessages[6];
					}else {
						output = "That's good. Just try excercise for now. Is there anything else that's been bothering you?";
					}
				}else {
					output = "I would recommend trying excercise and a regular sleep schedule. If that doesn't help trouble sleeping can be caused by stress or anxiety."
							+ "\n have you been feeling stressed or anxious lately?";
					scanner1.nextLine();
					temp = scanner1.next().toLowerCase();
					Pattern p7_3 = Pattern.compile("(.*)(maybe)(.*)");
					Matcher m7_3 = p7_3.matcher(temp);
					m7_1 = p7_1.matcher(temp);
					if(m7_1.find() || m7_3.find()) {
						output = sampleMessages[6];
					}else {
						output = "That's good. Try my reccomedations and see if they help. Is there anything else you wanted to talk about?";
					}
				}
			}
		} else if(m8.find()){ // Case 8
			System.out.println("Case 8");
			System.out.println(sampleMessages[2]);
			String temp = scanner1.next().toLowerCase();
			Pattern p8_1 = Pattern.compile("(.*)(yes)(.*)");
			Matcher m8_1 = p8_1.matcher(temp);
			Pattern p8_2 = Pattern.compile("(.*)(have\\s)(.*)");
			Matcher m8_2 = p8_2.matcher(temp);
			
			if(m8_1.find() || m8_2.find()) {
				System.out.println("It's good that you're trying to improve yourself! Procrastination can also be a symptom of anxiety or depression. Do you feel anxious or depressed?");
				scanner1.nextLine();
				temp = scanner1.next().toLowerCase();
				Pattern p8_3 = Pattern.compile("(.*)(maybe)(.*)");
				Matcher m8_3 = p8_3.matcher(temp);
				m8_1 = p8_1.matcher(temp);
				if(m8_1.find() || m8_3.find()) {
					output = sampleMessages[6];
				}else {
					output = "Thats good. I'd reccomend trying out a journal. It can be hard to start something, but it only takes an average of 66 days to form a new habit!. Is there anything else you wante to talk about?";
				}
			}else {
				output = "I'd reccomend trying it. It can be hard to start something, but it only takes an average of 66 days to form a new habit! Is there anything else you wante to talk about?";
			}
			
		}else if(m9.find()){
			System.out.println("Goodbye");
			System.exit(0);
			
		} else {
			System.out.println("No case match");
			output = noMessages[(int) (Math.random() * noMessages.length)];
		}
		
		return output;

	}

	public void extractKeywords(String input) {
		for (Characteristic c : contextGraph.getCharacteristicsList()) {
			if (input.contains(c.getName())) {
				// What method do we use when we find a keyword or synonym in the patient
				// message?
			}
			for (String s : c.getSynonymns()) {
				if (input.contains(s)) {
					// Same as before
				}
			}
		}
	}

}
