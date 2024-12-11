package exam119_2;

public class Grade {

	public static int analyze_grade(int grade[][], double weight[]) throws Exception {
		for (int[] g: grade) {
			if (g.length != weight.length) {
				throw new Exception("Error Length");
			}
		}

		double[] sumg = new double[weight.length];
		for (int i = 0; i < grade.length; i++) {
			for (int j = 0; j < grade[i].length; j++) {
				if (grade[i][j] > 100 || grade[i][j] < 0) {
					throw new Exception("Error Grade");
				}
				sumg[i] += grade[i][j] * weight[i];
			}
		}
		
		double sum = 0;
		for (double w: weight) {
			sum += w;
		}
		if (sum != 1) {
			throw new Exception("Error Weight");
		}
		
		int ans = 0;		
		for (double f: sumg) {
			if (f >= 60 ) {
				ans++;
			}
		}
		return ans;
	}
}
