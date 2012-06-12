package gui1_3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickMenu extends MouseAdapter
{
    DigitalClock digitalClock;

    RightClickMenu(DigitalClock digitalClock)
    {
        this.digitalClock = digitalClock;
    }

    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            System.out.println(e);
            digitalClock.popup.show(digitalClock, 0, 0);
        }
    }
}
