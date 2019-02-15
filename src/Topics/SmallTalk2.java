package Topics;

public class SmallTalk2 extends Topic {
	
	String[] messages = {"Thank you! What do you do for work or school *?",
			"And how old are you?",
			"Are you male or female?",
			"Thank you *! That's all I needed. How are you feeling today?"};

	public static void startTopic() {
		System.out.println("**********Starting SmallTalk**********");
		currentTopic++;
	}



}
