package TDD;

import java.awt.Dialog;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class TDD_Regi {

//	private String password;
//	private String telephones;
//	private String name;
//	private String passcheck;
//	
//	public TDD_Regi(String password,String passcheck,String telephones,String name) {
//		this.password= password;
//		this.passcheck= passcheck;
//		this.telephones= telephones;
//		this.name= name;
//		}
//	
//	public String getPassword() {
//		return this.password;
//		}
	
	public void checkPassword(String password,String passcheck,String telephones,String name) {
	if(name.length()==0||password.length()==0||telephones.length()==0||
 		   (name.length()!=name.replace(" ","").length())||
 		   (password.length()!=password.replace(" ","").length())||
 		   (telephones.length()!=telephones.replace(" ","").length())||
 		   (name.length()!=name.replace(",","").length())||
 		   (password.length()!=password.replace(",","").length())||
 		   (telephones.length()!=telephones.replace(",","").length())) {
 	   
 	   System.out.println("Empty and , are not allowed!");
    }
    else if(!password.equals(passcheck)){
    	System.out.println("Passwords are not the same!");
    }
    else {
    	System.out.println("Everything is OK!");
    }
}

}