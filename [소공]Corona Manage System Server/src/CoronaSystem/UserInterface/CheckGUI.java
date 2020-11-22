//Front: 전재욱
//Back: 전재욱
//Last Update : 20.11.23
//Des : 간단한 알림 표시용, 프로그램 종료, 해당 알림을 띄운 창 종료 등 기능 제공

package CoronaSystem.UserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Addon.MyColor;
import Addon.MyUtility;

public class CheckGUI extends JFrame{ //사용자에게 알림을 띄우기 위한 프레임
	
	static CheckGUI lastFrame = null; //CheckGUI는 2개가 열릴수 없음 그걸 위함
	boolean programExit = false; //이 프레임을 닫으면 프로그램까지 종료할 것인가?
	boolean disposeCaller = false; //이 프레밍를 닫으면 이 프레임을 호출한 프레임 또한 닫을 것인가?
	JFrame callerFrame; //호출한 프레임
	JButton okButton; //확인버튼
	
	//호출한 프레임, 알림 메시지, 프로그램 종료 여부, 호출 프레임 종료 여부
	public CheckGUI(JFrame callerFrame, String msg, boolean programExit,boolean disposeCaller) { 
		if(lastFrame != null) { //프레임 2개 띄우기 방지
			lastFrame.dispose();
		}
		lastFrame = this;
		this.callerFrame = callerFrame; //초기화
		this.programExit = programExit;
		this.disposeCaller = disposeCaller;
		
		msg = MyUtility.lineSpacing(msg, 16); //알림 메시지 16글자 기준으로 줄 나눔
		createFrame(msg, 14); //프레임 생성
	}
	
	private void createFrame(String msg, float fontSize) { //알림 메시지, 폰트 사이즈
		Toolkit tk = Toolkit.getDefaultToolkit(); //프레임 가운데에 띄우기 위함
		Dimension screenSize = tk.getScreenSize();
		
		MyEvent myActionEvent = new MyEvent(this); //확인버튼 클릭 이벤트용
		
		this.addWindowListener(new JFrameWindowClosingEventHandler()); //프레임 창 닫기 시
		
		this.setResizable(false);
		this.setBounds(screenSize.width / 2 - 125, screenSize.height / 2 - 75, 250, 150);
		this.setTitle("확인");
		URL src = CheckGUI.class.getResource("/resources/titleIcon.png"); //icon 이미지
		ImageIcon icon = new ImageIcon(src);
		this.setIconImage(icon.getImage());
		
		JPanel pane = new JPanel(); //메인 패널
		pane.setBackground(MyColor.ALICEBLUE);
		pane.setLayout(new BorderLayout());	
		
		this.setContentPane(pane);
		
		JLabel msgLabel = new JLabel(msg); //알림 메시지 라벨
		msgLabel.setFont(new Font("맑은 고딕", Font.PLAIN, (int)fontSize));
		msgLabel.setForeground(MyColor.PLUSIANBLUE);
		msgLabel.setHorizontalAlignment(JLabel.CENTER);
		
		okButton = new JButton("확인"); //확인 버튼
		okButton.addActionListener(myActionEvent);
		okButton.setBackground(MyColor.MIDNIGHTBLUE);
		okButton.setForeground(MyColor.WHITE);
		
		pane.add(msgLabel, BorderLayout.CENTER);
		pane.add(okButton, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private class MyEvent implements ActionListener{ //버튼 클릭이벤트

		private JFrame frame;
		
		public MyEvent(JFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();
			if(src.equals(okButton)) { //확인 버튼 클릭시
				if(!programExit) { //프로그램 종료가 아니라면
					frame.dispose(); //프레임 닫기
					if(disposeCaller) { //호출 프레임 종료라면
						if(callerFrame != null) { 
							callerFrame.dispose(); //호출 프레임 닫기
						}			
					}
				} else { //프로그램 종료 여부   true시
					System.exit(0);  //프로그램 종료
				}
			}
		}	
	}
	
	private class JFrameWindowClosingEventHandler extends WindowAdapter { //프레임 닫을시
		public void windowClosing(WindowEvent e) {
			if(e.getWindow() instanceof CheckGUI) {
				CheckGUI eFrame = (CheckGUI) e.getWindow();
				if(eFrame.equals(lastFrame)) 
					lastFrame.dispose(); //프레임 종료
					if(programExit) { //프로그램 종료 여부 true시
						System.exit(0); //프로그램 종료
					} else if(disposeCaller) { //호출 프레임 종료라면
						callerFrame.dispose(); //호출 프레임 닫기
					}	
			}	
		}
	}

	
}
