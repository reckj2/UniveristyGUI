package org.university.hardware;


import java.util.ArrayList;
import java.util.Collections;

import org.university.software.CampusCourse;
//import org.university.software.Course;
import java.io.Serializable;

public class Classroom implements Serializable{
	private String RoomNumber;
	private ArrayList<CampusCourse> courselist;
	
	public Classroom() {
		setRoomNumber("unknown");
		courselist = new ArrayList<CampusCourse>();
	}

	public String getRoomNumber() {
		return RoomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		RoomNumber = roomNumber;
	}

	public ArrayList<CampusCourse> getCourselist() {
		return courselist;
	}

	public void addCourse(CampusCourse course1) {
		this.courselist.add(course1);
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
		int tester = 0;
		int tester2 = 0;
		String tempName = "abc";
		String tempDept = "abc";
		int tempCourseNum = 0;
		
		ArrayList<Integer> tempSched = new ArrayList<>();
		
		for(int i = 0; i <this.getCourselist().size(); i++) {
			for(int j = 0; j < this.getCourselist().get(i).getSchedule().size(); j++) {
				tempSched.add(this.getCourselist().get(i).getSchedule().get(j));
			}
		}

		Collections.sort(tempSched);
		
		for(int i = 0; i < tempSched.size(); i++) {
			schedTemp = tempSched.get(i);
			temp2 = schedTemp%100;
			schedTemp = schedTemp - temp2;
			schedTemp = schedTemp/100;
			schedTemp = schedTemp -1;
			temp2 = temp2 -1;
			 
			for(int j = 0; j < this.courselist.size(); j++) {
				for(int k = 0; k < this.courselist.get(j).getSchedule().size(); k++) {
					tester = this.courselist.get(j).getSchedule().get(k);
					tester2 = tempSched.get(i);
					if(tester == tester2) {
						tempName = this.courselist.get(j).getName();
						tempDept = this.courselist.get(j).getDepartment().getDepartmentName();
						tempCourseNum = this.courselist.get(j).getCourseNumber();
					}
					
				}
			}
			
			System.out.println(Week[schedTemp]+ " " + Slot[temp2]+ " " + tempDept + tempCourseNum +" "+ tempName);
			
		}
		
		
		
		
		
	}
	
	
	
}
