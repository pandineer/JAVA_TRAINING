package ex03_11;

public class SimpleSortDouble extends SortDouble {

	static int count = 0;
	double tmp[] = {0.0, 0.1};

	protected void doSort()
	{
		if (count == 0)
		{
			sort(tmp);
			count = 1;
		}
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
