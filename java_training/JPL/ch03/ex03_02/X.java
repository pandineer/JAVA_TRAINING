/*
 * 練習問題3.2 p.72
 * テキストのクラスXとクラスYを入力して、各マスクの値の変化を表示するための出力文を追加しなさい。
 * mainメソッドを追加し、実行して結果を見て下さい。（ヒント：16進形式で数値を表示するための書式指定子%xを用いて、
 * 第１章で示したprintfメソッドを使用してください。）
 */

package ch03.ex03_02;

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
