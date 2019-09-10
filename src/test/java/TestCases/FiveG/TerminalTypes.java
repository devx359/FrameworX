package TestCases.FiveG;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TerminalTypes {
	ExtentReports reports;
	ExtentTest test;
	ExtentTest node1;
	ExtentTest node2;
	
	@BeforeClass
	public void setup(ITestContext context)
	{
		reports= (ExtentReports) context.getAttribute("extent");
	}
	
	@Test(priority=1)
	public void addTerminals()
	{
		System.out.println("Inside addTerminals");
		test = reports.createTest("addTerminals");
		node1=test.createNode("add 5G Terminal");
		node1.pass("add 5G Terminal");
	}
	
	@Test(priority=2)
	public void deleteTerminals()
	{
		test = reports.createTest("deleteTerminals");
		node2=test.createNode("delete 5G Terminal").createNode("tc 1");
		node2.pass("tc 1 passed");
		
		test.createNode("delete 5G Terminal").createNode("tc 2").fail("tc 2 fialed");
	}

}
