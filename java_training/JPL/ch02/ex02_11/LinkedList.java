/*
 * 練習問題2.11 p.51
 * LinkedListにtoStringメソッドを追加しなさい。
 */

package ch02.ex02_11;

public class LinkedList {

	Object object;
	LinkedList nextObject;

	LinkedList (Object associatedObject)
	{
		object = associatedObject;
	}

	public String toString()
	{
		String desc = "This method is toString of LinkedList!";
		return desc;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Vehicle temporary = new Vehicle();

		LinkedList test = new LinkedList(temporary);
		// test.object = new Vehicle();

		temporary = new Vehicle();
		test.nextObject = new LinkedList(temporary);
		System.out.println(((Vehicle)test.object).id);
		System.out.println(((Vehicle)test.nextObject.object).id);

		System.out.println("");

		System.out.println(test);
	}

}
