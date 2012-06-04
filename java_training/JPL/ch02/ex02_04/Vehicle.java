/*
 * 練習問題2.4 p.40
 * 練習問題2.3に対する答えについて考えてください。識別番号フィールドはfinalとすべきですか。
 */

package ch02.ex02_04;

public class Vehicle
{
    double currentSpeed;
    double currentDirection;
    String owner;

    public static int nextID = 1;
    final int id = nextID++;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Vehicle testVehicle1 = new Vehicle();
        testVehicle1.currentSpeed = 3.5;
        testVehicle1.currentDirection = 1.2;
        testVehicle1.owner = "Bob";
        System.out.println("Id: " + testVehicle1.id);
        System.out.println("Current speed: " + testVehicle1.currentSpeed);
        System.out.println("Current direction: "
                + testVehicle1.currentDirection);
        System.out.println("Owner: " + testVehicle1.owner);

        System.out.println("");

        Vehicle testVehicle2 = new Vehicle();
        testVehicle2.currentSpeed = 42;
        testVehicle2.currentDirection = 0.5;
        testVehicle2.owner = "Steve";
        System.out.println("Id: " + testVehicle2.id);
        System.out.println("Current speed: " + testVehicle2.currentSpeed);
        System.out.println("Current direction: "
                + testVehicle2.currentDirection);
        System.out.println("Owner: " + testVehicle2.owner);

        System.out.println("");

        System.out.println("current nextID: " + Vehicle.nextID);
    }

}
