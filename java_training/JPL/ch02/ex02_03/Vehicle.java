/*
 * 練習問題2.3 p.39
 * 次の乗り物の識別番号を保持するstaticフィールドと、車単位でID番号を保持するための非staticフィールドをVehicleクラスに追加しなさい。
 */

package ch02.ex02_03;

public class Vehicle
{
    double currentSpeed;
    double currentDirection;
    String owner;

    int id;
    public static int nextID = 1;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Vehicle testVehicle1 = new Vehicle();
        testVehicle1.id = Vehicle.nextID++;
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
        testVehicle2.id = Vehicle.nextID++;
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
