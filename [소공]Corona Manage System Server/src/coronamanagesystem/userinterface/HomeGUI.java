//Front: 전재욱
//Back: 최지혜
//Last Update : 20.11.23
//Des : 서버 메인화면


package coronamanagesystem.userinterface;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;

import addon.BubbleBorder;
import addon.MyColor;
import addon.MyUtility;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.*;

public class HomeGUI extends JFrame{

	private int frameWidth = 500;
	private int frameHeight = 650;
	private JLabel btn_adminInfoAdd2;
	private JLabel btn_adminInfoDel2;
	private JLabel lbl_coronicInfo_add;
	private JLabel lbl_coronicInfo_edit;
	private JLabel lbl_coronaMap_add;
	private JLabel lbl_coronaMap_del;
	private JLabel lbl_coronaMap_find;
	
	public HomeGUI() {
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new JFrameWindowClosingEventHandler()); //창 닫기 이벤트
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("코로나 관리 시스템 - 메뉴");
		getContentPane().setBackground(MyColor.ALICEBLUE);
		getContentPane().setLayout(null);

		// 둥근 모서리
		AbstractBorder brdr = new BubbleBorder(Color.BLACK, 2, 16, 0);

		// 리소스 미리 불러오기
		URL titleIconSrc = HomeGUI.class.getResource("/resources/titleIcon.png");
		ImageIcon titleIcon = MyUtility.resizeImage(new ImageIcon(titleIconSrc), 70, 70);
		setIconImage(titleIcon.getImage());

		URL homeSrc = HomeGUI.class.getResource("/resources/home.png");
		ImageIcon homeIcon = MyUtility.resizeImage(new ImageIcon(homeSrc), 50, 50);

		URL settingSrc = HomeGUI.class.getResource("/resources/settingIcon.png");
		ImageIcon settingIcon = MyUtility.resizeImage(new ImageIcon(settingSrc), 30, 30);
		
		URL src = HomeGUI.class.getResource("/resources/check.png");
		ImageIcon checkIcon = MyUtility.resizeImage(new ImageIcon(src), 20, 20);
		
		src = HomeGUI.class.getResource("/resources/delete.png");
		ImageIcon deleteIcon = MyUtility.resizeImage(new ImageIcon(src), 20, 20);
		
		src = HomeGUI.class.getResource("/resources/profile.png");
		ImageIcon profileIcon = MyUtility.resizeImage(new ImageIcon(src), 30, 30);
		
		src = HomeGUI.class.getResource("/resources/edit.png");
		ImageIcon editIcon = MyUtility.resizeImage(new ImageIcon(src), 20, 20);
		
		src = HomeGUI.class.getResource("/resources/mapinfo.png");
		ImageIcon mapInfoIcon = MyUtility.resizeImage(new ImageIcon(src), 30, 30);
		
		src = HomeGUI.class.getResource("/resources/inquiry.png");
		ImageIcon inquiryIcon = MyUtility.resizeImage(new ImageIcon(src), 20, 20);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(100, 30, 300, 70);
		getContentPane().add(topPanel);
		topPanel.setLayout(null);
		topPanel.setBackground(MyColor.LIGHTSKY);
		topPanel.setBorder(brdr);
		
		JLabel lb_title = new JLabel("Database 관리");
		lb_title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		lb_title.setHorizontalAlignment(SwingConstants.LEFT);
		lb_title.setBounds(12, 10, 170, 50);
		lb_title.setForeground(MyColor.PLUSIANBLUE);
		topPanel.add(lb_title);
		
		JLabel lb_homeIcon = new JLabel(homeIcon);
		lb_homeIcon.setBounds(235, 12, 50, 50);
		topPanel.add(lb_homeIcon);
		
		JPanel adminInfoPanel = new JPanel();
		adminInfoPanel.setBounds(50, 150, 400, 100);
		getContentPane().add(adminInfoPanel);
		adminInfoPanel.setLayout(null);
		adminInfoPanel.setBorder(brdr);
		adminInfoPanel.setBackground(MyColor.STEELBLUE);
		
//		JLabel lb_icon_adminInfo = new JLabel(settingIcon);
//		lb_icon_adminInfo.setBounds(10, 7, 40, 40);
//		adminInfoPanel.add(lb_icon_adminInfo);
//		
		
		JLabel lb_adminInfoTitle = new JLabel("관리자 정보");
		lb_adminInfoTitle.setIcon(settingIcon);
		lb_adminInfoTitle.setForeground(MyColor.LIGHTYELLOW);
		lb_adminInfoTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lb_adminInfoTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lb_adminInfoTitle.setBounds(22, 10, 200, 40);
		adminInfoPanel.add(lb_adminInfoTitle);
		
//		JLabel btn_adminInfoAdd1 = new JLabel(checkIcon);
//		btn_adminInfoAdd1.setHorizontalAlignment(SwingConstants.LEFT);
//		btn_adminInfoAdd1.setBounds(70, 65, 20, 20);
//		btn_adminInfoAdd1.setOpaque(false);
//		adminInfoPanel.add(btn_adminInfoAdd1);
		
		
		btn_adminInfoAdd2 = new JLabel("계정 추가");
		btn_adminInfoAdd2.setIcon(checkIcon);
		btn_adminInfoAdd2.setForeground(MyColor.WHITE);
		btn_adminInfoAdd2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btn_adminInfoAdd2.setHorizontalAlignment(SwingConstants.LEFT);
		btn_adminInfoAdd2.setBounds(75, 62, 100, 25);
		//btn_adminInfoAdd2.setOpaque(false);
		//btn_adminInfoAdd2.setBorder(new LineBorder(MyColor.SMOOTHBLACK, 2));
		adminInfoPanel.add(btn_adminInfoAdd2);
		btn_adminInfoAdd2.addMouseListener(new LabelButton());
		//클릭하면 계정 추가 프레임 열리게
		
//		JLabel btn_adminInfoDel1 = new JLabel(deleteIcon);
//		btn_adminInfoDel1.setHorizontalAlignment(SwingConstants.LEFT);
//		btn_adminInfoDel1.setBounds(240, 65, 20, 20);
//		btn_adminInfoDel1.setOpaque(false);
//
//		adminInfoPanel.add(btn_adminInfoDel1);
		
		
		btn_adminInfoDel2 = new JLabel("계정 삭제");
		btn_adminInfoDel2.setForeground(MyColor.WHITE);
		btn_adminInfoDel2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btn_adminInfoDel2.setIcon(deleteIcon);
		btn_adminInfoDel2.setHorizontalAlignment(SwingConstants.LEFT);
		btn_adminInfoDel2.setBounds(245, 62, 100, 25);
		//btn_adminInfoDel2.setOpaque(false);
		//btn_adminInfoDel2.setBorder(new LineBorder(MyColor.SMOOTHBLACK, 2));
		adminInfoPanel.add(btn_adminInfoDel2);
		btn_adminInfoDel2.addMouseListener(new LabelButton());
		//클릭하면 계정 삭제 프레임 열리게
		
		JPanel coronicInfoPanel = new JPanel();
		coronicInfoPanel.setLayout(null);
		coronicInfoPanel.setBackground(new Color(70, 130, 180));
		coronicInfoPanel.setBounds(50, 300, 400, 100);
		coronicInfoPanel.setBorder(brdr);
		getContentPane().add(coronicInfoPanel);
		
		JLabel lbl_coronicInfoTitle = new JLabel("확진자 프로필 정보");
		lbl_coronicInfoTitle.setIcon(profileIcon);
		lbl_coronicInfoTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_coronicInfoTitle.setForeground(new Color(239, 249, 55));
		lbl_coronicInfoTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lbl_coronicInfoTitle.setBounds(22, 10, 200, 40);
		coronicInfoPanel.add(lbl_coronicInfoTitle);
		
		lbl_coronicInfo_add = new JLabel("정보 추가");
		lbl_coronicInfo_add.setIcon(checkIcon);
		lbl_coronicInfo_add.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_coronicInfo_add.setForeground(Color.WHITE);
		lbl_coronicInfo_add.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbl_coronicInfo_add.setBounds(75, 62, 100, 25);
		coronicInfoPanel.add(lbl_coronicInfo_add);
		lbl_coronicInfo_add.addMouseListener(new LabelButton());
		//클릭하면 정보 추가 프레임 열리게
		
		lbl_coronicInfo_edit = new JLabel("정보 수정");
		lbl_coronicInfo_edit.setIcon(editIcon);
		lbl_coronicInfo_edit.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_coronicInfo_edit.setForeground(Color.WHITE);
		lbl_coronicInfo_edit.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbl_coronicInfo_edit.setBounds(245, 62, 100, 25);
		coronicInfoPanel.add(lbl_coronicInfo_edit);
		lbl_coronicInfo_edit.addMouseListener(new LabelButton());
		//클릭하면 정보 수정 프레임 열리게
		
		JPanel coronaMapPanel = new JPanel();
		coronaMapPanel.setLayout(null);
		coronaMapPanel.setBackground(new Color(70, 130, 180));
		coronaMapPanel.setBounds(50, 450, 400, 100);
		coronaMapPanel.setBorder(brdr);
		getContentPane().add(coronaMapPanel);
		
		JLabel lbl_coronaMapTitle = new JLabel("확진자 동선 정보");
		lbl_coronaMapTitle.setIcon(mapInfoIcon);
		lbl_coronaMapTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_coronaMapTitle.setForeground(new Color(239, 249, 55));
		lbl_coronaMapTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lbl_coronaMapTitle.setBounds(22, 10, 200, 40);
		coronaMapPanel.add(lbl_coronaMapTitle);

		
		lbl_coronaMap_add = new JLabel("정보 추가");
		lbl_coronaMap_add.setIcon(checkIcon);
		lbl_coronaMap_add.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_coronaMap_add.setForeground(Color.WHITE);
		lbl_coronaMap_add.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbl_coronaMap_add.setBounds(150, 62, 100, 25);
		coronaMapPanel.add(lbl_coronaMap_add);
		lbl_coronaMap_add.addMouseListener(new LabelButton());
		//클릭하면 동선 정보 추가 프레임 열리게
		
		lbl_coronaMap_del = new JLabel("정보 삭제");
		lbl_coronaMap_del.setIcon(deleteIcon);
		lbl_coronaMap_del.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_coronaMap_del.setForeground(Color.WHITE);
		lbl_coronaMap_del.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbl_coronaMap_del.setBounds(290, 62, 100, 25);
		coronaMapPanel.add(lbl_coronaMap_del);
		
		lbl_coronaMap_find = new JLabel("정보 조회");
		lbl_coronaMap_find.setIcon(inquiryIcon);
		lbl_coronaMap_find.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_coronaMap_find.setForeground(Color.WHITE);
		lbl_coronaMap_find.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbl_coronaMap_find.setBounds(10, 62, 100, 25);
		coronaMapPanel.add(lbl_coronaMap_find);
		lbl_coronaMap_del.addMouseListener(new LabelButton());
		// 클릭하면 동선 정보 삭제 프레임 열리게
	
		this.setVisible(true);
		
	}
	
	private class LabelButton extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==btn_adminInfoAdd2) {
				new AdminInfoAddGUI();
			}
			else if(e.getSource()==btn_adminInfoDel2) {
				new AdminInfoDeleteGUI();
			}
			else if(e.getSource()==lbl_coronicInfo_add) {
				new InformationAdd();
			}
			else if(e.getSource()==lbl_coronicInfo_edit) {
				new InformationChange();//프로필 정보수정
			}
			else if(e.getSource()== lbl_coronaMap_add) {
				new CoronaMapAdd();
			}
			else if(e.getSource()== lbl_coronaMap_del) {
				new CoronaMapDel();//동선 정보 삭제
			}	
		}
	}
	private class JFrameWindowClosingEventHandler extends WindowAdapter { //창 닫기시
		public void windowClosing(WindowEvent e) {
			if(e.getWindow() instanceof LoginGUI) { //홈 화면 닫으면
				System.exit(0); //프로그램 종료
			}	
		}
	}
}
