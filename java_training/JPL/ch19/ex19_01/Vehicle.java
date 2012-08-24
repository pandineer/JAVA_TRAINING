/*
 * 練習問題19.1 p.433
 * 練習問題2.16で作成したLinkedListクラスにドキュメンテーションコメントを追加しなさい。
 * javadocを生成して、誰かにそのクラスを使用したサンプルプログラムを書いてもらいなさい。
 * その人がサンプルプログラムを書けるまで、必要ならばコメントを書きなおして、同じ事を繰り返しなさい。
 */

/*
 * 練習問題2.16 p.59
 * リスト内の要素の数を返すメソッドをLinkedListに追加しなさい。
 */

package ch19.ex19_01;

public class Vehicle
{
    public double currentSpeed;
    double currentDirection;
    String owner;

    public static int nextID = 1;
    final int id = nextID++;

}
