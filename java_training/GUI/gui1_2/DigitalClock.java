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

import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;


public class DigitalClock extends Frame implements Runnable, ActionListener
{
	private static final long serialVersionUID = 1L;
	public int h;       // 時
	public int m;       // 分
	public int s;       // 秒
    public Calendar now = Calendar.getInstance();
    public Thread th;
    public PropertyDialog dialog;
    public Menu menuMenu;
    public MenuItem menuProperty;
    public Image imageBuffer;
    public Graphics graphicBuffer;

    // フォントの初期設定
    public Font f = new Font("TimesRoman", Font.PLAIN, 48);

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
        // [Menu]
        //Menu menuProperty = new Menu("Property");
        menuMenu = new Menu("Menu");
        menuMenu.addActionListener(this);
        menuBar.add(menuMenu);
        // [Menu] - [Property]
        menuProperty = new MenuItem("Property");
        menuMenu.add(menuProperty);

        // ダイアログを生成する
        dialog = new PropertyDialog(this);
    }



    public void paint(Graphics g)
    {
    	// バッファをクリアする
    	graphicBuffer.clearRect(0, 0, 200, 150);

    	// 背景を色つきで塗りつぶす
    	graphicBuffer.setColor(Color.orange);
    	graphicBuffer.fillRect(0, 0, 200, 150);

    	// 時間の描画
    	graphicBuffer.setFont(f);	// フォントの設定
    	graphicBuffer.setColor(Color.black);	// 文字色の設定
    	graphicBuffer.drawString(h+":"+m+":"+s, 20, 100);

    	// バッファのコピー
    	g.drawImage(imageBuffer, 0, 0,  this);
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
        while(true)
        {
        	// 現在時刻の獲得
            h = now.getInstance().get(now.HOUR_OF_DAY);
            m = now.getInstance().get(now.MINUTE);
            s = now.getInstance().get(now.SECOND);

            // 再描画
            repaint();

            try
            {
                th.sleep(1000); // スリープ1秒
            }
            catch(InterruptedException e)
            {
            	; // 何もしない
            }
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DigitalClock window = new DigitalClock("Digital Clock");

        window.th = new Thread(window);

        window.setSize(200, 150);
        window.setResizable(false);
        window.setVisible(true);

        window.imageBuffer = window.createImage(200, 150);
    	window.graphicBuffer = window.imageBuffer.getGraphics();

        window.th.start();     // スレッドスタート

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Property") // クリックしたのが「Property」だったら
		{
			dialog.setVisible(true);
		}
	}



}
