package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cotrollers.loginB;
import cotrollers.perfiluser;

public class connectDB {
	Connection connection = null;
	Statement stmt = null;
	ResultSet result;
	
	public void register(String name,String user,String email,String pwd) {
		try {
			Class.forName(DB.drive);
			connection = DriverManager.getConnection(DB.url, DB.usr, DB.pwd);
			
			if(connection != null) {
				System.out.println("Connected to database");
			}
			
			String sql = "INSERT INTO register values ('"+name+"','"+user+"','"+email+"','"+pwd+"')";
			stmt = connection.createStatement();
			result = stmt.executeQuery(sql);
			connection.close();
			stmt.close();
		}catch (ClassNotFoundException | SQLException ex) {
		    //System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
	}
	public boolean login(loginB lb) throws ClassNotFoundException{//read
		boolean status = false;
		Class.forName(DB.drive);
		
		try (Connection connection = DriverManager.getConnection(DB.url, DB.usr, DB.pwd);		
			PreparedStatement pstmt = connection.prepareStatement("SELECT *FROM register where email = ? and pwd = ?")){
			
			if(connection!=null) {
				System.out.println("Conexion a la base de datos");
			}
				System.out.println(loginB.email+"  "+loginB.pwd);
				pstmt.setString(1, lb.getEmail());
				pstmt.setString(2, lb.getPwd());
				
				result = pstmt.executeQuery();
				status = result.next();
				System.out.println(status);
			connection.close();
			
		}catch (SQLException ex) {
		    System.out.println(ex);
		}
		return status;
	}
	
	public void perfil(String email) {
		perfiluser perfil= new perfiluser(); 
		String dato;
		
		try {
			Class.forName(DB.drive);
			connection = DriverManager.getConnection(DB.url, DB.usr, DB.pwd);
			
			if(connection != null) {
				System.out.println("Connected to database");
			}
			
			String sql = "SELECT *FROM register where email = '"+email+"'";
			stmt = connection.createStatement();
			result = stmt.executeQuery(sql);
			
			while (result.next()) {
				dato = result.getString(1); 
				perfil.setfName(dato);
				
			}	
			System.out.println(perfil.getEmail());
			connection.close();
			stmt.close();
		}catch (ClassNotFoundException | SQLException ex) {
		    //System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
	}
	
	public boolean delete(loginB lb) throws ClassNotFoundException{
		boolean status = false;
		Class.forName(DB.drive);
		try (Connection connection = DriverManager.getConnection(DB.url, DB.usr, DB.pwd);		
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM account where email=?")){
			
			if(connection!=null) {
				System.out.println("Conexion a la base de datos");
			}
			System.out.println(lb.email);
			pstmt.setString(1, lb.getEmail());
				result = pstmt.executeQuery();
				status = result.next();
				System.out.println(status);
			connection.close();
			
		}catch (SQLException ex) {
		    //printSQLException(ex);
		}
		return status;
	}
	
	public boolean perfil(perfiluser p,loginB lb) throws ClassNotFoundException {
		Class.forName(DB.drive);
		boolean status = false;
		try (
				Connection connection = DriverManager.getConnection(DB.url, DB.usr, DB.pwd);		
				PreparedStatement pstmt = connection.prepareStatement("SELECT *FROM account WHERE email = ?")){
				
				if(connection!=null) {
					System.out.println("Conexion a la base de datos");
				}
				pstmt.setString(1, loginB.email);
				result = pstmt.executeQuery();
				
				while(result.next()) {
					perfiluser.fName = result.getString(1);
					perfiluser.nick = result.getString(2);
					perfiluser.email= result.getString(3);
					perfiluser.date= result.getString(5);
				}
				
				status = result.next();
				connection.close();
				
			}catch (SQLException ex) {
			   // printSQLException(ex);
			}
		return status;
	}
	public boolean update(perfiluser p,loginB lb) throws ClassNotFoundException{
		boolean status = false;
		Class.forName(DB.drive);
		try (
			Connection connection = DriverManager.getConnection(DB.url, DB.usr, DB.pwd);		
			PreparedStatement pstmt = connection.prepareStatement("UPDATE account SET fname = ?, pws = ? WHERE email =? ")){
			
			if(connection!=null) {
				System.out.println("Conexion a la base de datos");
			}
			connectDB db =new connectDB();
			db.perfil(p, lb);
			System.out.println();
			pstmt.setString(1, perfiluser.fName);
			pstmt.setString(2, perfiluser.nick);
			pstmt.setString(3,perfiluser.pwd);
			pstmt.setString(4,perfiluser.email);
			result = pstmt.executeQuery();
				status = result.next();
				System.out.println(status);
			connection.close();
			
		}catch (SQLException ex) {
		    //printSQLException(ex);
		}
		return status;
	}
}
