package ConvoBot;

import Topics.*;

public class Conversation {

	Patient patient;
	static Topic[] conversationTopic = {new Greetings(), new SmallTalk(), new Discussion(), new Advice(), new Goodbye()};
	boolean discussionOn;
	
	public Conversation() {
		Patient p = new Patient();
		startConversation();
		endConversation();
		
	}
	
	public void startConversation() {
		
		for(Topic t: conversationTopic) {
			t.startTopic();
			t.endTopic();
		}
		
	}
	
	public void endConversation() {
		discussionOn = false;
	}
}
