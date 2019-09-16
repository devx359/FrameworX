package TestCases.FiveG;



import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
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
	
	@Test(priority=1,enabled=true)
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
	
	@Test(dataProvider="testSteps",dataProviderClass=utils.TestdataProvider.class,priority=2)
	public void datadrive(Hashtable<String,String> tdata)
	{
		System.out.println(tdata.get("id")+" password: "+tdata.get("password"));
	}
	
	@Test(dataProvider="testSteps",dataProviderClass=utils.TestdataProvider.class,priority=3)
	public void datadrive2(Hashtable<String,String> tdata)
	{
		System.out.println(tdata.get("id")+" password: "+tdata.get("password"));
	}
	
	@Test(priority=4)
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

}
