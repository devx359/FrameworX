package TestCases.FiveG;



import java.util.Hashtable;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.json.simple.JSONObject;

import config.PathUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.*;
import userDefinedFunctions.UDF5_OtherFunctions;
import static io.restassured.RestAssured.given;


public class Test1 {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	
	
	@BeforeClass
	public void setup(ITestContext context)
	{
		driver=(WebDriver) context.getAttribute("driver");
		report=(ExtentReports) context.getAttribute("extent");
		
	}
	
	@Test(priority=1,enabled=false)
	public void meth(ITestContext context) throws InterruptedException
	{
		throw new NullPointerException();
		/*test=report.createTest("nokia red test");
		context.setAttribute("test", test);
		driver.get("https://github.com/nokia/RED");
		Thread.sleep(2000);
		UDF5_OtherFunctions udf = new UDF5_OtherFunctions(context);
		
		udf.Click("issues");
		Thread.sleep(2000);*/
	}
	
	@Test(dataProvider="testSteps",dataProviderClass=utils.TestdataProvider.class,priority=2 ,enabled=false)
	public void datadrive(Hashtable<String,String> tdata)
	{
		System.out.println(tdata.get("id")+" password: "+tdata.get("password"));
	}
	
	@Test(dataProvider="testSteps",dataProviderClass=utils.TestdataProvider.class,priority=3,enabled=false)
	public void datadrive2(Hashtable<String,String> tdata)
	{
		System.out.println(tdata.get("id")+" password: "+tdata.get("password"));
	}
	
	@Test(priority=4,enabled=false)
	public void apitesting()
	{
		JSONObject jsonreq = new JSONObject();
		jsonreq.put("name", "morpheus");
		jsonreq.put("job", "leader");
		

		System.out.println("JSON Request " + jsonreq);
		RestAssured.baseURI = "https://reqres.in/api/users";
		RestAssured.useRelaxedHTTPSValidation();// Resolved SSL Server certificate validation exceptions
		Response res = null;
		try {
			res =given().body(jsonreq).when().contentType(ContentType.JSON).post().then()
					/* .contentType(ContentType.JSON) */.extract().response();
		} catch (Exception e1) {

			e1.printStackTrace();
			test.fatal("Login API Connection Issue: " + e1);
		}
		ResponseBody body = res.getBody();
		System.out.println("Response Body is: " + body.asString());
		// Extracting response from JSON API response
		// System.out.println("Login API response: "+res);
		System.out.println("Creation timestamp "+res.body().path("createdAt").toString());
		System.out.println(res.getStatusCode());
		System.out.println(res.getTime());
		System.out.println(res.prettyPrint());
		
	}
	
	@Test(priority=5)
	public void gtest() throws InterruptedException
	{
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Ranorex");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(.,'ranorex tutorial')]")).click();
		
		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='r']/a"));
		
		
		for(WebElement el: ele)
		{
			System.out.println(el.findElement(By.xpath("h3/div")).getText());
			System.out.println(el.getAttribute("href"));
			
		}
		
		driver.findElement(By.xpath("//a[@id='pnnext']")).click();
		System.out.println("next page");
		List<WebElement> ele2 = driver.findElements(By.xpath("//div[@class='r']/a"));
		
		
		for(WebElement el: ele2)
		{
			System.out.println(el.findElement(By.xpath("h3/div")).getText());
			System.out.println(el.getAttribute("href"));
			
		}
		
		Thread.sleep(2000);
		
		WebElement link=driver.findElement(By.xpath("//div[contains(text(),'Ranorex - Automated Testing Tool for Desktop, ')]/ancestor::a"));
		
		Actions action = new Actions(driver);
		action.contextClick(link).sendKeys(Keys.ENTER).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
