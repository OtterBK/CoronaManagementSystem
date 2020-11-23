//Front: ¹éÀÎ±Ô
//Back: ÃÖÁöÇý
//Last Update : 20.11.22
//Des : È®ÁøÀÚ Á¤º¸ ¼öÁ¤ ÇÁ·¹ÀÓ

package coronamanagesystem.userinterface;

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

import addon.BubbleBorder;
import addon.MyColor;
import addon.MyUtility;

import javax.swing.JButton;

public class InformationChange extends JFrame{

	private int frameWidth = 500;
	private int frameHeight = 400;
	private JButton btn_findPassword;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel_6;
	private JTextField textField_6;
	private JTextField textField_5;
	private JTextField textField_7;
	
	public InformationChange() {
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //»ç¿ëÀÚÀÇ È­¸é Å©±â°ªÀ» ¾ò±âÀ§ÇÑ ÅøÅ¶ Å¬·¡½º
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("È®ÁøÀÚ ÇÁ·ÎÇÊ Á¤º¸ ¼öÁ¤");
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE);
		
		//µÕ±Ù ¸ð¼­¸®
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//¸®¼Ò½º ¹Ì¸® ºÒ·¯¿À±â
		URL titleIconSrc = InformationChange.class.getResource("/resources/titleIcon.png");
		ImageIcon titleIcon = MyUtility.resizeImage(new ImageIcon(titleIconSrc), 70, 70);
		setIconImage(titleIcon.getImage());
		
		URL logoSrc = InformationChange.class.getResource("/resources/logo.png");
		ImageIcon logoIcon = MyUtility.resizeImage(new ImageIcon(logoSrc), 70, 70);
		
		URL finderSrc = InformationChange.class.getResource("/resources/finder.png");
		ImageIcon finderIcon = MyUtility.resizeImage(new ImageIcon(finderSrc), 20, 20);
		
		URL homeSrc = InformationChange.class.getResource("/resources/home.png");
		ImageIcon homeIcon = MyUtility.resizeImage(new ImageIcon(homeSrc), 50, 50);
	
		
		lblNewLabel_6 = new JLabel(homeIcon);
		lblNewLabel_6.setFont(new Font("±¼¸²", Font.PLAIN, 6));
		lblNewLabel_6.setBounds(390, 15, 50, 50);
		getContentPane().add(lblNewLabel_6);
		
		btn_findPassword = new JButton("\uD655\uC778");
		btn_findPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		btn_findPassword.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		btn_findPassword.setBounds(413, 326, 57, 35);
		btn_findPassword.setOpaque(false);
		btn_findPassword.setBorder(null);
		btn_findPassword.setContentAreaFilled(false);
		btn_findPassword.setFocusable(false);
		btn_findPassword.setForeground(MyColor.SLATEGRAY);
		getContentPane().add(btn_findPassword);
		
		JLabel lblNewLabel = new JLabel("   \uD655\uC9C4\uC790 \uD504\uB85C\uD544 \uC815\uBCF4 \uC218\uC815");
		lblNewLabel.setBackground(MyColor.LIGHTSKY);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel.setBounds(50, 10, 400, 60);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new BubbleBorder(Color.BLACK, 2, 16, 0));
		lblNewLabel.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("\uC218\uC815\uD560 ID");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_1.setBounds(50, 80, 120, 35);
		lblNewLabel_1.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(184, 80, 150, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uB098\uC774");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_2.setBounds(50, 165, 50, 15);
		lblNewLabel_2.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC131\uBCC4");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_3.setBounds(50, 200, 50, 15);
		lblNewLabel_3.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uAC70\uC8FC\uC9C0");
		lblNewLabel_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_4.setBounds(50, 235, 50, 15);
		lblNewLabel_4.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uD655\uC9C4\uB0A0\uC9DC(\uB144, \uC6D4, \uC77C)");
		lblNewLabel_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_5.setBounds(50, 270, 120, 15);
		lblNewLabel_5.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 165, 270, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setForeground(Color.black);
		textField_1.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(184, 200, 270, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setForeground(Color.black);
		textField_2.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(184, 235, 270, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setForeground(Color.black);
		textField_3.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(184, 270, 90, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setForeground(Color.black);
		textField_4.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_4);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uC774\uB984");
		lblNewLabel_2_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(50, 130, 57, 15);
		lblNewLabel_2_1.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_2_1);
		
		textField_6 = new JTextField();
		textField_6.setForeground(Color.BLACK);
		textField_6.setColumns(10);
		textField_6.setBorder(new LineBorder(MyColor.WHITE, 2));
		textField_6.setBounds(184, 130, 270, 20);
		getContentPane().add(textField_6);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.BLACK);
		textField_5.setColumns(10);
		textField_5.setBorder(new LineBorder(MyColor.WHITE, 2));
		textField_5.setBounds(285, 270, 80, 20);
		getContentPane().add(textField_5);
		
		textField_7 = new JTextField();
		textField_7.setForeground(Color.BLACK);
		textField_7.setColumns(10);
		textField_7.setBorder(new LineBorder(MyColor.WHITE, 2));
		textField_7.setBounds(374, 270, 80, 20);
		getContentPane().add(textField_7);
		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}

}
