package plugin;

import javax.swing.JPanel;

public interface SamplePluginAppPlugin {
    public String getResult();              // 計算結果の取得
    public void setInputData(String input); // データのSetter
    public String getInputData();           // データのGetter
    public JPanel getPanel();               // GUIとなるJPanelを返す
}