package com.spotify.oauth2.tests.BeforeRefactor;

import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiSix;
import com.spotify.oauth2.pojo.BeforeRefactor.ErrorTwo;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistBuilderPatternThree;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistTwo;
import com.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests_Fourteen {
    /* *****************************************************************************************
    *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
    *  *****************************************************************************************
    *
    *     DESCRIPTION OF CHANGES:
    *         - refactored the repeating builder pattern code to method playListBuilder()
    *         - refactored the repeating assertion verification into assertStatusCode() and assertError() methods
    *         - Class created under src/test/java/api/BeforeRefactor folder
    *  *****************************************************************************************/

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    public PlaylistBuilderPatternThree playListBuilder(String name,String description,boolean _public){
     return  new PlaylistBuilderPatternThree()
                .setName(name)
                .setDescription(description)
                .set_public(_public);
    }

    public void assertPlayListIsEqual(PlaylistBuilderPatternThree responsePlayList, PlaylistBuilderPatternThree requestPlaylist){
        assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlaylist.get_public()));
    }

    public void assertStatusCode(int actualStatusCode,int expectedStatusCode){
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    public void assertError (ErrorTwo responseError,int expectedStatusCode,String expectedMessage){

        assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));
    }

    @Test
    public void shouldBeAbleToCreatePlayList() {
        PlaylistBuilderPatternThree requestPlaylist = playListBuilder("New PlayList", "New PlayList description", false);

        Response response = PlaylistApiSix.post(requestPlaylist);
        assertStatusCode(response.statusCode(),201);

        PlaylistBuilderPatternThree responsePlayList = response.as(PlaylistBuilderPatternThree.class);
        assertPlayListIsEqual(responsePlayList,requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() {
        PlaylistBuilderPatternThree requestPlaylist = playListBuilder("Updated PlayList name",
                "Updated PlayList description", false);

        Response response = PlaylistApiSix.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(),200);

        PlaylistBuilderPatternThree responsePlayList = response.as(PlaylistBuilderPatternThree.class);
        assertPlayListIsEqual(responsePlayList,requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist() {
        PlaylistBuilderPatternThree requestPlaylist = playListBuilder("New PUT PlayList", "New PlayList PUT description", false);
        // using PlaylistApiFive generic methods
        // Refactored this with the DataLoader class
        Response response = PlaylistApiSix.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertThat(response.getStatusCode(), equalTo(20));
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithOutName() {
        PlaylistBuilderPatternThree requestPlaylist = playListBuilder("", "New PlayList description", false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSix.post(requestPlaylist);
        assertStatusCode(response.getStatusCode(),400);

        ErrorTwo err = response.as(ErrorTwo.class);
        assertError(err,400,"Missing required field: name");
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String invalid_token = "12345";
        PlaylistBuilderPatternThree requestPlaylist = playListBuilder("New PlayList", "New PlayList description", false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSix.post(invalid_token,requestPlaylist);
        assertStatusCode(response.getStatusCode(),401);

        ErrorTwo err = response.as(ErrorTwo.class);
        assertError(err,401,"Missing required field: name");
    }
}
