/*
 * 練習問題2.14 p.58
 * LinkedListクラスのフィールドをprivateにして、フィールドに対するアクセッサーメソッドを追加しなさい。
 * どのフィールドが変更を許すメソッドを持ち、どのフィールドがそのようなメソッドを持つべきではないですか。
 */

package ch02.ex02_14;

public class Vehicle {
	public double currentSpeed;
	double currentDirection;
	String owner;


	public static int nextID = 0;
	final int id = nextID++;

}
