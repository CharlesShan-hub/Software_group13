package entity;
import java.io.*;

/**
 * Class {@code OpenVideoTool} is to open .mp4 files
 *
 * @author Hongtian Shan
 * @version 1.0
 * @since 2020/5/31
 */
public class OpenVideoTool{
	/**
	 * Open Video Function
	 */
	public static void openExe(int num) {
		Runtime rn=Runtime.getRuntime();   
		Process p= null; 
		File file = new File("./Video/"+num+"/index.mp4");
		try {   
		        if(System.getProperty("os.name").equals("Mac OS X")){
		                p = rn.exec( "open "+"./Video/"+num+"/index.mp4"); 
		        }else{
		                Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start "+file);
		        }
		        InputStream   in   =p.getInputStream();   
		        BufferedReader   br   =   new   BufferedReader(new   InputStreamReader(in));   
		        String   str   =   null;   
		        while((str=br.readLine())!= null){   
		                System.out.println(str);   
		        }   
		        br.close();   
		}catch(Exception   e)   {   
		        System.out.println( "Error   exec   notepad ");   
		}   
    }

    /**
	 * Open Video Function
	 */
    public static void openExe(String path) {
		Runtime rn=Runtime.getRuntime();   
		Process p= null; 
		File file = new File(path);
		try {   
		        if(System.getProperty("os.name").equals("Mac OS X")){
		                p = rn.exec( "open "+path); 
		        }else{
		                Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start "+file);
		        }
		        InputStream   in   =p.getInputStream();   
		        BufferedReader   br   =   new   BufferedReader(new   InputStreamReader(in));   
		        String   str   =   null;   
		        while((str=br.readLine())!= null){   
		                System.out.println(str);   
		        }   
		        br.close();   
		}catch(Exception   e)   {   
		        System.out.println( "Error   exec   notepad ");   
		}    
    }

}