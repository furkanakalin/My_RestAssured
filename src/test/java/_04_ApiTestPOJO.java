import Model.Location;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class _04_ApiTestPOJO {

    @Test
    public void extractJsonAll_POJO(){

        Location Location=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .extract().body().as(Model.Location.class);

        System.out.println("Location.getCountry() = " + Location.getCountry());

    }
}
