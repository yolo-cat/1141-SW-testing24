package exam119_2;

public class DateTransformation {

	public static String transformDate(String d) throws Exception {
		String[] s = d.split("/");
		
		int[] mday = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = Integer.parseInt(s[0]);
		int month = Integer.parseInt(s[1]);
		int day = Integer.parseInt(s[2]);
		
		if (year > 2020 || year < 1970) {
			throw new Exception("Error Year");
		}
		if (month < 1 || month > 12) {
			throw new Exception("Error Mouth");
		}
		if (day < 1 || day > 31) {
			throw new Exception("Error Day");
		}
		if (year % 4 == 0) {
			mday[1] = 29;
		}
		if (day > mday[month - 1]) {
			mday[1] = 29;
			throw new Exception("Error Day");
		}
		mday[1] = 29;
		String ans = String.format("%02d/%02d/%d", month, day, year);
		return ans;
	}
}
