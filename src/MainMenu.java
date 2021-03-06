
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class MainMenu {
	static Scanner in = new Scanner(System.in);
	
	/**
	 * Program runs from here
	 * @param args
	 */
	public static void main(String[] args){
		TreeMap<Integer, Person> people = new TreeMap<Integer, Person>();
		ArrayList<College> initialColleges = new ArrayList<College>();
		
		//Need multiple list of departments
		ArrayList<Department> inDeps1 = new ArrayList<Department>();
		ArrayList<Major> inMaj1 = new ArrayList<Major>();
		ArrayList<Course> inCor1 = new ArrayList<Course>();
		
		ArrayList<Department> inDeps2 = new ArrayList<Department>();
		ArrayList<Major> inMaj2 = new ArrayList<Major>();
		ArrayList<Course> inCor2 = new ArrayList<Course>();
		
		List<Course> cecs274pre = new ArrayList<Course>();
		List<Course> phys152pre = new ArrayList<Course>();
		
		List<Student> cecs174en = new ArrayList<Student>();
		List<Student> phys152en = new ArrayList<Student>();
		
		
		//Students
		people.put(986, new Student("Juan", "Garcia", "C", 986));
		people.put(432, new Student("Gary", "Stone", "", 432));
		
		//Professors
		people.put(175, new Professor("Melissa", "Cereal", "G", 175, 40000.00));
		people.put(764, new Professor("Jon", "Tron", "T", 764, 20000.00));
		
		//Admins
		people.put(345, new Admin("Malik", "Coleman", "K", 345, 300.00, true));
		people.put(323, new Admin("Jessica", "Trejo", "V", 323, 400.00, false));
		
		//Employees
		people.put(918, new Employee("Izuku", "Midoriya", "",  918, 20.00 ));
		people.put(421, new Employee("Shoto", "Todoroki", "", 743, 19.00));
	
		cecs174en.add((Student) people.get(986));
		cecs174en.add((Student) people.get(432));
		
		phys152en.add((Student) people.get(986));
		
		
		Course cecs174 = new Course("CECS 174", "1:00 pm", "2:00pm", "VEC-121", "FALL 2019"
				, (Professor) people.get(764), 25, 25.00);
		
		cecs274pre.add(cecs174);
		
		Course cecs274 = new Course("CECS 274", "3:00 pm", "4:00pm", "ECS-312", "FALL 2019"
				, (Professor) people.get(764), 25, cecs274pre, null, 25.00);
		
		Course phys151 = new Course("PHYS 151", "9:00 am", "10:00 am", "HSCI-103", "FALL 2019"
				, (Professor) people.get(175), 50, null, phys152en, 30.00);
		
		phys152pre.add(phys151);
		
		Course phys152 = new Course("PHYS 152", "9:00 am", "10:00am", "HSCI-102", "FALL 2019"
				, (Professor) people.get(175), 50, phys152pre, null, 30.00);
		
		
		inCor1.add(cecs174);
		inCor1.add(cecs274);
		
		inCor2.add(phys151);
		inCor2.add(phys152);
		
		inMaj1.add(new Major("Computer Science", inCor1));
		inDeps1.add(new Department("Computer Science", inMaj1, (Employee)people.get(918)));
		
		initialColleges.add(new College("Engineering", inDeps1, people.get(764)));
		initialColleges.add(new College("Natural Sciences"));

		
		//This object holds everything
		University uni = new University("California State University, Longer Beach", initialColleges, people);
		
		//Go time
		menu(uni);
		
	}
	
	/**
	 * User logs in here
	 * @param uni
	 */
	public static void menu(University uni){
		int exit = 1;
		do{
			Person user = login(uni);
			
			if(user instanceof Admin){
				if (((Admin) user).isSuper()) {
					superAdminMenu(uni, user);
				} else {
					AdminMenu(uni, user);
				}

			}else if( user instanceof Student){
				StudMenu(uni, (Student) user);
			}else{
				ProfMenu(uni, (Professor) user);
			}
			System.out.println("You have been logged out. \n");
		}while(exit == 1);	
	}
	
	
	/**
	 * Menu if user is Professor
	 * @param uni
	 * @param user
	 */
	private static void ProfMenu(University uni, Professor user){
		int choice;
		do{
			System.out.print("\nPROFESSOR MENU"
					+ "\n1. View Schedule");
			choice = Integer.parseInt(in.nextLine());
			
			if(choice == 1){
				user.displaySchedule();
			}
		}while(choice != 2);
	}

	/**
	 * Menu if user is Student
	 * @param uni
	 * @param user
	 */
	private static void StudMenu(University uni, Student user){
		int choice;
		do{
			System.out.print("\nSTUDENT MENU "
					+ "\n1. Enroll in Course "
					+ "\n2. Drop a Course "
					+ "\n3. View Schedule"
					+ "\n4. View Courses Completed"
					+ "\n5. Select Major "
					+ "\n6. Pay Tuition"
					+ "\n7. Exit "
					+ "\nEnter here: ");
			choice = Integer.parseInt(in.nextLine());

			if(choice == 1){
				enrollCourse(user, uni);
			}else if(choice == 2){
				dropCourse(user, uni);
			}else if(choice == 3){
				user.viewCurrentSchedule();
			}else if(choice == 4){
				user.viewCoursesTaken();
			}else if(choice == 5){
				selectMajor(user, uni);
			}else if(choice == 6){
				user.payTuition();
			}else if(choice == 7){
				return;
			}
		}while(choice != 7);
	}

	/**
	 * Menu if user is Admin
	 * @param uni
	 * @param user
	 */
	private static void AdminMenu(University uni, Person user){
		int choice;
		do{
		System.out.print("\nADMININSTRATOR MENU "
				+ "\n1. University Menu(UniversityName/Students/Employees)"
				+ "\n2. Other Menus(Colleges/Departments/Majors/Courses) "
				+ "\n3. Exit "
				+ "\nEnter here: ");
		choice = Integer.parseInt(in.nextLine());
		
		if(choice == 1){
			uni.uniMenu();
		}else if(choice == 2){
			uni.collegeMenu();
		}else if(choice == 3){
			return;
		}
		}while(choice != 3);
	}
	
	/**
	 * Menu if user is Super Admin
	 * @param uni
	 * @param user
	 */
	private static void superAdminMenu(University uni, Person user){
		int choice;
		do{
		System.out.print("\nSUPER ADMININSTRATOR MENU "
				+ "\n0. Add Admin"
				+ "\n1. University Menu(UniversityName/Students/Employees)"
				+ "\n2. Other Menus(Colleges/Departments/Majors/Courses) "
				+ "\n3. Exit "
				+ "\nEnter here: ");
		choice = Integer.parseInt(in.nextLine());
		
		if(choice == 1){
			uni.uniMenu();
		}else if(choice == 2){
			uni.collegeMenu();
		}else if(choice == 3){
			return;
		} else if (choice == 0) {
			uni.addAdmin();
		}
		}while(choice != 3);
	}
	
	
	/**
	 * Enrolls Students in a course
	 * @param s
	 * @param u
	 */
	public static void enrollCourse(Student s, University u){
		
		if(s.getCurrentUnits() >= 20){
			System.out.println("Max enrollment units reached!");
			return;
		}
		
		String name;
		Course cor;
		
		u.displayAllCourses();
		
		do{
			do{
				System.out.println("Enter name of class to add: ");
				name = in.nextLine();
			}while(u.findCourse(name) == null);

			cor = u.findCourse(name);

		}while(cor.addStudent(s) == false); 
		s.addSession(cor);
		s.calcCurrentUnits();

		 	
	}
	
	
	/**
	 * Drops student from a course
	 * @param s
	 * @param u
	 */
	public static void dropCourse(Student s, University u){
		
		if(s.getCurrentUnits() == 0){
			System.out.println("Not enrolled in any classes!");
			return;
		}
		
		String name;
		
		s.viewCurrentSchedule();
		
		do{
			System.out.println("Enter name of class to drop: ");
			name = in.nextLine();
		}while(u.findCourse(name) == null);
		
		Course cor = u.findCourse(name);
		
		cor.dropStudent(s);
		s.dropSession(cor);
		s.calcCurrentUnits();
	}
	
	
	/**
	 * Student selects major 
	 * @param s
	 * @param u
	 */
	public static void selectMajor(Student s, University u){
		String name;
		
		u.displayAllMajors();
		
		if (s.getMajor() == null) {
			System.out.println("\nCurrent Major: none");
		} else {
			System.out.println("\nCurrent Major: " + s.getMajor());
		}

		
		do{
			System.out.print("Enter new Major: ");
			name = in.nextLine();
			
		}while(u.findMajor(name) == null);
			
		s.setMajor(u.findMajor(name));
		System.out.println("Major Changed To: " + name);
	}

	
	/**
	 * Login information is checked and user is found
	 * @param uni
	 * @return Person
	 */
	public static Person login(University uni){
		int id = 0;
		
		do{
			System.out.print("Welcome to " + uni.getUniName() + 
					"!\nPlease enter your ID number: ");
			id = Integer.parseInt(in.nextLine());
		}while(uni.findPerson(id) == null);
		
		return uni.findPerson(id);
	}
	
}
