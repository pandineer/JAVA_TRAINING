package pandaAgent;

import java.awt.Color;
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

    // private final BoxLayout boxlayout = new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS);
    // private final GridBagLayout layout = new GridBagLayout();
    private final FlowLayout layout = new FlowLayout();
    private GridBagConstraints gbc = new GridBagConstraints();

    // Component
    private final ImagePanel balloonPanel = new ImagePanel("/pandaAgent/balloon.png");
    // public JPanel balloonPanel = new JPanel();
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

        this.setLayout(layout);
        this.setSize(windowSizeX, windowSizeY);
        this.setResizable(false);
        this.setVisible(true);

        gbc.fill = GridBagConstraints.BOTH;

        // Create Panel
        balloonPanel.setSize((windowSizeX - pandaSizeX), windowSizeY);
        balloonPanel.setPreferredSize(new Dimension(380, 240));
        // balloonPanel.setBackground(Color.red);
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gbc.gridwidth = 3;
        // gbc.gridheight = 1;
        // gbc.anchor = GridBagConstraints.WEST;

        // layout.setConstraints(balloonPanel, gbc);

        // balloonPanel.setSize(100, windowSizeY);
        this.add(balloonPanel);

        // pandaPanel.setSize(pandaSizeX, pandaSizeY);
        // pandaPanel.setSize(pandaSizeX, pandaSizeY);
        pandaPanel.setPreferredSize(new Dimension(pandaSizeX, pandaSizeY));
        gbc.gridx = 3;
        gbc.gridy = 0;
        // gbc.gridwidth = 1;
        // gbc.gridheight = 1;
        // gbc.anchor = GridBagConstraints.EAST;

        // layout.setConstraints(pandaPanel, gbc);
        // pandaPanel.setBounds(400, 0, 240, 420);

        this.add(pandaPanel);

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
