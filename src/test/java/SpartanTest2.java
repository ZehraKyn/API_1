import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SpartanTest2 {
    @Test
    public void Spartan1(){
        //Response response =get("http://52.90.77.165:8000/api/spartans");
        //System.out.println(response.prettyPrint());

    }
    @Test
    public void spartanGetOne(){
        Response response1 = get("http://52.90.77.165:8000/api/spartans/10");
        response1.prettyPrint();
    }

}
