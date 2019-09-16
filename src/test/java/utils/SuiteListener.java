package utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener{
	@Override
	  public void onFinish(ISuite suite) {
	    System.out.println("Finishing Suite");
	    
	  }
	 
	  @Override
	  public void onStart(ISuite suite) {
	    System.out.println("Starting suite");
	  }


}
