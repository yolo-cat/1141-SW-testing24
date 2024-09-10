package exam119_2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DateTransformationTest {
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		System.out.println("Start Testing");
//	}
//
//	@BeforeEach
//	void setup() {
//		System.out.println("testing each test case");
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//		System.out.println("End of the testing");
//	}

	@Test
	void test01() {
		String y = "2021/07/06";
		Exception exception = assertThrows(Exception.class, () -> DateTransformation.transformDate(y));
		assertEquals("Error Year", exception.getMessage());
	}

	@Test
	void test02() {

		String y = "2020/00/06";
		Exception exception = assertThrows(Exception.class, () -> DateTransformation.transformDate(y));
		assertEquals("Error Mouth", exception.getMessage());
	}

	@Test
	void test03() {
		String y = "2000/02/29";
		try {
			assertEquals(new String("02/29/2000"), DateTransformation.transformDate(y));
		} catch (Exception e) {
		}
	}

	@Test
	void test04() {
		String y = "2020/04/31";
		Exception exception = assertThrows(Exception.class, () -> DateTransformation.transformDate(y));
		assertEquals("Error Day", exception.getMessage());
	}

	@Test
	void test05() {
		String y = "2003/07/29";
		try {
			assertEquals("07/29/2003", DateTransformation.transformDate(y));
		} catch (Exception e) {
		}
	}

	@Test
	void test06() {
		String y = "1879/07/06";
		Exception exception = assertThrows(Exception.class, () -> DateTransformation.transformDate(y));
		assertEquals("Error Year", exception.getMessage());
	}
}