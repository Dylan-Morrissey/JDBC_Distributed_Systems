import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class JDBC {

	private final String userName = "root";
	private final String password = "root";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "test";
	private final String tableName = "employee";
	public int count = 0;
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);
		return conn;
	}

	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public void run() {
		// Connect to MySQL
		Connection conn = null;
		count = 0;
		employees = new ArrayList<Employee>();
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		try {
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM employee");
			ResultSet rs = s.getResultSet ();

			while (rs.next ()) {
				Employee employee = new Employee();
				employee.setSocialSecurityNumber(rs.getInt("social_security_number"));
				employee.setDateOfBirth(rs.getString("date_of_birth"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setSurname(rs.getString("surname"));
				employee.setSalary(rs.getInt("salary"));
				employee.setGender(rs.getString("gender").charAt(0));
	
				employees.add(employee);
				count ++;
				 }
			rs.close ();
			s.close ();
			System.out.println (count + " rows were retrieved");
			if (count == 0) {
				Employee employee = new Employee();
				employee.setSocialSecurityNumber(-1);
				employee.setDateOfBirth("");
				employee.setFirstName("");
				employee.setSurname("");
				employee.setSalary(-1);
				employee.setGender(" ".charAt(0));
				
				employees.add(employee);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: Could not create the resultSet");
			e.printStackTrace();
			return;
		}
	}
	
	public String search(String surname) {
		try {
			count = 0;
			Connection conn = this.getConnection();
			Statement s = conn.createStatement ();
			s.executeQuery ("SELECT * FROM `employee` WHERE `surname`='"+ surname +"'");
			ResultSet rs = s.getResultSet ();
			employees = new ArrayList<Employee>();
			while (rs.next ()) {
				Employee employee = new Employee();
				employee.setSocialSecurityNumber(rs.getInt("social_security_number"));
				employee.setDateOfBirth(rs.getString("date_of_birth"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setSurname(rs.getString("surname"));
				employee.setSalary(rs.getInt("salary"));
				employee.setGender(rs.getString("gender").charAt(0));
				
				employees.add(employee);
				count ++;
				 }
			rs.close ();
			s.close ();
			System.out.println (count + " rows were retrieved");
			if (count == 0) {
				Employee employee = new Employee();
				employee.setSocialSecurityNumber(0);
				employee.setDateOfBirth("");
				employee.setFirstName("");
				employee.setSurname("");
				employee.setSalary(0);
				employee.setGender(" ".charAt(0));
				
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return surname;
	}

	public Employee create(Employee employee) {
		Connection conn = null;
		
		try {
			conn = this.getConnection();
		    String insertTable = "INSERT INTO `employee`(`social_security_number`, `date_of_birth`, `first_name`, `surname`, `salary`, `gender`) "
		    + "VALUES ("+employee.getSocialSecurityNumber()+",'"
		    +employee.getDateOfBirth()+"','"
			+employee.getFirstName()+"','"
		    +employee.getSurname()+"',"
		    +employee.getSalary()+",'"
		    +employee.getGender()+"')";
		    
			
			this.executeUpdate(conn, insertTable);
			System.out.println("Value inserted table");
			run();
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not insert into the table");
			e.printStackTrace();
		}
		return employee;
	}
	
	public Employee update(Employee employee) {
		Connection conn = null;
		try {
			conn = this.getConnection();
		    String updateEmployee = "UPDATE `employee` SET "
		    		+"`date_of_birth`='"+employee.getDateOfBirth()
		    		+"',`first_name`='"+employee.getFirstName()
		    		+"',`surname`='"+employee.getSurname()
		    		+"',`salary`="+employee.getSalary()
		    		+",`gender`='"+employee.getGender()
		    		+"' WHERE social_security_number="+employee.getSocialSecurityNumber();
		    this.executeUpdate(conn, updateEmployee);
			System.out.println("User updated table");
			run();
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not insert into the table");
			e.printStackTrace();
		}
		return employee;
	}
	
	public Employee delete(Employee employee) {

		try {
			Connection conn = this.getConnection();
			String deleteEmployee = "DELETE FROM `employee` WHERE social_security_number="+employee.getSocialSecurityNumber();    					
			this.executeUpdate(conn, deleteEmployee);
			System.out.println("Value deleted from employee");
			run();
		} catch (SQLException e) {
			System.out.println("ERROR: Could not delete employee");
			e.printStackTrace();
		}
		return employee;
	}
	

	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		JDBC app = new JDBC();
		app.run();
	}
	
		
}