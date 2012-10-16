/*
 * 練習問題21.7 p.545
 * ArrayListを使用してスタックを実装しなさい。
 * スタック固有の異なるメソッドを提供するために、そのスタックのクラスは、ArrayListのサブクラスとすべきですか。
 * それともArrayListを内部で使用すべきですか。
 *
 * => 内部で使えば良いと思う。
 */

package ch21.ex21_07;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E>
{
    ArrayList<E> arrayList = new ArrayList<E>();
    int index = 0;

    public void push(E pushTarget)
    {
        arrayList.add(pushTarget);
        index++;
    }

    public E pop ()
    {
        if (index == 0)
        {
            throw new EmptyStackException();
        }
        else
        {
            E result = arrayList.get(--index);
            arrayList.set(index, null);
            return result;
        }
    }

    public E peek ()
    {
        if (index == 0)
        {
            throw new EmptyStackException();
        }
        else
        {
            return arrayList.get(index - 1);
        }
    }

    public boolean empty()
    {
        if (index == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int search(E searchTarget)
    {
        for (int i = 1; i <= arrayList.size(); i++)
        {
            if (searchTarget.equals(arrayList.get(arrayList.size() - i)))
            {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        ArrayListStack<String> test = new ArrayListStack<String>();
        System.out.println("empty() on empty stack: " + test.empty());
        System.out.println("push aaa");
        test.push("aaa");
        System.out.println("push bbb");
        test.push("bbb");
        System.out.println("push ccc");
        test.push("ccc");
        System.out.println("empty() on not empty stack: " + test.empty());
        System.out.println("search ccc: " + test.search("ccc"));
        System.out.println("search aaa: " + test.search("aaa"));
        System.out.println("search eee: " + test.search("eee"));
        System.out.println("peek: " + test.peek());
        System.out.println("pop: " + test.pop());
        System.out.println("pop: " + test.pop());
    }

}
