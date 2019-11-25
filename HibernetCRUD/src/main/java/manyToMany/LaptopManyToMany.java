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
public class LaptopManyToMany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="laptopId",updatable = false,nullable = false)
	private int laptopId;
	private String name;
	private int price;
	@ManyToMany
	private List<EmployeeManyToMany> emp = new ArrayList<EmployeeManyToMany>();
	public int getLaptopId() {
		return laptopId;
	}
	public void setLaptopId(int laptopId) {
		this.laptopId = laptopId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<EmployeeManyToMany> getEmp() {
		return emp;
	}
	public void setEmp(List<EmployeeManyToMany> emp) {
		this.emp = emp;
	}
	@Override
	public String toString() {
		return "LaptopManyToMany [laptopId=" + laptopId + ", name=" + name + ", price=" + price + ", emp=" + emp + "]";
	}
}
