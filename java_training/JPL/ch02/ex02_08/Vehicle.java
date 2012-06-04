/*
 * 練習問題2.8 p.47
 * LinkedListにはどのようなコンストラクタを追加すべきですか。
 */

package ch02.ex02_08;

public class Vehicle
{
    public double currentSpeed;
    double currentDirection;
    String owner;

    public static int nextID = 1;
    final int id = nextID++;

}
