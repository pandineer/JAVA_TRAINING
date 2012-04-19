package ex03_02;

public class X {

    {
	testShow("pre X initialize");
    }

    protected int xMask = 0x00ff;
    protected int fullMask;

    {
	testShow("after X initialize");
    }

    public X()
    {
	fullMask = xMask;
	testShow("after X constructor");
    }

    public int mask(int orig)
    {
	return (orig & fullMask);
    }

    void testShow(String when)
    {
	System.out.printf("xMask: %04x, yMask: %04x, fullMask: %04x When: %s%n", xMask, 0, fullMask, when);
    }

}
