package ConvoBot;

import java.util.Scanner;

/**
 * This class makes it easier to interact with the interface for the Android app.
 * @author Owner
 *
 */
public class PrintMessage {

	public static Scanner in = new Scanner(System.in);
	
	/**
	 * This reads the text the user types into the console when responding to the bot.
	 * @param input from user
	 */
	
	public static String messageFromUser() {
		return in.nextLine();
	}
	/**
	 * This sends the message from the bot to print out to the console.
	 * @param output from bot
	 */
	public static void messageFromBot(String output) {
		System.out.println(output);
	}

}
