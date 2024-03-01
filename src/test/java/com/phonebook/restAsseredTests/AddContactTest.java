package com.phonebook.restAsseredTests;

import com.phonebook.dto.ContactDTO;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class AddContactTest extends TestBase{
    @Test
    public void addContactSuccessTest() {
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
        System.out.println(message);
    }
}
