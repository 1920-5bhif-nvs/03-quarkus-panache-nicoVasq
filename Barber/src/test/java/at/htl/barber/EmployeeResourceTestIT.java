package at.htl.barber;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class EmployeeResourceTestIT {

    @Test
    public void test01_getAllEmployees(){
        given()
            .when().get("/employee")
            .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "[0].name", is("Sophie"),
                        "[1].name", is("Dianna"));
    }

    @Test
    public void test02_getEmployeeByName(){
        given()
            .when().get("/employee/Sophie")
            .then()
            .statusCode(200)
            .body("name", is("Sophie"));
    }
}
