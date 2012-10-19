/*
 * 課題2-2
 *  デジタル時計に次の機能追加を行なってください
 *   - メニューを付けて、プロパティダイアログを開ける
 *   - プロパティダイアログでは、以下の項目を設定できる
 *     1. フォントの指定
 *     2. フォントサイズの指定
 *     3. 文字色の指定（色がわかるようにカラーチップも表示すること）
 *     4. 時計の背景色の指定
 *   - フォントとフォントサイズを変更すると、時計を表示すべきフレームの大きさを適切に自動変更して、正しく表示されるようにする
 */

/*
 * 課題2-1
 * SwingのJFrameを使用して、時間を表示するデジタル時計（アナログ時計は不可）を作成してください。
 *  - javax.swing.JFrameを使用する
 *  - Windowsの普通のアプリケーションと同様にタイトルバーの「X」ボタンをクリックすると終了する。
 *  - デジタル時計の描画は、paintComponentメソッド内でGraphicsを使用して行う。テキストラベルによる単なる表示は、不可。
 */

package gui02_02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DigitalClock extends JFrame implements Runnable, ActionListener
{
    private static final long serialVersionUID = 1L;
    private Thread th;
    private DrawPanel drawPanel;

    private Date currentDate;
    private SimpleDateFormat simpleDataFormat = new SimpleDateFormat("HH:mm:ss");

    private String fontType = "TimesRoman";
    private int fontStyle = Font.PLAIN;
    private Integer fontSize = 48;
    private Color fontColor = Color.black;
    private Font fontSetting = new Font(fontType, fontStyle, fontSize);
    private Color backgroundColor = Color.white;

    private int windowSizeX = 48 * 8 + 50;
    private int windowSizeY = 48 + 50;

    private PropertyDialog dialog;

    private Menu menuMenu;
    private MenuBar menuBar;
    private MenuItem menuProperty;

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
        this.setSize(windowSizeX, windowSizeY);
        this.setResizable(true);
        this.setVisible(true);

        // Initialize drawPanel
        drawPanel = new DrawPanel();
        this.add(drawPanel);

        // Initialize time
        currentDate = new Date();

        // create menu bar
        menuBar = new MenuBar();
        setMenuBar(menuBar);

        // [Menu]
        menuMenu = new Menu("Menu");
        menuMenu.addActionListener(this);
        menuBar.add(menuMenu);

        // [Menu] - [Property]
        menuProperty = new MenuItem("Property");
        menuMenu.add(menuProperty);

        // create property dialog
        dialog = new PropertyDialog(this);
    }

    @Override
    public void run()
    {
        while(true)
        {
            drawPanel.repaint();
            this.setSize(windowSizeX, windowSizeY);
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

    public class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            // get current time
            currentDate = new Date();

            // set font setting
            fontSetting = new Font(fontType, fontStyle, fontSize);

            // set settings
            g.setFont(fontSetting);
            g.setColor(fontColor);

            // ウィンドウサイズの計算
            if (null != g)
            {
            windowSizeX = g.getFontMetrics().stringWidth(simpleDataFormat.format(currentDate));
            windowSizeX += getInsets().left;
            windowSizeX += getInsets().right;
            System.out.println("WindowSizeX: " + windowSizeX);
            }

            if (null != g)
            {
            windowSizeY = g.getFontMetrics().getAscent();
            windowSizeY += g.getFontMetrics().getDescent();
            windowSizeY += g.getFontMetrics().getLeading();
            // windowSizeY *= 2; // キャプチャした時刻用
            windowSizeY += getInsets().top;
            System.out.println("WindowSizeY: " + windowSizeY);
            }
            // this.setSize(500, 500);

            // set background
            g.setColor(backgroundColor);
            g.fillRect(0, 0, windowSizeX, windowSizeY);

            g.drawString(simpleDataFormat.format(currentDate), 30, 120);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand() == "Property")
        {
            // クリックしたのが「Property」だったら
            dialog.resetParameter();
            dialog.setVisible(true);
        }
        else
        {
            System.out.println("actionPerformed error");
        }
    }


    public String getFontType()
    {
        return fontType;
    }

    public void setFontType(String fontType)
    {
        this.fontType = fontType;
    }

    public int getFontStyle()
    {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle)
    {
        this.fontStyle = fontStyle;
    }

    public Integer getFontSize()
    {
        return fontSize;
    }

    public void setFontSize(Integer fontSize)
    {
        this.fontSize = fontSize;
    }

    public Color getFontColor()
    {
        return fontColor;
    }

    public void setFontColor(Color fontColor)
    {
        this.fontColor = fontColor;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
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
