package ConvoBot;
import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class SynonymsTest extends Synonyms {
	
	@BeforeEach
	void constructors() {
		greetingConstructor();
		happyConstructor();
		sadConstructor();
	}
	
	@Test
	void testIsGreeting() {
		assertTrue(isGreeting("howdy"));
		assertTrue(isGreeting("howDY"));
		assertFalse(isGreeting("sad"));
	}
	
	@Test
	void testIsHappy() {
		assertTrue(isHappy("happy"));
		assertTrue(isHappy("amaZing"));
		assertFalse(isHappy("sad"));
	}
	
	@Test
	void testIsSad() {
		assertTrue(isSad("sad"));
		assertTrue(isSad("SaD"));
		assertFalse(isSad("happy"));
	}


}
