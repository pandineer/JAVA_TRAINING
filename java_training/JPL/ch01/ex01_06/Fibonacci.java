/*
 * 練習問題1.6 p.7
 * 練習問題 1.3 で作成したプログラムを修正して、タイトルに対して名前付文字列定数を使用するように修正しなさい。
 */
package ch01.ex01_06;

public class Fibonacci {

	/**
	 * Define title
	 */
	static final String title = "Fibonacci!";

	/**
	 * @param args
	 *
	 * 値が50未満のフィボナッチ数列を表示する
	 */
	public static void main(String[] args) {
		// Show Title
		System.out.println(title);

		int lo = 1;
		int hi = 1;
		System.out.println(lo);
		while (hi < 50)
		{
			System.out.println(hi);
			hi = lo + hi;	// 新しいhi
			lo = hi - lo;	// 新しいloは、（合計 - 古いlo）
							// すなわち、古いhi
		}

	}

}
