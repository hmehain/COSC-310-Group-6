package Topics;

import ConvoBot.Message;

public abstract class Topic {
	
	public boolean currentTopic;

	public void startTopic() {
		currentTopic = true;
	};
	
	public void endTopic() {
		currentTopic = false;
	};
}
