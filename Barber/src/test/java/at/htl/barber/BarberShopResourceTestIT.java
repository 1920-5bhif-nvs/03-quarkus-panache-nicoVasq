package at.htl.barber;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class BarberShopResourceTestIT {

    @Test
    public void test01_getAllBarbershops(){
        given()
            .when().get("/barbershop")
            .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "[0].name", is("Klipp"),
                        "[1].name", is("DM"),
                        "[2].name", is("Schnittzone"));
    }

    @Test
    public void test02_getBarbershopByName(){
        given()
            .when().get("/barbershop/Klipp")
            .then()
                .statusCode(200)
                .body("name", is("Klipp"));
    }

    @Test
    public void test03_noBarbershopFound(){
        given()
            .when().get("/barbershop/Shop123")
            .then()
                .statusCode(404);
    }

    @Test
    public void test_04_postBarbershop(){
        Response response =
                given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Figaro\"}")
                .post("/barbershop")
                .then()
                    .statusCode(200)
                    .extract().response();

        assertThat(response.jsonPath().getString("name")).isEqualTo("Figaro");
    }

    @Test
    public void test_05_deleteBarberShop(){
        given()
            .when().delete("/barbershop/DM")
            .then()
                .statusCode(200);
    }



}
