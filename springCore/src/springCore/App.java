package springCore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import utility.Util;

public class App {

	public static void main(String[] args) {
		ApplicationContext context  = new ClassPathXmlApplicationContext("spring.xml");
		Vehicle car = (Vehicle) context.getBean("car");
		
		//Constructor injection
		Tyre tyre = (Tyre)context.getBean("tyre");
		System.out.println(tyre+"\n");
		
		Bike bike = (Bike)context.getBean("bike");
		System.out.println("Enter id of car");
		car.setId(Util.readInt());
		
		System.out.println("Enter Name of car");
		car.setName(Util.readString());
		
		System.out.println("Enter price of car");
		car.setPrice(Util.readInt());
		
		//Example of annotation based dependency injection
		car.drive();
		System.out.println("Car id :"+car.getId());
		System.out.println("Car Name :"+car.getName());
		System.out.println("Car price :"+car.getPrice());
		System.out.println(car);
		
		System.out.println();
		
		//Example of Setter injection
		bike.drive();
		System.out.println("bike id :"+bike.getId());
		System.out.println("bike Name :"+bike.getName());
		System.out.println("bike price :"+bike.getPrice());
		System.out.println(bike);
		((ConfigurableApplicationContext)context).close();
	}

}
