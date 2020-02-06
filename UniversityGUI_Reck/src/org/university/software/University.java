package org.university.software;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.university.hardware.Department;
import org.university.hardware.Classroom;

import java.io.Serializable;

public class University implements Serializable{
	
	public ArrayList<Department> departmentList;
	//public ArrayList<Course> coureslist;
	//public ArrayList<Student> studentlist;
	//public ArrayList<Professor> professorList;
	public ArrayList<Classroom> classroomList;
	
	public University() {
		departmentList = new ArrayList<Department>();
		classroomList = new ArrayList<Classroom>();
	}
	
	public static void saveData(University univ) {
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;
		
		try {
			fileOut = new FileOutputStream("University.ser");
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(univ);
			objOut.close();
			fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public static University loadData() {
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		
		University univ = null;
		
		try {
			fileIn = new FileInputStream("University.ser");
			objIn = new ObjectInputStream(fileIn);
			univ = (University) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return univ;
	}
	 
	public void printAll() {
		System.out.print("\n");

		System.out.println("List of departments:");
		for (int i = 0; i < departmentList.size(); i++) {
			System.out.println(departmentList.get(i).getDepartmentName());
		}
		
		System.out.print("\n");
		System.out.println("Classroom list:");
		for (int i = 0; i < classroomList.size(); i++) {
			System.out.println(classroomList.get(i).getRoomNumber());
		}
		
		System.out.print("\n");
		for (int i = 0; i < departmentList.size(); i++) {
			System.out.println("The professor list for department "+ departmentList.get(i).getDepartmentName());
			
			for (int j = 0; j < departmentList.get(i).getProfessorList().size(); j++) {
				System.out.println(departmentList.get(i).getProfessorList().get(j).getName());
			}
			
			System.out.print("\n");

			
			
		}
		
		//System.out.print("\n");
		for (int i = 0; i < departmentList.size(); i++) {
			System.out.println("The course list for department "+ departmentList.get(i).getDepartmentName());
			for (int j = 0; j < departmentList.get(i).getCampusCourseList().size(); j++) {
				System.out.println(this.departmentList.get(i).getCampusCourseList().get(j).getDepartment().getDepartmentName()
						+this.departmentList.get(i).getCampusCourseList().get(j).getCourseNumber()+ " "+
						this.departmentList.get(i).getCampusCourseList().get(j).getName());
			}
			for (int j = 0; j < departmentList.get(i).getOnlineCourseList().size(); j++) {
				System.out.println(this.departmentList.get(i).getOnlineCourseList().get(j).getDepartment().getDepartmentName()
						+this.departmentList.get(i).getOnlineCourseList().get(j).getCourseNumber()+ " "+
						this.departmentList.get(i).getOnlineCourseList().get(j).getName());
			}
			System.out.print("\n");
		}
		
		for (int i = 0; i < classroomList.size(); i++) {
			System.out.println("The schedule for classroom "+ classroomList.get(i).getRoomNumber());
			classroomList.get(i).printSchedule();
			System.out.print("\n");
		}
		
		for (int i = 0; i < departmentList.size(); i++) {
			System.out.println("Department "+ departmentList.get(i).getDepartmentName());
			System.out.print("\n");
			System.out.println("Printing Professor Schedules:");
			System.out.print("\n");
			for(int k =0; k < departmentList.get(i).getProfessorList().size(); k++) {
				System.out.println("The Schedule for Prof. " + 
			departmentList.get(i).getProfessorList().get(k).getName()+":");
				departmentList.get(i).getProfessorList().get(k).printSchedule();
				System.out.print("\n");
			}
			System.out.println("Printing Student Schedules:");
			System.out.print("\n");
			
			for (int j = 0; j < departmentList.get(i).getStudentList().size(); j++) {
				System.out.println("The schedule for Student " + 
			departmentList.get(i).getStudentList().get(j).getName()+":");
				departmentList.get(i).getStudentList().get(j).printSchedule();
				System.out.print("\n");
			}
			
			
			System.out.println("Printing Staff Schedules:");
			System.out.print("\n");
			for (int j = 0; j < departmentList.get(i).getStaffList().size(); j++) {
				System.out.println("The schedule for Employee " 
			+ departmentList.get(i).getStaffList().get(j).getName()+ ":");
				departmentList.get(i).getStaffList().get(j).printSchedule();
				System.out.print("\n");
				System.out.println("Staff: " + departmentList.get(i).getStaffList().get(j).getName() + " earns " +
						(departmentList.get(i).getStaffList().get(j).getPayRate() * 
								departmentList.get(i).getStaffList().get(j).getHoursWorked()) + " this month");
				//System.out.print("\n");
			}
			System.out.print("\n");
			System.out.println("The rosters for courses offered by " + departmentList.get(i).getDepartmentName());
			System.out.print("\n");
			
			for (int j = 0; j < departmentList.get(i).getCampusCourseList().size(); j++) {
				System.out.println("The roster for course " + 
						departmentList.get(i).getCampusCourseList().get(j).getDepartment().getDepartmentName()+
						departmentList.get(i).getCampusCourseList().get(j).getCourseNumber());
				departmentList.get(i).getCampusCourseList().get(j).printRoster();
				System.out.print("\n");		
			}
		}
		
		
	}
		
		
		
	//}
	
	///////////////////////////////////////////////////////////////////////
	public void printDepartmentList() {
		for (int i = 0; i < departmentList.size(); i++) {
			System.out.println(departmentList.get(i).getDepartmentName());
		}
	}
	
	public void printStudentList() {
		for (int i = 0; i < departmentList.size(); i++) {
			for (int j = 0; j < departmentList.get(i).getStudentList().size(); j++) {
			System.out.println(departmentList.get(i).getStudentList().get(j).getName());
			}
		}
	}
	
	public void printProfessorList() {
		for (int i = 0; i < departmentList.size(); i++) {
			for (int j = 0; j < departmentList.get(i).getProfessorList().size(); j++) {		
				System.out.println(departmentList.get(i).getProfessorList().get(j).getName());
			}
		}
	}
	
	public void printStaffList() {
		for (int i = 0; i < departmentList.size(); i++) {
			for (int j = 0; j < departmentList.get(i).getStaffList().size(); j++) {
				System.out.println(departmentList.get(i).getStaffList().get(j).getName());
			}
		}
	}
	
	public void printCourseList() {
		for (int i = 0; i < departmentList.size(); i++) {
			for (int j = 0; j < departmentList.get(i).getCampusCourseList().size(); j++) {
				System.out.println(this.departmentList.get(i).getCampusCourseList().get(j).getDepartment().getDepartmentName()
						+this.departmentList.get(i).getCampusCourseList().get(j).getCourseNumber()+ " "+
						this.departmentList.get(i).getCampusCourseList().get(j).getName());
			}
		}
		for (int i = 0; i < departmentList.size(); i++) {
			for (int j = 0; j < departmentList.get(i).getOnlineCourseList().size(); j++) {
				System.out.println(this.departmentList.get(i).getOnlineCourseList().get(j).getDepartment().getDepartmentName()
						+this.departmentList.get(i).getOnlineCourseList().get(j).getCourseNumber()+ " "+
						this.departmentList.get(i).getOnlineCourseList().get(j).getName());
			}
		}
	}
}
