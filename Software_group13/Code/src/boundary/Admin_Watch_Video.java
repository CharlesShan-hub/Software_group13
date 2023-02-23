package boundary;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.border.EmptyBorder;

import controller.Admin_Mine;
import entity.CSVUtil;
import entity.ClassGetter;
import entity.OpenVideoTool;

/**
 * Class {@code Admin_Watch_Video} is to play and filter 
 * the video to watch, and generate a scroll stream. 
 * When the user watches the video, the system will 
 * save the playback record.
 * 
 * @author   Shiwei Ma
 * @since    2020/5/30
 * @version  [1.0]
 */
public class Admin_Watch_Video {

	/* Announce Components */
	private JFrame frame;
 	/**pt is an object of JButton.*/
	private JButton pt;
	/**mt is an object of JLabel.*/
    private JLabel mt;
	/**contentPane is an object of JPanel.*/
 	private JPanel contentPane;
	/**textField is an object of JTextField.*/
	private JTextField textField;
	/**scrollPane is an object of JScrollPane.*/	
	JScrollPane scrollPane;
	/**panel is an object of JPanel.*/
	JPanel panel;
	/**comboBox is an object of JComboBox.*/
	JComboBox comboBox;
	/**btnNewButton_2 is an object of JButton.*/
	JButton btnNewButton_2;
	/**videoscrollPane is an object of JScrollPane.*/
	JScrollPane videoscrollPane;
	/**childpanel is an object of JPanel.*/
	JPanel childpanel;
	/**Implement a generic type of videoButtonsList.*/
	List<JButton> videoButtonsList =  new ArrayList<JButton>();
	/**Implement a generic type of videoLabelsList.*/
	List<JLabel> videoLabelsList = new ArrayList<JLabel>();

    /**
     * This method shows a basic GUI and plays videos.
     */
	public Admin_Watch_Video() {
		
		initialize();
		initvideo();
	}

    /**
     * This method initializes the contents of the frame.
     */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 106, 880, 545);
		frame.getContentPane().add(scrollPane);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(166,191,195));
		panel.setBounds(100, 106, 900, 545);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--ALL--", "--Yoga--", "--HIIT--", "--Strength--"}));
		comboBox.setBounds(49, 43, 97, 53);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initvideo();
			}
		});
		panel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(168, 43, 378, 53);
		panel.add(textField);
		textField.setColumns(10);

		btnNewButton_2 = new JButton("search");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initvideo();
			}
		});
		btnNewButton_2.setBounds(591, 43, 97, 53);
		panel.add(btnNewButton_2);
		
		
		videoscrollPane = new JScrollPane();
		videoscrollPane.setBounds(30, 110, 780, 330);
		panel.add(videoscrollPane);

		childpanel = new JPanel();
		childpanel.setBackground(new Color(238, 246, 250));
		videoscrollPane.setViewportView(childpanel);
		childpanel.setPreferredSize(new Dimension(750, 220));
		childpanel.setLayout(null);

		JButton toWatchBtn = new JButton("Watch");
		toWatchBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toWatchBtn.setBounds(100, 40, 149, 70);
		frame.getContentPane().add(toWatchBtn);
		
		JButton toUploadBtn = new JButton("Upload");
		toUploadBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toUploadBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Upload_Video();
			}
		});
		toUploadBtn.setBounds(250, 50, 149, 60);
		frame.getContentPane().add(toUploadBtn);
		
		JButton toLookBtn = new JButton("Lookup");
		toLookBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toLookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Look_Up_Info();
			}
		});
		toLookBtn.setBounds(400, 50, 149, 60);
		frame.getContentPane().add(toLookBtn);
		
		JButton toDelBtn = new JButton("Delete");
		toDelBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toDelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Del_Video();
			}
		});
		toDelBtn.setBounds(550, 50, 149, 60);
		frame.getContentPane().add(toDelBtn);
		
		JButton toPostBtn = new JButton("Post");
		toPostBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toPostBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Post_Activities();
			}
		});
		toPostBtn.setBounds(700, 50, 149, 60);
		frame.getContentPane().add(toPostBtn);
		
		JButton toMineBtn = new JButton("Mine");
		toMineBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toMineBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Mine();
			}
		});
		toMineBtn.setBounds(850, 50, 149, 60);
		frame.getContentPane().add(toMineBtn);

		JLabel back_ground_label = new JLabel("");
		back_ground_label.setIgnoreRepaint(true);
		back_ground_label.setIcon(new ImageIcon("./img/back4.jpg"));
		back_ground_label.setBounds(0, 0, 1080, 720);
		frame.getContentPane().add(back_ground_label);

	}  

    /**
     * This method can create a folder and a header of record.csv file.
     */
    public void createCsv(){
		// check Video folder exist
		File folder=new File("./userdata");
		if(!folder.exists()){
		    folder.mkdir();
		}
		// check Video information exist
		File file=new File("./userdata/record.csv");
		if(!file.exists()){
		    List<String> dataList=new ArrayList<String>();
		    dataList.add("UserID,VideoID,Date");
		    boolean isSuccess= CSVUtil.exportNewCsv(new File("./userdata/record.csv"), dataList);
		    System.out.println(isSuccess);
		}
    }

    /**
     * This method can write record.csv file.
     * @param    vId    [String]   [Video ID]
     */
    public void addInfo(String vId){
		// get time string
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = df.format(date);
		// make info
		String newInfo ="1"+","+
				vId+","+timeStr;
		// write info
		List<String> dataList=new ArrayList<String>();
		// dataList.add("VideoID,Title,Label,Brief,Duration,Editor,Date");
		dataList.add(newInfo);
		boolean isSuccess=CSVUtil.exportNewCsv(new File("./userdata/record.csv"), dataList);
		System.out.println(isSuccess);
    }

    /**
     * This method can change the CSV's row to a string.
     * @param    file    [File]   [file's address]
     * @return           [List]   [CSV's row]
     */
    public List<String> importCsv(File file){
		List<String> dataList=new ArrayList<String>();

		BufferedReader br=null;
		try {
		    br = new BufferedReader(new FileReader(file));
		    String line = "";
		    br.readLine();
		    while ((line = br.readLine()) != null) {
				dataList.add(line);
		    }
		}catch (Exception e) {
		}finally{
		    if(br!=null){
				try {
				    br.close();
				    br=null;
				} catch (IOException e) {
				    e.printStackTrace();
				}
		    }
		}

		return dataList;
    }

    /**
     * This method initializes the video.
     */
    public void initvideo(){
		List<String[]> outcome = new ClassGetter().getformatclass();
		outcome.remove(0);
		//List<JButton[]> videoButtonsList;
		//List<JLabel[]> videoLabelsList;
		if(videoButtonsList!=null){
			for(JButton item:videoButtonsList){
				item.setVisible(false);
			}
			for(JLabel item:videoLabelsList){
				item.setVisible(false);
			}
			videoButtonsList.clear();
			videoLabelsList.clear();
		}

		int count_line = 0;
		int h=0,i=0;
		for(String[] s : outcome){
			// Judge type of video
			if(comboBox.getSelectedItem().equals("--ALL--")==false){
				if(comboBox.getSelectedItem().equals("--"+s[2]+"--")==false){
					//System.out.println(comboBox.getSelectedItem()+"--"+s[2]+"--");
					continue;
				}
			}
			// search
			if(textField.getText().equals("")==false){
				// type 1: Euqal to video ID
				// type 2: similar to the video name
				// type 3: word contained in the video description
				if(SubString(textField.getText(),s[1]) || 
					SubString(s[1],textField.getText())||
					textField.getText().equals(s[0])||
					SubString(s[3],textField.getText())){
					System.out.println(s[1]);
				}else{
					continue;
				}
				
			}
			// pack video
			if(count_line==4){
				count_line = 0;
				h++;
			}
			if(h>1){
				childpanel.setPreferredSize(new Dimension(750, 220+(h-1)*300));   //(0, 0, 1080, 720)
			}
			int pictureNum=10000;
			pt=new JButton();
			int countNum=pictureNum+i;
			String videoId=s[0];
			pt.setIcon(new ImageIcon("./Video/"+videoId+"/index.png"));
			this.pt.setBounds(40+(i-h*4)*180,20+h*150,120,100);
			
			this.pt.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent actionEvent) {
					OpenVideoTool.openExe("./Video/"+videoId+"/index.mp4");
					createCsv();
					addInfo(videoId);
			    }
			});

			mt=new JLabel(s[1]);
			this.mt.setBounds(40+(i-h*4)*180,120+h*150,150,60);

			panel.add(pt);
			panel.add(mt);
			childpanel.add(pt);
			childpanel.add(mt);
			videoButtonsList.add(pt);
			videoLabelsList.add(mt);

			count_line++;
			i++;
		}
	}

    /**
     * This method can judge the second string is not a subclass of the first string.
     * @param    str1    [String]   [super string]
     * @param    str2    [String]   [subclass string]
     * @return           [Boolean]  [true or false]
     */
	public Boolean SubString(String str1, String str2){
		//String str1 = "nihaokokosdokosad ";
		//String str2 = "oko ";
		int total = 0;
		for (String tmp = str1; tmp != null&&tmp.length()>=str2.length();){
			if(tmp.indexOf(str2) == 0){
				return true;
				//total ++;
				//tmp = tmp.substring(str2.length());
			}else{
				tmp = tmp.substring(1);
			}
		}
		return false;

	}

}

