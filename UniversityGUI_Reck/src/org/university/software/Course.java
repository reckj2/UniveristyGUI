package org.university.software;

import java.util.ArrayList;
//import org.university.hardware.Classroom;
import org.university.hardware.Department;
import org.university.people.Person;
//import org.university.people.Person;
import org.university.people.Professor;
import org.university.people.Student;
//import org.university.people.Staff;
//import org.university.people.Employee;

import java.io.Serializable;


public abstract class Course implements Serializable{
	
	private String Name;
	private int CourseNumber;
	private ArrayList<Person> StudentRoster;
	private Department department;
	private Professor professor;
	private int credits;
	
	public Course() {
		Name = "Unknown";
		CourseNumber = 0;
		StudentRoster = new ArrayList<Person>();
		department = new Department();
		professor = new Professor();
		
		credits = 0;
	
		
	}
	
	public void addStudent(Student std) {
		StudentRoster.add(std);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getCourseNumber() {
		return CourseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		CourseNumber = courseNumber;
	}


	public ArrayList<Person> getStudentRoster() {
		return StudentRoster;
	}
	
	//public void setStudentRoster(Student std) {
	//	this.StudentRoster.add(std);
	//}
	
	public void removeStudent(Person std) {
		this.StudentRoster.remove(std);
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void addProfessor(Professor p1) {
		this.professor = p1;
		//this.professorCheck = true;
	}
	
	public Professor getProfessor() {
		return this.professor;
	}

	public void setCreditUnits(int creds) {
		credits = creds;
	}
	
	public int getCreditUnits() {
		return credits;
	}
	
	public void addStudentToRoster(Person p1) {
		StudentRoster.add(p1);
	}

	public abstract boolean availableTo(Student aStudent);
	
	
	

}
