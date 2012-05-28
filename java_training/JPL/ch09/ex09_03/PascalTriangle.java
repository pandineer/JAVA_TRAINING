/*
 * 練習問題9.3 p.185
 * 本性で学んだ演算子を使用して、練習問題7.3の解答をより明瞭、あるいは、より簡潔に書くことができるか検討しなさい。
 *
 * 練習問題7.3 p.155
 * 深さが12のパスカルの三角形を計算するプログラムを作成しなさい。三角形の各行を適切な長さの配列とし、
 * 各行の配列を長さ12のint配列の配列に格納しなさい。定数12ではなく、各配列の長さを使用して配列の配列を表示する
 * メソッドにより、結果を表示するようにプログラムしなさい。さらに、表示メソッドを変更することなく、12を他の
 * 定数に変更してみなさい。
 */

package ch09.ex09_03;

public class PascalTriangle {
	private int pascalArray[][];

	// パスカルの三角形の配列を作成する
	public void makePascal(int size)
	{
		pascalArray = new int[size][];
		for (int i = 0; i < size; i++)
		{
			pascalArray[i] = new int[i + 1];
			pascalArray[i][0] = 1;
			for (int j = 1; j < i; j++)
			{
				pascalArray[i][j] = pascalArray[i - 1][j-1] + pascalArray[i - 1][j];
			}
			pascalArray[i][i] = 1;
		}
	}

	// パスカルの三角形の配列を表示する
	public void showPascal()
	{
		for (int i = 0; i < pascalArray.length; i++)
		{
			for (int j = 0; j < pascalArray[i].length; j++)
			{
				System.out.printf(pascalArray[i][j] + " ");
			}
			System.out.printf("%n");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PascalTriangle test = new PascalTriangle();
		test.makePascal(12);
		test.showPascal();
	}

}
