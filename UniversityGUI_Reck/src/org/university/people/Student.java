package org.university.people;

import org.university.hardware.Department;
import org.university.software.CampusCourse;
//import org.university.software.Course;
import org.university.software.OnlineCourse;



public class Student extends Person{
	private Department department;
	private int requiredCredits;
	private int completedUnits;
	private int thissemesterunits;
	
	public Student() {
		department = new Department();
		setRequiredCredits(0);
		setCompletedUnits(0);
		thissemesterunits = 0;
	}
	
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
		//department.addStudent(this);
	}
	
	
	//****************************************//
	
	
	
	//****************************************//
	
	public void addCourse(CampusCourse c1) {	
		
		if(detectConflict(c1)) {
		return;
			}
		
		if(!c1.availableTo(this)) {
			System.out.println(this.getName() + " can't add Campus Course " +
					c1.getDepartment().getDepartmentName() + c1.getCourseNumber()+ " " + 
					c1.getName()+". Because this Campus course has enough student.");
			return;
		}
		
		this.getCampusCourselist().add(c1);
		this.thissemesterunits = this.thissemesterunits + c1.getCreditUnits();
		c1.addStudent(this);
	}
	
	
	public void addCourse(OnlineCourse c1) {
		if(!c1.availableTo(this)) {
			System.out.println("Student "+ this.getName() +" has only "+this.thissemesterunits+
			" on campus credits enrolled. Should have at least 6 credits registered before "
			+ "registering online courses."); 
			System.out.println(this.getName()+" can't add online Course "+c1.getDepartment().getDepartmentName()
			+c1.getCourseNumber() +" "+c1.getName()+". Because this student doesn't have enough Campus course credit.");
			return;
		}
		this.addOnlinecourselist(c1);
		c1.addStudent(this);
	//	this.thissemesterunits = this.thissemesterunits + c1.getCreditUnits();
	}
	
	public void dropCourse(OnlineCourse course1) {
		boolean check = false;
		
		for(int i = 0; i< course1.getStudentRoster().size(); i++) {
			if(course1.getStudentRoster().get(i).getName() == this.getName()) {
				check = true;

			}
		}
		if(check) {
		this.removeOnCourse(course1);
		//this.thissemesterunits = this.thissemesterunits - course1.getCreditUnits();
		course1.removeStudent(this);
		}
		else {
			System.out.println("The course "+course1.getDepartment().getDepartmentName()+course1.getCourseNumber()+
					" could not be dropped because "+this.getName()+" is not enrolled in "
					+course1.getDepartment().getDepartmentName()+course1.getCourseNumber()+".");
		}
	}
	
	//****************************************//	
	
	public void dropCourse(CampusCourse course1) {
		boolean check = false;
		
		for(int i = 0; i< course1.getStudentRoster().size(); i++) {
			if(course1.getStudentRoster().get(i).getName() == this.getName()) {
				check = true;
				
			}
		}
		if(check) {
			if(this.onlinecourselist.size() > 0) {
				if((this.thissemesterunits - course1.getCreditUnits()) < 6) {
					System.out.println(this.getName()+" can't drop this CampusCourse,"
					+ " because this student doesn't have enough "
					+ "campus course credit to hold the online course");
					return;
				}
			}
		this.removeCampCourse(course1);
		this.thissemesterunits = this.thissemesterunits - course1.getCreditUnits();
		course1.removeStudent(this);
		return;
		}
		else if(!check) {
			System.out.println("The course "+course1.getDepartment().getDepartmentName()+course1.getCourseNumber()+
					" could not be dropped because "+this.getName()+" is not enrolled in "
					+course1.getDepartment().getDepartmentName()+course1.getCourseNumber()+".");
			return;
		}
		
		
		
	}
	
	
	public int getTuitionFee() {
		int fee = 0;
		
		fee = fee + 300*thissemesterunits;
		
		for(int i = 0; i < onlinecourselist.size(); i++) {
			if(onlinecourselist.get(i).getCreditUnits() == 3) {
				fee = fee + 2000;
			}
			else {
				fee = fee +3000;
			}
		}
		return fee;
		
	}		

	public int requiredToGraduate() {
		return requiredCredits;
	}

	public void setRequiredCredits(int requiredCredits) {
		this.requiredCredits = requiredCredits;
	}

	public int getThissemesterunits() {
		return thissemesterunits;
	}


	public void setThissemesterunits(int thissemesterunits) {
		this.thissemesterunits = thissemesterunits;
	}


	public int getCompletedUnits() {
		return completedUnits;
	}

	public void setCompletedUnits(int completedCredits) {
		this.completedUnits = completedCredits;
		this.requiredCredits = (this.requiredCredits - completedCredits);
		this.requiredCredits = (this.requiredCredits - thissemesterunits);
	}
	
	
}
