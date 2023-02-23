package boundary;
import java.text.DecimalFormat;
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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
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
 * Class {@code CoachTeachUI} is the module that selects courses. 
 * The coach can see his students and how well they have 
 * completed the course. The coach can also increase or 
 * decrease the training tasks of the students based on the 
 * existing course. The task completion pairs of students 
 * are updated dynamically according to different plans.
 *
 * @author Hongtian Shan
 * @version 1.0
 * @since 2020/5/31
 */
public class CoachTeachUI {

	/* Announce Components */
	private JFrame frame;
	private JPanel panel;
	// Component - Child Page Choose Student
	private JLabel ltip1;
	private JScrollPane spStudent;
	private JList listStudent;
	private DefaultListModel listModelStu;
	// Component - Child Page All courses
	private JLabel ltip2;
	private JScrollPane spCourse;
	private JList listCourse;
	private DefaultListModel listModelCou;
	// Component - Child Page choosed courses
	private JLabel ltip3;
	private JScrollPane spSCourse;
	private JList listSCourse;
	private DefaultListModel listModelSCou;
	// Components - smart label
	private JLabel ltip_to1;
	private JLabel ltip_to2;
	// Component - Child Page progress
	private JLabel ltip4;
	private JProgressBar progressBar;
	// Var - The students belong to the coach
	private List<String[]> studentInfo;
	// Var - all classes aviliable
	private List<String[]> allclassInfo;
	// Var - Choosed classes
	private List<String[]> classInfo1;
	// Var - Unchoosed classes
	private List<String[]> classInfo0;
	// Var -  Choosed Student ID
	private String stu_id = "";
	// Var
	private String coachID="";
	
	/**
	 * Create the application. - Constructor(without CoachID just for test)
	 */
	public CoachTeachUI() {
		System.out.println("Warning! Do not have User ID!!");
		initialize();
		initclasses();
		initstudents();
	}

	/**
	 * Create the application. - Constructor(with CoachID)
	 * @param coachID coach ID
	 */
	public CoachTeachUI(String coachID) {
		this.coachID = coachID;
		initialize();
		initclasses();
		initstudents();
	}

	/**
	 * Initialize the contents of the frame.
	 * The interface layout and various click 
	 * functions are specified.
	 */
	private void initialize() {
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
		
		ltip1 = new JLabel("Choose Students");
		ltip1.setBounds(36, 34, 173, 24);
		panel.add(ltip1);

		spStudent = new JScrollPane();
		spStudent.setBounds(36, 104, 312, 311);
		panel.add(spStudent);
		
		listModelStu = new DefaultListModel();
		listStudent = new JList(listModelStu);
		listStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listStudent.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get index of choosed
				int i = listStudent.getSelectedIndex();
				// get id of the choosed item
				stu_id = studentInfo.get(i)[0];
				// change classes choosed
				initChoosedClasses();
				// change classes unchoosed
				initUnchoosedClasses();
				// change progressbar - should be the last init func
				initProgressBar();
			}
		});
		spStudent.setViewportView(listStudent);

		ltip2 = new JLabel("All Courses");
		ltip2.setBounds(394, 38, 99, 16);
		panel.add(ltip2);

		spCourse = new JScrollPane();
		spCourse.setBounds(366, 97, 207, 372);
		panel.add(spCourse);
		
		listModelCou = new DefaultListModel();
		listCourse = new JList(listModelCou);
		listCourse.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get index of choosed
				int i = listCourse.getSelectedIndex();
				// get id of the choosed item
				String class_id = classInfo0.get(i)[0];
				// choose class
				addClass(class_id);
				// change classes choosed
				initChoosedClasses();
				// change classes unchoosed
				initUnchoosedClasses();
				// change progressbar - should be the last init func
				initProgressBar();
			}
		});
		spCourse.setViewportView(listCourse);

		ltip3 = new JLabel("Student's Courses");
		ltip3.setBounds(636, 38, 126, 16);
		panel.add(ltip3);
		
		spSCourse = new JScrollPane();
		spSCourse.setBounds(634, 97, 207, 372);
		panel.add(spSCourse);
		
		listModelSCou = new DefaultListModel();
		listSCourse = new JList(listModelSCou);
		listSCourse.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get index of choosed
				int i = listSCourse.getSelectedIndex();
				// get id of the choosed item
				String class_id = classInfo1.get(i)[0];
				// reduce class
				reduceClass(class_id);
				// change classes choosed
				initChoosedClasses();
				// change classes unchoosed
				initUnchoosedClasses();
				// change progressbar - should be the last init func
				initProgressBar();
			}
		});
		spSCourse.setViewportView(listSCourse);

		ltip_to1 = new JLabel(">>>");
		//ltip_to1.setBounds(594, 257, 46, 16);
		ltip_to1.setBounds(594, 255, 46, 16);
		panel.add(ltip_to1);
		
		ltip_to2 = new JLabel("<<<");
		//ltip_to2.setBounds(594, 316, 46, 16);
		ltip_to2.setBounds(594, 314, 46, 16);
		panel.add(ltip_to2);
		
		ltip4 = new JLabel("Student Project");
		ltip4.setBounds(36, 453, 148, 16);
		panel.add(ltip4);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(152, 449, 202, 20);
		progressBar.setStringPainted(true);
		panel.add(progressBar);
		
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
		//toTeachButtom.setBounds(260, 50, 159, 66);
		toTeachButtom.setBounds(260, 40, 159, 76);
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
	 * Init Class Info - get all classes
	 */
	private void initclasses(){
		// Then get all classes
		allclassInfo = new ClassGetter().getformatclass();
		allclassInfo.remove(0); // Delete the table
	}

	/** 
	 * Init the Child Page1 - 
	 * Students choose list filling
	 */
	private void initstudents(){
		// First clear all elements
		listModelStu.removeAllElements();// jlist.getModel()
		// Then get all classes
		studentInfo = new ClassGetter().getformatstudent(coachID);
		// Init All class List table
		for (String[] s : studentInfo){
			//listModelStu.addElement(s[0]+","+s[1]); // The ID and title of the video
			listModelStu.addElement(s[1]);
		}
	}

	/** 
	 * init progressBar for current student
	 */
	private void initProgressBar(){
		//progressBar.setValue(currentProgress);
		// classInfo1 = new ClassGetter().getchoosedclass(coachID,stu_id);
		if(classInfo1==null || classInfo1.size()==0){
			progressBar.setValue(0);
			System.out.println("No classes choosed");
			return;
		}
		int classChoosedNum = classInfo1.size();
		System.out.println(classChoosedNum);
		int classWatchedNum = 0;
		List<String> watchedclassInfo=new ArrayList<String>();
		watchedclassInfo = CSVUtil.importCsv(new File("./userdata/record.csv"));
		watchedclassInfo.remove(0);
		for (String[] s : classInfo1){
			for(String d: watchedclassInfo){
				String temp[] = d.split(",");
				if(s[0].equals(temp[1]) && stu_id.equals(temp[0])){
					System.out.println("watched "+s[0]);
					classWatchedNum = classWatchedNum+1;
				}
			}
		}
		//DecimalFormat df=new DecimalFormat("0.00");
		//double x = Double.valueOf(classWatchedNum)/Double.valueOf(classChoosedNum)*100.0;
		//progressBar.setValue(x.intValue());
		progressBar.setValue(classWatchedNum*100/classChoosedNum);
		
	}

	/** 
	 * Get choosed classes 
	 */
	private void initChoosedClasses(){
		// First clear all elements
		listModelSCou.removeAllElements();// jlist.getModel()
		// Then get all choosed classes
		classInfo1 = new ClassGetter().getchoosedclass(coachID,stu_id);
		// Init All class List table
		for (String[] s : classInfo1){
			//listModelSCou.addElement(s[0]+","+s[1]); // The ID and title of the video
			listModelSCou.addElement(s[1]);
		}
	}

	/** 
	 * Get Unchoosed classes 
	 */
	private void initUnchoosedClasses(){
		// First clear all elements
		listModelCou.removeAllElements();// jlist.getModel()
		// Then get all unchoosed classes
		classInfo0 = new ArrayList<String[]>();
		boolean flag = true;
		for(String[] s:allclassInfo){
			flag = true;
			for(String[] j:classInfo1){
				if(j[0].equals(s[0])){
					flag = false;
					break;
				}
			}
			if(flag==true){
				classInfo0.add(s);
			}
		}
		// Init All class List table
		for (String[] s : classInfo0){
			//listModelCou.addElement(s[0]+","+s[1]); // The ID and title of the video
			listModelCou.addElement(s[1]);
		}
	}

	/** 
	 * Add Class for a student
	 * @param classID video ID
	 */
	private void addClass(String classID){
		// change course file
		List<String> dataList=new ArrayList<String>();
		dataList.add(coachID+","+stu_id+","+classID);
		CSVUtil.exportNewCsv(new File("./userdata/course.csv"), dataList);
	}

	/** 
	 * Reduces Class for a student
	 * @param classID video ID
	 */
	private void reduceClass(String classID){
		// change course file
		List<String> dataList=new ArrayList<String>();
		for (String s : CSVUtil.importCsv(new File("./userdata/course.csv"))){
			String[] temp = s.split(",");
			if(temp[0].equals(coachID) && temp[1].equals(stu_id) && temp[2].equals(classID)){
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
