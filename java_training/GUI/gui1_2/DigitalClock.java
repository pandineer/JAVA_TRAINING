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

package gui1_2;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.*;
import java.util.Calendar;


public class DigitalClock extends Frame implements Runnable
{
    static int h;       // 時
    static int m;       // 分
    static int s;       // 秒
    Calendar now = Calendar.getInstance();
    static Thread th;

    // Font f = new Font("TimesRoman", Font.PLAIN, 16);

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
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu menuMenu = new Menu("Menu");
        menuMenu.addActionListener(this);


    }

    @Override
    public void run()
    {
        while(true)
        {
            h = now.getInstance().get(now.HOUR_OF_DAY);
            m = now.getInstance().get(now.MINUTE);
            s = now.getInstance().get(now.SECOND);
            repaint();

            try
            {
                th.sleep(1000); // スリープ1秒
            }
            catch(InterruptedException e)
            {
            }
        }

    }

    public void paint(Graphics g)
    {
    	// setFont(f);
        g.drawString(h+":"+m+":"+s, 20, 50);
    }


    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DigitalClock window = new DigitalClock("Digital Clock");

        th = new Thread(window);

        window.setSize(200, 150);
        window.setResizable(false);
        window.setVisible(true);

        th.start();     // スレッドスタート

    }



}
