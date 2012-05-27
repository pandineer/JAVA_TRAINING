/*
 * 練習問題4.2 p.114
 * 99頁の練習問題3.12の回答を、最初にインタフェースを使用して書いていなければ、
 * インタフェースを使用して書き直しなさい。
 */

package ch04.ex04_02;

public class SimpleSortObject extends SortHarness {

	public void doSort()
	{

		for (int i = 0; i < getDataLength(); i++)
		{
			for (int j = i + 1; j < getDataLength(); j++)
			{
				if (compare(i, j) > 0)
				{
					swap(i, j);
				}
			}
		}
	}
}
