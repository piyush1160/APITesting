package DummyTesting.DummyTesting;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import java.util.*;

//import com.github.scribejava.core.model.Response;

import POJO.*;


public class SpecBuilder {
	public static void main (String[] args) {
	RestAssured.baseURI = "https://rahulshettyacademy.com";
    
	addPlace adding = new addPlace();
	adding.setAccuracy(50);
	adding.setName("Farm House");
	adding.setPhone_number("(+91) 900 893 3937");
	adding.setWebsite("www.google.com/maps");
	adding.setAddress("29, side layout, cohen 09");
	adding.setLanguage("Hindi-English");
	
	List<String> ls = new ArrayList<String>();
	ls.add("shoe park");
	ls.add("shop");
	
	adding.setTypes(ls);
	
	  Location l1 = new Location();
	 l1.setLat(-38.383494);
	  l1.setLng(33.427362);
	  adding.setLocation(l1);
	System.out.println("-----------------------");

	
	RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").build();
	ResponseSpecification reqres = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
//   RequestSpecification res = given().spec(req).body(adding);
//   Response response1 =  res.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();
//	 String responseString  = response1.asString();
//	 System.out.println(responseString);
	
		RequestSpecification res = 	   given().spec(req).body(adding);
	    Response response1=  res.when().post("/maps/api/place/add/json").then().spec(reqres).extract().response();
		String responseString  = response1.asString();
		System.out.println(responseString);
		
	
//    String res =  given().log().all().queryParam("key", "qaclick123").body(adding)
//      .when().post("/maps/api/place/add/json")
//    	 .then().assertThat().statusCode(200).extract().response().asString();
//    
//      System.out.println(res);
	}
}
