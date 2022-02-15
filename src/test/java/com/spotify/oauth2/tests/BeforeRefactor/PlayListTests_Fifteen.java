package com.spotify.oauth2.tests.BeforeRefactor;

import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiFifteen;
import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiSix;
import com.spotify.oauth2.pojo.BeforeRefactor.ErrorFifteen;
import com.spotify.oauth2.pojo.BeforeRefactor.ErrorTwo;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistBuilderPatternThree;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistFifteen;
import com.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests_Fifteen {
    /* *****************************************************************************************
    *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
    *  *****************************************************************************************
    *
    *     DESCRIPTION OF CHANGES:
    *         - refactored for Lombok WITHOUT builder tag in the PlaylistFifteen pojo file
    *         - refactored for Lombok WITHOUT builder tag in the ErrorFifteen pojo file
    *         - Only using getter and setter methods
    *         - Class created under src/test/java/api/BeforeRefactor folder
    *  *****************************************************************************************/

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    public PlaylistFifteen playListBuilder(String name, String description, boolean _public){
        PlaylistFifteen playList = new PlaylistFifteen();
        playList.setName(name);
        playList.setDescription(description);
        playList.set_public(_public);
        return playList;
    }

    public void assertPlayListIsEqual(PlaylistFifteen responsePlayList, PlaylistFifteen requestPlaylist){
        assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlaylist.get_public()));
    }

    public void assertStatusCode(int actualStatusCode,int expectedStatusCode){
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    public void assertError (ErrorFifteen responseError,int expectedStatusCode,String expectedMessage){

        assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));
    }

    @Test
    public void shouldBeAbleToCreatePlayList() {
        PlaylistFifteen requestPlaylist = playListBuilder("New PlayList", "New PlayList description", false);

        Response response = PlaylistApiFifteen.post(requestPlaylist);
        assertStatusCode(response.statusCode(),201);

        PlaylistFifteen responsePlayList = response.as(PlaylistFifteen.class);
        assertPlayListIsEqual(responsePlayList,requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() {
        PlaylistFifteen requestPlaylist = playListBuilder("Updated PlayList name",
                "Updated PlayList description", false);

        Response response = PlaylistApiFifteen.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(),200);

        PlaylistFifteen responsePlayList = response.as(PlaylistFifteen.class);
        assertPlayListIsEqual(responsePlayList,requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist() {
        PlaylistFifteen requestPlaylist = playListBuilder("New PUT PlayList", "New PlayList PUT description", false);
        // using PlaylistApiFive generic methods
        // Refactored this with the DataLoader class
        Response response = PlaylistApiFifteen.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertThat(response.getStatusCode(), equalTo(20));
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithOutName() {
        PlaylistFifteen requestPlaylist = playListBuilder("", "New PlayList description", false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiFifteen.post(requestPlaylist);
        assertStatusCode(response.getStatusCode(),400);

        ErrorFifteen err = response.as(ErrorFifteen.class);
        assertError(err,400,"Missing required field: name");
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String invalid_token = "12345";
        PlaylistFifteen requestPlaylist = playListBuilder("New PlayList", "New PlayList description", false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiFifteen.post(invalid_token,requestPlaylist);
        assertStatusCode(response.getStatusCode(),401);

        ErrorFifteen err = response.as(ErrorFifteen.class);
        assertError(err,401,"Missing required field: name");
    }
}
