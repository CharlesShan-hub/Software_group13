package controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import boundary.Admin_Del_Video;
import boundary.Admin_Look_Up_Info;
import boundary.Admin_Post_Activities;
import boundary.Admin_Upload_Video;
import boundary.Admin_Watch_Video;

import java.io.*;
import java.nio.file.Path;

/**
 * Class {@code CoachMyUI} is to view personal information. 
 * This module carries on the personal information reading, 
 * and has the logout button.
 *
 * @author Shiwei Ma
 * @version 1.0
 * @since 2020/5/30
 */
public class Admin_Mine {

	/* Announce Components */
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Admin_Mine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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

		//GUI
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(166,191,195));
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
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new UI();
			}
		});
		panel.add(logoutbutton);
		
		JButton toWatchBtn = new JButton("Watch");
		toWatchBtn.setBounds(100, 50, 149, 60);
		toWatchBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toWatchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Watch_Video();
			}
		});
		frame.getContentPane().add(toWatchBtn);
		
		JButton toUploadBtn = new JButton("Upload");
		toUploadBtn.setBounds(250, 50, 149, 60);
		toUploadBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toUploadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toUploadBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Upload_Video();
			}
		});
		frame.getContentPane().add(toUploadBtn);
		
		JButton toLookBtn = new JButton("Lookup");
		toLookBtn.setBounds(400, 50, 149, 60);
		toLookBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toLookBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Look_Up_Info();
			}
		});
		frame.getContentPane().add(toLookBtn);
		
		JButton toDelBtn = new JButton("Delete");
		toDelBtn.setBounds(550, 50, 149, 60);
		toDelBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toDelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Del_Video();
			}
		});
		frame.getContentPane().add(toDelBtn);
		
		JButton toPostBtn = new JButton("Post");
		toPostBtn.setBounds(700, 50, 149, 60);
		toPostBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		toPostBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new Admin_Post_Activities();
			}
		});
		frame.getContentPane().add(toPostBtn);
		
		JButton toMineBtn = new JButton("Mine");
		toMineBtn.setBounds(850, 40, 149, 70);
		toMineBtn.setFont(new Font("Arial", Font.PLAIN, 25));
		frame.getContentPane().add(toMineBtn);

		JLabel back_ground_label = new JLabel("");
		back_ground_label.setIgnoreRepaint(true);
		back_ground_label.setIcon(new ImageIcon("./img/back4.jpg"));
		back_ground_label.setBounds(0, 0, 1080, 720);
		frame.getContentPane().add(back_ground_label);
	}
}
