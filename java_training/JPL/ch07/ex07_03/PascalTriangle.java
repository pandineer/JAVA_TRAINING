package ex07_03;

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
