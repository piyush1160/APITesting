package DummyTesting.DummyTesting;
import io.restassured.*;
import static io.restassured.RestAssured.*;
import java.util.*;

import POJO.*;
import org.testng.annotations.Test;

//  how to make json through java code using pojo class
public class SerializedTest {
	//public static void main (String[] args) {
	@Test
	public void test002(){
	RestAssured.baseURI = "https://rahulshettyacademy.com";
    
	addPlace adding = new addPlace();
	adding.setAccuracy(50);
	adding.setName("Farm House");
	adding.setPhone_number("(+91) 900 893 3937");
	adding.setWebsite("www.google.com/maps");
	adding.setAddress("29, side layout, cohen 09");
	adding.setLanguage("English");
	
	List<String> ls = new ArrayList<String>();
	ls.add("shoe park");
	ls.add("shop");
	
	adding.setTypes(ls);
	
	  Location l1 = new Location();
	 l1.setLat(-38.383494);
	  l1.setLng(33.427362);
	  adding.setLocation(l1);
	System.out.println("-----------------------");

    String res =  given().log().all().queryParam("key", "qaclick123").body(adding)
      .when().post("/maps/api/place/add/json")
    	 .then().assertThat().statusCode(200).extract().response().asString();
    
      System.out.println(res);
	}
}
