package DummyTesting.DummyTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
 
	JsonPath js1 = new JsonPath(payload.CoursePrice());
	//  Print No of courses returned by API 
	
	int count = js1.getInt("courses.size()");
	int sum= 0;
	
	@Test
	public void sumOfCourse() {
		//int sum= 0;
       int purchaseAmount = js1.getInt("dashboard.purchaseAmount");
		for(int i= 0 ;i<count;i++) {
		//	if(sum != purchaseAmount ) {
			int copies = js1.getInt("courses[" +i+"].copies");
			int price  = js1.getInt("courses[" +i+"].price");
			
			sum += copies*price;
			System.out.println(sum);
			//}
		}
		System.out.println(sum);
		
		Assert.assertEquals(sum, purchaseAmount);
	}
	
	//int purchaseAmount = js1.getInt("dashboard.purchaseAmount");
	
	

}
