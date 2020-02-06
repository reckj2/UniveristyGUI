package org.university.people;

import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;

public class Staff extends Employee{
	double payRate;
	int hoursWorked;
	
	public Staff() {
		payRate = 0;
		hoursWorked = 0;
	}
	
	
	public double getPayRate() {
		return payRate;
	}


	public void setPayRate(double d) {
		this.payRate = d;
	}


	public int getHoursWorked() {
		return hoursWorked;
	}


	public void setMonthlyHours(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}


	public void setName(String na) {
		this.name = na;
	}
	
	public String getName() {
		return this.name;
	}

	
	public float earns() {
		float tempe = 0;
		tempe = (float) (hoursWorked * payRate);
		return tempe;
	}

	
	public void raise(double percent) {
		double actp = 0;
		actp = (percent /100);
		payRate = payRate + (payRate*actp);
		
	}

	
	public int getTuitionFee() {
		int fee = 0;
		
		if(campuscourselist.size() >0) {
			fee = fee + 300*campuscourselist.get(0).getCreditUnits();
		
		}
		
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
	
	public void addCourse(CampusCourse cC) {
		if(this.campuscourselist.size() > 0) {
			System.out.println(this.campuscourselist.get(0).getDepartment().getDepartmentName()+
					this.campuscourselist.get(0).getCourseNumber()+" is removed from "
					+this.getName()
					+ "'s schedule(Staff can only take one class at a time). "
					+ cC.getDepartment().getDepartmentName()+ cC.getCourseNumber()+
					" has been added to " +this.getName()
					+ "'s Schedule.");
			this.campuscourselist.clear();
			this.onlinecourselist.clear();
			this.campuscourselist.add(cC);
			cC.addStudentToRoster(this);
		}
		else if(this.onlinecourselist.size() > 0) {
			System.out.println(this.onlinecourselist.get(0).getDepartment().getDepartmentName()+
					this.onlinecourselist.get(0).getCourseNumber()+" is removed from "
					+this.getName()
					+ "'s schedule(Staff can only take one class at a time). "
					+ cC.getDepartment().getDepartmentName()+ cC.getCourseNumber()+
					" has been added to " +this.getName()
					+ "'s Schedule.");
			this.onlinecourselist.clear();
			this.campuscourselist.clear();
			this.campuscourselist.add(cC);
			cC.addStudentToRoster(this);
		}
		else {
			this.campuscourselist.add(cC);
			cC.addStudentToRoster(this);
		}
		
	}

	
	public void addCourse(OnlineCourse oC) {
		if(this.campuscourselist.size() > 0) {
			System.out.println(this.campuscourselist.get(0).getDepartment().getDepartmentName()+
					this.campuscourselist.get(0).getCourseNumber()+" is removed from "
					+this.getName()
					+ "'s schedule(Staff can only take one class at a time). "
					+ oC.getDepartment().getDepartmentName()+ oC.getCourseNumber()+
					" has been added to " +this.getName()
					+ "'s Schedule.");
			this.campuscourselist.clear();
			this.onlinecourselist.clear();
			this.onlinecourselist.add(oC);
			oC.addStudentToRoster(this);
		}
		else if(this.onlinecourselist.size() > 0) {
			System.out.println(this.onlinecourselist.get(0).getDepartment().getDepartmentName()+
					this.onlinecourselist.get(0).getCourseNumber()+" is removed from "
					+this.getName()
					+ "'s schedule(Staff can only take one class at a time). "
					+ oC.getDepartment().getDepartmentName()+ oC.getCourseNumber()+
					" has been added to " +this.getName()
					+ "'s Schedule.");
			this.onlinecourselist.clear();
			this.campuscourselist.clear();
			this.onlinecourselist.add(oC);
			oC.addStudentToRoster(this);
		}
		else {
			this.onlinecourselist.add(oC);
			oC.addStudentToRoster(this);
		}
		
	}
}
