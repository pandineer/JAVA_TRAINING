/*
 * 練習問題1.14 p.23
 * この節で述べたソニーウォークマン製品ファミリーのクラス構造を反映しているクラスを設計してみてください。
 * すべてのデータをprivateとし、メソッドをpublicとすることで、データを隠すためにメソッドを使用してください。
 * Walkmanクラスにはどのようなメソッドが必要ですか。どのメソッドが拡張されたクラスに追加されますか。
 */

package ch01.ex01_14;

public class Walkman {
	private String listener;

	public void listener(String who)
	{
		listener = who;
	};

	public void showListener()
	{
		System.out.println("Walkman listener: " + listener);
	}

}
