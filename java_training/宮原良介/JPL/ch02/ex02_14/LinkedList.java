package ex02_14;

// すべてのフィールドがprivateで良いと思う

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
		// test.object = new Vehicle();

		temporary = new Vehicle();
		// test.nextObject = new LinkedList(temporary);
		test.setNextObject(new LinkedList(temporary));
		// test.nextObject.object = new Vehicle();
		// System.out.println(((Vehicle)test.object).id);
		System.out.println(((Vehicle)test.getObject()).id);
		// System.out.println(((Vehicle)test.nextObject.object).id);
		System.out.println(((Vehicle)test.getNextObject().getObject()).id);

		System.out.println("");

		System.out.println(test);
	}

}
