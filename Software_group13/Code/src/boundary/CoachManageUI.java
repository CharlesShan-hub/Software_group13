package boundary;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

import controller.CoachMyUI;
import entity.CSVUtil;
import entity.ClassGetter;
import entity.DeleteFileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class {@code CoachManageUI} is used to check and delete videos 
 * for administrators and coaches. This class provides video 
 * information uploaded by itself according to coach ID. coaches 
 * can select courses to view or delete. When a course is deleted, 
 * information related to the course, such as watching records of 
 * students, will be automatically deleted.
 *
 * @author Hongtian Shan
 * @version 1.0
 * @since 2020/5/30
 */
public class CoachManageUI{

	/* Announce Components */
	private JFrame frame;
	private JPanel panel;
	// Component - Child Page Title
	private JLabel lMention1;
	// Component - ID selector
	private JLabel lMention2;
	private JTextField tCheckByID;
	private JButton bsearch;
	// Component - show all this coach's video
	private JList listMyVideo;
	private DefaultListModel listModel;
	private JScrollPane listScrollPane;
	// Component - show title
	private JLabel ltitle;
	// Component - show cover
	private JLabel lcover;
	// Component - delete video
	private JButton bDelete;
	// Var - all classes informaiton
	private List<String[]> classInfo;
	// Var - choosed item id
	String id_ = "0";
	// Var
	private String coachID="";
	
	/**
	 * Create the application. - Constructor(without CoachID just for test)
	 */
	public CoachManageUI() {
		System.out.println("Warning! Do not have User ID!!");
		initialize();
		initclasses();
	}

	/**
	 * Create the application. - Constructor(with CoachID)
	 * @param coachID coach ID
	 */
	public CoachManageUI(String coachID) {
		this.coachID = coachID;
		initialize();
		initclasses();
	}

	/**
	 * Initialize the contents of the frame.
	 * The interface layout and various click 
	 * functions are specified.
	 */
	private void initialize() {
		// frame and frame settings
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// jump to Video
		JButton toVideoButtom = new JButton("Video");
		toVideoButtom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CoachVideoUI new_window = new CoachVideoUI(coachID);
			}
		});
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(100,110,880,545);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lMention1 = new JLabel("My Video");
		lMention1.setBounds(24, 24, 96, 30);
		panel.add(lMention1);
		
		lMention2 = new JLabel("Video ID");
		lMention2.setBounds(24, 86, 96, 30);
		panel.add(lMention2);
		
		tCheckByID = new JTextField();
		tCheckByID.setBounds(115, 88, 101, 26);
		panel.add(tCheckByID);
		tCheckByID.setColumns(10);

		bsearch = new JButton("Search");
		bsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByID(tCheckByID.getText());
			}
		});
		bsearch.setBounds(217, 88, 90, 29);
		panel.add(bsearch);
		
		lcover = new JLabel("Video Photo");
		lcover.setBounds(363, 102, 464, 280);
		panel.add(lcover);
		
		ltitle = new JLabel("Video Title");
		ltitle.setBounds(520, 50, 190, 40);
		panel.add(ltitle);

		listScrollPane = new JScrollPane();
		listScrollPane.setBounds(24, 151, 277, 322);
		panel.add(listScrollPane);
		
		listModel = new DefaultListModel();
		listMyVideo = new JList(listModel);
		listMyVideo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMyVideo.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get index of choosed
				int i = listMyVideo.getSelectedIndex();
                // get id of the choosed item
                id_ = classInfo.get(i)[0];
                // change tip title
                ltitle.setText(classInfo.get(i)[1]);
                // change tip pic
                lcover.setIcon(new ImageIcon("./Video/"+id_+"/index.png"));
				lcover.setText("");
			}
		});
		listScrollPane.setViewportView(listMyVideo);
		
		bDelete = new JButton("Delete Video");
		bDelete.setBounds(493, 466, 117, 29);
		bDelete.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
                if(id_.equals("0")){
                	return;
                }
                // change tip title
                ltitle.setText("Video Title");
                // change tip pic
                lcover.setIcon(null);
				lcover.setText("Video Photo");
				// delete files
				DeleteFileUtil.deleteFile("./Video/"+id_+"/index.png");
				DeleteFileUtil.deleteFile("./Video/"+id_+"/index.mp4");
				DeleteFileUtil.deleteDirectory("./Video/"+id_);
				// change csv - Video CSV and Class CSV
				changeVideoCSV(id_);
				changeClassCSV(id_);
				// Init again
				initclasses();
			}
		});
		panel.add(bDelete);

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
		toManageButtom.setBounds(580, 40, 159, 76);
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
	 * Find video information according to video ID
	 * @param id__ the video id
	 */
	private void searchByID(String id__){
		for (String[] s : classInfo) {
			if(id__.equals(s[0])){
				id_ = id__;
				// change tip title
                ltitle.setText(s[1]);
                // change tip pic
                lcover.setIcon(new ImageIcon("./Video/"+id_+"/index.png"));
				lcover.setText("");
				break;
			}
		}
	}

	/**
	 * According to the read file, 
	 * initialize all the course information
	 */
	private void initclasses(){
		// First clear all elements
		listModel.removeAllElements();// jlist.getModel()
		// Then get all classes
		List<String[]> temp = new ClassGetter().getformatclass();
		temp.remove(0); // Delete the table
		// filter
		classInfo = new ArrayList<String[]>();
		for (String[] s : temp) {
			if(s[5].equals(coachID)){
				classInfo.add(s);
			}
		}
		// Init All class List table
		for (String[] s : classInfo) {
			//listModel.addElement(s[0]+","+s[1]); // The ID and title of the video
			listModel.addElement(s[1]);
		}
	}

	/**
	 * Change the file that records the video 
	 * information after deleting the video
	 * @param id_ the video id
	 */
	private void changeVideoCSV(String id_){
		// Get initial info
		List<String> classInfoStr = new ClassGetter().getclass();
		// write new file
		List<String> newClassInfoStr = new ArrayList<String>();
		for (String s : classInfoStr) {
			String[] temp = s.split(",");
			if(id_.equals(temp[0])){
				System.out.println("Delete line "+id_);
			}else{
				newClassInfoStr.add(s);
			}
		}
		// save
		DeleteFileUtil.deleteFile("./userdata/uploadVideo.csv");
		CSVUtil.exportNewCsv(new File("./userdata/uploadVideo.csv"), newClassInfoStr);
	}

	/**
	 * Change the file that records the train plan 
	 * information after deleting the video
	 * @param id_ the video id
	 */
	private void changeClassCSV(String id_){
		List<String> dataList=new ArrayList<String>();
		for (String s : CSVUtil.importCsv(new File("./userdata/course.csv"))){
			String[] temp = s.split(",");
			if(temp[2].equals(id_)){
				System.out.println("Delete class!");
			}else{
				dataList.add(s);
			}
		}
		// save
		DeleteFileUtil.deleteFile("./userdata/course.csv");
		CSVUtil.exportNewCsv(new File("./userdata/course.csv"), dataList);
	}

}
