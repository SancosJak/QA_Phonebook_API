package com.phonebook.restAsseredTests;

import com.phonebook.dto.AllContactsDTO;
import com.phonebook.dto.ContactDTO;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAllContactsTest extends TestBase{
    @Test
    public void deleteAllContactsTest() {
        AllContactsDTO allContactsDTO = given()
                .header("Authorization", "Bearer " + token)
                .get("/contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(AllContactsDTO.class);

        for (ContactDTO contact : allContactsDTO.getContacts()) {
            given()
                    .header("Authorization", "Bearer " + token)
                    .delete("/contacts/" + contact.getId())
                    .then()
                    .assertThat().statusCode(200)
                    .assertThat().body("message", equalTo("Contact was deleted!"));
            System.out.println("Contact with ID " + contact.getId() + " deleted successfully.");
        }
    }
}
