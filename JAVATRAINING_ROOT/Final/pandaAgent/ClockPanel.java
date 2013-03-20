package pandaAgent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ClockPanel extends JPanel implements Runnable
{
    private static final long serialVersionUID = -6446497458277303981L;
    private Date currentDate;
    private SimpleDateFormat simpleDataFormat = new SimpleDateFormat("M/dd HH:mm:ss");

    private String fontType = "Times New Roman";
    private int fontStyle = Font.PLAIN;
    private Integer fontSize = 40;
    private Color fontColor = Color.black;
    private Font fontSetting = new Font(fontType, fontStyle, fontSize);
    private Color backgroundColor = Color.white;

    public ClockPanel()
    {
        // Initialize time
        currentDate = new Date();
    }

    @Override
    public void paintComponent(Graphics g) {
        // set background
        g.setColor(backgroundColor);
        g.fillRect(0, 0, 240, 80);

        // draw date
        g.setColor(Color.black);
        g.setFont(fontSetting);
        g.drawString(simpleDataFormat.format(currentDate), 0, g.getFontMetrics().getMaxAscent());
        g.setColor(Color.black);
        // g.drawLine(0, 0, 240, 40); // base line for debug
    }

    @Override
    public void run()
    {
        while(true)
        {
            currentDate = new Date();
            repaint();
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println("Thread.sleep in ClockPanel->run");
                System.out.println(e);
            }
        }
    }
}
