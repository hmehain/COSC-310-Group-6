package ConvoBot;

public class SolutionMultiplierPair {
	
	/**
	 * Pair of solution+multiplier
	 * Used by characteristics
	 */
	
	Solution solution;
	int multiplier;
	
	SolutionMultiplierPair(Solution solution) {
		this(solution, 1);
	}
	SolutionMultiplierPair(Solution solution, int multiplier) {
		setSolution(solution);
		setMultiplier(multiplier);
	}
	
	Solution getSolution() {
		return solution;
	}
	int getMultiplier() {
		return multiplier;
	}
	
	void setSolution(Solution solution) {
		this.solution = solution;
	}
	void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	@Override
	public String toString() {
		return "s=[" + solution.toString() + "] m=" + multiplier;
	}
	
	public boolean equals(SolutionMultiplierPair sm) {
		if (this == sm) {
			return true;
		} else if (this.solution.equals(sm.solution) && this.multiplier == sm.multiplier) {
			return true;
		}
		
		return false;
	}
}