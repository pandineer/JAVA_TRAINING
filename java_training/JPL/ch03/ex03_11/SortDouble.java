/*
 * 練習問題3.11 p.99
 * ソートアルゴリズムが、気づかれることなくメトリックスに関して不正を行えるSortDoubleのセキュリティホールを
 * 少なくとも１つ見つけなさい。そのセキュリティホールをなくすように修正しなさい。
 * この場合、ソートアルゴリズムの作成者はmainを書かないと想定してください
 */

package ch03.ex03_11;

public abstract class SortDouble {
	private int SortCount = 0;
	private double[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	/** 全ソートをするために呼び出される */
	public final SortMetrics sort(double[] data)
	{
		values = data;
		// --  改良部分ここから --
		if (SortCount > 0)
		{
			System.out.println("doSort is used " + (SortCount + 1) + " times!");
		}
		else
		{
			curMetrics.init();
		}
		SortCount++;
		// -- 改良部分ここまで --

		/*
		// オリジナル
		curMetrics.init();
		*/

		doSort();
		return getMetrics();
	}

	public final SortMetrics getMetrics()
	{
		return curMetrics.clone();
	}

	/** 拡張したクラスが要素の数を知るため */
	protected final int getDataLength()
	{
		return values.length;
	}

	/** 拡張したクラスが要素を調べるため */
	protected final double probe(int i)
	{
		curMetrics.probeCnt++;
		return values[i];
	}

	/** 拡張したクラスが要素を比較するため */
	protected final int compare(int i, int j)
	{
		curMetrics.compareCnt++;
		double d1 = values[i];
		double d2 = values[j];
		if (d1 == d2)
		{
			return 0;
		}
		else
		{
			return (d1 < d2 ? -1 : 1);
		}
	}

	/** 拡張したクラスが要素を交換するため */
	protected final void swap(int i, int j)
	{
		curMetrics.swapCnt++;
		double tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	/** 拡張したクラスが実装する -- ソートするのに使用される */
	protected abstract void doSort();


}
