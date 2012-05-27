/*
 * 練習問題6.2 p.132
 * TURN_LEFTとTURN_RIGHTを表すenumを使用して、練習問題2.17をやり直しなさい。
 * enumを使用することにどのような利点がありますか。
 */

package ch06.ex06_02;

public class Vehicle {
	private double currentSpeed;
	private double currentDirection;
	private String owner;

	enum direction
	{
		TURN_RIGHT,
		TURN_LEFT,
	}

	// static final int TURN_RIGHT = 1;
	// static final int TURN_LEFT = 2;

	public void turn(direction direction)
	{
		if (direction == direction.TURN_RIGHT)
		{
			currentDirection = currentDirection + 1.0;
		}
		else if (direction == direction.TURN_LEFT)
		{
			currentDirection = currentDirection - 1.0;
		}
		else
		{
			; // 何もしない
		}
	}

	public void turn(double degree)
	{
		currentDirection = currentDirection + degree;
	}

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

		System.out.println("current direction: " + testVehicle1.getCurrentDirection());
		System.out.println("operate turn right");
		testVehicle1.turn(direction.TURN_RIGHT);
		System.out.println("current direction: " + testVehicle1.getCurrentDirection());
		System.out.println("operate turn left");
		testVehicle1.turn(direction.TURN_LEFT);
		System.out.println("current direction: " + testVehicle1.getCurrentDirection());
	}

}
