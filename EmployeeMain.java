package com.montran.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.montran.pojo.Employee;
import com.montran.util.EmployeeUtil;

public class EmployeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		int employeeid;
		String name;
		double salary;
		Employee employee;
		int userChoice;
		EmployeeUtil emputil=new EmployeeUtil();
		String continueChoice;
		boolean result;
		int noofemployees;
		Employee allemployees[]=null;
		Employee employees;
		
		emputil.DriverLoad();
		emputil.CheckConnection();
		
		do
		{
				employees=emputil.getAllEmployees();
				for (Employee emp : allemployees) {
				if(emp!=null)
				{
					System.out.println("-----------------------EmployeeDetails--------------------\n");
					System.out.println("Employee No\t\tEmployeeName\t\tSalary\n" );
					System.out.println(emp.getEmployeeId()+"\t\t\t"+emp.getName()+"\t\t\t"+emp.getSalary());
					System.out.println("\n---------------------------------------------------------\n");
				}
				
			}
			
			
			
			
			
			System.out.println("*******************MENU********************");
			System.out.println("1..Add New Employee");
			System.out.println("2..Add Multiple Employees");
			System.out.println("3..Update Employee");
			System.out.println("4..Delete Employee");
			System.out.println("5..Print Single Employee");
			System.out.println("6.Exit");
			System.out.println("Enter Choice");
			userChoice=sc.nextInt();
			
			switch (userChoice) {
			case 1://addnewemployee
				System.out.println("Enter Id :");
				employeeid=sc.nextInt();
				
				if(employeeid>0)
				{
					System.out.println("Enter name :");
					name=sc.next();
					
					System.out.println("Enter salary:");
					salary=sc.nextDouble();
					
					employee=new Employee(employeeid,name,salary);
					result=emputil.addnewEmployee(employee);
					if(result)
					{
						System.out.println("Employee Added succesfully");
						//emputil.CloseConnection();
						
					}
					else
					{
						System.out.println("Failed To add employee");
					}
				}
				
				else
				{
					System.out.println("Invalid EmployeeId");
				}
				
				break;
				
			case 2://addallemployees
				System.out.println("Enter number of employees");
				noofemployees=sc.nextInt();
				allemployees=new Employee[noofemployees];
				for(int i=0;i<noofemployees;i++)
				{
					System.out.println("Enter Id :");
					employeeid=sc.nextInt();

					
					System.out.println("Enter name :");
					name=sc.next();
					
					System.out.println("Enter salary:");
					salary=sc.nextDouble();
						
					employee = new Employee(employeeid, name, salary);
					allemployees[i] = employee;
				}
				result=emputil.addallEmployee(allemployees);
				if(result==true)
				{
					System.out.println("records added successfully");
					//emputil.CloseConnection();
				}
				else
				{
					System.out.println("failed to add records");
				}
				break;
				
			case 3://update employee
				System.out.println("Enter EmployeeID to Update : ");
				employeeid=sc.nextInt();
				System.out.println("Enter salary");
				salary=sc.nextDouble();
				result=emputil.updateemployeeSalary(employeeid,salary);
				if(result==true)
				{
					System.out.println("Record updated successfully");
					//emputil.CloseConnection();
				}
				else
				{
					System.out.println("Failed to update record");
				}
				
				break;
				
			case 4://delete employee
				System.out.println("Enter EmployeeId to delete employee details");
				employeeid=sc.nextInt();
				result=emputil.deleteEmployee(employeeid);
				if(result==true)
				{
					System.out.println("Record deleted");
				}
				else
					System.out.println("Failed to delete recordyes"
							+ "");
					
				break;
				
			case 5://getemployeebyemployeeid
				
				System.out.println("Enter EmployeeId : ");
				employeeid = sc.nextInt();
				
				employee = emputil.getEmployeeByEmployeeId(employeeid);
				if (employee != null) {
					System.out.println(employee);
				} else
					System.out.println("Employee Not Found !!");
				break;
					
			default:
				System.out.println("Invalid Choice");
				break;
			}
			System.out.println("Do you want to continue : ");
			continueChoice=sc.next();	
		}while(continueChoice.contentEquals("yes")|continueChoice.equals("y"));

	if(continueChoice.equals("no")|continueChoice.equals("n"))
	{
		emputil.CloseConnection();
	}
	
}
}
