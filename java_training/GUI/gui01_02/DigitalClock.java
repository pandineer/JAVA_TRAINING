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

package gui01_02;

import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;

public class DigitalClock extends Frame implements Runnable, ActionListener
{
    private static final long serialVersionUID = 1L;
    private Integer hourInteger; // 時
    private Integer minuteInteger; // 分
    private Integer secondInteger; // 秒

    private String hourString;
    private String minuteString;
    private String secondString;

    private Thread th;
    private PropertyDialog dialog;
    private Menu menuMenu;
    private MenuItem menuProperty;
    private MenuItem menuCapture;
    private Image imageBuffer;
    private Graphics graphicBuffer;

    private String fontType = "TimesRoman";
    private Integer fontSize = 48;
    private Color fontColor = Color.blue;
    private Color backgroundColor = Color.white;

    private int windowSizeX = 48 * 8 + 50;
    private int windowSizeY = 48 + 50;

    private String timeString;
    private String captureTimeString = "00:00:00";

    private MenuBar menuBar;

    private boolean captureFlag = false;

    // フォントのデフォルトの設定
    private Font fontSetting = new Font("TimesRoman", Font.PLAIN, 48);

    public DigitalClock(String title)
    {
        // タイトルバーにタイトルを登録する
        super(title);

        // ウィンドウを閉じられるようにする
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        // メニューバーを作成する
        menuBar = new MenuBar();
        setMenuBar(menuBar);

        // [Menu]
        menuMenu = new Menu("Menu");
        menuMenu.addActionListener(this);
        menuBar.add(menuMenu);

        // [Menu] - [Property]
        menuProperty = new MenuItem("Property");
        menuMenu.add(menuProperty);

        // [Menu] - [Capture!]
        menuCapture = new MenuItem("Capture!");
        menuMenu.add(menuCapture);

        // ダイアログを生成する
        dialog = new PropertyDialog(this);

        // 現在時刻用変数の初期化
        hourInteger = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minuteInteger = Calendar.getInstance().get(Calendar.MINUTE);
        secondInteger = Calendar.getInstance().get(Calendar.SECOND);

    }

    public void paint(Graphics g)
    {
        // 時・分・秒が一桁の時、0で二桁目を埋める
        if (hourInteger < 10)
        {
            hourString = "0" + hourInteger;
        }
        else
        {
            hourString = hourInteger.toString();
        }

        if (minuteInteger < 10)
        {
            minuteString = "0" + minuteInteger;
        }
        else
        {
            minuteString = minuteInteger.toString();
        }

        if (secondInteger < 10)
        {
            secondString = "0" + secondInteger;
        }
        else
        {
            secondString = secondInteger.toString();
        }
        timeString = hourString + ":" + minuteString + ":" + secondString;

        // ウィンドウサイズの計算
        windowSizeX = graphicBuffer.getFontMetrics().stringWidth(timeString);
        windowSizeX += getInsets().left;
        windowSizeX += getInsets().right;

        windowSizeY = graphicBuffer.getFontMetrics().getAscent();
        windowSizeY += graphicBuffer.getFontMetrics().getDescent();
        windowSizeY += graphicBuffer.getFontMetrics().getLeading();
        windowSizeY *= 2; // キャプチャした時刻用
        windowSizeY += getInsets().top;

        setSize(windowSizeX, windowSizeY);

        imageBuffer = createImage(windowSizeX, windowSizeY);
        graphicBuffer = imageBuffer.getGraphics();

        // 背景を色つきで塗りつぶす
        graphicBuffer.setColor(backgroundColor);
        graphicBuffer.fillRect(0, 0, windowSizeX, windowSizeY);

        // 時刻の描画
        fontSetting = new Font(fontType, Font.PLAIN, fontSize);
        graphicBuffer.setFont(fontSetting); // フォントの設定
        graphicBuffer.setColor(fontColor); // 文字色の設定
        graphicBuffer.drawString(timeString, 0, graphicBuffer.getFontMetrics()
                .getAscent() + getInsets().top - getInsets().bottom);

        // キャプチャした時刻の描画
        if (true == captureFlag)
        {
            captureTimeString = timeString;
            captureFlag = false;
        }
        graphicBuffer.drawString(captureTimeString, 0,
                (graphicBuffer.getFontMetrics().getAscent()) * 2
                        + getInsets().top - getInsets().bottom);

        // バッファのコピー
        g.drawImage(imageBuffer, 0, 0, this);
    }

    @Override
    public void update(Graphics g)
    {
        // ちらつき防止のため、updateメソッドからそのままpaintメソッドにつなぐ
        // (画面がクリアされないようにする)
        paint(g);
    }

    @Override
    public void run()
    {
        while (true)
        {
            // 現在時刻の獲得
            hourInteger = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            minuteInteger = Calendar.getInstance().get(Calendar.MINUTE);
            secondInteger = Calendar.getInstance().get(Calendar.SECOND);

            // 再描画
            repaint();

            try
            {
                Thread.sleep(100); // スリープ1秒
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
        DigitalClock window = new DigitalClock("Digital Clock");

        window.th = new Thread(window);

        window.setSize(220, 150);
        window.setResizable(false);
        window.setVisible(true);

        window.imageBuffer = window.createImage(220, 150);
        window.graphicBuffer = window.imageBuffer.getGraphics();

        window.th.start(); // スレッドスタート

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand() == "Property")
        {
            // クリックしたのが「Property」だったら
            dialog.setVisible(true);
        }
        else if (e.getActionCommand() == "Capture!")
        {
            // クリックしたのが「Capture!」だったら
            captureFlag = true;
        }
    }

}
