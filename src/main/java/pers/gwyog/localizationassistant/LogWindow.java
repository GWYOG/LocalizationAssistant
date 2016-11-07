package pers.gwyog.localizationassistant;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class LogWindow extends JFrame{
    
    /*
     *  用于弹出可以显示反馈信息(日志)及文字提示信息的一个窗体
     */
    private static final long serialVersionUID = 1L;
    
    public JPanel panel1,panel2;
    public JScrollPane scrollPane;
    public JTextArea textArea;
    public JLabel label;
    
    public LogWindow(String title){
        Container container = getContentPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        textArea = new JTextArea(600,200);
        scrollPane = new JScrollPane(textArea);
        label = new JLabel();
        container.add(panel1);
        panel1.add(scrollPane);
        panel1.add(panel2);
        panel2.add(Box.createVerticalStrut(10));
        panel2.add(label);
        panel2.add(Box.createVerticalStrut(60));
        setTitle(title);
        setSize(600,300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        textArea.setEditable(false);
    }

}
