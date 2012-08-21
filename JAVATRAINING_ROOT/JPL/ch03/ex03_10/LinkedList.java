/*
 * 練習問題3.10 p.93
 * （第２章の問題からの）LinkedListクラスをCloneableにして、値の複製ではなく、元のリストと同じ値を参照している
 * 新たなリストを返すcloneメソッドを書きなさい。つまり、１つのリストに対する変更は、他方のリストには影響しないが、
 * リストが参照しているオブジェクトに対する変更は、他方のリストから見えます。
 */

package ch03.ex03_10;

public class LinkedList implements Cloneable
{

    private Object object;
    private LinkedList nextObject;

    public LinkedList clone()
    {
        try
        {
            // デフォルトの仕組みで十分
            return (LinkedList) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // 起こり得ない。このクラスとObjectは複製できる
            throw new InternalError(e.toString());
        }
    }

    public int getObjectNumber()
    {
        int objectNumber = 0;
        LinkedList next = nextObject;
        while (true)
        {
            objectNumber++;
            if (next == null)
            {
                break;
            }
            else
            {
                next = nextObject.getNextObject();
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

    public void setNextObject(LinkedList target)
    {
        nextObject = target;
    }

    LinkedList(Object associatedObject)
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
        LinkedList link1 = new LinkedList(new Vehicle("test1"));

        link1.setNextObject(new LinkedList(new Vehicle("test2")));

        LinkedList link2 = new LinkedList(null);
        link2 = link1.clone();

        System.out.println(((Vehicle) link1.getObject()).getOwner());
        System.out.println(((Vehicle) link2.getObject()).getOwner());

        System.out.println("");

        // link1からもlink2からも変更が見える
        ((Vehicle) (link2).getObject()).setOwner("modified");

        System.out.println(((Vehicle) link1.getObject()).getOwner());
        System.out.println(((Vehicle) link2.getObject()).getOwner());

        // １つのリストに対する変更は、他方のリストに影響しない（link2のリストを別の参照にする）
        link2 = new LinkedList(new Vehicle("test3"));

        link2.setNextObject(new LinkedList(new Vehicle("test4")));

        System.out.println("");
        System.out.println(((Vehicle) link1.getObject()).getOwner());
        System.out.println(((Vehicle) link2.getObject()).getOwner());

    }

}
