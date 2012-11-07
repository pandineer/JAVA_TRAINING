/*
 * 課題1-3
 * 課題1-2のデジタル時計を、次のように修正してください。
 * ・FrameではなくWindowクラスを使用して、フレーム枠のないデジタル時計にする。
 * ・課題1-2のダイアログで指定できた属性は、マウスの右クリックでポップアップメニューを表示して、
 * 　カスケード形式で選択できるようにする（ダイアログは開かない）。
 * 時計内をマウスの左ボタンでクリックしたまま、デスクトップ上でウィンドウを移動させることができるようにする。
 */

/*
 * 課題1-2
 * デジタル時計に次の機能追加を行ってください。
 * ・メニューを付けて、プロパティダイアログを開ける。
 * ・プロパティダイアログでは、以下の項目を設定できる。
 * 　1. フォントの指定
 * 　2. フォントサイズの指定
 * 　3. 文字色の指定
 * 　4. 時計の背景色の指定
 * ・描画に際して、ちらつきをなくすようにダブルバッファリングする。
 * ・フォントとフォントサイズを変更すると、時計を表示すべきフレームの大きさを適切に自動変更して、正しく表示されるようにする。
 */

/*
 * 課題1-1
 * AWTのFrameを使用して、時間を表示するデジタル時計（アナログ時計は不可）を作成してください。
 * ・java.awt.Frameを使用する。
 * ・Windowsの普通のアプリケーションと同様にタイトルバーの「×」ボタンをクリックすると終了する。
 * ・デジタル時計の描画は、paintメソッド内でGraphicsを使用して行う。テキストラベルによる単なる表示は、不可。
 */

package gui02_03;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;

public class DigitalClock extends JWindow implements Runnable, ActionListener
{
    private static final long serialVersionUID = 1L;

    private Date currentDate;
    private SimpleDateFormat simpleDataFormat = new SimpleDateFormat("HH:mm:ss");

    private String stringCurrentTime = " Current: --:--:--";

    private Thread th;
    private Image imageBuffer;
    private Graphics graphicBuffer;

    private String fontType = "Broadway BT";
    private int fontStyle = Font.PLAIN;
    private Integer fontSize = 60;
    private Color fontColor = Color.black;
    private Color backgroundColor = Color.white;

    private int windowSizeX = 60 * 8 + 50;
    private int windowSizeY = 60 + 50;

    public JPopupMenu popup = new JPopupMenu();

    private JMenu property = new JMenu("Property");
    private JMenuItem menuItemExit = new JMenuItem("Exit");

    private JMenu menuFontType = new JMenu("Font Type");
    private JMenu menuFontStyle = new JMenu("Font Style");
    private JMenu menuFontSize = new JMenu("Font Size");
    private JMenu menuFontColor = new JMenu("Font Color");
    private JMenu menuBackgroundColor = new JMenu("BackgroundColor");

    static private String fonts[] = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    static private String stringFontStyle[] =
    { "Plain", "Bold", "Italic", "Bold and Italic" };
    static private int intFontStyle[] =
    { Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC };

    static private String stringColor[] =
    { "Black", "White", "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow",
            "Orange" };
    static private Color colorColor[] =
    { Color.black, Color.white, Color.red, Color.green, Color.blue, Color.cyan,
            Color.magenta, Color.yellow, Color.orange };

    private LeftDrag mouse;

    // フォントのデフォルトの設定
    private Font fontSetting = new Font(fontType, fontStyle, fontSize);

    // コンストラクタ
    public DigitalClock()
    {
        // 時刻用変数の初期化
        currentDate = new Date();

        property.add(menuFontType);
        {
            for (int i = 0; i < fonts.length; i++)
            {
                menuFontType.add(fonts[i]);
            }
        }
        property.add(menuFontStyle);
        {
            for (int i = 0; i < stringFontStyle.length; i++)
            {
                menuFontStyle.add(stringFontStyle[i]);
            }
        }
        property.add(menuFontSize);
        {
            for (Integer i = 10; i < 300; i += 30)
            {
                menuFontSize.add(i.toString());
            }
        }
        property.add(menuFontColor);
        {
            for (int i = 0; i < stringColor.length; i++)
            {
                menuFontColor.add(new JMenuItem(stringColor[i]));
            }
        }
        property.add(menuBackgroundColor);
        {
            for (int i = 0; i < stringColor.length; i++)
            {
                menuBackgroundColor.add(new JMenuItem(stringColor[i]));
            }
        }
        mouse = new LeftDrag(this);

        addMouseListener(mouse);
        addMouseMotionListener(mouse);


        popup.add(property);
        popup.add(menuItemExit);


        menuFontType.addActionListener(this);
        menuFontStyle.addActionListener(this);
        menuFontSize.addActionListener(this);
        menuFontColor.addActionListener(this);
        menuBackgroundColor.addActionListener(this);
        menuItemExit.addActionListener(this);


        // this.add(popup);


    }

    public void paint(Graphics g)
    {
        // 現在時刻の更新
        currentDate = new Date();

        // 時刻用文字列の生成
        stringCurrentTime = " Current: " + simpleDataFormat.format(currentDate);

        if (null != graphicBuffer)
        {
            // ウィンドウサイズの計算
            windowSizeX =  graphicBuffer.getFontMetrics().stringWidth(stringCurrentTime);

            windowSizeX += getInsets().left;
            windowSizeX += getInsets().right;

            windowSizeY = graphicBuffer.getFontMetrics().getAscent();
            windowSizeY += graphicBuffer.getFontMetrics().getDescent();
            windowSizeY += graphicBuffer.getFontMetrics().getLeading();
            windowSizeY += getInsets().top;
        }
        setSize(windowSizeX, windowSizeY);

        imageBuffer = createImage(windowSizeX, windowSizeY);
        graphicBuffer = imageBuffer.getGraphics();

        // 背景を色つきで塗りつぶす
        graphicBuffer.setColor(backgroundColor);
        graphicBuffer.fillRect(0, 0, windowSizeX, windowSizeY);

        // 時刻の描画
        fontSetting = new Font(fontType, fontStyle, fontSize);
        graphicBuffer.setFont(fontSetting); // フォントの設定
        graphicBuffer.setColor(fontColor); // 文字色の設定

        graphicBuffer.drawString(stringCurrentTime, 0, graphicBuffer
                .getFontMetrics().getAscent()
                + getInsets().top
                - getInsets().bottom);

        // バッファのコピー
        g.drawImage(imageBuffer, 0, 0, this);
    }

    public int getFontStyle()
    {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle)
    {
        this.fontStyle = fontStyle;
    }

    /*
    @Override
    public void update(Graphics g)
    {
        // ちらつき防止のため、updateメソッドからそのままpaintメソッドにつなぐ
        // (画面がクリアされないようにする)
        paint(g);
    }
    */

    @Override
    public void run()
    {
        while (true)
        {
            // 再描画
            repaint();

            try
            {
                Thread.sleep(10); // スリープ1秒
            }
            catch (InterruptedException e)
            {
                ; // 何もしない
            }
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

    public Integer getFontSize()
    {
        return fontSize;
    }

    public void setFontSize(int fontSize)
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

    public Font getFontSetting()
    {
        return fontSetting;
    }

    public void setFontSetting(Font fontSetting)
    {
        this.fontSetting = fontSetting;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DigitalClock window = new DigitalClock();

        window.th = new Thread(window);

        window.setSize(220, 150);
        window.setLocation(100, 100);
        window.setVisible(true);

        window.imageBuffer = window.createImage(220, 150);
        window.graphicBuffer = window.imageBuffer.getGraphics();
        window.setAlwaysOnTop(true);

        window.th.start(); // スレッドスタート

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e);
        if (menuFontType == e.getSource())
        {
            setFontType(e.getActionCommand());
        }
        else if (menuFontStyle == e.getSource())
        {
            for (int i = 0; i < stringFontStyle.length; i++)
            {
                if (e.getActionCommand() == stringFontStyle[i])
                {
                    setFontStyle(intFontStyle[i]);
                }
            }
        }
        else if (menuFontSize == e.getSource())
        {
            setFontSize(Integer.valueOf(e.getActionCommand()));
        }
        else if (menuFontColor == e.getSource())
        {
            setFontColor(changeStringToColor(e.getActionCommand()));
        }
        else if (menuBackgroundColor == e.getSource())
        {
            setBackgroundColor(changeStringToColor(e.getActionCommand()));
        }
        else if ("Exit" == e.getActionCommand())
        {
            System.exit(0);
        }
        else
        {
            System.out.println(e.getSource());
            System.out.println("This action is not implemented!");
        }
    }

    public Color changeStringToColor(String colorString)
    {
        for (int i = 0; i < stringColor.length; i++)
        {
            if (colorString == stringColor[i])
            {
                return colorColor[i];
            }
        }
        return Color.black;
    }

    public void setWindowLocation(int x, int y)
    {
        setLocation(x, y);
    }

    public String calculateErapsedTime(Date start, Date end)
    {
        Long diffAsSecond = (end.getTime() - start.getTime()) / 1000;
        Long diffHour = diffAsSecond / (60 * 60);
        Long diffMinute = (diffAsSecond - diffHour * 60 * 60) / 60;
        Long diffSecond = (diffAsSecond - diffHour * 60 * 60 - diffMinute * 60);

        String stringHour;
        String stringMinute;
        String stringSecond;

        if (diffHour < 10)
        {
            stringHour = "0" + diffHour;
        }
        else if (diffHour >= 100)
        {
            stringHour = "00";
        }
        else
        {
            stringHour = diffHour.toString();
        }

        if (diffMinute < 10)
        {
            stringMinute = "0" + diffMinute;
        }
        else
        {
            stringMinute = diffMinute.toString();
        }

        if (diffSecond < 10)
        {
            stringSecond = "0" + diffSecond;
        }
        else
        {
            stringSecond = diffSecond.toString();
        }

        return stringHour + ":" + stringMinute + ":" + stringSecond;
    }




    public class LeftDrag extends MouseAdapter
    {
        private DigitalClock digitalClock;
        private MouseEvent startMouse = null;

        LeftDrag(DigitalClock digitalClock)
        {
            this.digitalClock = digitalClock;
        }

        public void mouseReleased(MouseEvent e)
        {
            if (1 == e.getButton())
            {
                digitalClock.setLocation(digitalClock.getLocation().x + e.getX() - startMouse.getX(),
                        digitalClock.getLocation().y + e.getY() - startMouse.getY());
                startMouse = null;
            }
            else if (3 == e.getButton())
            {
                System.out.println(e.getComponent());
                System.out.println(e.isPopupTrigger());
                digitalClock.popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        public void mouseDragged(MouseEvent e)
        {
            if (null != startMouse)
            {
            digitalClock.setLocation(digitalClock.getLocation().x + e.getX() - startMouse.getX(),
                    digitalClock.getLocation().y + e.getY() - startMouse.getY());
            }
        }

        public void mousePressed(MouseEvent e)
        {
            System.out.println(e);
            if (1 == e.getButton())
            {
                startMouse = e;
            }
            else if (3 == e.getButton())
            {
                System.out.println(e.getComponent());
                System.out.println(e.isPopupTrigger());
                // digitalClock.popup.show(e.getComponent(), e.getX(), e.getY());
                // digitalClock.popup.show(e.getComponent(), 100, 100);
            }
        }
    }
}
