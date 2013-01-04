/*
 * 課題2-4
 * 課題2-2のデジタル時計で、属性をダイアログで指定できるようにしましたが、ダイアログを作りなおしてください。
 *  - レイアウトマネージャは、GridBagLayoutを使用する
 *  - プロパティダイアログは、属性名＋のリストメニューが縦に並ぶようにする。
 *  -       フォント フォントのリスト
 *    フォントサイズ サイズのリスト
 *            文字色 色のリスト(色がわかるようにカラーチップも表示すること)
 *            背景色 色のリスト
 *    この場合「属性名」のラベルは右寄せして、「値の選択リスト」メニューは左寄せる。
 *  - ダイアログの下には、「OK」「キャンセル」のボタンをダイアログの右下に寄せて表示し、それぞれのボタンを実装する。
 *    キャンセルされた場合には、正しく、元の値に戻るようにする。
 *  - java.util.prefsパッケージを使用して、プロパティダイアログの内容の保存と、時計の終了時の位置を保存する。
 *    再度、時計を起動した場合に、それらの保存を復元して、デスクトップの元の位置に表示されるようにする。
 */

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

package gui02_04;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class DigitalClock extends JFrame implements Runnable, ActionListener
{
    private static final long serialVersionUID = 1L;
    private Thread th;
    private DrawPanel drawPanel;
    private DigitalClock me;

    private Date currentDate;
    private SimpleDateFormat simpleDataFormat = new SimpleDateFormat("HH:mm:ss");

    private String fontType = "Times New Roman";
    private int fontStyle = Font.PLAIN;
    private Integer fontSize = 80;
    private Color fontColor = Color.black;
    private Font fontSetting = new Font(fontType, fontStyle, fontSize);
    private Color backgroundColor = Color.white;

    private PropertyDialog dialog;

    private JMenu menuMenu;
    private JMenuBar menuBar;
    private JMenuItem menuProperty;

    private Preferences prefs = Preferences.userNodeForPackage(this.getClass());

    private int windowSizeX = prefs.getInt("miyahara_window_width", 80 * 8 + 50);
    private int windowSizeY = prefs.getInt("miyahara_window_height", 80 + 50);

    // Constructor
    public DigitalClock()
    {
        // Set title
        super("JDigitalClock");

        // reference for this object to use inner class
        me = this;

        // To close window
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                // windowを閉じるときにパラメータを保存する
                prefs.putInt("miyahara_window_x", (int)getBounds().getX());
                prefs.putInt("miyahara_window_y", (int)getBounds().getY());
                prefs.putInt("miyahara_window_width", (int)getBounds().getWidth());
                prefs.putInt("miyahara_window_height", (int)getBounds().getHeight());

                prefs.put("miyahara_font_type", fontType);
                prefs.putInt("miyahara_font_size", fontSize);
                prefs.put("miyahara_font_color", PropertyDialog.changeColorToString(fontColor));
                prefs.put("miyahara_bg_color", PropertyDialog.changeColorToString(backgroundColor));

                System.exit(0);
            }
        });

        // Initialize



        // Initialaize window
        this.setSize(windowSizeX, windowSizeY);
        this.setResizable(false);
        this.setVisible(true);

        // prefsからパラメータを読み込む
        setBounds(prefs.getInt("miyahara_window_x", 500), prefs.getInt("miyahara_window_y", 100), prefs.getInt("miyahara_window_width", 80 * 8 + 50), prefs.getInt("miyahara_window_height", 80 + 50));
        fontType = prefs.get("miyahara_font_type", "Times New Roman");
        fontSize = prefs.getInt("miyahara_font_size", 80);
        fontColor = PropertyDialog.changeStringToColor(prefs.get("miyahara_font_color", PropertyDialog.stringColor[3]));
        backgroundColor = PropertyDialog.changeStringToColor(prefs.get("miyahara_bg_color", PropertyDialog.stringColor[0]));

        // Initialize drawPanel
        drawPanel = new DrawPanel();
        this.add(drawPanel);

        // Initialize time
        currentDate = new Date();

        // create menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // [Menu]
        menuMenu = new JMenu("Menu");
        menuMenu.addActionListener(this);
        menuBar.add(menuMenu);

        // [Menu] - [Property]
        menuProperty = new JMenuItem("Property...");
        menuProperty.addActionListener(this);
        menuMenu.add(menuProperty);

        // create property dialog
        dialog = new PropertyDialog(this);
    }

    @Override
    public void run()
    {
        while (true)
        {
            drawPanel.repaint();
            this.setSize(windowSizeX, windowSizeY);
            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e)
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

            // ウィンドウサイズの計算
            if (null != g)
            {
                windowSizeX = g.getFontMetrics().stringWidth(
                        simpleDataFormat.format(currentDate));
                windowSizeX += me.getInsets().left;
                windowSizeX += me.getInsets().right;
            }

            if (null != g)
            {
                // windowSizeY = g.getFontMetrics().getHeight();
                windowSizeY = g.getFontMetrics().getMaxAscent(); // 最大の高さ
                // windowSizeY = g.getFontMetrics().getAscent(); // 高さ。はみ出すかも。
                windowSizeY += g.getFontMetrics().getDescent(); // ベースラインから下の深さ
                // windowSizeY += g.getFontMetrics().getLeading(); // 行間
                // windowSizeY *= 2; // キャプチャした時刻用
                windowSizeY += me.getInsets().top;
                windowSizeY += menuBar.getHeight();
            }

            // set background
            g.setColor(backgroundColor);
            g.fillRect(0, 0, windowSizeX, windowSizeY);

            // draw date
            g.setColor(fontColor);
            g.drawString(simpleDataFormat.format(currentDate), 0, g.getFontMetrics().getMaxAscent());
            // g.drawLine(0, g.getFontMetrics().getMaxAscent(), 100, g.getFontMetrics().getMaxAscent()); // base line
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == menuProperty)
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
