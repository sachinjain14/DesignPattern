//Source :: https://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm

package DesignPatternPractice;

import java.util.ArrayList;
import java.util.List;

class Student {
	private String name;
	private int rollNo;
	
	public Student(String name, int rollNo) {
		this.name = name;
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}	
}

interface StudentDao {
	public List<Student> getAllStudents();
	public Student getStudent(int rollNo);
	public void updateStudent(Student student);
	public void deleteStudent(Student student);
}


class StudentDaoImpl implements StudentDao {
	List<Student> students;
	
	public StudentDaoImpl() {
		students = new ArrayList<Student>();
		Student student1 = new Student("Sachin",1);
		Student student2 = new Student("Nitesh",2);
		students.add(student1);
		students.add(student2);
	}
	
	@Override
	public List<Student> getAllStudents() {
		return students;
	}

	@Override
	public Student getStudent(int rollNo) {
		return students.get(rollNo);
	}

	@Override
	public void updateStudent(Student student) {
		students.get(student.getRollNo()).setName(student.getName());
		System.out.println("Student: Roll No "+student.getRollNo()+" ,updated in the database");
	}

	@Override
	public void deleteStudent(Student student) {
		students.remove(student.getRollNo());
		System.out.println("Student: Roll No "+student.getRollNo()+" ,deleted from the database");
	}
	
}

public class DaoDesignPatternExample {
	public static void main(String[] args) {
		StudentDao studentDao = new StudentDaoImpl();
		
		for(Student student : studentDao.getAllStudents()) {
			System.out.println("Student :: [Roll No. : "+student.getRollNo()+", Name : "+student.getName());
		}
		
		System.out.println("\nUpdate method is called");
		Student student = studentDao.getAllStudents().get(0);
		student.setName("Michael");
		studentDao.updateStudent(student);
		
		System.out.println("\nGet information method is called");
		studentDao.getStudent(0);
		System.out.println("Student :: [Roll No. : "+student.getRollNo()+", Name : "+student.getName());
	}
}
