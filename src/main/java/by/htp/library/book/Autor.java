package by.htp.library.book;

import java.util.Calendar;
import java.util.Date;

public class Autor {
	private String name;
	private String surname;
	private Date birthdate;
	
	public Autor() {
		super();
	}
	
	public Autor(String name, String surname, Date birthdate) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
	}
		
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		return "Autor [name=" + name + ", surname=" + surname + ", birthdate="
				+ birthdate + "]";
	}
}
