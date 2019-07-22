package DesignPatternPractice;

class Employee {
	String name;
	String company;
	int id;
	String passport_no;
	String temp_address;
	String perm_address;
	int salary;
	
	Employee(String name, String company, int id, String passport_no, String temp_address, String perm_address, int salary) {
		this.name = name;
		this.company = company;
		this.id = id;
		this.passport_no = passport_no;
		this.temp_address = temp_address;
		this.perm_address = perm_address;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", company=" + company + ", id=" + id + ", passport_no=" + passport_no
				+ ", temp_address=" + temp_address + ", perm_address=" + perm_address + ", salary=" + salary + "]";
	}
}

class EmployeeBuilder {
	String name;
	String company;
	int id;
	String passport_no;
	String temp_address;
	String perm_address;
	int salary;
	
	EmployeeBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	EmployeeBuilder setCompany(String company) {
		this.company = company;
		return this;
	}
	
	EmployeeBuilder setId(int id) {
		this.id = id;
		return this;
	}
	
	EmployeeBuilder setPassport_no(String passport_no) {
		this.passport_no = passport_no;
		return this;
	}
	
	EmployeeBuilder setTemp_address(String temp_address) {
		this.temp_address = temp_address;
		return this;
	}
	
	EmployeeBuilder setPer_address(String perm_address) {
		this.perm_address = perm_address;
		return this;
	}
	
	EmployeeBuilder setSalary(int salary) {
		this.salary = salary;
		return this;
	}
	
	Employee build() {
		return new Employee(name, company, id, passport_no, temp_address, perm_address, salary);
	}
}

public class BuilderDesignPatternExample {
	public static void main(String[] args) {
		Employee e = new EmployeeBuilder().setName("Sachin Jain").setSalary(1000).build();
		System.out.println(e);
	}
}
