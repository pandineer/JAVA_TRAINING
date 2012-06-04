/*
 * 練習問題3.4 p.84
 * もし、あるとしたら、VehicleとPassengerVehicleのどのメソッドをfinalにするのが適切ですか。
 */

package ch03.ex03_04;

public class PassengerVehicle extends Vehicle
{
    private int sheet;
    private int passenger;

    PassengerVehicle(int capacity, int people)
    {
        sheet = capacity;
        passenger = people;
    }

    final public int getSheet()
    {
        return sheet;
    }

    final public int getPassenger()
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

        System.out.println("");

        System.out.println(Vehicle.showCurrentID());

    }

}
