package com.phonebook.restAsseredTests;

import com.phonebook.dto.AllContactsDTO;
import com.phonebook.dto.ContactDTO;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllContactsTests extends TestBase{
    @Test
    public void getAllContactSuccessTest(){
        AllContactsDTO responseDTO = given()
                .header("Authorization", token)
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(AllContactsDTO.class);
        for (ContactDTO contact: responseDTO.getContacts()) {
            System.out.println(contact.getId() + "***" + contact.getName() + "***");
            System.out.println("==============================================");
        }
    }
}
