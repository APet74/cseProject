package com.airamerica.invoices;

import java.util.ArrayList;

import com.airamerica.Person;

public class TicketHolder {
private ArrayList<Person> person; 
private ArrayList<String> seatNum;
private ArrayList<String> id;
private ArrayList<Integer> age;
private ArrayList<String> nationality;
public TicketHolder(ArrayList<Person> person, ArrayList<String> seatNum, ArrayList<String> id, ArrayList<Integer> age,
		ArrayList<String> nationality) {
	super();
	this.person = person;
	this.seatNum = seatNum;
	this.id = id;
	this.age = age;
	this.nationality = nationality;
}
public ArrayList<Person> getPerson() {
	return person;
}
public void setPerson(ArrayList<Person> person) {
	this.person = person;
}
public ArrayList<String> getSeatNum() {
	return seatNum;
}
public void setSeatNum(ArrayList<String> seatNum) {
	this.seatNum = seatNum;
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
