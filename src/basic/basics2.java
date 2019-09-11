package basic;

import files.payLoad;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basics2 {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream(prop.getProperty("PROPERTIES_FILE_LOCATION"));
        prop.load(fis);
    }

    @Test
    public void postData() {
        RestAssured.baseURI = prop.getProperty("HOST");

        given().
                queryParam("key", prop.getProperty("KEY")).
                body(payLoad.getPostData()).
                    when().
                    post(resources.placePostData()).
                    then().assertThat().statusCode(200).
                    and().contentType(ContentType.JSON);
//                    and().body("status", equalTo("OK"));


        // Create a place =response (place id)

        // delete Place = (Request - Place id)


    }


}
