package controller;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import boundary.UserCheckProgressUI;
import boundary.UserSelectCoachUI;
import boundary.UserVideoUI;
import entity.CSVUtil;
import entity.DeleteFileUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;

/**
 * Class {@code UserInfomationUI} is to view personal information. 
 * This module carries on the personal information reading, 
 * and has the logout button.
 *
 * @author Youguang Zhou
 * @version 1.0
 * @since 2020/5/30
 */
public class UserInfomationUI extends JFrame {

	/* Announce Components */
	private JFrame frame;
	private JTextField textField;
	
	/* Component - loginID now */
	String lastID = null;
	/* Component - Recharge amount */
	String NofM = "";
	/* Component - Balance */
	String user_balance = null;
	/* Component - User type */	
	String user_type = null;

	/**
	 * Create the application.
	 */
	public UserInfomationUI() {
		intiuserinfomation();
	}
	
	/**
	 * Create the frame.
	 */
	private void intiuserinfomation() {
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
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1080, 720);

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
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserCheckProgressUI();
			}
		});
		btnNewButton_1_1.setBounds(438, 50, 159, 66);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Personal information");
		btnNewButton_1_2.setBounds(607, 40, 159, 76);
		frame.getContentPane().add(btnNewButton_1_2);
		
		
		panel.setBackground(new Color(205, 205, 190));
		panel.setBounds(100, 114, 880, 545);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(56, 80, 114, 46);
		panel.add(lblNewLabel);
		
		JLabel lblUserid = new JLabel("Registration date");
		lblUserid.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUserid.setBounds(56, 204, 175, 46);
		panel.add(lblUserid);
		
		JLabel lblUserid_1 = new JLabel("UserID");
		lblUserid_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUserid_1.setBounds(56, 148, 71, 46);
		panel.add(lblUserid_1);
		
		JLabel lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPhoneNumber.setBounds(56, 273, 175, 46);
		panel.add(lblPhoneNumber);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBalance.setBounds(56, 341, 93, 46);
		panel.add(lblBalance);
		
		JLabel lblUsertype = new JLabel("Usertype");
		lblUsertype.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUsertype.setBounds(56, 400, 93, 46);
		panel.add(lblUsertype);
		
		JButton btnNewButton_2 = new JButton("To become VIP");
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RewriteType(lastID);
				// refresh
				frame.dispose();
				new UserInfomationUI();
			}
		});
		btnNewButton_2.setBounds(581, 395, 195, 29);
		panel.add(btnNewButton_2);
		if(user_type.equals("Prime")){
			btnNewButton_2.setVisible(false);
		}
		
		JButton btnNewButton_2_1 = new JButton("Recharge");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NofM = textField.getText();
				RechargeMoney(lastID);
				// refresh
				frame.dispose();
				new UserInfomationUI();
			}
		});
		btnNewButton_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_2_1.setBounds(581, 148, 168, 29);
		panel.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Recharge amount");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setBounds(581, 48, 168, 40);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(591, 97, 142, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNewLabel_2 = new JLabel(name);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(319, 80, 168, 46);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(id);
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(319, 148, 168, 46);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(register_date);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(319, 204, 168, 46);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(phone);
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(319, 273, 168, 46);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(user_balance);
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(319, 341, 168, 46);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(user_type);
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(319, 400, 168, 46);
		panel.add(lblNewLabel_7);

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
	 * A function that users can recharge the balance.
	 */
	private void RechargeMoney(String LoginNow){
		// change course file
		int RechargeNum = 0;
		int result = 0;
		int i = 0;
		String info = "";
		List<String> dataList=new ArrayList<String>();
		
		
		for (String s : CSVUtil.importCsv(new File("./userdata/account.csv"))){
			String[] temp = s.split(",");
			RechargeNum = Integer.valueOf(NofM);
			if(temp[0].equals(LoginNow)){
				result = Integer.valueOf(temp[5]) + RechargeNum;
				temp[5] = String.valueOf(result);
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
	 * A function that users can upgrade into Prime.
	 */
	private void RewriteType(String loginNow){
		// change course file
		int NeedMoney = 0;
		int result = 0;
		String VIPcost = "";
		int i = 0;
		String info = "";
		List<String> dataList=new ArrayList<String>();
		
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader("./userdata/discount.csv"));
			while((VIPcost=reader1.readLine())!=null){
                String getcost[] = VIPcost.split(",");
                for (i=0;i<getcost.length;i++){
                    NeedMoney = Integer.valueOf(getcost[getcost.length-1]);
                }
            }
			reader1.close();
		}catch (Exception e) { 
            e.printStackTrace(); 
        }
		
		
			for (String s : CSVUtil.importCsv(new File("./userdata/account.csv"))){
				String[] temp = s.split(",");
				if(temp[0].equals(loginNow)  ){
					if (temp[4].equals("Normal")) {
						if(Integer.valueOf(temp[5]) > NeedMoney) {
							temp[4] = "Prime";
							result = Integer.valueOf(temp[5]) - NeedMoney;
							temp[5] = String.valueOf(result);
							for (i=0;i<temp.length;i++) {
								info = info + temp[i] + ",";
							}
							dataList.add(info);
						}else {
							System.out.print("Please Recharge!");
							dataList.add(s);
						}
					}else {
						System.out.print("You have already been a Prime!");
						dataList.add(s);
					}
					
				}else{
					dataList.add(s);
				}
			}
		
		
		// save
		DeleteFileUtil.deleteFile("./userdata/account.csv");
		CSVUtil.exportNewCsv(new File("./userdata/account.csv"), dataList);
	}
}
