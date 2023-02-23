package boundary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Admin_Mine;

import java.io.*;
import java.nio.file.Path;

/**
 * Class {@code Admin_Post_Activities} can Post some special offers.
 * For example, member price concessions and so on.
 * 
 * @author   Shiwei Ma
 * @since    2020/5/30
 * @version  [1.0]
 */
public class Admin_Post_Activities {
	
	/* Announce Components */
	private JFrame frame;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public Admin_Post_Activities() {
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

		
		JLabel lblNewLabel = new JLabel("The price of members to premier members");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(125, 95, 385, 28);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(530, 95, 103, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String fee = textField.getText();
					FileWriter fw = new FileWriter("./userdata/discount.csv",true);
					fw.write(fee + ",");
					fw.close();
				} 
				catch (IOException ep) {
					ep.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(678, 95, 103, 28);
		panel.add(btnNewButton);
		
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
		toPostBtn.setBounds(700, 40, 149, 70);
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
