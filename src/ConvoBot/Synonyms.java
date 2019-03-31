package ConvoBot;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import edu.mit.jwi.*;


public class Synonyms {
	// Might make these into a 2d array with weights
	private List<String> greetingList = new ArrayList<String>();
	private List<String> happyList = new ArrayList<String>();
	private List<String> sadList = new ArrayList<String>();

	/**
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
	
	*/
	public void getSynonyms(IDictionary dict) {
		
		// look up first sense of the word "hello"
		IIndexWord idxWord = dict.getIndexWord("hello", POS.NOUN);
		IWordID wordID = idxWord.getWordIDs().get(0); //1st meaning
		IWord word = dict.getWord(wordID);
		ISynset synset = word.getSynset();
		// iterate over words associated with the synset
		for(IWord w : synset.getWords())
			getGreetingList.add(w.getLemma());
		
		// look up first sense of the word "happy"
		IIndexWord idxWord = dict.getIndexWord("happy", POS.ADJECTIVE);
		IWordID wordID = idxWord.getWordIDs().get(0); //1st meaning
		IWord word = dict.getWord(wordID);
		ISynset synset = word.getSynset();
		// iterate over words associated with the synset
		for(IWord w : synset.getWords())
			getHappyList.add(w.getLemma());
		
		// look up first sense of the word "sad"
		IIndexWord idxWord = dict.getIndexWord("sad", POS.ADJECTIVE);
		IWordID wordID = idxWord.getWordIDs().get(0); //1st meaning
		IWord word = dict.getWord(wordID);
		ISynset synset = word.getSynset();
		// iterate over words associated with the synset
		for(IWord w : synset.getWords())
			getSadList.add(w.getLemma());
		
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
