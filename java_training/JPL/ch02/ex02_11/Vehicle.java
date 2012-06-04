/*
 * 練習問題2.11 p.51
 * LinkedListにtoStringメソッドを追加しなさい。
 */

package ch02.ex02_11;

public class Vehicle
{
    public double currentSpeed;
    double currentDirection;
    String owner;

    public static int nextID = 1;
    final int id = nextID++;

}
