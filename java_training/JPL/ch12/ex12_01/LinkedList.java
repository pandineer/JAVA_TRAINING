/*
 * 練習問題12.1 p.249
 * 以前の練習問題で作成したLinkedListクラスに対するObjectNotFoundExceptionクラスを作成しなさい。
 * リスト中のオブジェクトを探すfindメソッドを追加して、要求されたオブジェクトが含まれるLinkedListオブジェクトを返すか、
 * オブジェクトがリスト中に発見されなければその例外をスローするようにしなさい。
 * オブジェクトが発見されなかった時に、nullを返すより、例外をスローする方がなぜ好ましいですか。
 * なにか付け加えるとしたら、ObjectNotFoundExceptionはどのような追加データを保持すべきですか。
 *
 * answer: なぜ好ましいか：プログラマが例外処理を忘れない、例外発生時のデータを保持できる
 * answer: 追加すべきデータ：探していたオブジェクトを保持すべき
 */

/*
 * 練習問題11.1 p.219
 * 練習問題2.2で始めたLinkedListクラスを見直して、ジェネリッククラスとして書き直しなさい。
 */

/*
 * 練習問題2.2 p.37
 * LinkedListクラスを作成しなさい。LinkeListクラスは、Object型のフィールドと、リストの中で次のLinkedList要素への参照を持ちます。
 */

package ch12.ex12_01;

public class LinkedList<E>
{
    E object;
    LinkedList<E> nextObject;

    public LinkedList<E> find (E findTarget) throws ObjectNotFoundException
    {
        LinkedList<E> temp = this;
        while (temp != null)
        {
            if (findTarget == temp.object)
            {
                return temp;
            }
            temp = temp.nextObject;
        }
        throw new ObjectNotFoundException(findTarget);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        LinkedList<String> testList = new LinkedList<String>();

        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";

        testList.object = test1;
        testList.nextObject = new LinkedList<String>();
        testList.nextObject.object = test2;
        try
        {
            System.out.println(testList.find(test2));
            System.out.println(testList.find(test3));
        }
        catch (ObjectNotFoundException e)
        {
            System.out.println("Exception occurs!!");
            System.out.println(e);
            System.out.println(e.findTarget);
        }

    }

}
