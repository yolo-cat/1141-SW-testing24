package exam119_2;

public class WhiteBox {

	public int add(int x, int y, int z) {
		if (x > 0 & y > 0 & z > 0) {
			return x + y + z;
			
		}
		return x + y + z;
	}

	public int add2(int x, int y) {
		if (x > 0 & y > 0) {
			return x + y;
		}
		return x + y;
	}

	public void m() {
		int x = 1;
		int y = 0;
		int z = 0;
		int p = 0;
		int r = 0;
		if (x > 0) {
			x++;
		}
		if (x > 0 & y > 0) {
			x++;
		}
		if (x > 0 & y > 0 & z > 0) {
			x++;
		}

		if (x > 0 & y > 0 & z > 0 & p > 0) {
			x++;
		}
		if (x > 0 & y > 0 & z > 0 & p > 0 & r > 0) {
			x++;
		}

	}

}
