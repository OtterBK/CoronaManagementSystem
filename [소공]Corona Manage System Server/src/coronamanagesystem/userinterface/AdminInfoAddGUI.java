//Front: 전재욱
//Back: 
//Last Update : 20.11.23
//Des : 데이터베이스에 관리자 정보 추가할 수 있는 프레임 및 기능

package coronamanagesystem.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;

import addon.BubbleBorder;
import addon.IDFilter;
import addon.MyColor;
import addon.MyUtility;
import coronamanagesystem.CoronaSystem;

public class AdminInfoAddGUI extends JFrame{

	private int frameWidth = 400; //가로
	private int frameHeight = 450; //세로
	private JTextField tf_adminName; //어드민명 입력 텍스트필드
	private JTextField tf_id; //id 입력 텍스트필드
	private JPasswordField tf_password; //pw 입력 텍스트필드
	private JPasswordField tf_confirmPassword; //pw 확인 입력 테스트 필드
	private JLabel lbl_tempMessage;
	
	private JFrame frame; //자기 자신의 주소 저장용
	
	public AdminInfoAddGUI() {
		frame = this;
		
		setResizable(false); //창 사이즈 변경  불가능
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		
		setSize(frameWidth,frameHeight); //사이즈 설정
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //창 닫으면 그냥 닫힘
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		//화면 가운데
		
		setTitle("관리자 추가"); //타이틀 설정
		getContentPane().setBackground(MyColor.ALICEBLUE); //배경색은 앨리스 블루
		getContentPane().setLayout(null); //절대 배치 사용

		// 둥근 모서리
		AbstractBorder brdr = new BubbleBorder(Color.BLACK, 2, 16, 0);

		// 리소스 미리 불러오기
		URL src = AdminInfoAddGUI.class.getResource("/resources/check.png");
		ImageIcon checkIcon = MyUtility.resizeImage(new ImageIcon(src), 70, 70);
		
		src = AdminInfoAddGUI.class.getResource("/resources/check.png");
		ImageIcon homeIcon = MyUtility.resizeImage(new ImageIcon(src), 50, 50);
		
		JPanel topPanel = new JPanel(); //제목 나올 패널
		topPanel.setBounds(50, 10, 300, 70);
		getContentPane().add(topPanel);
		topPanel.setLayout(null);
		topPanel.setBackground(MyColor.LIGHTSKY);
		topPanel.setBorder(brdr);
		
		JLabel lb_title = new JLabel("계정 추가"); //제목
		lb_title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		lb_title.setHorizontalAlignment(SwingConstants.LEFT);
		lb_title.setBounds(12, 10, 170, 50);
		lb_title.setForeground(MyColor.PLUSIANBLUE);
		topPanel.add(lb_title);
		
		JLabel lb_homeIcon = new JLabel(homeIcon);
		lb_homeIcon.setBounds(235, 12, 50, 50);
		topPanel.add(lb_homeIcon);
		
		JLabel lbl_tfDes1 = new JLabel("관리자명"); 
		lbl_tfDes1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_tfDes1.setBounds(50, 115, 55, 25);
		lbl_tfDes1.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lbl_tfDes1);
		
		tf_adminName = new JTextField();
		tf_adminName.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_adminName.setBounds(125, 115, 210, 25);
		tf_adminName.setBackground(MyColor.LIGHTGRAY);
		tf_adminName.setForeground(Color.black);
		tf_adminName.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(tf_adminName);
		tf_adminName.setColumns(10);
		tf_adminName.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_adminName.addKeyListener(new KeyAdapter() { //관리자명은 최대 10글자까지만 입력 가능
			
			 @Override
	            public void keyTyped(KeyEvent e) {
				 JTextField tf = (JTextField) e.getSource();
	            	if(tf.getText().length() >= 10) {
	            		e.consume();
	            	}
	            }
			
		});
		
		tf_id = new JTextField();
		tf_id.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_id.setColumns(10);
		tf_id.setBounds(125, 160, 210, 25);
		tf_id.setBackground(MyColor.LIGHTGRAY);
		tf_id.setForeground(Color.black);
		tf_id.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_id.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		((AbstractDocument) tf_id.getDocument()).setDocumentFilter(new IDFilter()); //알파벳, 숫자만 허용
		tf_id.addKeyListener(new KeyAdapter() { //id는 12글자까지만 입력가능
			
			 @Override
	            public void keyTyped(KeyEvent e) {
	            	JTextField tf = (JTextField) e.getSource();
	            	if(tf.getText().length() >= 12) { //12자 이상 입력 못하게 제한
	            		e.consume();
	            	}      	
	            	
	            }
			
		});
		getContentPane().add(tf_id);
		
		JLabel lbl_tfDes2 = new JLabel("ID");
		lbl_tfDes2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_tfDes2.setBounds(50, 160, 55, 25);
		lbl_tfDes2.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lbl_tfDes2);
		
		tf_password = new JPasswordField();
		tf_password.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_password.setColumns(10);
		tf_password.setBounds(125, 205, 210, 25);
		tf_password.setBackground(MyColor.LIGHTGRAY);
		tf_password.setForeground(Color.black);
		tf_password.setEchoChar('*'); //해당 칸에는 입력시 * 로 표시함
		tf_password.setBorder(new LineBorder(MyColor.WHITE, 2));	
		tf_password.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_password.addKeyListener(new KeyAdapter() { //pw는 14글자까지만 입력 가능
			
			 @Override
	            public void keyTyped(KeyEvent e) {
	            	JPasswordField tf = (JPasswordField) e.getSource();
	            	if(tf.getText().length() >= 14) {
	            		e.consume();
	            	}
	            }
			
		});
		getContentPane().add(tf_password);
		
		JLabel lbl_tfDes3 = new JLabel("Password");
		lbl_tfDes3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_tfDes3.setBounds(50, 205, 55, 25);
		lbl_tfDes3.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lbl_tfDes3);
		
		tf_confirmPassword = new JPasswordField();
		tf_confirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_confirmPassword.setColumns(10);
		tf_confirmPassword.setBounds(125, 250, 210, 25);
		tf_confirmPassword.setBackground(MyColor.LIGHTGRAY);
		tf_confirmPassword.setForeground(Color.black);
		tf_confirmPassword.setBorder(new LineBorder(MyColor.WHITE, 2));	
		tf_confirmPassword.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_confirmPassword.setEchoChar('*'); //해당 칸에는 입력시 * 로 표시함
		tf_confirmPassword.addKeyListener(new KeyAdapter() { //pw확인은 14글자까지만 입력가능
			
			 @Override
	            public void keyTyped(KeyEvent e) {
	            	JPasswordField tf = (JPasswordField) e.getSource();
	            	if(tf.getText().length() >= 14) {
	            		e.consume();
	            	}
	            }
			
		});
		getContentPane().add(tf_confirmPassword);
		
		JLabel lbl_tfDes4 = new JLabel("PW 확인");
		lbl_tfDes4.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_tfDes4.setForeground(MyColor.PLUSIANBLUE);
		lbl_tfDes4.setBounds(50, 250, 55, 25);
		getContentPane().add(lbl_tfDes4);
		
		lbl_tempMessage = new JLabel("");
		lbl_tempMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tempMessage.setBounds(50, 300, 285, 30);
		lbl_tempMessage.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		getContentPane().add(lbl_tempMessage);
		
		JButton btn_add = new JButton("추가");
		btn_add.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btn_add.setForeground(MyColor.WHITE);
		btn_add.setFocusPainted(false);
		btn_add.setBounds(238, 340, 97, 23);
		btn_add.setBackground(MyColor.NAVY);
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //추가 버튼 눌렀을 때 
				String inputId = tf_id.getText(); //입력한 id값
				if(inputId.equals("")) {
					sendTempMsg("추가할 ID를 입력해주세요.");
					return;
				}
				String inputPw = tf_password.getText(); //입력한 pw값
				if(inputPw.equals("")) {
					sendTempMsg("PW를 입력해주세요.");
					return;
				}
				String inputAdminName = tf_adminName.getText(); //입력한 관리자명 값
				if(inputAdminName.equals("")) {
					sendTempMsg("관리자명을 입력해주세요.");
					return;
				}
				String inputCfPw = tf_confirmPassword.getText(); //입력한 pw확인값
				if(!inputPw.equals(inputCfPw)) { //만약 pw값과 pw확인값 불일치시
					sendTempMsg("비밀번호가 일치하지 않습니다.");
				} else {
					String baseId = CoronaSystem.database.getPassword(inputId); 
					if(baseId != null) { //이미 존재하는 id면
						sendTempMsg("이미 존재하는 ID입니다.");
					} else { //존재안하면
						CoronaSystem.database.insertAdminInfo(inputId, inputPw, inputAdminName);//계정 추가
						CheckGUI checkGUI = new CheckGUI(frame, "계정이 추가됐습니다.", false, true);
						//관리자 계정 추가 프레임까지 닫기
					}
				}
			}
		});
		getContentPane().add(btn_add);
		
		
		
		setVisible(true);
	}
	
	private void sendTempMsg(String tmpMsg) { //간단한 메시지 표시
		lbl_tempMessage.setText(tmpMsg);
	}
}
