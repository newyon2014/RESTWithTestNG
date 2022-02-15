package com.spotify.oauth2.api.applicationApi.BeforeRefactor;

import com.spotify.oauth2.api.Route;
import com.spotify.oauth2.api.applicationApi.BeforeRefactorRestResource.RestResourceEleven;
import com.spotify.oauth2.api.applicationApi.BeforeRefactorToken.TokenManagerEight;
import com.spotify.oauth2.pojo.BeforeRefactor.PlaylistNineteen;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.applicationApi.BeforeRefactorToken.TokenManagerEight.getToken;

public class PlaylistApiTwenty {
    /* *****************************************************************************************
     *  ***************   LIST OF ITEMS REFACTORED IN THIS CLASS FILE  **************************
     *  *****************************************************************************************
     *
     *     DESCRIPTION OF CHANGES:
     *         - No change - included only for Enum Status Code refactoring at Test case level
     *         - Class created under src/test/java/api/BeforeRefactor folder
     *  *****************************************************************************************/

   // static String access_token = "PMAK-5ff2d720d2a39a004250e5da-c658c4a8a1cee3516762cb1a51cba6c5e2";

    public static Response post(PlaylistNineteen requestPlaylist){
        return RestResourceEleven.post(Route.USERS + "/" + ConfigLoader.getInstance().getUser() + Route.PLAYLISTS,
                                  getToken(),requestPlaylist);
    }

    public static Response post(String token, PlaylistNineteen requestPlaylist){
        return RestResourceEleven.post(Route.USERS + "/" + ConfigLoader.getInstance().getUser() + Route.PLAYLISTS,token,requestPlaylist);
    }

    public static Response get(String playlistId){
        return RestResourceEleven.get(Route.PLAYLISTS + "/" + playlistId, TokenManagerEight.getToken());
    }

    public static Response update(String playlistId, PlaylistNineteen requestPlaylist){
        return RestResourceEleven.update(Route.PLAYLISTS + "/" + playlistId,TokenManagerEight.getToken(),requestPlaylist);
    }
}
