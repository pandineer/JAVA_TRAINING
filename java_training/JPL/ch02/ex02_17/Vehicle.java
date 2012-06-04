/*
 * 練習問題2.17 p.61
 * Vehicleに２つのturnメソッドを追加してください。
 * １つは、引数として回転する角度を受け取り、もう１つは定数Vehicle.TURN_LEFTかVehicle.TURN_RIGHTを引数として取ります。
 */

package ch02.ex02_17;

public class Vehicle
{
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

    public Vehicle(String ownerName)
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
    public static void main(String[] args)
    {

        Vehicle testVehicle1 = new Vehicle("Bob");

        System.out.println("current direction: "
                + testVehicle1.getCurrentDirection());
        System.out.println("add 0.5 to direction");
        testVehicle1.turn(0.5);
        System.out.println("current direction: "
                + testVehicle1.getCurrentDirection());
        System.out.println("operate turn right");
        testVehicle1.turn(TURN_RIGHT);
        System.out.println("current direction: "
                + testVehicle1.getCurrentDirection());
        System.out.println("operate turn left");
        testVehicle1.turn(TURN_LEFT);
        System.out.println("current direction: "
                + testVehicle1.getCurrentDirection());
    }

}
