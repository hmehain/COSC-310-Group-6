package Topics;

import ConvoBot.PrintMessage;

public class Goodbye2 extends Topic {

	String[] messages = {"Goodbye 0"};


	public static void startTopic(String name) {
		System.out.println("*******Starting Goodbye******");
		if (name != null) 
			PrintMessage.messageFromBot("Goodbye " + name);
		else
			PrintMessage.messageFromBot("Goodbye.");
		currentTopic++;
		
	}
	


}
