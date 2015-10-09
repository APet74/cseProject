package com.airamerica.invoices;

import java.util.ArrayList;

import com.airamerica.Person;

public class TicketHolder {
private ArrayList<String> seatNum = new ArrayList<>();
private ArrayList<String> person = new ArrayList<>(); 
private ArrayList<String> id = new ArrayList<>();
private ArrayList<Integer> age = new ArrayList<>();
private ArrayList<String> nationality = new ArrayList<>();

/*
public TicketHolder(String seatNumber, String personCode, String id, Integer age, String nationality) {
	super();
	this.seatNum.add(seatNumber);
	this.person.add(personCode);
	this.id.add(id);
	this.age.add(age);
	this.nationality.add(nationality);
	
}
*/
public TicketHolder(){
	
}

public ArrayList<String> getPerson() {
	return person;
}
public void addPerson(String person) {
	this.person.add(person);
}
public ArrayList<String> getSeatNum() {
	return seatNum;
}
public void addSeatNum(String seatNum) {
	this.seatNum.add(seatNum);
}
public ArrayList<String> getId() {
	return id;
}
public void addId(String id) {
	this.id.add(id);
}
public ArrayList<Integer> getAge() {
	return age;
}
public void addAge(Integer age) {
	this.age.add(age);
}
public ArrayList<String> getNationality() {
	return nationality;
}
public void addNationality(String nationality) {
	this.nationality.add(nationality);
}



}
