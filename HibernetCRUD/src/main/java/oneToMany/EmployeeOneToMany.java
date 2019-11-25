package oneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class EmployeeOneToMany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="empId",updatable = false,nullable = false)
	private int empId;
	private String empName;
	private int salary;
	@OneToMany(mappedBy = "emp")
	private List<LaptopOneToMany> laptop= new ArrayList<LaptopOneToMany>();
	public int getId() {
		return empId;
	}
	public void setId(int id) {
		this.empId = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public List<LaptopOneToMany> getLaptop() {
		return laptop;
	}
	public void setLaptop(List<LaptopOneToMany> laptop) {
		this.laptop = laptop;
	}
	
}
