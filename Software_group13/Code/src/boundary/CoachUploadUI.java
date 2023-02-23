package boundary;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CoachMyUI;
import entity.CSVUtil;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Class {@code CoachUploadUI} can upload the video and 
 * corresponding information. By inputting the corresponding 
 * information of the video and the path of the video and 
 * the video cover, the program can upload the corresponding 
 * operation, and the program will check the legitimacy 
 * from all angles.
 *
 * @author Hongtian Shan
 * @version 1.0
 * @since 2020/5/30
 */
public class CoachUploadUI {

	/* Announce Components */
	private JFrame frame;
	private JPanel panel;
	// Component - Title
	private JLabel lTitle;
	private JTextField tTitle;
	// Component - Type
	private JLabel lType;
	private JComboBox cType;
	private int cTypeID = 0;
	// Component - Note
	private JLabel lNote;
	private JTextArea tNote;
	// Component - Video
	private JButton bVideo;
	// Component - Picture
	private JButton bPicture;
	// Component - Upload
	private JButton bUpload;
	// Component - Cover show
	private JLabel lblNewLabel;
	// url of picture and video
	private String urlPicture = "";
	private String urlVideo = "";
	// id is the identification of video and picture
	private int id_ = 0;
	// Var
	private String coachID="";
	
	/**
	 * Create the application. - Constructor(without CoachID just for test)
	 */
	public CoachUploadUI() {
		System.out.println("Warning! Do not have User ID!!");
		initialize_file();
		initialize();
	}

	/**
	 * Create the application. - Constructor(with CoachID)
	 * @param coachID coach ID
	 */
	public CoachUploadUI(String coachID) {
		this.coachID = coachID;
		initialize_file();
		initialize();
	}

	/**
	 * Create the application. - Constructor(with CoachID)
	 * @param coachID coach ID
	 */
	private void initialize_file(){
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
	}

	/**
	 * Initialize the contents of the frame.
	 * The interface layout and various click 
	 * functions are specified.
	 */
	private void initialize() {
		// UI components
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(100, 110, 880, 545);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lTitle = new JLabel("Title");
		lTitle.setBounds(40, 50, 61, 16);
		panel.add(lTitle);
		
		tTitle = new JTextField();
		tTitle.setBounds(174, 45, 193, 26);
		panel.add(tTitle);
		tTitle.setColumns(10);
		
		lType = new JLabel("Type");
		lType.setBounds(40, 146, 61, 16);
		panel.add(lType);
		
		cType = new JComboBox();
		cType.setBounds(174, 142, 193, 27);
		cType.addItem("--Choose--");
		cType.addItem("Yoga");
		cType.addItem("HIIT");
		cType.addItem("Strength");
		cType.setSelectedIndex(0); // default is default_set
		panel.add(cType);
		
		lNote = new JLabel("Note");
		lNote.setBounds(40, 254, 61, 16);
		panel.add(lNote);

		tNote = new JTextArea();
		tNote.setBounds(174, 254, 193, 243);
		panel.add(tNote);
		
		bVideo = new JButton("Upload Video");
		bVideo.setBounds(413, 45, 117, 29);
		bVideo.addMouseListener(new MouseAdapter() {
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
					}else{
						System.out.println("Wrong type of file! You should upload xxx.mp4 file");
					}
				} else {

				}
			}
		});
		panel.add(bVideo);

		lblNewLabel = new JLabel("Cover");
		lblNewLabel.setBounds(413, 166, 405, 268);
		panel.add(lblNewLabel);
		// lblNewLabel.setText(new ImageIcon("WebRoot/img/sight1.png"));
		
		bPicture = new JButton("Upload Cover");
		bPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
						lblNewLabel.setIcon(new ImageIcon(urlPicture));
						lblNewLabel.setText("");
					}else{
						System.out.println("Wrong type of file! You should upload xxx.png file");
					}
				} else {

				}
			}
		});
		bPicture.setBounds(413, 100, 117, 29);
		panel.add(bPicture);
		
		bUpload = new JButton("Upload");
		bUpload.setBounds(413, 468, 117, 29);
		bUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validUpload()){
					addFile();
					addInfo();
					clearInfo();
				}
			}
		});
		panel.add(bUpload);
		
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
		
		// jump to Video
		JButton toVideoButtom = new JButton("Video");
		toVideoButtom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CoachVideoUI new_window = new CoachVideoUI(coachID);
			}
		});
		toVideoButtom.setBounds(100, 50, 159, 66);
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
		//toUploadButtom.setBounds(420, 50, 159, 66);
		toUploadButtom.setBounds(420, 40, 159, 76);
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
		
		JLabel back_ground_label = new JLabel("");
		back_ground_label.setIgnoreRepaint(true);
		back_ground_label.setIcon(new ImageIcon("./img/back2.jpg"));
		back_ground_label.setBounds(0, 0, 1080, 720);
		frame.getContentPane().add(back_ground_label);
	}

	/**
	 * Add Video or Picture information 
	 * to Video Folder 
	 */
	public void addInfo(){
		// get time string
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = df.format(date);
		// make info
		String newInfo = String.valueOf(id_)+","+
			tTitle.getText()+","+cType.getSelectedItem()+","+
			tNote.getText()+",0,"+coachID+","+timeStr;
		// write info
		List<String> dataList=new ArrayList<String>();
		//dataList.add("VideoID,Title,Label,Brief,Duration,Editor,Date");
		dataList.add(newInfo);
		boolean isSuccess=CSVUtil.exportNewCsv(new File("./userdata/uploadVideo.csv"), dataList);
		System.out.println(isSuccess);
	}

	/** clearInfo
	 */
	public void clearInfo(){
		tTitle.setText("");
		tNote.setText("");
		lblNewLabel.setIcon(null);
		lblNewLabel.setText("Cover");
		urlPicture = "";
		urlVideo = "";
	}

	/** Add Video or Picture to Folder
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
	 * @param source from dist
	 * @param dist to dist
	 */
	public void copyFile(File source,File dist){
		InputStream in = null;
		OutputStream out = null;
		System.out.println(source.toString());
		System.out.println(dist.toString());
		try {
			in = new FileInputStream(source); 
			out = new FileOutputStream(dist);
			
			byte[] by = new byte[10240];
			int len = 0;
			
			while((len = in.read(by)) != -1) { 
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
		if(tTitle.getText().equals("")){
			System.out.println("Lack of Title!");
			return false;
		}else if(cType.getSelectedItem()=="--Choose--"){
			System.out.println("Lack of Type!");
			return false;
		}else if(tNote.getText().equals("")){
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

}
