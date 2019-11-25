package oneToMany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class LaptopOneToMany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="laptopId",updatable=false,nullable=false)
	private int laptopId;
	private String name;
	private int price;
	@ManyToOne
	private EmployeeOneToMany emp;

	public int getId() {
		return laptopId;
	}

	public void setId(int id) {
		this.laptopId = id;
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

	public EmployeeOneToMany getEmp() {
		return emp;
	}

	public void setEmp(EmployeeOneToMany emp) {
		this.emp = emp;
	}
	
}
