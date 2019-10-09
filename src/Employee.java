import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Employee {
	
	private int socialSecurityNumber;
	private String dateOfBirth;
	private String firstName;
	private String surname;
	private int salary;
	private char gender;
	
	public Employee() {
		this.socialSecurityNumber = socialSecurityNumber;
		this.dateOfBirth = dateOfBirth;
		this.firstName = firstName;
		this.surname = surname;
		this.salary = salary;
		this.gender = gender;
	}
	
	public int getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(int socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Employee [socialSecurityNumber=" + socialSecurityNumber + ", dateOfBirth=" + dateOfBirth
				+ ", firstName=" + firstName + ", surname=" + surname + ", salary=" + salary + ", gender=" + gender
				+ "]";
	}

	
}