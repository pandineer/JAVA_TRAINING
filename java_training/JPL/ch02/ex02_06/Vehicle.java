/*
 * 練習問題2.6 p.43
 * LinkedListクラスにmainメソッドを書いて、Vehicle型のオブジェクトを数個作成して、リストの連続したノードに入れなさい。
 */

package ch02.ex02_06;

public class Vehicle {
	public double currentSpeed;
	double currentDirection;
	String owner;

	public static int nextID = 0;
	final int id = nextID++;

}
