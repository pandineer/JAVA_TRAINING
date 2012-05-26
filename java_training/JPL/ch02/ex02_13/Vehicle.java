/*
 * 練習問題2.13 p.58
 * Vehicleクラスのフィールドをprivateにして、フィールドに対するアクセッサーメソッドを追加しなさい。
 * どのフィールドが変更を許すメソッドを持ち、どのフィールドがそのようなメソッドを持つべきではないですか。
 */

package ch02.ex02_13;

// nextID以外は変更できて良いと思う。

public class Vehicle {
	private double currentSpeed;
	private double currentDirection;
	private String owner;

	public String getOwner()
	{
		return owner;
	}

	public double getCurrentSpeed()
	{
		return currentSpeed;
	}

	public void setCurrentSpeed(double speed)
	{
		currentSpeed = speed;
	}

	public double getCurrentDirection()
	{
		return currentDirection;
	}

	public void setCurrentDirection(double direction)
	{
		currentDirection = direction;
	}

	private static int nextID = 0;
	final int id = nextID++;

	public Vehicle()
	{
		;
	}

	public Vehicle (String ownerName)
	{
		owner = ownerName;
	}

	public String toString()
	{
		String desc = "owner: " + owner;

		return desc;
	}

	public static int showCurrentID()
	{
		// まだ識別番号が一度も使われていない場合は-1を返す
		return nextID - 1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Vehicle testVehicle1 = new Vehicle("Bob");
		testVehicle1.setCurrentSpeed(3.5);
		testVehicle1.setCurrentDirection(1.2);
		System.out.println("Id: " + testVehicle1.id);
		System.out.println("Current speed: " + testVehicle1.getCurrentSpeed());
		System.out.println("Current direction: " + testVehicle1.getCurrentDirection());
		System.out.println("Owner: " + testVehicle1.getOwner());

		System.out.println("");

		System.out.println("MAX used ID: " + Vehicle.showCurrentID());

		System.out.println("");

		System.out.println(testVehicle1);

	}

}
