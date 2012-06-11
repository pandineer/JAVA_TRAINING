/*
 * 練習問題11.3 p.219
 * 練習問題11.2は、良い考えですか。Attrがジェネリックであることは、第4章で定義したAttributedインタフェースに
 * どのように影響しますか。
 * Attributedオブジェクトに対しては、どのような意味を持ちますか。
 */

package ch11.ex11_03;

public interface Attributed<E>
{
    void add(Attr<E> newAttr);
    Attr<E> find(String attrName);
    Attr<E> remove(String attrName);
    java.util.Iterator<Attr<E>> attrs();
}
