package oneToMany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import HibernetCRUD.HibernetCRUD.Student;
import oneToOne.Employee;
import oneToOne.Laptop;
import utility.Util;

public class App {
	static Configuration con = new Configuration().configure("oneToMany.cfg.xml")
								.addAnnotatedClass(EmployeeOneToMany.class)
								.addAnnotatedClass(LaptopOneToMany.class);
	static ServiceRegistry reg = new ServiceRegistryBuilder()
									.applySettings(con.getProperties())
									.buildServiceRegistry();
	static SessionFactory sf= con.buildSessionFactory(reg);
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
			App.manyToOne(Util.readInt(),Util.readInt());	
			main(args);
			break;
		case 3:System.exit(0);
		default:main(args);
		}		
	}
	
	private static void insert()
	{
		EmployeeOneToMany employee = new EmployeeOneToMany();
		LaptopOneToMany laptop = new LaptopOneToMany();
		
		System.out.println("Enter name of laptop");
		laptop.setName(Util.readString());
		
		System.out.println("Enter price of laptop");
		laptop.setPrice(Util.readInt());
		
		System.out.println("Enter name of employee");
		employee.setEmpName(Util.readString());
		
		System.out.println("Enter salary of employee");
		employee.setSalary(Util.readInt());
		List<LaptopOneToMany> lappyList =new ArrayList<LaptopOneToMany>();
		lappyList = employee.getLaptop();
		lappyList.add(laptop);
		employee.setLaptop(lappyList);
		
		Transaction insert = session.beginTransaction();
		session.save(laptop);
		session.save(employee);
		insert.commit();
		System.out.println("Data Inserted Sussfully");
	}
	private static void show()
	{
		Transaction show = session.beginTransaction();
		Criteria laptopCriteria = session.createCriteria(LaptopOneToMany.class);
		Criteria empCriteria = session.createCriteria(EmployeeOneToMany.class);
		System.out.println("List of Employees");
		List<EmployeeOneToMany> list = (List<EmployeeOneToMany>)empCriteria.list();
		for (EmployeeOneToMany i : list) {
			System.out.println("*********************");
			System.out.println("Employee Id :"+i.getId());
			System.out.println("Name :" + i.getEmpName());
			System.out.println("Salary :" + i.getSalary());
		}
		System.out.println("*********************");
		System.out.println("\n\n\nList of Laptops");
		
		List<LaptopOneToMany> lappyList = (List<LaptopOneToMany>)laptopCriteria.list();
		for (LaptopOneToMany j : lappyList) {
			System.out.println("*********************");
			System.out.println("Laptop Id :"+j.getId());
			System.out.println("Name :"+j.getName());
			System.out.println("Price :"+j.getPrice());
		}
		System.out.println("************************");
		show.commit();
	}
	
	@SuppressWarnings("unchecked")
	private static void manyToOne(int employeeId,int laptopId)
	{
			try {
				Transaction assign = session.beginTransaction();
				Criteria laptopCriteria = session.createCriteria(LaptopOneToMany.class);
				Criteria empCriteria = session.createCriteria(EmployeeOneToMany.class);
				
				laptopCriteria.add(Restrictions.eq("laptopId",laptopId));
				empCriteria.add(Restrictions.eq("empId",employeeId));
				
				List<LaptopOneToMany> lapList = (List<LaptopOneToMany>)laptopCriteria.list();
				List<EmployeeOneToMany> empList = (List<EmployeeOneToMany>)empCriteria.list();
				
				lapList.get(0).setEmp(empList.get(0));
				session.update(lapList.get(0));
				session.update(empList.get(0));
				assign.commit();
				
				System.out.println("Laptop assigned to respective employee");
			}
			catch(Exception e)
			{
				System.out.println("Enter laptop is already assigned to employee");
			}
	}

}
