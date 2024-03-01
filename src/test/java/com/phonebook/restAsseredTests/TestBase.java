package com.phonebook.restAsseredTests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiZXhhbXBsZUBnbWEuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE3MDk5MjEyMDcsImlhdCI6MTcwOTMyMTIwN30.ZS3p8RXQNUDeitTh7QBeWITUxX9TG7P9RKN9CfK2nQ8";
    @BeforeMethod
    public void init() {
        RestAssured.baseURI ="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath ="v1";
    }
}
