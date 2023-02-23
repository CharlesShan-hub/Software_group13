package boundary;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JDialog;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.UserInfomationUI;
import entity.CSVUtil;
import entity.DeleteFileUtil;

import javax.swing.ImageIcon;


/**
 * Class {@code UserCheckProgressUI} is to view the users course 
 * progress and set target. 
 * This module carries on the personal target and 
 * course information reading and setting.
 * The function includes input of the weight.
 * and has the logout button.
 *
 * @author Youguang Zhou
 * @version 1.0
 * @since 2020/5/30
 */

public class UserCheckProgressUI extends JFrame {
	/* Announce Components */
	private JFrame frame;
	
	/**
	 * Initialize the contents of the frame.
	 */
	DefaultListModel ListModelSCouFini;
	DefaultListModel listModelSCouTotal;
	DefaultListModel ListModelSCouUnfini;
	String lastID = null;
	String NofM = "";
	String user_balance = null;
	String user_type = null;
	String weight_target = "";
	String weight_now = "";
	String weight_target_input = "";
	String weight_now_input = "";
	int FiniNum = 0;
	int TotalNum = 0;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the application.
	 */
	public UserCheckProgressUI() {
		inticheckprogress();
	}
	
	private void inticheckprogress() {
		
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
		    	weight_target = item[8];
		    	weight_now = item[9];
				}
			}
			inputStream2.close();
			br3.close();			

		} catch (IOException e1) {

				e1.printStackTrace();
		}
		
		//GUI
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		JButton btnNewButton = new JButton("Video");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserVideoUI();
			}
		});
		btnNewButton.setBounds(100, 50, 159, 66);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Choose coach");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user_type.equals("Prime")){
					frame.dispose();
					new UserSelectCoachUI();
				}else{
					FlowtypePage("You shuold be Prime first!");
					//System.out.println("You shuold be Prime first!");
				}
			}
		});
		btnNewButton_1.setBounds(269, 50, 159, 66);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Check progress");
		btnNewButton_1_1.setBounds(438, 40, 159, 76);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Personal information");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserInfomationUI();
			}
		});
		btnNewButton_1_2.setBounds(607, 50, 159, 66);
		frame.getContentPane().add(btnNewButton_1_2);

		
		panel.setBackground(new Color(205, 205, 190));
		panel.setBounds(100, 113, 880, 545);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("The video needed to watch");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(55, 146, 208, 70);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("The video finished");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(396, 146, 182, 70);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("The video unfinished");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(664, 146, 182, 70);
		panel.add(lblNewLabel_1_1);
		
		listModelSCouTotal = new DefaultListModel();
		JList list = new JList(listModelSCouTotal);
		Plan();
		list.setBounds(10, 242, 279, 260);
		panel.add(list);
		
		ListModelSCouFini = new DefaultListModel();
		JList list_1 = new JList(ListModelSCouFini);
		FinishPlan();
		list_1.setBounds(374, 242, 195, 169);
		panel.add(list_1);
		
		ListModelSCouUnfini = new DefaultListModel();
		JList list_1_1 = new JList(ListModelSCouUnfini);
		UnFinishPlan();
		list_1_1.setBounds(638, 242, 195, 169);
		panel.add(list_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Speed of Progress:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(424, 447, 195, 55);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(String.valueOf(FiniNum) + " / " + String.valueOf(TotalNum));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(652, 447, 182, 55);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Target weight:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(31, 101, 141, 40);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(weight_target);
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(134, 101, 141, 40);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Weight now:");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(31, 37, 182, 40);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(weight_now);
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(134, 37, 182, 40);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Input your Target:");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(396, 101, 162, 40);
		panel.add(lblNewLabel_8);
		
		textField = new JTextField();
		textField.setBounds(584, 109, 132, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("OK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weight_target_input = textField.getText();
				ChangeTarget(lastID);
				// refresh
				frame.dispose();
				new UserCheckProgressUI();
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton_2.setBounds(753, 106, 93, 30);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_9 = new JLabel("Input the weight now:");
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(396, 34, 195, 46);
		panel.add(lblNewLabel_9);
		
		textField_1 = new JTextField();
		textField_1.setBounds(584, 45, 131, 27);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_2_1 = new JButton("OK");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weight_now_input = textField_1.getText();
				ChangeNow(lastID);
				// refresh
				frame.dispose();
				new UserCheckProgressUI();
			}
		});
		btnNewButton_2_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton_2_1.setBounds(753, 42, 93, 30);
		panel.add(btnNewButton_2_1);

		JLabel back_ground_label = new JLabel("");
		back_ground_label.setIgnoreRepaint(true);
		back_ground_label.setIcon(new ImageIcon("./img/back3.jpg"));
		back_ground_label.setBounds(0, 0, 1080, 720);
		frame.getContentPane().add(back_ground_label);
	}
	
	/**
	 * A function that reminds users to upgrade to Prime.
	 */
	public void FlowtypePage(String warnwords) {
		JDialog warn = new JDialog();
		warn.setTitle("warning");
		warn.setBounds(100,50,500,100);
		warn.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel warn1 = new JLabel(warnwords);
		warn1.setFont(new Font("Arial",Font.PLAIN,36));
		warn.add(warn1);
		//warn.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		warn.setVisible(true);
	}

	/**
	 * A function that gets the course from the coach and shows.
	 */
	private void Plan(){
		String CourseTotal = "";
		String getcourseTotal = "";
		String courseInfoTotal = "";
		TotalNum = 0;
		int i = 0;
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader("./userdata/course.csv"));
			while((CourseTotal=reader1.readLine())!=null){
                String temp[] = CourseTotal.split(",");
                for (i=0;i<temp.length;i++){
                    if (temp[1].equals(lastID)) {
                    	courseInfoTotal = courseInfoTotal + temp[2] + ",";
                    	TotalNum = TotalNum + 1;
                    	break;
                    }
                }
            }
			
			for (String s : courseInfoTotal.split(",")){
				//listModelCou.addElement(s[0]+","+s[1]); // The ID and title of the video
				listModelSCouTotal.addElement(s);
			}
			reader1.close();
		}catch (Exception e) { 
            e.printStackTrace(); 
        }
	}
	
	/**
	 * A function that gets the courses which the users have finished.
	 */
	private void FinishPlan() {
		String Course = "";
		String getcourse = "";
		String courseInfoFinish = "";
		FiniNum = 0;
		int i = 0;
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader("./userdata/record.csv"));
			while((Course=reader1.readLine())!=null){
                String temp[] = Course.split(",");
                for (i=0;i<temp.length;i++){
                    if (temp[0].equals(lastID)) {
                    	courseInfoFinish = courseInfoFinish + temp[1] + ",";
                    	FiniNum = FiniNum + 1;
                    	break;
                    }
                }
            }
			
			//System.out.print(courseInfoFinish);
			for (String s : courseInfoFinish.split(",")){
				//listModelCou.addElement(s[0]+","+s[1]); // The ID and title of the video
				ListModelSCouFini.addElement(s);
			}
			reader1.close();
		}catch (Exception e) { 
            e.printStackTrace(); 
        }
	}
	
	/**
	 * A function that gets the courses which the users don't finished.
	 */
	private void UnFinishPlan() {
		String Course1 = "";
		String Course2 = "";
		String CourseTotal = "";
		String CourseFinish = "";
		String CourseUnfini = "";
		int i = 0;
		int j = 0;
		int x = 0;
		int y = 0;
		int k = 0;
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader("./userdata/record.csv"));
			BufferedReader reader2 = new BufferedReader(new FileReader("./userdata/course.csv"));
			while((Course1=reader2.readLine())!=null){
                String tempTotal[] = Course1.split(",");
                for (i=0;i<tempTotal.length;i++){
                    if (tempTotal[1].equals(lastID)) {
                    	CourseTotal = CourseTotal + tempTotal[2] + ",";
                    	break;
                    }
                }
            }
			while ((Course2=reader1.readLine())!=null){
				String tempFinish[] = Course2.split(",");
				for (j=0; j<tempFinish.length; j++) {
					if (tempFinish[0].equals(lastID)) {
						CourseFinish = CourseFinish + tempFinish[1] + ",";
						break;
					}
				}
			}
			String finish[] = CourseFinish.split(",");
			String total[] = CourseTotal.split(",");
			for(x=0; x<total.length; x++) {
				
				for (y=0; y<finish.length; y++) {
					if (finish[y].equals(total[x])) {
						break;
					}else {
						k = k + 1;
					}
				}
				if (k == finish.length) {
					CourseUnfini = CourseUnfini + total[x] + ",";
				}
				k = 0;
			}
			
			for (String s : CourseUnfini.split(",")){
				//listModelCou.addElement(s[0]+","+s[1]); // The ID and title of the video
				ListModelSCouUnfini.addElement(s);
			}
			
			
			reader1.close();
			reader2.close();
		}catch (Exception e) { 
            e.printStackTrace(); 
        }
	}
	
	/**
	 * A function that users can change their target weight.
	 */
	private void ChangeTarget(String LoginNow){
		// change course file
		//int RechargeNum = 0;
		//int result = 0;
		int i = 0;
		String info = "";
		ArrayList<String> dataList=new ArrayList<String>();
		
		
		for (String s : CSVUtil.importCsv(new File("./userdata/account.csv"))){
			String[] temp = s.split(",");
			//RechargeNum = Integer.valueOf(NofM);
			if(temp[0].equals(LoginNow)){
				temp[8] = weight_target_input;
				for (i=0;i<temp.length;i++) {
					info = info + temp[i] +",";
				}
				dataList.add(info);
				 
			}else{
				dataList.add(s);
			}
		}
		// save
		DeleteFileUtil.deleteFile("./userdata/account.csv");
		CSVUtil.exportNewCsv(new File("./userdata/account.csv"), dataList);
	}
	
	/**
	 * A function that users can change their weight now.
	 */
	private void ChangeNow(String LoginNow){
		// change course file
		//int RechargeNum = 0;
		//int result = 0;
		int i = 0;
		String info = "";
		ArrayList<String> dataList=new ArrayList<String>();
		
		
		for (String s : CSVUtil.importCsv(new File("./userdata/account.csv"))){
			String[] temp = s.split(",");
			//RechargeNum = Integer.valueOf(NofM);
			if(temp[0].equals(LoginNow)){
				temp[9] = weight_now_input;
				for (i=0;i<temp.length;i++) {
					info = info + temp[i] +",";
				}
				dataList.add(info);
				 
			}else{
				dataList.add(s);
			}
		}
		// save
		DeleteFileUtil.deleteFile("./userdata/account.csv");
		CSVUtil.exportNewCsv(new File("./userdata/account.csv"), dataList);
	}
	
	
}
