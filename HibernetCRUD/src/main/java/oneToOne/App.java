package oneToOne;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import utility.Util;

public class App {

	static Configuration con = new Configuration().configure("oneToOne.cfg.xml")
			.addAnnotatedClass(Employee.class)
			.addAnnotatedClass(Laptop.class);
	
	static ServiceRegistry reg = new ServiceRegistryBuilder()
			.applySettings(con.getProperties())
			.buildServiceRegistry();
	
	static SessionFactory sf = con.buildSessionFactory(reg);
	static Session session = sf.openSession();
	
	public static void main(String[] args) {
		
		System.out.println("Enter your choice\n1)Insert date\n2)Assign Data\n3)Exit");
		int choice = Util.readInt();
		switch(choice)
		{
		case 1:
			App.insert();
			main(args);
			break;
		case 2:
			App.show();
			System.out.println("Enter Employee id and Laptop id");
			App.oneToOne(Util.readInt(),Util.readInt());	
			main(args);
			break;
		case 3:System.exit(0);
		default:main(args);
		
		}
	}
	private static void insert()
	{
		Employee emp= new Employee();
		Laptop laptop = new Laptop();
		
		System.out.println("Enter name of laptop");
		laptop.setName(Util.readString());
		
		System.out.println("Enter price of laptop");
		laptop.setPrice(Util.readInt());
		
		System.out.println("Enter name of employee");
		emp.setEmpName(Util.readString());
		
		System.out.println("Enter Salary of employee");
		emp.setSalary(Util.readInt());
		emp.setLaptop(laptop);
		
		session.beginTransaction();
		session.save(laptop);
		session.save(emp);
		session.getTransaction().commit();
		
		System.out.println("Data inserted successfully");
	}
	private static void show()
	{
		Transaction show = session.beginTransaction();
		Criteria laptopCriteria = session.createCriteria(Laptop.class);
		Criteria empCriteria = session.createCriteria(Employee.class);
		System.out.println("List of Employees");
		List<Employee> list = (List<Employee>)empCriteria.list();
		for (Employee i : list) {
			System.out.println("*********************");
			System.out.println("Employee Id :"+i.getEmpId());
			System.out.println("Name :" + i.getEmpName());
			System.out.println("Salary :" + i.getSalary());
		}
		System.out.println("*********************");
		System.out.println("\n\n\nList of Laptops");
		
		List<Laptop> lappyList = (List<Laptop>)laptopCriteria.list();
		for (Laptop j : lappyList) {
			System.out.println("*********************");
			System.out.println("Laptop Id :"+j.getId());
			System.out.println("Name :"+j.getName());
			System.out.println("Price :"+j.getPrice());
		}
		System.out.println("************************");
		show.commit();
	}
	private static void oneToOne(int empId,int laptopId)
	{
		Laptop laptop = (Laptop)session.get(Laptop.class,laptopId);
		Employee emp = (Employee)session.get(Employee.class,empId);
		
		emp.setLaptop(laptop);
		session.beginTransaction();
		session.update(emp);
		session.getTransaction().commit();
	}

}
