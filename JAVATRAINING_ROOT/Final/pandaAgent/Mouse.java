package pandaAgent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter
{
    private PandaAgent pandaAgent;
    private MouseEvent startMouse = null;

    public Mouse(PandaAgent pandaAgent)
    {
        this.pandaAgent = pandaAgent;
    }

    public void mouseReleased(MouseEvent e)
    {
        if (MouseEvent.BUTTON1 == e.getButton())
        {
            pandaAgent.setLocation(pandaAgent.getLocation().x + e.getX() - startMouse.getX(),
                    pandaAgent.getLocation().y + e.getY() - startMouse.getY());
            startMouse = null;
        }
        else if (MouseEvent.BUTTON3 == e.getButton())
        {
            ;
        }
    }

    public void mouseDragged(MouseEvent e)
    {
        if (null != startMouse)
        {
            pandaAgent.setLocation(pandaAgent.getLocation().x + e.getX() - startMouse.getX(),
                    pandaAgent.getLocation().y + e.getY() - startMouse.getY());
        }
    }

    public void mousePressed(MouseEvent e)
    {
        if (MouseEvent.BUTTON1 == e.getButton())
        {
            startMouse = e;
        }
        else if (MouseEvent.BUTTON3 == e.getButton())
        {
            if (e.isPopupTrigger())
            {
                ;
            }
        }
    }
}
