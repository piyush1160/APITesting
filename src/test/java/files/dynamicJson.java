package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class dynamicJson {
  
	@Test(dataProvider="BokksData")
	public void addBook(String isbn , String aisle) {
	//	System.out.print(payload.AddBook());
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type","application/json")
				.body(payload.AddBook(isbn,aisle).toString()).when()    // parametrize json example we pass the the json data ..
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
    
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);
	
	}
	
	@DataProvider(name ="BokksData")
	public Object[][] getData(){
		return new Object[][] {{"xyz","1230"},{"dyuf","4560"},{"ghai","7809"}};
	}
//	
//	@Test
//	public void addBook(String isbn , String aisle) throws IOException {
//	//	System.out.print(payload.AddBook());
//		RestAssured.baseURI = "http://216.10.245.166";
//		String response = given().header("Content-Type","application/json")
//				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\User\\Desktop\\Book.json")))).when()    // pass direct json file
//		.post("/Library/Addbook.php")
//		.then().assertThat().statusCode(200).extract().response().asString();
//    
//		JsonPath js = new JsonPath(response);
//		String id = js.get("ID");
//		System.out.println(id);
//	
//	 }
//	@DataProvider(name ="BokksData")
//	public Object[][] getData(){
//		return new Object[][] {{"xyz","1230"},{"dyuf","4560"},{"ghai","7809"}};
//	}
	
	
	
}
