package manyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class EmployeeManyToMany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id",nullable = false,updatable = false)
	private int emp_id;
	private String name;
	private int salary;
	@ManyToMany(mappedBy = "emp")
	List<LaptopManyToMany> laptop = new ArrayList<LaptopManyToMany>();
	public int getId() {
		return emp_id;
	}
	public void setId(int id) {
		this.emp_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int price) {
		this.salary = price;
	}
	public List<LaptopManyToMany> getLaptop() {
		return laptop;
	}
	public void setLaptop(List<LaptopManyToMany> laptop) {
		this.laptop = laptop;
	}
	@Override
	public String toString() {
		return "EmployeeManyToMany [emp_id=" + emp_id + ", name=" + name + ", salary=" + salary + ", laptop=" + laptop
				+ "]";
	}
}
