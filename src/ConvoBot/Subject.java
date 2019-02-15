package ConvoBot;

public class Subject {
	
	/**
	 * Currently only attribute is name
	 * 
	 * Inherited by both Characteristic/Solution
	 * centerNode in graph is subject
	 * all nodes have a subject, used to avoid issues when casting, could be replaced by generics
	 */
	
	String name;
	
	public Subject(String name) {
		setName(name);
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
