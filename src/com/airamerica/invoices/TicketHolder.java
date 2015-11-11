package com.airamerica.invoices;

import java.util.ArrayList;

import com.airamerica.Person;
import com.airamerica.dataConversion.FindObject;

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
public String getPerson(int index){
	return person.get(index);
}
public void addPerson(String person) {
	this.person.add(person);
}
public ArrayList<String> getSeatNum() {
	return seatNum;
}
public String getSeat(int index) {
	return seatNum.get(index);
}
public void addSeatNum(String seatNum) {
	this.seatNum.add(seatNum);
}
public ArrayList<String> getId() {
	return id;
}
public String getId(int index) {
	return id.get(index);
}
public void addId(String id) {
	this.id.add(id);
}
public ArrayList<Integer> getAge() {
	return age;
}

public int getAge(int index){
	return this.age.get(index);
}

public String getSeatOfTicketholder(int index) {
	return this.seatNum.get(index);
}
public void addAge(Integer age) {
	this.age.add(age);
}
public ArrayList<String> getNationality() {
	return nationality;
}
public String getNationality(int index){
	return nationality.get(index);
}
public void addNationality(String nationality) {
	this.nationality.add(nationality);
}
public String getName(int index, ArrayList<Person> personArray){
	return String.format("%s, %s", ((Person) FindObject.find(this.person.get(index), personArray)).getlastName(), 
			((Person) FindObject.find(this.person.get(index), personArray)).getfirstName());
}

public int getNumberOfPassengers() {
	return this.age.size();
}


}