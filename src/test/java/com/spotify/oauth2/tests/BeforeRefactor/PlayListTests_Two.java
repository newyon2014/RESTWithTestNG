package com.spotify.oauth2.tests.BeforeRefactor;

import com.spotify.oauth2.pojo.BeforeRefactor.ErrorTwo;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistTwo;
import com.spotify.oauth2.pojo.Error;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests_Two {
    /* *****************************************************************************************
    *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
    *  *****************************************************************************************
    *
    *     DESCRIPTION OF CHANGES:
    *         - POJO class Update for Playlist
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
        PlaylistTwo requestPlaylist = new PlaylistTwo();
        requestPlaylist.setName("New PlayList");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.set_public(false);

        PlaylistTwo responsePlayList = given(requestSpecification)
                .body(requestPlaylist)
        .when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
        .then().spec(responseSpecification)
                .assertThat()
                .statusCode(201)
                .extract()
                .response()
                .as(PlaylistTwo.class);

        assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() {
        PlaylistTwo requestPlaylist = new PlaylistTwo();
        PlaylistTwo responsePlayList = given(requestSpecification)
        .when().get("playlists/4jbfp94u3gtpubg9uw")
        .then()
                .spec(responseSpecification)
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .as(PlaylistTwo.class);

        assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist() {
        PlaylistTwo requestPlaylist = new PlaylistTwo();
        requestPlaylist.setName("New PUT PlayList");
        requestPlaylist.setDescription("New PUT playlist description");
        requestPlaylist.set_public(false);

        given(requestSpecification)
                .body(requestPlaylist)
        .when().put("playlists/4jbfp94u3gtif043hf4w")
        .then()
                // .spec(responseSpecification) - put message don't have a response body. removing from this test
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithOutName() {
        PlaylistTwo requestPlaylist = new PlaylistTwo();
        requestPlaylist.setName(""); // No Name was specified in the value field
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.set_public(false);

        ErrorTwo err =  given(requestSpecification)
                .body(requestPlaylist)
       .when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
       .then().spec(responseSpecification)
                .assertThat()
                .statusCode(400)
                .extract()
                .response()
                .as(ErrorTwo.class);

        assertThat(err.getError().getStatus(), equalTo(400));
        assertThat(err.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        PlaylistTwo requestPlaylist = new PlaylistTwo();
        requestPlaylist.setName("New PUT PlayList");
        requestPlaylist.setDescription("New PUT playlist description");
        requestPlaylist.set_public(false);

        ErrorTwo err =  given(requestSpecification)
                .body(requestPlaylist)
       .when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
       .then().spec(responseSpecification)
                .assertThat()
                .statusCode(401)
                .extract()
                .response()
                .as(ErrorTwo.class);

        assertThat(err.getError().getStatus(), equalTo(400));
        assertThat(err.getError().getMessage(), equalTo("Missing required field: name"));
    }
}
