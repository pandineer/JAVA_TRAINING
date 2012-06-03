/*
 * 練習問題3.8 p.92
 * VehicleとPassengerVehicleをCloneable型にしなさい。複製に関して、４つの選択肢のどれを選択すべきでしょうか。
 * Object.cloneによる単純なコピーは、それらのクラスのcloneメソッドとしては正しいですか。
 */

package ch03.ex03_08;

public class PassengerVehicle extends Vehicle implements Cloneable
{
    private int sheet;
    private int passenger;

    public PassengerVehicle clone()
    {
        PassengerVehicle temp = (PassengerVehicle)super.clone();
        temp.sheet = sheet;
        temp.passenger = passenger;
        return temp;
    }

    PassengerVehicle(int capacity, int people)
    {
        sheet = capacity;
        passenger = people;
    }

    public int getSheet()
    {
        return sheet;
    }

    public int getPassenger()
    {
        return passenger;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        PassengerVehicle test1 = new PassengerVehicle(4, 3);
        System.out.println("Sheets number: " + test1.getSheet());
        System.out.println("Passenger number: " + test1.getPassenger());

        System.out.println("");

        PassengerVehicle test2 = new PassengerVehicle(8, 5);
        System.out.println("Sheets number: " + test2.getSheet());
        System.out.println("Passenger number: " + test2.getPassenger());

        PassengerVehicle test3;

        test3 = (PassengerVehicle) test2.clone();

        System.out.println("");

        System.out.println("clone result: sheet: " + test3.getSheet()
                + ", passenger: " + test3.getPassenger());

        System.out.println(test1.getId());
        System.out.println(test2.getId());
        System.out.println(test3.getId());


    }

}
