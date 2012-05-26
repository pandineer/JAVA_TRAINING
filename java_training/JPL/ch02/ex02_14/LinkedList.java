/*
 * 練習問題2.14 p.58
 * LinkedListクラスのフィールドをprivateにして、フィールドに対するアクセッサーメソッドを追加しなさい。
 * どのフィールドが変更を許すメソッドを持ち、どのフィールドがそのようなメソッドを持つべきではないですか。
 */

package ch02.ex02_14;

// すべてて変更可能で良いと思う。

public class LinkedList {

	private Object object;
	private LinkedList nextObject;

	public Object getObject()
	{
		return object;
	}

	public void setObject(Object target)
	{
		object = target;
	}

	public LinkedList getNextObject()
	{
		return nextObject;
	}

	public void setNextObject(LinkedList target)
	{
		nextObject = target;
	}

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

		temporary = new Vehicle();
		test.setNextObject(new LinkedList(temporary));
		System.out.println(((Vehicle)test.getObject()).id);
		System.out.println(((Vehicle)test.getNextObject().getObject()).id);

		System.out.println("");

		System.out.println(test);
	}

}
