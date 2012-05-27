/*
 * 練習問題6.4 p.135
 * 132頁の練習問題6.1の信号機の色のenumを修正して、各enum定数が適切なColorオブジェクトを持ち、
 * そのオブジェクトをgetColorで取り出せるようにしなさい。
 */

package ch06.ex06_04;

public class Color {
	public String ColorName;
	Color(String name)
	{
		ColorName = name;
	}
}
