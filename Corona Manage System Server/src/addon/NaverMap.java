package addon;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NaverMap extends JFrame{
	
	public static NaverMap lastFrame;
	
	JLabel la;
    JTextField txtSearch;
    JPanel searchPanel;
    JButton btnSearch;
    JTable table;
    JScrollPane sc;
    DefaultTableModel dtm;
    
    Object [] columnTitle= {"순번","이름","전화번호","주소"};
    ArrayList<Object[]> tableList = new ArrayList<Object[]>();
    private JButton selectButton;
 
    public NaverMap(JTextField returnField, JLabel latitude, JLabel longitude)
    {
    	super( "네이버 지도 검색 후 데이터 추출" );
    	if(lastFrame != null) { //마지막 열린 프레임객체가 null 이 아니라면
			try {
				lastFrame.dispose(); //이전 프레임은 닫아버림, 프레임을 2개 여는 것을 방지
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        searchPanel = new JPanel();
        la = new JLabel("검색어");
        txtSearch = new JTextField(16);
        searchPanel.add(la);
        searchPanel.add(txtSearch);
        searchPanel.setBackground(MyColor.SLATEGRAY);
        btnSearch = new JButton("검색");
        searchPanel.add( btnSearch );
          
        getContentPane().setBackground(MyColor.ALICEBLUE);
        
        ActionListener listenerSearch = new ActionListener() {
			
        	@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					search();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
        
		btnSearch.addActionListener(listenerSearch);
        getContentPane().setLayout(new BorderLayout(0, 0));
		
		
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        
        
        Action ok = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					search();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		txtSearch.setText("안양대학교");
        
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        txtSearch.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "ENTER");
        txtSearch.getActionMap().put("ENTER", ok);
        
        Object[][] rowData=new Object[0][4];
		dtm=new DefaultTableModel(rowData, columnTitle);
		table = new JTable(dtm);
		table.setBackground(MyColor.LIGHTGRAY);
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		
		sc = new JScrollPane(table);
		
		getContentPane().add(sc, BorderLayout.CENTER);
		
		selectButton = new JButton("\uC120\uD0DD");
		selectButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		selectButton.setForeground(MyColor.WHITE);
		selectButton.setFocusPainted(false);
		selectButton.setBounds(238, 340, 97, 23);
		selectButton.setBackground(MyColor.NAVY);
		selectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow = table.getSelectedRow();
				if(tableList.size() > 0) {
					Object[] obj = tableList.get(selectRow);
					String address = obj[3].toString();
					returnField.setText(address);
					latitude.setName(obj[4].toString());
					longitude.setName(obj[5].toString());
					NaverMap.this.dispose();
				}
			}
		});
		
		getContentPane().add(selectButton, BorderLayout.SOUTH);
		
  		setSize( 700, 700 );
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void search() throws Exception{
    	final String USER_AGENT = "Mozilla/5.0";
		
		System.out.println("search() start");
		
		String txtSch=txtSearch.getText();
		System.out.println("txtSch="+txtSch);
		
		String encSch = URLEncoder.encode(txtSch, "UTF-8");
		System.out.println("encSch="+encSch);
		
		int seq=1;
		
		for(int i=1;i<4;i++) {
			
			System.out.println("i="+i);
			
			String targetUrl="https://map.naver.com/v5/api/search?caller=pcweb&query="+encSch+"&type=place&searchCoord=126.97392940521242;37.5757732700577&page="+i+"&displayCount=20&isPlaceRecommendationReplace=true&lang=ko";
			//String targetUrl="https://map.naver.com/v5/api/search?caller=pcweb&query=종각역 스타벅스&type=place&searchCoord=126.97392940521242;37.5757732700577&page="+i+"&displayCount=20&isPlaceRecommendationReplace=true&lang=ko";
			//String targetUrl="https://www.google.com/";
			
			URL url = new URL(targetUrl); 
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
			con.setRequestMethod("GET"); 
			// optional default is GET 
			con.setRequestProperty("User-Agent", USER_AGENT); 
			// add request header 
			int responseCode = con.getResponseCode(); 
			System.out.println("HTTP 응답 코드 : " + responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8")); 
			String inputLine; 
			StringBuffer response = new StringBuffer(); 
			while ((inputLine = in.readLine()) != null) { 
				response.append(inputLine); 
			} in.close(); // print result 
			System.out.println("HTTP body : " + response.toString());
			
			JSONParser jsonParser = new JSONParser();
            String jsonInfo=response.toString(); 

            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);
	        System.out.println(jsonObject);
	        
            JSONObject objResult = (JSONObject) jsonObject.get("result");
            JSONObject objPlace = (JSONObject) objResult.get("place");
            JSONArray objList = (JSONArray) objPlace.get("list");
 
            System.out.println(objList);
            
            dtm.setNumRows(0); //초기화
            tableList.clear();
            selectButton.setEnabled(false);
            
            for(int j=0; j<objList.size(); j++){
            	 
                System.out.println("=DATA_"+j+" ===========================================");
                 
                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                JSONObject bookObject = (JSONObject) objList.get(j);
                
                String name=(String) bookObject.get("name");
                String tel=(String) bookObject.get("tel");
                String address=(String) bookObject.get("address");
                String x = (String) bookObject.get("x");
                String y = (String) bookObject.get("y");
                
                //JSON name으로 추출
                System.out.println("Info: name==>"+name);
                System.out.println("Info: tel==>"+tel);
                System.out.println("Info: address==>"+address);
                
                Object[] newRow= {seq,name,tel,address, y, x};
        		dtm.addRow(newRow);
        		tableList.add(newRow);
        		seq++;
            }
            
            if(tableList.size() > 0) selectButton.setEnabled(true);
            
		}
		System.out.println("search() end");
		
    }

}
