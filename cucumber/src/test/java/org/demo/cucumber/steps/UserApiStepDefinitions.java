package org.demo.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.demo.cucumber.config.CucumberSpringConfiguration;
import org.demo.cucumber.entity.User;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@CucumberContextConfiguration
public class UserApiStepDefinitions extends CucumberSpringConfiguration {
    User user;
    private Response response;

    @When("I send a GET request to url {string}")
    public void callGetAllUser(String url) {
        response = RestAssured
                .given()
                .port(port)
                .when()
                .get(url);
    }

    @Then("I send a POST request to {string} with the following details:")
    public void callCreateUser(String url, DataTable dataTable) {
        Map<String, String> userDetails = dataTable.asMaps().getFirst();
        response = RestAssured
                .given()
                .port(port)
                .body(userDetails)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post(url);
    }

    @Then("I send a GET request to url {string} with the user's email {string}")
    public void callGetUserByEmail(String url, String email) {
        response = RestAssured
                .given()
                .port(port)
                .when()
                .get(url, email);
    }

    @Then("I send a PUT request to {string} with the following details:")
    public void callUpdateUser(String url, DataTable dataTable) {
        Map<String, String> userDetails = dataTable.asMaps().getFirst();
        response = RestAssured
                .given()
                .port(port)
                .body(userDetails)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .put(url);
    }

    @Then("I send a DELETE request to url {string} with the user's email {string}")
    public void callDeleteUserByEmail(String url, String email) {
        response = RestAssured
                .given()
                .port(port)
                .when()
                .delete(url, email);
    }

    @Then("the response status code should be {int}")
    public void checkStatusResponse(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the response should contain {int} users")
    public void checkSizeResponse(int size) {
        List<User> users = response.body().as(new TypeRef<>() {
        });
        assertEquals(size, users.size());
    }

    @Then("the response should contain a user with name {string} and email {string}")
    public void checkResultResponseCreate(String name, String email) {
        user = response.body().as(User.class);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
    }

}
