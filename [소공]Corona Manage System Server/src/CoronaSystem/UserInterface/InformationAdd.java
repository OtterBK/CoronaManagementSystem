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

public class InformationAdd extends JFrame{

	private int frameWidth = 500;
	private int frameHeight = 400;
	private JTextField textField;
	private JLabel lblNewLabel_6;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	public InformationAdd() {
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new JFrameWindowClosingEventHandler()); //창 닫기 이벤트
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("확진자 프로필 정보 추가");
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE);
		
		//둥근 모서리
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//리소스 미리 불러오기
		URL titleIconSrc = InformationAdd.class.getResource("/resources/titleIcon.png");
		ImageIcon titleIcon = MyUtility.resizeImage(new ImageIcon(titleIconSrc), 70, 70);
		setIconImage(titleIcon.getImage());
		
		URL logoSrc = InformationAdd.class.getResource("/resources/logo.png");
		ImageIcon logoIcon = MyUtility.resizeImage(new ImageIcon(logoSrc), 70, 70);
		
		URL finderSrc = InformationAdd.class.getResource("/resources/finder.png");
		ImageIcon finderIcon = MyUtility.resizeImage(new ImageIcon(finderSrc), 20, 20);
		
		URL homeSrc = LoginGUI.class.getResource("/resources/home.png");
		ImageIcon homeIcon = MyUtility.resizeImage(new ImageIcon(homeSrc), 50, 50);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 30, 494, 70);
		getContentPane().add(topPanel);
		topPanel.setLayout(null);
		topPanel.setBackground(MyColor.LIGHTSKY);
		topPanel.setBorder(brdr);
		
		JLabel lb_title = new JLabel("확진자 프로필 정보 추가");
		lb_title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		lb_title.setHorizontalAlignment(SwingConstants.LEFT);
		lb_title.setBounds(50, 10, 250, 50);
		lb_title.setForeground(MyColor.PLUSIANBLUE);
		topPanel.add(lb_title);;
		
		JLabel lb_homeIcon = new JLabel(homeIcon);
		lb_homeIcon.setBounds(430, 10, 50, 50);
		topPanel.add(lb_homeIcon);
		
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1.setBounds(50, 140, 60, 20);
		lblNewLabel_1.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uB098\uC774");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_2.setBounds(50, 180, 60, 20);
		lblNewLabel_2.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC131\uBCC4");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_3.setBounds(50, 220, 60, 20);
		lblNewLabel_3.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uAC70\uC8FC\uC9C0");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_4.setBounds(50, 260, 60, 20);
		lblNewLabel_4.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uD655\uC9C4\uB0A0\uC9DC(\uB144, \uC6D4, \uC77C)");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_5.setBounds(50, 300, 120, 20);
		lblNewLabel_5.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(160, 140, 300, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(160, 180, 300, 20);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(160, 220, 300, 20);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(160, 260, 300, 20);
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(160, 300, 100, 20);
		getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(270, 300, 90, 20);
		getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(370, 300, 90, 20);
		getContentPane().add(textField_7);
		
		JLabel lblNewLabel = new JLabel("ID: 1");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		lblNewLabel.setBounds(211, 100, 60, 28);
		lblNewLabel.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_7 = new JLabel("\uD655\uC778");
		lblNewLabel_7.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		lblNewLabel_7.setBounds(422, 333, 60, 28);
		lblNewLabel_7.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_7);
		
		
		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}
	
	
	
	
	private class JFrameWindowClosingEventHandler extends WindowAdapter { //창 닫기시
		public void windowClosing(WindowEvent e) {
			if(e.getWindow() instanceof InformationAdd) { //홈 화면 닫으면
				System.exit(0); //프로그램 종료
			}	
		}
	}
}
