/*
 * 練習問題3.3 p.72
 * オブジェクトの生成の間に、拡張したクラスからの値を使用して、マスクの値を設定するのが重要な場合には、
 * どうすれば問題を回避できますか。
 */

package ch03.ex03_03;

public class Y extends X{

    {
	testShow("pre Y initialize");
    }

    protected int yMask = 0xff00;
    // static protected int yMask = 0xff00;

    {
	testShow("after Y initialize");
    }

    public Y()
    {
	fullMask |= yMask;
	testShow("after Y constructor");
    }

    void testShow(String when)
    {
	System.out.printf("xMask: %04x, yMask: %04x, fullMask: %04x When: %s%n", xMask, yMask, fullMask, when);
    }

    /*
    public static void main(String[] args) {
	Y test = new Y();
    }
    */

}
