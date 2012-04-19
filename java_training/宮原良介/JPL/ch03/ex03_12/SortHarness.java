package ex03_12;

abstract public class SortHarness {
	private int SortCount = 0;
	private Object[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	/** 全ソートをするために呼び出される */
	public final SortMetrics sort(Object[] data) {
		values = data;
		// -- 改良部分ここから --
		if (SortCount > 0) {
			System.out.println("doSort is used " + SortCount + " times!");
		} else {
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

	public final SortMetrics getMetrics() {
		return curMetrics.clone();
	}

	/** 拡張したクラスが要素の数を知るため */
	protected final int getDataLength() {
		return values.length;
	}

	/** 拡張したクラスが要素を調べるため */
	protected final Object probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	/** 拡張したクラスが要素を比較するため */
	protected final int compare(int i, int j) {
		curMetrics.compareCnt++;
		Object d1 = values[i];
		Object d2 = values[j];
		if (d1 == d2) {
			return 0;
		} else {
			if (d1.toString().compareTo(d2.toString()) == 1) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/** 拡張したクラスが要素を交換するため */
	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	/** 拡張したクラスが実装する -- ソートするのに使用される */
	protected abstract void doSort();

}