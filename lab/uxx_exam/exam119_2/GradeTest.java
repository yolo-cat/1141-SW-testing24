package exam119_2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Start Testing");
	}

	@BeforeEach
	void setup() {
		System.out.println("testing each test case");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("End of the testing");
	}

	@Test
	void testExp01() {
		int[][] g = new int[][] { { 100, 80, 90 }, { 0, 10, 20 } };
		double[] w = new double[] { 0.3, 0.3, 0.4, 0.5 };
		Exception exception = assertThrows(Exception.class, 
				() -> Grade.analyze_grade(g, w));
		assertEquals("Error Length", exception.getMessage());		
	}
	
	@Test
	void testExp02() {
		int[][] g = new int[][] { { 100, 80, 90 }, { 0, 10 } };
		double[] w = new double[] { 0.3, 0.3, 0.4 };
		Exception exception = assertThrows(Exception.class, 
				() -> Grade.analyze_grade(g, w));
		assertEquals("Error Length", exception.getMessage());		
	}


	@Test
	void testExp03() {
		int[][] g = new int[][] { { 101, 80, 90 }, { 0, 10, 20 } };
		double[] w = new double[] { 0.3, 0.3, 0.4 };
		Exception exception = assertThrows(Exception.class, 
				() -> Grade.analyze_grade(g, w));
		assertEquals("Error Grade", exception.getMessage());
	}

	@Test
	void testExp04() {
		int[][] g = new int[][] { { -1, 80, 90 }, { 0, 10, 20 } };
		double[] w = new double[] { 0.3, 0.3, 0.4 };
		Exception exception = assertThrows(Exception.class, 
				() -> Grade.analyze_grade(g, w));
		assertEquals("Error Grade", exception.getMessage());
	}

	@Test
	void testExp05() {
		int[][] g = new int[][] { { 100, 80, 90 }, { 0, 10, 20 } };
		double[] w = new double[] { 0.3, 0.3, 0.6 };
		Exception exception = assertThrows(Exception.class, 
				() -> Grade.analyze_grade(g, w));
		assertEquals("Error Weight", exception.getMessage());
	}

	@Test
	void test01() {
		int[][] g = new int[][] { { 100, 80, 90 }, { 0, 10, 20 } };
		double[] w = new double[] { 0.3, 0.3, 0.4 };
		try {
			assertEquals(1, Grade.analyze_grade(g, w));
		} catch (Exception e) {
		}
	}

	@Test
	void test02() {
		int[][] g = new int[][] { { 100, 80, 90 }, { 50, 80, 90 } };
		double[] w = new double[] { 0.3, 0.3, 0.4 };
		try {
			assertEquals(2, Grade.analyze_grade(g, w));
		} catch (Exception e) {
		}
	}

	@Test
	void test03() {
		int[][] g = new int[][] { { 0, 0, 0 }, { 0, 10, 20 } };
		double[] w = new double[] { 0.3, 0.3, 0.4 };
		try {
			assertEquals(0, Grade.analyze_grade(g, w));
		} catch (Exception e) {
		}
	}
}
