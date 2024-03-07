package DummyTesting.DummyTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.testng.Assert;
import java.io.FileReader;
public class Comparison {

    //  this is for comparing the

    @Test
    public void test001() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject obj1 = null;
        JSONObject obj2 = null;

        obj1 = (JSONObject) parser.parse(new FileReader("C:\\DummyTesting\\src\\test\\java\\files\\Json1.json"));
        obj2 = (JSONObject) parser.parse(new FileReader("C:\\DummyTesting\\src\\test\\java\\files\\Json2.json"));

        ObjectMapper mapper = new ObjectMapper();

        Assert.assertEquals(mapper.readTree(obj1.toJSONString()), mapper.readTree(obj2.toJSONString()));

      //  Assert.assertEquals(mapper.readTree(obj1.toJSONString()), mapper.readTree(obj2.toJSONString()));
    }
}
