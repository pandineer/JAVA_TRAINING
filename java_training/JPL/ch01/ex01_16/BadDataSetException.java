/*
 * 練習問題1.16 p.29
 * BadDataSetExceptionにフィールドを追加してデータの集まりの名前と、問題を通知しているI/O例外を保持できるようにしなさい。
 * それにより、その例外をキャッチしてエラーの詳細を知ることができるようになります。
 */

package ch01.ex01_16;

import java.io.IOException;

class BadDataSetException extends Exception
{

	private static final long serialVersionUID = 1L;
	String DataName;
	IOException HoldException;
	BadDataSetException(String a, IOException e)
	{
		DataName = a;
		HoldException = e;
	}
}