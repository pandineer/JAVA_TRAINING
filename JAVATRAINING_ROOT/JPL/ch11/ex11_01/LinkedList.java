/*
 * 練習問題11.1 p.219
 * 練習問題2.2で始めたLinkedListクラスを見直して、ジェネリッククラスとして書き直しなさい。
 */

/*
 * 練習問題2.2 p.37
 * LinkedListクラスを作成しなさい。LinkeListクラスは、Object型のフィールドと、リストの中で次のLinkedList要素への参照を持ちます。
 */

package ch11.ex11_01;

public class LinkedList<E>
{
    E object;
    LinkedList<E> nextObject;

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        LinkedList<String> test1 = new LinkedList<String>();
        test1.object = "test1";
        test1.nextObject = new LinkedList<String>();
        test1.nextObject.object = "test2";
        System.out.println(test1.object);
        System.out.println(test1.nextObject.object);

    }

}
