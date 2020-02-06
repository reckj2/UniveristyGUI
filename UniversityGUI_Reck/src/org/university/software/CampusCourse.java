package org.university.software;

import java.util.ArrayList;
import java.util.Collections;

import org.university.hardware.Classroom;
import org.university.people.Student;

public class CampusCourse extends Course {

	private int maxStudents;
	private ArrayList<Integer> Schedule;
	private Classroom classroom;
	
	public CampusCourse() {
		setMaxStudents(0);
		Schedule = new ArrayList<Integer>();
		classroom = new Classroom();
	}
	
	public ArrayList<Integer> getSchedule() {
		return Schedule;
	}

	public void setSchedule(int s1) {
		Schedule.add(s1);
	}
	
	public Classroom getRoomAssigned() {
		return classroom;
	}
	
	public void setMaxCourseLimit(int max) {
		setMaxStudents(max);
	}
	
	public void setRoomAssigned(Classroom cr1) {
		boolean checker = false;
		int courseinteger = 0;
		int scheduleinteger = 0;
		int schedTemp = 0;
		int temp2 = 0;
		
		String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri"};
		String[] Slot = {	"8:00am to 9:15am",
							"9:30am to 10:45am",
							"11:00am to 12:15pm",
							"12:30pm to 1:45pm",
							"2:00pm to 3:15pm",
							"3:30pm to 4:45pm"};
		int test = 0;
		int test2 = 0;
		
		
		
			for(int i = 0; i < cr1.getCourselist().size(); i++) {
				for(int j = 0; j <cr1.getCourselist().get(i).getSchedule().size(); j++) {
					for(int k = 0; k < this.getSchedule().size(); k ++) {
						if(cr1.getCourselist().get(i).getSchedule().get(j) == this.getSchedule().get(k) ) {
							checker = true;
							courseinteger = i;
							scheduleinteger = j;
							test = 1;
							test2 = 1;
							break;
						}
					}
					if(test2 == 1) {
						break;
					}
				}
				if (test == 1) {
					break;
				}
			 }
			
			if(checker) {
				schedTemp = cr1.getCourselist().get(courseinteger).getSchedule().get(scheduleinteger);
				temp2 = schedTemp%100;
				schedTemp = schedTemp - temp2;
				schedTemp = schedTemp/100;
				schedTemp = schedTemp -1;
				temp2 = temp2 -1;
				
				System.out.println(this.getDepartment().getDepartmentName() + this.getCourseNumber()+ 
						" conflicts with " + cr1.getCourselist().get(courseinteger).getDepartment().getDepartmentName()
						+cr1.getCourselist().get(courseinteger).getCourseNumber() + 
						". Conflicting time slot "+ Week[schedTemp]+ " " + Slot[temp2] + ". " +
						this.getDepartment().getDepartmentName() + this.getCourseNumber()+
						" course cannot be added to " + cr1.getRoomNumber() + "'s Schedule.");
			 }

		else {
			cr1.addCourse(this);
			this.classroom = cr1;
			}
	}
	
	public void printSchedule() {
		String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri"};
		String[] Slot = {	"8:00am to 9:15am",
							"9:30am to 10:45am",
							"11:00am to 12:15pm",
							"12:30pm to 1:45pm",
							"2:00pm to 3:15pm",
							"3:30pm to 4:45pm"};
		int schedTemp = 0;
		int temp2 = 0;
		String tempName = "abc";
		
		ArrayList<Integer> tempSched = new ArrayList<>();
		
			for(int j = 0; j < this.getSchedule().size(); j++) {
				tempSched.add(this.getSchedule().get(j));
			}
		
			Collections.sort(tempSched);
			
		for(int i = 0; i < tempSched.size(); i++) {
			schedTemp = tempSched.get(i);
			temp2 = schedTemp%100;
			schedTemp = schedTemp - temp2;
			schedTemp = schedTemp/100;
			schedTemp = schedTemp -1;
			temp2 = temp2 -1;
				
			tempName = this.classroom.getRoomNumber();			
			System.out.println(Week[schedTemp]+ " " + Slot[temp2]+ " "+ tempName);	
		}	
		
	}
	
	public void printRoster() {
		for(int i = 0; i < this.getStudentRoster().size(); i++) {
			System.out.println(this.getStudentRoster().get(i).getName());
		}
	}
	
	public boolean availableTo(Student aStudent) {
		if((this.getStudentRoster().size() == this.maxStudents)) {
			return false;
		}
		
		return true;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
}
