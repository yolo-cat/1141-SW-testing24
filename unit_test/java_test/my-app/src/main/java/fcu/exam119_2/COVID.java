package exam119_2;

public class COVID {

	public double[] covid19(int[] real, int[] diagnosis) throws Exception {
		double result[] = new double[2];
		int len = real.length;
		if (len != diagnosis.length)
			throw new Exception("Length not equal");

		int real_positive = 0;
		int diag_positive = 0;
		int diag_positive2 = 0;
		int real_positive2 = 0;

		for (int i = 0; i < len; i++) {
			if (real[i] == 1) { // 真陽性
				real_positive++;
				if (diagnosis[i] == 1) {
					diag_positive++;
				}
			}
			if (diagnosis[i] == 1) { // 檢查為陽性
				diag_positive2++;
				if (real[i] == 1) {
					real_positive2++;
				}
			}
		}

		// 陽性且被檢查出為陽性的比例
		double recall = real_positive == 0 ? 1 : (double) diag_positive / real_positive;
		// 檢查出為陽性且真的為陽性的比例
		double precision = diag_positive2 == 0 ? 1 : (double) real_positive2 / diag_positive2;

		assert recall <= 1.0;
		assert precision <= 1.0;
		result = new double[] { recall, precision };

		return result;
	}

}
