package Topics;

public class Greetings extends Topic{
	
	String[] messages = {"Hi! My name is Thebo (THErapy BOt). What�s your name?",
			"Welcome *, do you mind if I ask you a few questions before we start?"};

	@Override
	public void messageRules(String input) {
		/*
		 * fill in with pseudocode
		 */
		// First it needs to analyze the response.
		// 1. Break downs into sentences.
		String[] sentences = input.split(".");
		
		
		// 2. The sentences need to be checked for expected responses, such as "My name is, I'm, ect. Also needs to check for answers with just a name.
		/* for ( each sentence ){ 
		 * 		if (sentence contains response){
		 * 			split into words. words =  temp.split(" "); splits on space
		 * 			name = word after detected response
		 * 			patient.name("name"); - Sets patient name
		 * 		}
		 * 		else if ( Answered with one or two words, probably just the name.)
		 * 			patient.name("input");
		 * 		}else{ - No valid response
		 * 			System.out.println("Sorry I didn't understand your question. Could you please answer with just your name?)
		 * 			Scanner scanner = new Scanner(System.in);
    				String name = scanner.nextLine();
    				patient.name("name);
		 * 		}
		 * Now we have the name we can output the next part of the message string.
		 * 
		 String output = messages[2];
			 if (output.contains("*")) {
				 output.replace(*, name);
				 System.out.println(output);
			 }
		
	}*/

	

}
}
