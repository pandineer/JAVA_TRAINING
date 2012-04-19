package ex02_02;

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
