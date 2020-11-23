//Front: 전재욱
//Back: 전재욱
//Last Update : 20.11.23
//Des: 로그인 프레임 및 디자인

package CoronaSystem.UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import CoronaSystem.CoronaSystem;

public class LoginGUI extends JFrame{

	private int frameWidth = 500; //프레임 가로 크기
	private int frameHeight = 400; //프레임 세로크기
	private JFormattedTextField tf_id; //id 입력 필드
	private JPasswordField tf_pw; //pw 입력 필드
	private JButton btn_findPassword; //비밀번호 찾기 버튼
	
	public LoginGUI() {
		setResizable(false); //프레임 크기 조절 막기
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		
		setSize(frameWidth,frameHeight); //프레임 크기 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE); //로그인 프레임 닫으면 프로그램 종료
		addWindowListener(new JFrameWindowClosingEventHandler()); //창 닫기 이벤트
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("코로나 관리 시스템 - 로그인"); //프레임 제목 설정
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE); //배경색 설정
		
		//둥근 모서리
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//리소스 미리 불러오기
		URL titleIconSrc = LoginGUI.class.getResource("/resources/titleIcon.png");
		ImageIcon titleIcon = MyUtility.resizeImage(new ImageIcon(titleIconSrc), 70, 70);
		setIconImage(titleIcon.getImage());
		
		URL logoSrc = LoginGUI.class.getResource("/resources/logo.png");
		ImageIcon logoIcon = MyUtility.resizeImage(new ImageIcon(logoSrc), 70, 70);
		
		URL finderSrc = LoginGUI.class.getResource("/resources/finder.png");
		ImageIcon finderIcon = MyUtility.resizeImage(new ImageIcon(finderSrc), 20, 20);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(80, 167, 340, 150);
		getContentPane().add(centerPanel);
		centerPanel.setLayout(null);
		centerPanel.setBackground(MyColor.LIGHTSKY);
		
		JLabel lb_adminLogin = new JLabel("관리자 로그인");
		lb_adminLogin.setForeground(MyColor.PLUSIANBLUE);
		lb_adminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lb_adminLogin.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lb_adminLogin.setBounds(70, 10, 200, 30);
		centerPanel.add(lb_adminLogin);
		centerPanel.setBorder(new LineBorder(MyColor.PLUSIANBLUE));
		
		JLabel lb_id = new JLabel("ID");
		lb_id.setForeground(MyColor.PLUSIANBLUE);
		lb_id.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lb_id.setBounds(35, 65, 25, 20);
		centerPanel.add(lb_id);
		
		tf_id = new JFormattedTextField();
		tf_id.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_id.setBounds(90, 65, 220, 25);
		tf_id.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		centerPanel.add(tf_id);
		tf_id.setColumns(10);
		tf_id.setBackground(MyColor.LIGHTGRAY);
		tf_id.setForeground(Color.black);
		tf_id.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == 10) { // 엔터키 키를 눌렀으면
                	tryLogin(); //로그인 시도
                } 
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
//            	char c = e.getKeyChar(); 숫자만 입력 받을 때 사용
//            	if(!Character.isDigit(c)) {
//            		e.consume();
//            	}
            	
            	JTextField tf = (JTextField) e.getSource();
            	if(tf.getText().length() >= 12) { //12자 이상 입력 못하게 제한
            		e.consume();
            	}
            }
        });
		
		tf_pw = new JPasswordField();
		tf_pw.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_pw.setBounds(90, 100, 220, 25);
		tf_pw.setEchoChar('*'); //해당 칸에는 입력시 * 로 표시함
		tf_pw.setColumns(10);
		tf_pw.setBackground(MyColor.LIGHTGRAY);
		tf_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_pw.setForeground(Color.black);
		tf_pw.setBorder(new LineBorder(MyColor.WHITE, 2));	
		tf_pw.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == 10) { // 엔터키 키를 눌렀으면
                	tryLogin();
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
            	JPasswordField tf = (JPasswordField) e.getSource();
            	if(tf.getText().length() >= 14) { //14자 이상 입력 못하게 제한
            		e.consume();
            	}
            }
        });
		centerPanel.add(tf_pw);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setForeground(MyColor.PLUSIANBLUE);
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblPw.setBounds(35, 100, 45, 20);
		centerPanel.add(lblPw);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(124, 40, 260, 90);
		getContentPane().add(titlePanel);
		titlePanel.setLayout(null);
		titlePanel.setBackground(MyColor.STEELBLUE);
		titlePanel.setBorder(brdr);
		
		JLabel lb_icon_logo = new JLabel(logoIcon);
		lb_icon_logo.setBounds(12, 10, 70, 70);
		titlePanel.add(lb_icon_logo);
		
		JLabel lb_titleTop = new JLabel("Confirmed case -");
		lb_titleTop.setForeground(Color.WHITE);
		lb_titleTop.setHorizontalAlignment(SwingConstants.LEFT);
		lb_titleTop.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lb_titleTop.setBounds(94, 17, 250, 20);
		titlePanel.add(lb_titleTop);
		
		JLabel lb_titleBottom = new JLabel("human traffic");
		lb_titleBottom.setForeground(Color.WHITE);
		lb_titleBottom.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lb_titleBottom.setHorizontalAlignment(SwingConstants.CENTER);
		lb_titleBottom.setBounds(62, 47, 250, 20);
		titlePanel.add(lb_titleBottom);
		
		btn_findPassword = new JButton("Password 찾기");
		btn_findPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		btn_findPassword.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btn_findPassword.setBounds(350, 338, 130, 25);
		btn_findPassword.setOpaque(false);
		btn_findPassword.setBorder(null);
		btn_findPassword.setContentAreaFilled(false);
		btn_findPassword.setFocusable(false);
		btn_findPassword.setIcon(finderIcon);
		btn_findPassword.addActionListener(new ActionListener() { //비밀번호 찾기 클릭했다면
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame findPasswordGUI = new FindPasswordGUI(); //비밀번호 찾기 프레임 띄우기
			}
		});
		getContentPane().add(btn_findPassword);
		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}
	
	private void tryLogin() { //로그인 시도
		if (tf_id.getText().equals("")) { //ID입력 확인
			CheckGUI cf = new CheckGUI(this, "ID를 입력해주세요.", false, false);
		} else if (tf_pw.getText().equals("")) { //pW입려 확인
			CheckGUI cf = new CheckGUI(this, "PW를 입력해주세요.", false, false);
		} else {
			String id = tf_id.getText();
			String pw = ""; // 입력한 pw 저장할 곳
			char[] tmpPw = tf_pw.getPassword(); // 반환값이 char[] 이기 때문에 string 으로 바꾸기 위한 작업 필요
			for (char tmpCh : tmpPw) {
				Character.toString(tmpCh); // 한글자씩 가져와서 string으로 합침
				pw += tmpCh;
			}		
			
			String resPW = CoronaSystem.database.getPassword(id); //데이터베이스에서 id를 이용하여 pw를 가져옴
			if(resPW == null) { //데이터베이스에 해당 id가 없다면
				new CheckGUI(this, "존재하지 않는 ID입니다.", false, false); //실패 메시지와 이유 전달
			} else { 
				if(!pw.equals(resPW)) { //데이터베이스의 pw값과 사용자가 보낸 pw값이 일치하지 않다면 
					new CheckGUI(this, "비밀번호가 틀립니다.", false, false); //실패 메시지와 이유 전달
				} else { //로그인 성공 시 
					new HomeGUI(); // 메인 메뉴 프레임 띄우기
					this.dispose(); //로그인 프레임 닫기
				}
				
			}
		}
	}
	
	
	private class JFrameWindowClosingEventHandler extends WindowAdapter { //창 닫기시
		public void windowClosing(WindowEvent e) {
			if(e.getWindow() instanceof LoginGUI) { //로그인 화면 닫으면
				System.exit(0); //프로그램 종료
			}	
		}
	}
}
