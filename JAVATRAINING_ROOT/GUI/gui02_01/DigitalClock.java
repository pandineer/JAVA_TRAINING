/*
 * 課題2-1
 * SwingのJFrameを使用して、時間を表示するデジタル時計（アナログ時計は不可）を作成してください。
 *  - javax.swing.JFrameを使用する
 *  - Windowsの普通のアプリケーションと同様にタイトルバーの「X」ボタンをクリックすると終了する。
 *  - デジタル時計の描画は、paintComponentメソッド内でGraphicsを使用して行う。テキストラベルによる単なる表示は、不可。
 */

package gui02_01;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class DigitalClock extends JFrame implements Runnable
{
    private static final long serialVersionUID = 1L;
    private Thread th;

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
        this.setSize(220, 150);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void run()
    {
        // TODO 自動生成されたメソッド・スタブ

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
