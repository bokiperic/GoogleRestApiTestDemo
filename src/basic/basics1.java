package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basics1 {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/b.peric/GitHub/GoogleRestApiTestDemo/src/files/env.properties");
        prop.load(fis);
    }

    @Test
    public void test() {
        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "500").
                param("key", "AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).
                and().body("results[0].name", equalTo("Sydney")).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
                and().header("Server", "scaffolding on HTTPServer2");
    }
}
