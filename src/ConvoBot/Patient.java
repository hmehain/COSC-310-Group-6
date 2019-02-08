package ConvoBot;

public class Patient {
	
	int age;
	String name;
	ContextGraph patientGraph;
	boolean discussionOn;
	
	public Patient() {
		patientGraph = new ContextGraph("characteristics.txt");
		discussionOn = true;
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
