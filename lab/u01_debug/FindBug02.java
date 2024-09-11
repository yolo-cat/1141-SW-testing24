/**
 * Author: Sammy Chen, FCU
 */
package fcu.findbugs;

public class FindBug02 {
	public static void main(String[] args) {

		String greeting = "Hello World";

		greeting.replace("World", "SQA");

		System.out.println(greeting);

	}

}
