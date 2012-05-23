/*
 * 練習問題1.3 p.5
 * Fibonacci プログラムの出力リストにタイトルを追加しなさい。
 */

package ch01.ex01_03;

public class Fibonacci {

	/**
	 * @param args
	 *
	 * 値が50未満のフィボナッチ数列を表示する
	 */
	public static void main(String[] args) {
		// Show Title
		System.out.println("Fibonacci!");

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
