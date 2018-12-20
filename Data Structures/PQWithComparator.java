package com.training;

// Implementing PriorityQueue through Comparator in Java

import java.util.*;

public class PQWithComparator {
	
	public static void main (String[] args) {
		
		PriorityQueue<Student> pq = new 
				PriorityQueue<Student>(5, new StudentComparator());
		
		Student student1 = new Student("Engin", 3.8); 
		Student student2 = new Student("Robert", 3.6); 
		Student student3 = new Student("Megan", 3.2); 
		Student student4 = new Student("Anna", 3.1); 
		Student student5 = new Student("Kevin", 2.5); 
		
		pq.add(student1);
		pq.add(student2);
		pq.add(student3);
		pq.add(student4);
		pq.add(student5);
		
		while (!pq.isEmpty()) {   
            System.out.println(pq.poll().getName()); 
		}

	}
}

class StudentComparator implements Comparator<Student> {
	// Overriding compare()method of Comparator  for descending order(max PQ)
	@Override    
	public int compare(Student s1, Student s2) {
		if (s2.gpa > s1.gpa)
			return 1;
		if (s2.gpa < s1.gpa)
			return -1;
		else 
			return 0;		
	}

}

class Student {
	public String name;
	public double gpa;
	
	public Student(String name, double gpa) {
		this.name = name;
		this.gpa = gpa;
	}
	
	public String getName() {
		return name;
	}
}
