package boundary;
import java.awt.*;
import javax.swing.*;

import controller.Admin_Mine;
import entity.CSVUtil;
import entity.ClassGetter;
import entity.DeleteFileUtil;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class {@code Admin_Del_Video} is used to check and delete videos 
 * for administrators and coaches. Administrators can select any 
 * video to view or delete. When a video is deleted, information 
 * related to the video, such as watching records of students, 
 * will be automatically deleted.
 *
 * @author Shiwei Ma
 * @version 1.0
 * @since 2020/5/30
 */
public class Admin_Del_Video {

	/* Announce Components */
	private JFrame frame;
	private JPanel panel;
	/* Component - Child Page Title */
	private JLabel lMention1;
	/* Component - ID selector */
	private JLabel lMention2;
	private JTextField tCheckByID;
	private JButton bsearch;
	/* Component - show all this coach's video */
	private JList listMyVideo;
	private DefaultListModel listModel;
	private JScrollPane listScrollPane;
	/* Component - show title */
	private JLabel ltitle;
	/* Component - show cover */
	private JLabel lcover;
	/* Component - delete video */
	private JButton bDelete;
	/* Var - all classes informaiton */
	private List<String[]> classInfo;
	/* Var - choosed item id */
	String id_ = "0";

	/**
	 * Create the application.
	 */
	public Admin_Del_Video() {
		initialize();
		initclasses();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setBackground(new Color(166,191,195));
		panel.setBounds(100, 106, 900, 545);
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
				// Get index of choosed
				//int i = listMyVideo.getSelectedIndex();
                // get id of the choosed item
                //String id_ = classInfo.get(i)[0];
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
		toDelBtn.setBounds(550, 40, 149, 70);
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
		classInfo = new ClassGetter().getformatclass();
		classInfo.remove(0); // Delete the table
		// Init All class List table
		for (String[] s : classInfo) {
			listModel.addElement(s[0]+","+s[1]); // The ID and title of the video
		}
	}

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
	 * Change the file that records the video 
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
