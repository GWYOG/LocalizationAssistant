package pers.gwyog.localizationassistant;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MessageWindow extends JFrame implements ActionListener{
	
	/*
	 *	ʵ������LA����Ϣ��ʾ����(�ȴ����ɹ���ʧ��)
	 *	@Author: JJN(GWYOG)
	 */
	
	private static final long serialVersionUID = 1L;
	private ImageIcon imageIcon;
	private JLabel label,imageLabel;
	private JButton button;
	private String directory;
	private String message;

	
	public MessageWindow(MainFrame parentFrame, String title, String messageOrigin ,int imageType){
		super(title);
		int button_flag = 0;
		int icon_size = 40;
		switch(imageType){
			case -1: 
				directory="resources/error.png";message=messageOrigin;button = new JButton("ȷ��");button_flag=1;break;
			case 0: // to-change
				directory="resources/loading.gif";icon_size = 70;message="waiting...";break;
			case 1: 
				directory="resources/complete.png";message="Done!";button = new JButton("ȷ��");button_flag=1;break;
		}
		this.setSize(300,150);
		this.setAlwaysOnTop(true);
		int x=(int) (parentFrame.getLocation().getX() + parentFrame.getWidth()/5);
		int y=(int) (parentFrame.getLocation().getY() + parentFrame.getHeight()/2.5);
		this.setLocation(x, y);
		imageIcon = new ImageIcon(new ImageIcon(directory).getImage().getScaledInstance(icon_size, icon_size, Image.SCALE_DEFAULT));
		imageLabel = new JLabel(imageIcon);
		label = new JLabel(message);
		this.setLayout(null);
		this.getContentPane().add(imageLabel);
		this.getContentPane().add(label);
		label.setFont(new Font("΢���ź�",1,16));
		if(button_flag == 1)
		{
			imageLabel.setBounds(-25, -20, 128, 128);
			label.setBounds(70,-7,250,100);
			this.getContentPane().add(button);
			button.setBounds(120,70,60,20);
			button.addActionListener(this);
		}
		else
		{	
			imageLabel.setBounds(-15, -10, 128, 128);
			label.setBounds(140,3,250,100);
		}	
		this.setVisible(true);
		this.isAlwaysOnTop();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 

	}
	
	/* 
	 	public static void main(String[] args) {
		messageWindow mw = new messageWindow(new mainFrame(),"���","waiting...",1);
	}
	*/

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
			this.dispose();
	}
	
	public void close(){
		this.dispose();
	}

}
