package com.touresbalon.ordenes.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


public class ExampleResourceTest {


    public void testHelloEndpoint() {
        given()
          .when().get("/ordenes")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}