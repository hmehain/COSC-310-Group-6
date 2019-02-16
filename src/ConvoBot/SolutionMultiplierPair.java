package ConvoBot;

public class SolutionMultiplierPair {
	
	/**
	 * Pair of solution+multiplier
	 * Used by characteristics
	 */
	
	Solution solution;
	double multiplier;
	
	SolutionMultiplierPair(Solution solution) {
		this(solution, 1);
	}
	SolutionMultiplierPair(Solution solution, double multiplier) {
		setSolution(solution);
		setMultiplier(multiplier);
	}
	
	Solution getSolution() {
		return solution;
	}
	double getMultiplier() {
		return multiplier;
	}
	
	void setSolution(Solution solution) {
		this.solution = solution;
	}
	void setMultiplier(double multiplier2) {
		this.multiplier = multiplier2;
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