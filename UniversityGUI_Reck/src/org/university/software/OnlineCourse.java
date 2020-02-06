package org.university.software;

import org.university.people.Student;

public class OnlineCourse extends Course{

	
	public boolean availableTo(Student s1) {
		if(s1.getThissemesterunits() < 6) {
			return false;
		}
		return true;
	}
}
