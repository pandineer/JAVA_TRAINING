/*
 * 練習問題1.5 p.7
 * 表示する文字列に名前付文字列定数を使用するように HelloWorld アプリケーションを変更しなさい。
 * （文字列定数は、文字列リテラルで初期化できます。）
 */

package ch01.ex01_05;

public class HelloWorld {

	/**
	 * 表示するメッセージを定義する
	 */
	static final String message = "Hello, World";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(message);

	}

}
