/*
 * 練習問題2.8 p.47
 * LinkedListにはどのようなコンストラクタを追加すべきですか。
 */

package ch02.ex02_08;

public class LinkedList
{
    Object object;
    LinkedList nextObject;

    LinkedList(Object associatedObject)
    {
        object = associatedObject;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        LinkedList test = new LinkedList(new Vehicle());

        test.nextObject = new LinkedList(new Vehicle());
        System.out.println(((Vehicle) test.object).id);
        System.out.println(((Vehicle) test.nextObject.object).id);

    }

}
