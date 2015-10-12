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
private ArrayList<String> comment = new ArrayList<>();
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

public int getAgeOfTicketHolder(){
	int output = 0;
	
	for (int i = 0; i < age.size(); i++){
		output = age.get(i);
		

		}
	
	return output;
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
public String getName(int index, ArrayList<Person> personArray){
	StringBuilder sb = new StringBuilder();
	
	System.out.println(this.person.size() + " " + ((Person) FindObject.find(this.person.get(index), personArray)).getfirstName());
	sb.append(((Person) FindObject.find(this.person.get(index), personArray)).getfirstName());
	
	
	return sb.toString();
}

public int getNumberOfPassengers() {
	return this.age.size();
}


}
