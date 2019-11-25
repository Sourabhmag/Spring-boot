package HibernetCRUD.HibernetCRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import utility.Util;


/**
 * @author Sourabh Magdum	
 * @Purpose - CRUD operations of Hibernate
 * Date - 11/11/2019
 */
public class App {
	static Configuration con = new Configuration().configure().addAnnotatedClass(Student.class);
	static ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
	static SessionFactory sf = con.buildSessionFactory(reg);
	static Session session = sf.openSession();

	public static void main(String[] args) {
		// App.create();
		 App.read();
		//App.update();
		//App.delete();
	}

	/**
	 * Performs insert operation
	 */
	public static void create() {
		Student student = new Student();

		System.out.println("Enter name of student");
		student.setName(Util.readString());

		System.out.println("Enter phone number");
		student.setPhoneNo(Util.readString());

		System.out.println("Enter address");
		student.setAddress(Util.readString());

		Transaction tx = session.beginTransaction();
		session.save(student);
		tx.commit();

		System.out.println("Done");
	}
	/**
	 * Performs read operation
	 */
	public static void read() {
		session.beginTransaction();
		List<Student> list = (List<Student>) session.createQuery("FROM Student").list();
		for (Student i : list) {
			System.out.println("*********************");
			System.out.println("Name :" + i.getName());
			System.out.println("Phone no " + i.getPhoneNo());
			System.out.println("Address :" + i.getAddress());
		}
		System.out.println("*********************");
		session.getTransaction().commit();
	}
	/**
	 * Performs update operation
	 */
	public static void update() {
		session.beginTransaction();
		System.out.println("Enter phone Number");
		Student student = (Student) session.get(Student.class,Util.readString());
		
		System.out.println("Enter New Name");
		student.setName(Util.readString());

		System.out.println("Enter new Address");
		student.setAddress(Util.readString());

		session.update(student);
		session.getTransaction().commit();
		System.out.println("Data Updated Successfully");
	}
	/**
	 * Performs delete operation
	 */
	public static void delete() {
		session.beginTransaction();
		System.out.println("Enter phone Number");
		Student student = (Student) session.get(Student.class,Util.readString());
		
		session.delete(student);
		session.getTransaction().commit();
		System.out.println("Data Deleteded Successfully");
	}
}
