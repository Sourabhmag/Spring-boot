package springCore;

public interface Vehicle {
	void drive();
	int getId();
	String getName();
	int getPrice();
	void setId(int id);
	void setName(String name);
	void setPrice(int price);
	String toString();
}
