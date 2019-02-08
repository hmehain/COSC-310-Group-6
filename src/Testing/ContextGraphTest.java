package Testing;

import ConvoBot.ContextGraph;

public class ContextGraphTest {

	public static void main(String[] args) {
		ContextGraph g = new ContextGraph("graphTest.txt");
		System.out.println(g.toString());

	}

}
