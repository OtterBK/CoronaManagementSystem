package CoronaSystem.UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;

import Addon.BubbleBorder;
import Addon.MyColor;
import Addon.MyUtility;
import javax.swing.JButton;

public class CoronaMapAdd extends JFrame{

	private int frameWidth = 500;
	private int frameHeight = 400;
	private JButton btn_findPassword;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblNewLabel_6;
	
	public CoronaMapAdd() {
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //»ç¿ëÀÚÀÇ È­¸é Å©±â°ªÀ» ¾ò±âÀ§ÇÑ ÅøÅ¶ Å¬·¡½º
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new JFrameWindowClosingEventHandler()); //Ã¢ ´Ý±â ÀÌº¥Æ®
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("ÄÚ·Î³ª °ü¸® ½Ã½ºÅÛ - ·Î±×ÀÎ");
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE);
		
		//µÕ±Ù ¸ð¼­¸®
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//¸®¼Ò½º ¹Ì¸® ºÒ·¯¿À±â
		URL titleIconSrc = CoronaMapAdd.class.getResource("/resources/titleIcon.png");
		ImageIcon titleIcon = MyUtility.resizeImage(new ImageIcon(titleIconSrc), 70, 70);
		setIconImage(titleIcon.getImage());
		
		URL logoSrc = CoronaMapAdd.class.getResource("/resources/logo.png");
		ImageIcon logoIcon = MyUtility.resizeImage(new ImageIcon(logoSrc), 70, 70);
		
		URL finderSrc = CoronaMapAdd.class.getResource("/resources/finder.png");
		ImageIcon finderIcon = MyUtility.resizeImage(new ImageIcon(finderSrc), 20, 20);
		
		URL homeSrc = LoginGUI.class.getResource("/resources/home.png");
		ImageIcon homeIcon = MyUtility.resizeImage(new ImageIcon(homeSrc), 50, 50);
		ImageIcon resizeIcon = MyUtility.resizeImage(homeIcon, 30, 30);
	
		
		lblNewLabel_6 = new JLabel(resizeIcon);
		lblNewLabel_6.setFont(new Font("±¼¸²", Font.PLAIN, 6));
		lblNewLabel_6.setIcon(new ImageIcon(CoronaMapAdd.class.getResource("/resources/home.png")));
		lblNewLabel_6.setBounds(391, 8, 78, 77);
		getContentPane().add(lblNewLabel_6);
		
		btn_findPassword = new JButton("\uD655\uC778");
		btn_findPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		btn_findPassword.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		btn_findPassword.setBounds(413, 336, 48, 25);
		btn_findPassword.setOpaque(false);
		btn_findPassword.setBorder(null);
		btn_findPassword.setContentAreaFilled(false);
		btn_findPassword.setFocusable(false);
		getContentPane().add(btn_findPassword);
		
		JLabel lblNewLabel = new JLabel("   \uD655\uC9C4\uC790 \uB3D9\uC120 \uC815\uBCF4 \uCD94\uAC00");
		lblNewLabel.setBackground(MyColor.LIGHTSKY);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 14, 494, 57);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new BubbleBorder(Color.BLACK, 2, 16, 0));
		getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("\uCD94\uAC00\uD560 \uD655\uC9C4\uC790 ID");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		lblNewLabel_1.setBounds(54, 93, 118, 36);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(184, 96, 197, 36);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uB0A0\uC9DC");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_2.setBounds(54, 161, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC2DC\uAC04");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_3.setBounds(54, 201, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC7A5\uC18C(\uC2DC,\uAD6C)");
		lblNewLabel_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_4.setBounds(54, 243, 73, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uC0C1\uC138 \uC7A5\uC18C(\uAC74\uBB3C)");
		lblNewLabel_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_5.setBounds(54, 284, 86, 15);
		getContentPane().add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 160, 277, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(184, 200, 277, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(184, 242, 116, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(184, 283, 277, 21);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(312, 242, 149, 21);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}
	
	
	
	
	private class JFrameWindowClosingEventHandler extends WindowAdapter { //Ã¢ ´Ý±â½Ã
		public void windowClosing(WindowEvent e) {
			if(e.getWindow() instanceof CoronaMapAdd) { //È¨ È­¸é ´ÝÀ¸¸é
				System.exit(0); //ÇÁ·Î±×·¥ Á¾·á
			}	
		}
	}
}
