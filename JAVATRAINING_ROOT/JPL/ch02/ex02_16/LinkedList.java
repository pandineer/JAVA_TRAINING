/*
 * 練習問題2.16 p.59
 * リスト内の要素の数を返すメソッドをLinkedListに追加しなさい。
 */

package ch02.ex02_16;

public class LinkedList
{

    private Object object;
    private LinkedList nextObject;

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

        Vehicle temporary = new Vehicle();

        LinkedList test = new LinkedList(temporary);

        temporary = new Vehicle();
        test.setNextObject(new LinkedList(temporary));
        System.out.println(((Vehicle) test.getObject()).id);
        System.out.println(((Vehicle) test.getNextObject().getObject()).id);

        System.out.println("");

        System.out.println("Object number of test: " + test.getObjectNumber());
        System.out.println("Object number of next object of test: "
                + test.getNextObject().getObjectNumber());
    }

}
