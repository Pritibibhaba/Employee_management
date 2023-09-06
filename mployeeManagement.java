package Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mployeeManagement {
	static Connection con;
	
	public static int insertEmployee() throws Exception {
		Scanner s=new Scanner(System.in);
		
		System.out.println("Enter Employee Id :: ");
		String empid=s.nextLine();
		
		System.out.println("Enter Employee Name :: ");
		String name=s.nextLine();
		
		System.out.println("Enter Employee Dept :: ");
		String dept=s.nextLine();
		
		System.out.println("Enter Employee Mobile No :: ");
		Long mobile=s.nextLong();
		
		System.out.println("Enter Employee Salary :: ");
		int salary=s.nextInt();
		
		PreparedStatement ps=con.prepareStatement("insert into employedetails values (?,?,?,?,?)");
		ps.setString(1,empid);
		ps.setString(2,name);
		ps.setLong(3,mobile);
		ps.setString(4,dept);
		ps.setInt(5,salary);
		
		int n=ps.executeUpdate();
		
		
		if(n>0) {
			return 1;
		}  else{
			return 0;
		}
		
		
	}
	
	public static  int deleteEmployee() throws Exception {
		Scanner s=new Scanner(System.in);
		
		System.out.println("Enter your Employee Id");
		String emp=s.nextLine();
		
		PreparedStatement ps=con.prepareStatement("delete from employedetails where empid=?");
		ps.setString(1, emp);
		int n=ps.executeUpdate();
		
		if(n>0) {
			return 1;
		} else {
			return 0;
		}
		
		
		
	}
	
	public static void showEmployee() throws Exception{
		
		PreparedStatement ps=con.prepareStatement("select * from employedetails");
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("   "+rs.getString("empid")+"   "+rs.getString("empname")+"    "+rs.getString("empmobile")+"    "+rs.getString("dept")+"   "+rs.getString("salary"));
		}
		System.out.println();
		System.out.println();
		
	}
	
	public static int displayUpdate() {
		Scanner s=new Scanner(System.in);
		System.out.println("What you want to update");
		System.out.println("1. Update Name");
		System.out.println("2. Update Mobile No");
		int a=s.nextInt();
		
		if(a==1) {
			return 1;
		} else {
			return 2;
		} 
	
		
	}
	
	public static int updateEmployee() throws Exception {
		Scanner s=new Scanner(System.in);
		int n=displayUpdate();
		if(n==1) {
			System.out.println("Enter your EmployeeId ::");
			String newid=s.nextLine();
			
			System.out.println("Enter your name ::");
			String newname=s.nextLine();
			
			PreparedStatement ps=con.prepareStatement("update employedetails set empname=? where empid=?");
			ps.setString(1, newname);
			ps.setString(2,newid);
			int q=ps.executeUpdate();
			if(q>0) {
				return 1;
			} else {
				return 0;
			}
			
		} else {
			System.out.println("Enter your EmployeeId ::");
			String newid=s.nextLine();
			
			System.out.println("Enter your Mobile No ::");
			Long newmobile=s.nextLong();
			
			PreparedStatement ps=con.prepareStatement("update employedetails set empmobile=? where empid=?");
			ps.setLong(1, newmobile);
			ps.setString(2,newid);
			int q=ps.executeUpdate();
			if(q>0) {
				return 1;
			} else {
				return 0;
			}
			
		} 
		
	}
	
	public static int display() {
		Scanner s=new Scanner(System.in);
		System.out.println("1.Insert Employee Deatails");
		System.out.println("2.Update Employee Deatails");
		System.out.println("3.Delete Employee Deatails");
		System.out.println("4.Show Employee Deatails");
		System.out.println("5.Logout");
		
		int a=s.nextInt();
		if(a==1) {
			return 1;
		} else if(a==2) {
			return 2;
		} else if(a==3) {
			return 3;
		} else if(a==4) {
			return 4;
		} else {
			return 5;
		}
	}
   public static void main(String[] args) throws Exception {
	   
	   Class.forName("com.mysql.jdbc.Driver");
	   
	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee","root", "W@2915djkq#");
	   
	   while(true) {
		   int n=display();
		   
		   switch(n) {
		   case 1: 
			   int d=insertEmployee();
			   if(d>0) {
					System.out.println("Employee Data insert Sucessfully");
					System.out.println();
				} else {
					System.out.println("Employee Data insert Failed");
					System.out.println();
				}
			   break;
			   
		   case 2:
			   
			   int w=updateEmployee();
			   if(w==1) {
				   System.out.println("Employee Data Update Sucessfully");
				   System.out.println();
			   } else {
				   System.out.println("Employee Data Update Failed");
				   System.out.println();
			   }
			   
			   break;
			   
		   case 3:
			   int q=deleteEmployee();
			   if(q==1) {
				   System.out.println("Employee Data Delete Sucessfully");
				   System.out.println();
			   } else {
				   System.out.println("Employee Data Delete Failed");
				   System.out.println();
			   }
			   break;
			   
		   case 4:
			   showEmployee();
			   break;
		   case 5:
			   System.exit(0);
			   break;
			   
		   }
	   }
   }
}
