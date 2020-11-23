//Front: 김태연
//Back: 
//Last Update : 20.11.22
//Des : 코로나 동선 추가 프레임

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
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.Box;
import java.awt.Dimension;

public class CoronaMapDel extends JFrame{

	private int frameWidth = 500;
	private int frameHeight = 400;
	private JButton btn_findPassword;
	private JTextField textField;
	private JLabel lblNewLabel_6;
	
	public CoronaMapDel() {
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("코로나 관리 시스템 - 로그인");
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE);
		
		//둥근 모서리
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//리소스 미리 불러오기
		URL titleIconSrc = CoronaMapDel.class.getResource("/resources/titleIcon.png");
		ImageIcon titleIcon = MyUtility.resizeImage(new ImageIcon(titleIconSrc), 70, 70);
		setIconImage(titleIcon.getImage());
		
		URL logoSrc = CoronaMapDel.class.getResource("/resources/logo.png");
		ImageIcon logoIcon = MyUtility.resizeImage(new ImageIcon(logoSrc), 70, 70);
		
		URL finderSrc = CoronaMapDel.class.getResource("/resources/finder.png");
		ImageIcon finderIcon = MyUtility.resizeImage(new ImageIcon(finderSrc), 20, 20);
		
		URL homeSrc = CoronaMapDel.class.getResource("/resources/home.png");
		ImageIcon homeIcon = MyUtility.resizeImage(new ImageIcon(homeSrc), 50, 50);
	
		
		lblNewLabel_6 = new JLabel(homeIcon);
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 6));
		lblNewLabel_6.setBounds(390, 15, 50, 50);
		getContentPane().add(lblNewLabel_6);
		
		btn_findPassword = new JButton("\uD655\uC778");
		btn_findPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		btn_findPassword.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btn_findPassword.setBounds(413, 336, 48, 25);
		btn_findPassword.setOpaque(false);
		btn_findPassword.setBorder(null);
		btn_findPassword.setContentAreaFilled(false);
		btn_findPassword.setFocusable(false);
		getContentPane().add(btn_findPassword);
		
		JLabel lblNewLabel = new JLabel("\uD655\uC9C4\uC790 \uB3D9\uC120 \uC815\uBCF4 \uC0AD\uC81C");
		lblNewLabel.setBackground(MyColor.LIGHTSKY);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel.setBounds(50, 10, 400, 60);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new BubbleBorder(Color.BLACK, 2, 16, 0));
		getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("\uC0AD\uC81C\uD560 \uD655\uC9C4\uC790 ID");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1.setBounds(50, 100, 120, 35);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(184, 106, 178, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setMaximumSize(new Dimension(5, 0));
		verticalBox.setBounds(76, 145, 345, 203);
		getContentPane().add(verticalBox);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setMaximumSize(new Dimension(350, 25));
		verticalBox.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setMaximumSize(new Dimension(350, 25));
		verticalBox.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setMaximumSize(new Dimension(350, 25));
		verticalBox.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setMaximumSize(new Dimension(350, 25));
		verticalBox.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setMaximumSize(new Dimension(350, 25));
		verticalBox.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setMaximumSize(new Dimension(350, 25));
		verticalBox.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setMaximumSize(new Dimension(350, 25));
		verticalBox.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setMaximumSize(new Dimension(350, 25));
		verticalBox.add(lblNewLabel_10);
		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}

}
