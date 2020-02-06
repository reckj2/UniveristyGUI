package org.university.hardware;

import java.util.ArrayList;
import java.io.Serializable;

//import org.university.people.Person;
import org.university.people.Professor;
import org.university.people.Staff;
import org.university.people.Student;
import org.university.software.CampusCourse;
//import org.university.software.Course;
import org.university.software.OnlineCourse;

public class Department implements Serializable{
	private String Name;
	//private ArrayList<Course> courselist;
	private ArrayList<CampusCourse> campuscourselist;
	private ArrayList<OnlineCourse> onlinecourselist;
	private ArrayList<Student> roster;
	private ArrayList<Professor> professorlist;
	private ArrayList<Staff> stafflist;
	//private University university;
	
	
	
	public Department() {
		Name = "Unknown";
		//courselist = new ArrayList<Course>();
		roster = new ArrayList<Student>();
		professorlist = new ArrayList<Professor>();
		stafflist = new ArrayList<Staff>();
		campuscourselist = new ArrayList<CampusCourse>();
		onlinecourselist = new ArrayList<OnlineCourse>();
	}
	
	
	public Department(String newName) {
		Name = newName;
	}
	
	public String getDepartmentName() {
		return Name;
	}
	public void setDepartmentName(String name) {
		Name = name;
	}
	
	public void addCourse(CampusCourse course) {
		this.campuscourselist.add(course);
		course.setDepartment(this);
	}
	
	public void addCourse(OnlineCourse c1) {
		this.onlinecourselist.add(c1);
		c1.setDepartment(this);
	}
	
	
	//public ArrayList<Course> getCourseList(){
	//	return this.courselist;
	//}
	
	public void addStudent(Student newStud) {
		this.roster.add(newStud);
		newStud.setDepartment(this);
	}
	
	public ArrayList<Student> getStudentList(){
		return this.roster;
	}
	
	public void addProfessor(Professor p1) {
		this.professorlist.add(p1);
	}
	
	public ArrayList<Professor> getProfessorList(){
		return professorlist;
	}
	
	public void printStudentList() {
		for(int i = 0; i < this.roster.size() ; i++) {
		System.out.println(this.roster.get(i).getName());
	}
	}
	
	
	public void printProfessorList() {
		for(int i = 0; i < this.professorlist.size() ; i++) {
		System.out.println(this.professorlist.get(i).getName());
	}
	}
	
	public void printCourseList() {
		for(int i = 0; i < this.campuscourselist.size() ; i++) {
			System.out.println(this.campuscourselist.get(i).getDepartment().getDepartmentName()
				+this.campuscourselist.get(i).getCourseNumber()+ " "+
				this.campuscourselist.get(i).getName());
		}
		for(int i = 0; i < this.onlinecourselist.size() ; i++) {
			System.out.println(this.onlinecourselist.get(i).getDepartment().getDepartmentName()
					+this.onlinecourselist.get(i).getCourseNumber()+ " "+
					this.onlinecourselist.get(i).getName());
		}
		
	}


	public ArrayList<Staff> getStaffList() {
		return stafflist;
	}


	public void addStaff(Staff newstaff) {
		this.stafflist.add(newstaff);
	}
	
	public ArrayList<CampusCourse> getCampusCourseList(){
		return campuscourselist;
	}
	
	public ArrayList<OnlineCourse> getOnlineCourseList(){
		return onlinecourselist;
	}
}
