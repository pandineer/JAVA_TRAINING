package ex02_01;

public class Vehicle {
	double currentSpeed;
	double currentDirection;
	String owner;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vehicle testVehicle = new Vehicle();
		testVehicle.currentSpeed = 3.5;
		testVehicle.currentDirection = 1.2;
		testVehicle.owner = "Bob";
		System.out.println("Current speed: " + testVehicle.currentSpeed);
		System.out.println("Current direction: " + testVehicle.currentDirection);
		System.out.println("Owner: " + testVehicle.owner);

	}

}
