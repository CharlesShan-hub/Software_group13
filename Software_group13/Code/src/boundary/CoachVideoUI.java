package boundary;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CoachMyUI;
import entity.CSVUtil;
import entity.ClassGetter;
import entity.OpenVideoTool;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.io.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Class {@code CoachVideoUI} is to play and filter 
 * the video to watch, and generate a scroll stream. 
 * When the user watches the video, the system will 
 * save the playback record.
 *
 * @author Hongtian Shan
 * @version 1.0
 * @since 2020/5/31
 */
public class CoachVideoUI {

	private JFrame frame;
	// Var
	private String coachID="";

	public JButton pt;
    public JLabel mt;
	private JPanel contentPane;
	private JTextField textField;

	String lastID = null;
	String NofM = "";
	String user_balance = null;
	String user_type = null;

	JScrollPane scrollPane;
	JPanel panel;
	JComboBox comboBox;
	JButton btnNewButton_2;
	JScrollPane videoscrollPane;
	JPanel childpanel;

	List<JButton> videoButtonsList =  new ArrayList<JButton>();
	List<JLabel> videoLabelsList = new ArrayList<JLabel>();

	/**
	 * Create the application. - Constructor(without CoachID just for test)
	 */
	public CoachVideoUI() {
		System.out.println("Warning! Do not have User ID!!");
		initialize();
		initvideo();
	}

	/**
	 * Create the application. - Constructor(with CoachID)
	 * @param coachID coach ID
	 */
	public CoachVideoUI(String coachID) {
		this.coachID = coachID;
		initialize();
		initvideo();
	}

	/**
	 * Initialize the contents of the frame.
	 * The interface layout and various click 
	 * functions are specified.
	 */
	private void initialize() {
		/* Get id */
		String line = null;
		String name = null;
		String id = null;
		String register_date = null;
		String phone = null;
		int lastRow = 0;
		int rowCount = 0;
		File file = new File("./userdata/logid.csv");
		Path PATHtemp = file.toPath();
		String PATH = PATHtemp.toString();
		File file1 = new File("./userdata/account.csv");
		Path PATHtemp1 = file1.toPath();
		String PATH1 = PATHtemp1.toString();
		try {
			FileInputStream inputStream1 = new FileInputStream(PATH);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(inputStream1));
			while((line=br1.readLine())!=null){
				lastRow++;
			}
			inputStream1.close();
			br1.close();

			FileInputStream inputStream2 = new FileInputStream(PATH);
			BufferedReader br2 = new BufferedReader(new InputStreamReader(inputStream2));
			while((line=br2.readLine())!=null){
				rowCount++;
				if(rowCount==lastRow){
					String[] item = line.split(",");
					lastID = item[0];
		      break;
				}
			}
			inputStream2.close();
			br2.close();

			FileInputStream inputStream3 = new FileInputStream(PATH1);
			BufferedReader br3 = new BufferedReader(new InputStreamReader(inputStream3));
			while((line=br3.readLine())!=null){
				String[] item = line.split(",");
		    if(item[0].equals(lastID)){
		    	name = item[1];
		    	id = item[0];
		    	user_balance = item[5];
		    	register_date = item[6];
		    	phone = item[3];
		    	user_type = item[4];
				}
			}
			inputStream2.close();
			br3.close();			

		} catch (IOException e1) {

				e1.printStackTrace();
		}

		/*Other components*/
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 106, 880, 545);
		frame.getContentPane().add(scrollPane);

		/*************************************/

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(100, 114, 880, 545);
		contentPane.add(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--ALL--", "--Yoga--", "--HIIT--", "--Strength--"}));
		comboBox.setBounds(49, 43, 97, 53);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("You choosed me!");
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
				//System.out.println("xoxo!");
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

		/*************************************/
		
		JLabel testlabel = new JLabel("Video");
		scrollPane.setColumnHeaderView(testlabel);
		
		// jump to Video
		JButton toVideoButtom = new JButton("Video");
		//toVideoButtom.setBounds(100, 50, 159, 66);
		toVideoButtom.setBounds(100, 40, 159, 76);
		frame.getContentPane().add(toVideoButtom);
		
		// jump to Teaching
		JButton toTeachButtom = new JButton("Teach");
		toTeachButtom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CoachTeachUI new_window = new CoachTeachUI(coachID);
			}
		});
		toTeachButtom.setBounds(260, 50, 159, 66);
		frame.getContentPane().add(toTeachButtom);
		
		// jump to Upload
		JButton toUploadButtom = new JButton("Upload");
		toUploadButtom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CoachUploadUI new_window = new CoachUploadUI(coachID);
			}
		});
		toUploadButtom.setBounds(420, 50, 159, 66);
		frame.getContentPane().add(toUploadButtom);
		
		// jump to Manage Video
		JButton toManageButtom = new JButton("Manage");
		toManageButtom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CoachManageUI new_window = new CoachManageUI(coachID);
			}
		});
		toManageButtom.setBounds(580, 50, 159, 66);
		frame.getContentPane().add(toManageButtom);
		
		// jump to Coach Information
		JButton toMyButtom = new JButton("Mine");
		toMyButtom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CoachMyUI new_window = new CoachMyUI(coachID);
			}
		});
		toMyButtom.setBounds(740, 50, 159, 66);
		frame.getContentPane().add(toMyButtom);
		
		JLabel back_ground_label = new JLabel("");
		back_ground_label.setIgnoreRepaint(true);
		back_ground_label.setIcon(new ImageIcon("./img/back2.jpg"));
		back_ground_label.setBounds(0, 0, 1080, 720);
		frame.getContentPane().add(back_ground_label);
	}  
	
	/**
	 * Check the files you should have, 
	 * and create files if they are missing.
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
     * Save the record of the user 
     * watching the video to a file
     * @param vId video ID
     */
    public void addInfo(String vId){
        // get time string
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = df.format(date);
        // make info
        String newInfo =lastID+","+
                vId+","+timeStr;
        // write info
        List<String> dataList=new ArrayList<String>();
        //dataList.add("VideoID,Title,Label,Brief,Duration,Editor,Date");
        dataList.add(newInfo);
        boolean isSuccess=CSVUtil.exportNewCsv(new File("./userdata/record.csv"), dataList);
        System.out.println(isSuccess);
    }

    /**
     * Acts a function that reads the 
     * contents of a CSV file
     * @param file csv path file
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
     * Initialize the video, read the video information, 
     * and display the video. And scrolling window Settings.
     */
    public void initvideo(){
		List<String[]> outcome = new ClassGetter().getformatclass();
		outcome.remove(0);
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
	 * Get SubString
	 * @param str1 the long string
	 * @param str2 the short string
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

