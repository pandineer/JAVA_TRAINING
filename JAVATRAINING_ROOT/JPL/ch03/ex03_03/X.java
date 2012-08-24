/*
 * 練習問題3.3 p.72
 * オブジェクトの生成の間に、拡張したクラスからの値を使用して、マスクの値を設定するのが重要な場合には、
 * どうすれば問題を回避できますか。
 */

package ch03.ex03_03;

public class X {

    {
	testShow("pre X initialize");
    }

    protected int xMask = 0x00ff;
    protected int fullMask;

    {
	testShow("after X initialize");
    }

    public X()
    {
	fullMask = xMask;
	testShow("after X constructor");
    }

    public int mask(int orig)
    {
	return (orig & fullMask);
    }

    void testShow(String when)
    {
	System.out.printf("xMask: %04x, yMask: %04x, fullMask: %04x When: %s%n", xMask, 0, fullMask, when);
    }

}
