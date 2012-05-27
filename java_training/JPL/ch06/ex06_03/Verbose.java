/*
 * 練習問題6.3 p.132
 * 104頁4.2.1節のVerboseインタフェースを、整数定数の代わりに、メッセージレベルのためのenumを使用して再定義しなさい。
 */

package ch06.ex06_03;

public interface Verbose {
	enum Level
	{
		SILENT,
		TERSE,
		NORMAL,
		VERBOSE,
	}

	void setVerbosity(Level level);
	Level getVerbosity();
}
