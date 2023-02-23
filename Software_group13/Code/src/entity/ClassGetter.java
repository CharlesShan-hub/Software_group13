package entity;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class {@code ClassGetter} ClassGetter is used to read 
 * CSV files and return content in a specific format. 
 * It is easy for programmers to read specific information.
 *
 * @author Hongtian Shan
 * @version 1.0
 * @since 2020/5/30
 */
public class ClassGetter{
	// variables declaration
		// List for all Lectures(String formated)
	public List<String> all_class;
		// List for all Lectures(list formated)
	public List<String[]> all_class_format = new ArrayList<String[]>();
		// List for all students
	public List<String> all_student = new ArrayList<String>();
		// List for students for a coach (by CooachID) (String formated)
	private List<String> real_all_student;
		// List for students for a coach (by CooachID) (list formated)
	public List<String[]> all_student_format = new ArrayList<String[]>();
		// List for video id that choosed for a student
	private List<String> choosed_class_id = new ArrayList<String>();
		// List for all video informaiton of video that choosed for a student
	public List<String[]> choosed_class = new ArrayList<String[]>();

	/** 
	 * Read the video information file and 
	 * return all course information.
	 * @return all_class: List for all Lectures(String formated)
	 */
	public List<String> getclass(){
		all_class =  CSVUtil.importCsv(new File("./userdata/uploadVideo.csv"));
		return all_class;
	}

	/** 
	 * Read the video information file and 
	 * return all course information.
	 * @return all_class_format: List for all Lectures(string list formated)
	 */
	public List<String[]> getformatclass(){
		all_class = getclass();
		for (String s : all_class){
			all_class_format.add(s.split(","));
		}
		return all_class_format;
	}

	/** 
	 * Read the video information file and 
	 * return all course id choosed by a student.
	 * @param coachID coach ID
	 * @param stuID student ID
	 * @return choosed_class_id: List for video id that choosed for a student
	 */
	public List<String> getchoosedclassID(String coachID, String stuID){
		for (String s : CSVUtil.importCsv(new File("./userdata/course.csv"))){
			String[] temp = s.split(",");
			if(temp[0].equals(coachID) && temp[1].equals(stuID)){
				choosed_class_id.add(temp[2]);
			}
		}
		return choosed_class_id;
	}

	/** 
	 * Read the video information file and 
	 * return all course information choosed by a student.
	 * @param coachID coach ID
	 * @param stuID student ID
	 * @return choosed_class_id: List for video information that choosed for a student
	 */
	public List<String[]> getchoosedclass(String coachID, String stuID){
		choosed_class_id = getchoosedclassID(coachID, stuID);
		all_class_format = getformatclass();
		for (String s : choosed_class_id){
			for(String[] j : all_class_format){
				if(s.equals(j[0])){
					choosed_class.add(j);
				}
			}
		}
		return choosed_class;
	}

	/** 
	 * Read the video information file and 
	 * return all students belong to a coach.
	 * @param coachID coach ID
	 * @return all_student: List for student info that choosed for a student
	 */
	public List<String> getstudent(String coachID){
		real_all_student =  CSVUtil.importCsv(new File("./userdata/account.csv"));
		for (String s : real_all_student){
			String[] temp = s.split(",");
			if(coachID.equals(temp[7])){
				all_student.add(s);
			}
		}
		return all_student;
	}

	/** 
	 * Read the video information file and 
	 * return all students info belong to a coach.
	 * @param coachID coach ID
	 * @return all_student_format: List for student info that choosed for a student(String List)
	 */
	public List<String[]> getformatstudent(String coachID){
		all_student = getstudent(coachID);
		for (String s : all_student){
			all_student_format.add(s.split(","));
		}
		return all_student_format;
	}

}