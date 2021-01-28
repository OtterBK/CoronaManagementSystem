package coronamanagesystem.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;

import com.toedter.calendar.JDateChooser;

import addon.AreaData;
import addon.BubbleBorder;
import addon.DigitFilter;
import addon.MyColor;
import addon.MyUtility;
import coronamanagesystem.CoronaSystem;
import javax.swing.JPanel;

public class InformationAdd extends JFrame {

	private int frameWidth = 500;
	private int frameHeight = 600;
	private JTextField tf_name;
	private JTextField tf_age;
	private JFrame frame;
	private JComboBox cb_city;
	private JComboBox cb_town;
	private JComboBox cb_dis;
	private JLabel lbl_tempMessage;
	private JDateChooser dateChooser;
	private JLabel lbl_info_name;
	private JLabel lbl_info_address;
	private JLabel lbl_info_date;
	private JLabel lbl_info_age;
	private JLabel lbl_info_id;
	private JLabel lbl_info_image;
	private JTextField tf_detailAddress;

	public InformationAdd() {
		frame=this;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); //ªÁøÎ¿⁄¿« »≠∏È ≈©±‚∞™¿ª æÚ±‚¿ß«— ≈¯≈∂ ≈¨∑°Ω∫
		
		setSize(frameWidth,frameHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds((int) tk.getScreenSize().getWidth() / 2 - frameWidth /2, (int) tk.getScreenSize().getHeight() / 2 - frameHeight/2, frameWidth, frameHeight);
		
		setTitle("»Æ¡¯¿⁄ «¡∑Œ«  ¡§∫∏ √ﬂ∞°");
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(MyColor.ALICEBLUE);
		
		//µ’±Ÿ ∏º≠∏Æ
		AbstractBorder brdr = new BubbleBorder(Color.BLACK,2,16,0);
		
		//∏Æº“Ω∫ πÃ∏Æ ∫“∑Øø¿±‚
		URL titleIconSrc = InformationAdd.class.getResource("/resources/titleIcon.png");
		ImageIcon titleIcon = MyUtility.resizeImage(new ImageIcon(titleIconSrc), 70, 70);
		setIconImage(titleIcon.getImage());
		
		URL logoSrc = InformationAdd.class.getResource("/resources/logo.png");
		ImageIcon logoIcon = MyUtility.resizeImage(new ImageIcon(logoSrc), 70, 70);
		
		URL finderSrc = InformationAdd.class.getResource("/resources/finder.png");
		ImageIcon finderIcon = MyUtility.resizeImage(new ImageIcon(finderSrc), 20, 20);
		
		URL src = AdminInfoAddGUI.class.getResource("/resources/check.png");
		ImageIcon checkIcon = MyUtility.resizeImage(new ImageIcon(src), 50, 50);
		
		URL mSrc = InformationAdd.class.getResource("/resources/m.png");
		ImageIcon mIcon = MyUtility.resizeImage(new ImageIcon(mSrc), 70, 70);;
		
		URL wmSrc = InformationAdd.class.getResource("/resources/wm.png");
		ImageIcon wmIcon = MyUtility.resizeImage(new ImageIcon(wmSrc), 70, 70);;
		
		JLabel lblNewLabel_6 = new JLabel(checkIcon);
		lblNewLabel_6.setFont(new Font("±º∏≤", Font.PLAIN, 6));
		lblNewLabel_6.setBounds(390, 15, 50, 50);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		lblNewLabel_1.setBounds(50, 187, 60, 20);
		lblNewLabel_1.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uB098\uC774");
		lblNewLabel_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		lblNewLabel_2.setBounds(50, 227, 60, 20);
		lblNewLabel_2.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC131\uBCC4");
		lblNewLabel_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		lblNewLabel_3.setBounds(50, 269, 60, 20);
		lblNewLabel_3.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uAC70\uC8FC\uC9C0");
		lblNewLabel_4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		lblNewLabel_4.setBounds(50, 316, 60, 20);
		lblNewLabel_4.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uD655\uC9C4\uB0A0\uC9DC(\uB144, \uC6D4, \uC77C)");
		lblNewLabel_5.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		lblNewLabel_5.setBounds(50, 440, 120, 20);
		lblNewLabel_5.setForeground(MyColor.PLUSIANBLUE);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_menu = new JLabel("   \uD655\uC9C4\uC790 \uD504\uB85C\uD544 \uC815\uBCF4 \uCD94\uAC00");
		lblNewLabel_menu.setOpaque(true);
		lblNewLabel_menu.setForeground(new Color(0, 52, 88));
		lblNewLabel_menu.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 18));
		lblNewLabel_menu.setBorder(new BubbleBorder(Color.BLACK, 2, 16, 0));
		lblNewLabel_menu.setBackground(new Color(135, 206, 250));
		lblNewLabel_menu.setBounds(50, 10, 400, 60);
		getContentPane().add(lblNewLabel_menu);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(50, 80, 400, 90);
		infoPanel.setBackground(MyColor.WHITE);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		String n = CoronaSystem.database.getMaxId(); // ID «“¥Á
		lbl_info_id = new JLabel("ID: "+n);
		lbl_info_id.setBounds(90, 10, 60, 25);
		infoPanel.add(lbl_info_id);
		lbl_info_id.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18));
		lbl_info_id.setForeground(MyColor.PLUSIANBLUE);
		
		lbl_info_name = new JLabel("");
		lbl_info_name.setForeground(new Color(0, 52, 88));
		lbl_info_name.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18));
		lbl_info_name.setBounds(90, 45, 80, 25);
		infoPanel.add(lbl_info_name);
		
		lbl_info_age = new JLabel("");
		lbl_info_age.setForeground(new Color(0, 52, 88));
		lbl_info_age.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18));
		lbl_info_age.setBounds(180, 45, 80, 25);
		infoPanel.add(lbl_info_age);
		
		lbl_info_date = new JLabel("");
		lbl_info_date.setForeground(new Color(0, 52, 88));
		lbl_info_date.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18));
		lbl_info_date.setBounds(270, 45, 110, 25);
		infoPanel.add(lbl_info_date);
		
		lbl_info_address = new JLabel("");
		lbl_info_address.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_info_address.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_info_address.setForeground(new Color(0, 52, 88));
		lbl_info_address.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 14));
		lbl_info_address.setBounds(150, 10, 230, 25);
		infoPanel.add(lbl_info_address);
		
		lbl_info_image = new JLabel("");
		lbl_info_image.setBounds(10, 10, 70, 70);
		infoPanel.add(lbl_info_image);
		
		tf_detailAddress = new JTextField();
		tf_detailAddress.setText("");
		tf_detailAddress.setForeground(Color.BLACK);
		tf_detailAddress.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		tf_detailAddress.setColumns(10);
		tf_detailAddress.setBorder(new LineBorder(MyColor.WHITE, 2));
		tf_detailAddress.setBackground(new Color(211, 211, 211));
		tf_detailAddress.setBounds(160, 391, 300, 30);
		getContentPane().add(tf_detailAddress);
		
		tf_name = new JTextField();
		tf_name.setBounds(160, 182, 300, 30);
		tf_name.setBackground(MyColor.LIGHTGRAY);
		tf_name.setForeground(Color.black);
		tf_name.setBorder(new LineBorder(MyColor.WHITE, 2));	
		tf_name.setColumns(10);
		tf_name.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		getContentPane().add(tf_name);
		tf_name.setColumns(10);
		tf_name.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lbl_info_name.setText(tf_name.getText());
				
			}
		});
		
		tf_age = new JTextField();
		tf_age.setColumns(10);
		tf_age.setBounds(160, 222, 300, 30);
		tf_age.setBackground(MyColor.LIGHTGRAY);
		tf_age.setForeground(Color.black);
		tf_age.setBorder(new LineBorder(MyColor.WHITE, 2));	
		tf_age.setColumns(10);
		tf_age.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		((AbstractDocument) tf_age.getDocument()).setDocumentFilter(new DigitFilter());
		tf_age.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lbl_info_age.setText(tf_age.getText()+"ºº");
				
			}
		});
		getContentPane().add(tf_age);
		
		ButtonGroup buttonGroup =  new ButtonGroup();
		
		JRadioButton rb_gender_man = new JRadioButton("\uB0A8");
		rb_gender_man.setBounds(160, 270, 60, 19);
		rb_gender_man.setOpaque(false);
		rb_gender_man.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rb_gender_man.isSelected()) {
					lbl_info_image.setIcon(mIcon);
				} else {
					lbl_info_image.setIcon(wmIcon);
				}
				
			}
		});
		getContentPane().add(rb_gender_man);
		
		JRadioButton rb_gender_woman = new JRadioButton("\uC5EC");
		rb_gender_woman.setBounds(254, 270, 60, 19);
		rb_gender_woman.setOpaque(false);
		rb_gender_woman.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rb_gender_man.isSelected()) {
					lbl_info_image.setIcon(mIcon);
				} else {
					lbl_info_image.setIcon(wmIcon);
				}
				
			}
		});
		getContentPane().add(rb_gender_woman);
		
		buttonGroup.add(rb_gender_man);
		buttonGroup.add(rb_gender_woman);
		
		tf_name.setText("");
		tf_age.setText("");
		
		JButton btn_confirm = new JButton("\uCD94\uAC00");
		btn_confirm.setForeground(new Color(112, 128, 144));
		btn_confirm.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 18));
		btn_confirm.setFocusable(false);
		btn_confirm.setBorder(null);
		btn_confirm.setForeground(MyColor.WHITE);
		btn_confirm.setFocusPainted(false);
		btn_confirm.setBounds(238, 340, 97, 23);
		btn_confirm.setBackground(MyColor.NAVY);
		btn_confirm.setBounds(403, 500, 57, 35);
		btn_confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String name, age, gender, address = "", date;
				String id = lbl_info_id.getText().split(" ")[1];

				name=tf_name.getText();
				age= tf_age.getText();
				
				if(rb_gender_man.isSelected()) 
					gender="≥≤";
				else
					gender="ø©";

				address += cb_city.getSelectedItem().toString() + " ";
				address += cb_town.getSelectedItem().toString() + " ";
				address += cb_dis.getSelectedItem().toString();
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date tmpDate = dateChooser.getDate();
				date = dateFormat.format(tmpDate);
				//date 
				if(name.equals("")) {
					sendTempMsg("¿Ã∏ß¿ª ¿‘∑¬«ÿ¡÷ººø‰.");
				}
				else if(age.equals("")) {
					sendTempMsg("≥™¿Ã∏¶ ¿‘∑¬«ÿ¡÷ººø‰.");
				}
				else {
					CoronaSystem.database.insertCoronicInfo(id, name, age, gender, address, date);
					new CheckGUI(InformationAdd.this, "»Æ¡¯¿⁄∞° √ﬂ∞°µ∆Ω¿¥œ¥Ÿ.", false, true);
				}
			}
		});
		getContentPane().add(btn_confirm);

		String[] cityList = AreaData.areaData.keySet().toArray(new String[AreaData.areaData.keySet().size()]);
		cb_city = new JComboBox(cityList);
		cb_city.setBounds(160, 311, 140, 30);
		cb_city.setBackground(MyColor.DARKAQUA);
		cb_city.setForeground(MyColor.BLACK);
		cb_city.setBackground(MyColor.LIGHTGRAY);
		cb_city.setForeground(Color.black);
		cb_city.setBorder(new LineBorder(MyColor.WHITE, 2));	
		cb_city.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		cb_city.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cityName = cb_city.getSelectedItem().toString();
				Collection<String> tmpC = AreaData.areaData.get(cityName).keySet();
				String[] townList = tmpC.toArray(new String[tmpC.size()+1]);
				cb_town.removeAllItems(); //æ∆¿Ã≈€ ¿¸√º ªË¡¶
				for(String town : townList)
					cb_town.addItem(town);
			}
		});
		getContentPane().add(cb_city);
		
		cb_town = new JComboBox();
		cb_town.setBounds(320, 311, 140, 30);
		cb_town.setBackground(MyColor.LIGHTYELLOW);
		cb_town.setForeground(MyColor.BLACK);
		cb_town.setBackground(MyColor.LIGHTGRAY);
		cb_town.setForeground(Color.black);
		cb_town.setBorder(new LineBorder(MyColor.WHITE, 2));	
		cb_town.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		cb_town.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String cityName = cb_city.getSelectedItem().toString();
				Object townName = cb_town.getSelectedItem();
				if(townName == null)  return;
				townName = townName.toString();
				ArrayList<String> disList = AreaData.areaData.get(cityName).get(townName);
				cb_dis.removeAllItems(); //æ∆¿Ã≈€ ¿¸√º ªË¡¶
				if(disList == null) return; //null¿Ã∏È π´Ω√
				for(String dis : disList) 
					cb_dis.addItem(dis);
				
				String address = "";
				address += cb_city.getSelectedItem().toString() + " ";
				address += cb_town.getSelectedItem().toString() + " ";
				address += cb_dis.getSelectedItem().toString();
				
				lbl_info_address.setText(address);
			}
		});
		getContentPane().add(cb_town);
		
		cb_dis = new JComboBox();
		cb_dis.setBounds(160, 351, 300, 30);
		cb_dis.setBackground(MyColor.DARKAQUA);
		cb_dis.setForeground(MyColor.BLACK);
		cb_dis.setBackground(MyColor.LIGHTGRAY);
		cb_dis.setForeground(Color.black);
		cb_dis.setBorder(new LineBorder(MyColor.WHITE, 2));	
		cb_dis.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		cb_dis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String cityName = cb_city.getSelectedItem().toString();
				Object townName = cb_town.getSelectedItem();
				if(townName == null)  return;
				townName = townName.toString();
				ArrayList<String> disList = AreaData.areaData.get(cityName).get(townName);
				cb_dis.removeAllItems(); //æ∆¿Ã≈€ ¿¸√º ªË¡¶
				if(disList == null) return; //null¿Ã∏È π´Ω√
				for(String dis : disList) 
					cb_dis.addItem(dis);
				
				String address = "";
				address += cb_city.getSelectedItem().toString() + " ";
				address += cb_town.getSelectedItem().toString() + " ";
				address += cb_dis.getSelectedItem().toString();
				String detailAddress = tf_detailAddress.getText();
				if(!detailAddress.equals("")) {
					address += " " + detailAddress;
				}
				
				lbl_info_address.setText(address);
			}
		});
		getContentPane().add(cb_dis);
		
		
		cb_city.setSelectedIndex(0);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(178, 440, 145, 25);
		getContentPane().add(dateChooser);
		
		lbl_tempMessage = new JLabel("");
		lbl_tempMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tempMessage.setForeground(Color.RED);
		lbl_tempMessage.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		lbl_tempMessage.setBounds(116, 470, 285, 30);
		getContentPane().add(lbl_tempMessage);
		

		

		
		//JLabel lb_icon_finder = new JLabel(finderIcon);
		//lb_icon_finder.setBounds(358, 339, 20, 25);
		//getContentPane().add(lb_icon_finder);
		
		setVisible(true);	
	}
	
	private void sendTempMsg(String tmpMsg) { //∞£¥‹«— ∏ﬁΩ√¡ˆ «•Ω√
		lbl_tempMessage.setText(tmpMsg);
	}
}
