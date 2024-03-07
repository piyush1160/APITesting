package DummyTesting.DummyTesting;


import static io.restassured.RestAssured.given;

import java.util.*;

import org.junit.Test;
import org.testng.Assert;

import POJO.Api;
import POJO.Courses;
import POJO.MainResponse;
import POJO.WebAutomation;
import io.restassured.path.json.JsonPath;
public class OAuthApi {
//public static void main(String[] args) {
    @Test
   public void testOAuth(){
        // this is the best example of client credentials authentication.
    String res =	given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
    	.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
    	.formParam("grant_type","client_credentials").formParam("scope","test").when().log().all()
    	.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
    
       System.out.println(res);
       JsonPath js1 = new JsonPath(res);
       String token = js1.getString("access_token");
       
       System.out.println(token);
       
      // Assert.assertEquals(token, "OgATxzNFy6g3xnFS/G9hEw==");
       
//   String response =     given().queryParam("access_token", token).when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
//      System.out.println(response);

       
       MainResponse gc =     given().queryParam("access_token", token).
               when().log().all()
               .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(MainResponse.class);
      
        System.out.println(gc.getLinkedIn());
     System.out.println(gc.getUrl());
   //  Courses gcc =     given().queryParam("access_token", token).when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(Courses.class);
     System.out.println("-------------------------------------------------------");
   
     // 28.02.2024
     System.out.println(gc.getCourses().getApi().get(0).getCourseTitle());
     
     String coursetitle = gc.getCourses().getApi().get(0).getCourseTitle();
      List<Api> ans = gc.getCourses().getApi();
      
      for(int i= 0 ; i<ans.size() ;i++) {
    	  if(ans.get(i).getCourseTitle().equalsIgnoreCase(coursetitle)) {
    		 System.out.println( ans.get(i).getPrice() );
    	  }
      }
     
     List<WebAutomation> answeb = gc.getCourses().getWebAutomation();
    		 
    	for(int i= 0 ; i<answeb.size() ;i++) {
    	 // if(ans.get(i).getCourseTitle().equalsIgnoreCase(coursetitle)) {
    		 System.out.println( answeb.get(i).getCourseTitle() );
    	  //}
      }
     
     // compare the course title
    	
    	String[] coursesTitle = {"Selenium Webdriver Java","Cypress","Protractor"};
        
    	ArrayList<String> a = new ArrayList<>();
    	for(int i= 0 ; i<answeb.size() ;i++) {
       	 // if(ans.get(i).getCourseTitle().equalsIgnoreCase(coursetitle)) {
       		 a.add( answeb.get(i).getCourseTitle() );
       	  //}
         }
        List<String> finalList = Arrays.asList(coursesTitle); 
        Assert.assertTrue(a.equals(finalList));
        
        
    	
     
     
     
     
     

    }
}
