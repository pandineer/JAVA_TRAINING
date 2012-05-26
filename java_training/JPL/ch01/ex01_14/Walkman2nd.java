/*
 * 練習問題1.14 p.23
 * この節で述べたソニーウォークマン製品ファミリーのクラス構造を反映しているクラスを設計してみてください。
 * すべてのデータをprivateとし、メソッドをpublicとすることで、データを隠すためにメソッドを使用してください。
 * Walkmanクラスにはどのようなメソッドが必要ですか。どのメソッドが拡張されたクラスに追加されますか。
 */

package ch01.ex01_14;

public class Walkman2nd extends Walkman {
	private String listener2nd;

	public void listen2nd(String who)
	{
		listener2nd = who;
	}

	public void showListener2ne()
	{
		System.out.println("Walkman 2nd listener: " + listener2nd);
	}

}
