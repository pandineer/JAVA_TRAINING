/*
 * 練習問題3.8 p.92
 * VehicleとPassengerVehicleをCloneable型にしなさい。複製に関して、４つの選択肢のどれを選択すべきでしょうか。
 * Object.cloneによる単純なコピーは、それらのクラスのcloneメソッドとしては正しいですか。
 */

package ch03.ex03_08;

public class Vehicle implements Cloneable
{
    private double currentSpeed;
    private double currentDirection;
    private String owner;

    static final int TURN_RIGHT = 1;
    static final int TURN_LEFT = 2;

    private static int nextID = 1;
    // final int id = nextID++;
    int id;

    public Vehicle clone()
    {
        try
        {
            // IDの処理のため、コンストラクタを実行する必要がある
            Vehicle temp = (Vehicle)super.clone();
            temp.id = nextID++;
            temp.currentSpeed = currentSpeed;
            temp.currentDirection = currentDirection;
            temp.owner = owner;
            return temp;
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

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        Vehicle testVehicle1;

        if (args.length == 0)
        {
            System.out.println("!");
            testVehicle1 = new Vehicle("Bob");
        } else
        {
            System.out.println("1");
            testVehicle1 = new Vehicle(args[0]);
        }
        testVehicle1.changeSpeed(3.5);
        testVehicle1.setCurrentDirection(1.2);
        System.out.println("Id: " + testVehicle1.id);
        System.out.println("Current speed: " + testVehicle1.getCurrentSpeed());
        System.out.println("Current direction: "
                + testVehicle1.getCurrentDirection());
        System.out.println("Owner: " + testVehicle1.getOwner());

        System.out.println("");

        System.out.println("MAX used ID: " + Vehicle.showCurrentID());

        System.out.println("");

        System.out.println(testVehicle1);

        testVehicle1.stop();
        System.out.println("after stop method, Current speed: "
                + testVehicle1.getCurrentSpeed());

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

        Vehicle testVehicle2 = new Vehicle();

        testVehicle2 = (Vehicle) testVehicle1.clone();


        System.out.println("clone result: "
                + testVehicle2.getCurrentDirection());
    }
}
