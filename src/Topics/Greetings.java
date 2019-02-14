package Topics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ConvoBot.PrintMessage;

public class Greetings extends Topic {

	static String[] messages = { "Hi! My name is Thebo (THErapy BOt). What's your name?",
			"Welcome 0, can I ask you a few questions before we start?",
			"Is there something you would like to talk about?"};

	public static void startTopic() {
		
		System.out.println("*************Starting Greetings topic************");
		
		PrintMessage.messageFromBot(messages[0]); // First message to user
		String input = PrintMessage.messageFromUser(); // First message from user
		
		// First it needs to analyze the response.

		Pattern[] pattern = { Pattern.compile("(My name is )(.*)"), Pattern.compile("(I'm )(.*)"),
				Pattern.compile("(\\w*\b?(\\w*)?)") };

		Matcher[] matcher = new Matcher[pattern.length];
		for (int i = 0; i < matcher.length; i++) {
			matcher[i] = pattern[i].matcher(input);
		}
		// 2. The sentences need to be checked for expected responses, such as "My name
		// is, I'm, ect. Also needs to check for answers with just a name.
		String name = null;
		while (name == null) {
			if (matcher[0].find())
				name = matcher[0].group(2);
			else if (matcher[1].find())
				name = matcher[1].group(2);
			else if (matcher[2].find())
				name = input.trim();

			/*
			 * if - No valid response System.out.println("Sorry I didn't understand your
			 * question. Could you please answer with just your name?) Scanner scanner = new
			 * Scanner(System.in); String name = scanner.nextLine(); patient.name("name);
			 */

			if (name == null) {
				PrintMessage.messageFromBot(
						"Sorry I didn't understand your question. Could you please answer with just your name?");
				input = PrintMessage.messageFromUser();
			}
		}

		/*
		 * Now we have the name we can output the next part of the message string.
		 * 
		 * String output = messages[1]; if (output.contains("0")) { output.replace(0,
		 * name); System.out.println(output); }
		 * 
		 */
		if (messages[1].contains("0"))
			PrintMessage.messageFromBot(messages[1].replace("0", name));
		
		// If user responds with no, ask them what they would like to talk about
		if (PrintMessage.messageFromUser().toLowerCase().contains("no")) {
			PrintMessage.messageFromBot(messages[2]);
			// go to Discussion directly and skip small talk
			currentTopic = 2;
		} else {
			currentTopic++;
		}
	}
}
