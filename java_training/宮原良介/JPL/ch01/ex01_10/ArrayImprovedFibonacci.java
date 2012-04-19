package ex01_10;

public class ArrayImprovedFibonacci {

	static final int START_INDEX = 9;
	static final int FINAL_INDEX = 1;

	static class Fibonacci
	{
		public int value;
		public boolean is_even;
	}
	/**
	 * @param args
	 * 偶数要素に'*'を付けて、フィボナッチ数列の最初の方の要素を表示する
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		// String mark;

		Fibonacci[] result = new Fibonacci[100];

		for (int i = 0; i < 100; i++)
		{
			result[i] = new Fibonacci();
		}

		// System.out.println(START_INDEX + ": " + lo);
		result[START_INDEX].value = lo;
		if (result[START_INDEX].value % 2 == 0)
		{
			result[START_INDEX].is_even = true;
		}
		for (int i = START_INDEX - 1; i >= FINAL_INDEX; i--)
		{
			result[i].value = hi;
			if (result[i].value % 2 == 0)
			{
				// mark = " *";
				result[i].is_even = true;
			}
			else
			{
				// mark = "";
				;
			}
			// System.out.println(i + ": " + hi + mark);
			hi = lo + hi;
			lo = hi - lo;
		}

		for (int i = START_INDEX; i >= FINAL_INDEX; i--)
		{
			if (result[i].is_even == true)
			{
				System.out.println(result[i].value + " *");
			}
			else
			{
				System.out.println(result[i].value);
			}
		}

	}

}
