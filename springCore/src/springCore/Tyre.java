package springCore;

/**
 * @author Sourabh Magdum
 * @Purpose - Model class to understand XML based dependency injection(Constructor injection)
 * Constructor Injection - values set by using Constructor so it is called Constructor injection
 *  Date - 13/11/2019
 */
public class Tyre {
	private String brand;
	public Tyre(String brand) {
		super();
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Tyre [brand=" + brand + "]";
	}
	
}
