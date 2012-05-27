/*
 * 練習問題4.4 p.114
 * インタフェースのみ使用して、コレクションクラス階層を設計しなさい。
 */

package ch04.ex04_04;

public class VehicleLinkedListImpl implements Cloneable, VehicleLinkedList
{

    private Vehicle vehicle;
    private VehicleLinkedListImpl nextVehicle;

    public VehicleLinkedListImpl clone()
    {
        try
        {
            // デフォルトの仕組みで十分
            return (VehicleLinkedListImpl) super.clone();
        } catch (CloneNotSupportedException e)
        {
            // 起こり得ない。このクラスとObjectは複製できる
            throw new InternalError(e.toString());
        }
    }

    public int getVehicleNumber()
    {
        int vehicleNumber = 0;
        VehicleLinkedListImpl next = nextVehicle;
        while (true)
        {
            vehicleNumber++;
            if (next == null)
            {
                break;
            } else
            {
                next = (VehicleLinkedListImpl)nextVehicle.getNextVehicle();
            }
        }
        return vehicleNumber;
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(Vehicle target)
    {
        vehicle = target;
    }

    public VehicleLinkedList getNextVehicle()
    {
        return nextVehicle;
    }

    public void setNextVehicle(VehicleLinkedListImpl target)
    {
        nextVehicle = target;
    }

    VehicleLinkedListImpl(Vehicle associatedVehicle)
    {
        vehicle = associatedVehicle;
        nextVehicle = null;
    }

    public String toString()
    {
        String desc = "This method is toString of LinkedList!";
        return desc;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        // リストが参照しているオブジェクトに対する変更は、他方のリストから見える
        Vehicle temporary = new Vehicle("test1");

        VehicleLinkedListImpl link1 = new VehicleLinkedListImpl(temporary);

        temporary = new Vehicle("test2");
        link1.setNextVehicle(new VehicleLinkedListImpl(temporary));

        VehicleLinkedListImpl link2 = new VehicleLinkedListImpl(null);
        link2 = link1.clone();

        System.out.println(((Vehicle)link1.getVehicle()).getOwner());
        System.out.println(((Vehicle)link2.getVehicle()).getOwner());

        // link1からもlink2からも変更が見える
        ((Vehicle)(link2).getVehicle()).setOwner("modified");

        System.out.println("");
        System.out.println(((Vehicle)link1.getVehicle()).getOwner());
        System.out.println(((Vehicle)link2.getVehicle()).getOwner());


        // １つのリストに対する変更は、他方のリストに影響しない（link2のリストを別の参照にする）
        temporary = new Vehicle("test3");

        link2 = new VehicleLinkedListImpl(temporary);

        temporary = new Vehicle("test4");
        link2.setNextVehicle(new VehicleLinkedListImpl(temporary));

        System.out.println("");
        System.out.println(((Vehicle)link1.getVehicle()).getOwner());
        System.out.println(((Vehicle)link2.getVehicle()).getOwner());


    }

}
