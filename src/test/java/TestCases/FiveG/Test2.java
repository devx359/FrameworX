package TestCases.FiveG;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.jayway.restassured.RestAssured.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/*import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;*/
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;

import junit.framework.Assert;

public class Test2 {
	
	@Test(enabled=false)
	public void meth()
	{
		RestAssured.baseURI="https://reqres.in/api";
		
		JSONObject req= new JSONObject();
		req.put("name", "morpheus");
		req.put("job","leader");
		
		System.out.println(req);
		
		//post
		Response res = given().relaxedHTTPSValidation().body(req).
					    when().post("/users").
					    then().extract().response();
					    		
		System.out.println(res.body().asString());
		System.out.println("Time taken "+res.getTimeIn(TimeUnit.MILLISECONDS));
		System.out.println("Status code "+res.getStatusLine());
		
		//get
		
		res= given().params("page",2).when().get("/users").then().extract().response();
		System.out.print("GET response ");
		System.out.print(res.body().asString());
		
	
	}
	
	@Test(enabled=false)
	public void meth2()
	{
		Assert.fail();
		SoftAssert ass = new SoftAssert();
		ass.assertEquals(10, 10);
		ass.assertAll();
		
		HashSet<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			
		}
	}

	
}
