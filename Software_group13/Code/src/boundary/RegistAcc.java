package boundary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import javax.swing.*;

import controller.UI;

import java.awt.*;
import java.util.Date;
import java.util.Random;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;

/**
 * Class {@code RegistAcc} can genegrate a Register Page of 
 * the visual gym system. In this register page, users can 
 * regist an account with username / password / telephone
 * and customer type ( Normal or Coach)
 * And two buttons Back and Done
 * They link to homepage and confirmation interface
 * A random UserID will be generated and showed
 * @author Weirui Sun, Zhexu Liu
 * @version 1.0
 * @since 2020/5/31
 */
public class RegistAcc {
	
	File file = new File("userdata/account.csv");
	Path PATHtemp = file.toPath();
	String PATH = PATHtemp.toString();
	
	private static String DIGIT_REGEX = "[0-9]+";
	private String randomname;
	private String password;
	private String telephones;
	private String name;
	private String type;
	private String passcheck;
	public RegistAcc(){
		initRegistAcc();
	}
	
	public void initRegistAcc() {
		//UI y= new UI();
		//y.setVise(false);
		JDialog register = new JDialog();									//initialize regist part
		register.setTitle("register");
		register.setBounds(0,0,1080,720);									//set bound
		register.getContentPane().setLayout(null);
		Container c = register.getContentPane();							//container
		JLabel title = new JLabel("Welcome to regist Virtual Gym!");
		title.setFont(new Font("Times New Roman",Font.PLAIN,36));
		//JLabel picture2=new JLabel(new ImageIcon("img/logo.png"));
		//JLabel picture2=new JLabel(new ImageIcon(PATHb));
		JLabel getusername = new JLabel("type in your username");			//set lables 
		getusername.setFont(new Font("Times New Roman",Font.PLAIN,24));
		JLabel getpassword = new JLabel("type in your password");
		getpassword.setFont(new Font("Times New Roman",Font.PLAIN,24));
		JLabel checkpassword = new JLabel("type in your password again");
		checkpassword.setFont(new Font("Times New Roman",Font.PLAIN,24));
		JLabel telephone = new JLabel("type in your telephone");
		telephone.setFont(new Font("Times New Roman",Font.PLAIN,24));
		JLabel customer = new JLabel("choose customer type");
		customer.setFont(new Font("Times New Roman",Font.PLAIN,24));
		JTextField setusername=new JTextField(20);
		TextField setpassword=new TextField(20);							//set the input text box
		setpassword.setEchoChar('*');
		TextField setcheckpassword=new TextField(20);
		setcheckpassword.setEchoChar('*');
		JTextField settelephone=new JTextField(20);
		JButton done = new JButton("Done");
		ButtonGroup group = new ButtonGroup();
		JRadioButton choice1=new JRadioButton("Normal",true);
		choice1.setFont(new Font("Times New Roman",Font.PLAIN,24));			//set type face
		JRadioButton choice2=new JRadioButton("Coach");
		choice2.setFont(new Font("Times New Roman",Font.PLAIN,24));
		JButton backbutton = new JButton("Back");
		File fileR = new File("./img/sun.jpg");
		Path PATHtempR = fileR.toPath();									//set path 
		String PATHR = PATHtempR.toString();
		JLabel picture_reg=new JLabel(new ImageIcon(PATHR));
		
		
		c.add(title);														//add components 
		//c.add(picture2);
		c.add(getusername);
		c.add(getpassword);
		c.add(checkpassword);
		c.add(telephone);
		c.add(customer);
		c.add(setusername);
		c.add(setpassword);
		c.add(setcheckpassword);
		c.add(settelephone);
		c.add(done);
		group.add(choice1);
		group.add(choice2);
		c.add(choice1);
		c.add(choice2);
		c.add(backbutton);
		c.add(picture_reg);
		
		title.setBounds(300,32,500,50);									//set bounds
		//picture2.setBounds(100,50,145,138);
		//picture2.setBounds(100,50,150,150);
		getusername.setBounds(160,114,300,50);
		setusername.setBounds(620,114,300,50);
		getpassword.setBounds(160,196,300,50);
		setpassword.setBounds(620,196,300,50);
		checkpassword.setBounds(160,268,300,50);
		setcheckpassword.setBounds(620,268,300,50);
		telephone.setBounds(160,340,300,50);
		settelephone.setBounds(620,340,300,50);
		customer.setBounds(160,412,300,50);
		choice1.setBounds(620,464,300,50);
		choice2.setBounds(620,500,300,50);
		done.setBounds(500,560,100,50);
		backbutton.setBounds(400,560,100,50);
		picture_reg.setBounds(0,0,1080,720);
		
		done.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {						//action listener of done botton
				name=setusername.getText();
				password=setpassword.getText();
				passcheck=setcheckpassword.getText();
				telephones=settelephone.getText();
				
		           for(Component m:c.getComponents()){
	                    if(m instanceof JRadioButton){
	                        if(((JRadioButton) m).isSelected()){
	                            type = ((JRadioButton)m).getText();
	                        }
	                    }
	                }
		           if(name.length()==0||password.length()==0||telephones.length()==0|| //check user input
		        		   (name.length()!=name.replace(" ","").length())||
		        		   (password.length()!=password.replace(" ","").length())||
		        		   (telephones.length()!=telephones.replace(" ","").length())||
		        		   (name.length()!=name.replace(",","").length())||
		        		   (password.length()!=password.replace(",","").length())||
		        		   (telephones.length()!=telephones.replace(",","").length())) {
		        	   
		        	   warnPage("Empty and , are not allowed!");
		           }
		           else if(!password.equals(passcheck)){								//check whether the password is same
		               warnPage("Passwords are not the same!");
		           }
 		           else if((!(telephones.length()==11))||(!telephones.matches(DIGIT_REGEX))) {
		        	   warnPage("Phone number must be 11 digits!");
		           }
		           else {
		           randomname=getRandString(6);

		           try {
			        	
			           
			            if(!file.exists()) {
			            	addFILE(randomname+",",PATH);								//check whether the file exist or it will create one
			            	addFILE(name+",",PATH);										//input user information
				            addFILE(password+",",PATH);
				            addFILE(telephones+",",PATH);
				            addFILE(type+",",PATH);
				            addFILE("0"+",",PATH);
				            addFILE(getSysDate()+",",PATH);
				            addFILE("0"+",",PATH);
				            addFILE("0"+",",PATH);
				            addFILE("0"+",",PATH);
				            addFILE("\n",PATH);
				            FlowtypePage("Your userID is "+randomname+" This is used for login in, please remember it!");
			            }
			            
			            else {
			            	BufferedReader in = new BufferedReader(new FileReader(PATH));
				            String str1;										// find the account.csv
				            String str2;
				            in.mark((int)PATH.length()+1);

			            	in.reset();
			            	if(in.readLine()==null){
		            		addFILE(randomname+",",PATH);							//input user information
			            	addFILE(name+",",PATH);
				            addFILE(password+",",PATH);
				            addFILE(telephones+",",PATH);
				            addFILE(type+",",PATH);
				            addFILE("0"+",",PATH);
				            addFILE(getSysDate()+",",PATH);
				            addFILE("0"+",",PATH);
				            addFILE("0"+",",PATH);
				            addFILE("0"+",",PATH);
				            addFILE("\n",PATH);
				            FlowtypePage("Your userID is "+randomname+" This is used for login in, please remember it!");
			            	in.close();
			            }
			            	
							FileInputStream checkcoach = new FileInputStream(PATH);
							BufferedReader cc = new BufferedReader(new InputStreamReader(checkcoach));
							int i=0;
							while((str1=cc.readLine())!=null){					//check whether the coach number is larger than 7
								String[] ccs = str1.split(",");
								 if(ccs[4].equals("Coach"))
									 i++;
							}
							cc.close();
							if(i>7&&type.equals("Coach")){
								FlowtypePage("Coach number larger than 7");				
								
							}
							
							if(i<=7||type.equals("Normal")||type.equals("Prime")){
							
			            while ((str2 = in.readLine()) != null) {
			            	//lines++;
			                String[] numPIN = str2.split(",");
			                if(randomname.equals(numPIN[0])) {
			                	randomname=getRandString(6);
			                	in.reset();
			                }
			            }
			            in.close();
			            addFILE(randomname+",",PATH);
			           
			            addFILE(name+",",PATH);
			            addFILE(password+",",PATH);						//input user information
			            addFILE(telephones+",",PATH);
			            addFILE(type+",",PATH);
			            addFILE("0"+",",PATH);
			            addFILE(getSysDate()+",",PATH);
			            addFILE("0"+",",PATH);
			            addFILE("0"+",",PATH);
			            addFILE("0"+",",PATH);
			            addFILE("\n",PATH);
			            FlowtypePage("Your userID is "+randomname+" This is used for login in, please remember it!");
			            
			            }
							
		           }
			       
			        } catch (IOException e2) {
			        	e2.printStackTrace();
			        }

			}


		           
		
			}
		});

		
	    backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.dispose();
				new UI();
			}
		});

		
//		register.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		register.setVisible(true);
//		register.dispose();
}
	
	/////////
	 public static String getRandString(int length)
	    {
	        String charList = "0123456789";
	        String rev = "";											//set the  user id
	        Random f = new SecureRandom();
	        for(int i=0;i<length;i++)
	        {
	           rev += charList.charAt(Math.abs(f.nextInt())%charList.length());
	        }
	        return rev;
	    }
	 
	 public String getSysDate() {
		 Date date = new Date();
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//set time 
		  String timeStr = df.format(date);
		  return timeStr;
	 }
	 
		public boolean addFILE(String string,String path) {

		    
		    FileWriter fw = null;
		    try {
		        fw = new FileWriter(path,true);							//set how to add information to the account.csv
		        
		        fw.write(string);
		      
		        fw.close();
		        return true;
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return false;
		}
		public void warnPage(String warnwords) {
			JDialog warn = new JDialog();
			warn.setTitle("warning");									
			warn.setSize(400, 200);
			warn.setBounds(300,200,600,300);							//set the warning to tell users
          warn.setLayout(null);
          JLabel warn1 = new JLabel(warnwords);
          warn1.setFont(new Font("Arial",Font.PLAIN,36));
          warn1.setBounds(50,0, 600, 240);
          warn.add(warn1);
          warn.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
          warn.setVisible(true);
          warn.dispose();
		}
		public void FlowtypePage(String warnwords) {
			JDialog warn = new JDialog();
			warn.setTitle("warning");
			warn.setBounds(300,200,1200,300);						//set the big information window
          warn.setLayout(new FlowLayout(FlowLayout.CENTER));
          JLabel warn1 = new JLabel(warnwords);
          warn1.setFont(new Font("Arial",Font.PLAIN,36));
          warn.add(warn1);
          warn.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
          warn.setVisible(true);
          warn.dispose();
		}
	//////////
	
}