/*
 * 練習問題11.2 p.219
 * 第3章のAttrクラスを、ジェネリッククラスとして書き直しなさい。
 */

package ch11.ex11_02;

public class StringAttr extends Attr<String>
{
    StringAttr(String name)
    {
        super(name);
    }

    StringAttr(String name, String value)
    {
        super(name, value);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        StringAttr test1 = new StringAttr("test1");
        test1.setValue("test1.value");
        StringAttr test2 = new StringAttr("test2", "test2.value");

        System.out.println(test1);
        System.out.println(test2);

    }

}
