/*
 * 練習問題3.4 p.84
 * もし、あるとしたら、VehicleとPassengerVehicleのどのメソッドをfinalにするのが適切ですか。
 */

package ch03.ex03_04;

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

    final public static int showCurrentID()
    {
        // まだ識別番号が一度も使われていない場合は0を返す
        return nextID - 1;
    }

}
