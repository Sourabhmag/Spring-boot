package manyToMany;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import oneToMany.EmployeeOneToMany;
import oneToMany.LaptopOneToMany;
import utility.Util;

public class App {
	
	static Configuration con = new Configuration().configure("manyToMany.cfg.xml")
			.addAnnotatedClass(EmployeeManyToMany.class)
			.addAnnotatedClass(LaptopManyToMany.class);
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
			App.assign(Util.readInt(),Util.readInt());	
			main(args);
			break;
		case 3:System.exit(0);
		default:main(args);
		}		

	}
	private static void insert()
	{
		EmployeeManyToMany emp= new EmployeeManyToMany();
		LaptopManyToMany laptop = new LaptopManyToMany();
		
		System.out.println("Enter laptop name");
		laptop.setName(Util.readString());
		
		System.out.println("Enter laptop price");
		laptop.setPrice(Util.readInt());
		
		System.out.println("Enter name of employee");
		emp.setName(Util.readString());
		
		System.out.println("Enter salary of employee");
		emp.setSalary(Util.readInt());
		
		
		session.beginTransaction();
		session.save(laptop);
		session.save(emp);
		session.getTransaction().commit();
	}
	private static void show()
	{
		Transaction show = session.beginTransaction();
		Criteria laptopCriteria = session.createCriteria(LaptopManyToMany.class);
		Criteria empCriteria = session.createCriteria(EmployeeManyToMany.class);
		System.out.println("List of Employees");
		List<EmployeeManyToMany> list = (List<EmployeeManyToMany>)empCriteria.list();
		for (EmployeeManyToMany i : list) {
			System.out.println("*********************");
			System.out.println("Employee Id :"+i.getId());
			System.out.println("Name :" + i.getName());
			System.out.println("Salary :" + i.getSalary());
		}
		System.out.println("*********************");
		System.out.println("\n\n\nList of Laptops");
		
		List<LaptopManyToMany> lappyList = (List<LaptopManyToMany>)laptopCriteria.list();
		for (LaptopManyToMany j : lappyList) {
			System.out.println("*********************");
			System.out.println("Laptop Id :"+j.getLaptopId());
			System.out.println("Name :"+j.getName());
			System.out.println("Price :"+j.getPrice());
		}
		System.out.println("************************");
		show.commit();
	}
	private static void assign(int empId,int laptopId)
	{
		LaptopManyToMany laptop = (LaptopManyToMany)session.get(LaptopManyToMany.class,laptopId);
		EmployeeManyToMany emp = (EmployeeManyToMany)session.get(EmployeeManyToMany.class,empId);
		
		laptop.getEmp().add(emp);
		
		session.beginTransaction();
		session.update(emp);
		session.update(laptop);
		session.getTransaction().commit();
	}

}
