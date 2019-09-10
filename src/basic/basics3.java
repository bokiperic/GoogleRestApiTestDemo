package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.resources;
import files.payLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basics3 {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/b.peric/GitHub/GoogleRestApiTestDemo/src/files/env.properties");
        prop.load(fis);
    }

    @Test
    public void AddAndDeletePlace() {

        // Task  1 - Grab the response
        RestAssured.baseURI = prop.getProperty("HOST");

        Response response = given().
                                queryParam("key", prop.getProperty("KEY")).
                                body(payLoad.getPostData()).
                                when().
                                post(resources.placePostData()).
                                then().assertThat().statusCode(200).
                                and().contentType(ContentType.JSON).
//                                and().body("status", equalTo("OK")).
                                extract().response();

        String responseString = response.asString();
        System.out.println("Response: " + responseString);

        // Task 2 - Grab the PlaceID from response
        JsonPath responseJson = new JsonPath(responseString);
        String placeID = responseJson.get("place_id");
        System.out.println(placeID);

        // Task 3 - Place this placeID in the Delete request
        given().
                queryParam("key", prop.getProperty("KEY")).
                body("{" +
                        "\"place_id\": \"" + placeID + "\"" +
                        "}").
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).
                and().body("status", equalTo("OK"));
    }

}
