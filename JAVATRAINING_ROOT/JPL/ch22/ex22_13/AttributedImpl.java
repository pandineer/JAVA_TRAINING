package ch22.ex22_13;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributedImpl implements Attributed, Iterable<Attr>
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
        return attrTable.remove(name);
    }

    @Override
    public Iterator<Attr> attrs()
    {
        return attrTable.values().iterator();
    }

}
