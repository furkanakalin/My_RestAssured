import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class _03_ApiTestExtract {

    @Test
    public void extractingJsonPath() {

        String ulkeAdi=
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .extract().path("country")  // PATH i country olan değeri EXTRACT yap
        ;

        System.out.println("ulkeAdi = " + ulkeAdi);
        Assert.assertEquals(ulkeAdi, "United States");
    }

    @Test
    public void extractingJsonPath2() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu testNG Assertion ile doğrulayınız

        String state=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("places[0].state")  // PATH i country olan değeri EXTRACT yap
                ;

        System.out.println("state = " + state);
        Assert.assertEquals(state, "California");
    }

    @Test
    public void extractingJsonPath3() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.

        int limit=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .log().body()
                        .extract().path("meta.pagination.limit")  // PATH i country olan değeri EXTRACT yap
                ;

        System.out.println("limit = " + limit);
        Assert.assertTrue(limit == 10);
    }

    @Test
    public void extractingJasonParh4(){
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // tüm id leri nasıl alırız.

        ArrayList<Integer> idies=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().path("data.id");

        System.out.println("idies = " + idies);

    }

    @Test
    public void extractingJasonPath5(){
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // tüm name leri nasıl alırız.

        ArrayList<Integer> names=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().path("data.name");

        System.out.println("names = " + names);

    }

    @Test
    public void extractingJasonAllResponce(){
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // tüm verileri nasıl alırız.

        Response dataAll=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().response();

        List<Integer> idies=dataAll.path("data.id");
        List<String> names=dataAll.path("data.name");
        int limit=dataAll.path("meta.pagination.limit");

        System.out.println("idies = " + idies);
        System.out.println("names = " + names);
        System.out.println("limit = " + limit);

    }

}
