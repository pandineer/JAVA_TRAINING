/*
 * 練習問題4.3 p.114
 * 前の練習問題のLinkedListクラスは、インタフェースであるべきですか。
 * どうあるべきかを決める前に、実装クラスとインタフェースクラスを使用して書き直しなさい。
 */

package ch04.ex04_03;

public interface LinkedList {
	int getObjectNumber();
	Object getObject();
	void setObject(Object target);
	LinkedList getNextObject();
	void setNextObject(LinkedListImpl target);
}
