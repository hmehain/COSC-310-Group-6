package ConvoBot;

import Topics.*;

public class Conversation {

	Patient patient;
	public static boolean startNextTopic = false;
	ContextGraph contextGraph;

	public Conversation() {
		Patient p = new Patient();
		Topic.currentTopic = 0;
		while (Topic.currentTopic < 5) {
			//System.out.println("////current Topic: " + Topic.currentTopic);
			switch (Topic.currentTopic) {
			case 0:
				p.setName(Greetings.startTopic());
				break;
			case 1:
				SmallTalk.startTopic(p.getName(), "null", 0);
				break;
			case 2:
				Discussion d = new Discussion();
				d.startTopic();
				break;
			case 3:
				Advice.startTopic();
				break;
			case 4:
				Goodbye.startTopic(p.getName());
				break;
			}

		}
		System.out.println("*********Ending Conversation*******");
	}
}