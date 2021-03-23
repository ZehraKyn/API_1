import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class SpartanRestAssured {
    @BeforeAll
    public static void setUp(){
        baseURI= "http://107.20.53.61:8000";
        basePath ="/api";

    }

    @Test
    public void SpartanOneGET(){

        Response response = get("/spartans/13").prettyPeek();
        System.out.println("Name' :" + response.path("name"));
        System.out.println("Id' :" + response.path("id"));
        System.out.println("Gender' :" + response.path("gender"));
        System.out.println("Phone' :" + response.path("phone"));

        assertThat(response.path("id"), is(13));
        assertThat(response.path("name"), is("Jaimie"));

        assertThat(response.contentType(), is("application/json;charset=UTF-8"));
        assertThat(response.statusCode(), is (200));


    }

    @Test
    public void SpartanTest3(){

        given()
                .log().all()
                .pathParam("id", 13)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
        .get("spartans/{id}").prettyPeek()
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",is (13));

    }
    @Test
    public void SpartanSearch(){
        Response response = given().
                log().all()
                .queryParam("gender", "Male")
                .accept(ContentType.JSON)
                .when()
                .get("spartans/search").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();
                //.body("gender", not(containsString("Male")))

        List<String> spartan = response.path("content.gender");
        System.out.println("Size " +spartan.size());
        assertThat(spartan,not(hasItem("Female")));

        //assertThat(spartan,not(hasItem("Female")));
        assertThat(spartan,hasSize(54));
    }

}
