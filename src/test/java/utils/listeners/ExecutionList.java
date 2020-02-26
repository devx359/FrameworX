package utils.listeners;

import org.testng.IExecutionListener;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;

public class ExecutionList implements IExecutionListener {
	
	public ExtentReports reports;
	ExtentManager ExtentManagerObj;

	@Override
	public void onExecutionStart() {
		/*ExtentManagerObj = new ExtentManager();
		System.out.println("Setting report");
		reports = ExtentManagerObj.GetExtent("10.0.3 Test Results");*/
		
		System.out.println("***** onExecutionStart *******");
		
	}

	@Override
	public void onExecutionFinish() {
		// TODO Auto-generated method stub
		//reports.flush();
		System.out.println("**** onExecutionFinish ******");
		
	}

}
