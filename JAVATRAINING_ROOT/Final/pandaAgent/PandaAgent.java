package pandaAgent;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;


import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;




public class PandaAgent extends JFrame implements Runnable, ActionListener
{
    // Fixed Parameter
    private final int windowSizeX = 640;
    private final int windowSizeY = 400;
    private final int clockPanelSizeX = 240;
    private final int clockPanelSizeY = 40;
    private final int pandaSizeX = 240;
    private final int pandaSizeY = 380;
    private final int speakSpeed = 50;
    private final int speakQueueSize = 10;
    private final int speakMouthSwitchSpeed = 3;
    private final int pandaSleep = 100;

    // Variable Parameter
    private boolean isProxyEnable = false;
    private String proxyHost = "";
    private int proxyPort = 8080;
    private String proxyUsername = "";
    private String proxyPassword = "";

    // String
    private final String helloString = "Hello, how are you??";

    // Component
    private final BorderLayout balloonInternalLayout = new BorderLayout();
    private final FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
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
    private final JMenuItem menuItemExit = new JMenuItem("Good bye");

    // Popup
    private final JPopupMenu pandaPopup = new JPopupMenu();

    // Other
    private static final long serialVersionUID = -6637964777080358948L;
    private final Queue<String> speakQueue = new LinkedList<String>();
    private final Thread clockThread = new Thread(clockPanel);
    private final Mouse mouse;
    private final NetworkDialog networkDialog = new NetworkDialog(this);
    private boolean isPandaSpeakStop = false;
    /**
     * Show popup menu
     *
     * @param me mouse event for location information
     * @return none
     */
    public void showPopup(MouseEvent me)
    {
        pandaPopup.show(me.getComponent(), me.getX(), me.getY());
    }

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
    public HttpResponse getHttpResponse(String targetURL) throws IOException, ClientProtocolException
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        System.out.println(isProxyEnable);
        if (isProxyEnable)
        {
            HttpHost proxy = null;
            proxy = new HttpHost(proxyHost, proxyPort, "http");
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

            Credentials credentials = new UsernamePasswordCredentials(proxyUsername, proxyPassword);
            AuthScope scope = new AuthScope(proxyHost, proxyPort);
            httpClient.getCredentialsProvider().setCredentials(scope, credentials);
            System.out.println(proxyHost);
            System.out.println(proxyPort);
            System.out.println(proxyUsername);
        }

        HttpGet request = new HttpGet(targetURL);
        HttpResponse httpResponse = null;
        try
        {
            httpResponse = httpClient.execute(request);
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
            throw new ClientProtocolException("In getHttpResponse");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new IOException("In getHttpResponse");
        }

        return httpResponse;
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

        // For Test
        menuPanda.add(menuItemForTest1);
        menuItemForTest1.addActionListener(this);

        // Initialaize window
        this.setUndecorated(true);
        this.setVisible(true);
        this.setVisible(false);
        this.setAlwaysOnTop(true);
        this.setSize(windowSizeX + this.getInsets().left + this.getInsets().right, windowSizeY + this.getInsets().top);
        this.setResizable(false);
        // this.setJMenuBar(menuBar);
        this.setVisible(true);

        contentPane.setLayout(flowLayout);
        this.setLayout(flowLayout);

        // Create Panel
        balloonPanel.setPreferredSize(new Dimension(windowSizeX - pandaSizeX - 5, windowSizeY));
        contentPane.add(balloonPanel);

        rightPanel.setPreferredSize(new Dimension(pandaSizeX, windowSizeY));
        contentPane.add(rightPanel);

        clockPanel.setPreferredSize(new Dimension(clockPanelSizeX, clockPanelSizeY));
        clockThread.start();
        rightPanel.setLayout(flowLayout);
        rightPanel.add(clockPanel);

        pandaPanel.setPreferredSize(new Dimension(pandaSizeX, pandaSizeY));
        rightPanel.add(pandaPanel);

        balloonPanel.setLayout(balloonInternalLayout);
        balloonLeftPadding.setPreferredSize(new Dimension(100, 100));
        balloonLeftPadding.setOpaque(false);
        balloonPanel.add("West", balloonLeftPadding);
        balloonPanel.add("Center", pandaMessageLabel);

        // Initialze Popup
        pandaPopup.add(menuPanda);
        pandaPopup.add(menuPreference);
        pandaPopup.add(menuItemExit);
        menuItemExit.addActionListener(this);

        // Other
        mouse = new Mouse(this);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

        // refresh
        this.setVisible(false);
        this.setVisible(true);

    }

    private synchronized void speak(String givenPhrase)
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
            isPandaSpeakStop = true;
            while(isPandaSpeakStop)
            {
                Thread.sleep(1000);
            }
            pandaMessageLabel.setText("");
            pandaPanel.changeImage("/pandaAgent/panda_agent_close.png");
            isMouthOpen = false;
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
            networkDialog.setVisible(true);
        }
        if (e.getSource() == menuItemForTest1)
        {
            try
            {
                System.out.println(getHttpResponse("http://panda.holy.jp"));
            }
            catch(Exception ex)
            {
                System.out.println("Exception in Test1: " + ex);
            }
        }
        if (e.getSource() == menuItemExit)
        {
            System.exit(0);
        }
    }


    public final boolean isProxyEnable()
    {
        return isProxyEnable;
    }

    public final void setProxyEnable(boolean isProxyEnable)
    {
        this.isProxyEnable = isProxyEnable;
    }

    public final String getProxyHost()
    {
        return proxyHost;
    }

    public final void setProxyHost(String proxyHost)
    {
        this.proxyHost = proxyHost;
    }

    public final int getProxyPort()
    {
        return proxyPort;
    }

    public final void setProxyPort(int proxyPort)
    {
        this.proxyPort = proxyPort;
    }

    public final String getProxyUsername()
    {
        return proxyUsername;
    }

    public final void setProxyUsername(String proxyUsername)
    {
        this.proxyUsername = proxyUsername;
    }

    public final String getProxyPassword()
    {
        return proxyPassword;
    }

    public final void setProxyPassword(String proxyPassword)
    {
        this.proxyPassword = proxyPassword;
    }

    public final boolean isPandaSpeakStop()
    {
        return isPandaSpeakStop;
    }

    public final void setPandaSpeakStop(boolean isPandaSpeakStop)
    {
        this.isPandaSpeakStop = isPandaSpeakStop;
    }

    public static void main(String[] args)
    {
        PandaAgent panda = new PandaAgent();
        Thread mainThread = new Thread(panda);
        mainThread.start();
    }
}
