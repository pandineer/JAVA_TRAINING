package Window;

import java.awt.*;
import java.awt.event.*;

class test extends Frame implements WindowListener{
        public test(String title) {
                super(title);
                addWindowListener(this);
        }
        public static void main(String args[]) {
                test win = new test("Kitty on your lap");
                win.setSize(300 , 300);
                win.setVisible(true);
        }

        public void windowActivated(WindowEvent e) {
                System.out.println("アクティブイベント");
        }
        public void windowClosed(WindowEvent e) {
                System.out.println("ウィンドウクローズイベント");
                System.exit(0);
        }
        public void windowClosing(WindowEvent e) {
                System.out.println("クローズ要求イベント");
                dispose();
        }
        public void windowDeactivated(WindowEvent e) {
                System.out.println("非アクティブイベント");
        }
        public void windowDeiconified(WindowEvent e) {
                System.out.println("非アイコン化イベント");
        }
        public void windowIconified(WindowEvent e) {
                System.out.println("アイコン化イベント");
        }
        public void windowOpened(WindowEvent e) {
                System.out.println("ウィンドウオープンイベント");
        }
}