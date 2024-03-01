package com.phonebook.restAsseredTests;

import com.phonebook.dto.ContactDTO;
import com.phonebook.dto.MessageDTO;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactTests extends TestBase{
    String id;
    @BeforeMethod
    public void precondition(){
        ContactDTO contactDTO = ContactDTO.builder()
                .name("Tony")
                .lastName("Stark")
                .phone("1234567890")
                .email("tonystark@gm.com")
                .address("USA")
                .description("Avenger")
                .build();
        String message = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(contactDTO)
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(containsString("Contact was added!"))
                .extract().path("message");
        String[] all = message.split(": ");
        id = all[1];
    }
    @Test
    public void deleteByIDSuccessTest(){
        given()
                .header("Authorization",token)
                .when()
                .delete("contacts/"+ id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was deleted!"))
                .extract().response().as(MessageDTO.class);
    }
}
