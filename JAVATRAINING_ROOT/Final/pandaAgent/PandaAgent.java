package pandaAgent;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PandaAgent extends JFrame
{
    // Parameter
    private final int windowSizeX = 640;
    private final int windowSizeY = 420;
    private final int pandaSizeX = 240;
    private final int pandaSizeY = 420;

    private final FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 0);

    // Component
    private final ImagePanel balloonPanel = new ImagePanel("/pandaAgent/balloon.png");
    private final ImagePanel pandaPanel = new ImagePanel("/pandaAgent/panda_agent_close.png");

    public PandaAgent()
    {
        // Set title
        super("Panda Agent");

        // To close window
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        // Initialaize window

        this.setVisible(true);
        this.setVisible(false);

        this.setLayout(layout);
        this.setSize(windowSizeX + this.getInsets().left + this.getInsets().right,
                windowSizeY);
        this.setResizable(false);
        this.setVisible(true);

        System.out.println(this.getInsets().left + " " + this.getInsets().right);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(layout);

        // Create Panel
        balloonPanel.setPreferredSize(new Dimension(windowSizeX - pandaSizeX - 5, windowSizeY));
        // this.add(balloonPanel);
        contentPane.add(balloonPanel);

        pandaPanel.setPreferredSize(new Dimension(pandaSizeX, pandaSizeY));
        // this.add(pandaPanel);
        contentPane.add(pandaPanel);

        balloonPanel.add(new JLabel("Test"));
    }

    /**
     *
     */
    private static final long serialVersionUID = -6637964777080358948L;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        PandaAgent panda = new PandaAgent();
    }

}
