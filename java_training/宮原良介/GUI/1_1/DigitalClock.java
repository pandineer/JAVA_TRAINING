import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;
import java.util.Calendar;


public class DigitalClock extends Frame implements WindowListener, Runnable
{
    static int h;       // 時
    static int m;       // 分
    static int s;       // 秒
    Calendar now = Calendar.getInstance();
    static Thread th;

    public DigitalClock(String title)
    {
        super(title);
        addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        dispose();

    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void windowActivated(WindowEvent e)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void run()
    {
        while(true)
        {
            h = now.getInstance().get(now.HOUR_OF_DAY);
            m = now.getInstance().get(now.MINUTE);
            s = now.getInstance().get(now.SECOND);
            repaint();

            try
            {
                th.sleep(1000); // スリープ1秒
            }
            catch(InterruptedException e)
            {
            }
        }

    }

    public void paint(Graphics g)
    {
        g.drawString(h+":"+m+":"+s, 50, 59);
    }


    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DigitalClock window = new DigitalClock("Test");

        th = new Thread(window);

        window.setSize(200, 100);
        window.setVisible(true);

        th.start();     // スレッドスタート

    }



}
