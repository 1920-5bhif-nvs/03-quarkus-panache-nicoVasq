package at.htl.barber;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
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

}
