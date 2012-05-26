/*
 * 練習問題1.14 p.23
 * この節で述べたソニーウォークマン製品ファミリーのクラス構造を反映しているクラスを設計してみてください。
 * すべてのデータをprivateとし、メソッドをpublicとすることで、データを隠すためにメソッドを使用してください。
 * Walkmanクラスにはどのようなメソッドが必要ですか。どのメソッドが拡張されたクラスに追加されますか。
 */

package ch01.ex01_14;

public class walkman3rd extends Walkman2nd {
	private String customer;

	public void communication(String who)
	{
		customer = who;
	}

	public void showCustomer()
	{
		System.out.println("Communication customer: " + customer);
	}

	public static void main(String[] args) {
		walkman3rd test = new walkman3rd();

		test.listener("Bob");
		test.showListener();

		test.listen2nd("Jason");
		test.showListener2ne();

		test.communication("communication cutomer");
		test.showCustomer();
	}

}
