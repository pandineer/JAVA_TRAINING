package ex02_06;

public class LinkedList {

	Object object;
	LinkedList nextObject;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LinkedList test = new LinkedList();
		test.object = new Vehicle();

		test.nextObject = new LinkedList();
		test.nextObject.object = new Vehicle();
		System.out.println(((Vehicle)test.object).id);
		System.out.println(((Vehicle)test.nextObject.object).id);

	}

}
