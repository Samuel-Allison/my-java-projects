
import static org.junit.Assert.*;

import java.lang.reflect.Modifier;

import org.junit.Test;

public class RPSTest {

	@Test
	public void testStructure() {
		assertTrue("Choice must be abstract",
				Modifier.isAbstract(Choice.class.getModifiers()));
		try {
			assertTrue(
					"Choice must have abstract method beats() with correct parameter types",
					Modifier.isAbstract(Choice.class.getDeclaredMethod("beats",
							new Class[] { Choice.class }).getModifiers()));
		} catch (NoSuchMethodException e) {
			fail("Choice must have abstract method beats() with correct parameter types");
		}
	}

	private void testTies(Choice c1, Choice c2) {
		try {
			c1.beats(c1);
			fail("Rock vs Rock should throw an exception");
		} catch (TieException e) {
		} catch (Exception e) {
			fail("Wrong exception type thrown");
		}
	}

	@Test
	public void testSameObjectsBattle() {
		Rock r1 = new Rock();
		Rock r2 = new Rock();
		Paper p1 = new Paper();
		Paper p2 = new Paper();
		Scissors s1 = new Scissors();
		Scissors s2 = new Scissors();
		testTies(r1, r2);
		testTies(r1, r1);
		testTies(s1, s2);
		testTies(s1, s1);
		testTies(p1, p2);
		testTies(p1, p1);
	}
	
	@Test 
	public void testWins() {
		Rock r1 = new Rock();
		Paper p1 = new Paper();
		Scissors s1 = new Scissors();
		assertTrue(r1.beats(s1));
		assertTrue(s1.beats(p1));
		assertTrue(p1.beats(r1));
	}
	
	@Test 
	public void testLosses() {
		Rock r1 = new Rock();
		Paper p1 = new Paper();
		Scissors s1 = new Scissors();
		assertFalse(r1.beats(p1));
		assertFalse(s1.beats(r1));
		assertFalse(p1.beats(s1));
	}

	@Test
	public void testEquals() {
		Object r1 = new Rock();
		Object r2 = new Rock();
		Object p1 = new Paper();
		Object p2 = new Paper();
		Object s1 = new Scissors();
		Object s2 = new Scissors();
		assertTrue("Two rocks are always equal",r1.equals(r1));
		assertTrue("Two rocks are always equal",r1.equals(r2));
		assertTrue("Two papers are always equal",p1.equals(p1));
		assertTrue("Two papers are always equal",p1.equals(p2));
		assertTrue("Two scissors are always equal",s1.equals(s1));
		assertTrue("Two scissors are always equal",s1.equals(s2));
	}
	

	@Test
	public void testNotEquals() {
		Object r1 = new Rock();
		Object p1 = new Paper();
		Object s1 = new Scissors();
		assertFalse("Rock does not equal paper",r1.equals(p1));
		assertFalse("Rock does not equal scissors",r1.equals(s1));
		assertFalse("Paper does not equal scissors",p1.equals(s1));
		assertFalse("Paper does not equal rock",p1.equals(r1));
		assertFalse("Scissors does not equal rock",s1.equals(r1));
		assertFalse("Scissors does not equal paper",s1.equals(p1));
		
		assertFalse("Rock does not equal null",r1.equals(null));
		assertFalse("Paper does not equal null",p1.equals(null));
		assertFalse("Scissors does not equal null",s1.equals(null));

	}

}