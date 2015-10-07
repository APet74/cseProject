package com.airamerica.invoices;

import java.util.ArrayList;

import com.airamerica.Person;

public class TicketHolder {
private ArrayList<String> seatNum = new ArrayList<>();
private ArrayList<String> person = new ArrayList<>(); 
private ArrayList<String> id = new ArrayList<>();
private ArrayList<Integer> age = new ArrayList<>();
private ArrayList<String> nationality = new ArrayList<>();

public TicketHolder(String seatNumber, String personCode, String id, Integer age, String nationality) {
	super();
	this.seatNum.add(seatNumber);
	this.person.add(personCode);
	this.id.add(id);
	this.age.add(age);
	this.nationality.add(nationality);
	
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
public void setId(ArrayList<String> id) {
	this.id = id;
}
public ArrayList<Integer> getAge() {
	return age;
}
public void setAge(ArrayList<Integer> age) {
	this.age = age;
}
public ArrayList<String> getNationality() {
	return nationality;
}
public void setNationality(ArrayList<String> nationality) {
	this.nationality = nationality;
}




}
