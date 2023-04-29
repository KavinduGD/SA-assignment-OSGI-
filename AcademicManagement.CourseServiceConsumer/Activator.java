package academicmanagement.courseserviceconsumer;

import academicmanagement.courseservicepublisher.CourseServices;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	

	ServiceReference reference;

	public void start(BundleContext context) throws Exception {
		System.out.println(ANSI_YELLOW+"Customer Consumer Start"+ANSI_RESET);
		 reference = context.getServiceReference(CourseServices.class.getName());
		CourseServices courseServices= (CourseServices)context.getService(reference);
		
		//courseServices.updateCourse();
		courseMethod(courseServices);
	}
	
	private void courseMethod(CourseServices courseServices) {
		int option;
		String subOption="y";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println(ANSI_RED+"                    ----------Course Management Section ----------\n"+ANSI_RESET);
		System.out.println("                         1  - Add course to the Database");
		System.out.println("                         2  - Get all Course in the Database");
		System.out.println("                         3  - Get Course By the Id ");
		System.out.println("                         4  - Update Course by the Id");
		System.out.println("                         5  - Delete Course by the Id");
		System.out.println("\n                  --------------------------------------------------");
		System.out.println("\n                  --------------------------------------------------");
		
		
		System.out.print("\n\nChoose an option : ");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
		case 1:
			courseServices.addCource();
			
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to Add Another Course (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.addCource();
				}
			}
			courseMethod(courseServices);
			break;
		case 2:
			courseServices.getAllCourses();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to See All Courses again (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.getAllCourses();
				}
			}
			courseMethod(courseServices);
			break;
		case 3:
			courseServices.getCouseByID();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want get another Course (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.getCouseByID();
				}
			}
			courseMethod(courseServices);
			break;
		case 4:
			courseServices.updateCourse();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to update again(y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.updateCourse();
				}
			}
			courseMethod(courseServices);
			break;
		case 5:
			courseServices.deleteCourseByID();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to delete another Course (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.deleteCourseByID();
				}
			}
			courseMethod(courseServices);
			break;
		default:
			System.out.println("Incorrect Input. Try Again...");
			courseMethod(courseServices);
	}
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println(ANSI_YELLOW+"Course Consumer Stop"+ANSI_RESET);
		context.ungetService(reference);
	}

}
