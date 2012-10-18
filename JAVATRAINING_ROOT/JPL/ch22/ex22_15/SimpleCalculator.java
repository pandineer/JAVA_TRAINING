package ch22.ex22_15;

import java.util.Stack;

public class SimpleCalculator
{
    static Stack<Object> stack = new Stack<Object>();
    static String tmp = null;
    static String[] splitString;

    // need Reversed Polish Notation(delimiter = " ")
    static double calc(String expression)
    {

        splitString = expression.split(" ");

        for (int i = 0; i < splitString.length; i++)
        {
            
            
            stack.push(splitString[i]);
        }

       //  return calcReversedPolish();
    }

    static double calcReversedPolish()
    {
        tmp = stack.pop().toString();

        switch (tmp.toString().charAt(0))
        {
            case '+':
                return (calcReversedPolish() + calcReversedPolish());
            case '-':
                return (calcReversedPolish() - calcReversedPolish());
            case '*':
                return (calcReversedPolish() * calcReversedPolish());
            case '/':
                return (calcReversedPolish() / calcReversedPolish());
            case '%':
                return (calcReversedPolish() % calcReversedPolish());
        }

        if (tmp.equals("sin"))
            return Math.sin(calcReversedPolish());
        if (tmp.equals("cos"))
            return Math.cos(calcReversedPolish());
        if (tmp.equals("tan"))
            return Math.tan(calcReversedPolish());
        if (tmp.equals("asin"))
            return Math.asin(calcReversedPolish());
        if (tmp.equals("acos"))
            return Math.acos(calcReversedPolish());
        if (tmp.equals("atan"))
            return Math.atan(calcReversedPolish());
        if (tmp.equals("atan2"))
            return Math.atan2(calcReversedPolish(), calcReversedPolish());
        if (tmp.equals("toRadians"))
            return Math.toRadians(calcReversedPolish());
        if (tmp.equals("toDegrees"))
            return Math.toDegrees(calcReversedPolish());
        if (tmp.equals("exp"))
            return Math.exp(calcReversedPolish());
        if (tmp.equals("sinh"))
            return Math.sinh(calcReversedPolish());
        if (tmp.equals("cosh"))
            return Math.cosh(calcReversedPolish());
        if (tmp.equals("tanh"))
            return Math.tanh(calcReversedPolish());
        if (tmp.equals("pow"))
            return Math.pow(calcReversedPolish(), calcReversedPolish());
        if (tmp.equals("log"))
            return Math.log(calcReversedPolish());
        if (tmp.equals("log10"))
            return Math.log10(calcReversedPolish());
        if (tmp.equals("sqrt"))
            return Math.sqrt(calcReversedPolish());
        if (tmp.equals("cbrt"))
            return Math.cbrt(calcReversedPolish());
        if (tmp.equals("signum"))
            return Math.signum(calcReversedPolish());
        if (tmp.equals("ceil"))
            return Math.ceil(calcReversedPolish());
        if (tmp.equals("floor"))
            return Math.floor(calcReversedPolish());
        if (tmp.equals("rint"))
            return Math.rint(calcReversedPolish());
        if (tmp.equals("round"))
            return Math.round(calcReversedPolish());
        if (tmp.equals("abs"))
            return Math.abs(calcReversedPolish());
        if (tmp.equals("max"))
            return Math.max(calcReversedPolish(), calcReversedPolish());
        if (tmp.equals("min"))
            return Math.min(calcReversedPolish(), calcReversedPolish());
        if (tmp.equals("hypot"))
            return Math.hypot(calcReversedPolish(), calcReversedPolish());


        return Double.valueOf(tmp);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println(SimpleCalculator.calc("1 2 + 3 4 - +"));
        System.out.println(SimpleCalculator.calc("100 200 max 200 5000 min +"));
        System.out.println(SimpleCalculator.calc("4 sqrt 8 pow"));
    }

}
