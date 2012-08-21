/*
 * 練習問題4.4 p.114
 * インタフェースのみ使用して、コレクションクラス階層を設計しなさい。
 */

package ch04.ex04_04;

public class Vehicle implements Cloneable
{
    private double currentSpeed;
    private double currentDirection;
    private String owner;

    static final int TURN_RIGHT = 1;
    static final int TURN_LEFT = 2;

    private static int nextID = 1;

    int id;

    public Vehicle clone()
    {
        try
        {
            // 単純なObject.cloneによるコピーでは、idが正しく処理されない

            // IDの処理が必要
            Vehicle temp = (Vehicle) super.clone();
            temp.id = nextID++;
            temp.currentSpeed = currentSpeed;
            temp.currentDirection = currentDirection;
            temp.owner = owner;
            return temp;
        }
        catch (CloneNotSupportedException e)
        {
            // 起こり得ない。このクラスとObjectは複製できる
            throw new InternalError(e.toString());
        }
    }

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

    public int getId()
    {
        return id;
    }

    public Vehicle()
    {
        id = nextID++;
    }

    public Vehicle(String ownerName)
    {
        this();
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

    public void setOwner(String owner)
    {
        this.owner = owner;
    }
}
