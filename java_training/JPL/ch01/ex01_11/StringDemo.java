/*
 * 練習問題1.11 p.19
 * StringsDemoアプリケーションを修正して、他の文字列を使用してみてください。
 */

package ch01.ex01_11;

public class StringDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String myName = "Ryosuke";

		myName = myName + " Miyahara";
		System.out.println("Name = " + myName);

	}

}
