//package DummyTesting.DummyTesting;
//
//import files.payload;
//import io.restassured.path.json.JsonPath;
//
//public class ComplexJson {
//
//		public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//			JsonPath js1 = new JsonPath(payload.CoursePrice());
//			//  Print No of courses returned by API
//
//			int count = js1.getInt("courses.size()");
//			 System.out.println(count);
//			 //Print Purchase Amount..
//
//		int amount =	 js1.getInt("dashboard.purchaseAmount");
//
//		System.out.println(amount);
//
//		//Print Title of the first course
//
//	String ans = js1.get("courses[0].title");
//	System.out.println(ans);
//	System.out.println("--------------------");
//	 //Print All course titles and their respective Prices
//
//	for(int i= 0 ;i<count;i++) {
//
//		// ("c
//		System.out.println(js1.get("courses[" +i+"].title"));
//			System.out.println(js1.get("courses[" +i+"].price"));
//	}
//
//	System.out.println(" Print no of copies sold by RPA Course");
//
//	for(int i= 0 ;i<count;i++) {
//		String s = js1.get("courses[" +i+"].title");
//			if(s.equalsIgnoreCase("RPA")) {
//				System.out.println(js1.get("courses[" +i+"].copies"));
//				break;
//			}
//	}
//		//Verify if Sum of all Course prices matches with Purchase Amount
//
//	int sum= 0;
//	int purchaseAmount = js1.getInt("dashboard.purchaseAmount");
//	for(int i= 0 ;i<count;i++) {
//		if(sum != purchaseAmount ) {
//		int copies =	js1.getInt("courses[" +i+"].copies");
//		int price = 	js1.getInt("courses[" +i+"].price");
//
//		sum += copies*price;
//		}
//	}
//
//	System.out.println(sum);
//
//
//
//
//
//
//	}
//
//}
