/*
 * 練習問題10.3 p.204
 * 練習問題6.1の「週の曜日」を表すenumを使用して、曜日を受け取ってその日が働く日であればtrueを返し、そうでなければ
 * falseを返すメソッドを書きなさい。
 * 最初にネストしたif-else文を使用して、それから、switch文を使用しなさい。どちらが明瞭なコードだと考えますか。
 * Answer: switch
 */

package ch10.ex10_03;

import ch10.ex10_03.EnumTest.day;

public class IsWorkingDay
{
    static boolean isWorkingDayIfElse(EnumTest.day target)
    {
        if (target == day.MONDAY || target == day.TUESDAY || target == day.WEDNESDAY || target == day.THURSDAY || target == day.FRIDAY)
        {
            return true;
        }
        else
        {
            if (target == day.SATURDAY || target == day.SUNDAY)
            {
                return false;
            }
            else
            {
                throw new IllegalArgumentException();
            }
        }
    }

    static boolean isWorkingDaySwitch(EnumTest.day target)
    {
        switch (target)
        {
            case MONDAY: case TUESDAY: case WEDNESDAY: case THURSDAY: case FRIDAY:
                return true;
            case SATURDAY: case SUNDAY:
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("monday: " + isWorkingDayIfElse(day.MONDAY));
        System.out.println("tuesday: " + isWorkingDayIfElse(day.TUESDAY));
        System.out.println("wednesday: " + isWorkingDayIfElse(day.WEDNESDAY));
        System.out.println("thursday: " + isWorkingDayIfElse(day.THURSDAY));
        System.out.println("friday: " + isWorkingDayIfElse(day.FRIDAY));
        System.out.println("saturday: " + isWorkingDayIfElse(day.SATURDAY));
        System.out.println("sudnay; " + isWorkingDayIfElse(day.SUNDAY));
        System.out.println("");
        System.out.println("monday: " + isWorkingDaySwitch(day.MONDAY));
        System.out.println("tuesday: " + isWorkingDaySwitch(day.TUESDAY));
        System.out.println("wednesday: " + isWorkingDaySwitch(day.WEDNESDAY));
        System.out.println("thursday: " + isWorkingDaySwitch(day.THURSDAY));
        System.out.println("friday: " + isWorkingDaySwitch(day.FRIDAY));
        System.out.println("saturday: " + isWorkingDaySwitch(day.SATURDAY));
        System.out.println("sudnay; " + isWorkingDaySwitch(day.SUNDAY));
    }

}
