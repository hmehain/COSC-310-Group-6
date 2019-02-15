package ConvoBot;

import Topics.*;

import Topics.*;

public class Conversation {

	Patient patient;
	public static boolean startNextTopic = false;

	public Conversation() {
		Patient p = new Patient();
		Topic.currentTopic = 0;
		while (Topic.currentTopic < 5) {
			//System.out.println("////current Topic: " + Topic.currentTopic);
			switch (Topic.currentTopic) {
			case 0:
				Greetings.startTopic();
				break;
			case 1:
				SmallTalk2.startTopic();
				break;
			case 2:
				Discussion2.startTopic();
				break;
			case 3:
				Advice2.startTopic();
				break;
			case 4:
				Goodbye2.startTopic();
				break;
			}

		}
		System.out.println("*********Ending Conversation*******");
	}
}