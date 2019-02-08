package ConvoBot;

public class Patient {
	
	int age;
	String name;
	ContextGraph patientGraph;
	
	
	public Patient() {
		patientGraph = new ContextGraph("characteristics.txt");
		
	}
	
	
	
}
