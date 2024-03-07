package DummyTesting.DummyTesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.util.Asserts;
import org.testng.Assert;

import files.payload;

import static io.restassured.RestAssured.*;

public class first {

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub 
//     RestAssured.baseURI= "https://rahulshettyacademy.com";
//      given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
//      .body("{\r\n"
//      		+"  \"location\": {\r\n"
//      		+ "    \"lat\": -38.383494,\r\n"
//      		+ "    \"lng\": 33.427362\r\n"
//      		+ "  },\r\n"
//      		+ "  \"accuracy\": 50,\r\n"
//      		+ "  \"name\": \"Frontline house\",\r\n"
//      		+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
//      		+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
//      		+ "  \"types\": [\r\n"
//      		+ "    \"shoe park\",\r\n"
//      		+ "    \"shop\"\r\n"
//      		+ "  ],\r\n"
//      		+ "  \"website\": \"http://rahulshettyacademy.com\",\r\n"
//      		+ "  \"language\": \"French-IN\"\r\n"
//      		+ "}\r\n"
//      		+ "").when().post("maps/api/place/add/json")
//      .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)");
//      
		
		// 2ND WAY TO write code call payload class access the String and use in the body ....
		
//		RestAssured.baseURI= "https://rahulshettyacademy.com";
//	      given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
//	      .body(payload.AddPlace()).when().post("maps/api/place/add/json")
//	      .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)");
		
		// extract response as string and strong in a string... 
		
		
		// example of direct pass json file 
		// change the content of the file to String  -> Content of File can convert into byte -> Byte data to String  (by returning the String Object)
		RestAssured.baseURI= "https://rahulshettyacademy.com" ;
	      String responseStroe = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	      .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\User\\Desktop\\Book.json")))).when().post("maps/api/place/add/json")
	      .then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
	      
	      System.out.println(responseStroe);
	      
	      JsonPath js = new JsonPath(responseStroe);
	      //  if we send wrong name it throw the null path_id === null ....
	       String placeId = js.getString("place_id");
	       
	       System.out.println(placeId);
	       
	       
	       
	       // for updation
	       
//	       given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n"
//	       		+ "\"place_id\":\"b8806b0790062f4b095614bc7b70a99a\",\r\n"
//	       		+ "\"address\":\"70 Summer walk, India\",\r\n"
//	       		+ "\"key\":\"qaclick123\"\r\n"
//	       		+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200);
	       
	       String updateaddress = "70 Summer walk, Dubai";
	       given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n"
		       		+ "\"place_id\":\""+placeId+"\",\r\n"
		       		+ "\"address\":\""+updateaddress+"\",\r\n"
		       		+ "\"key\":\"qaclick123\"\r\n"
		       		+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).header("Server","Apache/2.4.52 (Ubuntu)");
		  
	      
	    //get   
	    String response1=   given().log().all().queryParam("key","qaclick123")
	      .queryParam("place_id",placeId)
	      .when().get("maps/api/place/get/json")
	      .then().log().all().assertThat().statusCode(200).extract().response().asString();
	    
	    JsonPath js1 = new JsonPath(response1);
	      
	     String actualAddress = js1.getString("address");
	      System.out.println(actualAddress);
	       
	      
	      Assert.assertEquals(actualAddress, updateaddress);
	      
	      
	      
	      
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	}

}
