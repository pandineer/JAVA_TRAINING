package ch01.ex01_04;

public class SquareList {

	/**
	 * @param args
	 *
	 * 100未満の平方表を表示する
	 */
	public static void main(String[] args) {
		System.out.println("Show square list!");

		int i = 1;

		while (i * i < 100)
		{
			System.out.println(i*i);
			i++;
		}

	}

}
