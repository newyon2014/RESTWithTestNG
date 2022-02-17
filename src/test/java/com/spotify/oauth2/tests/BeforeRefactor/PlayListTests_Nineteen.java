package com.spotify.oauth2.tests.BeforeRefactor;

import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiFifteen;
import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiNineteen;
import com.spotify.oauth2.api.applicationApi.BeforeRefactor.PlaylistApiSixteen;
import com.spotify.oauth2.pojo.BeforeRefactor.ErrorTwo;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistNineteen;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistSixteen;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("PlayList API")
public class PlayListTests_Nineteen {
    /* *****************************************************************************************
    *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
    *  *****************************************************************************************
    *     DESCRIPTION OF CHANGES:
    *         - refactored for Java faker changes
    *         - Updated the hard coded Playlist name and Playlist description using java Faker
    *         - Class created under src/test/java/api/BeforeRefactor folder
    *  *****************************************************************************************/

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @Step
    public PlaylistNineteen playListBuilder(String name, String description, boolean _public){
        return PlaylistNineteen.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }

    @Step
    public void assertPlayListIsEqual(PlaylistNineteen responsePlayList, PlaylistNineteen requestPlaylist){
        assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode,int expectedStatusCode){
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    @Step
    public void assertError (ErrorTwo responseError,int expectedStatusCode,String expectedMessage){

        assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));
    }

    @Story("Create Playlist story")
    @Link("https://example.org/mylink/")
    @Link(name="allure",type="mylink")
    @Issue("Defect 3456")
    @TmsLink("TestCase_001")
    @Description("Add description for this test case using this annotation")
    @Test(description = "should be able to create a playlist")
    public void shouldBeAbleToCreatePlayList() {
        PlaylistNineteen requestPlaylist = playListBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);

        Response response = PlaylistApiNineteen.post(requestPlaylist);
        assertStatusCode(response.statusCode(),201);

        PlaylistNineteen responsePlayList = response.as(PlaylistNineteen.class);
        assertPlayListIsEqual(responsePlayList,requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToGetAPlaylist() {
        // We can't use faker utils on this method as it's fetching a specific playlist name
        PlaylistNineteen requestPlaylist = playListBuilder("Updated playlist name",
                "Updated playlist description", false);

        Response response = PlaylistApiFifteen.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(),200);

        PlaylistNineteen responsePlayList = response.as(PlaylistNineteen.class);
        assertPlayListIsEqual(responsePlayList,requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToUpdateAPlaylist() {
        PlaylistNineteen requestPlaylist = playListBuilder(FakerUtils.generateName(), FakerUtils.generateDescription(), false);
        // using PlaylistApiFive generic methods
        // Refactored this with the DataLoader class
        Response response = PlaylistApiNineteen.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertThat(response.getStatusCode(), equalTo(20));
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithOutName() {
        PlaylistNineteen requestPlaylist = playListBuilder("", FakerUtils.generateDescription(), false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiNineteen.post(requestPlaylist);
        assertStatusCode(response.getStatusCode(),400);

        ErrorTwo err = response.as(ErrorTwo.class);
        assertError(err,400,"Missing required field: name");
    }

    @Test
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String invalid_token = "12345";
        PlaylistNineteen requestPlaylist = playListBuilder("New PlayList", "New PlayList description", false);

        // using PlaylistApiFive generic methods
        Response response = PlaylistApiNineteen.post(invalid_token,requestPlaylist);
        assertStatusCode(response.getStatusCode(),401);

        ErrorTwo err = response.as(ErrorTwo.class);
        assertError(err,401,"Missing required field: name");
    }
}
