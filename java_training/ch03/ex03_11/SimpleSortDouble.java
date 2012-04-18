package ex03_11;

public class SimpleSortDouble extends SortDouble {

	static int count = 0;
	double tmp[] = {0.0, 0.1};

	protected void doSort()
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
		// sortを終えた後に関係ないデータを用いてもう一度ソートを行うと、
		// カウンタがクリアされ、最終的にcomparesのカウンタ値しか残らない
		if (count == 0)
		{
			count = 1;
			sort(tmp);
		}
	}
}
