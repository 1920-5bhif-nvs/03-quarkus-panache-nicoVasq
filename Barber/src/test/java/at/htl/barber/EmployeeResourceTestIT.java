package at.htl.barber;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
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

    @Test
    public void test03_postEmployee(){
         Response response =
                 given()
                 .contentType(ContentType.JSON)
                 .body("{\"name\":\"Phil\",\"barberShop\":{\"name\":\"Klipp\"},\"salary\":1300.0}")
                 .post("/employee")
                 .then()
                    .statusCode(200)
                    .extract().response();

         assertThat(response.jsonPath().getString("name")).isEqualTo("Phil");
    }

    @Test
    public void test_04_putEmployee(){
        //Update the employees salary
        Response response =
                given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Phil\",\"barberShop\":{\"name\":\"Klipp\"},\"salary\":1500.0}")
                .post("/employee")
                .then()
                    .statusCode(200)
                    .extract().response();

        assertThat(response.jsonPath().getDouble("salary")).isEqualTo(1500.0);
    }

    @Test
    public void test_05_deleteEmployee(){
        given()
            .when().delete("/employee/Phil")
            .then()
                    .statusCode(200);
    }


}
