package ConvoBot;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class Synonyms {
	// Might make these into a 2d array with weights
	private List<String> greetingList = new ArrayList<String>();
	private List<String> happyList = new ArrayList<String>();
	private List<String> sadList = new ArrayList<String>();

	// ----- List constructors ----- //
	public void greetingConstructor() {
		getGreetingList().add("hello");
		getGreetingList().add("hi");
		getGreetingList().add("hey");
		getGreetingList().add("howdy");
		getGreetingList().add("salutations");
		Collections.sort(getGreetingList(), String.CASE_INSENSITIVE_ORDER); // Sorts the list alphabetically
	}

	public void happyConstructor() {
		getHappyList().add("happy");
		getHappyList().add("good");
		getHappyList().add("glad");
		getHappyList().add("amazing");
		getHappyList().add("content");
		getHappyList().add("jolly");
		Collections.sort(getHappyList(), String.CASE_INSENSITIVE_ORDER);
	}

	public void sadConstructor() {
		getSadList().add("sad");
		getSadList().add("unhappy");
		getSadList().add("depressed");
		getSadList().add("dejected");
		getSadList().add("regretful");
		getSadList().add("miserable");
		getSadList().add("glum");
		getSadList().add("blue");
		getSadList().add("dismal");
		getSadList().add("wretched");
		getSadList().add("downcast");
		Collections.sort(getHappyList(), String.CASE_INSENSITIVE_ORDER);

	}

	// ----- Getters and Setters ----- //
	public List<String> getGreetingList() {
		return greetingList;
	}

	public void setGreetingList(List<String> greetingList) {
		this.greetingList = greetingList;
	}

	public List<String> getHappyList() {
		return happyList;
	}

	public void setHappyList(List<String> happyList) {
		this.happyList = happyList;
	}

	public List<String> getSadList() {
		return sadList;
	}

	public void setSadList(List<String> sadList) {
		this.sadList = sadList;
	}


	// ----- Synonym Check ----- //
	public boolean isGreeting(String word) {
		word = word.toLowerCase();
		if (getGreetingList().contains(word))
			return true;
		else
			return false;
	}

	public boolean isHappy(String word) {
		word = word.toLowerCase();
		if (getHappyList().contains(word))
			return true;
		else
			return false;
	}

	public boolean isSad(String word) {
		word = word.toLowerCase();
		if (getSadList().contains(word))
			return true;
		else
			return false;
	}
	

}
