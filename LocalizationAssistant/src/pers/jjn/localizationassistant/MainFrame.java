package pers.gwyog.localizationassistant;

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
	 *	LA�������GUI���ú��¼�������ʵ��
	 * 	@Author: JJN(GWYOG)
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7;   	 //ѡ���Ӧ�����
	private JPanel panel1_1,panel1_2;
	private JPanel panel2_1,panel2_2,panel2_3,panel2_3_1,panel2_3_2,panel2_warning,panel2_4,panel2_5,panel2_6,panel2_7;
	private JPanel panel3_1,panel3_2,panel3_3,panel3_3_1,panel3_3_2,panel3_4,panel3_5;
	private JPanel panel4_1,panel4_2,panel4_3,panel4_4,panel4_5,panel4_6,panel4_7;
	private JPanel panel5_1,panel5_2,panel5_3,panel5_4,panel5_5,panel5_6;
	private JPanel panel6_1,panel6_2,panel6_3,panel6_4,panel6_5,panel6_6,panel6_7;
	private JPanel panel7_1,panel7_2,panel7_3,panel7_4;
	private JButton button1,button2,button2_go,button2_open1,button2_open2;
	private JButton button3_open1,button3_open2,button3_go;
	private JButton button4_open1,button4_open2,button4_open3,button4_open4,button4_go;
	private JButton button5_open1,button5_open2,button5_go;
	private JButton button6_open1,button6_open2,button6_open3,button6_open4,button6_go;
	private JButton button7_open1,button7_open2,button7_go;
	private JLabel label1_1;
	private JLabel label2_1,label2_2,label2_3,label2_4,label2_warning,label2_5,label2_6;
	private JLabel label2_5_des,label2_6_des;
	private JLabel label3_1,label3_2,label3_3_1,label3_3_2,label3_4;
	private JLabel label3_4_des;
	private JLabel label4_1,label4_2,label4_3,label4_4,label4_5,label4_6;
	private JLabel label5_1,label5_2,label5_3,label5_4,label5_5;
	private JLabel label6_1,label6_2,label6_3,label6_4,label6_5,label6_6;
	private JLabel label7_1,label7_2,label7_3;
	//private JList list1,list2;				//to-do: auto-merge
	private JTabbedPane tabbedPane;
	private JTextField textField2_1,textField2_2,textField2_3,textField2_4,textField2_5,textField2_6;
	private JTextField textField3_1,textField3_2,textField3_3_1,textField3_3_2,textField3_4;
	private JTextField textField4_1,textField4_2,textField4_3,textField4_4;
	private JTextField textField5_1,textField5_2,textField5_3;
	private JTextField textField6_1,textField6_2,textField6_3,textField6_4;
	private JTextField textField7_1,textField7_2,textField7_3;
	private JRadioButton radioButton4_5_1,radioButton4_5_2,radioButton4_5_3,radioButton4_5_4,radioButton4_5_5;
	private JRadioButton radioButton6_5_1,radioButton6_5_2;
	private JCheckBox checkBox4_6_1,checkBox4_6_2,checkBox5_4,checkBox5_5,checkBox6_6;
	private ButtonGroup buttongroup4,buttongroup6;
	
	//�ڹ��췽����ʵ��LA����GUI�Ĳ���
	public MainFrame(){
		super("Localization Assistant v1.4");
		setSize(592,640);
		
		//panels
		panel1 = new JPanel();  
	    panel2 = new JPanel();  
	    panel3 = new JPanel(); 
	    panel4 = new JPanel();
	    panel5 = new JPanel();
	    panel6 = new JPanel();
	    panel7 = new JPanel();
	    panel1_1 = new JPanel();
	    panel1_2 = new JPanel();
	    panel2_1 = new JPanel();
	    panel2_2 = new JPanel();
	    panel2_3 = new JPanel();
	    panel2_3_1 = new JPanel();
	    panel2_3_2 = new JPanel();
	    panel2_warning = new JPanel();
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
	    panel5_6 = new JPanel();
	    panel6_1 = new JPanel();
	    panel6_2 = new JPanel();
	    panel6_3 = new JPanel();
	    panel6_4 = new JPanel();
	    panel6_5 = new JPanel();
	    panel6_6 = new JPanel();
	    panel6_7 = new JPanel();
	    panel7_1 = new JPanel();
	    panel7_2 = new JPanel();
	    panel7_3 = new JPanel();
	    panel7_4 = new JPanel();
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
	    panel5_5.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel5_6.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel6_1.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel6_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel6_3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel6_4.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel6_5.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel6_6.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel6_7.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel7_1.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel7_2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel7_3.setLayout(new FlowLayout(FlowLayout.LEFT));
	    panel7_4.setLayout(new FlowLayout(FlowLayout.CENTER));
	    panel2_3.setSize(600,30);
	    panel3_3.setSize(600,30);
	    
        //TabbedPane
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab("������Ϣ",null,panel1,"����LA�Ļ�����Ϣ");  
        tabbedPane.addTab("�����滻",null,panel2,"�����û���Ҫ�����������滻�ַ���");  
        tabbedPane.addTab("�Ⱦ�����ַ���",null,panel3,"ÿ�����ɸ��ַ��Ͳ���ĳ���ַ���");  
        tabbedPane.addTab("�����ı�����",null,panel4,"���ɰ��ı��п��õ������Զ����µ��°��ı���");  
        tabbedPane.addTab("������������",null,panel5,"ɾ��ָ���ַ�������е��������ݻ���������Ϣ");
        tabbedPane.addTab("��������滻",null,panel6,"���Ѿ�����Ĵ����滻δ����Ĳ�������");
        tabbedPane.addTab("��Ƶ�ʻ�ͳ��",null,panel7,"ͳ��Ӣ���ı��еĸ�Ƶ�ʻ�");
        
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
		button6_go = new JButton("Go");
		button6_go.setSize(50,50);
		button7_go = new JButton("Go");
		button7_go.setSize(50,50);
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
		button6_open1 = new JButton("Open");
		button6_open2 = new JButton("Open");
		button6_open3 = new JButton("Open");
		button6_open4 = new JButton("Open");
		button7_open1 = new JButton("Open");
		button7_open2 = new JButton("Open");
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
		button6_go.addActionListener(this);
		button6_open1.addActionListener(this);
		button6_open2.addActionListener(this);
		button6_open3.addActionListener(this);
		button6_open4.addActionListener(this);
		button7_go.addActionListener(this);
		button7_open1.addActionListener(this);
		button7_open2.addActionListener(this);
		
		//Labels
		label1_1 = new JLabel();
		label1_1.setText("<html><body>����:JJN(GWYOG)<br/>�汾��:v1.4<br/>����:2016/6/29</body></html>");
		label2_1 = new JLabel("�������ļ�:");
		label2_2 = new JLabel("������ļ�:");
		label2_3 = new JLabel("���滻���ַ���:");			
		label2_4 = new JLabel("�滻�ɵ��ַ���:");
		label2_warning = new JLabel("<html><body>ע��:���滻���ַ���������������ʽ����<br/>������Ļ����Բ��ùܣ������滻\".\"ʱ��Ҫ����\"\\\\.\"</body></html>");
		label2_5 = new JLabel("*(�����Ϊ��)  ��������:");
		label2_5_des = new JLabel("��\"����\"����\"����-����\"����ʽ��ʾ,�м���Ӣ�Ķ��ŷָ�,Ϊ�մ������������");
		label2_6 = new JLabel("*(�����Ϊ��)  �滻���Է�:");
		label2_6_des = new JLabel("�ö��ַ��м佫�����滻(��\"-\"�ָ�,ֻ֧������һ���ַ���\"�ַ�-�ַ�\"����ʽ):");
		label3_1 = new JLabel("�������ļ�:");
		label3_2 = new JLabel("������ļ�:");
		label3_3_1 = new JLabel("����ַ�����:");
		label3_3_2 = new JLabel("��������ַ���:");
		label3_4 = new JLabel("*(�����Ϊ��)  ��������:");
		label3_4_des = new JLabel("��\"����\"����\"����-����\"����ʽ��ʾ,�м���Ӣ�Ķ��ŷָ�,Ϊ�մ������������");
		label4_1 = new JLabel("�ɰ汾 en_US.lang:");
		label4_2 = new JLabel("�ɰ汾 zh_CN.lang:");
		label4_3 = new JLabel("�°汾 en_US.lang:");
		label4_4 = new JLabel("������ļ�(�°汾 zh_CN.lang):");
		label4_5 = new JLabel("���·�ʽ(1��ʾ�滻\"=\"ǰ�Ĳ���,2��ʾ�滻\"=\"��Ĳ���):");
		label4_6 = new JLabel("����ģʽ:");
		label5_1 = new JLabel("�������ļ�:");
		label5_2 = new JLabel("������ļ�:");
		label5_3 = new JLabel("��մ��ַ�������(�ɸ�Ϊ������)����е�����:");
		label5_4 = new JLabel("���ʱ�������ַ���:");
		label5_5 = new JLabel("���������Ϣ(���ȼ�����):");
		label6_1 = new JLabel("�ο���Ӣ���ı�en_US.lang:");
		label6_2 = new JLabel("���Ʒ�����ı�zh_CN.lang:");
		label6_3 = new JLabel("�������İ��Ʒ�����ı�zh_CN.lang:");
		label6_4 = new JLabel("��������ļ�:");
		label6_5 = new JLabel("�滻ģʽ:");
		label6_6 = new JLabel("����ģʽ:");
		label7_1 = new JLabel("��ͳ�Ƹ�Ƶ�ʻ��en_US.lang");
		label7_2 = new JLabel("ͳ�ƽ��������ļ�:");
		label7_3 = new JLabel("ͳ�ƹ�����(��Ӣ�Ķ��ŷָ�,�ڴ��б��еĵ��ʽ�������ͳ��):");
		
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
		textField6_1 = new JTextField(40);
		textField6_2 = new JTextField(40);
		textField6_3 = new JTextField(40);
		textField6_4 = new JTextField(40);
		textField7_1 = new JTextField(40);
		textField7_2 = new JTextField(40);
		textField7_3 = new JTextField(40);
		this.add(tabbedPane);  
        //contentPane.setBackground(Color.white);  
        
        //radioButtons
        radioButton4_5_1 = new JRadioButton("��ʽ1");
        radioButton4_5_2 = new JRadioButton("��ʽ2");
        radioButton4_5_3 = new JRadioButton("��1��2");
        radioButton4_5_4 = new JRadioButton("��2��1");
        radioButton4_5_5 = new JRadioButton("��ȫƥ��");
        radioButton6_5_1 = new JRadioButton("��ͨ�滻");
        radioButton6_5_2 = new JRadioButton("����\"Ӣ��=����\"��ʽ���ļ������滻");
        radioButton4_5_1.setSelected(true);
        radioButton6_5_1.setSelected(true);
        radioButton4_5_1.addActionListener(this);
        radioButton4_5_2.addActionListener(this);
        radioButton4_5_3.addActionListener(this);
        radioButton4_5_4.addActionListener(this);
        radioButton4_5_5.addActionListener(this);
        radioButton6_5_1.addActionListener(this);
        radioButton6_5_2.addActionListener(this);
        buttongroup4 = new ButtonGroup();
        buttongroup6 = new ButtonGroup();
        buttongroup4.add(radioButton4_5_1);
        buttongroup4.add(radioButton4_5_2);
        buttongroup4.add(radioButton4_5_3);
        buttongroup4.add(radioButton4_5_4);
        buttongroup4.add(radioButton4_5_5);
        buttongroup6.add(radioButton6_5_1);
        buttongroup6.add(radioButton6_5_2);
        
        //checkBoxs
        checkBox4_6_1 = new JCheckBox("����У��ģʽ");
        checkBox4_6_1.setEnabled(false);
        checkBox4_6_2 = new JCheckBox("��ʾ�����Ϣ");
        checkBox4_6_2.setEnabled(false);
        checkBox5_4 = new JCheckBox("�������ַ���");
        checkBox5_5 = new JCheckBox("���������Ϣ");
        checkBox6_6 = new JCheckBox("����У��ģʽ");
        
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
		panel2.add(panel2_warning);
		panel2_warning.add(label2_warning);
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
		panel3.add(Box.createVerticalStrut(180));
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
		panel4_6.add(checkBox4_6_1);
		panel4_6.add(checkBox4_6_2);
		panel4.add(panel4_7);
		panel4_7.add(button4_go);
		panel4.add(Box.createVerticalStrut(100));
		//Fifth Main Panel
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
		panel5_4.add(Box.createHorizontalStrut(30));
		panel5_4.add(checkBox5_4);
		panel5.add(panel5_5);
		panel5_5.add(label5_5);
		panel5_5.add(checkBox5_5);
		panel5.add(panel5_6);
		panel5_6.add(button5_go);
		panel5.add(Box.createVerticalStrut(170));
		//Sixth Main Panel
		panel6.setLayout(new BoxLayout(panel6,BoxLayout.Y_AXIS));
		panel6.add(panel6_1);
		panel6_1.add(label6_1);
		panel6_1.add(button6_open1);
		panel6_1.add(textField6_1);
		panel6.add(panel6_2);
		panel6_2.add(label6_2);
		panel6_2.add(button6_open2);
		panel6_2.add(textField6_2);
		panel6.add(panel6_3);
		panel6_3.add(label6_3);
		panel6_3.add(button6_open3);
		panel6_3.add(textField6_3);
		panel6.add(panel6_4);
		panel6_4.add(label6_4);
		panel6_4.add(button6_open4);
		panel6_4.add(textField6_4);
		panel6.add(panel6_5);
		panel6_5.add(label6_5);
		panel6_5.add(radioButton6_5_1);
		panel6_5.add(radioButton6_5_2);
		panel6.add(panel6_6);
		panel6_6.add(label6_6);
		panel6_6.add(checkBox6_6);
		panel6.add(panel6_7);
		panel6_7.add(button6_go);
		panel6.add(Box.createVerticalStrut(160));
		//Seventh Main Panel
		panel7.setLayout(new BoxLayout(panel7,BoxLayout.Y_AXIS));
		panel7.add(panel7_1);
		panel7_1.add(label7_1);
		panel7_1.add(button7_open1);
		panel7_1.add(textField7_1);
		panel7.add(panel7_2);
		panel7_2.add(label7_2);
		panel7_2.add(button7_open2);
		panel7_2.add(textField7_2);
		panel7.add(panel7_3);
		panel7_3.add(label7_3);
		panel7_3.add(textField7_3);
		panel7.add(panel7_4);
		panel7_4.add(button7_go);
		panel7.add(Box.createVerticalStrut(300));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	
	//main����
	public static void main(String[] args) {
		new MainFrame();
	}

	//ʵ�ּ��������¼�
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
		if(e.getSource() == radioButton4_5_1 || e.getSource() == radioButton4_5_2 || e.getSource() == radioButton4_5_3 || e.getSource() == radioButton4_5_4 || e.getSource() == radioButton4_5_5)
		{
			 if(radioButton4_5_2.isSelected()){	
				 checkBox4_6_1.setEnabled(true);
				 checkBox4_6_2.setEnabled(true);
			 }
			 else if(radioButton4_5_5.isSelected()){
				 checkBox4_6_1.setEnabled(false);
				 checkBox4_6_1.setSelected(false);
				 checkBox4_6_2.setEnabled(true);
			 }
			 else{	
				 checkBox4_6_1.setEnabled(false);
				 checkBox4_6_1.setSelected(false);
				 checkBox4_6_2.setEnabled(false);
				 checkBox4_6_2.setSelected(false);
			 }
		}
		if(e.getSource() == radioButton6_5_1){
			label6_1.setText("�ο���Ӣ���ı�en_US.lang:");
			textField6_2.setEnabled(true);
			button6_open2.setEnabled(true);
		}
		else if(e.getSource() == radioButton6_5_2){
			label6_1.setText("ÿ��Ϊ\"Ӣ��=����\"��ʽ��utf-8�����ļ�:");
			textField6_2.setText("");
			textField6_2.setEnabled(false);
			button6_open2.setEnabled(false);
		}
		if(command == "Open"){
			filedialog_open = new FileDialog(this, "���ļ�", FileDialog.LOAD);
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
			else if(e.getSource() == button6_open1){
				if(directory != null && filename != null)
					textField6_1.setText(directory + filename);
			}
			else if(e.getSource() == button6_open2){
				if(directory != null && filename != null)
					textField6_2.setText(directory + filename);
			}
			else if(e.getSource() == button6_open3){
				if(directory != null && filename != null)
					textField6_3.setText(directory + filename);
			}
			else if(e.getSource() == button6_open4){
				if(directory != null && filename != null)
					textField6_4.setText(directory + filename);
			}
			else if(e.getSource() == button7_open1){
				if(directory != null && filename != null)
					textField7_1.setText(directory + filename);
			}
			else if(e.getSource() == button7_open2){
				if(directory != null && filename != null)
					textField7_2.setText(directory + filename);
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
				if(textField2_5.getText().indexOf("��") == -1){
					String []rowNumberSplit = {"Blank"};
					if(!textField2_5.getText().trim().equals("")) 
						rowNumberSplit = textField2_5.getText().split(",");
					String []specialIgnoreSymbol = textField2_6.getText().split("-");	
					if(textField2_6.getText().length()!=3 && textField2_6.getText().length()!=0 && (!(textField2_6.getText().length() == 3 && specialIgnoreSymbol.length!=2)))
						 new MessageWindow(this,"����","�滻���Է������ʽ����",-1);
					else{	
						FileModifier fileModifier = new FileModifier(this,fileInput,fileOutput,sOrigin,sTo,rowNumberSplit,specialIgnoreSymbol);
						fileModifier.functionConditionalReplace();
					}
				}
				else
					 new MessageWindow(this,"����","����,��ʹ��Ӣ�Ķ��ŷָ���",-1);
			}
			else if(e.getSource() == button3_go){
				String fileInput = textField3_1.getText();
				String fileOutput = textField3_2.getText();
				String sInterval = textField3_3_1.getText();
				String sAdd = textField3_3_2.getText();
				if(textField3_4.getText().indexOf("��") == -1){
					String []rowNumberSplit = {"Blank"};
					if(!textField3_4.getText().trim().equals("")) 
						rowNumberSplit = textField3_4.getText().split(",");
					FileModifier fileModifier = new FileModifier(this,fileInput,fileOutput,sInterval,sAdd,rowNumberSplit);
					fileModifier.functionAdd();				
				}
				else
					 new MessageWindow(this,"����","����,��ʹ��Ӣ�Ķ��ŷָ���",-1);
				
			}
			else if(e.getSource() == button4_go){
				String fileInput1 = textField4_1.getText();
				String fileInput2 = textField4_2.getText();
				String fileInput3 = textField4_3.getText();
				String fileOutput = textField4_4.getText();
				int updateType = radioButton4_5_1.isSelected()?1:radioButton4_5_2.isSelected()?2:radioButton4_5_3.isSelected()?3:radioButton4_5_4.isSelected()?4:radioButton4_5_5.isSelected()?5:-1;
				int checkModeStatus = (updateType == 2 && checkBox4_6_1.isSelected())? 1 : 0;
				int informationModeStatus = ((updateType == 2 || updateType == 5) && checkBox4_6_2.isSelected())? 1 : 0;
				updateType = updateType * 100 + checkModeStatus * 10 + informationModeStatus;
				FileModifier fileModifier = new FileModifier(this,fileInput1,fileInput2,fileInput3,fileOutput,updateType);
				fileModifier.functionUpdateLocalization();		
			}
			else if(e.getSource() == button5_go){
				String fileInput1 = textField5_1.getText();
				String fileOutput = textField5_2.getText();
				String sRemoveFlag = textField5_3.getText();
				int flag1 = checkBox5_4.isSelected()?1:0;
				int flag2 = checkBox5_5.isSelected()?1:0;
				FileModifier fileModifier = new FileModifier(this,fileInput1,fileOutput,sRemoveFlag,flag1*10+flag2);
				fileModifier.functionConditionalRemove();		
			}
			else if(e.getSource() == button6_go){
				String fileInput1 = textField6_1.getText();
				String fileInput2 = textField6_2.getText();
				String fileInput3 = textField6_3.getText();
				String fileOutput = textField6_4.getText();
				int updateType = radioButton6_5_1.isSelected()?0:1;
				int checkModeStatus = checkBox6_6.isSelected()?1:0;
				FileModifier fileModifier = new FileModifier(this,fileInput1,fileInput2,fileInput3,fileOutput,updateType*10+checkModeStatus);
				fileModifier.functionAutoReplaceEnglishTextWithChineseTranslation();
			}
			else if(e.getSource() == button7_go){
				String fileInput1 = textField7_1.getText();
				String fileOutput = textField7_2.getText();
				if(textField7_3.getText().indexOf("��")==-1){
					String []filter;
					filter = textField7_3.getText().split(",");
					FileModifier fileModifier = new FileModifier(this,fileInput1,fileOutput,filter);
					fileModifier.functionWordCount();
					
				}
				else
					new MessageWindow(this,"����","����,��ʹ��Ӣ�Ķ��ŷָ���",-1);
			}
		}
	}
    private FileDialog filedialog_open;  

}
