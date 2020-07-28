package com.montran.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.montran.pojo.Employee;

public class EmployeeUtil {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "montran";
	String password = "montran";
	String sql = "";
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	Connection connection;
	
	
	public void DriverLoad()
	{
		try {
			Class.forName(driver);
			System.out.println("Driver loaded successfully.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void CheckConnection()
	{
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			if (connection != null) {
				System.out.println("Connection Success !! ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void CloseConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	public boolean addnewEmployee(Employee employee) {
		
		sql= "insert into employee_details values(?,?,?)";
		try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, employee.getEmployeeId());
				preparedStatement.setString(2, employee.getName());
				preparedStatement.setDouble(3, employee.getSalary());
				preparedStatement.executeUpdate();
				
				
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	public boolean addallEmployee(Employee employee[])
	{
		for (Employee emp : employee) {
			sql= "insert into employee_details values(?,?,?)";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, emp.getEmployeeId());
				preparedStatement.setString(2, emp.getName());
				preparedStatement.setDouble(3, emp.getSalary());

				
				preparedStatement.executeUpdate();
				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return false;
	}
	
	public boolean updateemployeeSalary(int employeeId ,  double newSalary)
	{
		sql = "update employee_details set salary=? where employee_id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, newSalary);
			preparedStatement.setInt(2, employeeId);
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean deleteEmployee(int employeeId)
	{
		sql = "delete from employee_details where employee_id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employeeId);
			preparedStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Employee getEmployeeByEmployeeId(int employeeId)
	{
		sql = "select * from employee_details where employee_id=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,employeeId);

			preparedStatement.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Employee getAllEmployees()
	{
		sql = "select * from employee_details"  ;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println("Employee Id :: " + resultSet.getInt("employee_id"));
				System.out.println("Name :: " + resultSet.getString("name"));
				System.out.println("Salary :: " + resultSet.getDouble("salary"));
				System.out.println("----------------------------------------");
			}
			//connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
		
		
		
	}
	
	
	
	
	
	
		
	
	

}



	
	
	
	
	
	
		

	


