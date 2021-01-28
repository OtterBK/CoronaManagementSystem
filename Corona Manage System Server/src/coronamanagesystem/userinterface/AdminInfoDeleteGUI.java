

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

import javax.swing.JButton;

public class AdminInfoDeleteGUI extends JFrame{

	private int frameWidth = 400; //가로
	private int frameHeight = 450; //세로
	private JTextField tf_id; //id 입력 필드
	private JPasswordField tf_password; //pw 입력 필드
	private JLabel lbl_tempMessage;
	
	public AdminInfoDeleteGUI() {
		setResizable(false); //창 사이즈 조절 불가능
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		 
		setSize(frameWidth,frameHeight); //프레임 사이즈 설정
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //창 닫으면 닫힘
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("관리자 삭제"); //타이틀 설정
		getContentPane().setBackground(MyColor.ALICEBLUE); //배경색
		getContentPane().setLayout(null);

		// 둥근 모서리
		AbstractBorder brdr = new BubbleBorder(Color.BLACK, 2, 16, 0);

		// 리소스 미리 불러오기
		URL src = AdminInfoDeleteGUI.class.getResource("/resources/check.png");
		ImageIcon checkIcon = MyUtility.resizeImage(new ImageIcon(src), 70, 70);
		
		src = AdminInfoDeleteGUI.class.getResource("/resources/delete.png");
		ImageIcon deleteIcon = MyUtility.resizeImage(new ImageIcon(src), 50, 50);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(50, 10, 300, 70);
		getContentPane().add(topPanel);
		topPanel.setLayout(null);
		topPanel.setBackground(MyColor.LIGHTSKY);
		topPanel.setBorder(brdr);
		
		JLabel lb_title = new JLabel("계정 삭제");
		lb_title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		lb_title.setHorizontalAlignment(SwingConstants.LEFT);
		lb_title.setBounds(12, 10, 170, 50);
		lb_title.setForeground(MyColor.PLUSIANBLUE);
		topPanel.add(lb_title);
		
		JLabel lb_homeIcon = new JLabel(deleteIcon);
		lb_homeIcon.setBounds(235, 12, 50, 50);
		topPanel.add(lb_homeIcon);
		
		tf_id = new JTextField();
		tf_id.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_id.setColumns(10);
		tf_id.setBounds(125, 160, 210, 25);
		tf_id.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_id.setBackground(MyColor.LIGHTGRAY);
		tf_id.setForeground(Color.black);
		tf_id.setBorder(new LineBorder(MyColor.WHITE, 2));
		((AbstractDocument) tf_id.getDocument()).setDocumentFilter(new IDFilter()); //알파벳, 숫자만 입력
		tf_id.addKeyListener(new KeyAdapter() {
			
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
		
		tf_password = new JPasswordField(); //패스워드 필드 사용
		tf_password.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_password.setColumns(10);
		tf_password.setBounds(125, 205, 210, 25);
		tf_password.setBackground(MyColor.LIGHTGRAY);
		tf_password.setForeground(Color.black);
		tf_password.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_password.setEchoChar('*'); //해당 칸에는 입력시 * 로 표시함
		tf_password.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(tf_password);
		
		JLabel lbl_tfDes3 = new JLabel("Password");
		lbl_tfDes3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_tfDes3.setBounds(50, 205, 55, 25);
		lbl_tfDes3.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lbl_tfDes3);
		
		lbl_tempMessage = new JLabel(""); //나중에 행동에 대한 결과값을 간단하게 표시해줄 라벨
		lbl_tempMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tempMessage.setBounds(50, 300, 285, 30);
		lbl_tempMessage.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lbl_tempMessage.setForeground(Color.RED);
		getContentPane().add(lbl_tempMessage);
		
		JButton btn_add = new JButton("삭제");
		btn_add.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btn_add.setForeground(MyColor.WHITE);
		btn_add.setFocusPainted(false);
		btn_add.setBounds(238, 340, 97, 23);
		btn_add.setBackground(MyColor.NAVY);
		btn_add.addActionListener(new ActionListener() { //삭제 버튼 클릭시
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputId = tf_id.getText();
				String inputPw = tf_password.getText();
				String pw = CoronaSystem.database.getPassword(inputId);
				if(pw == null) {
					sendTempMsg("존재하지 않는 ID 입니다.");
				} else {
					if(pw.equals(inputPw)) {
						CoronaSystem.database.deleteAdminInfo(inputId);
						sendTempMsg(inputId+" 계정이 삭제됐습니다.");	
					} else {
						sendTempMsg("잘못된 패스워드입니다.");
					}
				}
				
			}
		});
		getContentPane().add(btn_add);
		
		
		
		setVisible(true);
	}
	
	private void sendTempMsg(String tmpMsg) {
		lbl_tempMessage.setText(tmpMsg);
	}
}
