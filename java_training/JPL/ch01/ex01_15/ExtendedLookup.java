/*
 * 練習問題1.15 p.25
 * addとremoveメソッドを宣言したインタフェースを、Lookupを拡張して定義しなさい。
 * その拡張したインタフェースを新たなクラスに実装しなさい。
 */

package ch01.ex01_15;

public interface ExtendedLookup extends Lookup {
	void add(String name);
	void remove();

}
