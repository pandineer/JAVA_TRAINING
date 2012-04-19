package ex02_17;

public class Vehicle {
	private double currentSpeed;
	private double currentDirection;
	private String owner;

	static final int TURN_RIGHT = 1;
	static final int TURN_LEFT = 2;

	public void turn(int direction)
	{
		if (direction == TURN_RIGHT)
		{
			currentDirection = currentDirection + 1.0;
		}
		else if (direction == TURN_LEFT)
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
		// testVehicle1.id = Vehicle.nextID++;
		// testVehicle1.currentSpeed = 3.5;
		testVehicle1.changeSpeed(3.5);
		// testVehicle1.currentDirection = 1.2;
		testVehicle1.setCurrentDirection(1.2);
		// testVehicle1.owner = "Bob";
		System.out.println("Id: " + testVehicle1.id);
		// System.out.println("Current speed: " + testVehicle1.currentSpeed);
		System.out.println("Current speed: " + testVehicle1.getCurrentSpeed());
		// System.out.println("Current direction: " + testVehicle1.currentDirection);
		System.out.println("Current direction: " + testVehicle1.getCurrentDirection());
		// System.out.println("Owner: " + testVehicle1.owner);
		System.out.println("Owner: " + testVehicle1.getOwner());

		System.out.println("");

		/*
		Vehicle testVehicle2 = new Vehicle();
		// testVehicle2.id = Vehicle.nextID++;
		testVehicle2.currentSpeed = 42;
		testVehicle2.currentDirection = 0.5;
		testVehicle2.owner = "Steve";
		System.out.println("Id: " + testVehicle2.id);
		System.out.println("Current speed: " + testVehicle2.currentSpeed);
		System.out.println("Current direction: " + testVehicle2.currentDirection);
		System.out.println("Owner: " + testVehicle2.owner);

		System.out.println("");
		*/
		// System.out.println("current nextID: " + Vehicle.nextID);

		System.out.println("MAX used ID: " + Vehicle.showCurrentID());

		System.out.println("");

		System.out.println(testVehicle1);

		testVehicle1.stop();
		System.out.println("after stop method, Current speed: " + testVehicle1.getCurrentSpeed());

		System.out.println("current direction: " + testVehicle1.getCurrentDirection());
		System.out.println("add 0.5 to direction");
		testVehicle1.turn(0.5);
		System.out.println("current direction: " + testVehicle1.getCurrentDirection());
		System.out.println("operate turn right");
		testVehicle1.turn(TURN_RIGHT);
		System.out.println("current direction: " + testVehicle1.getCurrentDirection());
		System.out.println("operate turn left");
		testVehicle1.turn(TURN_LEFT);
		System.out.println("current direction: " + testVehicle1.getCurrentDirection());
	}

}
