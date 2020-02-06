package org.university.people;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;

import org.university.software.CampusCourse;
//import org.university.software.Course;
import org.university.software.OnlineCourse;

public abstract class Person implements Serializable{
	protected String name;
	protected ArrayList<Integer> schedule;
	protected ArrayList<CampusCourse> campuscourselist;
	protected ArrayList<OnlineCourse> onlinecourselist;
	
	public Person() {
		setName("unknown");
		schedule = new ArrayList<Integer>();
		campuscourselist = new ArrayList<CampusCourse>();
		onlinecourselist = new ArrayList<OnlineCourse>();
	}
	
	public ArrayList<CampusCourse> getCampusCourselist() {
		return campuscourselist;
	}
	
	
	public void removeCampCourse(CampusCourse c1) {
		this.campuscourselist.remove(c1);
	}
	
	public void removeOnCourse(OnlineCourse c1) {
		this.onlinecourselist.remove(c1);
	}
	
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
					tester = this.getCampusCourselist().get(i).getSchedule().get(j);
					tester2 = aCourse.getSchedule().get(k);
					if (tester == tester2) {
					
						schedTemp = aCourse.getSchedule().get(k);
						temp2 = schedTemp%100;
						schedTemp = schedTemp - temp2;
						schedTemp = schedTemp/100;
						schedTemp = schedTemp -1;
						temp2 = temp2 -1;
						
//						System.out.println(aCourse.getDepartment().getDepartmentName()+ aCourse.getCourseNumber() +
//								" course cannot be added to " + this.getName()+ "'s Schedule. " +
//								aCourse.getDepartment().getDepartmentName()+ aCourse.getCourseNumber() +
//								" conflicts with " + this.getCampusCourselist().get(i).getDepartment().getDepartmentName()+
//								this.getCampusCourselist().get(i).getCourseNumber()+ 
//								". Conflicting time slot is " + Week[schedTemp]+ " " + Slot[temp2] + "." );
					
					conflict = true;
					}
				}
			}
		}
		return conflict;
	}
	
	public int returnBadSched(CampusCourse aCourse) {
		int tester = 0;
		int tester2 = 0;
		
		for (int i = 0; i < this.getCampusCourselist().size(); i++) {
			for(int j = 0; j < this.getCampusCourselist().get(i).getSchedule().size(); j++) {
				for(int k = 0; k < aCourse.getSchedule().size(); k ++) {
					tester = this.getCampusCourselist().get(i).getSchedule().get(j);
					tester2 = aCourse.getSchedule().get(k);
					if (tester == tester2) {
						return tester;
					}
				}
			}
		}
		return 0;
	}
	
	public CampusCourse returnBadCC(CampusCourse aCourse) {
		int tester = 0;
		int tester2 = 0;
		
		for (int i = 0; i < this.getCampusCourselist().size(); i++) {
			for(int j = 0; j < this.getCampusCourselist().get(i).getSchedule().size(); j++) {
				for(int k = 0; k < aCourse.getSchedule().size(); k ++) {
					tester = this.getCampusCourselist().get(i).getSchedule().get(j);
					tester2 = aCourse.getSchedule().get(k);
					if (tester == tester2) {
						return this.getCampusCourselist().get(i);
					}
				}
			}
		}
		return aCourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Integer> schedule) {
		this.schedule = schedule;
	}

	public ArrayList<OnlineCourse> getOnlinecourselist() {
		return onlinecourselist;
	}

	public void addOnlinecourselist(OnlineCourse onlinecourselist) {
		this.onlinecourselist.add(onlinecourselist);
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
		
		for(int i = 0; i <this.getCampusCourselist().size(); i++) {
			for(int j = 0; j < this.getCampusCourselist().get(i).getSchedule().size(); j++) {
				tempSched.add(this.getCampusCourselist().get(i).getSchedule().get(j));
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
			 
			for(int j = 0; j < this.campuscourselist.size(); j++) {
				for(int k = 0; k < this.campuscourselist.get(j).getSchedule().size(); k++) {
					tester = this.campuscourselist.get(j).getSchedule().get(k);
					tester2 = tempSched.get(i);
					if(tester == tester2) {
						tempName = this.campuscourselist.get(j).getName();
						tempDept = this.campuscourselist.get(j).getDepartment().getDepartmentName();
						tempCourseNum = this.campuscourselist.get(j).getCourseNumber();
					}
					
				}
			}
			
			System.out.println(Week[schedTemp]+ " " + Slot[temp2]+ " " + tempDept + tempCourseNum +" "+ tempName);
			
		}
		
		for(int i = 0; i < onlinecourselist.size(); i++) {
			System.out.println(onlinecourselist.get(i).getCourseNumber()+ " " + onlinecourselist.get(i).getName());
		}
	}

	public abstract void addCourse(CampusCourse cC);
	
	public abstract void addCourse(OnlineCourse oC);




}