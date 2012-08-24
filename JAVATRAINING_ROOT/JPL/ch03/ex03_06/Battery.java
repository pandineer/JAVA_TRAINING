/*
 * 練習問題3.6 p.86
 * Vehicleを変更して、コンストラクタでVehicleと関連付けされるEnergySourceオブジェクトの参照を持つようにしなさい。
 * EnergySourceクラスはabstractクラスでなければなりません。
 * なぜならば、GasTankオブジェクトの満タンの測定の方法は、Batteryオブジェクトとは異なるでしょう。
 * EnergySourceにabstractのemptyメソッドを入れて、GasTankとBatteryクラスでそのメソッドを実装しなさい。
 * 動力源が空でないことを保証するstartメソッドをVehicleに追加しなさい。
 */

package ch03.ex03_06;

public class Battery extends EnergySource
{
    void empty()
    {
        System.out.println("Battery's empty method");
    }

}
