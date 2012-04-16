package ex03_09;

public class Garage implements Cloneable
{
    Vehicle VehicleList[];

    public Garage clone()
    {
        try
        {
            Garage newGarage = (Garage) super.clone();
            newGarage.VehicleList = new Vehicle[2];
            for (int i = 0; i < VehicleList.length; i++)
            {
                newGarage.VehicleList[i] = VehicleList[i].clone();
            }
            return newGarage;
        }
        catch (CloneNotSupportedException e)
        {
            // 起こり得ない。
            throw new InternalError(e.toString());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Garage test1 = new Garage();
        test1.VehicleList = new Vehicle[2];
        test1.VehicleList[0] = new Vehicle("test1");
        test1.VehicleList[1] = new Vehicle("test2");

        Garage test2 = test1.clone();
        test2.VehicleList[0].setOwner("test1 -> modified");

        System.out.println(test1.VehicleList[0].getOwner());
        System.out.println(test1.VehicleList[1].getOwner());
        System.out.println(test2.VehicleList[0].getOwner()); // 元のオブジェクトに影響を与えないことを確認
        System.out.println(test2.VehicleList[1].getOwner()); // コピーできていることを確認




    }

}
