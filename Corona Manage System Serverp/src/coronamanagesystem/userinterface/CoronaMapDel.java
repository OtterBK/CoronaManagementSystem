package coronamanagesystem.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import addon.AreaData;
import addon.BubbleBorder;
import addon.MyColor;
import addon.MyUtility;
import coronamanagesystem.CoronaSystem;
import lu.tudor.santec.jtimechooser.JTimeChooser;

public class CoronaMapDel extends JFrame {

	private int frameWidth = 500;
	private int frameHeight = 600;
	private JFrame frame;
	private JLabel lbl_info_name;
	private JLabel lbl_info_address;
	private JLabel lbl_info_date;
	private JLabel lbl_info_age;
	private JLabel lbl_info_id;
	private JLabel lbl_info_image;
	private JTextField tf_id;
	
	private String coronicId = "";
	private JLabel lbl_longitude;
	private JLabel lbl_latitude;
	private DefaultTableModel dtm;
	private JTable table;
	
	private ArrayList<String[]> resultData = new ArrayList<String[]>();

	Object [] columnTitle= {"날짜","시간","주소"};
	
	public CoronaMapDel() {
		frame=this;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //사용자의 화면 크기값을 얻기위한 툴킷 클래스
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("확진자 프로필 정보 수정");
		
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
		
		URL checkSrc = AdminInfoAddGUI.class.getResource("/resources/check.png");
		ImageIcon checkIcon = MyUtility.resizeImage(new ImageIcon(checkSrc), 50, 50);
		
		URL mSrc = InformationAdd.class.getResource("/resources/m.png");
		ImageIcon mIcon = MyUtility.resizeImage(new ImageIcon(mSrc), 70, 70);;
		
		URL wmSrc = InformationAdd.class.getResource("/resources/wm.png");
		ImageIcon wmIcon = MyUtility.resizeImage(new ImageIcon(wmSrc), 70, 70);;
		
		JLabel lblNewLabel_6 = new JLabel(checkIcon);
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 6));
		lblNewLabel_6.setBounds(390, 15, 50, 50);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_menu = new JLabel("   \uB3D9\uC120 \uC815\uBCF4 \uCD94\uAC00");
		lblNewLabel_menu.setOpaque(true);
		lblNewLabel_menu.setForeground(new Color(0, 52, 88));
		lblNewLabel_menu.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel_menu.setBorder(new BubbleBorder(Color.BLACK, 2, 16, 0));
		lblNewLabel_menu.setBackground(new Color(135, 206, 250));
		lblNewLabel_menu.setBounds(50, 10, 400, 60);
		getContentPane().add(lblNewLabel_menu);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(50, 80, 400, 90);
		infoPanel.setBackground(MyColor.WHITE);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		String n = CoronaSystem.database.getMaxId(); // ID 할당
		lbl_info_id = new JLabel("ID:");
		lbl_info_id.setBounds(90, 10, 30, 25);
		infoPanel.add(lbl_info_id);
		lbl_info_id.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lbl_info_id.setForeground(MyColor.PLUSIANBLUE);
		
		lbl_info_name = new JLabel("");
		lbl_info_name.setForeground(new Color(0, 52, 88));
		lbl_info_name.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lbl_info_name.setBounds(90, 45, 80, 25);
		infoPanel.add(lbl_info_name);
		
		lbl_info_age = new JLabel("");
		lbl_info_age.setForeground(new Color(0, 52, 88));
		lbl_info_age.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lbl_info_age.setBounds(180, 45, 80, 25);
		infoPanel.add(lbl_info_age);
		
		lbl_info_date = new JLabel("");
		lbl_info_date.setForeground(new Color(0, 52, 88));
		lbl_info_date.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lbl_info_date.setBounds(270, 45, 110, 25);
		infoPanel.add(lbl_info_date);
		
		lbl_info_address = new JLabel("");
		lbl_info_address.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_info_address.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_info_address.setForeground(new Color(0, 52, 88));
		lbl_info_address.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbl_info_address.setBounds(170, 10, 220, 25);
		infoPanel.add(lbl_info_address);
		
		lbl_info_image = new JLabel("");
		lbl_info_image.setBounds(10, 10, 70, 70);
		infoPanel.add(lbl_info_image);
		
		ButtonGroup buttonGroup =  new ButtonGroup();
		
		Object[][] rowData=new Object[0][3];
		dtm=new DefaultTableModel(rowData, columnTitle);
		table = new JTable(dtm);
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table.getColumnModel().getColumn(0).setPreferredWidth(3);
		table.getColumnModel().getColumn(1).setPreferredWidth(3);
		table.getColumnModel().getColumn(2).setPreferredWidth(15);
		
		tf_id = new JTextField();
		tf_id.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_id.setBounds(120, 10, 40, 25);
		tf_id.setBackground(MyColor.LIGHTGRAY);
		tf_id.setForeground(Color.black);
		tf_id.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_id.setBorder(new LineBorder(MyColor.SLATEGRAY, 2));	
		tf_id.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tf_id.getText();
				String[] coronic = CoronaSystem.database.getCoronicInfo(id);
				if(coronic == null) {
					new CheckGUI(CoronaMapDel.this,id+"번 확진자에 대한 정보가 존재하지 않습니다.", false, false);
				} else {
					coronicId = id;
					String name = coronic[0];
					String age = coronic[1];
					String gender = coronic[2];
					String address = coronic[3];
					String date = coronic[4];
					lbl_info_name.setText(name);
					lbl_info_age.setText(age+"세");
					
					if(gender.equals("남")) {
						lbl_info_image.setIcon(mIcon);
					} else {
						lbl_info_image.setIcon(wmIcon);
					}
					
					lbl_info_address.setText(address);
					
					
					lbl_info_date.setText(date);
					
					updateTable();
					
					
					
				}
				
			}
		});
		infoPanel.add(tf_id);
		tf_id.setColumns(10);
		
		JButton btn_confirm = new JButton("\uC0AD\uC81C");
		btn_confirm.setForeground(new Color(112, 128, 144));
		btn_confirm.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		btn_confirm.setFocusable(false);
		btn_confirm.setBorder(null);
		btn_confirm.setForeground(MyColor.WHITE);
		btn_confirm.setFocusPainted(false);
		btn_confirm.setBounds(403, 520, 97, 23);
		btn_confirm.setBackground(MyColor.NAVY);
		btn_confirm.setBounds(403, 500, 57, 35);
		btn_confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow = table.getSelectedRow();
				String[] data = resultData.get(selectRow);
				CoronaSystem.database.deleteCoronaInfo(data[0], data[1], data[5]);
				
				updateTable();
			}
		});
		getContentPane().add(btn_confirm);

		String[] cityList = AreaData.areaData.keySet().toArray(new String[AreaData.areaData.keySet().size()]);
		
		lbl_latitude = new JLabel("");
		lbl_latitude.setBounds(117, 429, 57, 15);
		getContentPane().add(lbl_latitude);
		
		lbl_longitude = new JLabel("");
		lbl_longitude.setBounds(117, 458, 57, 15);
		getContentPane().add(lbl_longitude);
		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setLocation(50, 180);
		scrollpane.setSize(400, 300);
		scrollpane.getViewport().setBackground(MyColor.LIGHTSKY);
		getContentPane().add(scrollpane);

		
		JTimeChooser timeChooser = new JTimeChooser(new Date());
		timeChooser.setBounds(178, 400, 272, 25);

		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}
	
	public void updateTable() {
		dtm.setNumRows(0);
		resultData.clear();
		
		ArrayList<String[]> coronaMapData = CoronaSystem.database.getAllCoronaMapInfo();

		if(coronaMapData != null) {
			for(String[] data : coronaMapData) {
				String compareId = data[5];
				if(compareId.equals(coronicId)) {
					resultData.add(data);
					Object[] obj = new Object[3];
					obj[0] = data[0];
					obj[1] = data[1];
					obj[2] = data[6];
					dtm.addRow(obj);
				}
			}
		}
	}

}
