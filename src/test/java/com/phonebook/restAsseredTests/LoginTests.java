package com.phonebook.restAsseredTests;

import com.phonebook.dto.AuthRequestDTO;
import com.phonebook.dto.AuthResponseDTO;
import com.phonebook.dto.ErrorDTO;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginTests extends TestBase {

    AuthRequestDTO auth = AuthRequestDTO.builder()
            .username("example@gma.com")
            .password("Pa$sWord1")
            .build();

    @Test
    public void loginSuccessTest() {
        AuthResponseDTO dto = given()
                .contentType("application/json")
                .body(auth)
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDTO.class);
        System.out.println(dto);
    }
    @Test
    public void loginSuccessTest2() {
        String responseToken = given()
                .contentType("application/json")
                .body(auth)
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("token"))
                .extract().path("token");
        System.out.println(responseToken);
    }

    @Test
    public void loginWrongEmailTest_401() {

        ErrorDTO errorDTO = given()
                .contentType(ContentType.JSON)
                .body(AuthRequestDTO.builder()
                        .username("example@gmacom")
                        .password("Pa$sWord1")
                        .build())
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDTO.class);
//        System.out.println(errorDTO.getError());
//        System.out.println(errorDTO.getMessage());

        Assert.assertEquals(errorDTO.getError(),"Unauthorized");
//        Unauthorized
//        Login or Password incorrect
    }
    @Test
    public void loginWrongPasswordTest_401() {
       given()
               .contentType(ContentType.JSON)
               .body(AuthRequestDTO.builder()
                       .username("example@gma.com")
                       .password("Pa$sWord")
                       .build())
               .post("user/login/usernamepassword")
               .then()
               .assertThat().statusCode(401)
               .assertThat().body("message", equalTo("Login or Password incorrect"));

    }
}
