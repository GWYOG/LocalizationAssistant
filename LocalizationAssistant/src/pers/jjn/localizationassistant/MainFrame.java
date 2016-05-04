package pers.jjn.localizationassistant;

import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements ActionListener{
	
	/*
	 *	LA主窗体的GUI布置和事件监听及实现
	 * 	@Author: JJN(GWYOG)
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel panel1,panel2,panel3,panel4,panel5;   	 //选项卡对应的面板
	private JPanel panel1_1,panel1_2;
	private JPanel panel2_1,panel2_2,panel2_3,panel2_3_1,panel2_3_2,panel2_4,panel2_5,panel2_6,panel2_7;
	private JPanel panel3_1,panel3_2,panel3_3,panel3_3_1,panel3_3_2,panel3_4,panel3_5;
	private JPanel panel4_1,panel4_2,panel4_3,panel4_4,panel4_5,panel4_6,panel4_7;
	private JPanel panel5_1,panel5_2,panel5_3,panel5_4,panel5_5;
	private JButton button1,button2,button2_go,button2_open1,button2_open2;
	private JButton button3_open1,button3_open2,button3_go;
	private JButton button4_open1,button4_open2,button4_open3,button4_open4,button4_go;
	private JButton button5_open1,button5_open2,button5_go;
	private JLabel label1_1;
	private JLabel label2_1,label2_2,label2_3,label2_4,label2_5,label2_6;
	private JLabel label2_5_des,label2_6_des;
	private JLabel label3_1,label3_2,label3_3_1,label3_3_2,label3_4;
	private JLabel label3_4_des;
	private JLabel label4_1,label4_2,label4_3,label4_4,label4_5,label4_6;
	private JLabel label5_1,label5_2,label5_3,label5_4;
	//private JList list1,list2;				//to-do: auto-merge
	private JTabbedPane tabbedPane;
	private JTextField textField2_1,textField2_2,textField2_3,textField2_4,textField2_5,textField2_6;
	private JTextField textField3_1,textField3_2,textField3_3_1,textField3_3_2,textField3_4;
	private JTextField textField4_1,textField4_2,textField4_3,textField4_4;
	private JTextField textField5_1,textField5_2,textField5_3;
	private JRadioButton radioButton4_5_1,radioButton4_5_2,radioButton4_5_3,radioButton4_5_4,radioButton4_5_5;
	private JCheckBox checkBox4_6,checkBox5;
	private ButtonGroup buttongroup;
	
	//在构造方法中实现LA所有GUI的布置
	public MainFrame(){
		super("Localization Assistant v1.3");
		setSize(480,540);
		Container contentPane = this.getContentPane();
		
		//panels
		panel1 = new JPanel();  
	    panel2 = new JPanel();  
	    panel3 = new JPanel(); 
	    panel4 = new JPanel();
	    panel5 = new JPanel();
	    panel1_1 = new JPanel();
	    panel1_2 = new JPanel();
	    panel2_1 = new JPanel();
	    panel2_2 = new JPanel();
	    panel2_3 = new JPanel();
	    panel2_3_1 = new JPanel();
	    panel2_3_2 = new JPanel();
	    panel2_4 = new JPanel();
	    panel2_5 = new JPanel();
	    panel2_6 = new JPanel();
	    panel2_7 = new JPanel();
	    panel3_1 = new JPanel();
	    panel3_2 = new JPanel();
	    panel3_3 = new JPanel();
	    panel3_3_1 = new JPanel();
	    panel3_3_2 = new JPanel();
	    panel3_4 = new JPanel();
	    panel3_5 = new JPanel();
	    panel4_1 = new JPanel();
	    panel4_2 = new JPanel();
	    panel4_3 = new JPanel();
	    panel4_4 = new JPanel();
	    panel4_5 = new JPanel();
	    panel4_6 = new JPanel();
	    panel4_7 = new JPanel();
	    panel5_1 = new JPanel();
	    panel5_2 = new JPanel();
	    panel5_3 = new JPanel();
	    panel5_4 = new JPanel();
	    panel5_5 = new JPanel();
	    panel1_1.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel1_2.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel2_1.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel2_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel2_3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel2_3_1.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel2_3_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel2_4.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel2_5.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel2_6.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel2_7.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel3_1.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel3_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel3_3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel3_3_1.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel3_3_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel3_4.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel3_5.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel4_1.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel4_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel4_3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel4_4.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel4_5.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel4_6.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel4_7.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel5_1.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel5_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel5_3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel5_4.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel5_5.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel2_3.setSize(600,30);
	    panel3_3.setSize(600,30);
	    
        //TabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("基本信息",null,panel1,"关于LA的基本信息");  
        tabbedPane.addTab("条件替换",null,panel2,"根据用户需要，有条件地替换字符串");  
        tabbedPane.addTab("等距添加字符串",null,panel3,"每隔若干个字符就插入某个字符串");  
        tabbedPane.addTab("可用文本更新",null,panel4,"将旧版文本中可用的译文自动更新到新版文本中");  
        tabbedPane.addTab("逐行条件清理",null,panel5,"删除指定字符串后该行的所有内容");
        
        //Buttons
		button1 = new JButton("Open");
		button2 = new JButton("Exit");
		button2_go = new JButton("Go");
		button2_go.setSize(50, 50);
		button3_go = new JButton("Go");
		button3_go.setSize(50, 50);
		button4_go = new JButton("Go");
		button4_go.setSize(50, 50);
		button5_go = new JButton("Go");
		button5_go.setSize(50,50);
		button2_open1 = new JButton("Open");
		button2_open2 = new JButton("Open");
		button3_open1 = new JButton("Open");
		button3_open2 = new JButton("Open");
		button4_open1 = new JButton("Open");
		button4_open2 = new JButton("Open");
		button4_open3 = new JButton("Open");
		button4_open4 = new JButton("Open");
		button5_open1 = new JButton("Open");
		button5_open2 = new JButton("Open");
		button1.addActionListener(this);
		button2.addActionListener(this);
		button2_go.addActionListener(this);
		button2_open1.addActionListener(this);
		button2_open2.addActionListener(this);
		button3_go.addActionListener(this);
		button3_open1.addActionListener(this);
		button3_open2.addActionListener(this);
		button4_go.addActionListener(this);
		button4_open1.addActionListener(this);
		button4_open1.setEnabled(false);
		button4_open2.addActionListener(this);
		button4_open3.addActionListener(this);
		button4_open4.addActionListener(this);
		button5_go.addActionListener(this);
		button5_open1.addActionListener(this);
		button5_open2.addActionListener(this);
		
		//Labels
		label1_1 = new JLabel();
		label1_1.setText("<html><body>作者:JJN(GWYOG)<br/>版本号:v1.3<br/>日期:2016/5/5</body></html>");
		label2_1 = new JLabel("待操作的文件:");
		label2_2 = new JLabel("输出到的文件:");
		label2_3 = new JLabel("待替换的字符串:");			
		label2_4 = new JLabel("替换成的字符串:");
		label2_5 = new JLabel("*(此项可为空)  操作行数:");
		label2_5_des = new JLabel("用\"数字\"或是\"数字-数字\"的形式表示,中间以英文逗号分隔,为空代表操作所有行");
		label2_6 = new JLabel("*(此项可为空)  替换忽略符:");
		label2_6_des = new JLabel("该对字符中间将不作替换(以\"-\"分隔,只支持输入一对字符即\"字符-字符\"的形式):");
		label3_1 = new JLabel("待操作的文件:");
		label3_2 = new JLabel("输出到的文件:");
		label3_3_1 = new JLabel("间隔字符个数:");
		label3_3_2 = new JLabel("待插入的字符串:");
		label3_4 = new JLabel("*(此项可为空)  操作行数:");
		label3_4_des = new JLabel("用\"数字\"或是\"数字-数字\"的形式表示,中间以英文逗号分隔,为空代表操作所有行");
		label4_1 = new JLabel("旧版本 en_US.lang:");
		label4_2 = new JLabel("旧版本 zh_CN.lang:");
		label4_3 = new JLabel("新版本 en_US.lang:");
		label4_4 = new JLabel("输出到的文件(新版本 zh_CN.lang):");
		label4_5 = new JLabel("更新方式(1表示替换\"=\"前的部分,2表示替换\"=\"后的部分):");
		label4_6 = new JLabel("是否开启校验模式:");
		label5_1 = new JLabel("待操作的文件:");
		label5_2 = new JLabel("输出到的文件:");
		label5_3 = new JLabel("清空此字符串后该行的内容:");
		label5_4 = new JLabel("清空时是否保留该字符串:");
		
		//textFields
		textField2_1 = new JTextField(40);
		textField2_2 = new JTextField(40);
		textField2_3 = new JTextField(10);
		textField2_4 = new JTextField(10);
		textField2_5 = new JTextField(40);
		textField2_6 = new JTextField(40);
		textField3_1 = new JTextField(40);
		textField3_2 = new JTextField(40);
		textField3_3_1 = new JTextField(10);
		textField3_3_2 = new JTextField(10);
		textField3_4 = new JTextField(40);
		textField4_1 = new JTextField(40);
		textField4_1.setEnabled(false);
		textField4_2 = new JTextField(40);
		textField4_3 = new JTextField(40);
		textField4_4 = new JTextField(40);
		textField5_1 = new JTextField(40);
		textField5_2 = new JTextField(40);
		textField5_3 = new JTextField(40);
		contentPane.add(tabbedPane);  
        contentPane.setBackground(Color.white);  
        
        //radioButtons
        radioButton4_5_1 =new JRadioButton("方式1");
        radioButton4_5_2 =new JRadioButton("方式2");
        radioButton4_5_3 =new JRadioButton("先1后2");
        radioButton4_5_4 =new JRadioButton("先2后1");
        radioButton4_5_5 =new JRadioButton("完全匹配");
        radioButton4_5_1.setSelected(true);
        radioButton4_5_1.addActionListener(this);
        radioButton4_5_2.addActionListener(this);
        radioButton4_5_3.addActionListener(this);
        radioButton4_5_4.addActionListener(this);
        radioButton4_5_5.addActionListener(this);
        buttongroup = new ButtonGroup();
        buttongroup.add(radioButton4_5_1);
        buttongroup.add(radioButton4_5_2);
        buttongroup.add(radioButton4_5_3);
        buttongroup.add(radioButton4_5_4);
        buttongroup.add(radioButton4_5_5);
        
        //checkBoxs
        checkBox4_6 = new JCheckBox("开启");
        checkBox5 = new JCheckBox("不删除该字符串");
        
        //First Main Panel
		//panel1.add(button1);
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
        panel1.add(Box.createVerticalStrut(170));
        panel1.add(panel1_1);
        panel1.add(panel1_2);
        panel1.add(Box.createVerticalStrut(170));
		panel1_1.add(label1_1);
		panel1_2.add(button2);
		//Second Main Panel
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		panel2.add(panel2_1);
		panel2_1.add(label2_1);
		panel2_1.add(button2_open1);
		panel2_1.add(textField2_1);
		panel2.add(panel2_2);
		panel2_2.add(label2_2);
		panel2_2.add(button2_open2);
		panel2_2.add(textField2_2);
		panel2.add(panel2_3);
		panel2_3.add(panel2_3_1);
		panel2_3.add(panel2_3_2);
		panel2_3_1.add(label2_3);
		panel2_3_1.add(textField2_3);
		panel2_3_2.add(label2_4);
		panel2_3_2.add(textField2_4);
		panel2.add(panel2_5);
		panel2_5.add(label2_5);
		panel2_5.add(label2_5_des);
		panel2_5.add(textField2_5);
		panel2.add(panel2_6);
		panel2_6.add(label2_6);
		panel2_6.add(label2_6_des);
		panel2_6.add(textField2_6);
		panel2.add(panel2_7);
		panel2_7.add(button2_go);
		//Third Main Panel
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
		panel3.add(panel3_1);
		panel3_1.add(label3_1);
		panel3_1.add(button3_open1);
		panel3_1.add(textField3_1);
		panel3.add(panel3_2);
		panel3_2.add(label3_2);
		panel3_2.add(button3_open2);
		panel3_2.add(textField3_2);
		panel3.add(panel3_3);
		panel3_3.add(panel3_3_1);
		panel3_3.add(panel3_3_2);
		panel3_3_1.add(label3_3_1);
		panel3_3_1.add(textField3_3_1);
		panel3_3_2.add(label3_3_2);
		panel3_3_2.add(textField3_3_2);
		panel3.add(panel3_4);
		panel3_4.add(label3_4);
		panel3_4.add(label3_4_des);
		panel3_4.add(textField3_4);
		panel3.add(panel3_5);
		panel3_5.add(button3_go);
		//Fourth Main Panel
		panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
		panel4.add(panel4_1);
		panel4_1.add(label4_1);
		panel4_1.add(button4_open1);
		panel4_1.add(textField4_1);
		panel4.add(panel4_2);
		panel4_2.add(label4_2);
		panel4_2.add(button4_open2);
		panel4_2.add(textField4_2);
		panel4.add(panel4_3);
		panel4_3.add(label4_3);
		panel4_3.add(button4_open3);
		panel4_3.add(textField4_3);
		panel4.add(panel4_4);
		panel4_4.add(label4_4);
		panel4_4.add(button4_open4);
		panel4_4.add(textField4_4);
		panel4.add(panel4_5);
		panel4_5.add(label4_5);
		panel4_5.add(Box.createHorizontalStrut(80));
		panel4_5.add(radioButton4_5_1);
		panel4_5.add(radioButton4_5_2);
		panel4_5.add(radioButton4_5_3);
		panel4_5.add(radioButton4_5_4);
		panel4_5.add(radioButton4_5_5);
		panel4.add(panel4_6);
		panel4_6.add(label4_6);
		panel4_6.add(checkBox4_6);
		panel4.add(panel4_7);
		panel4_7.add(button4_go);
		panel5.setLayout(new BoxLayout(panel5,BoxLayout.Y_AXIS));
		panel5.add(panel5_1);
		panel5_1.add(label5_1);
		panel5_1.add(button5_open1);
		panel5_1.add(textField5_1);
		panel5.add(panel5_2);
		panel5_2.add(label5_2);
		panel5_2.add(button5_open2);
		panel5_2.add(textField5_2);
		panel5.add(panel5_3);
		panel5_3.add(label5_3);
		panel5_3.add(textField5_3);
		panel5.add(panel5_4);
		panel5_4.add(label5_4);
		panel5_4.add(checkBox5);
		panel5.add(panel5_5);
		panel5_5.add(button5_go);
		panel5.add(Box.createVerticalStrut(170));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	
	//main函数
	public static void main(String[] args) {
		new MainFrame();
	}

	//实现监听到的事件
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(e.getSource() == radioButton4_5_1 || e.getSource() == radioButton4_5_2 || e.getSource() == radioButton4_5_3 || e.getSource() == radioButton4_5_4 || e.getSource() == radioButton4_5_5)
		{
			 if(radioButton4_5_1.isSelected())
			 {	textField4_1.setEnabled(false);
			 	textField4_1.setText("");
			 	button4_open1.setEnabled(false);
			 }
			 else
			 {	textField4_1.setEnabled(true);
			    button4_open1.setEnabled(true);
					
			 }
		}
		
		if(command == "Open"){
			filedialog_open = new FileDialog(this, "打开文件", FileDialog.LOAD);
			filedialog_open.setVisible(true);
			String directory = filedialog_open.getDirectory();
			String filename = filedialog_open.getFile();
			if(e.getSource() == button1){
				System.out.println(directory + filename);
			}
			else if(e.getSource() == button2_open1){
				if(directory != null && filename != null)
					textField2_1.setText(directory + filename);
			}
			else if(e.getSource() == button2_open2){
				if(directory != null && filename != null)
					textField2_2.setText(directory + filename);
			}
			else if(e.getSource() == button3_open1){
				if(directory != null && filename != null)
					textField3_1.setText(directory + filename);
			}
			else if(e.getSource() == button3_open2){
				if(directory != null && filename != null)
					textField3_2.setText(directory + filename);
			}
			else if(e.getSource() == button4_open1){
				if(directory != null && filename != null)
					textField4_1.setText(directory + filename);
			}
			else if(e.getSource() == button4_open2){
				if(directory != null && filename != null)
					textField4_2.setText(directory + filename);
			}
			else if(e.getSource() == button4_open3){
				if(directory != null && filename != null)
					textField4_3.setText(directory + filename);
			}
			else if(e.getSource() == button4_open4){
				if(directory != null && filename != null)
					textField4_4.setText(directory + filename);
			}
			else if(e.getSource() == button5_open1){
				if(directory != null && filename != null)
					textField5_1.setText(directory + filename);
			}
			else if(e.getSource() == button5_open2){
				if(directory != null && filename != null)
					textField5_2.setText(directory + filename);
			}
		}
		else if(command == "Exit")
			System.exit(0);
		else if(command == "Go"){
			if(e.getSource() == button2_go){
				String fileInput = textField2_1.getText();
				String fileOutput = textField2_2.getText();
				String sOrigin = textField2_3.getText();
				String sTo = textField2_4.getText();
				if(textField2_5.getText().indexOf("，") == -1){
					String []rowNumberSplit = {"Blank"};
					if(!textField2_5.getText().trim().equals("")) 
						rowNumberSplit = textField2_5.getText().split(",");
					String []specialIgnoreSymbol = textField2_6.getText().split("-");	
					if(textField2_6.getText().length()!=3 && textField2_6.getText().length()!=0 && (!(textField2_6.getText().length() == 3 && specialIgnoreSymbol.length!=2)))
						 new MessageWindow(this,"错误！","替换忽略符输入格式错误！",-1);
					else{	
						FileModifier filemodifier = new FileModifier(this,fileInput,fileOutput,sOrigin,sTo,rowNumberSplit,specialIgnoreSymbol);
						filemodifier.functionConditionalReplace();
					}
				}
				else
					 new MessageWindow(this,"错误！","错误,请使用英文逗号分隔！",-1);
			}
			else if(e.getSource() == button3_go){
				String fileInput = textField3_1.getText();
				String fileOutput = textField3_2.getText();
				String sInterval = textField3_3_1.getText();
				String sAdd = textField3_3_2.getText();
				if(textField3_4.getText().indexOf("，") == -1){
					String []rowNumberSplit = {"Blank"};
					if(!textField3_4.getText().trim().equals("")) 
						rowNumberSplit = textField3_4.getText().split(",");
					FileModifier filemodifier = new FileModifier(this,fileInput,fileOutput,sInterval,sAdd,rowNumberSplit);
					filemodifier.functionAdd();				
				}
				else
					 new MessageWindow(this,"错误！","错误,请使用英文逗号分隔！",-1);
				
			}
			else if(e.getSource() == button4_go){
				String fileInput1 = textField4_1.getText();
				String fileInput2 = textField4_2.getText();
				String fileInput3 = textField4_3.getText();
				String fileOutput = textField4_4.getText();
				int updateType = radioButton4_5_1.isSelected()?1:radioButton4_5_2.isSelected()?2:radioButton4_5_3.isSelected()?3:radioButton4_5_4.isSelected()?4:radioButton4_5_5.isSelected()?5:-1;
				int checkModeStatus = ((updateType == 2 || updateType == 5) && checkBox4_6.isSelected())? 1 : 0;
				updateType = updateType * 10 + checkModeStatus;
				FileModifier filemodifier = new FileModifier(this,fileInput1,fileInput2,fileInput3,fileOutput,updateType);
				filemodifier.functionUpdateLocalization();		
			}
			else if(e.getSource() == button5_go){
				String fileInput1 = textField5_1.getText();
				String fileOutput = textField5_2.getText();
				String sRemoveFlag = textField5_3.getText();
				int flag = checkBox5.isSelected()?1:0;
				FileModifier filemodifier = new FileModifier(this,fileInput1,fileOutput,sRemoveFlag,flag);
				filemodifier.functionConditionalRemove();		
			}
		}
	}
    private FileDialog filedialog_open;  

}
