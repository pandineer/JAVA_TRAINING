/*
 * 課題2-1
 * SwingのJFrameを使用して、時間を表示するデジタル時計（アナログ時計は不可）を作成してください。
 *  - javax.swing.JFrameを使用する
 *  - Windowsの普通のアプリケーションと同様にタイトルバーの「X」ボタンをクリックすると終了する。
 *  - デジタル時計の描画は、paintComponentメソッド内でGraphicsを使用して行う。テキストラベルによる単なる表示は、不可。
 */

package gui02_01;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DigitalClock extends JFrame implements Runnable
{
    private static final long serialVersionUID = 1L;
    private Thread th;
    private JPanel drawPanel;

    private Date currentDate;
    private SimpleDateFormat simpleDataFormat = new SimpleDateFormat("HH:mm:ss");

    // Constructor
    public DigitalClock()
    {
        // Set title
        super("JDigitalClock");

        // To close window
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        // Initialize

        // Initialaize window
        this.setSize(220, 150);
        this.setResizable(false);
        this.setVisible(true);

        // Initialize drawPanel
        drawPanel = new JPanel();

        // Initialize time
        currentDate = new Date();
    }

    @Override
    public void run()
    {
        while(true)
        {
            drawPanel.repaint();
            System.out.println("!");
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println("Error occurs at sleep: " + e);
            }

        }
    }

    @Override
    public void paintComponents(Graphics g)
    {
        System.out.println("1");
        super.paintComponents(g);
        g.drawString(simpleDataFormat.format(currentDate), 10, 10);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DigitalClock window = new DigitalClock();
        window.th = new Thread(window);
        window.th.start(); // スレッドスタート
    }
}
