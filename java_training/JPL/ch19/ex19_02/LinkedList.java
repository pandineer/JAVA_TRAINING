/*
 * 練習問題19.2 p.433
 * privateメンバーも含むように練習問題19.1を修正しなさい。
 * (privateメンバーを含む)完全なjavadocを生成して、誰かにそのクラスを説明してもらいなさい。
 * その人が説明できるようになるまで、必要ならばコメントを書きなおして、同じ事を繰り返しなさい。
 */

/*
 * 練習問題19.1 p.433
 * 練習問題2.16で作成したLinkedListクラスにドキュメンテーションコメントを追加しなさい。
 * javadocを生成して、誰かにそのクラスを使用したサンプルプログラムを書いてもらいなさい。
 * その人がサンプルプログラムを書けるまで、必要ならばコメントを書きなおして、同じ事を繰り返しなさい。
 */

/*
 * 練習問題2.16 p.59
 * リスト内の要素の数を返すメソッドをLinkedListに追加しなさい。
 */

package ch19.ex19_02;

/**
 * A <code>LinkedList</code> object has an object and link to next <code>LinkedList</code> object.
 *
 * @version 1.1
 * @author Miyahara
 * @since 1.0
 */
public class LinkedList
{

    /** The object. **/
    private Object object;
    /** The link to next <code>LikedList</code> object. **/
    private LinkedList nextObject;

    /**
     * Return number of how many object linked from this object.
     */
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

    /** Return this object. **/
    public Object getObject()
    {
        return object;
    }

    /** Set object. **/
    public void setObject(Object target)
    {
        object = target;
    }

    /** Return Linked object. */
    public LinkedList getNextObject()
    {
        return nextObject;
    }

    /** Set linked object. */
    public void setNextObject(LinkedList target)
    {
        nextObject = target;
    }

    /**
     * Create a new <code>LinkedList</code> object with associated object.
     * @param associatedObject Object for this LinkedList
     */
    LinkedList(Object associatedObject)
    {
        object = associatedObject;
        nextObject = null;
    }

    /** <code>toString()</code> is overrided for practice. */
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
