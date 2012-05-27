/*
 * 練習問題3.10 p.93
 * （第２章の問題からの）LinkedListクラスをCloneableにして、値の複製ではなく、元のリストと同じ値を参照している
 * 新たなリストを返すcloneメソッドを書きなさい。つまり、１つのリストに対する変更は、他方のリストには影響しないが、
 * リストが参照しているオブジェクトに対する変更は、他方のリストから見えます。
 */

package ch03.ex03_10;

public class Vehicle implements Cloneable
{
    private double currentSpeed;
    private double currentDirection;
    private String owner;

    static final int TURN_RIGHT = 1;
    static final int TURN_LEFT = 2;

    public Vehicle clone()
    {
        try
        {
            // デフォルトの仕組みで十分
            return (Vehicle) super.clone();
        } catch (CloneNotSupportedException e)
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
        } else if (direction == TURN_LEFT)
        {
            currentDirection = currentDirection - 1.0;
        } else
        {
            ; // 何もしない
        }
    }

    public void turn(double degree)
    {
        currentDirection = currentDirection + degree;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
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
}
