package ex02_08;

public class LinkedList {

	Object object;
	LinkedList nextObject;

	LinkedList (Object associatedObject)
	{
		object = associatedObject;
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
		// test.nextObject.object = new Vehicle();
		System.out.println(((Vehicle)test.object).id);
		System.out.println(((Vehicle)test.nextObject.object).id);

	}

}
