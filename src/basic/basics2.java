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

public class basics2 {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/b.peric/GitHub/GoogleRestApiTestDemo/src/files/env.properties");
        prop.load(fis);
    }

    @Test
    public void postData() {
        RestAssured.baseURI = "http://216.10.245.166/maps/api/place/add/xml?key=qaclick123";

        given().
                queryParam("key", prop.getProperty("KEY")).
                body("{" +
                        "\"location\": {" +
                            "\"lat\": -33.8669710," +
                            "\"lng\": 151.1958750" +
                        "}," +
                        "\"accuracy\": 50," +
                        "\"name\": \"Google Shoes!\"," +
                        "\"phone number\": \"(02) 9374 4000\"," +
                        "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," +
                        "\"types\": [\"shoe store\"]," +
                        "\"website\": \"http://www.google.com.au/\"," +
                        "\"language\": \"en-AU\"" +
                "}").
                    when().
                    post("/maps/api/place/add/json").
                    then().assertThat().statusCode(200).
                    and().contentType(ContentType.JSON);
//                    and().body("status", equalTo("OK"));


        // Create a place =response (place id)

        // delete Place = (Request - Place id)


    }


}
