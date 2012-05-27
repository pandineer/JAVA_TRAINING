/*
 * 練習問題6.3 p.132
 * 104頁4.2.1節のVerboseインタフェースを、整数定数の代わりに、メッセージレベルのためのenumを使用して再定義しなさい。
 */

package ch06.ex06_03;

public class Test implements Verbose {
	Level level;
	public void setVerbosity(Level level)
	{
		this.level = level;
	}

	public Level getVerbosity()
	{
		return level;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test test = new Test();
		test.setVerbosity(Level.SILENT);
		System.out.println("level after set SILENT: " + test.level);
		test.setVerbosity(Level.TERSE);
		System.out.println("level after set TERSE: " + test.level);
		test.setVerbosity(Level.NORMAL);
		System.out.println("level after set NORMAL: " + test.level);
		test.setVerbosity(Level.VERBOSE);
		System.out.println("level after set VERBOSE: " + test.level);
	}

}
