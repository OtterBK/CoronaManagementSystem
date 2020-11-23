//Front: 김태연
//Back: 
//Last Update : 20.11.22
//Des : 코로나 동선 추가 프레임

package coronamanagesystem.userinterface;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;

import addon.BubbleBorder;
import addon.MyColor;
import addon.MyUtility;
import coronamanagesystem.CoronaSystem;

public class InformationChange extends JFrame{

	private static final ActionListener ActionListener = null;
	private int frameWidth = 500;
	private int frameHeight = 400;
	private JButton btn_confirm;	
	private JTextField tf_id0;
	private JTextField tf_name;
	private JTextField tf_age0;//이름 입력 텍스트 필드
	private JTextField tf_gender;
	private JTextField tf_address;//성별
	private JTextField tf_year;//거주지
	private JLabel lblNewLabel_6;
	//년
	private JTextField tf_month;//월
	private JTextField tf_date;//일
	private JLabel lbl_tempMessage;
	private JFrame frame;
	
	public InformationChange() {
		frame = this;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new JFrameWindowClosingEventHandler()); //창 닫기 이벤트
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("확진자 프로필 정보 수정");
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE);
		
		//둥근 모서리
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//리소스 미리 불러오기
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
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 6));
		lblNewLabel_6.setBounds(390, 15, 50, 50);
		getContentPane().add(lblNewLabel_6);
		

		
		JLabel lblNewLabel = new JLabel("   \uD655\uC9C4\uC790 \uD504\uB85C\uD544 \uC815\uBCF4 \uC218\uC815");
		lblNewLabel.setBackground(MyColor.LIGHTSKY);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel.setBounds(50, 10, 400, 60);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new BubbleBorder(Color.BLACK, 2, 16, 0));
		lblNewLabel.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("\uC218\uC815\uD560 ID");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel_1.setBounds(50, 80, 120, 35);
		lblNewLabel_1.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lbl_age = new JLabel("\uB098\uC774");
		lbl_age.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbl_age.setBounds(50, 165, 50, 15);
		lbl_age.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lbl_age);
		
		JLabel lblNewLabel_3 = new JLabel("\uC131\uBCC4");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_3.setBounds(50, 200, 50, 15);
		lblNewLabel_3.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uAC70\uC8FC\uC9C0");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_4.setBounds(50, 235, 50, 15);
		lblNewLabel_4.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uD655\uC9C4\uB0A0\uC9DC(\uB144, \uC6D4, \uC77C)");
		lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_5.setBounds(50, 270, 120, 15);
		lblNewLabel_5.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_5);
		
		tf_id0 = new JTextField();
		tf_id0.setForeground(Color.BLACK);
		tf_id0.setColumns(10);
		tf_id0.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_id0.setBounds(180, 80, 120, 35);
		tf_id0.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
            }
            
            @Override
	       	 public void keyTyped(KeyEvent e) {
	      		  char c = e.getKeyChar();
	      		  
	      		  if (!Character.isDigit(c)) {
	      			 new CheckGUI(frame, "ID를 숫자로 입력하여 주세요.", false, false); //실패 메시지와 이유 전달
	      			//tf_id0.setText("");
	      			 e.consume();
	      			 return;
	      		  }
	      	}
	     });
		getContentPane().add(tf_id0);
		
		tf_name = new JTextField();
		tf_name.setForeground(Color.BLACK);
		tf_name.setColumns(10);
		tf_name.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_name.setBounds(184, 130, 270, 20);
		getContentPane().add(tf_name);
		
		tf_age0 = new JTextField();
		tf_age0.setBounds(184, 165, 270, 20);
		getContentPane().add(tf_age0);
		tf_age0.setColumns(10);
		tf_age0.setForeground(Color.black);
		tf_age0.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_age0.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
            }
            
            @Override
	       	 public void keyTyped(KeyEvent e) {
	      		  char c = e.getKeyChar();
	      		  
	      		  if (!Character.isDigit(c)) {
	      			 new CheckGUI(frame, "나이를 숫자로 입력하여 주세요.", false, false); //실패 메시지와 이유 전달
	      			 tf_age0.setText("");
	      			 e.consume();
	      			 return;
	      		  }
	      	}
	     });
		getContentPane().add(tf_age0);
		
		tf_gender = new JTextField();
		tf_gender.setBounds(184, 200, 270, 20);
		getContentPane().add(tf_gender);
		tf_gender.setColumns(10);
		tf_gender.setForeground(Color.black);
		tf_gender.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(tf_gender);
		
		tf_address = new JTextField();
		tf_address.setBounds(184, 235, 270, 20);
		getContentPane().add(tf_address);
		tf_address.setColumns(10);
		tf_address.setForeground(Color.black);
		tf_address.setBorder(new LineBorder(MyColor.WHITE, 2));	
		
		getContentPane().add(tf_address);
		
		tf_year = new JTextField();
		tf_year.setBounds(184, 270, 90, 20);
		getContentPane().add(tf_year);
		tf_year.setColumns(10);
		tf_year.setForeground(Color.black);
		tf_year.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(tf_year);
		
		JLabel lbl_name = new JLabel("\uC774\uB984");
		lbl_name.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbl_name.setBounds(50, 130, 57, 15);
		lbl_name.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lbl_name);
		

		
		tf_month = new JTextField();
		tf_month.setForeground(Color.BLACK);
		tf_month.setColumns(10);
		tf_month.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_month.setBounds(285, 270, 80, 20);
		getContentPane().add(tf_month);
		
		tf_date = new JTextField();
		tf_date.setForeground(Color.BLACK);
		tf_date.setColumns(10);
		tf_date.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_date.setBounds(374, 270, 80, 20);
		getContentPane().add(tf_date);
		
		tf_gender = new JTextField();
		tf_gender.setForeground(Color.BLACK);
		tf_gender.setColumns(10);
		tf_gender.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_gender.setBounds(184, 200, 270, 20);
		getContentPane().add(tf_gender);
		

		
		btn_confirm = new JButton("\uD655\uC778");
		btn_confirm.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		btn_confirm.setBounds(413, 326, 57, 35);
		btn_confirm.setOpaque(false);
		btn_confirm.setBorder(null);
		btn_confirm.setFocusable(false);
		btn_confirm.setForeground(MyColor.SLATEGRAY);
		btn_confirm.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputId=tf_id0.getText();//입력한 ID값
				
				if(inputId.equals("")) {
					new CheckGUI(frame, "수정할 확진자의 ID를 입력하여 주세요.", false, false); //실패 메시지와 이유 전달
				}
				else if(CoronaSystem.database.getName_Info(inputId)==null) {
					new CheckGUI(frame, "조회할 ID가 존재하지 않습니다.", false, false); //실패 메시지와 이유 전달
				}
				else {
					tf_name.setText(CoronaSystem.database.getName_Info(inputId));
					tf_age0.setText(CoronaSystem.database.getAge_Info(inputId));
					tf_gender.setText(CoronaSystem.database.getGender_Info(inputId));
					tf_address.setText(CoronaSystem.database.getAddress_Info(inputId));
					return;
				}
				tf_id0.setText("");
				tf_name.setText("");
				tf_age0.setText("");
				tf_gender.setText("");
				tf_address.setText("");
			}
		});
		
		getContentPane().add(btn_confirm);//확인
		

		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}
	private void sendTempMsg(String tmpMsg) { //간단한 메시지 표시
		lbl_tempMessage.setText(tmpMsg);
	}
	
	
	
	private class JFrameWindowClosingEventHandler extends WindowAdapter { //창 닫기시
		public void windowClosing(WindowEvent e) {
			if(e.getWindow() instanceof InformationChange) { //홈 화면 닫으면
				System.exit(0); //프로그램 종료
			}	
		}
	}
}
