/**
 * Author: Sammy Chen, FCU
 */
package fcu.findbugs;

public class FindBug01 {

	private static final String eName = "Mary";

	public boolean playWith(String _name) {
		if (_name == eName)
			return true;
		return false;
	}

	public static void main(String[] args) {

		FindBug01 lb1 = new FindBug01();
		boolean forever = lb1.playWith(new String("Mary"));
		System.out.println("I play with: " + forever);
	}
}