//Front: 김태연
//Back: 
//Last Update : 20.11.22
//Des : 코로나 동선 추가 프레임

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
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new JFrameWindowClosingEventHandler()); //창 닫기 이벤트
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("코로나 관리 시스템 - 로그인");
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE);
		
		//둥근 모서리
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//리소스 미리 불러오기
		URL titleIconSrc = CoronaMapAdd.class.getResource("/resources/titleIcon.png");
		ImageIcon titleIcon = MyUtility.resizeImage(new ImageIcon(titleIconSrc), 70, 70);
		setIconImage(titleIcon.getImage());
		
		URL logoSrc = CoronaMapAdd.class.getResource("/resources/logo.png");
		ImageIcon logoIcon = MyUtility.resizeImage(new ImageIcon(logoSrc), 70, 70);
		
		URL finderSrc = CoronaMapAdd.class.getResource("/resources/finder.png");
		ImageIcon finderIcon = MyUtility.resizeImage(new ImageIcon(finderSrc), 20, 20);
		
		URL homeSrc = CoronaMapAdd.class.getResource("/resources/home.png");
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
		
		JLabel lblNewLabel = new JLabel("   \uD655\uC9C4\uC790 \uB3D9\uC120 \uC815\uBCF4 \uCD94\uAC00");
		lblNewLabel.setBackground(MyColor.LIGHTSKY);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel.setBounds(50, 10, 400, 60);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new BubbleBorder(Color.BLACK, 2, 16, 0));
		getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("\uCD94\uAC00\uD560 \uD655\uC9C4\uC790 ID");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1.setBounds(50, 100, 120, 35);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(184, 106, 200, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uB0A0\uC9DC");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_2.setBounds(50, 161, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC2DC\uAC04");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_3.setBounds(50, 201, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC7A5\uC18C(\uC2DC,\uAD6C)");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_4.setBounds(50, 243, 73, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uC0C1\uC138 \uC7A5\uC18C(\uAC74\uBB3C)");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_5.setBounds(50, 284, 86, 15);
		getContentPane().add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 160, 277, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setForeground(Color.black);
		textField_1.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(184, 200, 277, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setForeground(Color.black);
		textField_2.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(184, 242, 116, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setForeground(Color.black);
		textField_3.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(184, 283, 277, 21);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setForeground(Color.black);
		textField_4.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(312, 242, 149, 21);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		textField_5.setForeground(Color.black);
		textField_5.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(textField_5);
		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}
	
	
	
	
	private class JFrameWindowClosingEventHandler extends WindowAdapter { //창 닫기시
		public void windowClosing(WindowEvent e) {
			if(e.getWindow() instanceof CoronaMapAdd) { //홈 화면 닫으면
				System.exit(0); //프로그램 종료
			}	
		}
	}
}
