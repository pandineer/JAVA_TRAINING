package pandaAgent;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PandaAgent extends JFrame implements Runnable, ActionListener
{
    // Parameter
    private final int windowSizeX = 640;
    private final int windowSizeY = 420;
    private final int pandaSizeX = 240;
    private final int pandaSizeY = 420;
    private final int speakSpeed = 50;
    private final int speakQueueSize = 10;
    private final int speakMouthSwitchSpeed = 3;
    private final int pandaSleep = 100;

    // String
    private final String helloString = "Hello, how are you??";

    // Component
    private final BorderLayout balloonInternalLayout = new BorderLayout();
    private final FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
    private final Container contentPane = this.getContentPane();
    private final ImagePanel balloonPanel = new ImagePanel("/pandaAgent/balloon.png");
    private final ImagePanel pandaPanel = new ImagePanel("/pandaAgent/panda_agent_close.png");
    private final JLabel pandaMessageLabel = new JLabel();
    private final JPanel balloonLeftPadding = new JPanel();

    // Menu
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuPanda = new JMenu("Panda");
    private final JMenuItem menuItemHello = new JMenuItem("Hello");
    private final JMenu menuPreference = new JMenu("Preference");
    private final JMenuItem menuItemNetwork = new JMenuItem("Network...");

    // Other
    private static final long serialVersionUID = -6637964777080358948L;
    private final Queue<String> speakQueue = new LinkedList<String>();

    /**
     * Request panda to speak.
     *
     *  @param requestPhrase Panda speaks this phrase, not null
     *  @return when speak queue is full, returns false
     */
    public boolean speakRequest(String requestPhrase)
    {
        if (null == requestPhrase)
        {
            throw new NullPointerException("Please give phrase. ");
        }
        if (speakQueue.size() < speakQueueSize)
        {
            speakQueue.add(requestPhrase);
            return true;
        }
        else
        {
            return false;
        }
    }

    // Constructor
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

        // Initialize menu bar
        menuBar.add(menuPanda);
        menuBar.add(menuPreference);
        menuPanda.add(menuItemHello);
        menuItemHello.addActionListener(this);
        menuPreference.add(menuItemNetwork);
        menuItemNetwork.addActionListener(this);

        // Initialaize window
        this.setVisible(true);
        this.setVisible(false);
        this.setSize(windowSizeX + this.getInsets().left + this.getInsets().right, windowSizeY + this.getInsets().top);
        this.setResizable(false);
        this.setJMenuBar(menuBar);
        this.setVisible(true);

        contentPane.setLayout(layout);

        // Create Panel
        balloonPanel.setPreferredSize(new Dimension(windowSizeX - pandaSizeX - 5, windowSizeY));
        contentPane.add(balloonPanel);

        pandaPanel.setPreferredSize(new Dimension(pandaSizeX, pandaSizeY));
        contentPane.add(pandaPanel);

        balloonPanel.setLayout(balloonInternalLayout);
        balloonLeftPadding.setPreferredSize(new Dimension(100, 100));
        balloonLeftPadding.setOpaque(false);
        balloonPanel.add("West", balloonLeftPadding);
        balloonPanel.add("Center", pandaMessageLabel);
    }

    private void speak(String givenPhrase)
    {
        StringBuilder speakPhrase = new StringBuilder();
        boolean isMouthOpen = true;

        try
        {
            for (int i = 0; i < givenPhrase.length(); i++)
            {
                speakPhrase.append(givenPhrase.charAt(i));
                pandaMessageLabel.setText(speakPhrase.toString());
                Thread.sleep(speakSpeed);
                if ((i % speakMouthSwitchSpeed) == 0)
                {
                    if (isMouthOpen)
                    {
                        pandaPanel.changeImage("/pandaAgent/panda_agent_close.png");
                        isMouthOpen = false;
                    }
                    else
                    {
                        pandaPanel.changeImage("/pandaAgent/panda_agent_open.png");
                        isMouthOpen = true;
                    }
                }
                else
                {
                    // do nothind
                }
                pandaPanel.repaint();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void run()
    {
        String speak = null;
        while(true)
        {
            try
            {
                Thread.sleep(pandaSleep);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            speak = speakQueue.poll();
            if (null != speak)
            {
                this.speak(speak);
            }
            else
            {
                ; // nothig
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == menuItemHello)
        {
            speakRequest(helloString);
        }
    }


    public static void main(String[] args)
    {
        PandaAgent panda = new PandaAgent();
        Thread mainThread = new Thread(panda);
        mainThread.start();
    }
}
