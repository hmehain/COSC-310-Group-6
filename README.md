# COSC-310-Group-6
Assignment for COSC 310 Group 6 Assignment 2

# Summary
We have built a therapy chat bot. It asks questions about the user, and uses the responses to provide advice.

We identified five problems common among university students-depression, anxiety, anger, stress, and insomnia-and built a weighted graph with edges between a Patient object and each problem. Based on user responses, edge weight to specific problems will be increased or decreased. These edge weights trickle down to graph edges between each problem and possible advice. Finally, based on these edge weights, the bot will provide advice to the user.
