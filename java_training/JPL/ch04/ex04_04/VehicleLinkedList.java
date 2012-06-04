/*
 * 練習問題4.4 p.114
 * インタフェースのみ使用して、コレクションクラス階層を設計しなさい。
 */

package ch04.ex04_04;

public interface VehicleLinkedList
{
    int getVehicleNumber();

    Vehicle getVehicle();

    void setVehicle(Vehicle target);

    VehicleLinkedList getNextVehicle();

    void setNextVehicle(VehicleLinkedListImpl target);
}
