package ex02_05;

public class Vehicle {
	double currentSpeed;
	double currentDirection;
	String owner;


	public static int nextID = 0;
	final int id = nextID++;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vehicle testVehicle1 = new Vehicle();
		// testVehicle1.id = Vehicle.nextID++;
		testVehicle1.currentSpeed = 3.5;
		testVehicle1.currentDirection = 1.2;
		testVehicle1.owner = "Bob";
		System.out.println("Id: " + testVehicle1.id);
		System.out.println("Current speed: " + testVehicle1.currentSpeed);
		System.out.println("Current direction: " + testVehicle1.currentDirection);
		System.out.println("Owner: " + testVehicle1.owner);

		System.out.println("");

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

		System.out.println("current nextID: " + Vehicle.nextID);
	}

}
