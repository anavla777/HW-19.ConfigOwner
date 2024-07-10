package tests;

import api.authorization.AuthApi;
import config.TestDataConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.AddBookRequestDTO;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.DemoqaSpec.demoqaCreatedResponseSpec;
import static specs.DemoqaSpec.demoqaRequestSpec;

public class ApiSteps {
    static final TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());

    @Step("Add new book into list")
    public void addListOfBook() {
        AddBookRequestDTO bookData = new AddBookRequestDTO();
        String userID = AuthApi.extractValueFromCookieString("userID");
        String token = AuthApi.extractValueFromCookieString("token");
        bookData.setUserId(userID);
        bookData.setIsbn(testDataConfig.isbn());

        Response addBookResponse = given(demoqaRequestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(demoqaCreatedResponseSpec)
                .extract()
                .response();
        assertThat(bookData.getCollectionOfIsbns()
                .get(0).getIsbn(), equalTo(addBookResponse.path("books[0].isbn")));
    }
}
