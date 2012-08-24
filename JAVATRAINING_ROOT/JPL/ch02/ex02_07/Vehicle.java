/*
 * 練習問題2.7 p.47
 * Vehicleに２つのコンストラクタを追加しなさい。１つは引数なしコンストラクタで、もう１つは最初の所有者の名前を引数に取ります。
 * mainプログラムを修正して、前と同じ出力が生成されるようにしなさい。
 */

package ch02.ex02_07;

public class Vehicle
{
    public double currentSpeed;
    double currentDirection;
    String owner;

    public static int nextID = 1;
    final int id = nextID++;

    public Vehicle()
    {
        ;
    }

    public Vehicle(String ownerName)
    {
        owner = ownerName;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        Vehicle testVehicle1 = new Vehicle("Bob");
        testVehicle1.currentSpeed = 3.5;
        testVehicle1.currentDirection = 1.2;
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
