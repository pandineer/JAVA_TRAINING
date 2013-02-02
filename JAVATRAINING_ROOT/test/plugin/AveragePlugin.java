package plugin;

import java.awt.Rectangle;
import javax.swing.*;

public class AveragePlugin implements SamplePluginAppPlugin {
    private JTextArea input = null;
    
    public String toString(){
        return "Average";
    }
    
    public String getResult() {
        String inputData = input.getText();
        if (inputData == null) inputData = "0";
        String[] arr = inputData.split("\n");
        int[] intarr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            try {
                intarr[i] = Integer.parseInt(arr[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        int total = 0;
        for (int i : intarr) {
            total += i;
        }
        double av = total / intarr.length;
        double res = ((int)(av * 100)) / 100;
        return Double.toString(res);
    }

    public String getInputData() {
        return input.getText();
    }
    
    public void setInputData(String str) {
        input.setText(str);
    }
    
    public JPanel getPanel() {
        JPanel p = new JPanel();
        p.setLayout(null);
        input = new JTextArea();
        input.setLineWrap(true);
        JScrollPane s = new JScrollPane(input);
        s.setBounds(new Rectangle(25,25,75,50));
        p.add(s);
        return p;
    }
}