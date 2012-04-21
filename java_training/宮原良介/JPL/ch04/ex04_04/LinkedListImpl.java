package ex04_04;

public class LinkedListImpl implements Cloneable, LinkedList
{

    private Object object;
    private LinkedListImpl nextObject;

    public LinkedListImpl clone()
    {
        try
        {
            // デフォルトの仕組みで十分
            return (LinkedListImpl) super.clone();
        } catch (CloneNotSupportedException e)
        {
            // 起こり得ない。このクラスとObjectは複製できる
            throw new InternalError(e.toString());
        }
    }

    public int getObjectNumber()
    {
        int objectNumber = 0;
        LinkedListImpl next = nextObject;
        while (true)
        {
            objectNumber++;
            if (next == null)
            {
                break;
            } else
            {
                next = (LinkedListImpl)nextObject.getNextObject();
            }
        }
        return objectNumber;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object target)
    {
        object = target;
    }

    public LinkedList getNextObject()
    {
        return nextObject;
    }

    public void setNextObject(LinkedListImpl target)
    {
        nextObject = target;
    }

    LinkedListImpl(Object associatedObject)
    {
        object = associatedObject;
        nextObject = null;
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

        LinkedListImpl link1 = new LinkedListImpl(temporary);

        temporary = new Vehicle("test2");
        link1.setNextObject(new LinkedListImpl(temporary));

        LinkedListImpl link2 = new LinkedListImpl(null);
        link2 = link1.clone();

        System.out.println(((Vehicle)link1.getObject()).getOwner());
        System.out.println(((Vehicle)link2.getObject()).getOwner());

        // link1からもlink2からも変更が見える
        ((Vehicle)(link2).getObject()).setOwner("modified");

        System.out.println("");
        System.out.println(((Vehicle)link1.getObject()).getOwner());
        System.out.println(((Vehicle)link2.getObject()).getOwner());


        // １つのリストに対する変更は、他方のリストに影響しない（link2のリストを別の参照にする）
        temporary = new Vehicle("test3");

        link2 = new LinkedListImpl(temporary);

        temporary = new Vehicle("test4");
        link2.setNextObject(new LinkedListImpl(temporary));

        System.out.println("");
        System.out.println(((Vehicle)link1.getObject()).getOwner());
        System.out.println(((Vehicle)link2.getObject()).getOwner());


    }

}
