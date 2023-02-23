package boundary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;

import javax.swing.*;

import controller.UI;

import java.awt.*;
import java.util.Random;
import java.security.SecureRandom;

/**
 * Class {@code LoginAcc} can genegrate a Login Page of 
 * the visual gym system. In this register page, users can 
 * login an account with UserID and password
 * And two buttons Back and Done
 * They link to homepage and function interfaces
 * @author Weirui Sun, Zhexu Liu
 * @version 1.0
 * @since 2020/5/31
 */
public class LoginAcc {
	
	JFrame loginer = new JFrame();							//container
	public String ID = "";									//initiate ID
	private File file = new File("./userdata/account.csv");	//file path
	private Path PATHtemp = file.toPath();
	private String PATH = PATHtemp.toString();
	private File file1 = new File("./userdata/logid.csv");
	private Path PATHtemp1 = file1.toPath();
	private String PATH1 = PATHtemp1.toString();

	public LoginAcc(){										//initiateAcc
		initLoginAcc();
	}

	public void initLoginAcc() {							
		loginer.setTitle("loginer");						//setTitle
		loginer.setBounds(0,0,1080,720);					//setbounds
		loginer.setLayout(null);					
		loginer.setVisible(true);							//set visable
		Container c = loginer.getContentPane();				//container
		JLabel title = new JLabel("Welcome to log in Virtual Gym!");//set title
		title.setBounds(280,20,500,50);
		title.setFont(new Font("Times New Roman",Font.PLAIN,36));//set typeface
		JLabel getuserID = new JLabel("user ID");					
		JLabel getpassword = new JLabel("password");
		JTextField setuserID=new JTextField(20);				//set userid
		TextField setpassword=new TextField(20);				//set password
		setpassword.setEchoChar('*');
		JLabel jl3=new JLabel(new ImageIcon("img/logo.png"));	
		JButton logbutton = new JButton("Log in");
		JButton backbutton = new JButton("Back");
		File fileb = new File("./img/sun.jpg");
		Path PATHtempb = fileb.toPath();						//set path
		String PATHb = PATHtempb.toString();
		JLabel picture_log=new JLabel(new ImageIcon(PATHb));
			
		c.add(title);											//add components
		c.add(getuserID);
		c.add(getpassword);
		c.add(setuserID);
		c.add(setpassword);
		c.add(jl3);
		c.add(logbutton);
		c.add(backbutton);
		c.add(picture_log);

		
		jl3.setBounds(300,100,400,175);							//set bounds
		getuserID.setBounds(460, 290, 400, 50);
		getuserID.setFont(new Font("Arial",Font.PLAIN,24));
		setuserID.setBounds(360, 350, 300, 50);
		setuserID.setFont(new Font("Arial",Font.PLAIN,24));
		getpassword.setBounds(450, 410, 400, 50);
		getpassword.setFont(new Font("Arial",Font.PLAIN,24));
		setpassword.setBounds(360, 470, 300, 50);
		setpassword.setFont(new Font("Arial",Font.PLAIN,24));
		picture_log.setBounds(0,0,1080,720);
		
	    logbutton.setBounds(505, 560, 100, 50);
	    logbutton.addActionListener(new ActionListener() {		//login button actionlistener
			public void actionPerformed(ActionEvent e) {
				try {
					if(readFile(PATH,setuserID.getText(),setpassword.getText())) {
						addFILE(setuserID.getText()+",",PATH1);
						addFILE(readCerPart(PATH,setuserID.getText(),4),PATH1);//add ID into loginID.csv 
						addFILE("\n",PATH1);
						loginer.dispose();

						//MSW write
						String line = null;
						String user_type = null;						//find account.csv
						FileInputStream inputStream1 = new FileInputStream(PATH);
						BufferedReader br1 = new BufferedReader(new InputStreamReader(inputStream1));
						while((line=br1.readLine())!=null){
							String[] subArray = line.split(",");
							if(setuserID.getText().equals(subArray[0])) {//set the user_type
								user_type=subArray[4];
								break;
							}
						}
						inputStream1.close();
						br1.close();

						System.out.println(user_type);						
						if(user_type.equals("Admin")){
							loginer.dispose();
							new Admin_Watch_Video();				//go to admin UI
						}else if(user_type.equals("Coach")){
							String x = setuserID.getText();
							loginer.dispose();
							new CoachVideoUI(x);					//go to coach UI
						}else{
							loginer.dispose();						//go to user UI
							new UserVideoUI();
						}
					}
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});

		backbutton.setBounds(405, 560, 100, 50);					//set back button  
	    backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginer.dispose();
				new UI();
			}
		});
		
	}
	public boolean addFILE(String string,String path) {// the add file method

	    
	    FileWriter fw = null;
	    try {
	        fw = new FileWriter(path,true);				//file writer
	        
	        fw.write(string);
	      
	        fw.close();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	 public boolean readFile(String path,String ID,String password) throws IOException {

		  
		  boolean flag = false;
		  
		  FileInputStream inputStream = new FileInputStream(path);			//open account.csv
		  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		  String str = null;
		  int checki=0;
		  int lengthi=0;
		  while((str = bufferedReader.readLine()) != null)
		  {
		   lengthi++;
		   String[] subArray = str.split(",");
		   if(ID.equals(subArray[0])&& password.equals(subArray[2])) {    //check when ID and password is right
		    flag=true;
		    checki=0;
		    break;
		   }
		   
		   if(ID.equals(subArray[0])){
		    if(!password.equals(subArray[2])){
		    FlowtypePage("id or password false!");						//check password is false
		    break;
		    }}
		   
		   if(!ID.equals(subArray[0])){
		    checki++;
		    
		    }
		   
		   
		  }
		   if(checki==lengthi){
		     FlowtypePage("id false or the account not exist!");      //check ID is false
		    }
		  //close
		  inputStream.close();
		  bufferedReader.close();
		  return flag;
		 }
	public String readCerPart(String path,String ID,int i) throws IOException {

		String a="No Such ID!";
		
		FileInputStream inputStream = new FileInputStream(path);							
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
		String str = null;
		while((str = bufferedReader.readLine()) != null)
		{
			String[] subArray = str.split(","); //read all lines
			if(ID.equals(subArray[0])) {
				a=subArray[i];
				break;
			}
		}
			
		//close
		inputStream.close();
		bufferedReader.close();
		return a;
	}
	public void FlowtypePage(String warnwords) {  //show a big message
		JDialog warn = new JDialog();
		warn.setTitle("warning");
		warn.setBounds(200,100,1200,300);
      warn.setLayout(new FlowLayout(FlowLayout.CENTER));
      JLabel warn1 = new JLabel(warnwords);
      warn1.setFont(new Font("Arial",Font.PLAIN,36));
      warn.add(warn1);
      warn.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
      warn.setVisible(true);
      warn.dispose();
	}
	
}
