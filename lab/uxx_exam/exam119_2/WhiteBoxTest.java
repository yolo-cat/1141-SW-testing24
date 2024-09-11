package exam119_2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WhiteBoxTest {

	@Test
	void test() {
		WhiteBox wb = new WhiteBox();
		assertEquals(18, wb.add(6, -6, 6));
	}
	
	@Test	
	void test2() {
		WhiteBox wb = new WhiteBox();
		assertEquals(0, wb.add(-6, 6, 0));
	}

	@Test	
	void test3() {
		WhiteBox wb = new WhiteBox();
		assertEquals(6, wb.add(6, 6, -6));
	}

	@Test	
	void test4() {
		WhiteBox wb = new WhiteBox();
		assertEquals(18, wb.add(6, 6, 6));
	}
	
//	@Test
//	void test5() {
//		WhiteBox wb = new WhiteBox();
//		assertEquals(18, wb.add2(6, 6));
//	}
	
//	@Test
//	void test7() {
//		WhiteBox wb = new WhiteBox();
//		assertEquals(18, wb.add2(6, 6));
//	}
//

	@Test
	void test6a() {
		WhiteBox wb = new WhiteBox();
		assertEquals(6, wb.add2(6, 6));
	}
	@Test
	void test6b() {
		WhiteBox wb = new WhiteBox();
		assertEquals(0, wb.add2(6, -6));
	}
//	@Test
//	void test6c() {
//		WhiteBox wb = new WhiteBox();
//		assertEquals(0, wb.add2(-6, 6));
//	}
//	@Test
//	void test6d() {
//		WhiteBox wb = new WhiteBox();
//		assertEquals(-12, wb.add2(-6, -6));
//	}
	

}
