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

package gui01_03;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickMenu extends MouseAdapter
{
    private DigitalClock digitalClock;
    private MouseEvent startMouse = null;

    RightClickMenu(DigitalClock digitalClock)
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
        if (1 == e.getButton())
        {
            startMouse = e;
        }
        else if (3 == e.getButton())
        {
            digitalClock.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
