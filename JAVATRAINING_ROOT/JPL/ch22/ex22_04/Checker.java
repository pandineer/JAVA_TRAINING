/*
 * 練習問題22.4 p.563
 * オブザーバーへ変化を通知するのにObserver/Observableを使用するAttributedインタフェースの実装を提供しなさい。
 */

package ch22.ex22_04;

import java.util.Observable;
import java.util.Observer;

public class Checker implements Observer
{
    AttributedImpl attrImpl;

    public Checker(AttributedImpl attrImpl)
    {
        this.attrImpl = attrImpl;
        this.attrImpl.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println("Checker's update method is invoked with " + arg.toString());
    }

    public static void main(String[] args)
    {
        AttributedImpl test = new AttributedImpl();
        Checker checker = new Checker(test);
        test.add(new Attr("test_name", "test_value"));
    }
}
