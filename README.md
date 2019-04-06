# COSC-310-Group-6
Assignment for COSC 310 Group 6 Assignment 2

# Summary
We have built a therapy chat bot. It asks questions about the user, and uses the responses to provide advice.

We identified five problems common among university students-depression, anxiety, anger, stress, and insomnia-and built a weighted graph with edges between a Patient object and each problem. Based on user responses, edge weight to specific problems will be increased or decreased. These edge weights trickle down to graph edges between each problem and possible advice. Finally, based on these edge weights, the bot will provide advice to the user.

Currently the bot runs in the console. Although we spent time on building an Android app, we were not able to implement it within the timeframe of this project.

# Class Breakdown

# ConvoBot Classes
1) Characteristics - list of solutions  + multiplier pairs, created based on info read from text file. Characteristics are used to change the weightings in ContextGraph.

2) ContextGraph - Weighted Graph that takes in characteristics and weights to decide on recommended therapy options.

3) Conversation - decides on what conversation rules apply to the given input turn.

4) Edge & Node - Helper classes for ContextGraph.

5) Patient - Stores information about the user such as name, age, ect.

6) PrintMessage - Helper class for app integration.

# Topics Classes
 Each of the topics classes specifies rules that the bot must follow depending on where in the conversation it is. The rules change what will be output given an input, and what Thebo can ask. 
 1) Advice - Outputs advice at the end of the chat depending on what the user answered during the chat.
 
 2) Discussion - Main chat body, this is where the ContextGraph will be passed Characteristics from.
 
 3) Goodbye - Terminates the program.
 
 4) Greetings - Starts the program and introduces Thebo.
 
 5) SmallTalk - Asks basic questions to get user info.
<<<<<<< HEAD
  

=======
>>>>>>> branch 'master' of https://github.com/hmehain/COSC-310-Group-6.git

# Class Structure


