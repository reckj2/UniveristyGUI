package org.university.people;

public abstract class Employee extends Person{
	
	
	public Employee() {
		
	}
	
	public abstract float earns();
	public abstract void raise(double percent);
}
