package ch22.ex22_13;

public interface Attributed
{
    void add(Attr newAttr);
    Attr find(String attrName);
    Attr remove(String attrName);
    java.util.Iterator<Attr> attrs();
}
