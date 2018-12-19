package com.camp;

public class LinkedListCustomDemo {
	
	public static void main(String[] args) {
		LinkedListCustom<String> ls = new LinkedListCustom<String>();
		ls.add("Engin");	
		ls.add("Rebecca");
		ls.add("Miguel");
		ls.add("Audal");
		
		System.out.println("before delete");
		ls.display();
		
		System.out.println("after delete");
		ls.delete();
		ls.display();	
	}
}
