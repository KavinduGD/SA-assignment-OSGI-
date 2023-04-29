package databaseconnection;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {
	 public static final String ANSI_RESET = "\u001B[0m";
	   public static final String ANSI_YELLOW = "\u001B[33m";

	private ServiceRegistration<?> databaseConnectionService;


	public void start(BundleContext context) throws Exception {
		System.out.println(ANSI_YELLOW+"Starting DB Connection"+ANSI_RESET);
		
		 DatabaseConnection databaseConnection = new DatabaseConnection();
	      databaseConnectionService = context.registerService(
	       IDatabaseConnection.class.getName(), databaseConnection, null);
	  
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println(ANSI_YELLOW+"Stoping DB Connection"+ANSI_RESET);
		if (databaseConnectionService != null) {
            databaseConnectionService.unregister();
            databaseConnectionService = null;
        }
	}

}
