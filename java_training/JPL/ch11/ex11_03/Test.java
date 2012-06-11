/*
 * 練習問題11.3 p.219
 * 練習問題11.2は、良い考えですか。Attrがジェネリックであることは、第4章で定義したAttributedインタフェースに
 * どのように影響しますか。
 * Attributedオブジェクトに対しては、どのような意味を持ちますか。
 */

package ch11.ex11_03;

import java.util.Iterator;

public class Test implements Attributed<String>
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void add(Attr<String> newAttr)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public Attr<String> find(String attrName)
    {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public Attr<String> remove(String attrName)
    {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public Iterator<Attr<String>> attrs()
    {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
