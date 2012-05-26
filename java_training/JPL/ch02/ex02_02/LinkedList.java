/*
 * 練習問題2.2 p.37
 * LinkedListクラスを作成しなさい。LinkeListクラスは、Object型のフィールドと、リストの中で次のLinkedList要素への参照を持ちます。
 */

package ch02.ex02_02;

public class LinkedList {

	Object object;
	LinkedList nextObject;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LinkedList test1 = new LinkedList();
		test1.object = 1;
		test1.nextObject = new LinkedList();
		test1.nextObject.object = 2;
		System.out.println(test1.object);
		System.out.println(test1.nextObject.object);

	}

}
