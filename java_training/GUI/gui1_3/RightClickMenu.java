package gui1_3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickMenu extends MouseAdapter
{
    private DigitalClock digitalClock;

    RightClickMenu(DigitalClock digitalClock)
    {
        this.digitalClock = digitalClock;
    }

    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            digitalClock.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
