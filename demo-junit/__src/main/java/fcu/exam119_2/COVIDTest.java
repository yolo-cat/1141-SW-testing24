package exam119_2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class COVIDTest {

	@Test
	void test01() {
		COVID covid = new COVID();
		int[] real = new int[] { 1, 0, 1, 0 };
		int[] diagnosis = new int[] { 1, 0, 1, 0, 1 };
		Exception exception = assertThrows(Exception.class, 
				() -> covid.covid19(real, diagnosis));
		assertEquals("Length not equal", exception.getMessage());
	}

	@Test
	void test02() {
		COVID covid = new COVID();
		int[] real = new int[] { 1, 0, 1, 0 };
		int[] diagnosis = new int[] { 1, 0, 1, 0 };
		double[] ans = new double[] { 1, 1 };
		try {
			assertArrayEquals(covid.covid19(real, diagnosis), ans, 0);
		} catch (Exception e) {
		}
	}

	@Test
	void test03() {
		COVID covid = new COVID();

		int[] real = new int[] { 1, 1, 1, 1, 1 };
		int[] diagnosis = new int[] { 1, 0, 1, 0, 0 };
		double[] ans = new double[] { 0.4, 1 };
		try {
			assertArrayEquals(covid.covid19(real, diagnosis), ans, 0);
		} catch (Exception e) {
		}
	}

	@Test
	void test04() {
		COVID covid = new COVID();

		int[] real = new int[] { 1, 1, 1, 1, 1, 0 };
		int[] diagnosis = new int[] { 1, 1, 0, 1, 0, 1 };
		double[] ans = new double[] { 0.6, 0.75 };
		try {
			assertArrayEquals(covid.covid19(real, diagnosis), ans, 0);
		} catch (Exception e) {
		}
	}

	@Test
	void test05() {
		COVID covid = new COVID();

		int[] real = new int[] { 1, 1, 1, 1, 1, 0, 0 };
		int[] diagnosis = new int[] { 0, 0, 0, 0, 0, 0 };
		double[] ans = new double[] { 0, 0 };
		try {
			assertArrayEquals(covid.covid19(real, diagnosis), ans, 0);
		} catch (Exception e) {
		}
	}

	@Test
	void test06() {
		COVID covid = new COVID();

		int[] real = new int[] { 1, 1, 1, 1, 1, 0, 0 };
		int[] diagnosis = new int[] { 0, 0, 0, 0, 0, 1 };
		double[] ans = new double[] { 0, 0 };
		try {
			assertArrayEquals(covid.covid19(real, diagnosis), ans, 0);
		} catch (Exception e) {
		}
	}

}
