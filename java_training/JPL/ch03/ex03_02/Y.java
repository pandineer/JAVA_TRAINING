/*
 * 練習問題3.2 p.72
 * テキストのクラスXとクラスYを入力して、各マスクの値の変化を表示するための出力文を追加しなさい。
 * mainメソッドを追加し、実行して結果を見て下さい。（ヒント：16進形式で数値を表示するための書式指定子%xを用いて、
 * 第１章で示したprintfメソッドを使用してください。）
 */

package ch03.ex03_02;

public class Y extends X
{

    {
        testShow("pre Y initialize");
    }

    protected int yMask = 0xff00;

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
        System.out.printf(
                "xMask: %04x, yMask: %04x, fullMask: %04x When: %s%n", xMask,
                yMask, fullMask, when);
    }

    public static void main(String[] args)
    {
        new Y();
    }

}
