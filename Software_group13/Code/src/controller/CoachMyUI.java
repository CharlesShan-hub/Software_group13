package controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import boundary.CoachManageUI;
import boundary.CoachTeachUI;
import boundary.CoachUploadUI;
import boundary.CoachVideoUI;

import java.io.*;
import java.nio.file.Path;

/**
 * Class {@code CoachMyUI} is to view personal information. 
 * This module carries on the personal information reading, 
 * and has the logout button.
 *
 * @author Hongtian Shan
 * @version 1.0
 * @since 2020/5/30
 */
public class CoachMyUI {

	private JFrame frame;

	// Var
	private String coachID="";
	
	/**
	 * Create the application. - Constructor(without CoachID just for test)
	 */
	public CoachMyUI() {
		System.out.println("Warning! Do not have User ID!!");
		initialize();
	}

	/**
	 * Create the application. - Constructor(with CoachID)
	 * @param coachID coach ID
	 */
	public CoachMyUI(String coachID) {
		this.coachID = coachID;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * The interface layout and various click 
	 * functions are specified.
	 */
	private void initialize() {
		//Function
		//read file
		String line = null;
		String lastID = null;
		String name = null;
		String id = null;
		String register_date = null;
		String phone = null;
		String user_type = null;
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
		    	register_date = item[6];
		    	phone = item[3];
		    	user_type = item[4];
				}
			}
			inputStream2.close();
			br2.close();			

		} catch (IOException e1) {

				e1.printStackTrace();
		}



		//UI
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(100, 106, 900, 545);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(123, 79, 91, 34);
		panel.add(lblNewLabel);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Arial", Font.PLAIN, 20));
		lblId.setBounds(123, 155, 91, 34);
		panel.add(lblId);
		
		JLabel lblRegisterDate = new JLabel("Register Date");
		lblRegisterDate.setFont(new Font("Arial", Font.PLAIN, 20));
		lblRegisterDate.setBounds(123, 225, 123, 34);
		panel.add(lblRegisterDate);
		
		JLabel lblPhoneNum = new JLabel("Phone");
		lblPhoneNum.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPhoneNum.setBounds(123, 297, 91, 34);
		panel.add(lblPhoneNum);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUserType.setBounds(123, 374, 101, 34);
		panel.add(lblUserType);
		
		JLabel lblNewLabel_1 = new JLabel(name);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(282, 79, 91, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(id);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(282, 155, 91, 34);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(register_date);
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(282, 225, 150, 34);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(phone);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(282, 297, 150, 34);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(user_type);
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(282, 374, 91, 34);
		panel.add(lblNewLabel_5);

		JButton logoutbutton = new JButton("Log Out");
		logoutbutton.setFont(new Font("Arial", Font.PLAIN, 20));
		logoutbutton.setBounds(400, 454, 110, 34);
		logoutbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				new UI();
			}
		});
		panel.add(logoutbutton);
		
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
		//toMyButtom.setBounds(740, 50, 159, 66);
		toMyButtom.setBounds(740, 40, 159, 76);
		frame.getContentPane().add(toMyButtom);
		
		JLabel back_ground_label = new JLabel("");
		back_ground_label.setIgnoreRepaint(true);
		back_ground_label.setIcon(new ImageIcon("./img/back2.jpg"));
		back_ground_label.setBounds(0, 0, 1080, 720);
		frame.getContentPane().add(back_ground_label);
	}
}
