package academicmanagement.courseservicepublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import databaseconnection.DatabaseConnection;

public class CourseServicesImpl implements CourseServices{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	
	private Connection connection = null;
	private Statement statement = null;
	private  DatabaseConnection databaseConnection;
	private ResultSet resultSet;
	
	Scanner scn = new Scanner(System.in);
	
	public CourseServicesImpl() {
		
		databaseConnection=(DatabaseConnection) new DatabaseConnection();
		connection= databaseConnection.getConnection();
	}
	

	@Override
	public void addCource() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Add Course to Database-----------\n"+ANSI_RESET);
			
		Course course= new Course();
		
		System.out.print("enter course id    - ");
		course.setId(scn.nextInt());
		System.out.print("\n");
		
		System.out.print("enter course Name  - ");
		course.setName(scn.next());
		System.out.print("\n");
		
		System.out.print("enter course Price - ");
		course.setPrice(scn.nextDouble());
		System.out.print("\n");
		
	
		
		   String insertStudentSql = "INSERT INTO course (id, name, price) VALUES (?, ?, ?)";

           try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudentSql)) {
               preparedStatement.setInt(1, course.getId());
               preparedStatement.setString(2, course.getName());
               preparedStatement.setDouble(3, course.getPrice());
              
               int result = preparedStatement.executeUpdate();
               if (result > 0) {
            	   
            	   System.out.println(ANSI_GREEN +"New Course Added to the Database"+ANSI_RESET);
            	   System.out.println(ANSI_BLUE+"CourseID =  "+course.getId()+"   Course Name =  "+course.getName()+"   Course Fee = "+course.getPrice()+ANSI_RESET);

                    
               }
           } catch (SQLException e) {
               System.out.println("Failed to add the course.");
              
           } 
		
	}

	@Override
	public void getAllCourses() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Get All Cources-----------\n"+ANSI_RESET);

		ArrayList<Course> courseList=new ArrayList<Course>();
		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM course";
			resultSet = statement.executeQuery(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	
		
		try {
		      while (resultSet.next()) {
		    	  Course course = new Course();
		    	  
		    	  course.setId(resultSet.getInt("id"));
		    	  course.setName(resultSet.getString("name"));
		    	  course.setPrice(resultSet.getDouble("price"));
			    	 
			    	  courseList.add(course);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-10s | %-32s | %-20s |%n";
		
		System.out.printf("Course List\n");

		
		
		System.out.format(ANSI_BLUE+"+------------+----------------------------------+----------------------+%n");
		System.out.format(leftAlignFormat, "Course ID", "Course Name", "Course Fee");
		System.out.format("+------------+----------------------------------+----------------------+%n");

		for(Course courseModel: courseList){
			 System.out.format(leftAlignFormat, courseModel.getId(), courseModel.getName(), courseModel.getPrice());
	      } 


System.out.format("+------------+----------------------------------+----------------------+%n"+ANSI_RESET);
		
	}

	@Override
	public void getCouseByID() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Get Cource By ID-----------\n"+ANSI_RESET);
		
		int courseId;
		
		System.out.print("Enter Course ID : ");
		courseId = Integer.parseInt(scn.next());
		
		String query = "SELECT * FROM course WHERE id = '"+ courseId +"'";
		
		
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			
			
		      while (resultSet.next()) { 
		    	  System.out.println("\n");
		    	  System.out.println("Details of Course ID- " + courseId);
		    	  
		    	  System.out.format(ANSI_BLUE+"+------------------+--------------------------------+------------------+%n");
		    	  System.out.format("|    Course ID     |   Course Name                  |    Course Fee    |%n");
		    	  System.out.format("+------------------+--------------------------------+------------------+%n");
		    	  
		    	  System.out.printf("|%10d        |%s         |%10.2f        |\n", resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("Price"));
   	
		    	  System.out.format("+------------------+--------------------------------+------------------+%n"+ANSI_RESET);
		      }
		} catch (SQLException exc) {
			System.out.println("Error Occured");
			System.out.println(exc.getMessage());
		}
		
	}

	@Override
	public void deleteCourseByID() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Delete Cources By ID-----------\n"+ANSI_RESET);

		ArrayList<Course> courseList=new ArrayList<Course>();
		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM course";
			resultSet = statement.executeQuery(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		try {
		      while (resultSet.next()) {
		    	  Course course = new Course();
		    	  
		    	  course.setId(resultSet.getInt("id"));
		    	  course.setName(resultSet.getString("name"));
		    	  course.setPrice(resultSet.getDouble("price"));
			    	 
			    	  courseList.add(course);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-10s | %-32s | %-20s |%n";
		
		System.out.printf("Course List\n");

		
		
		System.out.format(ANSI_BLUE+"+------------+----------------------------------+----------------------+%n");
		System.out.format(leftAlignFormat, "Course ID", "Course Name", "Course Fee");
		System.out.format("+------------+----------------------------------+----------------------+%n");

		for(Course courseModel: courseList){
			 System.out.format(leftAlignFormat, courseModel.getId(), courseModel.getName(), courseModel.getPrice());
	      } 


System.out.format("+------------+----------------------------------+----------------------+%n"+ANSI_RESET);
		int courseID;
		
		System.out.print("Enter Course ID To Delete : ");
		courseID = Integer.parseInt(scn.next());
		String query ="delete from course where id='"+ courseID+"' ";
				
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("\nCourse with Course ID= "+courseID+ "  Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error occured while deleting");
			System.out.println(exc.getMessage());
		}


}


	
	@Override
	public void updateCourse() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Update Course-----------\n"+ANSI_RESET);
		ArrayList<Course> courseList=new ArrayList<Course>();
		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM course";
			resultSet = statement.executeQuery(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		try {
		      while (resultSet.next()) {
		    	  Course course = new Course();
		    	  
		    	  course.setId(resultSet.getInt("id"));
		    	  course.setName(resultSet.getString("name"));
		    	  course.setPrice(resultSet.getDouble("price"));
			    	 
			    	  courseList.add(course);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-10s | %-32s | %-20s |%n";
		
		System.out.printf("Course List\n");

		
		
		System.out.format(ANSI_BLUE+"+------------+----------------------------------+----------------------+%n");
		System.out.format(leftAlignFormat, "Course ID", "Course Name", "Course Fee");
		System.out.format("+------------+----------------------------------+----------------------+%n");

		for(Course courseModel: courseList){
			 System.out.format(leftAlignFormat, courseModel.getId(), courseModel.getName(), courseModel.getPrice());
	      } 


System.out.format("+------------+----------------------------------+----------------------+%n"+ANSI_RESET);
		
	    int courseId;
	    System.out.print("Enter Course ID To Update : ");
	    courseId = Integer.parseInt(scn.next());

	    String query = "SELECT * FROM course WHERE id = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setInt(1, courseId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            System.out.print("Enter new name for course: ");
	            String newName = scn.next();
	            System.out.print("Enter new price for course: ");
	            Double newPrice = scn.nextDouble();

	            String updateQuery = "UPDATE course SET name = ?, price = ? WHERE id = ?";
	            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
	                updateStatement.setString(1, newName);
	                updateStatement.setDouble(2, newPrice);
	                updateStatement.setInt(3, courseId);
	                int result = updateStatement.executeUpdate();
	                if (result > 0) {
	                	System.out.println("\n");
	                	System.out.println(ANSI_BLUE+"Course ID= " + courseId + "  Updated with New name= " + newName + "  New price= " + newPrice+ANSI_RESET );

	                   
	                }
	            } catch (SQLException e) {
	                System.out.println("Failed to update the course.");
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Course with ID " + courseId + " not found.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error occurred while updating course.");
	        e.printStackTrace();
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}
	

	
}
