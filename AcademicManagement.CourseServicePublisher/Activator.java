package academicmanagement.courseservicepublisher;

import java.security.Provider.Service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RESET = "\u001B[0m";

	ServiceRegistration registration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println(ANSI_YELLOW+"Course Registration Service Start"+ANSI_RESET);
		CourseServices courseServices=new CourseServicesImpl();
		registration= context.registerService(CourseServices.class.getName(),courseServices, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println(ANSI_YELLOW+"Course Registration Service Stop"+ANSI_RESET);
		registration.unregister();
	}

}
