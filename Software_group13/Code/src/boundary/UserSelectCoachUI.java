package boundary;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.UserInfomationUI;
import entity.CSVUtil;
import entity.DeleteFileUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;


/**
	 * Class {@code UserSelectCoach} According to the registered coach information 
	 * and the number of generated related buttons,
	 * let the user choose a unique coach or not.
	 * After selecting a certain coach,
	 * the coach's information will be written into the user's relevant information.
	 *
	 * @author Yueyan Cao
	 * @version 1.0
	 * @since 2020/5/30
	 */
public class UserSelectCoachUI extends JFrame {

	/* Announce Components */
	private JFrame frame;
	public JRadioButton selectButton;
	private JPanel contentPane;
	public ArrayList<String> clist=new ArrayList<>();
	public List<String> dataList=new ArrayList<String>();
	public JRadioButton[] one;
	public String sta="";

	/**
	 * Create the application.
	 */
	public UserSelectCoachUI() {
	
		/* Announce Components */
		frame = new JFrame();
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

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
		btnNewButton_1.setBounds(269, 40, 159, 76);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Check progress");
		btnNewButton_1_1.setBounds(438, 50, 159, 66);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserCheckProgressUI();
			}
		});
		frame.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("Personal information");
		btnNewButton_1_2.setBounds(607, 50, 159, 66);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new UserInfomationUI();
			}
		});
		frame.getContentPane().add(btnNewButton_1_2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(205, 205, 190));
		panel.setBounds(100, 114, 880, 545);
		//contentPane.add(panel);
		panel.setLayout(null);
		frame.add(panel);

		JButton btnNewButton_2 = new JButton("choose");
		btnNewButton_2.setBounds(314, 446, 162, 43);
		panel.add(btnNewButton_2);

		JLabel back_ground_label = new JLabel("");
		back_ground_label.setIgnoreRepaint(true);
		back_ground_label.setIcon(new ImageIcon("./img/back3.jpg"));
		back_ground_label.setBounds(0, 0, 1080, 720);
		frame.getContentPane().add(back_ground_label);

		getCoach();
		JRadioButton[] one=new JRadioButton[clist.size()];
		//Realize the function of single selection
		ButtonGroup buttonGroup=new ButtonGroup();

		List<String> statue=new ArrayList<String>();

		for (int i = 0; i <clist.size() ; i++) {

			one[i]=new JRadioButton(clist.get(i));
			one[i].setBounds(160,60+(i)*60,440,50);

			buttonGroup.add(one[i]);
			panel.add(one[i]);
		}


		//Add monitoring for the generated button 
		if(0<one.length){
			changeStatue(0);

			one[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					System.out.println("sssssssssssssssss1");
					sta="yes0";
					btnNewButton_2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							System.out.println(sta);
							if(sta.equals("yes0")){
								RewriteType(dataList.get(0));
							}
						}
					});

				}
			});
		}

		//Add monitoring for the generated button 
		if(1<one.length){
			changeStatue(1);
			one[1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					System.out.println("shuhusssssss2");
					sta="yes1";
					btnNewButton_2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							System.out.println(sta);
							if(sta.equals("yes1")){
								RewriteType(dataList.get(1));

							}
						}
					});
				}
			});
		}

		//Add monitoring for the generated button 
		if(2<one.length){
			changeStatue(2);
			one[2].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					//String needID=dataList.get();
					String s="yes";
					System.out.println(dataList.size());
					System.out.println(dataList.get(2));
					sta="yes2";
					btnNewButton_2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							if(sta.equals("yes2")){
								RewriteType(dataList.get(2));
							}
						}
					});
				}
			});
		}

		//Add monitoring for the generated button 
		if(3<one.length){
			changeStatue(3);
			one[3].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					sta="yes3";
					btnNewButton_2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							if(sta.equals("yes3")){
								RewriteType(dataList.get(3));
							}
						}
					});
				}
			});
		}

		//Add monitoring for the generated button 
		if(4<one.length){
			changeStatue(4);
			one[4].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					sta="yes4";
					btnNewButton_2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							if(sta.equals("yes4")){
								RewriteType(dataList.get(4));
							}
						}
					});
				}
			});
		}

		//Add monitoring for the generated button 
		if(5<one.length){
			changeStatue(5);
			one[5].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					sta="yes5";
					btnNewButton_2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							if(sta.equals("yes5")){
								RewriteType(dataList.get(5));
							}
						}
					});
				}
			});
		}

		//Add monitoring for the generated button 
		if(6<one.length){
			changeStatue(6);
			one[6].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					sta="yes6";
					btnNewButton_2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							if(sta.equals("yes6")){
								RewriteType(dataList.get(6));
							}
						}
					});
				}
			});
		}

		//Add monitoring for the generated button 
		if(7<one.length){
			changeStatue(7);
			one[7].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					sta="yes7";
					btnNewButton_2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent actionEvent) {
							if(sta.equals("yes7")){
								RewriteType(dataList.get(7));
							}
						}
					});
				}
			});
		}


	}

/**
 * This method can change the statue of the current button
 * @param    t  current button serial number
 */
public void changeStatue(int t){
		sta="yes"+t;
}

/**
 * This method can get coach information
 */
public void getCoach(){
	String coachInfo="";
	int coachNum = 0;
	int result = 0;
	String message="";
	String judgeCoach = "";
	int i = 0;
	String info = "";

	try {
		BufferedReader reader1 = new BufferedReader(new FileReader("./userdata/account.csv"));
		while((message=reader1.readLine())!=null){
			String judge[] = message.split(",");
			if(judge[4].equals("Coach")){
				coachInfo="Coach Nme:  "+judge[1]+"                                                TeleNum:  "+judge[3];
				coachNum++;
				clist.add(coachInfo);
				dataList.add(judge[0]);
			}
		}

		reader1.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
}

/**
 * This method can get current ID number of the user
 */
public String currentID(){
	String lastID="";
	String line = null;

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


		inputStream2.close();


	} catch (IOException e1) {

		e1.printStackTrace();
	}
	return lastID;
}

	/**
	 * This method can write coach information into the current user's relevant information row
	 *@param     cID   ID number of coach 
	 */
	private void RewriteType(String cID){
		// change course file
		List<String> changeList=new ArrayList<String>();
		String userId=currentID();
		String newInfo="";

		for (String s : CSVUtil.importCsv(new File("./userdata/account.csv"))){
			String[] temp = s.split(",");
			if(temp[0].equals(userId) ){
				for (int j = 0; j <temp.length ; j++) {
					if(j==7){
						newInfo=newInfo+cID+",";
					}else{
						newInfo=newInfo+temp[j]+",";
					}
				}
			  changeList.add(newInfo);
			}else{
				changeList.add(s);
			}

		}

		DeleteFileUtil.deleteFile("./userdata/account.csv");//delete files with outdated information
		CSVUtil.exportNewCsv(new File("./userdata/account.csv"), changeList);//generate new file
	}


}

