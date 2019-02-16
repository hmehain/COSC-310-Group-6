package ConvoBot;

import java.util.ArrayList;

public class Characteristic extends Subject {
	
	private ArrayList<String> synonyms;
	
	// list of solutions+multiplier pairs, created based on info read from text file
	private ArrayList<SolutionMultiplierPair> characteristicSolutions;
	
	// Constructor
	public Characteristic(String name, ArrayList<String> synonyms) {
		super(name);
		
		this.synonyms = synonyms;
		
		characteristicSolutions = new ArrayList<>();
	}


	// gets multiplier for a given solution in the characteristicSolutions list
	public double getMultiplier(Solution solution) {
		int i = getSolutionIndex(solution);
		return characteristicSolutions.get(i).getMultiplier();
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("Characteristic:");
		
		str.append("name=[");
		str.append(super.getName());
		
		str.append("];synonyms=[");
		for (String synonym : synonyms) {
			str.append(synonym);
			str.append(",");
		}
		str.append("];solutions=[");
		for (SolutionMultiplierPair cs : characteristicSolutions) {
			str.append(cs.toString());
			str.append(",");
		}
		str.append("]");
		
		return str.toString();
	}
	
	// adds a solution+multiplier pair to the characteristicSolutions list
	public void addSolution(Solution solution, double multiplier) {
		if (!solutionInCharacteristicSolutions(solution)) {
			characteristicSolutions.add(new SolutionMultiplierPair(solution, multiplier));
		}
	}
	
	// returns list of all solution+multiplier pairs from characteristicSolutions list
	public ArrayList<SolutionMultiplierPair> getSolutions() {
		return this.characteristicSolutions;
	}
	
	// checks if a solution is already in characteristicSolutions list, called when solution+multiplier being added to list
	public boolean solutionInCharacteristicSolutions(Solution solution) {
		if (getSolutionIndex(solution) >= 0) {
			return true;
		}
		return false;
	}
	
	// gets index of solution in characteristicSolutions list
	private int getSolutionIndex(Solution solution) {
		for (int i = 0; i < characteristicSolutions.size(); i++) {
			for (SolutionMultiplierPair cs : characteristicSolutions) {
				if (cs.getSolution().equals(solution)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	// characteristics equal if all attributes are equal
	public boolean equals(Characteristic ch) {
		if (this == ch) {
			return true;
		} else if (this.synonyms.equals(ch.synonyms) && this.characteristicSolutions.equals(ch.characteristicSolutions)) {
			return true;
		}
		
		return false;
	}
	
	
}
