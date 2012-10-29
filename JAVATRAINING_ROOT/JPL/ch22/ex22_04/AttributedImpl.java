/*
 * 練習問題22.4 p.563
 * オブザーバーへ変化を通知するのにObserver/Observableを使用するAttributedインタフェースの実装を提供しなさい。
 */

package ch22.ex22_04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AttributedImpl extends Observable implements Attributed, Iterable<Attr>
{
    protected Map<String, Attr> attrTable = new HashMap<String, Attr>();

    @Override
    public Iterator<Attr> iterator()
    {
        return attrs();
    }

    @Override
    public void add(Attr newAttr)
    {
        setChanged();
        notifyObservers(newAttr.getName() + " is added. ");
        attrTable.put(newAttr.getName(), newAttr);
    }

    @Override
    public Attr find(String name)
    {
        return attrTable.get(name);
    }

    @Override
    public Attr remove(String name)
    {
        setChanged();
        notifyObservers(name + " is removed. ");
        return attrTable.remove(name);
    }

    @Override
    public Iterator<Attr> attrs()
    {
        return attrTable.values().iterator();
    }

}
