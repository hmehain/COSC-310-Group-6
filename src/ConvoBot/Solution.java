package ConvoBot;

public class Solution extends Subject {

	// currently solutions only have a name, read in from file
	public Solution(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	
	public String getName() {
		return super.getName();
	}
	public void setName(String name) {
		super.setName(name);
	}
	
	public boolean equals(Solution s) {
		if (this.getName().equals(s.getName())) {
			return true;
		}
		return false;
	}
}
