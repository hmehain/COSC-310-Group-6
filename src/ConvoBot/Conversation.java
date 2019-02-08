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
		Message message = new Message();
		while (discussionOn) {
			message.output();
			message.input();
		}
		
	}
	
	public void endConversation() {
		discussionOn = false;
	}
}
