package pandaAgent.extension;

import pandaAgent.PandaAgent;

public class GoogleCalendarToday
{
    private PandaAgent panda;

    // Constructor
    public GoogleCalendarToday(PandaAgent givenPanda)
    {
        panda = givenPanda;
    }

    // Test function
    public void test()
    {
        System.out.println(panda.getHttpResponse());
    }
}
