package com.spotify.oauth2.tests.BeforeRefactor;

import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiFifteen;
import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiSixteen;
import com.spotify.oauth2.pojo.BeforeRefactor.ErrorTwo;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistFifteen;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistSixteen;
import com.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests_Sixteen {
    /* *****************************************************************************************
    *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
    *  *****************************************************************************************
    *
    *     DESCRIPTION OF CHANGES:
    *         - refactored for Lombok WITH builder tag in the PlaylistSixteen pojo file
     *        - refactored for Lombok WITHOUT builder tag in the ErrorSixteen pojo file
    *         - Class created under src/test/java/api/BeforeRefactor folder
    *  *****************************************************************************************/

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    public PlaylistSixteen playListBuilder(String name, String description, boolean _public){
        return PlaylistSixteen.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }

    public void assertPlayListIsEqual(PlaylistSixteen responsePlayList, PlaylistSixteen requestPlaylist){
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
        PlaylistSixteen requestPlaylist = playListBuilder("New PlayList", "New PlayList description", false);

        Response response = PlaylistApiSixteen.post(requestPlaylist);
        assertStatusCode(response.statusCode(),201);

        PlaylistSixteen responsePlayList = response.as(PlaylistSixteen.class);
        assertPlayListIsEqual(responsePlayList,requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() {
        PlaylistSixteen requestPlaylist = playListBuilder("Updated PlayList name",
                "Updated PlayList description", false);

        Response response = PlaylistApiFifteen.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(),200);

        PlaylistSixteen responsePlayList = response.as(PlaylistSixteen.class);
        assertPlayListIsEqual(responsePlayList,requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist() {
        PlaylistSixteen requestPlaylist = playListBuilder("New PUT PlayList", "New PlayList PUT description", false);
        // using PlaylistApiFive generic methods
        // Refactored this with the DataLoader class
        Response response = PlaylistApiSixteen.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertThat(response.getStatusCode(), equalTo(20));
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithOutName() {
        PlaylistSixteen requestPlaylist = playListBuilder("", "New PlayList description", false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSixteen.post(requestPlaylist);
        assertStatusCode(response.getStatusCode(),400);

        ErrorTwo err = response.as(ErrorTwo.class);
        assertError(err,400,"Missing required field: name");
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String invalid_token = "12345";
        PlaylistSixteen requestPlaylist = playListBuilder("New PlayList", "New PlayList description", false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiSixteen.post(invalid_token,requestPlaylist);
        assertStatusCode(response.getStatusCode(),401);

        ErrorTwo err = response.as(ErrorTwo.class);
        assertError(err,401,"Missing required field: name");
    }
}
