package com.spotify.oauth2.tests.BeforeRefactor;

import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiSix;
import com.spotify.oauth2.pojo.BeforeRefactor.ErrorTwo;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistBuilderPatternThree;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistTwo;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests_Nine {
    /* *****************************************************************************************
    *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
    *  *****************************************************************************************
    *
    *     DESCRIPTION OF CHANGES:
    *         - No change needed in the test class even if the TokenManagerNine.java changed
    *         - No change needed with the TokenManagerNine refactoring
    *         - Class created under src/test/java/api/BeforeRefactor folder
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

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSix.post(requestPlaylist);
        assertThat(response.getStatusCode(), equalTo(201));

        // replaced the requestSpecification in given() with getRequestSpec()
        PlaylistTwo responsePlayList = response.as(PlaylistTwo.class);

        assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() {
        PlaylistTwo requestPlaylist = new PlaylistTwo();

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSix.get("4jbfp94u3gtpubg9uw");
        assertThat(response.getStatusCode(), equalTo(20));

        PlaylistTwo responsePlayList = response.as(PlaylistTwo.class);

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

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSix.update("4jbfp94u3gtpubg9uw", requestPlaylist);
        assertThat(response.getStatusCode(), equalTo(20));
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithOutName() {
        // Builder pattern
        PlaylistBuilderPatternThree requestPlaylist = new PlaylistBuilderPatternThree()
                .setName("") // No Name was specified in the value field
                .setDescription("New PlayList description")
                .set_public(false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSix.post(requestPlaylist);
        assertThat(response.getStatusCode(), equalTo(400));

        ErrorTwo err = response.as(ErrorTwo.class);

        assertThat(err.getError().getStatus(), equalTo(400));
        assertThat(err.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String invalid_token = "12345";
        // Builder pattern
        PlaylistBuilderPatternThree requestPlaylist = new PlaylistBuilderPatternThree()
                .setName("New PUT PlayList")
                .setDescription("New PlayList PUT description")
                .set_public(false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSix.post(invalid_token,requestPlaylist);
        assertThat(response.getStatusCode(), equalTo(401));

        ErrorTwo err = response.as(ErrorTwo.class);

        assertThat(err.getError().getStatus(), equalTo(401));
        assertThat(err.getError().getMessage(), equalTo("Missing required field: name"));
    }
}
