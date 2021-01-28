package coronamanagesystem.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import addon.AreaData;
import addon.BubbleBorder;
import addon.MyColor;
import addon.MyUtility;
import addon.NaverMap;
import coronamanagesystem.CoronaSystem;
import lu.tudor.santec.jtimechooser.JTimeChooser;
import lu.tudor.santec.jtimechooser.demo.JTimeChooserDemo;

public class CoronaMapAdd extends JFrame {

	private int frameWidth = 500;
	private int frameHeight = 600;
	private JFrame frame;
	private JLabel lbl_tempMessage;
	private JDateChooser dateChooser;
	private JLabel lbl_info_name;
	private JLabel lbl_info_address;
	private JLabel lbl_info_date;
	private JLabel lbl_info_age;
	private JLabel lbl_info_id;
	private JLabel lbl_info_image;
	private JTextField tf_id;
	
	private String coronicId = "";
	private JLabel label_1;
	private JTextField tf_address;
	private JTextField tf_detail;
	private JButton btn_searchAdd;
	private JTimeChooser timeChooser_1;
	private JLabel lbl_longitude;
	private JLabel lbl_latitude;

	public CoronaMapAdd() {
		frame=this;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //»ç¿ëÀÚÀÇ È­¸é Å©±â°ªÀ» ¾ò±âÀ§ÇÑ ÅøÅ¶ Å¬·¡½º
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("È®ÁøÀÚ ÇÁ·ÎÇÊ Á¤º¸ ¼öÁ¤");
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE);
		
		//µÕ±Ù ¸ð¼­¸®
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//¸®¼Ò½º ¹Ì¸® ºÒ·¯¿À±â
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
		lblNewLabel_6.setFont(new Font("±¼¸²", Font.PLAIN, 6));
		lblNewLabel_6.setBounds(390, 15, 50, 50);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("\uB0A0\uC9DC(\uB144, \uC6D4, \uC77C)");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblNewLabel_5.setBounds(50, 285, 85, 20);
		lblNewLabel_5.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_menu = new JLabel("   \uB3D9\uC120 \uC815\uBCF4 \uCD94\uAC00");
		lblNewLabel_menu.setOpaque(true);
		lblNewLabel_menu.setForeground(new Color(0, 52, 88));
		lblNewLabel_menu.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_menu.setBorder(new BubbleBorder(Color.BLACK, 2, 16, 0));
		lblNewLabel_menu.setBackground(new Color(135, 206, 250));
		lblNewLabel_menu.setBounds(50, 10, 400, 60);
		getContentPane().add(lblNewLabel_menu);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(50, 80, 400, 90);
		infoPanel.setBackground(MyColor.WHITE);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		String n = CoronaSystem.database.getMaxId(); // ID ÇÒ´ç
		lbl_info_id = new JLabel("ID:");
		lbl_info_id.setBounds(90, 10, 30, 25);
		infoPanel.add(lbl_info_id);
		lbl_info_id.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		lbl_info_id.setForeground(MyColor.PLUSIANBLUE);
		
		lbl_info_name = new JLabel("");
		lbl_info_name.setForeground(new Color(0, 52, 88));
		lbl_info_name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		lbl_info_name.setBounds(90, 45, 80, 25);
		infoPanel.add(lbl_info_name);
		
		lbl_info_age = new JLabel("");
		lbl_info_age.setForeground(new Color(0, 52, 88));
		lbl_info_age.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		lbl_info_age.setBounds(180, 45, 80, 25);
		infoPanel.add(lbl_info_age);
		
		lbl_info_date = new JLabel("");
		lbl_info_date.setForeground(new Color(0, 52, 88));
		lbl_info_date.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		lbl_info_date.setBounds(270, 45, 110, 25);
		infoPanel.add(lbl_info_date);
		
		lbl_info_address = new JLabel("");
		lbl_info_address.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_info_address.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_info_address.setForeground(new Color(0, 52, 88));
		lbl_info_address.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		lbl_info_address.setBounds(170, 10, 220, 25);
		infoPanel.add(lbl_info_address);
		
		lbl_info_image = new JLabel("");
		lbl_info_image.setBounds(10, 10, 70, 70);
		infoPanel.add(lbl_info_image);
		
		ButtonGroup buttonGroup =  new ButtonGroup();
		
		tf_id = new JTextField();
		tf_id.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_id.setBounds(120, 10, 40, 25);
		tf_id.setBackground(MyColor.LIGHTGRAY);
		tf_id.setForeground(Color.black);
		tf_id.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tf_id.setBorder(new LineBorder(MyColor.SLATEGRAY, 2));	
		tf_id.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tf_id.getText();
				String[] coronic = CoronaSystem.database.getCoronicInfo(id);
				if(coronic == null) {
					sendTempMsg(id+"¹ø È®ÁøÀÚ¿¡ ´ëÇÑ Á¤º¸°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
				} else {
					coronicId = id;
					String name = coronic[0];
					String age = coronic[1];
					String gender = coronic[2];
					String address = coronic[3];
					String date = coronic[4];
					lbl_info_name.setText(name);
					lbl_info_age.setText(age+"¼¼");
					
					if(gender.equals("³²")) {
						lbl_info_image.setIcon(mIcon);
					} else {
						lbl_info_image.setIcon(wmIcon);
					}
					
					lbl_info_address.setText(address);
					
					
					lbl_info_date.setText(date);
					
				}
				
			}
		});
		infoPanel.add(tf_id);
		tf_id.setColumns(10);
		
		JButton btn_confirm = new JButton("\uCD94\uAC00");
		btn_confirm.setForeground(new Color(112, 128, 144));
		btn_confirm.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
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
				String time = timeChooser_1.getFormatedTime();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date tmpDate = dateChooser.getDate();
				String date = dateFormat.format(tmpDate);
				String address = tf_address.getText();
				//date 
				if(address.equals("")) {
					sendTempMsg("ÁÖ¼Ò¸¦ ¼±ÅÃÇØÁÖ¼¼¿ä.");
				} else if(coronicId.equals("")) {
					sendTempMsg("È®ÁøÀÚ¸¦ ¼±ÅÃÇØÁÖ¼¼¿ä.");
				}
				else {
					String latitude = lbl_latitude.getName();
					String longitude = lbl_longitude.getName();
					String detail = tf_detail.getText();
					CoronaSystem.database.insertCoronaMapInfo(date, time, latitude, longitude, detail, coronicId, address);
					new CheckGUI(CoronaMapAdd.this, "µ¿¼±ÀÌ Ãß°¡µÆ½À´Ï´Ù.", false, false);
				}
			}
		});
		getContentPane().add(btn_confirm);

		String[] cityList = AreaData.areaData.keySet().toArray(new String[AreaData.areaData.keySet().size()]);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setHorizontalAlignment(SwingConstants.RIGHT);
		dateChooser.setBounds(139, 285, 309, 25);
		getContentPane().add(dateChooser);
		
		lbl_tempMessage = new JLabel("");
		lbl_tempMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tempMessage.setForeground(Color.RED);
		lbl_tempMessage.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		lbl_tempMessage.setBounds(117, 470, 285, 30);
		getContentPane().add(lbl_tempMessage);
		
		JLabel label = new JLabel("\uC2DC\uAC04");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(new Color(0, 52, 88));
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label.setBounds(50, 356, 85, 20);
		getContentPane().add(label);
		
		timeChooser_1 = new JTimeChooser();
		timeChooser_1.getTimeField().setBackground(Color.DARK_GRAY);
		timeChooser_1.getTimeField().setForeground(Color.BLACK);
		timeChooser_1.getTimeField().setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 22));
		timeChooser_1.getTimeField().setHorizontalAlignment(SwingConstants.CENTER);
		timeChooser_1.setBounds(139, 343, 311, 42);
		JFormattedTextField timeField = timeChooser_1.getTimeField();
		timeField.setBackground(MyColor.BAIZI);
		getContentPane().add(timeChooser_1);
		
		label_1 = new JLabel("\uB3D9\uC120");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(new Color(0, 52, 88));
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_1.setBounds(50, 205, 30, 20);
		getContentPane().add(label_1);
		
		tf_address = new JTextField();
		tf_address.setBounds(120, 202, 250, 30);
		getContentPane().add(tf_address);
		tf_address.setColumns(10);
		tf_address.setBackground(MyColor.LIGHTGRAY);
		tf_address.setForeground(Color.black);
		tf_address.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tf_address.setBorder(new LineBorder(MyColor.WHITE, 2));	
		tf_address.setEditable(false);
		
		btn_searchAdd = new JButton("\uAC80\uC0C9");
		btn_searchAdd.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		btn_searchAdd.setBounds(385, 202, 60, 65);
		btn_searchAdd.setForeground(MyColor.WHITE);
		btn_searchAdd.setFocusPainted(false);
		btn_searchAdd.setBackground(MyColor.NAVY);
		btn_searchAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new NaverMap(tf_address, lbl_latitude, lbl_longitude );		
			}
		});
		getContentPane().add(btn_searchAdd);
		
		tf_detail = new JTextField();
		tf_detail.setColumns(10);
		tf_detail.setBounds(120, 237, 250, 30);
		tf_detail.setBackground(MyColor.LIGHTGRAY);
		tf_detail.setForeground(Color.black);
		tf_detail.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		tf_detail.setBorder(new LineBorder(MyColor.WHITE, 2));	
		getContentPane().add(tf_detail);
		
		JLabel label_2 = new JLabel("\uC0C1\uC138\uC815\uBCF4");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(new Color(0, 52, 88));
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_2.setBounds(50, 240, 60, 20);
		getContentPane().add(label_2);
		
		lbl_latitude = new JLabel("");
		lbl_latitude.setBounds(117, 429, 57, 15);
		getContentPane().add(lbl_latitude);
		
		lbl_longitude = new JLabel("");
		lbl_longitude.setBounds(117, 458, 57, 15);
		getContentPane().add(lbl_longitude);
		
		JTimeChooser timeChooser = new JTimeChooser(new Date());
		timeChooser.setBounds(178, 400, 272, 25);

		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}
	
	private void sendTempMsg(String tmpMsg) { //°£´ÜÇÑ ¸Þ½ÃÁö Ç¥½Ã
		lbl_tempMessage.setText(tmpMsg);
	}
}
