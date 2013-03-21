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
import javax.swing.JWindow;

/*
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
*/

import pandaAgent.extension.GoogleCalendarToday;

public class PandaAgent extends JFrame implements Runnable, ActionListener
{
    // Parameter
    private final int windowSizeX = 640;
    private final int windowSizeY = 420;
    private final int clockPanelSizeX = 240;
    private final int clockPanelSizeY = 40;
    private final int pandaSizeX = 240;
    private final int pandaSizeY = 380;
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
    private final JPanel rightPanel = new JPanel();
    private final ClockPanel clockPanel = new ClockPanel();
    private final ImagePanel pandaPanel = new ImagePanel("/pandaAgent/panda_agent_close.png");
    private final JLabel pandaMessageLabel = new JLabel();
    private final JPanel balloonLeftPadding = new JPanel();

    // Menu
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuPanda = new JMenu("Panda");
    private final JMenuItem menuItemHello = new JMenuItem("Hello");
    private final JMenu menuPreference = new JMenu("Preference");
    private final JMenuItem menuItemNetwork = new JMenuItem("Network...");
    private final JMenuItem menuItemForTest1 = new JMenuItem("Test1");

    // Other
    private static final long serialVersionUID = -6637964777080358948L;
    private final Queue<String> speakQueue = new LinkedList<String>();
    private final Thread clockThread = new Thread(clockPanel);
    private final Mouse mouse;

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

    /**
     * getHttpRequestReponse
     */
    /*
    public HttpResponse getHttpResponse()
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        // TODO: プロクシ関係の実装
        // HttpHost proxy = null;
        // proxy = new HttpHost(PROXY_HOST, PROXY_PORT, "http");
        // httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        // Credentials credentials = new UsernamePasswordCredentials("proxy_username", "proxu_password");
        // AuthScope scope = new AuthScope(PROXY_HOST, PROXY_PORT);
        // httpClient.getCredentialsProvider().setCredentials(scope, credentials);

        // HttpGet request = new HttpGet("http://google.com");
        HttpGet request = new HttpGet("http://panda.holy.jp");
        HttpResponse httpResponse = null;
        try
        {
            httpResponse = httpClient.execute(request);
        }
        catch (Exception e)
        {
            System.out.println("http request execute error. ");
        }
        return httpResponse;
    }
    */

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

        // For Test
        menuPanda.add(menuItemForTest1);
        menuItemForTest1.addActionListener(this);

        setUndecorated(true);
        // Initialaize window
        this.setVisible(true);
        this.setVisible(false);
        this.setSize(windowSizeX + this.getInsets().left + this.getInsets().right, windowSizeY + this.getInsets().top);
        this.setResizable(false);
        this.setJMenuBar(menuBar);
        this.setVisible(true);

        contentPane.setLayout(layout);
        this.setLayout(layout);

        // Create Panel
        balloonPanel.setPreferredSize(new Dimension(windowSizeX - pandaSizeX - 5, windowSizeY));
        contentPane.add(balloonPanel);

        rightPanel.setPreferredSize(new Dimension(pandaSizeX, windowSizeY));
        contentPane.add(rightPanel);

        clockPanel.setPreferredSize(new Dimension(clockPanelSizeX, clockPanelSizeY));
        clockThread.start();
        rightPanel.setLayout(layout);
        rightPanel.add(clockPanel);

        pandaPanel.setPreferredSize(new Dimension(pandaSizeX, pandaSizeY));
        rightPanel.add(pandaPanel);

        balloonPanel.setLayout(balloonInternalLayout);
        balloonLeftPadding.setPreferredSize(new Dimension(100, 100));
        balloonLeftPadding.setOpaque(false);
        balloonPanel.add("West", balloonLeftPadding);
        balloonPanel.add("Center", pandaMessageLabel);

        // Other
        mouse = new Mouse(this);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

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
        if (e.getSource() == menuItemNetwork)
        {
            // TODO: please implement
        }
        if (e.getSource() == menuItemForTest1)
        {
            GoogleCalendarToday tmp = new GoogleCalendarToday(this);
            tmp.test();
        }
    }


    public static void main(String[] args)
    {
        PandaAgent panda = new PandaAgent();
        Thread mainThread = new Thread(panda);
        mainThread.start();
    }
}
