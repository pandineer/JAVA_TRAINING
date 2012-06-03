/*
 * 練習問題2.15 p.58
 * 乗り物の現在のスピードを引数で渡された値に変更するchangeSpeedメソッドと、スピードをゼロにするstopメソッドを追加しなさい。
 */

package ch02.ex02_15;

public class Vehicle {
	private double currentSpeed;
	private double currentDirection;
	private String owner;

	private static int nextID = 1;
	final int id = nextID++;

	public String getOwner()
	{
		return owner;
	}

	public double getCurrentSpeed()
	{
		return currentSpeed;
	}

	public void changeSpeed(double speed)
	{
		currentSpeed = speed;
	}

	public void stop()
	{
		currentSpeed = 0.0;
	}

	public double getCurrentDirection()
	{
		return currentDirection;
	}

	public void setCurrentDirection(double direction)
	{
		currentDirection = direction;
	}



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
		// まだ識別番号が一度も使われていない場合は0を返す
		return nextID - 1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Vehicle testVehicle1 = new Vehicle("Bob");
		testVehicle1.changeSpeed(3.5);
		testVehicle1.setCurrentDirection(1.2);
		System.out.println("Id: " + testVehicle1.id);
		System.out.println("Current speed: " + testVehicle1.getCurrentSpeed());
		System.out.println("Current direction: " + testVehicle1.getCurrentDirection());
		System.out.println("Owner: " + testVehicle1.getOwner());

		System.out.println("");

		System.out.println("MAX used ID: " + Vehicle.showCurrentID());

		System.out.println("");

		System.out.println(testVehicle1);

		testVehicle1.stop();
		System.out.println("after stop method, Current speed: " + testVehicle1.getCurrentSpeed());

	}

}
