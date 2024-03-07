package DummyTesting.DummyTesting;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import java.util.*;
import java.io.File;
import POJO.LoginReq;
import POJO.LoginResponse;
import POJO.OrderDetail;
import POJO.Orders;
import groovy.transform.stc.POJO;
import org.testng.annotations.Test;

public class EcomerseApiTest {

//	public static void main(String[] args) {
//	}
		// TODO Auto-generated method stub
	@Test
     public void test001()

	{ // login to website ..
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();

		LoginReq lobj = new LoginReq();
		lobj.setuserEmail("piyush1234@gmail.com");
		lobj.setuserPassword("Test@1234");

		//RequestSpecification reqLogin = RestAssured.given().spec(req).body(lobj);
		RequestSpecification reqLogin = given().spec(req).body(lobj);

		LoginResponse response = reqLogin.when().post("api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);


		String token = response.getToken();
		String id = response.getUserId();
		//  String responseString = response.asString();


		System.out.println(token);


		//   Add Product to cart  ..

		RequestSpecification addProdreq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token)
				.build();

		RequestSpecification reqAdd = given().param("productName", "Iphone").param("productAddedBy", id).param("productCategory", "Smartphone").param("productSubCategory", "Apple")
				.param("productPrice", "150000").param("productDescription", "Titanium").param("productFor", "men")
				.multiPart("productImage", new File("C:\\Users\\User\\Pictures\\Camera Roll\\Titanium.jpg"));

		String resAdd = reqAdd.when().spec(addProdreq).post("api/ecom/product/add-product").then().log().all().extract().response().asString();
  // it is a class which take string as input and perform some action on the json .
		JsonPath js = new JsonPath(resAdd);
		String productId = js.get("productId");

		System.out.println(productId);

		System.out.println(resAdd);



		// Create Order in API ...

		RequestSpecification createProdreq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).setContentType(ContentType.JSON)
				.build();

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("Bharat");
		orderDetail.setProductOrderedId(productId);


		List<OrderDetail> list = new ArrayList<>();
		list.add(orderDetail);

		Orders order = new Orders();
		order.setOrders(list);

		RequestSpecification createOrderrequest = given().log().all().spec(createProdreq).body(order);

		Response createOrderres = createOrderrequest.when().post("api/ecom/order/create-order").then().log().all().extract().response();

		String stringResponse = createOrderres.asString();
		System.out.println(stringResponse);


		// Delete Product from api..

		RequestSpecification deletebaseProdreq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON)
				.build();


		RequestSpecification deleteOrderrequest = given().log().all().spec(deletebaseProdreq).pathParam("productId", productId);
		 // store the response in the reference variable and then do some action which is required ...
		Response DeleteOrderres = deleteOrderrequest.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response();


		String stringDeleteResponse = DeleteOrderres.asString();
		System.out.println(stringDeleteResponse);


	}
	}


