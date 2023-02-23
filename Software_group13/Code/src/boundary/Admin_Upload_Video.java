package boundary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Admin_Mine;
import entity.CSVUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.nio.file.Path;

/**
 * Class {@code Admin_Upload_Video} can upload the video and 
 * corresponding information. By inputting the corresponding 
 * information of the video and the path of the video and 
 * the video cover, the program can upload the corresponding 
 * operation, and the program will check the legitimacy 
 * from all angles.
 * 
 * @author   Shiwei Ma
 * @since    2020/5/30
 * @version  [1.0]
 */
public class Admin_Upload_Video {
	/* Announce Components */
	private JFrame frame = new JFrame();
	private	JPanel panel = new JPanel();
	/* Component - Title */
	private JTextField textField1 = new JTextField("");
	/* Component - Note */
	private JTextField textField2 = new JTextField("");
	/* Display component */
	private JLabel lblNewLabel_1 = new JLabel("Title");
	private	JLabel lblNewLabel_2 = new JLabel("Type");
	private	JLabel lblNewLabel_3 = new JLabel("Note");
	private	JLabel lblNewLabel_4 = new JLabel("Cover");
	private	JButton btnNewButton_1 = new JButton("Upload Video");
	private	JButton btnNewButton_2 = new JButton("Upload Cover");
	private JButton btnNewButton_3 = new JButton("Upload");
	/* Component - Type */	
	private JComboBox comboBox = new JComboBox();
	/* url of picture and video */
	private String urlPicture = "";
	private String urlVideo = "";
	/* id is the identification of video and picture */	
	public int id_ = 0;

	/**
	 * Create the application.
	 */
	public Admin_Upload_Video() {

		// check Video folder exist
		File folder=new File("./Video");
		if(!folder.exists()){
			folder.mkdir();
		}
		// check Video information exist
		File file=new File("./userdata/uploadVideo.csv");
		if(!file.exists()){
			List<String> dataList=new ArrayList<String>();
			dataList.add("VideoID,Title,Label,Brief,Duration,Editor,Date");
			boolean isSuccess=CSVUtil.exportNewCsv(new File("./userdata/uploadVideo.csv"), dataList);
			System.out.println(isSuccess);
		}
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panel.setBackground(new Color(166,191,195));
		panel.setBounds(100, 106, 900, 545);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(40, 50, 61, 21);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(40, 146, 61, 23);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(40, 254, 61, 23);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(413, 166, 405, 268);
		panel.add(lblNewLabel_4);		
		
		textField1.setFont(new Font("Arial", Font.PLAIN, 16));
		textField1.setBounds(174, 50, 193, 21);
		panel.add(textField1);
		textField1.setColumns(10);
		
		textField2.setFont(new Font("Arial", Font.PLAIN, 16));
		textField2.setBounds(174, 254, 193, 21);
		panel.add(textField2);
		textField2.setColumns(10);

		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton_1.setBounds(475, 50, 148, 29);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int option = fileChooser.showOpenDialog(new JFrame("Choose File"));
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String[] strArray = file.getName().split("\\.");
					int suffixIndex = strArray.length -1;
					String fileType = strArray[suffixIndex];
					if(fileType.equals("mp4")){
						urlVideo = file.getPath();
						System.out.println(urlVideo);
					}else{
						System.out.println("Wrong type of file! You should upload xxx.mp4 file");
					}
				} else {

				}
			}
		});		
		panel.add(btnNewButton_1);
		
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton_2.setBounds(475, 105, 148, 29);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int option = fileChooser.showOpenDialog(new JFrame("Choose File"));
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String[] strArray = file.getName().split("\\.");
					int suffixIndex = strArray.length -1;
					String fileType = strArray[suffixIndex];
					if(fileType.equals("png")){
						urlPicture = file.getPath();
						lblNewLabel_4.setIcon(new ImageIcon(urlPicture));
						lblNewLabel_4.setText("");
						System.out.println(urlPicture);
					}else{
						System.out.println("Wrong type of file! You should upload xxx.png file");
					}
				} else {

				}
			}
		});
		panel.add(btnNewButton_2);
		
		btnNewButton_3.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_3.setBounds(413, 468, 117, 29);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validUpload()){
					addFile();
					addInfo();
					clearInfo();
				}
			}
		});
		panel.add(btnNewButton_3);
		

		comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBox.setBounds(174, 142, 193, 27);
		comboBox.addItem("--Choose--");
		comboBox.addItem("Yoga");
		comboBox.addItem("HIIT");
		comboBox.addItem("Strength");
		panel.add(comboBox);
		
		JButton toWatchBtn = new JButton("Watch");
		toWatchBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toWatchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Watch_Video();
			}
		});
		toWatchBtn.setBounds(100, 50, 149, 60);
		frame.getContentPane().add(toWatchBtn);
		
		JButton toUploadBtn = new JButton("Upload");
		toUploadBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toUploadBtn.setBounds(250, 40, 149, 70);
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
	 * Clear info 
	 */
	public void clearInfo(){
		textField1.setText("");
		textField2.setText("");
		lblNewLabel_4.setIcon(null);
		lblNewLabel_4.setText("Cover");
		urlPicture = "";
		urlVideo = "";
	}

	/**
	 * Add Video or Picture information to Video Folder 
	 */
	public void addInfo(){
		// get time string
		Date date = new Date();// Get the current date
		String editor = loginID();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// Format the date
		String timeStr = df.format(date);// Get the time of type String
		// make info
		String newInfo = String.valueOf(id_)+","+
			textField1.getText()+","+comboBox.getSelectedItem()+","+
			textField2.getText()+",0,"+editor+","+timeStr;
		// write info
		List<String> dataList=new ArrayList<String>();
		//dataList.add("VideoID,Title,Label,Brief,Duration,Editor,Date");
		dataList.add(newInfo);
		boolean isSuccess=CSVUtil.exportNewCsv(new File("./userdata/uploadVideo.csv"), dataList);
		System.out.println(isSuccess);
	}

	/** 
	 * Add Video or Picture to Folder
	 */
	public void addFile(){
		// Get folder id and creat folder
		id_ = newVideoID();
		File file = new File("./Video/"+id_);
		file.mkdir();
		// upload video and image
		File originPicture = new File(urlPicture);
		File originVideo = new File(urlVideo);
		copyFile(originVideo,new File("./Video/"+id_+"/index.mp4"));//originVideo.getName()
		copyFile(originPicture,new File("./Video/"+id_+"/index.png"));//originPicture.getName()
	}

	/** Save File
	 * @param source	[File] [from dist]
	 * @param dist		[File] [to dist]
	 */
	public void copyFile(File source,File dist){
		InputStream in = null;
		OutputStream out = null;
		System.out.println(source.toString());
		System.out.println(dist.toString());
		try {
			in = new FileInputStream(source); // Pass in the source file address
			out = new FileOutputStream(dist); // Pass in new address
			
			byte[] by = new byte[10240]; // A byte array is used to transfer the byte stream
			int len = 0;
			
			while((len = in.read(by)) != -1) { // Each read from IN by is stored in memory, and the length of each transfer is recorded
				out.write(by, 0, len);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}	
	}

	/**
	 * Check whether valid to add video
	 */
	public Boolean validUpload(){
		if(textField1.getText().equals("")){
			System.out.println("Lack of Title!");
			return false;
		}else if(comboBox.getSelectedItem()=="--Choose--"){
			System.out.println("Lack of Type!");
			return false;
		}else if(textField2.getText().equals("")){
			System.out.println("Lack of Note!");
			return false;
		}else if(urlVideo.equals("")){
			System.out.println("Lack of video url!");
			return false;
		}else if(urlPicture.equals("")){
			System.out.println("Lack of picture url!");
			return false;
		}
		return true;
	}

	/** 
	 * Generate Video ID
	 */
	public int newVideoID(){
	    int id_ = 10000;
		File folder = new File("./Video/"+id_);
		while(folder.exists()){
			id_ = id_+1;
			folder = null;
			folder = new File("./Video/"+id_);
		}
	    System.out.println(id_);
	    return id_;
	}

	/** 
	 * Get login ID
	 * @return 	[String] [loginID]
	 */
	public String loginID(){
		File file1 = new File("./userdata/logid.csv");
		Path PATHtemp1 = file1.toPath();
		String PATH = PATHtemp1.toString();
		String lastID = null;
		String line = null;
		int lastRow = 0;
		int rowCount = 0;
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

			return lastID;
		} catch (IOException e1) {

			e1.printStackTrace();
			return null;
		}
	}

}
