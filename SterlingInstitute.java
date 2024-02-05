import java.util.Scanner;
import java.sql.*;
public class SterlingInstitute{
	static final String driver = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/sterlinginstitute";
	static final String user = "root";
	static final String pass ="root";
	//Login Page Method
	static boolean login(){
		boolean loginSuccess = false;
		String id1 = "";
		String password1 = "";
		String id = "";
		String password = "";
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter ID : ");
		id = sc.nextLine();
		System.out.print("Enter Password : ");
		password = sc.nextLine();
		
		try{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(DB_URL,user,pass);
			Statement stmt = con.createStatement();
			String query = "SELECT*FROM Login";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				id1 = rs.getString("ID");
				password1 = rs.getString("PASSWORD");
				
				if(id.equals(id1)&&password.equals(password1)){
				loginSuccess = true;
				break;
			}
			else{
				loginSuccess = false;
				
			}
			}

			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return loginSuccess;
	}
	//Sign Up Page Method
	static void signUp(){
		int i = 0;
		int code = 0;
		String id="";
		String password="";
		String name="";
		while(i!=8008){
			Scanner sc = new Scanner(System.in);
		System.out.print("Sign Up \n Two-Step verification:\n");
		code = sc.nextInt();
			if(code==8008){
				Scanner data = new Scanner(System.in);
				System.out.print("Set ID: ");
				id = data.nextLine();
				System.out.print("Set Password: ");
				password = data.nextLine();
				System.out.print("Set Full Name: ");
				name = data.nextLine();
				
				try{
					Class.forName(driver);
			Connection con = DriverManager.getConnection(DB_URL,user,pass);
			String query = "INSERT INTO signup(ID,PASSWORD,NAME)"+"VALUES(?,?,?)";
			String query1 = "INSERT INTO login(id,password)"+"VALUES(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			PreparedStatement ps1 = con.prepareStatement(query1);
			ps.setString(1,id);
			ps.setString(2,password);
			ps.setString(3,name);
			ps1.setString(1,id);
			ps1.setString(2,password);
			int j = ps.executeUpdate();
			ps1.executeUpdate();
			
			if(j>0){
				System.out.print("\n\n Data Inserted Successfully....\n\n");
			}
			else{
				System.out.println("\n\n Data Not Inserted\n\n");
			}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Wrong pin code!!");
				return;
			}
		}
		
	}
	public static void main(String[] args){
		
		int i = 1;
		while(i!=1111){
			System.out.println("\n\t\t\tWELCOME!!\n\t\t   Sterling Institute.");
			System.out.println("Please Enter Key...");
			System.out.println("1. Login\n\n2. Sign Up\n\n3. Exit\n");
			Scanner sc = new Scanner(System.in);
			int key = sc.nextInt();
			switch(key){
				case 1 :
							boolean result = login();
							if(result){
								System.out.println("SuccessFully Login");
							}
							else{
								System.out.println("Wrong UserName or Password");
							}
							break;
				case 2 : 
							signUp();
							System.out.println("Sign Up Page...");
							break;
				case 3 : 
							i =1111;
							break;
			}
		}
	}
}