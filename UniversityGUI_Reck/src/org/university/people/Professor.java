package org.university.people;

import java.util.ArrayList;
//import java.util.Collections;

import org.university.software.CampusCourse;
//import org.university.software.Course;
import org.university.software.OnlineCourse;
//import org.university.hardware.Department;
import org.university.people.Employee;

public class Professor extends Employee{
	private float salary;
	//private Department department;
//////////////////////////////////////////////////////////////
	public Professor(){
			salary = 0;
			//department = new Department();
		
	}
/////////////////////////////////////////////////////////////
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<CampusCourse> getCampusCourselist(){
		return campuscourselist;
	}
	
	public void setSalary(int sal) {
		salary = sal;
	}
	
///////////////////////////////////////////////////////////
	public boolean detectConflict(CampusCourse aCourse) {
		String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri"};
		String[] Slot = {	"8:00am to 9:15am",
							"9:30am to 10:45am",
							"11:00am to 12:15pm",
							"12:30pm to 1:45pm",
							"2:00pm to 3:15pm",
							"3:30pm to 4:45pm"};
		int schedTemp = 0;
		int temp2 = 0;
		
		int tester = 0;
		int tester2 = 0;
		
		boolean conflict = false;
		
		if(this.getCampusCourselist().size() == 0) {
			return false;
		}
		
		for (int i = 0; i < this.getCampusCourselist().size(); i++) {
			for(int j = 0; j < this.getCampusCourselist().get(i).getSchedule().size(); j++) {
				for(int k = 0; k < aCourse.getSchedule().size(); k ++) {
					/////////////////////////////////
					tester = this.getCampusCourselist().get(i).getSchedule().get(j);
					tester2 = aCourse.getSchedule().get(k);
					////////////////////////////////
					if (tester == tester2) {
						
						schedTemp = aCourse.getSchedule().get(k);
						temp2 = schedTemp%100;
						schedTemp = schedTemp - temp2;
						schedTemp = schedTemp/100;
						schedTemp = schedTemp -1;
						temp2 = temp2 -1;
						
						System.out.println(aCourse.getDepartment().getDepartmentName()+ aCourse.getCourseNumber() +
								" course cannot be added to " + this.getName()+ "'s Schedule. " +
								aCourse.getDepartment().getDepartmentName()+ aCourse.getCourseNumber() +
								" conflicts with " + this.getCampusCourselist().get(i).getDepartment().getDepartmentName()+
								this.getCampusCourselist().get(i).getCourseNumber()+
								". Conflicting time slot is " + Week[schedTemp]+ " " + Slot[temp2] + "." );
						
						conflict = true;
					}
				}
			}
		}
		return conflict;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void addCourse(CampusCourse c1) {
		
		
		if(detectConflict(c1)) {
		return;
			}
		
		if(c1.getProfessor().getName() != "unknown") {
			System.out.println("The professor "+ this.getName()+ " cannot be assigned to this campus course because professor "
					+ c1.getProfessor().getName() + " is already assigned to the course " + c1.getName()+".");
			return;
		}
		this.campuscourselist.add(c1);
		c1.addProfessor(this);
		//c1.getDepartment().addProfessor(this);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
public void addCourse(OnlineCourse c1) {
	if(c1.getProfessor().getName() != "unknown") {
		System.out.println("The professor cannot be assigned to this online course because professor "
				+ c1.getProfessor().getName() + " is already assigned to the online course " + c1.getName()+".");
		return;
	}
	this.onlinecourselist.add(c1);
	c1.addProfessor(this);
	//c1.getDepartment().addProfessor(this);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

public float earns() {
	float newnew = 0;
	newnew = salary/26;
	return newnew;
}
public void raise(double percent) {
	double actp = 0;
	actp = (percent /100);
	salary = (float) (salary + (salary*actp));
}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
}
