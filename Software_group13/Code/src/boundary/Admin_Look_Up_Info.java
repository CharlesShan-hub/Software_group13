package boundary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Admin_Mine;

import java.io.*;
import java.nio.file.Path;

/**
 * Class {@code Admin_Look_Up_Info} is used to look up
 * all users' information including Normal, Prime, 
 * Coach and Administrator.
 *
 * @author Shiwei Ma
 * @version 1.0
 * @since 2020/5/30
 */
public class Admin_Look_Up_Info {

	/* Announce Components */
	private JFrame frame;
	private JTable table;

	/**
	 * Create the application.
	 */
	public Admin_Look_Up_Info() {
		initialize();
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(166,191,195));
		panel.setBounds(100, 106, 900, 545);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea textArea= new JTextArea();
		textArea.setBounds(87, 49, 718, 392);
		textArea.setEditable(false);
		textArea.setVisible(true);
		panel.add(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(50, 50, 800, 400);
		panel.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea.append("ID\tName\tPassword\tPhone\tUser Type\tBalance\tRegister Date\t\tHis coach\tGoal(KG)\tPhysical(KG)\n");

		File file = new File("./userdata/account.csv");
		Path PATHtemp = file.toPath();
		String PATH = PATHtemp.toString();
		String line = null;
		try {
			FileInputStream inputStream1 = new FileInputStream(PATH);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(inputStream1));
			while((line=br1.readLine())!=null){
				String[] item = line.split(",");			
				for(int i=0; i<item.length; i++){
			    textArea.append(item[i]+"\t");
		  	}
		  	textArea.append("\n");
			}
			inputStream1.close();
			br1.close();

		} catch (IOException e1) {

				e1.printStackTrace();
		}


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
		toLookBtn.setBounds(400, 40, 149, 70);
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
}
