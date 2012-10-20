/*
 * 練習問題21.4 p.537
 * ListIteratorオブジェクトをフィルターするためにListIteratorを実装したShortStringsの別のバージョンを作成しなさい。
 * そのクラスは、ShortStringsを拡張すべきですか。
 *
 * 拡張しないほうがよいと思う。
 * 扱われるIteratorがメソッドによって変わってしまうため。
 */

package ch21.ex21_04;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListShortStrings implements ListIterator<String>
{
    private ListIterator<String> strings; // 元の文字列
    private String nextShort; // 次が不明ならばnull
    private String previousShort; // 前がないならnull
    private final int maxLen; // この長さ以下の文字列だけを返す

    public ListShortStrings(ListIterator<String> strings, int maxLen)
    {
        this.strings = strings;
        this.maxLen = maxLen;
    }

    public boolean hasNext()
    {
        if (nextShort != null) // すでに見つけている
        {
            return true;
        }
        while (strings.hasNext())
        {
            nextShort = strings.next();
            if (nextShort.length() <= maxLen)
            {
                return true;
            }
        }
        nextShort = null; // 見つけられなかった
        return false;
    }

    public String next()
    {
        if (nextShort == null && !hasNext())
        {
            throw new NoSuchElementException();
        }
        String n = nextShort; // nextShortを記憶する
        nextShort = null; // nextShortを消費する
        return n; // nextShortを返す
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasPrevious()
    {
        if (previousShort != null) // すでに見つけている
        {
            return true;
        }
        while (strings.hasPrevious())
        {
            previousShort = strings.previous();
            if (previousShort.length() <= maxLen)
            {
                return true;
            }
        }
        previousShort = null; // 見つけられなかった
        return false;
    }

    @Override
    public String previous()
    {
        if (previousShort == null && !hasPrevious())
        {
            throw new NoSuchElementException();
        }
        String p = previousShort; // previousShortを記憶する
        previousShort = null; // previousShortを消費する
        return p;
    }

    @Override
    public int nextIndex()
    {
        if (nextShort != null) // すでに見つけている
        {
            return strings.nextIndex();
        }
        while (strings.hasNext())
        {
            int nextIndex = strings.nextIndex();
            nextShort = strings.next();
            if (nextShort.length() <= maxLen)
            {
                return nextIndex;
            }
        }
        // 見つけられなかった
        return 0;
    }

    @Override
    public int previousIndex()
    {
        if (previousShort != null) // すでに見つけている
        {
            return strings.previousIndex();
        }
        while (strings.hasPrevious())
        {
            int previousIndex = strings.previousIndex();
            previousShort = strings.previous();
            if (previousShort.length() <= maxLen)
            {
                return previousIndex;
            }
        }
        // 見つけられなかった
        return 0;
    }

    @Override
    public void set(String e)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(String e)
    {
        throw new UnsupportedOperationException();
    }




    public static void main(String[] args)
    {
        ArrayList<String> testArrayList = new ArrayList<String>();
        testArrayList.add("aaa");
        testArrayList.add("bbbbbbbbbb");
        testArrayList.add("ccccc");
        testArrayList.add("dd");
        testArrayList.add("eeeeeeeeee");

        ListShortStrings test = new ListShortStrings(testArrayList.listIterator(), 5);

        System.out.println(test.nextIndex());
        System.out.println(test.next());
        System.out.println(test.nextIndex());
        System.out.println(test.next());
        System.out.println(test.nextIndex());
        System.out.println(test.next());

        System.out.println(test.previousIndex());
        System.out.println(test.previous());
        System.out.println(test.previousIndex());
        System.out.println(test.previous());
        System.out.println(test.previousIndex());
        System.out.println(test.previous());

    }

}
