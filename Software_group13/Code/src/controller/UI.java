package controller;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import boundary.LoginAcc;
import boundary.RegistAcc;
import entity.OpenVideoTool;

import java.awt.Color;

import java.io.*;

/**
 * Class {@code UI} can genegrate a homepage of the visual gym 
 * system. In this homepage, three buttons are provided
 * Regist / Log in / About
 * These buttons link to Register UI, Login UI and 
 * the publicity video
 * @author Weirui Sun, Zhexu Liu
 * @version 1.0
 * @since 2020/5/31
 */
@SuppressWarnings("serial")
public class UI extends JFrame{

	/**
	 * Launch the application.
     * @param   args       [no parameter]
	 */
	public static void main(String[] args) {
		new UI();
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		/**
		 * Initialize interface start.
		 */ 
		
		JFrame frame = new JFrame();					//Initialize JFrame
		frame.setBackground(new Color(240, 240, 240));	//set colour
		frame.setTitle(" Menu");						//Set frame title
		frame.getContentPane().setLayout(null);			//Cancel layout manager settings
		frame.setBounds(0,0,1080,720);					//set bound
		Container c = frame.getContentPane();			//container
		JButton regist = new JButton("Regist");			//set buttons
		JButton login = new JButton("Log in");
		JButton temp1 = new JButton("About");
		File fileb = new File("./img/valley.jpg");		//set the file path 
		Path PATHtempb = fileb.toPath();
		String PATHb = PATHtempb.toString();
		JLabel picture1=new JLabel(new ImageIcon(PATHb));
		

		c.add(login);									//set the buttons
		c.add(regist);
		c.add(temp1);
		c.add(picture1);
		
		regist.setBounds(176,20,200,50);				//set bounds
		login.setBounds(432,20,200,50);
		temp1.setBounds(688,20,200,50);
		picture1.setBounds(0,0,1080,720);
		
		frame.setVisible(true);							//set visible
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//regist
		regist.addActionListener(new ActionListener() {//regist button action listener
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new RegistAcc();
			}
		});
		
		
		login.addActionListener(new ActionListener() {//login button action listener
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginAcc();
			}
		});

		temp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenVideoTool.openExe("./img/gym.mp4");
			}
		});
		
	}

}
