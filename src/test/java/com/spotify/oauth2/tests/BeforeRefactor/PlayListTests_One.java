package com.spotify.oauth2.tests.BeforeRefactor;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests_One {
    /* *****************************************************************************************
    *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
    *  *****************************************************************************************
    *
    *     DESCRIPTION OF CHANGES:
    *         - Base Test class
    *  *****************************************************************************************/

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "PMAK-5ff2d720d2a39a004250e5da-c658c4a8a1cee3516762cb1a51cba6c5e2";

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                addHeader("Authorization", "Bearer " + access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void shouldBeAbleToCreatePlayList() {
        String payLoad = "{\n" +
                "  \"name\": \"New Playlist\",\n" +
                "  \"description\": \"New playlist description\",\n" +
                "  \"public\": false\n" +
                "}";
        given(requestSpecification)
                .body(payLoad)
                .when()
                .post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("New PlayList"),
                        "description", equalTo("New PlayList description"),
                        "public", equalTo(false));
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() {
        given(requestSpecification)
                .when()
                .get("playlists/4jbfp94u3gtpubg9uw")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Updated New PlayList"),
                        "description", equalTo("Updated New PlayList description"),
                        "public", equalTo(false));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist() {
        String payLoad = "{\n" +
                "  \"name\": \"New Playlist for PUT\",\n" +
                "  \"description\": \"New playlist for PUT description\",\n" +
                "  \"public\": false\n" +
                "}";
        given(requestSpecification)
                .body(payLoad)
                .when()
                .put("playlists/4jbfp94u3gtif043hf4w")
                .then()
                // .spec(responseSpecification) - put message don't have a response body. removing from this test
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithOutName() {
        String payLoad = "{\n" +
                "  \"name\": \"\",\n" + // No Name was specified in the value field
                "  \"description\": \"New playlist description\",\n" +
                "  \"public\": false\n" +
                "}";
        given(requestSpecification)
                .body(payLoad)
                .when()
                .post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(400)
                .body("error.status", equalTo("400"),
                        "error", equalTo("Missing required field: name"));

    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String payLoad = "{\n" +
                "  \"name\": \"New Playlist Negative scenario\",\n" +
                "  \"description\": \"New playlist for Negative scenario\",\n" +
                "  \"public\": false\n" +
                "}";
        given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + access_token)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(payLoad).
             when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
             .then().spec(responseSpecification)
                .assertThat()
                .statusCode(401)
                .body("error.status", equalTo("401"),
                        "error", equalTo("Invalid Access Token"));
    }
}
