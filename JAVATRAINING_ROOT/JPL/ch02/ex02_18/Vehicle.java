/*
 * 練習問題2.18 p.64
 * Vehicle.mainを変更して、コマンドラインで指定された名前を所有者として持つ車を作成し、表示するようにしなさい。
 */

package ch02.ex02_18;

public class Vehicle
{
    private double currentSpeed;
    private double currentDirection;
    private String owner;

    static final int TURN_RIGHT = 1;
    static final int TURN_LEFT = 2;

    private static int nextID = 1;
    final int id = nextID++;

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
        // まだ識別番号が一度も使われていない場合は0を返す
        return nextID - 1;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Vehicle testVehicle1;

        if (args.length == 0)
        {
            testVehicle1 = new Vehicle("Bob");
        }
        else
        {
            testVehicle1 = new Vehicle(args[0]);
        }

        System.out.println("Owner: " + testVehicle1.getOwner());

    }

}
