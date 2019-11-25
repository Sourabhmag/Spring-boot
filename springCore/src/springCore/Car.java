package springCore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Sourabh Magdum
 * @Purpose - Model class to understand Annotation based dependency injection
 *  Date - 13/11/2019
 */
@Component
public class Car implements Vehicle {
	private int id;
	private String name;
	private int price;
	@Autowired
	private Tyre tyre;
	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("driving car");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Tyre getTyre() {
		return tyre;
	}

	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", price=" + price + ", tyre=" + tyre + "]";
	}

}
