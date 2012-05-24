package ex01_13;

public class PrintfImprovedFibonacci {

	static final int START_INDEX = 9;
	static final int FINAL_INDEX = 1;
	/**
	 * @param args
	 * 偶数要素に'*'を付けて、フィボナッチ数列の最初の方の要素を表示する
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;

		// System.out.println(START_INDEX + ": " + lo);
		System.out.printf("%d: %2d%n", START_INDEX, lo);
		for (int i = START_INDEX - 1; i >= FINAL_INDEX; i--)
		{
			if (hi % 2 == 0)
			{
				mark = " *";
			}
			else
			{
				mark = "";
			}
			// System.out.println(i + ": " + hi + mark);
			System.out.printf("%d: %2d%s%n", i, hi, mark);
			hi = lo + hi;
			lo = hi - lo;
		}

	}

}
