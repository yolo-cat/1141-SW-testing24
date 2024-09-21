package fcu.junitdemo;

/*
 * 
 * This is a buggy program, use the debug concept to debug this program
 */
public class Sin {

	static double stop = 0.001;

	public static void main(String[] args) {
		Sin s = new Sin();
		System.out.println("The value of sin(30')");
		System.out.println(s.sin(Math.PI / 6.0));
	}

	/*
	 * @param x: �|��
	 * 
	 * �o�ӵ{���z�L���Ǯi�}�@���� x �� sine �ȡC�ѩ���Ǯi�}���i�H�@���i��L�a�ȡA�ڭ̦b�{���������]�w�������C�@�}�l�ڭ����L�] n
	 * ���A�M��C���[ inc �ӳ��A���۾F���⦸�t�Z�p�� stop �ɴN����A�_�h�N�~���U�h�C
	 */
	public double sin(double x) {

		int n = 2; // �@�}�l�]���Ӽ�; starting loop number
		int inc = 3; // �C�@���h�[ inc �ӭӼ�; we add inc loop each time
		double stop = 0.000000000001; // �������; the stopping criteria

		double s1 = sin(x, n);
		n = n + inc;
		double s2 = sin(x, n);

		while (Math.abs(s2 - s1) >= stop) {
			s1 = s2;
			n = n + inc;
			s2 = sin(x, n);
		}

		return s2;
	}

	/*
	 * @param n: number of items, for example, x-x^3/3!+x^5/5!, then n=3
	 * 
	 * �o�ӰƵ{���̭������֪��ܼơA�i�H�ϥ� breakpoint ���{�����b�o�ӰƵ{���A�A�ϥ� variable view
	 * �Ӭݰ���L�{���o���ܼƪ��ܤơC
	 */
	double sin(double x, int n) {
		double v = x;
		int postive = 1;
		for (int i = 1; i < 2 * n; i = i + 2) {

			// (Math.pow(x, i) / factorial(i)) �o�ӭȦ��I�����A�A�i�H�� expression view
			// �Ӭݤ@�U�o�ӭȬO���O�A���檺
			v = v + postive * (Math.pow(x, i) / factorial(i));
			postive = postive * -1;
		}

		return v;
	}

	/*
	 * �ƾǪ�����; factorial(3) = 3*2*1
	 */
	double factorial(double s) {
		double r = 1;

		for (int i = 1; i <= s; i++) {
			r = r * i;
		}
		return r;
	}

}