package com.spotify.oauth2.tests.BeforeRefactor;

import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistTwo;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistBuilderPatternThree;
import com.spotify.oauth2.pojo.Error;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.BeforeRefactor.SpecBuilderFour.getRequestSpec;
import static com.spotify.oauth2.api.BeforeRefactor.SpecBuilderFour.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests_Four {
    /* *****************************************************************************************
    *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
    *  *****************************************************************************************
    *
    *     DESCRIPTION OF CHANGES:
    *         - Introduction of spec builder
    *         - To remove repetition when creating request & response spec builders
    *         - Class created under src/test/java/api/BeforeRefactor/specBuilderFour folder
    *  *****************************************************************************************/

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @Test
    public void shouldBeAbleToCreatePlayList() {
        // Builder pattern
        PlaylistBuilderPatternThree requestPlaylist = new PlaylistBuilderPatternThree()
                .setName("New PlayList")
                .setDescription("New PlayList description")
                .set_public(false);

        // replaced the requestSpecification in given() with getRequestSpec()
        PlaylistTwo responsePlayList = given(getRequestSpec())
                .body(requestPlaylist)
        .when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")

        // replaced the responseSpecification in spec() with getResponseSpec()
        .then().spec(getResponseSpec())
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

        // replaced the requestSpecification in given() with getRequestSpec()
        PlaylistTwo responsePlayList = given(getRequestSpec())
        .when().get("playlists/4jbfp94u3gtpubg9uw")

        // replaced the responseSpecification in spec() with getResponseSpec()
        .then().spec(getResponseSpec())
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
        // Builder pattern
        PlaylistBuilderPatternThree requestPlaylist = new PlaylistBuilderPatternThree()
                .setName("New PUT PlayList")
                .setDescription("New PlayList PUT description")
                .set_public(false);

        // replaced the requestSpecification in given() with getRequestSpec()
        given(getRequestSpec())
                .body(requestPlaylist)
        .when().put("playlists/4jbfp94u3gtif043hf4w")
        .then()
                // .spec(responseSpecification) - put message don't have a response body. removing from this test
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithOutName() {
        // Builder pattern
        PlaylistBuilderPatternThree requestPlaylist = new PlaylistBuilderPatternThree()
                .setName("") // No Name was specified in the value field
                .setDescription("New PlayList description")
                .set_public(false);

        // replaced the requestSpecification in given() with getRequestSpec()
        Error err =  given(getRequestSpec())
                .body(requestPlaylist)
       .when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")

       // replaced the responseSpecification in spec() with getResponseSpec()
       .then().spec(getResponseSpec())
                .assertThat()
                .statusCode(400)
                .extract()
                .response()
                .as(Error.class);

        assertThat(err.getError().getStatus(), equalTo(400));
        assertThat(err.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        // Builder pattern
        PlaylistBuilderPatternThree requestPlaylist = new PlaylistBuilderPatternThree()
                .setName("New PUT PlayList")
                .setDescription("New PlayList PUT description")
                .set_public(false);

        // Using invalid access token on the header
        Error err =  given()
                      .body(requestPlaylist)
                      .baseUri("https://api.spotify.com")
                      .basePath("/v1")
                      .header("Authorization", "Bearer " + "1234")
                      .contentType(ContentType.JSON)
                      .log().all()
                      .body(requestPlaylist)
       .when().post("users/c0sb51wjod0fli2g7k0r2lc5e/playlists")
        //replaced the responseSpecification in spec() with getResponseSpec()
       .then().spec(getResponseSpec())
                .assertThat()
                .statusCode(401)
                .extract()
                .response()
                .as(Error.class);

        assertThat(err.getError().getStatus(), equalTo(401));
        assertThat(err.getError().getMessage(), equalTo("Missing required field: name"));
    }
}
