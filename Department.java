import java.util.ArrayList;

public class Department {
	private String name;
	private int maxmajors=50;
	//private Employee DepartmentChair;
	//ArrayList <Major> majors = new ArrayList<Major>();
	//To test
	ArrayList <String> majors = new ArrayList<String>();

	public Department() {
		name= "(void)";
		
	}
	
	//Include: Employee _DepartmentChair
	//Include: ArrayList _majors
	public Department(String _name) {
		this.name =_name;
		//this.DepartmentChair= _DepartmentChair
		//this.majors= _majors
		
	}
	//Final Parameter must be an object of type major
	public void addMajor (String major) {
		if (majors.size()<=maxmajors)
			majors.add(major);
		else
			System.out.println("Maximum number of majors in Department reached");
	}
	//Final Parameter must be an object of type major
	public void deleteMajor (String major) {
		majors.remove(major);
	}
	
}

	